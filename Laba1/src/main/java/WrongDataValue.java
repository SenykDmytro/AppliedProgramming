/**
 * Виключення для значень того ж типу, але яке не входить в діапазон
 */
public class WrongDataValue extends Exception{
    public void message(String str){
        System.out.println(str);
    }
}
