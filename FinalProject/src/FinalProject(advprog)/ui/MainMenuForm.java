package FinalProject.advprog.ui;

public class MainMenuForm extends javax.swing.JFrame {

    public MainMenuForm() {
        initComponents();
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        btnPatientDemo = new javax.swing.JButton();
        btnGeneralHistory = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CHF Interview System - Main Menu");

        btnPatientDemo.setText("Patient Demographics");
        btnPatientDemo.addActionListener(evt -> openPatientDemo());

        btnGeneralHistory.setText("General Medical History");
        btnGeneralHistory.addActionListener(evt -> openGeneralHistory());

        btnExit.setText("Exit");
        btnExit.addActionListener(evt -> System.exit(0));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
            .addGroup(layout.createSequentialGroup()
                .addGap(40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btnPatientDemo, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGeneralHistory, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40))
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addGap(40)
                .addComponent(btnPatientDemo)
                .addGap(20)
                .addComponent(btnGeneralHistory)
                .addGap(20)
                .addComponent(btnExit)
                .addGap(40)
        );

        pack();
    }

    private void openPatientDemo() {
        new PatientDemographicsForm().setVisible(true);
    }

    private void openGeneralHistory() {
        int patientId = 1; // MUST exist in patienttable
        new GeneralMedicalHistoryForm(patientId).setVisible(true);
    }

    private javax.swing.JButton btnPatientDemo;
    private javax.swing.JButton btnGeneralHistory;
    private javax.swing.JButton btnExit;
}
