package transportSystem.train.trainTest;

import static org.junit.Assert.*;

import org.junit.Test;

import transportSystem.train.ComfortLevel;


public class ComfortLevelTest {

    ComfortLevel comfortLevel;

    @Test
    public void comfortLevelShouldAssignProperly() {

        assertEquals(ComfortLevel.assignComfortLevel(1), ComfortLevel.UNBEARABLE);
        assertEquals(ComfortLevel.assignComfortLevel(11), ComfortLevel.UNCOMFORTABLE);
        assertEquals(ComfortLevel.assignComfortLevel(26), ComfortLevel.TOLERABLE);
        assertEquals(ComfortLevel.assignComfortLevel(49), ComfortLevel.TOLERABLE);
        assertEquals(ComfortLevel.assignComfortLevel(89), ComfortLevel.COMFORTABLE);
        assertEquals(ComfortLevel.assignComfortLevel(90), ComfortLevel.HEAVENLY);
    }

    @Test
    public void compareLevels() {
        assertEquals(1, ComfortLevel.UNCOMFORTABLE.compareTo(ComfortLevel.UNBEARABLE));
        assertEquals(0, ComfortLevel.UNCOMFORTABLE.compareTo(ComfortLevel.UNCOMFORTABLE));
        assertEquals(-1, ComfortLevel.UNCOMFORTABLE.compareTo(ComfortLevel.TOLERABLE));
    }

}
