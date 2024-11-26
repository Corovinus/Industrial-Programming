import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class FibonachiGeneratorTest {

    private final FibonachiGenerator generator = new FibonachiGenerator();

    @Test
    void testGenerateValidInput() {
        List<Long> result = generator.generate(10);
        List<Long> expected = List.of(0L, 1L, 1L, 2L, 3L, 5L, 8L, 13L, 21L, 34L);
        assertEquals(expected, result, "Wrong output for n=10.");
    }

    @Test
    void testGenerateOneElement() {
        List<Long> result = generator.generate(1);
        List<Long> expected = List.of(0L);
        assertEquals(expected, result, "Wrong output for n=1.");
    }

    @Test
    void testGenerateTwoElements() {
        List<Long> result = generator.generate(2);
        List<Long> expected = List.of(0L, 1L);
        assertEquals(expected, result, "Wrong output for n=2.");
    }

    @Test
    void testGenerateLargeInput() {
        assertDoesNotThrow(() -> generator.generate(50), "Failed for n=50.");
    }

    @Test
    void testGenerateNegativeInput() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> generator.generate(-1));
        assertEquals("n must be > 0.", exception.getMessage());
    }

    @Test
    void testGenerateZeroInput() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> generator.generate(0));
        assertEquals("n must be > 0.", exception.getMessage());
    }

    @Test
    void testOverflow() {
        ArithmeticException exception = assertThrows(ArithmeticException.class, () -> generator.generate(100));
        assertEquals("Overflow.", exception.getMessage());
    }
}
