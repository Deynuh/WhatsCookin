package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {

    private Restaurant swissChalet;

    @BeforeEach
    void runBefore() {
        swissChalet = new Restaurant("Swiss Chalet", "Canadian rotisserie and grill");
    }

    @Test
    void testConstructor() {
        assertEquals("Swiss Chalet", swissChalet.getName());
        assertEquals("Canadian rotisserie and grill", swissChalet.getDescription());
    }

}
