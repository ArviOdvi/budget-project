package com.budget.my;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


/**
 * Unit test for simple App.
 */
public class AppTest 

{
    @Test
    public void testMainDoesNotThrowException() {
        assertDoesNotThrow(() -> App.main(new String[]{}));
    }
}
