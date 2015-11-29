package tbsc.randomutils.common.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import tbsc.randomutils.common.base.ItemFoodRU;
import tbsc.randomutils.common.reference.Reference;

import java.util.List;
import java.util.Random;

public class ItemMysticalFood extends ItemFoodRU {

    private String unlocalized;
    public int foodType;

    public ItemMysticalFood() {
        super(7, 7, false);
        Random r = new Random();
        this.foodType = r.nextInt(6);
        switch(foodType) {
            case 0:
                this.unlocalized = "foodMysticalPineapple";
                break;
            case 1:
                this.unlocalized = "foodMysticalApple";
                break;
            case 2:
                this.unlocalized = "foodMysticalPear";
                break;
            case 3:
                this.unlocalized = "foodMysticalPoop";
                break;
            case 4:
                this.unlocalized = "foodMysticalMango";
                break;
            case 5:
                this.unlocalized = "foodMysticalBanana";
                break;
        }
        setUnlocalizedName(unlocalized);
        this.setTextureName(this.unlocalized);
    }

    @Override
    public void registerIcons(IIconRegister iconRegister) {
        this.itemIcon = iconRegister.registerIcon(Reference.Mod.TEXTURE_PREFIX + ":" + this.unlocalized);
    }

    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List lore, boolean p_77624_4_) {
        lore.add("Mystical fruit is mystical");
        lore.add("It really is magical!");
        lore.add("");
    }
}
