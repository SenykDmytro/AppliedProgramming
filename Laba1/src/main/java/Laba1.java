import java.io.File;

/**
 * @author Senyk Dmytro
 * @version 0.2
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
            }
            else if (N==2){
                System.out.println(str+3);
            }
            else{
                Numbers numbers =new Numbers(N);
                int sum=numbers.getSum();
                System.out.println(str+sum);
            }
        }
        catch (NumberFormatException exception){
            System.out.println("Неправильний тип введеного значення");
        }
        catch (WrongDataValue exception) {
            exception.message("Значення замале");
        }
    }
}

