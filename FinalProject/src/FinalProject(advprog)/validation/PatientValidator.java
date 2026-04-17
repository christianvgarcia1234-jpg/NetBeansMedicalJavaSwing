package FinalProject.advprog.validation;

import FinalProject.advprog.model.Patient;
import java.util.ArrayList;
import java.util.List;

public class PatientValidator {

    public static List<String> validate(Patient p) {
        List<String> errors = new ArrayList<>();

        if (p.getLastName() == null || p.getLastName().isBlank()) {
            errors.add("Last Name is required.");
        }

        if (p.getFirstName() == null || p.getFirstName().isBlank()) {
            errors.add("First Name is required.");
        }

        if (p.getDateOfBirth() == null || p.getDateOfBirth().isBlank()) {
            errors.add("Date of Birth is required.");
        }

        if (p.getGender() == null || p.getGender().isBlank()) {
            errors.add("Gender is required.");
        }

        return errors;
    }
}
