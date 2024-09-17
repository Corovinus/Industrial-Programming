import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        IArray array = new Array();
        array.setArray();
        System.out.println("Start array:");
        array.coutArray();

        //Задание 1
        // Поменять местами строку, содержащую элемент с наибольшим значением в матрице со
        //строкой, содержащей элемент с наименьшим значением. Вывести на экран полученную
        //матрицу. Для каждой строки с нулевым элементом на главной диагонали вывести ее номер
        //и значение наибольшего из элементов этой строки.

        array.swapArray();
        System.out.println("End array:");
        array.coutArray();


        if (!array.diagonalAnswer()) {
            System.out.println("No diagonal answer\n");
        }

        //Задание 2.
        // Найти максимальное из чисел, встречающихся в заданной матрице ровно два раза.

        System.out.println("Two max elements in array: ");
        int max = array.findMaxTwoTimes();
        if (max == -1)
            System.out.println("Array does not contain two same elements");
        else
            System.out.println(max + "\n");


        //Задание 3.
        // Упорядочить ее столбцы по неубыванию их первых элементов.

        System.out.println("Array with sorted columns in non-decreasing order of their first elements:");
        array.swapColumnsNonDec();
        array.coutArray();
    }
}