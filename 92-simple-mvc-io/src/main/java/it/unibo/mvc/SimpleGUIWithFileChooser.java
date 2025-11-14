package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * A very simple program using a graphical interface.
 */
public final class SimpleGUIWithFileChooser {

    private static final int PROPORTION = 5;
    private final JFrame frame = new JFrame();
    private final Controller controller = new Controller();

    SimpleGUIWithFileChooser() {
        // Creazione dei componenti
        final JPanel panel = new JPanel();
        final JPanel topPanel = new JPanel();
        final JTextField textField = new JTextField();
        final JButton browseButton = new JButton("Browse...");
        final JButton saveButton = new JButton("Save");

        // Configurazione layout
        panel.setLayout(new BorderLayout());
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));

        // Configurazione componenti
        textField.setEditable(false);
        textField.setText(controller.getPath());
        browseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final JFileChooser fileChooser = new JFileChooser();
                final int result = fileChooser.showSaveDialog(null);
                switch (result) {
                    case JFileChooser.APPROVE_OPTION -> {
                        controller.setFile(fileChooser.getSelectedFile());
                        textField.setText(controller.getPath());
                    }
                    case JFileChooser.CANCEL_OPTION -> System.out.println("operazione annulata");
                    default -> JOptionPane.showMessageDialog(
                                frame,
                                "An error has occurred.",
                                "Error",
                                javax.swing.JOptionPane.ERROR_MESSAGE
                            );
                }
            }
        });

        // Composizione della GUI
        topPanel.add(textField);
        topPanel.add(browseButton);
        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(saveButton, BorderLayout.SOUTH);

        // Configurazione del frame
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void display() {
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / PROPORTION, sh / PROPORTION);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(final String... args) {
        new SimpleGUIWithFileChooser().display();
    }
}
