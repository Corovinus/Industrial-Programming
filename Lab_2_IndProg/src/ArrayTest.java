import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;


class ArrayTest extends Array{

    private Array array;

    @BeforeEach
    void setup() {
        array = new Array();
    }

    private void setSimulatedInput(String input) {
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        //Подавление вывода
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        array.setArray();
    }

    private String captureOutput(Runnable action) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(out));
        action.run();
        System.setOut(originalOut);
        return out.toString().replaceAll("\\r\\n?", "\n");
    }

    @Test
    void testInput() {
        String simulatedInput = "3\n3\n1 2 3\n4 5 6\n7 8 9\n";
        setSimulatedInput(simulatedInput);
        int[][] expectedArray = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        assertArrayEquals(expectedArray, array.getArray());
    }

    @Test
    void testOutput() {
        setSimulatedInput("3\n3\n1 2 3\n4 5 6\n7 8 9\n");

        String output = captureOutput(array::coutArray);
        String expectedOutput = """
                1 2 3
                4 5 6
                7 8 9
                
                """;

        assertEquals(expectedOutput, output);
    }

    @Test
    void testGetMaxValue() {
        setSimulatedInput("3\n3\n1 2 3\n4 5 6\n7 8 9\n");
        assertEquals(9, array.getMaxValue());
    }

    @Test
    void testGetMinValue() {
        setSimulatedInput("3\n3\n1 5 3\n4 0 8\n9 6 2\n");
        assertEquals(0, array.getMinValue());
    }

    @Test
    void testSwapArray() {
        setSimulatedInput("3\n3\n1 2 3\n4 5 6\n7 8 9\n");
        array.swapArray();
        int[][] expectedArray = {
                {7, 8, 9},
                {4, 5, 6},
                {1, 2, 3}
        };
        assertArrayEquals(expectedArray, array.getArray());
    }

    @Test
    void testMaxElementOfLine() {
        setSimulatedInput("3\n3\n2 5 3\n4 5 6\n7 8 9\n");
        assertEquals(5, array.maxElementOfLine(0));
        assertEquals(6, array.maxElementOfLine(1));
        assertEquals(9, array.maxElementOfLine(2));
    }

    @Test
    void testDiagonalAnswer() {
        setSimulatedInput("3\n3\n1 0 3\n4 0 6\n7 8 0\n");
        String output = captureOutput(array::diagonalAnswer);

        String expectedOutput = """
                Diagonal answer is:
                Number of line: 2
                Max Element of line: 6
                
                Number of line: 3
                Max Element of line: 8
                
                """;
        assertEquals(expectedOutput, output);
    }

    @Test
    void testSwapLines() {
        setSimulatedInput("3\n3\n1 2 3\n4 5 6\n7 8 9\n");
        array.swapLines(0, 2);
        int[][] expectedArray = {
                {7, 8, 9},
                {4, 5, 6},
                {1, 2, 3}
        };
        assertArrayEquals(expectedArray, array.getArray());
    }

    @Test
    void testSwapColumns() {
        setSimulatedInput("3\n3\n1 2 3\n4 5 6\n7 8 9\n");
        array.swapColumns(0, 2);
        int[][] expectedArray = {
                {3, 2, 1},
                {6, 5, 4},
                {9, 8, 7}
        };
        assertArrayEquals(expectedArray, array.getArray());

        setSimulatedInput("1\n1\n1");
        array.swapColumns(0, 0);
        int[][] expectedArray1 = {
                {1}
        };
        assertArrayEquals(expectedArray1, array.getArray());
    }

    @Test
    void testSwapColumnsNonDec() {
        setSimulatedInput("3\n3\n3 1 2\n6 4 5\n9 7 8\n");
        array.swapColumnsNonDec();
        int[][] expectedArray = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        assertArrayEquals(expectedArray, array.getArray());

        setSimulatedInput("4\n4\n2 1 4 3\n1 2 3 4\n1 2 3 4\n 1 2 3 4\n");
        array.swapColumnsNonDec();
        int[][] expectedArray1 = {
                {1, 2, 3, 4},
                {2, 1, 4, 3},
                {2, 1, 4, 3},
                {2, 1, 4, 3}
        };
        assertArrayEquals(expectedArray1, array.getArray());
    }

    @Test
    void testFindMaxTwoTimes() {
        setSimulatedInput("3\n3\n1 2 3\n4 5 5\n7 8 9\n");
        assertEquals(5, array.findMaxTwoTimes());

        setSimulatedInput("3\n3\n1 9 4\n4 5 5\n7 8 9\n");
        assertEquals(9, array.findMaxTwoTimes());

        setSimulatedInput("3\n3\n1 2 3\n4 5 6\n7 8 9\n");
        assertEquals(-1, array.findMaxTwoTimes());

        setSimulatedInput("1\n1\n5");
        assertEquals(-1, array.findMaxTwoTimes());

    }
}
