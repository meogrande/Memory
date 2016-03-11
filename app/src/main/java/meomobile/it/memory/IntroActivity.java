package meomobile.it.memory;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        Button b = (Button) findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), MainActivity.class);
                TextView righe = (TextView) findViewById(R.id.editText_righe);
                TextView colonne = (TextView) findViewById(R.id.editText_colonne);
                i.putExtra("righe", righe.getText().toString().trim());
                i.putExtra("colonne", colonne.getText().toString().trim());
                startActivity(i);
            }
        });
    }
}
