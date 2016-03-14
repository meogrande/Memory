package meomobile.it.memory.game;

import android.view.View;
import android.widget.ImageView;

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
    private ImageView iv;

    public Card(ImageView iv, int image) {
        isVisible = false;
        isMatched = false;
        this.image = image;
        this.iv = iv;
        this.hash = iv.hashCode();
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
     * Solo il package richiamare il metodo
     */
    void flip() {
        isVisible = !isVisible;
        iv.setImageResource(getImage());
        System.out.println("Messa la carta " + hash + " a: " + isVisible());
    }

    /**
     * Rimette una carta nascosta
     */
    void reset() {
        isVisible = false;
        iv.setImageResource(getImage());
        iv.refreshDrawableState();
    }

    /**
     * Nasconde la carta
     */
    void hide() {
        iv.setVisibility(View.INVISIBLE);
    }
}
