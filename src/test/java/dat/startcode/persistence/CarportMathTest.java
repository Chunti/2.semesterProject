package dat.startcode.persistence;

import dat.startcode.model.services.CarportMath;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;


/** 
* CarportMath Tester. 
* 
* @author <Authors name> 
* @since <pre>maj 26, 2022</pre> 
* @version 1.0 
*/ 
public class CarportMathTest {
    int length;
    int width;
    int shedLength;
    int totalLength;


@Before
public void before() throws Exception {

    length = 570;
    width = 600;
    shedLength = 210;
    totalLength = 780;

} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: getRafters(int totalLength) 
* 
*/ 
@Test
public void testGetRafters() throws Exception { 
    assertEquals(15,CarportMath.getRafters(totalLength));
} 

/** 
* 
* Method: getspacing(int totalLength, int rafters) 
* 
*/ 
@Test
public void testGetspacing() throws Exception {
    assertEquals(55,CarportMath.getspacing(totalLength,15));
} 

/** 
* 
* Method: getShedPlanks(int shedLength, int width) 
* 
*/ 
@Test
public void testGetShedPlanks() throws Exception {
    assertEquals(200,CarportMath.getShedPlanks(shedLength,width));
}

/** 
* 
* Method: getPosts(int length, int width) 
* 
*/ 
@Test
public void testGetPosts() throws Exception {
    assertEquals(7,CarportMath.getPosts(length,width));

} 

/** 
* 
* Method: getPostsWithShed(int length, int width, int shedLength) 
* 
*/ 
@Test
public void testGetPostsWithShed() throws Exception {
    assertEquals(11,CarportMath.getPostsWithShed(length,width,shedLength));
} 

/** 
* 
* Method: getStern(int length) 
* 
*/ 
@Test
public void testGetStern() throws Exception {
    int[] expectedValue = new int[] {4,540};
    assertArrayEquals(expectedValue,CarportMath.getStern(totalLength));
} 

/** 
* 
* Method: getLooseWood(int length) 
* 
*/ 
@Test
public void testGetLooseWood() throws Exception {
    int[] expectedValue = new int[] {12,270};
    assertArrayEquals(expectedValue,CarportMath.getLooseWood(width,3));
} 

/** 
* 
* Method: getRoofingSheets(int length, int width) 
* 
*/ 
@Test
public void testGetRoofingSheets() throws Exception {
    int[] expectedValue = new int[] {6,6};
    assertArrayEquals(expectedValue,CarportMath.getRoofingSheets(totalLength,width));
} 

/** 
* 
* Method: getPerforatedTape(int length, int width) 
* 
*/ 
@Test
public void testGetPerforatedTape() throws Exception {
    assertEquals(2,CarportMath.getPerforatedTape(length,width));
} 


} 
