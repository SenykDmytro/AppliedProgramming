/**
 * @author Senyk Dmytro
 * @version 0.1
 */
public class Laba1 {
    /**
     * main метод який виконує завдання:
     * обробка виключень
     * отримання результату
     * @param args: перший аргумент вказує на кількість чисел в ряді
     */
    public static void main(String[] args) {
        try {
            int N= Integer.parseInt(args[0]);
            if (N<1)throw new WrongDataValue();
            String str="Сума N перших чисел Люка рівна ";
            if (N==1){
                System.out.println(str+1);
                return;
            }
            else if (N==2){
                System.out.println(str+3);
                return;
            }
            Number numbers[]= new Number[N+1];
            int L0=2;
            numbers[0]=new Number(L0,0);
            numbers[1]=new Number(1,1);
            for (int i = 2; i < numbers.length; i++) {
                numbers[i]=new Number(getNumber(numbers[i-1].getValue(),numbers[i-2].getValue()),i);
            }
            int sum=getSum(numbers);
            System.out.println(str+sum);
        }
        catch (NumberFormatException exception){
            System.out.println("Неправильний тип введеного значення");
        }
        catch (WrongDataValue exception) {
            exception.message("Значення замале");
        }
    }

    /**
     * Пошук суми N перших чисел Люка
     * @param numbers: масив чисел Люка
     * @return повертає суму ряду
     */
    public static int getSum(Number[] numbers){
        int sum=0;
        for (Number n : numbers) {
            sum+=n.getValue();
        }
        sum-=2;
        return sum;
    }

    /**
     * Пошук числа Люка
     * @param x1 i-1
     * @param x2 i-2
     * @return повертає суму x1 і x2
     */
    public static int getNumber(int x1,int x2){
        return x1+x2;
    }
}
/**
 *  Клас для обгортки числа Люка
 */
class Number{
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
}