package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the Event class
 */
public class EventTest {
	private Event e;
    private Event e2;
	private Date d;
    private Date d2;
    private RecipeBook rb;
	
	//NOTE: these tests might fail if time at which line (2) below is executed
	//is different from time that line (1) is executed.  Lines (1) and (2) must
	//run in same millisecond for this test to make sense and pass.
	
	@BeforeEach
	public void runBefore() {
        rb = new RecipeBook("Test Recipe Book");
        rb.addRecipe(new Recipe("recipe1","test1",1, null));
		e = new Event("Added a new recipe called: " + rb.getRecipeBook().get(0).getName());   // more tests?
		//d = Calendar.getInstance().getTime();   // (2)

        e2 = new Event("Removed recipe: " + rb.getRecipeBook().get(0).getName());
        rb.removeRecipe(0);
        //d2 = Calendar.getInstance().getTime();
    }
	
	@Test
	public void testEvent() {
		assertEquals("Added a new recipe called: recipe1", e.getDescription());
		//assertEquals(d, e.getDate());
        assertEquals("Removed recipe: recipe1", e2.getDescription());
        //assertEquals(d2, e.getDate());
	}

//	@Test
//	public void testToString() {
//		assertEquals(d.toString() + "\n" + "Added a new recipe called: 1", e.toString());
//	}
}
