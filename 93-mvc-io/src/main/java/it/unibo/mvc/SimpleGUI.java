package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * A very simple program using a graphical interface.
 *
 */
public final class SimpleGUI {

    private static final int PROPORTION = 5;
    private final JFrame frame = new JFrame();
    private final SimpleController controller = new SimpleController();

    /**
     * Constructs a GUI with a text area for writing text to a file.
     */
    public SimpleGUI() {
        // Creazione componenti
        final JPanel panel = new JPanel();
        final JPanel bottomPanel = new JPanel();
        final JTextField textField = new JTextField();
        final JTextArea textArea = new JTextArea();
        final JButton printButton = new JButton("Print");
        final JButton showHistoryButton = new JButton("Show history");

        // Layout
        panel.setLayout(new BorderLayout());
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));

        // Composizione GUI
        panel.add(textField, BorderLayout.NORTH);
        panel.add(textArea, BorderLayout.CENTER);
        panel.add(bottomPanel, BorderLayout.SOUTH);
        bottomPanel.add(printButton);
        bottomPanel.add(showHistoryButton);

        // Frame
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Handlers
        printButton.addActionListener(e -> {
            controller.setNextString(textField.getText());
            controller.printCurrentString();
        });
        showHistoryButton.addActionListener(e -> {
            textArea.setText(controller.getHistory().toString());
        });

    }

    private void display() {
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / PROPORTION, sh / PROPORTION);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     *  @param args gli argomenti passati non servono a nulla
     */
    public static void main(final String... args) {
        new SimpleGUI().display();
    }
}
