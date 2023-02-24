/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

/**
 *
 * @author Gokhan
 */
public class Appointment 
{
    private int AppointmentID;
    private String AppointmentDateTime;
    private int PatientID;
    private int DentistID;
    private String AppointmentNote;
    
    public Appointment(int apID, String apDateTime, int pID, int dID, String apNote)
    {
        this.AppointmentID = apID;
        this.AppointmentDateTime = apDateTime;
        this.PatientID = pID;
        this.DentistID = dID;
        this.AppointmentNote = apNote;
    }

    public int getapID() {
        return AppointmentID;
    }

    public String getapDateTime() {
        return AppointmentDateTime;
    }
    
    public int getpID() {
        return PatientID;
    }
    
    public int getdID() {
        return DentistID;
    }
        
    public String getapNote() {
        return AppointmentNote;
    }
    

    @Override
    public String toString() {
        return "Appointment{" + "ID=" + AppointmentID + ", AppointmentDateTime=" + AppointmentDateTime + ", PatientID=" + PatientID + ", DentistID=" + DentistID
                + ", AppointmentNote=" + AppointmentNote + '}';
    }

   
}
