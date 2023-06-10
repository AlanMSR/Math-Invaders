import com.mygdx.game.AdvancedEnemy;
import com.mygdx.game.Player;
import com.mygdx.game.PlayerProjectile;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
//import org.junit.Test;
//import static org.junit.Assert.*;

class AdvancedEnemyTest {

    @Test
    void checkAnswer_WhenCorrectAnswerProvided_ShouldReduceHealthPoints() {
        // Arrange
        AdvancedEnemy enemy = new AdvancedEnemy();
        enemy.setAnswer(5);
        Player player = new Player(1);
        //player.getProjectile().getNumber();

        // Act
        boolean result = enemy.checkAnswer(player);

        // Assert
        assertTrue(result);
        assertEquals(0, enemy.getHealthPoints());
    }


}
