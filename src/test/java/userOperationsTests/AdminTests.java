package userOperationsTests;

import org.junit.jupiter.api.Test;
import users.Admin;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class AdminTests {

    @Test
    public void testPerformActionWithInvalidInput(){
        Admin admin = new Admin();
        int selectedOption = 7;

        assertThrows(IndexOutOfBoundsException.class, () -> admin.performAction(selectedOption));
    }

    @Test
    public void testPerformActionWithInvalidNegativeInput(){
        Admin admin = new Admin();
        int selectedOption = -1;
        assertThrows(IndexOutOfBoundsException.class, () -> admin.performAction(7));
    }
}
