package tbsc.randomutils.common.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import tbsc.randomutils.common.base.ItemBaseRU;
import tbsc.randomutils.common.reference.Reference;
import tbsc.randomutils.common.misc.CaseHelper;

import java.util.List;
import java.util.Random;

public class ItemChattier extends ItemBaseRU {

    private String unlocalized;

    public ItemChattier() {
        unlocalized = "itemChattier";
        setUnlocalizedName(unlocalized);
        this.setMaxStackSize(1);
    }

    @Override
    public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side,
                                  float hitX, float hitY, float hitZ) {
        Random r = new Random();
        player.addChatMessage(new ChatComponentText(CaseHelper.switchChattier(r.nextInt(8))));
        return true;
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List lore, boolean p_77624_4_) {
        lore.add("This item will say stuff in chat!");
    }

    @Override
    public void registerIcons(IIconRegister iconRegister) {
        this.itemIcon = iconRegister.registerIcon(Reference.Mod.TEXTURE_PREFIX + ":" + unlocalized);
    }

}
