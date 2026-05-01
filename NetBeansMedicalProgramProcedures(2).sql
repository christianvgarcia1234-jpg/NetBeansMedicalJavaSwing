DELIMITER $$

CREATE PROCEDURE sp_GetFamilyHistoryByPatient(IN p_PatientID INT)
BEGIN
    SELECT *
    FROM familyhistorytable
    WHERE PatientID = p_PatientID
      AND deleted = 0
    ORDER BY FamilyID DESC;
END$$

CREATE PROCEDURE sp_InsertFamilyHistory(
    IN p_PatientID INT,
    IN p_Name VARCHAR(50),
    IN p_Relation VARCHAR(50),
    IN p_Alive TINYINT,
    IN p_LivesWithPatient TINYINT,
    IN p_MajorDisorder VARCHAR(254),
    IN p_SpecificTypeDisorder VARCHAR(254),
    IN p_DisorderHRF TINYINT
)
BEGIN
    INSERT INTO familyhistorytable
    (PatientID, Name, Relation, Alive, `Lives with patient`,
     MajorDisorder, SpecificTypeDisorder, DisorderHRF, deleted)
    VALUES
    (p_PatientID, p_Name, p_Relation, p_Alive, p_LivesWithPatient,
     p_MajorDisorder, p_SpecificTypeDisorder, p_DisorderHRF, 0);
END$$

CREATE PROCEDURE sp_UpdateFamilyHistory(
    IN p_FamilyID INT,
    IN p_Name VARCHAR(50),
    IN p_Relation VARCHAR(50),
    IN p_Alive TINYINT,
    IN p_LivesWithPatient TINYINT,
    IN p_MajorDisorder VARCHAR(254),
    IN p_SpecificTypeDisorder VARCHAR(254),
    IN p_DisorderHRF TINYINT
)
BEGIN
    UPDATE familyhistorytable
    SET Name = p_Name,
        Relation = p_Relation,
        Alive = p_Alive,
        `Lives with patient` = p_LivesWithPatient,
        MajorDisorder = p_MajorDisorder,
        SpecificTypeDisorder = p_SpecificTypeDisorder,
        DisorderHRF = p_DisorderHRF
    WHERE FamilyID = p_FamilyID;
END$$

DELIMITER ;