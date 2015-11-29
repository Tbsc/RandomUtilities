package tbsc.randomutils.common.misc;

public class CaseHelper {

    public static String switchChattier(int randomCase) {
        switch (randomCase) {
            case 0:
                return "I HATE HOMEWORK!";
            case 1:
                return "swag";
            case 2:
                return "Is it cool if I cooled a cooler while it's cold outside?";
            case 3:
                return "y u do dis to meh";
            case 4:
                return "meow";
            case 5:
                return "i gotta go";
            case 6:
                return "*poop*";
            case 7:
                return "Are you sure that if I'm goi.... *sleeps*";
        }
        return "WTF IT SHOULDN'T HAPPEN WAAAA";
    }

    public static FoodType getFoodTypeFromInt(int foodInt) {
        switch (foodInt) {
            case 0:
                return FoodType.PINEAPPLE;
            case 1:
                return FoodType.APPLE;
            case 2:
                return FoodType.PEAR;
            case 3:
                return FoodType.POOP;
            case 4:
                return FoodType.MANGO;
            case 5:
                return FoodType.BANANA;
            default:
                return FoodType.APPLE;
        }
    }

}
