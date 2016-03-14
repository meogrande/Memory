package meomobile.it.memory;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import meomobile.it.memory.game.Card;
import meomobile.it.memory.game.GameModel;


public class MainActivity extends AppCompatActivity {
    ImageView iw1;
    String[] images_array;
    private GameModel gameModel;
    private int rows;
    private int columns;
    private static final ScheduledExecutorService worker =
            Executors.newSingleThreadScheduledExecutor();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        // la metto full screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        System.out.println(intent.getStringExtra("righe"));

        int rows = Integer.valueOf(intent.getStringExtra("righe"));
        int columns = Integer.valueOf(intent.getStringExtra("colonne"));

        setContentView(R.layout.activity_main);

        // carico i valori e creo un vettore di carte
        images_array = getResources().getStringArray(R.array.immages_array);
        // Creo il modello del gioco
        gameModel = new GameModel();

        // Genero il vettore dei nomi da cui estrarre
        ArrayList<String> random = new ArrayList<String>();
        for (int i=0; i<rows*columns/2; i++) {
            // Aggiungo due immagini per ogni carta
            random.add(images_array[i]);
            random.add(images_array[i]);
        }

        // Il listener
        ImageListener il = new ImageListener();

        TableLayout tableLayout = (TableLayout) findViewById(R.id.tableLayout);

        for (int i = 0; i < rows; i++) {

            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                    LayoutParams.WRAP_CONTENT));
            //tableRow.setBackgroundResource(R.drawable.shelf);

            for (int j = 0; j < columns; j++) {
                ImageView imageView = new ImageView(this);
                imageView.setAdjustViewBounds(true);
                imageView.setOnClickListener(il);
                imageView.setPadding(1,1,1,1);
                tableRow.addView(imageView, j);

                // Creo una nuova carta prendendo un'immagine casuale
                int n = (int)(Math.random()*random.size());
                // Prendo l'immagine corrispondente al nome
                int id = getResources().getIdentifier(random.remove(n), "drawable", getPackageName());
                // Creo la carta
                Card c = new Card(imageView, id);
                imageView.setImageResource(c.getImage());

                // La aggiungo alla mappa
                gameModel.put(imageView.hashCode(), c);
            }

            tableLayout.addView(tableRow, i);
        }

    }

    class ImageListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            // metto l'altra immagine
            ImageView iv = (ImageView) v;
            //int n = (int)(Math.random() * getResources().getInteger(R.integer.images_number));
            //int id = getResources().getIdentifier(images_array[n], "drawable", getPackageName());
            Card c = gameModel.flip(iv.hashCode());
            System.out.println("C'è un match:" + gameModel.hasMatch());

            if (gameModel.getFlippedCards()==2) {
                // Controllo se c'è il match
                // Le gira dopo due secondo
                new Handler().postDelayed(new Runnable()
                {
                    public void run()
                    {
                        gameModel.endTurn();
                    }
                }, 1000);

            }
            //System.out.println(v.hashCode());
        }
    }
}
