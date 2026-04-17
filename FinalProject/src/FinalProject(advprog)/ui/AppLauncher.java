package FinalProject.advprog.ui;

public class AppLauncher {

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            new MainMenuForm().setVisible(true);
        });
    }
}
