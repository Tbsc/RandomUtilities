package tbsc.randomutils.init;

import cpw.mods.fml.common.registry.GameRegistry;
import tbsc.randomutils.base.ItemBaseRU;
import tbsc.randomutils.base.ItemFoodRU;
import tbsc.randomutils.item.*;

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
