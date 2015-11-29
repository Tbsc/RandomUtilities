package tbsc.randomutils.common.event;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.oredict.OreDictionary;
import tbsc.randomutils.RandomUtilities;
import tbsc.randomutils.common.init.ItemInit;
import tbsc.randomutils.common.init.MiscInit;
import tbsc.randomutils.common.item.ItemMysticalFood;
import tbsc.randomutils.common.misc.CaseHelper;
import tbsc.randomutils.common.misc.FoodHelper;
import tbsc.randomutils.common.misc.FoodType;
import tbsc.randomutils.common.misc.GameRuleManager;
import tbsc.randomutils.common.reference.Reference;

import java.util.ArrayList;
import java.util.List;

public class RUEventHandler {

    /*
    VOID DEATH EVENTS
     */

    private boolean diedByVoid = false; // Death type variable
    private List<ItemStack> inventoryItems = null; // List of pre-death inventory

    @SubscribeEvent
    public void onPlayerDeath(LivingDeathEvent event) { // Gets executed when any living entity dies
        if (!GameRuleManager.getRuleBoolean(event.entity.worldObj.getGameRules(), Reference.Gamerule.RULE_VOIDRESTORE))
            return; // No restore, gamerule option
        if (event.entityLiving.worldObj.getGameRules().getGameRuleBooleanValue("keepInventory") == true)
            return; // Runs only if keepInv is disabled
        if (event.entityLiving instanceof EntityPlayer) { // Is a player?
            EntityPlayer thePlayer = (EntityPlayer) event.entityLiving; // Cast to player
            if (event.source.getDamageType().equalsIgnoreCase("outOfWorld")) { // Is void damage?
                inventoryItems = thePlayer.inventoryContainer.getInventory(); // Save inventory to a list
                diedByVoid = true; // For the respawn event
            }
        }
    }

    @SubscribeEvent
    public void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) { // Gets executed when a player respawns
        if (diedByVoid && inventoryItems != null) { // if the death happened by void + the list isn't null
            for (int i = 0; i < inventoryItems.size(); i++) {
                Container playerInv = event.player.inventoryContainer; // After-death inventory (empty but about to fill)
                playerInv.putStackInSlot(i, inventoryItems.get(i)); // Add the new items
            }
            inventoryItems = null; // Reset the items
            diedByVoid = false; //  Reset the void death counter
        }
    }

    /*
    FLIGHT EVENTS
     */

    private boolean flightStopFall = false; // field used to detect if falling from losing the flight ring

    @SubscribeEvent
    public void onTick(TickEvent.PlayerTickEvent event) { // Gets called every tick
        if (event.phase != TickEvent.Phase.END) return; // I want it to run only at the end of each tick
        EntityPlayer player = event.player; // Player variable
        if (player.inventory.hasItem(ItemInit.itemRing) || player.capabilities.isCreativeMode) // If the player HAS the
            // item, make him be able to fly
            player.capabilities.allowFlying = true; // Set allow flight
        else if (!player.inventory.hasItem(ItemInit.itemRing)) { // If it DOESN'T have the item,
            player.capabilities.allowFlying = false; // then DISABLE flight
            player.capabilities.isFlying = false; // and DISABLE current flight
            flightStopFall = true;
            // Because I don't want any cheaty flight, have it or no flight for ya!
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGH) // it's high priority, cuz I want him to NOT get damage!
    public void onLivingFall(LivingFallEvent event) { // When an entity falls
        if (event.entityLiving instanceof EntityPlayer) // check is it a player?
            if (flightStopFall) { // it is, so check iff falling from flight, then
                flightStopFall = false; // clear field and
                event.setCanceled(true); // disable death/damage
            }
    }

    /*
    MYSTICAL FOOD
     */

    @SubscribeEvent
    public void onItemPickup(PlayerEvent.ItemPickupEvent event) { // executed when picking up items
        if (!RandomUtilities.shouldKewlShenanigans) return; // Me = sad :c, disabled by config

        ItemStack slot8Item = event.player.inventory.getStackInSlot(7); // get the itemstack in slot 8 (no. 7)
        int[] droppedOreEntries = OreDictionary.getOreIDs(event.pickedUp.getEntityItem()); // ore dictionary entries for picked up
        List<String> droppedOreEntriesName = new ArrayList<String>(); // List that will get populated when converting id -> name

        for (int i = 0; i < droppedOreEntries.length; i++) { // loop through id ore entries
            droppedOreEntriesName.add(OreDictionary.getOreName(droppedOreEntries[i])); // populate ore name list
        }

        if (slot8Item == null) return; // if slot 8 itemstack is null, then stop

        if (slot8Item.getItem() == null) return; // if slot 8 item is null, then stop

        // If slot 8 has the mystical food "generic" (any) type, then continue
        if (slot8Item.getItem() == ItemInit.itemMysticalFood && slot8Item.getItem() instanceof ItemMysticalFood) {
            ItemMysticalFood mysticalFood = (ItemMysticalFood) slot8Item.getItem(); // Cast to mystical food type, so
            // I can check to see what mystical food type it is, so if it is a pineapple, then continue
            if (CaseHelper.getFoodTypeFromInt(mysticalFood.foodType).getId() == FoodType.PINEAPPLE.getId()) {
                boolean isOre = false; // boolean to check whether picked up is an ore
                String toBeIngotType = null; // ore dictionary name AFTER converting to ingot (only ingots!)

                for (int i = 0; i < droppedOreEntriesName.size(); i++) { // loop through the ore's entries
                    String oreName = droppedOreEntriesName.get(i); // get the current ore name entry
                    if (oreName.contains("ore")) { // if it contains ore, then it IS an ore, so continue
                        isOre = true; // set boolean that it IS an ore
                        toBeIngotType = "ingot" + oreName.substring(3); // define ingot type
                    }
                }

                if (isOre) { // using boolean, check if it is an ore
                    InventoryPlayer inv = event.player.inventory; // get inventory

                    if (OreDictionary.getOres(toBeIngotType).isEmpty()) { // if no ingots exist (at least defined)
                        return; // then stop
                    }

                    ItemStack ingotType = OreDictionary.getOres(toBeIngotType).get(0); // get the ingot type, as itemstack
                    ingotType.stackSize = 2; // set stack size to 2, as we want ore multiplication!
                    inv.addItemStackToInventory(ingotType); // add the ingots to the player's inv
                    ItemStack pickedUp = event.pickedUp.getEntityItem(); // will check type of picked ore, rn saving itemstack

                    for (Slot slot : (ArrayList<Slot>) event.player.inventoryContainer.inventorySlots) { // loop through inv slots
                        if (slot.getStack() == null) { // if slot is empty (null), then skip (I don't want any NullPointerExceptions!)
                            continue;
                        }

                        if (slot.getStack().getItem() == pickedUp.getItem()) { // if item is the ore picked up
                            slot.decrStackSize(pickedUp.stackSize); // then delete it, as we don't want infinite ingots
                        }
                    }

                    event.player.addChatMessage(new ChatComponentText("m4k3 1t r41n!")); // self explanatory, but M4K3 1T R41N!
                }
            }
        }
    }

    @SubscribeEvent
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK || event.action == PlayerInteractEvent.Action.RIGHT_CLICK_AIR) {
            ItemStack stack = event.entityPlayer.getCurrentEquippedItem();
            if (stack != null) {
                if (stack.getItem() != null) {
                    if (stack.isItemEnchanted()) {
                        if (EnchantmentHelper.getEnchantmentLevel(137, stack) >= 1) {
                            FoodHelper.eatItem(stack, event.entityPlayer);
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void onInventoryUpdate(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.START && event.side == Side.SERVER) {
            if (event.player.inventory.hasItem(ItemInit.itemMysticalFood)) {
                boolean hasMysticalFood = event.player.inventory.hasItemStack(new ItemStack(ItemInit.itemMysticalFood));
                if (hasMysticalFood) {
                    ItemStack foodStack = null;
                    for (int i = 0; i < event.player.inventory.mainInventory.length; i++) {
                        ItemStack stack = event.player.inventory.mainInventory[i];
                        if (stack != null) {
                            if (stack.getItem() == ItemInit.itemMysticalFood) {
                                foodStack = stack;
                            }
                        }
                    }
                    if (!foodStack.isItemEnchanted())
                        foodStack.addEnchantment(MiscInit.enchantmentTasty, 1);
                }
            }
        }
    }

}
