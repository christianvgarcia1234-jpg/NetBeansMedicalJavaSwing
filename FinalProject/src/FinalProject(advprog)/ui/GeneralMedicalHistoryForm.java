package FinalProject.advprog.ui;

import FinalProject.advprog.dbutils.GeneralHistoryDBUtil;
import FinalProject.advprog.model.GeneralMedicalHistory;

import java.awt.Color;
import javax.swing.JOptionPane;

public class GeneralMedicalHistoryForm extends javax.swing.JFrame {

    private enum FormMode { VIEW, EDIT }
    private FormMode mode = FormMode.VIEW;

    private GeneralMedicalHistory currentHistory;
    private int currentPatientID = 0;

    // Required for NetBeans GUI Builder
    public GeneralMedicalHistoryForm() {
        initComponents();
        setLocationRelativeTo(null);
        setMode(FormMode.VIEW);
    }

    // Required for your application logic
    public GeneralMedicalHistoryForm(int patientID) {
        this.currentPatientID = patientID;
        initComponents();
        setLocationRelativeTo(null);
        setMode(FormMode.VIEW);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        lblBloodType = new javax.swing.JLabel();
        txtBloodType = new javax.swing.JTextField();
        chkSmoker = new javax.swing.JCheckBox();
        chkAlcohol = new javax.swing.JCheckBox();
        lblAllergies = new javax.swing.JLabel();
        txtAllergies = new javax.swing.JTextField();
        lblChronic = new javax.swing.JLabel();
        txtChronic = new javax.swing.JTextField();

        btnNew = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnInterview = new javax.swing.JButton();  

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("General Medical History");

        lblBloodType.setText("Blood Type:");
        chkSmoker.setText("Smoker");
        chkAlcohol.setText("Alcohol Use");
        lblAllergies.setText("Allergies:");
        lblChronic.setText("Chronic Conditions:");

        btnNew.setText("New");
        btnNew.addActionListener(evt -> newRecord());

        btnEdit.setText("Edit");
        btnEdit.addActionListener(evt -> editRecord());

        btnSave.setText("Save");
        btnSave.addActionListener(evt -> saveRecord());

        btnDelete.setText("Delete");
        btnDelete.addActionListener(evt -> deleteRecord());

        btnInterview.setText("Start Interview");     
        btnInterview.addActionListener(evt -> startInterview()); 

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblBloodType)
                    .addComponent(chkSmoker)
                    .addComponent(chkAlcohol)
                    .addComponent(lblAllergies)
                    .addComponent(lblChronic))
                .addGap(20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtBloodType, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAllergies, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtChronic, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20))
            .addGroup(layout.createSequentialGroup()
                .addGap(20)
                .addComponent(btnNew)
                .addGap(10)
                .addComponent(btnEdit)
                .addGap(10)
                .addComponent(btnSave)
                .addGap(10)
                .addComponent(btnDelete)
                .addGap(10)
                .addComponent(btnInterview))  
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addGap(20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBloodType)
                    .addComponent(txtBloodType))
                .addGap(15)
                .addComponent(chkSmoker)
                .addGap(10)
                .addComponent(chkAlcohol)
                .addGap(15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAllergies)
                    .addComponent(txtAllergies))
                .addGap(15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblChronic)
                    .addComponent(txtChronic))
                .addGap(25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNew)
                    .addComponent(btnEdit)
                    .addComponent(btnSave)
                    .addComponent(btnDelete)
                    .addComponent(btnInterview))   
                .addGap(20)
        );

        pack();
    }

    private void setMode(FormMode newMode) {
        this.mode = newMode;

        boolean editable = (newMode == FormMode.EDIT);
        Color bg = editable ? Color.WHITE : new Color(230,230,230);

        txtBloodType.setEditable(editable);
        txtAllergies.setEditable(editable);
        txtChronic.setEditable(editable);

        txtBloodType.setBackground(bg);
        txtAllergies.setBackground(bg);
        txtChronic.setBackground(bg);

        chkSmoker.setEnabled(editable);
        chkAlcohol.setEnabled(editable);
    }

    private void newRecord() {
        currentHistory = new GeneralMedicalHistory();
        currentHistory.setPatientID(currentPatientID);

        txtBloodType.setText("");
        txtAllergies.setText("");
        txtChronic.setText("");
        chkSmoker.setSelected(false);
        chkAlcohol.setSelected(false);

        setMode(FormMode.EDIT);
    }

    private void editRecord() {
        if (currentHistory == null) {
            JOptionPane.showMessageDialog(this, "No record loaded.");
            return;
        }
        setMode(FormMode.EDIT);
    }

    private void saveRecord() {

        if (currentHistory == null) {
            currentHistory = new GeneralMedicalHistory();
        }

        currentHistory.setPatientID(currentPatientID);
        currentHistory.setBloodType(txtBloodType.getText());
        currentHistory.setSmoker(chkSmoker.isSelected());
        currentHistory.setAlcoholUse(chkAlcohol.isSelected());
        currentHistory.setAllergies(txtAllergies.getText());
        currentHistory.setChronicConditions(txtChronic.getText());

        try {
            if (currentHistory.getHistoryID() == 0) {
                currentHistory = GeneralHistoryDBUtil.insertHistory(currentHistory);
            } else {
                GeneralHistoryDBUtil.updateHistory(currentHistory);
            }

            JOptionPane.showMessageDialog(this, "Record saved.");
            setMode(FormMode.VIEW);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void deleteRecord() {
        if (currentHistory == null || currentHistory.getHistoryID() == 0) {
            JOptionPane.showMessageDialog(this, "No record to delete.");
            return;
        }

        try {
            GeneralHistoryDBUtil.deleteHistory(currentHistory.getHistoryID());
            JOptionPane.showMessageDialog(this, "Record deleted.");
            newRecord();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    // Start Interview
    private void startInterview() {

        if (currentPatientID <= 0) {
            JOptionPane.showMessageDialog(this, "No valid patient selected.");
            return;
        }

        try {
            FinalProject.advprog.model.Patient p = new FinalProject.advprog.model.Patient();
            p.setPatientID(currentPatientID);

            FinalProject.advprog.interview.GeneralHistoryInterview interview =
                    new FinalProject.advprog.interview.GeneralHistoryInterview(p);

            interview.start(this);

            currentHistory = GeneralHistoryDBUtil.getHistoryByPatientId(currentPatientID);

            if (currentHistory != null) {
                txtBloodType.setText(currentHistory.getBloodType());
                chkSmoker.setSelected(currentHistory.isSmoker());
                chkAlcohol.setSelected(currentHistory.isAlcoholUse());
                txtAllergies.setText(currentHistory.getAllergies());
                txtChronic.setText(currentHistory.getChronicConditions());
            }

            JOptionPane.showMessageDialog(this, "Interview completed and saved.");

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error running interview: " + e.getMessage());
        }
    }

    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnInterview;   

    private javax.swing.JLabel lblBloodType;
    private javax.swing.JLabel lblAllergies;
    private javax.swing.JLabel lblChronic;

    private javax.swing.JTextField txtBloodType;
    private javax.swing.JTextField txtAllergies;
    private javax.swing.JTextField txtChronic;

    private javax.swing.JCheckBox chkSmoker;
    private javax.swing.JCheckBox chkAlcohol;
}
