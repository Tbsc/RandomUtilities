package tbsc.randomutils.common.misc;

/**
 * @author Tbsc on 27/10/2015, 19:19
 */
public enum FoodType {

    PINEAPPLE(0), APPLE(1), PEAR(2), POOP(3), MANGO(4), BANANA(5);

    public int id;

    FoodType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
