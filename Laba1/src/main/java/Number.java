/**
 *  Клас для обгортки числа Люка
 */
public class Number{
    private int value;
    private int place;
    public Number(int value, int place) {
        this.value = value;
        this.place = place;
    }
    public int getPlace() {
        return place;
    }
    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Number{" +
                "value=" + value +
                ", place=" + place +
                '}';
    }
}
