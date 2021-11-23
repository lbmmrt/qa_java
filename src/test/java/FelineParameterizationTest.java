import com.example.Feline;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class FelineParameterizationTest {

    private final int kittensCount;
    private final int expectedKittensCount;

    public FelineParameterizationTest(int kittensCount, int expected) {
        this.kittensCount = kittensCount;
        this.expectedKittensCount = expected;
    }

    @Parameterized.Parameters
    public static Object[][] getSumKittens() {
        return new Object[][]{
                {1, 1},
                {22, 22},
                {333, 333},
        };
    }

    @Test
    public void getKittensWithArgumentReturnIntPositiveResult() {
        Feline feline = new Feline();
        int actual = feline.getKittens(kittensCount);
        assertEquals(actual, expectedKittensCount);
    }
}
