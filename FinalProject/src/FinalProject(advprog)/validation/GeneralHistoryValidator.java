package FinalProject.advprog.validation;

import FinalProject.advprog.model.GeneralMedicalHistory;
import java.util.ArrayList;
import java.util.List;

public class GeneralHistoryValidator {

    public static List<String> validate(GeneralMedicalHistory h) {
        List<String> errors = new ArrayList<>();

        if (h.getBloodType() == null || h.getBloodType().isBlank()) {
            errors.add("Blood Type is required.");
        }

        if (h.getAllergies() == null || h.getAllergies().isBlank()) {
            errors.add("Allergies field is required.");
        }

        return errors;
    }
}
