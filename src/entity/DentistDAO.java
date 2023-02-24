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
public class DentistDAO implements DAO<Dentist>
{   
    public DentistDAO() {
        
    }
    List<Dentist> dentists;
    /**
     * Get a single dentist entity as an dentist object
     * @param id
     * @return 
     */
    @Override
    public Optional<Dentist> get(int id) {
        DB db = DB.getInstance();
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM DC_Dentist WHERE Dentist_ID = ?";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            Dentist dentist = null;
            while (rs.next()) {
                dentist = new Dentist(rs.getInt("Dentist_ID"), rs.getString("Dentist_First_Name"), 
                        rs.getString("Dentist_Last_Name"), rs.getString("Dentist_Phone_Number"), 
                        rs.getString("Dentist_Title"));
            }
            return Optional.ofNullable(dentist);
        } catch (SQLException ex) {
            System.err.println(ex.toString());
            return null;
        }
    }
    
    /**
     * Get all dentist entities as a List
     * @return 
     */
    @Override
    public List<Dentist> getAll() {
        DB db = DB.getInstance();
        ResultSet rs = null;
        dentists = new ArrayList<>();
        try {
            String sql = "SELECT * FROM DC_Dentist";
            rs = db.executeQuery(sql);
            Dentist dentist = null;
            while (rs.next()) {
                 dentist = new Dentist(rs.getInt("Dentist_ID"), rs.getString("Dentist_First_Name"), 
                        rs.getString("Dentist_Last_Name"), rs.getString("Dentist_Phone_Number"), 
                        rs.getString("Dentist_Title"));
                dentists.add(dentist);
            }
            return dentists;
        } catch (SQLException ex) {
            System.err.println(ex.toString());
            return null;
        }
    }
    
    /**
     * Insert an dentist object into dentist table
     * @param dentist 
     */
    @Override
    public void insert(Dentist dentist)
    {
        DB db = DB.getInstance();
        try {
            String sql = "INSERT INTO DC_Dentist(Dentist_ID, Dentist_First_Name, Dentist_Last_Name, Dentist_Phone_Number, Dentist_Title) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setInt(1, dentist.getdID());
            stmt.setString(2, dentist.getdFname());
            stmt.setString(3, dentist.getdLname());
            stmt.setString(4, dentist.getdPhoneN());
            stmt.setString(5, dentist.getdTitle());            
            int rowInserted = stmt.executeUpdate();
            if (rowInserted > 0) {
                System.out.println("A new DENTIST was inserted successfully!");
            }
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
    }
    
    /**
     * Update an dentist entity in database if it exists using an dentist object
     * @param dentist
     */
    @Override
    public void update(Dentist dentist) {
        DB db = DB.getInstance();
        try {
            String sql = "UPDATE DC_Dentist SET Dentist_First_Name=?, Dentist_Last_Name=?, Dentist_Phone_Number=?, Dentist_Title=? WHERE Dentist_ID=?";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setString(1, dentist.getdFname());
            stmt.setString(2, dentist.getdLname());
            stmt.setString(3, dentist.getdPhoneN());
            stmt.setString(4, dentist.getdTitle());
            stmt.setInt(5, dentist.getdID());
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing dentist was updated successfully!");
            }
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
    }
    
    /**
     * Delete an dentist from dentist table if the entity exists
     * @param dentist 
     */
    @Override
    public void delete(Dentist dentist) {
        DB db = DB.getInstance();
        try {
            String sql = "DELETE FROM DC_Dentist WHERE Dentist_ID = ?";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setInt(1, dentist.getdID());
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("An dentist was deleted successfully!");
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
            String sql = "SELECT * FROM DC_Dentist WHERE Dentist_ID = -1";
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
