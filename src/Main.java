import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        UIManager.put("swing.boldMetal", Boolean.FALSE);
        javax.swing.SwingUtilities.invokeLater(() -> {
            Window window = new Window("Message Digest 5 (MD-5) and Secure Hash Algorithm 1 (SHA-1) encryption algorithms.");
            window.pack();
            window.setVisible(true);
            window.setResizable(false);
            window.setLocationRelativeTo(null);
        });
    }
}
