package it.unibo.mvc;

import java.util.List;

/**
 * it must model a simple controller responsible of I/O access.
 */
public interface Controller {
    /**
     * A method for setting the next string to print. Null values are not acceptable, and an exception should be produced.
     * 
     * @param string string to save.
     */
    void setNextString(String string);

    /**
     * A method for getting the next string to print.
     * 
     * @return the next string.
     */
    String getNextString();

    /**
     * A method for getting the history of the printed strings (in form of a List of Strings).
     * 
     * @return a listo of strings.
     */
    List<String> getHistory();

    /**
     * A method that prints the current string. f the current string is unset, an IllegalStateException should be thrown.
     * 
     * @return the current string.
     */
    String getCurrentString();
}
