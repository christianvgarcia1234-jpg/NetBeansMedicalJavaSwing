package FinalProject.advprog.interview;

import javax.swing.JOptionPane;
import FinalProject.advprog.model.GeneralMedicalHistory;
import FinalProject.advprog.model.Patient;
import FinalProject.advprog.logging.InterviewLogger;
import FinalProject.advprog.dbutils.GeneralHistoryDBUtil;

public class GeneralHistoryInterview {

    private InterviewNode root;
    private final Patient patient;
    private final GeneralMedicalHistory history;
    private final InterviewLogger logger;

    public GeneralHistoryInterview(Patient patient) {
        this.patient = patient;
        this.history = new GeneralMedicalHistory();
        this.history.setPatientID(patient.getPatientID());   
        this.logger = new InterviewLogger(patient);
        buildTree();
    }

    private void buildTree() {

        // Question 1 — Smoking
        InterviewNode q1 = new InterviewNode("Do you smoke?");
        q1.setHandler(ans -> history.setSmoker(ans));

        // Question 2 — Alcohol
        InterviewNode q2 = new InterviewNode("Do you drink alcohol?");
        q2.setHandler(ans -> history.setAlcoholUse(ans));

        // Question 3 — Allergies
        InterviewNode q3 = new InterviewNode("Do you have any allergies?");
        q3.setHandler(ans -> {
            if (ans) {
                String allergies = JOptionPane.showInputDialog(null, "Enter allergies:");
                history.setAllergies(allergies == null ? "" : allergies);
            } else {
                history.setAllergies("None");
            }
        });

        // Question 4 — Chronic Conditions
        InterviewNode q4 = new InterviewNode("Do you have any chronic conditions?");
        q4.setHandler(ans -> {
            if (ans) {
                String conditions = JOptionPane.showInputDialog(null, "Enter chronic conditions:");
                history.setChronicConditions(conditions == null ? "" : conditions);
            } else {
                history.setChronicConditions("None");
            }
        });

        // Linear flow
        q1.setYesNode(q2);
        q1.setNoNode(q2);

        q2.setYesNode(q3);
        q2.setNoNode(q3);

        q3.setYesNode(q4);
        q3.setNoNode(q4);

        q4.setYesNode(null);
        q4.setNoNode(null);

        root = q1;
    }

    public void start(javax.swing.JFrame parent) {
        InterviewNode current = root;

        while (current != null) {

            int result = JOptionPane.showConfirmDialog(
                    parent,
                    current.getQuestion(),
                    "General Medical History Interview",
                    JOptionPane.YES_NO_OPTION
            );

            boolean answer = (result == JOptionPane.YES_OPTION);

            logger.log(current.getQuestion(), answer);

            if (current.getHandler() != null) {
                current.getHandler().accept(answer);
            }

            current = answer ? current.getYesNode() : current.getNoNode();
        }

        // Save to DB
        try {
            GeneralHistoryDBUtil.insertHistory(history);   
        } catch (Exception e) {
            JOptionPane.showMessageDialog(parent, "Error saving interview: " + e.getMessage());
            e.printStackTrace();
        }

        try {
            logger.close();
        } catch (Exception ignored) {}
    }
}
