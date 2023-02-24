/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.dateTime;

/**
 *
 * @author Gokhan
 */
public class Dentist 
{
    private int DentistID;
    private String DentistFname;
    private String DentistLname;
    private String DentistPhoneN;
    private String DentistTitle;
    
    public Dentist(int dID, String dFname, String dLname, String dPhoneN, String dTitle)
    {
        this.DentistID = dID;
        this.DentistFname = dFname;
        this.DentistLname = dLname;
        this.DentistPhoneN = dPhoneN;
        this.DentistTitle = dTitle;

    }

    public int getdID() {
        return DentistID;
    }

    public String getdFname() {
        return DentistFname;
    }
    
    public String getdLname() {
        return DentistLname;
    }

    public String getdPhoneN() {
        return DentistPhoneN;
    }

    public String getdTitle() {
        return DentistTitle;
    }

    @Override
    public String toString() {
        return "Dentist{" + "ID=" + DentistID + ", firstName=" + DentistFname + ", lastName=" + DentistLname + ", "
                + "PhoneNumber=" + DentistPhoneN + ", Title=" + DentistTitle + '}';
    }



    

    
}
