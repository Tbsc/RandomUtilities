package tbsc.randomutils.common.cmd;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.FoodStats;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tbsc
 * @usage This class doesn't really work rn; gonna fix it, just not high priority
 */

public class CommandRU implements ICommand {

    private List aliases;
    private String name;
    private String usage;

    public CommandRU() {
        this.aliases = new ArrayList<String>();
        this.aliases.add("ru");
        this.aliases.add("randomutilities");
        this.aliases.add("randomutils");

        this.name = "ru";

        this.usage = "Usage: /ru <subcommand> or /ru help for help";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("help")) {
                triggerHelpSubcommand(sender);
                return;
            }
            if (args[0].equalsIgnoreCase("heal")) {
                triggerHealSubcommand(sender, args);
                return;
            }
            if (args[0].equalsIgnoreCase("damage")) {
                triggerDamageCommand(sender, args);
                return;
            }
            if (args[0].equalsIgnoreCase("feed")) {
                triggerFeedCommand(sender, args);
                return;
            }
            if (args[0].equalsIgnoreCase("starve")) {
                triggerStarveCommand(sender, args);
                return;
            }
            sender.addChatMessage(new ChatComponentText(getCommandUsage(sender)));
            return;
        }
        if (args.length == 0) {
            sender.addChatMessage(new ChatComponentText(getCommandUsage(sender)));
            return;
        }
    }

    private void triggerHelpSubcommand(ICommandSender sender) {
        sender.addChatMessage(new ChatComponentText("*something* = required | <something> = optional"));
        sender.addChatMessage(new ChatComponentText("/ru help - Outputs possible commands"));
        sender.addChatMessage(new ChatComponentText("/ru heal <amount of health> <player> - Heals defined/sender in amount/max"));
        sender.addChatMessage(new ChatComponentText("/ru damage *amount* <player> - Damages defined/sender"));
        sender.addChatMessage(new ChatComponentText("/ru feed <amount> <player> - Feeds defined/sender in amount/max"));
        sender.addChatMessage(new ChatComponentText("/ru starve *amount* <player> - Removes hunger from defined/sender"));
    }

    private void triggerHealSubcommand(ICommandSender sender, String[] args) {
        if (sender instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) sender;
            if (args.length == 1) {
                player.setHealth(player.getMaxHealth());
                player.setFire(0);
                return;
            }
            if (args.length == 2) {
                if (NumberUtils.isNumber(args[1])) {
                    player.heal(Float.parseFloat(args[1]));
                    player.setFire(0);
                    return;
                }
                EntityPlayer otherPlayer = player.worldObj.getPlayerEntityByName(args[1]);
                otherPlayer.setHealth(otherPlayer.getMaxHealth());
                otherPlayer.setFire(0);
            }
            if (args.length == 3) {
                EntityPlayer otherPlayer = player.worldObj.getPlayerEntityByName(args[2]);
                if (otherPlayer == null) {
                    sender.addChatMessage(new ChatComponentText("Other player not found!"));
                    return;
                }
                player.heal(Float.parseFloat(args[1]));
                player.setFire(0);
                return;
            }
        }
        sender.addChatMessage(new ChatComponentText("Not a player!"));
    }

    private void triggerDamageCommand(ICommandSender sender, String[] args) {
        if (sender instanceof EntityPlayer) { // Is a player?
            EntityPlayer player = (EntityPlayer) sender; // Cast to player
            if (args.length == 1) { // No damage amount
                player.addChatMessage(new ChatComponentText("Damage amount is required!")); // Notify
                return; // Abort
            }
            if (args.length == 2) { // Damage is specified
                if (NumberUtils.isNumber(args[1])) { // Is it a real number
                    player.setHealth(player.getHealth() - Float.parseFloat(args[1])); // Damage player
                    return; // Abort
                }
                player.addChatMessage(new ChatComponentText("Damage amount is not numeric!"));
            }
            if (args.length == 3) {
                EntityPlayer otherPlayer = player.worldObj.getPlayerEntityByName(args[2]);
                if (otherPlayer == null) {
                    player.addChatMessage(new ChatComponentText("Other player not found!"));
                    return;
                }
                otherPlayer.setHealth(otherPlayer.getHealth() - Float.parseFloat(args[1]));
                return;
            }
        }
        sender.addChatMessage(new ChatComponentText("Not a player!"));
    }

    private void triggerFeedCommand(ICommandSender sender, String[] args) {
        if (sender instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) sender;
            FoodStats stats = player.getFoodStats();
            if (args.length == 1) {
                stats.setFoodLevel(20);
                stats.setFoodSaturationLevel(20);
                return;
            }
            if (args.length == 2) {
                if (NumberUtils.isNumber(args[1])) {
                    stats.setFoodLevel(stats.getFoodLevel() + Integer.parseInt(args[1]));
                    return;
                }
                EntityPlayer otherPlayer = player.worldObj.getPlayerEntityByName(args[1]);
                FoodStats otherStats = otherPlayer.getFoodStats();
                otherStats.setFoodLevel(20);
                otherStats.setFoodSaturationLevel(20);
                return;
            }
            if (args.length == 3) {
                EntityPlayer otherPlayer = player.worldObj.getPlayerEntityByName(args[2]);
                if (otherPlayer == null) {
                    sender.addChatMessage(new ChatComponentText("Other player not found!"));
                    return;
                }
                FoodStats otherStats = otherPlayer.getFoodStats();
                otherStats.setFoodLevel(Integer.parseInt(args[1]));
                return;
            }
        }
        sender.addChatMessage(new ChatComponentText("Not a player!"));
    }

    private void triggerStarveCommand(ICommandSender sender, String[] args) {
        if (sender instanceof EntityPlayer) { // Is a player?
            EntityPlayer player = (EntityPlayer) sender; // Cast to player
            FoodStats stats = player.getFoodStats();
            if (args.length == 1) { // No amount
                player.addChatMessage(new ChatComponentText("Amount is required!")); // Notify
                return; // Abort
            }
            if (args.length == 2) { // Amount is specified
                if (NumberUtils.isNumber(args[1])) { // Is it a real number
                    stats.setFoodLevel(stats.getFoodLevel() - Integer.parseInt(args[1])); // Starve player
                    return; // Abort
                }
                player.addChatMessage(new ChatComponentText("Amount is not numeric!"));
                return;
            }
            if (args.length == 3) {
                EntityPlayer otherPlayer = player.worldObj.getPlayerEntityByName(args[2]);
                if (otherPlayer == null) {
                    player.addChatMessage(new ChatComponentText("Other player not found!"));
                    return;
                }
                FoodStats otherStats = otherPlayer.getFoodStats();
                otherStats.setFoodLevel(otherStats.getFoodLevel() - Integer.parseInt(args[1]));
                return;
            }
        }
        sender.addChatMessage(new ChatComponentText("Not a player!"));
    }

    @Override
    public String getCommandName() {
        return this.name;
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return this.usage;
    }

    @Override
    public List getCommandAliases() {
        return aliases;
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }

    @Override
    public List addTabCompletionOptions(ICommandSender sender, String[] args) {
        return null;
    }

    @Override
    public boolean isUsernameIndex(String[] p_82358_1_, int p_82358_2_) {
        return false;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }

}
