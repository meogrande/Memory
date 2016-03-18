package meomobile.it.memory.game;

import java.util.HashMap;

/**
 * Creato da fabio on 14/03/2016.
 */
public class GameModel {
    HashMap<Integer, Card> card_list;
    int flippedCards; // Il numero di carte girate
    // Memorizzo le carte girate
    Card first;
    Card second;
    int points;
    int guessedCards; // Il numero delle carte indovinate

    public GameModel() {
        card_list = new HashMap<Integer, Card>();
        flippedCards = 0;
        points = 0;
    }

    public void put(Integer i, Card value) {
        card_list.put(i, value);
    }

    Card get(Integer i) {
        return card_list.get(i);
    }

    // Ritorna quante carte sono state girate
    public int getFlippedCards() {
        return flippedCards;
    }

    /**
     * Ritorna vero se ci sono due carte girate e sono uguali
     *
     * @return
     */
    public boolean hasMatch() {
        boolean result = false;
        // Se sono girate in due e hanno la stessa immagine
        if ((flippedCards == 2) && (first.getImage() == second.getImage())) {
            result = true;
        }
        return result;
    }

    /**
     * Gira la carta indicata da i e la ritorna
     * E' il cuore del gioco: se ho zero carte cliccate la gira e basta
     * Se ho una carta cliccate la gira solo se non è già girata
     * Se ho due carte cliccate non fa nulla
     */
    public Card flip(Integer i) {
        Card c = get(i);
        if (flippedCards == 0) {
            c.flip();
            first = c;
            flippedCards = 1;

        } else if (flippedCards == 1) {
            if (!c.isVisible()) {
                c.flip();
                second = c;
                flippedCards = 2;
            }
        }
        return c;
    }

    /**
     * Fine turno quando ho due carte girate
     */
    public void endTurn() {
        if (flippedCards==2) {
            // Se c'è il match faccio scomparire le carte
            if (hasMatch()) {
                first.hide();
                second.hide();
                // Aumento il punteggio di 100 punti
                points = points + 100;
                // Aggiungo due alle carte indovinate
                guessedCards += 2;
            } else {
                // Le ricopro e tolgo 30 punti
                points -=30;
                if (first != null) {
                    first.reset();
                }
                if (second != null) {
                    second.reset();
                }
            }
            // Rimetto il contatore a zero
            flippedCards = 0;
        }
    }

    /**
     * Returns true if the game is finished
     */
    public boolean isGameFinished() {
        if (guessedCards==card_list.size()) {
            return true;
        }
        return false;
    }

    /**
     * Restituisce i punti
     * @return
     */
    public int getPoints() {
        return points;
    }
}
