package tbsc.randomutils.common.misc;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.world.GameRules;
import net.minecraftforge.event.world.WorldEvent;
import tbsc.randomutils.common.reference.Reference;

/**
 * @author Tbsc on 29/10/2015, 22:26
 */
public class GameRuleManager {

    private static void addRule(GameRules rules, String name, String defaultValue) {
        if (!rules.hasRule(name)) rules.addGameRule(name, defaultValue);
    }

    public static boolean getRuleBoolean(GameRules rules, String ruleKey) {
        return rules.getGameRuleBooleanValue(ruleKey);
    }

    @SubscribeEvent
    public void onWorldLoad(WorldEvent.Load evt) {
        final GameRules rules = evt.world.getGameRules();
        addRule(rules, Reference.Gamerule.RULE_VOIDRESTORE, "true");
    }

}
