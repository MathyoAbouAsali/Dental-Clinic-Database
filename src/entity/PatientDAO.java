/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;
import core.DB;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


/**
 *
 * @author Gokhan
 */
public class PatientDAO implements DAO<Patient>
{   
    public PatientDAO() {
        
    }
    List<Patient> patients;
    /**
     * Get a single patient entity as a patient object
     * @param id
     * @return 
     */
    @Override
    public Optional<Patient> get(int id) {
        DB db = DB.getInstance();
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM DC_Patient WHERE Patient_ID = ?";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            Patient patient = null;
            while (rs.next()) {
                patient = new Patient(rs.getInt("Patient_ID"), rs.getString("First_Name"), rs.getString("Last_Name"), rs.getString("Patient_Gender"),
                        rs.getString("Patient_Phone_Number"));
            }
            return Optional.ofNullable(patient);
        } catch (SQLException ex) {
            System.err.println(ex.toString());
            return null;
        }
    }
    
    /**
     * Get all patient entities as a List
     * @return 
     */
    @Override
    public List<Patient> getAll() {
        DB db = DB.getInstance();
        ResultSet rs = null;
        patients = new ArrayList<>();
        try {
            String sql = "SELECT * FROM DC_Patient";
            rs = db.executeQuery(sql);
            Patient patient = null;
            while (rs.next()) {
                patient = new Patient(rs.getInt("Patient_ID"), rs.getString("First_Name"), rs.getString("Last_Name"),
                        rs.getString("Patient_Gender"), rs.getString("Patient_Phone_Number"));
                patients.add(patient);
            }
            return patients;
        } catch (SQLException ex) {
            System.err.println(ex.toString());
            return null;
        }
    }
    
    /**
     * Insert a patient object into patient table
     * @param patient 
     */
    @Override
    public void insert(Patient patient)
    {
        DB db = DB.getInstance();
        try {
            String sql = "INSERT INTO DC_Patient(Patient_ID, First_Name, Last_Name, Patient_Gender, Patient_Phone_Number) VALUES (?, ?, ?, ?, ? )";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setInt(1, patient.getpID());
            stmt.setString(2, patient.getPatientFname());
            stmt.setString(3, patient.getPatientLname());
            stmt.setString(4, patient.getPatientGender());
            stmt.setString(5, patient.getPatientPhoneN());
            int rowInserted = stmt.executeUpdate();
            if (rowInserted > 0) {
                System.out.println("A new patient was inserted successfully!");
            }
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
    }
    
    /**
     * Update a patient entity in database if it exists using a patient object
     * @param patient
     */
    @Override
    public void update(Patient patient) {
        DB db = DB.getInstance();
        try {
            String sql = "UPDATE DC_Patient SET First_Name=?, Last_Name=?, Patient_Gender=?, Patient_Phone_Number=?  WHERE Patient_ID=?";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setString(1, patient.getPatientFname());
            stmt.setString(2, patient.getPatientLname());
            stmt.setString(3, patient.getPatientGender());
            stmt.setString(4, patient.getPatientPhoneN());
            stmt.setInt(5, patient.getpID());
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing patient was updated successfully!");
            }
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
    }
    
    /**
     * Delete a patient from patient table if the entity exists
     * @param patient 
     */
    @Override
    public void delete(Patient patient) {
        DB db = DB.getInstance();
        try {
            String sql = "DELETE FROM DC_Patient WHERE Patient_ID = ?";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setInt(1, patient.getpID());
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A patient was deleted successfully!");
            }
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
    }
    
    /**
     * Get all column names in a list array
     * @return 
     */
    @Override
    public List<String> getColumnNames() {
        DB db = DB.getInstance();
        ResultSet rs = null;
        List<String> headers = new ArrayList<>();
        try {
            String sql = "SELECT * FROM DC_Patient WHERE Patient_ID = -1";
//We just need this sql query to get the column headers
            rs = db.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            //Get number of columns in the result set
            int numberCols = rsmd.getColumnCount();
            for (int i = 1; i <= numberCols; i++) {
                headers.add(rsmd.getColumnLabel(i));//Add column headers to the list
            }
            return headers;
        } catch (SQLException ex) {
            System.err.println(ex.toString());
            return null;
        } 
    }
}
