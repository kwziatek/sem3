package app;

import junit.framework.TestCase;

import java.util.Objects;

public class CopyTest extends TestCase {

    Copy copy = new Copy("zbrodnia i kara", "dostojewski", 1900, 1);
    public void testTestEquals() {
        assertFalse(copy.equals("zbrodnia i kara"));
    }

    public void testTestEquals1() {
        assertTrue(copy.equals(new Copy("zbrodnia i kara", "dostojewski", 1900, 1)));
    }

    public void testTestHashCode() {
        assertEquals(Objects.hashCode(copy.getBookId()), copy.hashCode());
    }
}