package tbsc.randomutils.init;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.MinecraftForge;
import tbsc.randomutils.event.RUEventHandler;
import tbsc.randomutils.misc.EnchantmentTasty;
import tbsc.randomutils.misc.GameRuleManager;

public class MiscInit {

    public static EnchantmentTasty enchantmentTasty = new EnchantmentTasty();

    public static void init() {
        ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST, new WeightedRandomChestContent(new ItemStack(ItemInit.itemRing),
                1, 1, 100));
        Enchantment.addToBookList(enchantmentTasty);
    }

    public static void eventInit() {
        RUEventHandler eventHandler = new RUEventHandler();
        MinecraftForge.EVENT_BUS.register(eventHandler);
        FMLCommonHandler.instance().bus().register(eventHandler);

        GameRuleManager gameRuleManager = new GameRuleManager();
        MinecraftForge.EVENT_BUS.register(gameRuleManager);
    }

}
