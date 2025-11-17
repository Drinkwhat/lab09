package it.unibo.mvc;

import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * This class is a simple application that writes a random number on a file.
 *
 * <p>
 * This application does not exploit the model-view-controller pattern, and as
 * such is just to be used to learn the basics, not as a template for your
 * applications.
 */
public class BadIOGUI {

    private static final String TITLE = "A very simple GUI application";
    private final JFrame frame = new JFrame(TITLE);

    /**
     * Creates a new BadIOGUI.
     */
    public BadIOGUI() {
        // Creazione componenti
        final String path = System.getProperty("user.home")
                + File.separator
                + BadIOGUI.class.getSimpleName() + ".txt";
        final Path path2 = Paths.get(path);
        final Random randomGenerator = new Random();
        final JPanel canvas = new JPanel();
        final JPanel horizontalPanel = new JPanel();
        final JButton write = new JButton("Write on file");
        final JButton read = new JButton("Read");

        // Layout
        canvas.setLayout(new BorderLayout());
        horizontalPanel.setLayout(new BoxLayout(horizontalPanel, BoxLayout.X_AXIS));

        // Composizione GUI
        horizontalPanel.add(write);
        horizontalPanel.add(read);
        canvas.add(horizontalPanel, BorderLayout.CENTER);

        // Frame
        frame.setContentPane(canvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Handlers
        write.addActionListener(e -> {
            /*
             * This would be VERY BAD in a real application.
             *
             * This makes the Event Dispatch Thread (EDT) work on an I/O
             * operation. I/O operations may take a long time, during which
             * your UI becomes completely unresponsive.
             */
            try (PrintStream ps = new PrintStream(path, StandardCharsets.UTF_8)) {
                ps.print(randomGenerator.nextInt());
            } catch (final IOException err) {
                JOptionPane.showMessageDialog(frame, err, "Error", JOptionPane.ERROR_MESSAGE);
                err.printStackTrace(); // NOPMD: allowed as this is just an exercise
            }
        });
        read.addActionListener(e -> {
            try {
                final List<String> content = Files.readAllLines(path2);
                System.err.println(content.toString()); // NOPMD
            } catch (final IOException err) {
                System.err.println(err); // NOPMD
            }
        });
    }

    private void display() {
        /*
         * Make the frame one fifth the resolution of the screen. This very method is
         * enough for a single screen setup. In case of multiple monitors, the
         * primary is selected. In order to deal coherently with multimonitor
         * setups, other facilities exist (see the Java documentation about this
         * issue). It is MUCH better than manually specify the size of a window
         * in pixel: it takes into account the current resolution.
         */
        // final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        // final int sw = (int) screen.getWidth();
        // final int sh = (int) screen.getHeight();
        // frame.setSize(sw / PROPORTION, sh / PROPORTION);
        frame.pack();
        /*
         * Instead of appearing at (0,0), upper left corner of the screen, this
         * flag makes the OS window manager take care of the default positioning
         * on screen. Results may vary, but it is generally the best choice.
         */
        frame.setLocationByPlatform(true);
        /*
         * OK, ready to push the frame onscreen
         */
        frame.setVisible(true);
    }

    /**
     * Launches the application.
     *
     * @param args ignored
     */
    public static void main(final String... args) {
        new BadIOGUI().display();
    }
}
