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


/**
 *
 * @author Gokhan
 */
public class AppointmentDAO implements DAO<Appointment>
{   
    public AppointmentDAO() {
        
    }
    List<Appointment> appointments;
    /**
     * Get a single appointment entity as a appointment object
     * @param id
     * @return 
     */
    @Override
    public Optional<Appointment> get(int id) {
        DB db = DB.getInstance();
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM DC_Appointment WHERE Appointment_ID = ?";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            Appointment appointment = null;
            while (rs.next()) {
                appointment = new Appointment(rs.getInt("Appointment_ID"), rs.getString("Appointment_Date_Time"), rs.getInt("Patient_ID"), rs.getInt("Dentist_ID"),
                        rs.getString("Appointment_Note"));
            }
            return Optional.ofNullable(appointment);
        } catch (SQLException ex) {
            System.err.println(ex.toString());
            return null;
        }
    }
    
    /**
     * Get all appointment entities as a List
     * @return 
     */
    @Override
    public List<Appointment> getAll() {
        DB db = DB.getInstance();
        ResultSet rs = null;
        appointments = new ArrayList<>();
        try {
            String sql = "SELECT * FROM DC_Appointment";
            rs = db.executeQuery(sql);
            Appointment appointment = null;
            while (rs.next()) {
                appointment = new Appointment(rs.getInt("Appointment_ID"), rs.getString("Appointment_Date_Time"), rs.getInt("Patient_ID"), rs.getInt("Dentist_ID"),
                        rs.getString("Appointment_Note"));
                appointments.add(appointment);
            }
            return appointments;
        } catch (SQLException ex) {
            System.err.println(ex.toString());
            return null;
        }
    }
    
    /**
     * Insert a appointment object into appointment table
     * @param appointment 
     */
    @Override
    public void insert(Appointment appointment)
    {
        DB db = DB.getInstance();
        try {
            String sql = "INSERT INTO DC_Appointment(Appointment_ID, Appointment_Date_Time, Patient_ID, Dentist_ID, Appointment_Note) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setInt(1, appointment.getapID());
            stmt.setString(2, appointment.getapDateTime());
            stmt.setInt(3, appointment.getpID());
            stmt.setInt(4, appointment.getdID());
            stmt.setString(5, appointment.getapNote());
            int rowInserted = stmt.executeUpdate();
            if (rowInserted > 0) {
                System.out.println("A new appointment was inserted successfully!");
            }
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
    }
    
    /**
     * Update a appointment entity in database if it exists using a appointment object
     * @param appointment
     */
    @Override
    public void update(Appointment appointment) {
        DB db = DB.getInstance();
        try {
            String sql = "UPDATE DC_Appointment SET  Patient_ID=?, Dentist_ID=?, Appointment_Note=?, " + "Appointment_Date_Time=?  WHERE Appointment_ID=?";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setInt(1, appointment.getpID());
            stmt.setInt(2, appointment.getdID());
            stmt.setString(3, appointment.getapNote());
            stmt.setString(4, appointment.getapDateTime());
            stmt.setInt(5, appointment.getapID());
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing appointment was updated successfully!");
            }
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
    }
    
    /**
     * Delete a appointment from appointment table if the entity exists
     * @param appointment 
     */
    @Override
    public void delete(Appointment appointment) {
        DB db = DB.getInstance();
        try {
            String sql = "DELETE FROM DC_Appointment WHERE Appointment_ID = ?";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setInt(1, appointment.getapID());
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("An appointment was deleted successfully!");
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
            String sql = "SELECT * FROM DC_Appointment WHERE Appointment_ID = -1";
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
