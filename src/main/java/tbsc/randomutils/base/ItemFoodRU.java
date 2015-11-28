package tbsc.randomutils.base;

import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import tbsc.randomutils.RandomUtilities;
import tbsc.randomutils.reference.Reference;

public class ItemFoodRU extends ItemFood {

    public ItemFoodRU(int p_i45339_1_, float p_i45339_2_, boolean p_i45339_3_) {
        super(p_i45339_1_, p_i45339_2_, p_i45339_3_);
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
