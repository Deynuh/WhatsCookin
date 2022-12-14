package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

// Tests the RestaurantList class
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
        testRestaurantList = new RestaurantList("Restaurant List");
        testRestaurantList.setRestaurantList(testArrayList);
    }

    @Test
    void testConstructor() {
        assertEquals(testArrayList, testRestaurantList.getRestaurantList());
    }

    @Test
    void testSetName() {
        RestaurantList noName = new RestaurantList("");
        noName.setName("A Name");
        assertEquals("A Name", noName.getName());
    }

    @Test
    void testAddRestaurant() {
        testRestaurantList.addRestaurant(restaurant3);
        //assertTrue(testRestaurantList.getRestaurantList().contains(restaurant1));
        //assertTrue(testRestaurantList.getRestaurantList().contains(restaurant3));
        assertEquals(restaurant3, testRestaurantList.getRestaurantList().get(2));
        assertEquals(3, testRestaurantList.getRestaurantList().size());
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
        testRestaurantList.removeRestaurant(1);
        assertEquals(1, testRestaurantList.getRestaurantList().size());
        assertEquals(restaurant1, testRestaurantList.getRestaurantList().get(0));
    }

    @Test
    void testMultipleRemoveRestaurants() {
        testRestaurantList.removeRestaurant(0);
        testRestaurantList.removeRestaurant(0);
        assertEquals(0, testRestaurantList.getRestaurantList().size());
    }

    @Test
    void testRandomRestaurant() {
        RestaurantList nullRestaurantList = new RestaurantList("Null Restaurant");
        assertNotNull(testRestaurantList.randomRestaurant());
        assertNull(nullRestaurantList.randomRestaurant());
        //assertTrue(testRestaurantList.getRestaurantList().contains(testRestaurantList.randomRestaurant()));
    }
}
