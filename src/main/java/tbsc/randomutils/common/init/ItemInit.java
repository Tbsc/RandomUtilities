package tbsc.randomutils.common.init;

import cpw.mods.fml.common.registry.GameRegistry;
import tbsc.randomutils.common.base.ItemBaseRU;
import tbsc.randomutils.common.base.ItemFoodRU;
import tbsc.randomutils.common.item.*;

public class ItemInit {

    public static ItemBaseRU itemChattier;
    public static ItemBaseRU itemRing;

    public static ItemFoodRU itemPancake;
    public static ItemFoodRU itemMysticalFood;

    public static void init() {
        itemPancake = new ItemPancake();
        itemChattier = new ItemChattier();
        itemRing = new ItemFlightRing();
        itemMysticalFood = new ItemMysticalFood();

        GameRegistry.registerItem(itemPancake, "foodPancake");
        GameRegistry.registerItem(itemChattier, "itemChattier");
        GameRegistry.registerItem(itemRing, "itemFlightRing");
        GameRegistry.registerItem(itemMysticalFood, "foodMystical");
    }

}
