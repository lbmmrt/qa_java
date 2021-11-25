import com.example.Feline;
import com.example.Lion;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class LionTest {

    @Mock
    private Feline feline;

    private Lion lion;

    @Test
    public void sexMaleReturnTrue() throws Exception {
        lion = new Lion("Самец", feline);
        boolean actual = lion.doesHaveMane();
        assertTrue(actual);
    }

    @Test
    public void sexFemaleReturnTrue() throws Exception {
        lion = new Lion("Самка", feline);
        boolean actual = lion.doesHaveMane();
        assertFalse(actual);
    }

    @Test(expected = Exception.class)
    public void incorrectSexThrowsException() throws Exception {
        lion = new Lion("Самочка", feline);
    }

    @Test
    public void getKittensReturnNumberOne() throws Exception {
        lion = new Lion("Самец", feline);
        Mockito.when(feline.getKittens()).thenReturn(1);
        int actual = lion.getKittens();
        int expected = 1;
        assertEquals(expected, actual);
    }

    @Test
    public void eatMeatReturnsListFoodAnimalsBirdsFish() throws Exception {
        lion = new Lion("Самец", feline);
        List<String> foodList = List.of("Животные", "Птицы", "Рыба");
        Mockito.when(feline.getFood(any())).thenReturn(foodList);
        List<String> actual = lion.getFood();
        assertEquals(foodList, actual);
    }

}
