package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

// Tests the RecipeBook class
class RecipeBookTest {
    private RecipeBook testRecipeBook;
    private ArrayList<Recipe> testArrayList;
    private Recipe recipe1;
    private Recipe recipe2;
    private Recipe recipe3;
    private Recipe recipe4;

    @BeforeEach
    void runBefore() {
        recipe1 = new Recipe("Random1","First recipe", 0, null);
        recipe2 = new Recipe("Random2","Second recipe",0,null);
        recipe3 = new Recipe("Random3","Third recipe",0,null);
        recipe4 = new Recipe("Random4","Fourth recipe",0,null);
        testArrayList = new ArrayList<>();
        testArrayList.add(recipe1);
        testArrayList.add(recipe2);
        testRecipeBook = new RecipeBook("test");
        testRecipeBook.setRecipeBook(testArrayList);
    }

    @Test
    void testConstructor() {
        assertEquals("test", testRecipeBook.getName());
        assertEquals(testArrayList, testRecipeBook.getRecipeBook());
    }

    @Test
    void testConstructorNameVariations() {
        RecipeBook noName = new RecipeBook("");
        assertEquals("", noName.getName());

        RecipeBook noSetName = new RecipeBook("");
        noSetName.setName("test2");
        assertEquals("test2", noSetName.getName());
    }


    @Test
    void testAddRecipe() {
        testRecipeBook.addRecipe(recipe3);
        //assertTrue(testRecipeBook.getRecipeBook().contains(recipe1));
        //assertTrue(testRecipeBook.getRecipeBook().contains(recipe3));
        assertEquals(recipe3, testRecipeBook.getRecipeBook().get(2));
        assertEquals(3, testRecipeBook.getRecipeBook().size());
    }

    @Test
    void testMultipleAddRecipes() {
        testRecipeBook.addRecipe(recipe3);
        testRecipeBook.addRecipe(recipe4);
        assertEquals(4, testRecipeBook.getRecipeBook().size());
        assertEquals(recipe3, testRecipeBook.getRecipeBook().get(2));
        assertEquals(recipe4, testRecipeBook.getRecipeBook().get(3));
    }

    @Test
    void testRemoveRecipe() {
        testRecipeBook.removeRecipe(1);
        assertEquals(1, testRecipeBook.getRecipeBook().size());
        assertEquals(recipe1, testRecipeBook.getRecipeBook().get(0));
    }

    @Test
    void testMultipleRemoveRecipes() {
        testRecipeBook.removeRecipe(0);
        testRecipeBook.removeRecipe(0);
        assertEquals(0, testRecipeBook.getRecipeBook().size());
    }

    @Test
    void testRandomRecipe() {
        RecipeBook nullRecipeBook = new RecipeBook("null");
        assertNull(nullRecipeBook.randomRecipe());
        assertNotNull(testRecipeBook.randomRecipe());
        //assertTrue(testRecipeBook.getRecipeBook().contains(testRecipeBook.randomRecipe()));
    }
}
