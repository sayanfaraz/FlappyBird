import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by Sayan Faraz on 2016-09-13.
 */
public class FlappyBirdTest {

    // ATTRIBUTES
    public FlappyBird testFlappyBird;

    // SETUP, TEARDOWN
    @BeforeMethod
    public void setUp() throws Exception {
        testFlappyBird = new FlappyBird();
    }

    @AfterMethod
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetPos() throws Exception {
        // Vars
        int[] expectedPos = {50, 0};

        // Test
        Assert.assertEquals(testFlappyBird.getPos(), expectedPos);
    }

    @Test
    public void testSetPos() throws Exception {
        // Vars
        int x = 40, y = 40;
        int[] expectedPos = {40, 40};
        int[] expectedPrevPos = {50, 0};

        // Setup
        testFlappyBird.setPos(expectedPos);

        // Test: set pos
        Assert.assertEquals(testFlappyBird.getPos(), expectedPos);
        // Test: set prev pos
        Assert.assertEquals(testFlappyBird.getPrevPos(), expectedPrevPos);
    }

    @Test
    public void testGetPrevPos() throws Exception {

    }

    @Test
    public void testSetPrevPos() throws Exception {

    }

    @Test
    public void testIsAlive() throws Exception {

    }

    @Test
    public void testSetAlive() throws Exception {

    }

    @Test
    public void testGetImgDimensions() throws Exception {

    }

    @Test
    public void testSetImgDimensions() throws Exception {

    }

    @Test
    public void testGetFallTime() throws Exception {

    }

    @Test
    public void testSetFallTime() throws Exception {

    }

    @Test
    public void testClickBird() throws Exception {

    }

    @Test
    public void testFall() throws Exception {

    }

    @Test
    public void testKill() throws Exception {

    }

    @Test
    public void testResurrect() throws Exception {

    }

    @Test
    public void testOnXObstacle() throws Exception {

    }

    @Test
    public void testOnYObstacle() throws Exception {

    }

    @Test
    public void testInsideObstacle() throws Exception {

    }

    @Test
    public void testMoveUp() throws Exception {

    }

    @Test
    public void testMoveUp1() throws Exception {

    }

    @Test
    public void testMoveDown() throws Exception {

    }

    @Test
    public void testMoveDown1() throws Exception {

    }

    @Test
    public void testHasCollided() throws Exception {

    }

}