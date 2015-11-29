package tbsc.randomutils.common.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import tbsc.randomutils.common.base.ItemFoodRU;
import tbsc.randomutils.common.reference.Reference;

public class ItemPancake extends ItemFoodRU {

    private String unlocalized;

    public ItemPancake() {
        super(9, 3, false);
        unlocalized = "foodPancake";
        setUnlocalizedName(unlocalized);
        this.setAlwaysEdible();
        this.setPotionEffect(10, 2, 1, Float.MAX_VALUE);
    }

    @Override
    public void registerIcons(IIconRegister iconRegister) {
        this.itemIcon = iconRegister.registerIcon(Reference.Mod.TEXTURE_PREFIX + ":" + unlocalized);
    }
}
