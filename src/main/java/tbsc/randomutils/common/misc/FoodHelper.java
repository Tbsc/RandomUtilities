package tbsc.randomutils.common.misc;

import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.network.play.client.C0APacketAnimation;

/**
 * @author Tbsc on 29/11/2015, 18:09
 */
public class FoodHelper {

    /**
     * This method will make any player "eat" any item (doesn't have to be a food item!)
     * @param stack the item to be "eaten"
     * @param player the player that's about to "eat"
     */
    public static void eatItem(ItemStack stack, EntityPlayer player) {
        if (!player.worldObj.isRemote) {
            NetHandlerPlayServer handler = ((EntityPlayerMP) player).playerNetServerHandler;
            handler.processAnimation(new C0APacketAnimation(player, 3));
            stack.stackSize =- stack.stackSize;
            int plusFood = (int) Math.floor(stack.getDisplayName().length() / 2);
            player.getFoodStats().setFoodLevel(player.getFoodStats().getFoodLevel() + plusFood);
        }
    }

}
