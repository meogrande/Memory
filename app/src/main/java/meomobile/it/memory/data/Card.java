package meomobile.it.memory.data;

import meomobile.it.memory.R;

/**
 * Creato da fabio on 11/03/2016.
 */
public class Card {
    public final static int IMAGE_BACK = R.drawable.back;

    private int image;
    private int hash;
    private boolean isVisible;
    private boolean isMatched;

    public Card(int hash, int image) {
        isVisible = false;
        isMatched = false;
        this.image = image;
        this.hash = hash;
    }

    public int getHash() {
        return hash;
    }

    /**
     * Ritorna l'immagine dello stato corrente della carta
     * @return
     */
    public int getImage() {
        // Se è girato
        if (isVisible) {
            return image;
        } else {
            return IMAGE_BACK;
        }
    }

    public void setMatched(boolean matched) {
        isMatched = matched;
    }

    public boolean isMatched() {
        return isMatched;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    /**
     * Inverto lo stato della carta
     */
    public void turn() {
        isVisible = !isVisible;
    }
}
