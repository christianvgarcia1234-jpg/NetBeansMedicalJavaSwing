package FinalProject.advprog.dbutils;

import FinalProject.advprog.model.GeneralMedicalHistory;
import java.sql.*;

public class GeneralHistoryDBUtil {

    public static GeneralMedicalHistory getHistoryByPatientId(int patientID) throws SQLException {
        String sql = "SELECT * FROM GeneralMedicalHistory WHERE PatientID = ? AND IsDeleted = 0";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, patientID);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                GeneralMedicalHistory h = new GeneralMedicalHistory();
                h.setHistoryID(rs.getInt("HistoryID"));
                h.setPatientID(rs.getInt("PatientID"));
                h.setBloodType(rs.getString("BloodType"));
                h.setSmoker(rs.getBoolean("Smoker"));
                h.setAlcoholUse(rs.getBoolean("AlcoholUse"));
                h.setAllergies(rs.getString("Allergies"));
                h.setChronicConditions(rs.getString("ChronicConditions"));
                return h;
            }
        }
        return null;
    }

    public static GeneralMedicalHistory insertHistory(GeneralMedicalHistory h) throws SQLException {

        String sql = "INSERT INTO GeneralMedicalHistory "
                + "(PatientID, BloodType, Smoker, AlcoholUse, Allergies, ChronicConditions, LastUpdated) "
                + "VALUES (?, ?, ?, ?, ?, ?, NOW())";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, h.getPatientID());
            ps.setString(2, h.getBloodType());
            ps.setBoolean(3, h.isSmoker());
            ps.setBoolean(4, h.isAlcoholUse());
            ps.setString(5, h.getAllergies());
            ps.setString(6, h.getChronicConditions());

            ps.executeUpdate();

            ResultSet keys = ps.getGeneratedKeys();
            if (keys.next()) {
                int newId = keys.getInt(1);
                return getHistoryById(newId);
            }
        }

        return null;
    }

    public static void updateHistory(GeneralMedicalHistory h) throws SQLException {

        String sql = "UPDATE GeneralMedicalHistory SET "
                + "BloodType=?, Smoker=?, AlcoholUse=?, Allergies=?, ChronicConditions=?, LastUpdated=NOW() "
                + "WHERE HistoryID=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, h.getBloodType());
            ps.setBoolean(2, h.isSmoker());
            ps.setBoolean(3, h.isAlcoholUse());
            ps.setString(4, h.getAllergies());
            ps.setString(5, h.getChronicConditions());
            ps.setInt(6, h.getHistoryID());

            ps.executeUpdate();
        }
    }

    public static void deleteHistory(int historyID) throws SQLException {

        String sql = "UPDATE GeneralMedicalHistory SET IsDeleted = 1 WHERE HistoryID = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, historyID);
            ps.executeUpdate();
        }
    }

    public static GeneralMedicalHistory getHistoryById(int id) throws SQLException {

        String sql = "SELECT * FROM GeneralMedicalHistory WHERE HistoryID = ? AND IsDeleted = 0";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                GeneralMedicalHistory h = new GeneralMedicalHistory();
                h.setHistoryID(rs.getInt("HistoryID"));
                h.setPatientID(rs.getInt("PatientID"));
                h.setBloodType(rs.getString("BloodType"));
                h.setSmoker(rs.getBoolean("Smoker"));
                h.setAlcoholUse(rs.getBoolean("AlcoholUse"));
                h.setAllergies(rs.getString("Allergies"));
                h.setChronicConditions(rs.getString("ChronicConditions"));
                return h;
            }
        }

        return null;
    }
}
