package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantListTest {
    private RestaurantList testRestaurantList;
    private ArrayList<Restaurant> testArrayList;
    private Restaurant restaurant2;
    private Restaurant restaurant3;

    @BeforeEach
    void runBefore() {
        Restaurant restaurant1 = new Restaurant("Random1","First restaurant");
        restaurant2 = new Restaurant("Random2","Second restaurant");
        restaurant3 = new Restaurant("Random3","Third restaurant");
        testArrayList = new ArrayList<>();
        testArrayList.add(restaurant1);
        testArrayList.add(restaurant2);
        testRestaurantList = new RestaurantList(testArrayList);
    }

    @Test
    void testConstructor() {
        assertEquals(testArrayList, testRestaurantList.getRestaurantList());
    }

    @Test
    void testAddRestaurant() {
        testRestaurantList.addRestaurant(restaurant3);
        assertEquals(restaurant3, testRestaurantList.getRestaurantList().get(2));
    }

    @Test
    void testRemoveRestaurant() {
        testRestaurantList.removeRestaurant(0);
        assertEquals(1, testRestaurantList.getRestaurantList().size());
        assertEquals(restaurant2, testRestaurantList.getRestaurantList().get(0));
    }

    @Test
    void testRandomRestaurant() {
        assertNotNull(testRestaurantList.randomRestaurant());
    }
}
