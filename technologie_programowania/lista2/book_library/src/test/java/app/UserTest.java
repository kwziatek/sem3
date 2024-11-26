package app;

import junit.framework.TestCase;

public class UserTest extends TestCase {
    User user = new User("karol", "wziatek", 7);
    public void testGetUserId() {
        assertEquals(7, user.getUserId());
    }

    public void testGetUserId1() {
        assertNotSame(8, user.getUserId());
    }

    public void testTestToString() {
        assertEquals("karol wziatek: 7", user.toString());
    }

    public void testTestToString1() {
        assertNotSame("karol wziatek, 7", user.toString());
    }
}