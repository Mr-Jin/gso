/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import classes.Period;
import classes.Period2;
import classes.Time;
import fontys.time.DayInWeek;
import static junit.framework.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Tim Smeets
 * @author Jin Tran
 * @author Luke van der Loop
 */
public class timeTests {
    
    public timeTests() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    //@author Luke van der Loop
    @Test
    public void TestTime(){
     Time t = new Time(2013,3,20,2,30);
     Time t2 = new Time(2013,3,20,2,40);
     assertEquals("Jaar komt niet overeen",2013,t.getYear());
     assertEquals("Maand komt niet overeen",3,t.getMonth());
     assertEquals("Dag komt niet overeen",20,t.getDay());
     assertEquals("Uur komt niet overeen",2,t.getHours());
     assertEquals("Minuten komt niet overeen",30,t.getMinutes());
     assertEquals("Dag van de week komt niet overeen","WED",t.getDayInWeek().toString());

     t.plus(20);
     assertEquals("Minuten plus 20 komt niet overeen",50,t.getMinutes());
     t.plus(-40);
     assertEquals("Minuten plus -20 komt niet overeen",10,t.getMinutes());
    
     
     assertEquals("Verschil tijd komt niet overeen",30,t.difference(t2));
    }
    
    @Test
    public void testFouteDatums()
    {
     try{Time falsemonth = new Time(2013,13,20,2,30);
     fail("Foute maand");}
     catch(IllegalArgumentException e){}
     
     try{Time falseday = new Time(2013,12,33,2,30);
     fail("Foute dag");}
     catch(IllegalArgumentException e){}
     
     try{Time falsehour = new Time(2013,12,20,24,30);
     fail("Fout uur");}
     catch(IllegalArgumentException e){}
     
     try{Time falseminute = new Time(2013,12,20,2,60);
     fail("Foute dag");}
     catch(IllegalArgumentException e){}
    }
    @Test
     public void TestPeriod2(){
        // periode 1= -adfas
        //periode 2= =
        //periode 3 = #
        //  bt2##bt#-#-bt1=#-=#-=#et1-#-#-et##et2
        // bt2 > bt > bt1 > et1 > et > et2
        Time bt = new Time(2013,3,20,2,30);
        Time et = new Time(2014,3,20,2,30);
        Time bt1 = new Time(2013,4,20,2,20);
        Time et1 = new Time(2014,2,20,2,30);
        Time bt2 = new Time(2012,3,20,2,30);
        Time et2 = new Time(2014,4,20,2,30);
        Period2 p = new Period2(bt,et);
        
        assertEquals("periode lengte in minuten fout",525600,p.length());
        
        assertEquals("get begintijd klopt niet",bt,p.getBeginTime());
        assertEquals("get eindtijd klopt niet",0,p.getEndTime().compareTo(et));
        
        p.setBeginTime(bt2);
        assertEquals("set begintijd klopt niet",bt2,p.getBeginTime());
        p.setEndTime(et2);
        assertEquals("set eindtijd klopt niet",0,p.getEndTime().compareTo(et2));
        Period2 pOud = new Period2(bt2,et2);
        p.changeLengthWith(5);        
        assertEquals("move plus werkt niet",pOud.length()+5,p.length());
        p = new Period2(bt,et);
        p.move(10);
        assertEquals("begintijdstip is niet verplaatst",10,p.getBeginTime().difference(bt));
        assertEquals("eindtijdstip is niet verplaatst",10,p.getEndTime().difference(et));
        p = new Period2(bt,et);
        p.move(-10);
        assertEquals("begintijdstip is niet verplaatst NEGATIEF",10,p.getBeginTime().difference(bt));
        assertEquals("eindtijdstip is niet verplaatst NEGATIEF",10,p.getEndTime().difference(et));
        
        p = new Period2(bt,et);
        Period2 p2 = new Period2(bt1,bt1);
        assertTrue("perioden overlappen wel volledig",p.isPartOf(p2));
        Time bt3 = new Time(2013,3,20,2,30);
        Time et3 = new Time(2013,5,20,2,30);
        Time bt4 = new Time(2013,2,20,2,30);
        Time et4 = new Time(2013,4,20,2,30);
        p = new Period2(bt3,et3);
        p2 = new Period2(bt4,et4);
        assertNotNull("perioden hebben een intersect",p.unionWith(p2));
        assertNotNull("perioden hebben een intersect",p.intersectionWith(p2));
        //assertNotNull("perioden overlappen wel",)
    }

    //@author Tim Smeets
    @Test
    public void TestPeriod(){
        // periode 1= -
        //periode 2= =
        //periode 3 = #
        //  bt2##bt#-#-bt1=#-=#-=#et1-#-#-et##et2
        // bt2 > bt > bt1 > et1 > et > et2
        Time bt = new Time(2013,3,20,0,0);
        Time et = new Time(2014,3,20,0,0);
        Time bt1 = new Time(2013,4,20,2,20);
        Time et1 = new Time(2014,2,20,2,30);
        Time bt2 = new Time(2012,3,20,2,30);
        Time et2 = new Time(2014,4,20,2,30);
        Period p = new Period(bt,et);
        
        assertEquals("periode lengte in minuten fout",525600,p.length());
        
        assertEquals("get begintijd klopt niet",bt,p.getBeginTime());
        assertEquals("get eindtijd klopt niet",et,p.getEndTime());
        
        p.setBeginTime(bt2);
        assertEquals("set begintijd klopt niet",bt2,p.getBeginTime());
        p.setEndTime(et2);
        assertEquals("set eindtijd klopt niet",0,p.getEndTime().compareTo(et2));
        Period pOud = new Period(bt2,et2);
        p.changeLengthWith(5);        
        assertEquals("move plus werkt niet",pOud.length()+5,p.length());
        p = new Period(bt,et);
        p.move(10);
        assertEquals("begintijdstip is niet verplaatst",10,p.getBeginTime().difference(bt));
        assertEquals("eindtijdstip is niet verplaatst",10,p.getEndTime().difference(et));
        p = new Period(bt,et);
        p.move(-10);
        assertEquals("begintijdstip is niet verplaatst NEGATIEF",10,p.getBeginTime().difference(bt));
        assertEquals("eindtijdstip is niet verplaatst NEGATIEF",10,p.getEndTime().difference(et));
        
        p = new Period(bt,et);
        Period p2 = new Period(bt1,bt1);
        assertTrue("perioden overlappen wel volledig",p.isPartOf(p2));
                Time bt3 = new Time(2013,3,20,2,30);
        Time et3 = new Time(2013,5,20,2,30);
        Time bt4 = new Time(2013,2,20,2,30);
        Time et4 = new Time(2013,4,20,2,30);
        p = new Period(bt3,et3);
        p2 = new Period(bt4,et4);
        assertNotNull("perioden hebben een intersect",p.unionWith(p2));
        assertNotNull("perioden hebben een intersect",p.intersectionWith(p2));
        
        //assertNotNull("perioden overlappen wel",)
    }

    
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
