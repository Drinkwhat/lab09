package it.unibo.mvc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Controller semplice che mantiene una storia di stringhe inserite e permette
 * di navigare tra di esse.
 *
 * <p>
 * Le stringhe vengono aggiunte in coda alla lista storica; il controller tiene
 * traccia dell'indice corrente nella storia. Fornisce operazioni per aggiungere
 * una nuova stringa, ottenere la stringa successiva, ottenere la stringa
 * corrente e recuperare la storia fino all'elemento corrente.
 * </p>
 */
public final class SimpleController implements Controller {

    private final List<String> list;
    private int currentIndex;

    /**
     * Costruisce un nuovo SimpleController vuoto.
     */
    SimpleController() {
        this.list = new ArrayList<>();
        this.currentIndex = -1;
    }

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    public String getNextString() {
        if (currentIndex + 1 >= list.size()) {
            throw new IllegalStateException("no next string");
        }
        currentIndex++;
        return list.get(currentIndex);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getHistory() {
        if (currentIndex < 0) {
            return Collections.emptyList();
        }
        return new ArrayList<>(list.subList(0, currentIndex + 1));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCurrentString() {
        if (currentIndex < 0 || currentIndex >= list.size()) {
            throw new IllegalStateException("no current string");
        }
        return list.get(currentIndex);
    }

    /**
     * Stampa la stringa corrente su System.out. Utile per debugging / esercizi.
     *
     * @throws IllegalStateException se non esiste una stringa corrente
     */
    public void printCurrentString() {
        System.out.println(getCurrentString()); // : allowed as this is just an exercise
    }
}
