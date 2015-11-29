package tbsc.randomutils.client.gui;

import cpw.mods.fml.client.config.GuiConfig;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import tbsc.randomutils.RandomUtilities;
import tbsc.randomutils.common.reference.Reference;

/**
 * @author Tbsc on 29/10/2015, 19:39
 */
public class ConfigGuiRU extends GuiConfig {

    public ConfigGuiRU(GuiScreen parentScreen) {
        super(parentScreen,
                new ConfigElement(RandomUtilities.config.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(),
                Reference.Mod.MODID, true, false, GuiConfig.getAbridgedConfigPath(RandomUtilities.config.toString()));
    }


}
