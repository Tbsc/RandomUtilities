package tbsc.randomutils.reference;

import java.util.Locale;

public class Reference {

    public static class Mod {
        public static final String MODID = "RandomUtilities";
        public static final String VERSION = "0.1";
        public static final String NAME = "Random Utilities";
        public static final String CLIENT_PROXY = "tbsc.randomutils.client.proxy.ClientProxy";
        public static final String SERVER_PROXY = "tbsc.randomutils.proxy.ServerProxy";
        public static final String GUI_FACTORY = "tbsc.randomutils.client.gui.RUModGuiFactory";
        public static final String TEXTURE_PREFIX = MODID.toLowerCase(Locale.US);
    }

    public static class Gamerule {
        public static final String RULE_VOIDRESTORE = "doVoidRestore";
    }

}
