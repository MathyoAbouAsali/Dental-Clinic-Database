/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;
import java.sql.Date;
/**
 *
 * @author Gokhan
 */
public class Patient 
{
    private int PatientID;
    private String PatientFname;
    private String PatientLname;
    private String PatientGender;
    private String PatientPhoneN;
    
    public Patient(int ID, String Fname, String Lname, String Gender, String PhoneN)
    {
        this.PatientID = ID;
        this.PatientFname = Fname;
        this.PatientLname = Lname;
        this.PatientGender = Gender;
        this.PatientPhoneN = PhoneN;
    }

    public int getpID() {
        return PatientID;
    }

    public String getPatientFname() {
        return PatientFname;
    }
    
    public String getPatientLname() {
        return PatientLname;
    }
    
    public String getPatientGender() {
        return PatientGender;
    }
        
    public String getPatientPhoneN() {
        return PatientPhoneN;
    }
    

    @Override
    public String toString() {
        return "Patient{" + "ID=" + PatientID + ", Firstname=" + PatientFname + ", Lastname=" + PatientLname + ", Gender=" + PatientGender + ", PhoneNumber=" + PatientPhoneN + '}';
    }

   
}
