package tbsc.randomutils.common.base;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import tbsc.randomutils.RandomUtilities;
import tbsc.randomutils.common.reference.Reference;

public class ItemBaseRU extends Item {

    protected String unlocalized;

    public ItemBaseRU() {
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
