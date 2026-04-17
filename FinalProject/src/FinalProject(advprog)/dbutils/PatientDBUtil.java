package FinalProject.advprog.dbutils;

import FinalProject.advprog.model.Patient;
import java.sql.*;

public class PatientDBUtil {

    // ---------------------------------------------------------
    // INSERT PATIENT (with LAST_INSERT_ID)
    // ---------------------------------------------------------
    public static Patient insertPatient(Patient p) throws SQLException {

        String sql = "INSERT INTO patienttable "
                + "(PtLastName, PtPreviousLastName, PtFirstName, HomeAddress1, HomeCity, "
                + "HomeStateProvinceRegion, HomeZipPostalCode, HomePhone, DateOfBirth, Gender) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, p.getLastName());
            ps.setString(2, p.getPreviousLastName());
            ps.setString(3, p.getFirstName());
            ps.setString(4, p.getHomeAddress1());
            ps.setString(5, p.getHomeCity());
            ps.setString(6, p.getHomeStateProvinceRegion());
            ps.setString(7, p.getHomeZipPostalCode());
            ps.setString(8, p.getHomePhone());
            ps.setString(9, p.getDateOfBirth());
            ps.setString(10, p.getGender());

            ps.executeUpdate();

            // Retrieve the auto-generated PatientID
            ResultSet keys = ps.getGeneratedKeys();
            if (keys.next()) {
                int newId = keys.getInt(1);
                return getPatientById(newId);   // return the full patient object
            }
        }

        return null;
    }

    // ---------------------------------------------------------
    // UPDATE PATIENT
    // ---------------------------------------------------------
    public static void updatePatient(Patient p) throws SQLException {

        String sql = "UPDATE patienttable SET "
                + "PtLastName=?, PtPreviousLastName=?, PtFirstName=?, HomeAddress1=?, HomeCity=?, "
                + "HomeStateProvinceRegion=?, HomeZipPostalCode=?, HomePhone=?, DateOfBirth=?, Gender=? "
                + "WHERE PatientID=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, p.getLastName());
            ps.setString(2, p.getPreviousLastName());
            ps.setString(3, p.getFirstName());
            ps.setString(4, p.getHomeAddress1());
            ps.setString(5, p.getHomeCity());
            ps.setString(6, p.getHomeStateProvinceRegion());
            ps.setString(7, p.getHomeZipPostalCode());
            ps.setString(8, p.getHomePhone());
            ps.setString(9, p.getDateOfBirth());
            ps.setString(10, p.getGender());
            ps.setInt(11, p.getPatientID());

            ps.executeUpdate();
        }
    }

    // ---------------------------------------------------------
    // DELETE PATIENT (soft delete)
    // ---------------------------------------------------------
    public static void deletePatient(int id) throws SQLException {

        String sql = "UPDATE patienttable SET IsDeleted = 1 WHERE PatientID = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    // ---------------------------------------------------------
    // GET PATIENT BY ID
    // ---------------------------------------------------------
    public static Patient getPatientById(int id) throws SQLException {

        String sql = "SELECT * FROM patienttable WHERE PatientID = ? AND IsDeleted = 0";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Patient p = new Patient();
                p.setPatientID(rs.getInt("PatientID"));
                p.setLastName(rs.getString("PtLastName"));
                p.setPreviousLastName(rs.getString("PtPreviousLastName"));
                p.setFirstName(rs.getString("PtFirstName"));
                p.setHomeAddress1(rs.getString("HomeAddress1"));
                p.setHomeCity(rs.getString("HomeCity"));
                p.setHomeStateProvinceRegion(rs.getString("HomeStateProvinceRegion"));
                p.setHomeZipPostalCode(rs.getString("HomeZipPostalCode"));
                p.setHomePhone(rs.getString("HomePhone"));
                p.setDateOfBirth(rs.getString("DateOfBirth"));
                p.setGender(rs.getString("Gender"));
                return p;
            }
        }

        return null;
    }
}
