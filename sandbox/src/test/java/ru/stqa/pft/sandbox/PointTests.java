package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {
    @Test
    public void testDistance (){
        Point p1 = new Point(5,3);
        Point p2 = new Point(3,-1);
        Assert.assertEquals(p1.distance(p2),4.47213595499958);
    }
    @Test
    public void test1 (){
        Point p1 = new Point(5,3);
        Point p2 = new Point(3,-1);
        Assert.assertEquals((int) p1.distance(p2),4);
    }
    @Test
    public void test2 (){
        Point p1 = new Point(5,3);
        Point p2 = new Point(4,5);
        Assert.assertNotEquals(p1.distance(p2),0);
    }
}
