/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import classes.Contact;
import classes.Period;
import classes.Period2;
import classes.Time;
import classes.Appointment;
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

    //@author Luke van der Loop
    @Test
    public void TestTime() {
        Time t = new Time(2013, 3, 20, 2, 30);
        Time t2 = new Time(2013, 3, 20, 2, 40);
        assertEquals("Jaar komt niet overeen", 2013, t.getYear());
        assertEquals("Maand komt niet overeen", 3, t.getMonth());
        assertEquals("Dag komt niet overeen", 20, t.getDay());
        assertEquals("Uur komt niet overeen", 2, t.getHours());
        assertEquals("Minuten komt niet overeen", 30, t.getMinutes());
        assertEquals("Dag van de week komt niet overeen", "WED", t.getDayInWeek().toString());

        t.plus(20);
        assertEquals("Minuten plus 20 komt niet overeen", 50, t.getMinutes());
        t.plus(-40);
        assertEquals("Minuten plus -20 komt niet overeen", 10, t.getMinutes());

        assertEquals("Verschil tijd komt niet overeen", 30, t.difference(t2));
    }

    //@Author Luke van der loop
    @Test
    public void testFouteDatums() {
        try {
            Time falsemonth = new Time(2013, 13, 20, 2, 30);
            fail("Foute maand");
        } catch (IllegalArgumentException e) {
        }

        try {
            Time falseday = new Time(2013, 12, 33, 2, 30);
            fail("Foute dag");
        } catch (IllegalArgumentException e) {
        }

        try {
            Time falsehour = new Time(2013, 12, 20, 24, 30);
            fail("Fout uur");
        } catch (IllegalArgumentException e) {
        }

        try {
            Time falseminute = new Time(2013, 12, 20, 2, 60);
            fail("Foute dag");
        } catch (IllegalArgumentException e) {
        }
    }

    @Test

    //@Author Jin Tran
    public void TestPeriod2() {
        // periode 1= -adfas
        //periode 2= =
        //periode 3 = #
        //  bt2##bt#-#-bt1=#-=#-=#et1-#-#-et##et2
        // bt2 > bt > bt1 > et1 > et > et2
        Time bt = new Time(2013, 3, 20, 2, 30);
        Time et = new Time(2014, 3, 20, 2, 30);
        Time bt1 = new Time(2013, 4, 20, 2, 20);
        Time et1 = new Time(2014, 2, 20, 2, 30);
        Time bt2 = new Time(2012, 3, 20, 2, 30);
        Time et2 = new Time(2014, 4, 20, 2, 30);
        Period2 p = new Period2(bt, et);

        assertEquals("periode lengte in minuten fout", 525600, p.length());

        assertEquals("get begintijd klopt niet", 0, p.getBeginTime().difference(bt));
        assertEquals("get eindtijd klopt niet", 0, p.getEndTime().compareTo(et));

        p.setBeginTime(bt2);
        assertEquals("set begintijd klopt niet", bt2, p.getBeginTime());
        p.setEndTime(et2);
        assertEquals("set eindtijd klopt niet", 0, p.getEndTime().difference(et2));
        p = new Period2(bt, et);
        Period2 pOud = new Period2(bt, et);
        p.changeLengthWith(5);
        assertEquals("change length with werkt niet", pOud.length() + 5, p.length());
        p = new Period2(bt, et);
        p.move(10);
        assertEquals("begintijdstip is niet verplaatst", 0, bt.difference(p.getBeginTime()));
        assertEquals("eindtijdstip is niet verplaatst", 10, et.difference(p.getEndTime()));
        p = new Period2(bt, et);
        p.move(-10);
        assertEquals("begintijdstip is niet verplaatst NEGATIEF", 0, bt.difference(p.getBeginTime()));
        //assertEquals("eindtijdstip is niet verplaatst NEGATIEF",-10,et.difference(p.getEndTime()));

        p = new Period2(bt, et);
        Time bt3 = new Time(2013, 3, 20, 2, 30);
        Time et3 = new Time(2013, 5, 20, 2, 30);
        Time bt4 = new Time(2013, 2, 20, 2, 30);
        Time et4 = new Time(2013, 4, 20, 2, 30);
        Time et5 = new Time(2014, 6, 20, 2, 30);
        p = new Period2(bt3, et3);
        Period2 p2 = new Period2(bt4, et4);
        Period2 pUnion = new Period2(bt4, et3);
        Period2 pWrong = new Period2(et3,et5);
        Period2 pIntersect1 = new Period2(bt3,et4);
        assertNotNull("perioden hebben een union", p.unionWith(p2));
        assertEquals("Union with komt niet overeen", pUnion.length(), p2.unionWith(p).length());

        try
        {
            p2.unionWith(pWrong);
            fail("Union zou Exception moeten geven");
        }
        catch(IllegalArgumentException e){}
        
        assertNotNull("perioden hebben een intersect", p.intersectionWith(p2));
        
        assertEquals("Intersection with komt niet overeen",pIntersect1.length(),p.intersectionWith(p2).length());
    }

    //@author Tim Smeets
    @Test
    public void TestPeriod() {
        // periode 1= -
        //periode 2= =
        //periode 3 = #
        //  bt2##bt#-#-bt1=#-=#-=#et1-#-#-et##et2
        // bt2 > bt > bt1 > et1 > et > et2
        Time bt = new Time(2013, 3, 20, 0, 0);
        Time et = new Time(2014, 3, 20, 0, 0);
        Time bt1 = new Time(2013, 4, 20, 2, 20);
        Time et1 = new Time(2014, 2, 20, 2, 30);
        Time bt2 = new Time(2012, 3, 20, 2, 30);
        Time et2 = new Time(2014, 4, 20, 2, 30);
        Period p = new Period(bt, et);

        assertEquals("periode lengte in minuten fout", 525600, p.length());

        assertEquals("get begintijd klopt niet", bt, p.getBeginTime());
        assertEquals("get eindtijd klopt niet", et, p.getEndTime());

        p.setBeginTime(bt2);
        assertEquals("set begintijd klopt niet", bt2, p.getBeginTime());
        p.setEndTime(et2);
        assertEquals("set eindtijd klopt niet", 0, p.getEndTime().compareTo(et2));
        Period pOud = new Period(bt2, et2);
        p.changeLengthWith(10);
        assertEquals("change length with werkt niet", pOud.length(), p.length());
        p = new Period(bt, et);
        p.move(10);
        assertEquals("begintijdstip is niet verplaatst", 0, p.getBeginTime().difference(bt));
        assertEquals("eindtijdstip is niet verplaatst", 0, p.getEndTime().difference(et));
        p = new Period(bt, et);
        p.move(-10);
        assertEquals("begintijdstip is niet verplaatst NEGATIEF", 0, p.getBeginTime().difference(bt));
        assertEquals("eindtijdstip is niet verplaatst NEGATIEF", 0, p.getEndTime().difference(et));

        Time bt3 = new Time(2013, 3, 20, 2, 30);
        Time et3 = new Time(2013, 5, 20, 2, 30);
        Time bt4 = new Time(2013, 2, 20, 2, 30);
        Time et4 = new Time(2013, 4, 20, 2, 30);
        p = new Period(bt3, et3); // 2013-3-20-2-30 t/m 2013-5-20-2-30
        Period p1 = new Period(bt, et); // 2013-3-20-0-0 t/m 2014-3-20-0-0
        Period p2 = new Period(bt1, et1);// 2013-4-20-2-20 t/m 2014-2-20-2-30
        Period p3 = new Period(bt4, et4); // 2013-2-20-2-30 t/m 2013-4-20-2-30
        Period pWrong = new Period(et1, et);
        Period pIntersect = new Period(bt,et3);
        assertNotNull("perioden horen een union te hebben", p.unionWith(p2));
        assertEquals("Periode Union komt niet overeen", p1.length(), p.unionWith(p1).length());
        try {
            p.unionWith(pWrong);
            fail("Periode Union zou exception moeten geven");
        } catch (IllegalArgumentException e) {
        }

        assertEquals("Periode Union komt niet overeen (periode binnen in een andere periode)",p1.length(),p1.unionWith(p2).length());
        
        assertNotNull("perioden horen een intersect te hebben", p.intersectionWith(p2));
        assertEquals("Periode Intersect komt niet overeen",pIntersect.length(),p.intersectionWith(p1).length());
       
    }

    //@Author Jin Tran
    @Test
    public void testAppointment() {

        Time bt = new Time(2013, 3, 20, 2, 30);
        Time et = new Time(2014, 3, 20, 2, 30);
        Period p = new Period(bt, et);
        Appointment a = new Appointment("Afspraak", p);
        Contact c = new Contact("Peter");

        assertNotNull("Aanmaken appointment mislukt", a);

        assertEquals("Onderwerp komt niet overeen", "Afspraak", a.getSubject());
        assertEquals("Periode komt niet overaan", p, a.getPeriod());
        //assertFalse("Contacten worden teruggegeven", );
        assertTrue("Contact is niet toegevoegd", a.AddContact(c));

        assertTrue("Contact is niet succesvol toegevoegd", a.invitees().hasNext());

        a.RemoveContact(c);

        assertFalse("Contact is niet succesvol verwijderd", a.invitees().hasNext());

    }

    //@Author Tim Smeets
    @Test
    public void testContact() {
        Contact c = new Contact("Piet");
        Contact c1 = new Contact("Jan");

        Time bt = new Time(2013, 3, 20, 2, 30);
        Time et = new Time(2014, 3, 20, 2, 30);
        Period p = new Period(bt, et);
        Appointment a = new Appointment("Afsprak1", p);

        assertEquals("Naam komt niet overeen", "Piet", c.getName());
        assertTrue("Afspraak is niet toegevoegd", c.addAppointment(a));
        assertTrue("Afspraak is niet succesvol toegevoegd", c.Appointments().hasNext());

        c.removeAppointment(a);

        assertFalse("Afspraak is niet succesvol verwijderd", c.Appointments().hasNext());
    }

}
