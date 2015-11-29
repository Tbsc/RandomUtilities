package tbsc.randomutils.common.block;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import tbsc.randomutils.common.base.BlockCakeRU;

import java.util.Random;

/**
 * @author Tbsc on 29/11/2015, 16:09
 */
public class BlockFunCake extends BlockCakeRU {

    public BlockFunCake() {

    }

    @Override
    public boolean onBlockActivated(World p_149727_1_, int p_149727_2_, int p_149727_3_, int p_149727_4_, EntityPlayer p_149727_5_, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
        doFunCakeEffects(p_149727_1_, p_149727_5_);
        return super.onBlockActivated(p_149727_1_, p_149727_2_, p_149727_3_, p_149727_4_, p_149727_5_, p_149727_6_, p_149727_7_, p_149727_8_, p_149727_9_);
    }

    @Override
    public void onBlockClicked(World p_149699_1_, int p_149699_2_, int p_149699_3_, int p_149699_4_, EntityPlayer p_149699_5_) {
        super.onBlockClicked(p_149699_1_, p_149699_2_, p_149699_3_, p_149699_4_, p_149699_5_);
        doFunCakeEffects(p_149699_1_, p_149699_5_);
    }

    private void doFunCakeEffects(World world, EntityPlayer player) {
        Random r = new Random();
        Effect whatHappens = Effect.fromId(r.nextInt());
        switch (whatHappens) {
            case BLOW_UP:
                world.createExplosion(player, player.posX, player.posY, player.posZ, 2.0F, true);
                player.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "BOOM!"));
                break;
            case TP:
                double x = player.posX - player.posY;
                double z = player.posZ + player.posY;
                double y = world.getTopSolidOrLiquidBlock((int) x, (int) z);
                player.setPosition(x, y, z);
                break;
            case JUMP_HIGH:
                player.addPotionEffect(new PotionEffect(8, 20, 25, true));
                player.addChatMessage(new ChatComponentText("See ya on the moon!"));
                break;
            case RANDOM_POT_EFFECT:
                player.addPotionEffect(new PotionEffect(r.nextInt(23), 30, 2));
                player.addChatMessage(new ChatComponentText("I wonder what did I get... Hmm..."));
                break;
        }
    }

    private enum Effect {

        BLOW_UP(0), TP(1), JUMP_HIGH(2), RANDOM_POT_EFFECT(3),
        NONE(1337),

            X_TOTAL_AMOUNT(4);

        int id;

        Effect(int id) {
            this.id = id;
        }

        static Effect fromId(int id) {
            switch (id) {
                case 0:
                    return BLOW_UP;
                case 1:
                    return TP;
                case 2:
                    return JUMP_HIGH;
                case 3:
                    return RANDOM_POT_EFFECT;
                default:
                    return NONE;
            }
        }

    }

}
