/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package classes;

import fontys.time.DayInWeek;
import java.util.GregorianCalendar;
import java.util.Calendar;
import fontys.time.ITime;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Luke van der Loop
 */
public class Time implements ITime{
    private GregorianCalendar t;
    
    public Time(int year, int month, int day, int hour, int minute) throws IllegalArgumentException{
        if(month<1 || 12<month)
        {throw new IllegalArgumentException("Ongeoorloofd getal in maand.");}
        if(day<1 || 31<day)
        {throw new IllegalArgumentException("Ongeoorloofd getal in dag.");}
        if(hour<0 || 23<hour)
        {throw new IllegalArgumentException("Ongeoorloofd getal in uur.");}
        if(minute<0 || 59<minute)
        {throw new IllegalArgumentException("Ongeoorloofd getal in dag.");}
        t = new GregorianCalendar();
        t.set(year,month,day,hour,minute);
    }

    @Override
    public int getYear() {
        return t.get(GregorianCalendar.YEAR);
    }
    @Override
    public int getMonth() {
        return t.get(GregorianCalendar.MONTH);
    }

    @Override
    public int getDay() {
        return t.get(GregorianCalendar.DAY_OF_MONTH);
    }

    @Override
    public int getHours() {
        return t.get(GregorianCalendar.HOUR_OF_DAY);
    }

    @Override
    public int getMinutes() {
        return t.get(GregorianCalendar.MINUTE);
    }

    @Override
    public DayInWeek getDayInWeek() {
        return DayInWeek.values()[t.get(GregorianCalendar.DAY_OF_WEEK_IN_MONTH)];
    }

    @Override
    public ITime plus(int minutes) {
        t.add(GregorianCalendar.MINUTE, minutes);       
        return this;
    }

    @Override
    public int difference(ITime time) {
        GregorianCalendar otherTime = new GregorianCalendar();
        otherTime.set(time.getYear(),time.getMonth(),time.getDay(),time.getHours(),time.getMinutes());
            
        return (int)((otherTime.getTimeInMillis()-t.getTimeInMillis())/60000);
        
    }

    @Override
    public int compareTo(ITime o) {
        GregorianCalendar t2 = new GregorianCalendar();
        t2.set(o.getYear(), o.getMonth(), o.getDay(), o.getHours(), o.getMinutes());
        return this.difference(o);
    }
    
}
