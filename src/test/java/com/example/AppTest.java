package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AppTest {
    @Test
    void addsTwoNumbers() {
        assertEquals(4, App.add(2, 2));
    }
}
