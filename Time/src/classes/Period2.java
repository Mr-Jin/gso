/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package classes;

import fontys.time.IPeriod;
import fontys.time.ITime;

/**
 *
 * @author tim smeets
 */
public class Period2 implements IPeriod{
    ITime bt;
    long duur;
    
    public Period2(ITime bt, ITime et) {
        this.bt = bt;
        this.duur = bt.difference(et);
    }
    
    
    @Override
    public ITime getBeginTime() {
        return this.bt;
    }

    @Override
    public ITime getEndTime() {
        Time t = new Time(this.bt.getYear(),this.bt.getMonth(),this.bt.getDay(),this.bt.getHours(),this.bt.getMinutes());
        t.plus((int) duur);
        return t;
    }

    @Override
    public int length() {
       return (int) this.duur;
    }

    @Override
    public void setBeginTime(ITime beginTime) {
       this.bt = beginTime;
    }

    @Override
    public void setEndTime(ITime endTime) {
       this.duur = endTime.difference(bt);
    }

    @Override
    public void move(int minutes) {
        this.bt.plus(minutes);
    }

    @Override
    public void changeLengthWith(int minutes) {
        this.duur += minutes;
    }

    @Override
    public boolean isPartOf(IPeriod period) {
        if(period.getBeginTime().compareTo(this.getBeginTime())>0&& this.length() <= period.length() ){
            return true;
        }
        return false;
    }
    
    
    /**
     * 
     * @param period de te vergelijken periode
     * @return true wanner ze kruisen false wanner dit niet zo is
     */
    public boolean isIntersect(IPeriod period){
        if(this.getBeginTime().compareTo(period.getEndTime())<0 || this.getEndTime().compareTo(period.getBeginTime())>0 ){
            return false;
        }
        return true;
    }
    
    @Override
    public IPeriod unionWith(IPeriod period) {
        
        if(!this.isIntersect(period)){
            return null;
        }
        
        Time t = new Time(1,1,1,1,1);
        Period2 newp = new Period2(t,new Time(1,1,1,1,1));
        if(this.bt.compareTo(period.getBeginTime())<=0){
            newp.setBeginTime(bt);
        }
        else{
            newp.setBeginTime(period.getBeginTime());
        }
        if(this.getEndTime().compareTo(period.getEndTime())<=0){
            newp.setEndTime(period.getEndTime());
        }else{
            newp.setEndTime(this.getEndTime());
        }
        return null;
    }

    @Override
    public IPeriod intersectionWith(IPeriod period) {
        ITime btnew;
        ITime etnew;
       if(!this.isIntersect(period)){
           return null;
       }
       if(this.isPartOf(period)){
           return this;
       }
       if(period.isPartOf(this)){
           return period;
       }
       
       if(this.getBeginTime().compareTo(period.getBeginTime())>0){
           btnew = this.getBeginTime();
           etnew = period.getEndTime();
           return new Period2(btnew,etnew);
       }
       if(this.getBeginTime().compareTo(period.getBeginTime())<0){
           btnew = period.getBeginTime();
           etnew = this.getEndTime();
           return new Period2(btnew,etnew);
       }
       return null;
       
    }
    
}
