package tbsc.randomutils;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import tbsc.randomutils.cmd.CommandRU;
import tbsc.randomutils.init.ItemInit;
import tbsc.randomutils.init.MiscInit;
import tbsc.randomutils.init.RecipeInit;
import tbsc.randomutils.proxy.IProxy;
import tbsc.randomutils.reference.Reference;

@Mod(modid = Reference.Mod.MODID, name = Reference.Mod.NAME, version = Reference.Mod.VERSION,
        guiFactory = Reference.Mod.GUI_FACTORY)
public class RandomUtilities {

    @Mod.Instance
    public static RandomUtilities instance;

    @SidedProxy(clientSide = Reference.Mod.CLIENT_PROXY, serverSide = Reference.Mod.SERVER_PROXY)
    public static IProxy proxy;

    public static CreativeTabs tabRu;
    public static Configuration config;

    public static boolean shouldKewlShenanigans;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        config = new Configuration(event.getSuggestedConfigurationFile());

        tabRu = new CreativeTabs(CreativeTabs.getNextID(), Reference.Mod.MODID) { // Creative tab
            @Override
            public Item getTabIconItem() {
                return ItemInit.itemPancake;
            }

            @Override
            public String getTranslatedTabLabel() {
                return "Random Utilities";
            }
        };

        // Registration
        ItemInit.init();
        RecipeInit.init();
        MiscInit.init();
        MiscInit.eventInit();
        syncConfig();
    }

    public static void syncConfig() {
        try {
            // Load config
            config.load();

            // Read props from config
            Property kewlShenanigansProp = config.get(Configuration.CATEGORY_GENERAL, "kewlShenanigans", "true",
                    "that's kewl m8");
            shouldKewlShenanigans = kewlShenanigansProp.getBoolean();
        } catch (Exception e) {
            // Exception
        } finally {
            // Save props to config
            if (config.hasChanged()) config.save();
        }
    }


    @EventHandler
    public void onServerStarting(FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandRU());
    }

}
