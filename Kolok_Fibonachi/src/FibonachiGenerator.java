import java.util.ArrayList;
import java.util.List;

public class FibonachiGenerator {

    /**
     * Генерирует первые n чисел Фибоначчи.
     *
     * @param n количество чисел для генерации, должно быть положительным.
     * @return список первых n чисел Фибоначчи.
     * @throws IllegalArgumentException если n <= 0.
     */

    public List<Long> generate(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n must be > 0.");
        }

        List<Long> fibonachiNumbers = new ArrayList<>();
        if (n == 1) {
            fibonachiNumbers.add(0L);
            return fibonachiNumbers;
        }

        fibonachiNumbers.add(0L);
        fibonachiNumbers.add(1L);

        for (int i = 2; i < n; i++) {
            long next = fibonachiNumbers.get(i - 1) + fibonachiNumbers.get(i - 2);
            if (next < 0) { // Проверка на переполнение
                throw new ArithmeticException("Overflow.");
            }
            fibonachiNumbers.add(next);
        }

        return fibonachiNumbers;
    }
}
