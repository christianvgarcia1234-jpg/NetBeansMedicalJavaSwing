package FinalProject.advprog.ui;

import FinalProject.advprog.dbutils.PatientDBUtil;
import FinalProject.advprog.model.Patient;
import FinalProject.advprog.validation.PatientValidator;

import java.awt.Color;
import java.util.List;
import javax.swing.JOptionPane;

public class PatientDemographicsForm extends javax.swing.JFrame {

    private enum FormMode { VIEW, EDIT }
    private FormMode mode = FormMode.VIEW;

    private Patient currentPatient;

    public PatientDemographicsForm() {
        initComponents();
        setLocationRelativeTo(null);
        setMode(FormMode.VIEW);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        lblLastName = new javax.swing.JLabel();
        txtLastName = new javax.swing.JTextField();
        lblFirstName = new javax.swing.JLabel();
        txtFirstName = new javax.swing.JTextField();
        lblDOB = new javax.swing.JLabel();
        txtDOB = new javax.swing.JTextField();
        lblGender = new javax.swing.JLabel();
        txtGender = new javax.swing.JTextField();

        btnEdit = new javax.swing.JButton();
        btnNew = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Patient Demographics");

        lblLastName.setText("Last Name *");
        lblFirstName.setText("First Name *");
        lblDOB.setText("Date of Birth (YYYY-MM-DD) *");
        lblGender.setText("Gender *");

        btnEdit.setText("Edit");
        btnEdit.addActionListener(evt -> editRecord());

        btnNew.setText("New");
        btnNew.addActionListener(evt -> newRecord());

        btnSave.setText("Save");
        btnSave.addActionListener(evt -> saveRecord());

        btnDelete.setText("Delete");
        btnDelete.addActionListener(evt -> deleteRecord());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblLastName)
                    .addComponent(lblFirstName)
                    .addComponent(lblDOB)
                    .addComponent(lblGender))
                .addGap(20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDOB, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGender, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20))
            .addGroup(layout.createSequentialGroup()
                .addGap(20)
                .addComponent(btnEdit)
                .addGap(10)
                .addComponent(btnNew)
                .addGap(10)
                .addComponent(btnSave)
                .addGap(10)
                .addComponent(btnDelete)
                .addGap(20))
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addGap(20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLastName)
                    .addComponent(txtLastName))
                .addGap(15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFirstName)
                    .addComponent(txtFirstName))
                .addGap(15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDOB)
                    .addComponent(txtDOB))
                .addGap(15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblGender)
                    .addComponent(txtGender))
                .addGap(25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEdit)
                    .addComponent(btnNew)
                    .addComponent(btnSave)
                    .addComponent(btnDelete))
                .addGap(20)
        );

        pack();
    }

    // ---------------------------------------------------------
    // MODE HANDLING
    // ---------------------------------------------------------
    private void setMode(FormMode newMode) {
        this.mode = newMode;

        boolean editable = (newMode == FormMode.EDIT);
        Color bg = editable ? Color.WHITE : new Color(230,230,230);

        txtLastName.setEditable(editable);
        txtFirstName.setEditable(editable);
        txtDOB.setEditable(editable);
        txtGender.setEditable(editable);

        txtLastName.setBackground(bg);
        txtFirstName.setBackground(bg);
        txtDOB.setBackground(bg);
        txtGender.setBackground(bg);
    }

    // ---------------------------------------------------------
    // BUTTON HANDLERS
    // ---------------------------------------------------------
    private void editRecord() {
        if (currentPatient == null) {
            JOptionPane.showMessageDialog(this, "No patient loaded.");
            return;
        }
        setMode(FormMode.EDIT);
    }

    private void newRecord() {
        currentPatient = new Patient();
        txtLastName.setText("");
        txtFirstName.setText("");
        txtDOB.setText("");
        txtGender.setText("");
        setMode(FormMode.EDIT);
    }

    private void saveRecord() {

        if (currentPatient == null) {
            currentPatient = new Patient();
        }

        currentPatient.setLastName(txtLastName.getText());
        currentPatient.setPreviousLastName(""); // optional field
        currentPatient.setFirstName(txtFirstName.getText());
        currentPatient.setHomeAddress1("");
        currentPatient.setHomeCity("");
        currentPatient.setHomeStateProvinceRegion("");
        currentPatient.setHomeZipPostalCode("");
        currentPatient.setHomePhone("");
        currentPatient.setDateOfBirth(txtDOB.getText());
        currentPatient.setGender(txtGender.getText());

        List<String> errors = PatientValidator.validate(currentPatient);

        if (!errors.isEmpty()) {
            JOptionPane.showMessageDialog(this, String.join("\n", errors));
            return;
        }

        try {
            if (currentPatient.getPatientID() == 0) {
                currentPatient = PatientDBUtil.insertPatient(currentPatient);  // LAST_INSERT_ID FIX
            } else {
                PatientDBUtil.updatePatient(currentPatient);
            }

            JOptionPane.showMessageDialog(this, "Record saved.");
            setMode(FormMode.VIEW);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void deleteRecord() {
        if (currentPatient == null || currentPatient.getPatientID() == 0) {
            JOptionPane.showMessageDialog(this, "No record to delete.");
            return;
        }

        try {
            PatientDBUtil.deletePatient(currentPatient.getPatientID());
            JOptionPane.showMessageDialog(this, "Record deleted.");
            newRecord();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    // ---------------------------------------------------------
    // VARIABLES
    // ---------------------------------------------------------
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnSave;
    private javax.swing.JLabel lblDOB;
    private javax.swing.JLabel lblFirstName;
    private javax.swing.JLabel lblGender;
    private javax.swing.JLabel lblLastName;
    private javax.swing.JTextField txtDOB;
    private javax.swing.JTextField txtFirstName;
    private javax.swing.JTextField txtGender;
    private javax.swing.JTextField txtLastName;
}
