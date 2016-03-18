package meomobile.it.memory;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        // Per l'easy
        ImageButton b = (ImageButton) findViewById(R.id.imageButton_easy);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Creo un intento
                Intent i = new Intent(v.getContext(), MainActivity.class);
                // Inserisco i valori nell'intento
                i.putExtra("righe", 2);
                i.putExtra("colonne", 2);
                // attivo la nuova Activity
                startActivity(i);
            }
        });

        // Per il medium
        ImageButton b1 = (ImageButton) findViewById(R.id.imageButton_medium);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Creo un intento
                Intent i = new Intent(v.getContext(), MainActivity.class);
                // Inserisco i valori nell'intento
                i.putExtra("righe", 3);
                i.putExtra("colonne",4);
                // attivo la nuova Activity
                startActivity(i);
            }
        });

        // Per l'hard
        ImageButton b2 = (ImageButton) findViewById(R.id.imageButton_hard);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Creo un intento
                Intent i = new Intent(v.getContext(), MainActivity.class);
                // Inserisco i valori nell'intento
                i.putExtra("righe", 4);
                i.putExtra("colonne",6);
                // attivo la nuova Activity
                startActivity(i);
            }
        });
    }
}
