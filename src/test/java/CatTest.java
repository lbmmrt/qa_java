import com.example.Cat;
import com.example.Feline;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class CatTest {

    @Mock
    private Feline feline;

    private Cat cat;

    @Before
    public void before() throws Exception {
        MockitoAnnotations.openMocks(this).close();
        cat = new Cat(feline);
    }

    @Test
    public void getSoundReturnMeowPositiveResult() {
        String actual = cat.getSound();
        String expected = "Мяу";
        assertEquals(expected, actual);
    }

    @Test
    public void getFoodReturnListFoodMilkCucumberFishPositiveResult() throws Exception {
        List<String> foodList = List.of("Молоко", "Огурец", "Рыба");
        Mockito.when(feline.eatMeat()).thenReturn(foodList);
        List<String> actual = cat.getFood();
        assertEquals(foodList, actual);

    }
}
