import com.example.Feline;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.Assert.assertEquals;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class FelineTest {

    private final Feline feline = new Feline();

    @Test
    public void eatMeatReturnsListFoodAnimalsBirdsFishPositiveResult() throws Exception {
        List<String> actual = feline.eatMeat();
        List<String> expected = List.of("Животные", "Птицы", "Рыба");
        assertEquals(actual, expected);
    }

    @Test
    public void getFamilyReturnFelinePositiveResult() {
        String actual = feline.getFamily();
        String expected = "Кошачьи";
        assertEquals(actual, expected);
    }

    @Test
    public void getKittensWithArgumentReturnIntPositiveResult() {
        int actual = feline.getKittens(9);
        int expected = 9;
        assertEquals(actual, expected);
    }

    @Test
    public void getKittensReturnNumberOnePositiveResult() {
        int actual = feline.getKittens();
        int expected = 1;
        assertEquals(actual, expected);
    }
}
