package it.unibo.mvc;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * Application controller. Performs the I/O.
 */
class Controller {

    private File file;

    Controller() {
        final String home = System.getProperty("user.home");
        final String sep = System.getProperty("file.separator");
        this.file = new File(home + sep + "output.txt");
    }

    public void setFile(final File file) {
        this.file = file;
    }

    public String getPath() {
        return file.getAbsolutePath();
    }

    public File getFile() {
        return file;
    }

    public void write(final String text) {
        try {
            Files.writeString(file.toPath(), text);
        } catch (final IOException e) {
            e.printStackTrace(); // NOPMD: allowed as this is just an exercise
        }
    } 
}
