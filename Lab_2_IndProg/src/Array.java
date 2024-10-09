import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
public class Array implements IArray {
    int[][] array;
    public void setArray() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of lines ");
        int n = sc.nextInt();
        System.out.println("Enter the number of columns ");
        int m = sc.nextInt();
        array = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                array[i][j] = sc.nextInt();
            }
        }
    }

    @Override
    public void setArrayFromFile(String filePath) {
        try {
            Scanner sc = new Scanner(new File(filePath));

            int n = sc.nextInt();
            int m = sc.nextInt();

            array = new int[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    array[i][j] = sc.nextInt();
                }
            }

            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    @Override
    public int[][] getArray() {
        return array;
    }

    @Override
    public void coutArray() {
        for (int[] ints : array) {
            for (int j = 0; j < ints.length; j++) {
                System.out.print(ints[j]);
                if (j < ints.length - 1) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    protected int getMaxValue() {
        int maxValue = array[0][0];
        for (int[] ints : array) {
            for (int anInt : ints) {
                if (anInt > maxValue) {
                    maxValue = anInt;
                }
            }
        }
        return maxValue;
    }

    protected int getMinValue() {
        int minValue = array[0][0];
        for (int[] ints : array) {
            for (int anInt : ints) {
                if (anInt <= minValue) {
                    minValue = anInt;
                }
            }
        }
        return minValue;
    }

    protected void swapLines(int line1, int line2) {
        for (int j = 0; j < array[0].length; j++) {
            int temp = array[line1][j];
            array[line1][j] = array[line2][j];
            array[line2][j] = temp;
        }
    }

    protected void swapColumns(int col1, int col2) {
        for (int i = 0; i < array.length; i++) {
            int temp = array[i][col1];
            array[i][col1] = array[i][col2];
            array[i][col2] = temp;
        }
    }

    public void swapArray() {
        int maxValue = array[0][0];
        int minValue = array[0][0];
        int maxLine = 0;
        int minLine = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] > maxValue) {
                    maxValue = array[i][j];
                    maxLine = i;
                }
                if (array[i][j] < minValue) {
                    minValue = array[i][j];
                    minLine = i;
                }
            }
        }

        for (int i = 0; i < array.length; i++) {
            if (i != minLine && containsValue(array[i], maxValue)) {
                swapLines(i, minLine);
                break;
            }
        }

    }


    private boolean containsValue(int[] row, int value) {
        for (int elem : row) {
            if (elem == value) {
                return true;
            }
        }
        return false;
    }

    protected int maxElementOfLine(int j) {
        int maxValue = array[0][0];
        for (int i = 1; i < array.length; i++) {
            if (array[j][i] > maxValue) {
                maxValue = array[j][i];
            }
        }
        return maxValue;
    }

    @Override
    public boolean diagonalAnswer() {
        System.out.println("Diagonal answer is:");
        boolean answer = false;
        for (int i = 0, j = 0; i < array.length && j < array[i].length; i++) {
            j = i;
            if (array[i][j] == 0) {
                answer = true;
                System.out.println("Number of line: " + (i + 1));
                System.out.println("Max Element of line: " + maxElementOfLine(i) + "\n");
            }
        }
        return answer;
    }

    @Override
    public int findMaxTwoTimes() {
        int[] newArray = new int[array.length * array[0].length];
        for (int i = 0; i < array.length; i++) {
            System.arraycopy(array[i], 0, newArray, i * array[i].length, array[i].length);
        }


        Arrays.sort(newArray);
        for (int i = 0; i < newArray.length / 2; i++) {
            int tmp = newArray[i];
            newArray[i] = newArray[newArray.length - i - 1];
            newArray[newArray.length - i - 1] = tmp;
        }

        int maxValue;
        maxValue = -1;
        int count = 0;
        for (int i = 0; i < newArray.length; i++) {
            if (count == 1 && newArray[i] != maxValue) {
                return maxValue;
            }
            if (newArray[i] == maxValue)
                count++;
            else {
                maxValue = newArray[i];
                count = 0;
            }


        }
        if (count == 1) {
            return maxValue;
        }

        return -1;
    }

    @Override
    public void swapColumnsNonDec() {
        int rows = array.length;
        int cols = array[0].length;
        for (int j = 0; j < cols - 1; j++) {
            for (int k = j + 1; k < cols; k++) {
                if (array[0][j] > array[0][k]) {
                    swapColumns(j, k);
                }
            }
        }
    }

}
