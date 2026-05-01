DELIMITER $$

CREATE PROCEDURE sp_GetPatientById(IN p_PatientID INT)
BEGIN
    SELECT *
    FROM patienttable
    WHERE PatientID = p_PatientID;
END$$

CREATE PROCEDURE sp_SearchPatientsByLastName(IN p_LastName VARCHAR(128))
BEGIN
    SELECT *
    FROM patienttable
    WHERE PtLastName LIKE CONCAT(p_LastName, '%')
    ORDER BY PtLastName, PtFirstName;
END$$

CREATE PROCEDURE sp_InsertPatient(
    IN p_PtLastName VARCHAR(128),
    IN p_PtFirstName VARCHAR(128),
    IN p_HomeAddress1 VARCHAR(128),
    IN p_HomeCity VARCHAR(128),
    IN p_HomeState VARCHAR(50),
    IN p_HomeZip VARCHAR(15),
    IN p_EmailAddress VARCHAR(128),
    IN p_DOB DATETIME,
    IN p_Gender VARCHAR(50)
)
BEGIN
    INSERT INTO patienttable
    (PtLastName, PtFirstName, HomeAddress1, HomeCity, `HomeState/Province/Region`,
     HomeZip, EmailAddress, DOB, Gender)
    VALUES
    (p_PtLastName, p_PtFirstName, p_HomeAddress1, p_HomeCity, p_HomeState,
     p_HomeZip, p_EmailAddress, p_DOB, p_Gender);

    SELECT LAST_INSERT_ID() AS NewPatientID;
END$$

CREATE PROCEDURE sp_UpdatePatient(
    IN p_PatientID INT,
    IN p_PtLastName VARCHAR(128),
    IN p_PtFirstName VARCHAR(128),
    IN p_HomeAddress1 VARCHAR(128),
    IN p_HomeCity VARCHAR(128),
    IN p_HomeState VARCHAR(50),
    IN p_HomeZip VARCHAR(15),
    IN p_EmailAddress VARCHAR(128),
    IN p_DOB DATETIME,
    IN p_Gender VARCHAR(50)
)
BEGIN
    UPDATE patienttable
    SET PtLastName = p_PtLastName,
        PtFirstName = p_PtFirstName,
        HomeAddress1 = p_HomeAddress1,
        HomeCity = p_HomeCity,
        `HomeState/Province/Region` = p_HomeState,
        HomeZip = p_HomeZip,
        EmailAddress = p_EmailAddress,
        DOB = p_DOB,
        Gender = p_Gender
    WHERE PatientID = p_PatientID;
END$$

DELIMITER ;