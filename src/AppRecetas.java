import javax.swing.SwingUtilities;

public class AppRecetas {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new InterfazRecetas().setVisible(true);
            }
        });
    }
}
