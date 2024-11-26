package app;

import junit.framework.TestCase;

public class LibraryTest extends TestCase {
    Library lib = new Library();

    public void testCreateNewCopy() {
        lib.createNewCopy("zbrodnia i kara", "dostojewski", 1900);
        assertTrue(lib.getCopiesInLibrary().contains(new Copy("zbrodnia i kara", "dostojewski", 1900, 0)));
    }

    public void testCheckOutCopy() {
        lib.createNewCopy("zbrodnia i kara", "dostojewski", 1900);
        lib.checkOutCopy("zbrodnia i kara", "dostojewski", 0);
        assertTrue(lib.getCheckedOutCopies().containsKey(new Copy("zbrodnia i kara", "dostojewski", 1900, 0)));
    }

    public void testCheckOutCopy1() {
        lib.createNewCopy("zbrodnia i kara", "dostojewski", 1900);
        lib.checkOutCopy("zbrodnia i kara", "dostojewski", 0);
        assertFalse(lib.getCopiesInLibrary().contains(new Copy("zbrodnia i kara", "dostojewski", 1900, 0)));
    }

    public void testReturnCopy() {
        lib.createNewCopy("zbrodnia i kara", "dostojewski", 1900);
        lib.checkOutCopy("zbrodnia i kara", "dostojewski", 0);
        lib.returnCopy("zbrodnia i kara", "dostojewski", 1900, 0);
        assertFalse(lib.getCheckedOutCopies().containsKey(new Copy("zbrodnia i kara", "dostojewski", 1900, 0)));
    }

    public void testReturnCopy1() {
        lib.createNewCopy("zbrodnia i kara", "dostojewski", 1900);
        lib.checkOutCopy("zbrodnia i kara", "dostojewski", 0);
        lib.returnCopy("zbrodnia i kara", "dostojewski", 1900, 0);
        assertTrue(lib.getCopiesInLibrary().contains(new Copy("zbrodnia i kara", "dostojewski", 1900, 0)));
    }

    public void testAddNewUser() {
        lib.addNewUser("karol", "wziatek");
        assertTrue(lib.getUsers().contains(new User("karol", "wziatek", 0)));
    }

    public void testAddNewUser1() {
        lib.addNewUser("karol", "wziatek");
        assertFalse(lib.getUsers().contains(new User("karol", "wziatek", 1)));
    }
}