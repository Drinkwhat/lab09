package it.unibo.mvc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 
 *
 */
public final class SimpleController implements Controller {

    private final List<String> list;
    private int currentIndex;

    SimpleController() {
        this.list = new ArrayList<>();
        this.currentIndex = -1;
    }

    @Override
    public void setNextString(final String string) {
        if (string == null) {
            throw new IllegalArgumentException("string cannot be null");
        }

        if (string.isBlank()) {
            throw new IllegalArgumentException("string cannot be blank");
        }

        if (list.add(string)) {
            currentIndex++;
        }
    }

    @Override
    public String getNextString() {
        if (currentIndex + 1 >= list.size()) {
            throw new IllegalStateException("no next string");
        }
        currentIndex++;
        return list.get(currentIndex);
    }

    @Override
    public List<String> getHistory() {
        if (currentIndex < 0) {
            return Collections.emptyList();
        }
        return new ArrayList<>(list.subList(0, currentIndex + 1));
    }

    @Override
    public String getCurrentString() {
        if (currentIndex < 0 || currentIndex >= list.size()) {
            throw new IllegalStateException("no current string");
        }
        return list.get(currentIndex);
    }

    /**
     * 
     */
    public void printCurrentString() {
        System.out.println(getCurrentString()); // NOPMD
    }
}
