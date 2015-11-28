package tbsc.randomutils.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import tbsc.randomutils.base.ItemBaseRU;
import tbsc.randomutils.reference.Reference;

public class ItemFlightRing extends ItemBaseRU {

    private String unlocalized;

    public ItemFlightRing() {
        this.unlocalized = "itemFlightRing";
        setUnlocalizedName(this.unlocalized);
        this.setMaxStackSize(1);
    }

    @Override
    public void registerIcons(IIconRegister iconRegister) {
        this.itemIcon = iconRegister.registerIcon(Reference.Mod.TEXTURE_PREFIX + ":" + unlocalized);
    }

}
