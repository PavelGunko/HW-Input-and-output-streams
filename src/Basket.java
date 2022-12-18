import java.io.*;
import java.util.Scanner;

public class Basket implements Serializable {

    protected String[] names;
    protected int[] prices;
    protected int[] amounts;


    //конструктор, принимающий массив цен и названий продуктов;
    public Basket(String[] names, int[] prices) {
        this.names = names;
        this.prices = prices;
        this.amounts = new int[names.length];


    }

    //конструтор по умолчанию для сериализации, для Bin должен быть private
    public Basket() {

    }

    //метод добавления amount штук продукта номер productNum в корзину;
    public void addToBasket(int productNum, int amount) {
        amounts[productNum] += amount;
    }

    //вывод карзины
    public void printCart() {
        int sumProducts = 0;        //итоговая сумма корзины
        System.out.println("Ваша корзина: ");
        for (int i = 0; i < names.length; i++) {
            if (amounts[i] > 0) {
                System.out.println(names[i] + " " + amounts[i] + " шт. "
                        + prices[i] + "руб./шт. " + amounts[i] * prices[i] + " руб. в сумме");
            }
            sumProducts += amounts[i] * prices[i];
        }
        System.out.println("Итого: " + sumProducts + " руб.");
    }

    //метод сохранения корзины в текстовый файл;


    public void saveBin(File textFile) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(textFile));) {
            out.writeObject(this);

        }
    }
    /*статический(!) метод восстановления объекта корзины из текстового
         файла, в который ранее была она сохранена;*/
    public static Basket loadFromBin(File textFile) throws IOException, ClassNotFoundException { // если будет объект класса которого нет в проекте
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(textFile));) {
            return (Basket) in.readObject();
        }
    }

}

