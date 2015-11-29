package tbsc.randomutils.common.base;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import tbsc.randomutils.RandomUtilities;
import tbsc.randomutils.common.reference.Reference;

import java.util.Set;

public class ItemToolRU extends ItemTool {

    protected ItemToolRU(float damage, ToolMaterial material, Set idk) {
        super(damage, material, idk);
        this.setCreativeTab(RandomUtilities.tabRu);
    }

    @Override
    public String getUnlocalizedName() {
        return String.format("item.%s%s", Reference.Mod.MODID + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        return String.format("item.%s%s", Reference.Mod.MODID + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    protected String getUnwrappedUnlocalizedName(String unlocalizedName) {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }

}
