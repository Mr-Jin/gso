/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package classes;

import fontys.time.IPeriod;
import fontys.time.ITime;
import java.util.Calendar;

/**
 *
 * @author Jin Tran
 */
public class Period implements IPeriod {

    private ITime bt;
    private ITime et;
            
    public Period(ITime bt, ITime et) {
        this.bt = bt;
        this.et = et;
    }

    @Override
    public ITime getBeginTime() {
        return bt;
    }

    @Override
    public ITime getEndTime() {
        return et;
    }

    @Override
    public int length() {
        return this.bt.difference(et);
    }

    @Override
    public void setBeginTime(ITime beginTime) {
        this.bt = beginTime;
    }

    @Override
    public void setEndTime(ITime endTime) {
        this.et = endTime;
    }

    @Override
    public void move(int minutes) {
        this.et.plus(minutes);
        this.bt.plus(minutes);
    }

    @Override
    public void changeLengthWith(int minutes) {
        this.bt.plus(minutes);
    }

    @Override
    public boolean isPartOf(IPeriod period) {
        if(this.bt.compareTo(period.getBeginTime()) <= 0 && this.et.compareTo(period.getEndTime()) <= 0)
        {
            return true;
        }
        return false;
    }

    @Override
    public IPeriod unionWith(IPeriod period) {
        Time t = new Time(1,1,1,1,1);
        Period p = new Period(t,new Time(1,1,1,1,1));
        
        if(!this.isPartOf(period)){
            return this;
        }
        if(period.getBeginTime().compareTo(this.getBeginTime())<=0){
            p.setBeginTime(period.getBeginTime());
        }
        else{
            p.setBeginTime(this.getBeginTime());
        }
        if(period.getEndTime().compareTo(this.getEndTime())>=0){
            p.setEndTime(period.getEndTime());
        }
        else{
            p.setEndTime(this.getEndTime());
        }
        
        if(p.getBeginTime()!=t){
            return p;
        }
        
        return null;
    }
    

    @Override
    public IPeriod intersectionWith(IPeriod period) {
        Calendar thisEndCal = Calendar.getInstance();
        thisEndCal.setTimeInMillis(0);
        thisEndCal.set(period.getEndTime().getYear(), period.getEndTime().getMonth(), period.getEndTime().getDay(), period.getEndTime().getHours(), period.getEndTime().getMinutes());
        
        Calendar periodBeginCal = Calendar.getInstance();
        periodBeginCal.setTimeInMillis(0);
        periodBeginCal.set(period.getBeginTime().getYear(), period.getBeginTime().getMonth(), period.getBeginTime().getDay(), period.getBeginTime().getHours(), period.getBeginTime().getMinutes());
               
        if (thisEndCal.after(periodBeginCal))
        {
            if (period.length() > this.length()){
                return period;
            }
            else
            {
                return this;
            }             
        }
        else
        { 
            return null;
        }

    }
    
}
