/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package classes;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author tim smeets
 */
public class Contact {

    /**
     * bevat afspraken voor agenda
     */
    private ArrayList<Appointment> agenda;
    
    /**
     * naam van contact
     */
    private String name;
    
    /**
     *  constructor
     * @param name naam van contact
     */
    public Contact(String name){
        this.name = name;
        this.agenda = new ArrayList();
    }
    
    /**
     *
     * @return naam van contact
     */
    public String getName(){
        return this.name;
    }
    /**
     * 
     * @param a appointment
     * @return kan worden geplaatst of niet
     */
   boolean addAppointment(Appointment b){
       for(Appointment a: this.agenda){
           if(b.getPeriod().intersectionWith(a.getPeriod()) != null)
           {
               return false;
           }
       }
       this.agenda.add(b);
       return true;
           
   }
   /**
    * verwijder afspraak uit agenda
    * @param a de te verwijderen appointment
    */
   void removeAppointment(Appointment a){
       this.agenda.remove(a);
   }
    
    public Iterator<Appointment> Appointments()
    {
        return this.agenda.iterator();
    }
    
}
