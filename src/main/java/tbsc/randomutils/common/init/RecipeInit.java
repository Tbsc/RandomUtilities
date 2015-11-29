package tbsc.randomutils.common.init;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import tbsc.randomutils.common.item.ItemPancake;

public class RecipeInit {

    public static void init() {
        GameRegistry.addRecipe(new ItemStack(ItemInit.itemChattier),
                "GI ",
                "IPI",
                " I ",
                'G', Items.gold_ingot, 'I', Items.iron_ingot, 'P', Items.paper);
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemInit.itemMysticalFood, 8),
                " AA",
                "AAA",
                "AA ",
                'A', "listAllFood"));
        if (Loader.isModLoaded("harvestcraft"))
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemInit.itemPancake, 4),
                    "   ",
                    "WEW",
                    "PS ",
                    'W', Items.wheat, 'E', Items.egg, 'P', "pan" , 'S', Items.sugar));
        else
            GameRegistry.addRecipe(new ItemStack(ItemInit.itemPancake, 4),
                    "   ",
                    "WEW",
                    " S ",
                    'W', Items.wheat, 'E', Items.egg, 'S', Items.sugar);
    }

}
