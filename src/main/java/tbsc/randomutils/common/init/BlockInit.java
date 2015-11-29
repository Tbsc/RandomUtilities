package tbsc.randomutils.common.init;

import cpw.mods.fml.common.registry.GameRegistry;
import tbsc.randomutils.common.base.BlockCakeRU;
import tbsc.randomutils.common.block.BlockFunCake;

public class BlockInit {

    public static BlockCakeRU blockFunCake;

    public static void init() {
        blockFunCake = new BlockFunCake();

        GameRegistry.registerBlock(blockFunCake, "blockFunCake");
    }

}
