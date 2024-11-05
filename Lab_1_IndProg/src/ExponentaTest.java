import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExponentaTest {
    @Test
    void testExponenta() {
        double aproximation = Exponenta.calculateExponential(5,3);
        double answer = Math.exp(5);
        assertEquals(answer, aproximation, Math.pow(10,-3) + 0.001);

        aproximation = Exponenta.calculateExponential(-15,6);
        answer = Math.exp(-15);
        assertEquals(answer, aproximation, Math.pow(10,-6) + 0.001);

        aproximation = Exponenta.calculateExponential(26,8);
        answer = Math.exp(26);
        assertEquals(answer, aproximation, Math.pow(10,-8) + 0.001);
    }
}