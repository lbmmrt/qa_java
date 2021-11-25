import com.example.Feline;
import com.example.LionAlex;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class LionAlexTest {

    @Mock
    private Feline feline;

    private LionAlex lionAlex;

    @Before
    public void before() throws Exception {
        MockitoAnnotations.openMocks(this).close();
        lionAlex = new LionAlex(feline);
    }

    @Test
    public void getFriendsReturnFriendsNameMartyMelmanGloria() {
        List<String> actual = lionAlex.getFriends();
        List<String> expected = List.of("Марти", "Глория", "Мелман");
        assertEquals(expected, actual);
    }

    @Test
    public void getPlaceOfLivingReturnNewYorkZoo() {
        String actual = lionAlex.getPlaceOfLiving();
        String expected = "Нью-Йоркский зоопарк";
        assertEquals(expected, actual);
    }

    @Test
    public void getKittensReturnZero() {
        int actual = lionAlex.getKittens();
        int expected = 0;
        assertEquals(expected, actual);
    }

}
