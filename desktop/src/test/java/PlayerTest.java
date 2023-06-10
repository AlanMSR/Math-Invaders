import com.badlogic.gdx.Gdx;
import com.mygdx.game.*;
import com.badlogic.gdx.Files;
import com.badlogic.gdx.files.FileHandle;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.junit.runner.RunWith;
import test

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(GdxTestRunner.class)
public class PlayerTest {
    private Player player;

    @Mock
    private Files filesMock;

    @Before
    public void setUp() {
        
        MockitoAnnotations.initMocks(this);
        Gdx.files = filesMock;
        player = new Player(1);
    }

    @After
    public void tearDown() {
        // Clean up after each test if needed
    }

    @Test
    public void testGetPlayerNumber() {
        int expectedPlayerNumber = 1;
        int actualPlayerNumber = player.getPlayerNumber();
        assertEquals(expectedPlayerNumber, actualPlayerNumber);
    }

    @Test
    public void testSetScoreAndGetScore() {
        int score = 100;
        player.setScore(score);
        int actualScore = player.getScore();
        assertEquals(score, actualScore);
    }

    // Add more test methods as needed
}