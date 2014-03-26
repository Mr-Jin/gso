/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package classes;

import fontys.time.IPeriod;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author tim
 */
class Appointment {
    private String subject;
    private IPeriod period;
    private ArrayList<Contact> invitees;
    
    public Appointment(String subject, IPeriod period){
        this.subject = subject;
        this.period = period;
        }
    public String getSubject(){
        return this.subject;
    }
    
    public IPeriod getPeriod(){
        return this.period;
    }
    
    public Iterator<Contact> invitees(){
        return this.invitees.iterator();
    }
    
    public boolean AddContact(Contact c)
    {
        
    }
    
    public void RemoveContact(Contact o)
    {
        this.invitees.remove(o);
    }
    
}
