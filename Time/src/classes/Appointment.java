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
public class Appointment {
    private String subject;
    private IPeriod period;
    private ArrayList<Contact> invitees;
    
    
    /**
     * Constructor van Appointment.
     * maakt een nieuwe afspraak aan met een onderwerp en een periode.
     * @param subject
     * @param period 
     */
    public Appointment(String subject, IPeriod period){
        this.subject = subject;
        this.period = period;
        this.invitees = new ArrayList<Contact>();
        }
    
    /**
     * om het onderwerp terug te geven
     * @return Subject
     */
    public String getSubject(){
        return this.subject;
    }
    
    /**
     * om de periode terug te geven.
     * @return Period
     */
    public IPeriod getPeriod(){
        return this.period;
    }
    
    /**
     * Een Iterator door alle toegevoegde contacten
     * @return Iterator<Contact>
     */
    public Iterator<Contact> invitees(){
        return this.invitees.iterator();
    }
    
    /**
     * Een contact toe te voegen, returned true als gelukt is en false wanneer niet gelukt is
     * @param c
     * @return true/false
     */
    public boolean AddContact(Contact c)
    {
        if(c.Appointments() == null)
        {
            this.invitees.add(c);
            return true;
        }
        else
        {
        while(c.Appointments().hasNext())
        {
            Appointment a = c.Appointments().next();
            if(a.getPeriod().intersectionWith(this.period) != null)
            {
                return false;
            }
        }
        this.invitees.add(c);
        }
        return true;
    }
    
    /**
     * verwijderd een contact.
     * @param o 
     */
    public void RemoveContact(Contact o)
    {
        this.invitees.remove(o);
    }
    
}
