DELIMITER $$

CREATE PROCEDURE sp_GetGeneralHistoryByPatient(IN p_PatientID INT)
BEGIN
    SELECT *
    FROM generalmedicalhistorytable
    WHERE PatientID = p_PatientID
      AND deleted = 0
    LIMIT 1;
END$$

CREATE PROCEDURE sp_InsertGeneralHistory(
    IN p_PatientID INT,
    IN p_Tobacco VARCHAR(50),
    IN p_TobaccoQuantity VARCHAR(75),
    IN p_TobaccoDuration VARCHAR(75),
    IN p_Alcohol VARCHAR(50),
    IN p_AlcoholQuantity VARCHAR(75),
    IN p_AlcoholDuration VARCHAR(75),
    IN p_Drug VARCHAR(25),
    IN p_DrugType VARCHAR(254),
    IN p_DrugDuration VARCHAR(75),
    IN p_BloodType VARCHAR(10),
    IN p_Rh VARCHAR(10)
)
BEGIN
    INSERT INTO generalmedicalhistorytable
    (PatientID, Tobacco, TobaccoQuantity, Tobaccoduraton,
     Alcohol, AlcoholQuantity, Alcoholduration,
     Drug, DrugType, Drugduration,
     BloodType, Rh, deleted)
    VALUES
    (p_PatientID, p_Tobacco, p_TobaccoQuantity, p_TobaccoDuration,
     p_Alcohol, p_AlcoholQuantity, p_AlcoholDuration,
     p_Drug, p_DrugType, p_DrugDuration,
     p_BloodType, p_Rh, 0);
END$$

CREATE PROCEDURE sp_UpdateGeneralHistory(
    IN p_GeneralMedicalHistoryID INT,
    IN p_Tobacco VARCHAR(50),
    IN p_TobaccoQuantity VARCHAR(75),
    IN p_TobaccoDuration VARCHAR(75),
    IN p_Alcohol VARCHAR(50),
    IN p_AlcoholQuantity VARCHAR(75),
    IN p_AlcoholDuration VARCHAR(75),
    IN p_Drug VARCHAR(25),
    IN p_DrugType VARCHAR(254),
    IN p_DrugDuration VARCHAR(75),
    IN p_BloodType VARCHAR(10),
    IN p_Rh VARCHAR(10)
)
BEGIN
    UPDATE generalmedicalhistorytable
    SET Tobacco = p_Tobacco,
        TobaccoQuantity = p_TobaccoQuantity,
        Tobaccoduraton = p_TobaccoDuration,
        Alcohol = p_Alcohol,
        AlcoholQuantity = p_AlcoholQuantity,
        Alcoholduration = p_AlcoholDuration,
        Drug = p_Drug,
        DrugType = p_DrugType,
        Drugduration = p_DrugDuration,
        BloodType = p_BloodType,
        Rh = p_Rh
    WHERE GeneralMedicalHistoryID = p_GeneralMedicalHistoryID;
END$$

DELIMITER ;

