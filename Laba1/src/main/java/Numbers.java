public class Numbers {
    private Number[] arr_numbers;
    private int sum;
    public Numbers(int N) {
        this.arr_numbers=new Number[N];
        this.arr_numbers[0]=new Number(1,1);
        this.arr_numbers[1]=new Number(3,2);
        for (int i = 2; i < arr_numbers.length; i++) {
            arr_numbers[i]=new Number(setNumber(arr_numbers[i-1].getValue(),arr_numbers[i-2].getValue()),i+1);
        }
        setSum();
    }

    public Number[] getArr_numbers() {
        return arr_numbers;
    }

    /**
     * @return суму
     */
    public int getSum() {
        return sum;
    }

    /**
     * Пошук суми N перших чисел Люка
     * @param numbers: масив чисел Люка
     * @return повертає суму ряду
     */
    private int pre_setSum(Number[] numbers){
        int sum=0;
        for (Number n : numbers) {
            sum+=n.getValue();
        }
        return sum;
    }

    /**
     * Пошук числа Люка
     * @param x1 i-1
     * @param x2 i-2
     * @return повертає суму x1 і x2
     */
    private   int setNumber(int x1, int x2){
        return x1+x2;
    }

    /**
     *  Встановлюєм значення суми
     */
    private void setSum() {
        this.sum = pre_setSum(this.arr_numbers);
    }

}
