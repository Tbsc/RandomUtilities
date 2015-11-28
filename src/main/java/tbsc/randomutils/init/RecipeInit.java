package tbsc.randomutils.init;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class RecipeInit {

    public static void init() {
        GameRegistry.addRecipe(new ItemStack(ItemInit.itemChattier),
                "GI ",
                "IPI",
                " I ",
                'G', Items.gold_ingot, 'I', Items.iron_ingot, 'P', Items.paper);
    }

}
