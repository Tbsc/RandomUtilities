package tbsc.randomutils.misc;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;

/**
 * @author Tbsc on 5/11/2015, 17:00
 */
public class EnchantmentTasty extends Enchantment {

    public EnchantmentTasty() {
        super(137, 1, EnumEnchantmentType.all);
    }

    @Override
    public boolean canApply(ItemStack stack) {
        return stack != null && stack.getItem() instanceof ItemFood;
    }

    @Override
    public String getName() {
        return "Tasty";
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return stack != null && stack.getItem() instanceof ItemFood;
    }

    @Override
    public boolean isAllowedOnBooks() {
        return false;
    }
}
