import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static void divide() {
        System.out.print("---------------------------------------\n");
    }
    public static void main(String[] args) {
        IArray array = new Array();
        array.setArrayFromFile("input.txt");
        System.out.println("Start array:");
        array.coutArray();

        //Задание 1
        //Поменять местами строку, содержащую элемент с наибольшим значением в матрице со
        //строкой, содержащей элемент с наименьшим значением. Вывести на экран полученную
        //матрицу. Для каждой строки с нулевым элементом на главной диагонали вывести ее номер
        //и значение наибольшего из элементов этой строки.

        array.swapArray();
        System.out.println("End array:");
        array.coutArray();
        divide();

        array.setArrayFromFile("input.txt");

        if (!array.diagonalAnswer()) {
            System.out.println("No diagonal answer\n");
        }
        divide();

        //Задание 2.
        // Найти максимальное из чисел, встречающихся в заданной матрице ровно два раза.

        array.setArrayFromFile("input.txt");
        array.coutArray();
        System.out.println("Two max elements in array: ");
        int max = array.findMaxTwoTimes();
        if (max == -1)
            System.out.println("Array does not contain two same elements");
        else
            System.out.println(max + "\n");
        divide();


        //Задание 3.
        // Упорядочить ее столбцы по неубыванию их первых элементов.
        array.setArrayFromFile("input.txt");
        array.coutArray();
        System.out.println("Array with sorted columns in non-decreasing order of their first elements:");
        array.swapColumnsNonDec();
        array.coutArray();
        divide();

    }
}