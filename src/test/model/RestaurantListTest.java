package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantListTest {
    private RestaurantList testRestaurantList;
    private ArrayList<Restaurant> testArrayList;
    private Restaurant restaurant1;
    private Restaurant restaurant2;
    private Restaurant restaurant3;
    private Restaurant restaurant4;

    @BeforeEach
    void runBefore() {
        restaurant1 = new Restaurant("Random1","First restaurant");
        restaurant2 = new Restaurant("Random2","Second restaurant");
        restaurant3 = new Restaurant("Random3","Third restaurant");
        restaurant4 = new Restaurant("Random4","Fourth restaurant");
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
    void testMultipleAddRestaurants() {
        testRestaurantList.addRestaurant(restaurant3);
        testRestaurantList.addRestaurant(restaurant4);
        assertEquals(4, testRestaurantList.getRestaurantList().size());
        assertEquals(restaurant3, testRestaurantList.getRestaurantList().get(2));
        assertEquals(restaurant4, testRestaurantList.getRestaurantList().get(3));
    }

    @Test
    void testRemoveRestaurant() {
        testRestaurantList.removeRestaurant(0);
        assertEquals(1, testRestaurantList.getRestaurantList().size());
        assertEquals(restaurant2, testRestaurantList.getRestaurantList().get(0));
    }

    @Test
    void testMultipleRemoveRestaurants() {
        testRestaurantList.removeRestaurant(0);
        testRestaurantList.removeRestaurant(0);
        assertEquals(0, testRestaurantList.getRestaurantList().size());
    }

    @Test
    void testRandomRestaurant() {
        assertNotNull(testRestaurantList.randomRestaurant());
    }
}
