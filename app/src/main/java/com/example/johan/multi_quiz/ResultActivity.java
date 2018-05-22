package com.example.johan.multi_quiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    TextView printScore;
    TextView commentaire;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        printScore = findViewById(R.id.votrescore);
        commentaire = findViewById(R.id.commentaire);
        Intent intentGet = getIntent(); //getIntent
        int score = intentGet.getIntExtra("score", 0);

        printScore.setText(score+"/5");

        switch (score){
            case 0:
                commentaire.setText("Dommage, ce n'est peut-être pas votre jour !");
                break;
            case 1:
                commentaire.setText("Voyons le bon coté des choses, c'est déjà mieux que 0.");
                break;
            case 2:
                commentaire.setText("Pas mal, encore un petit effort pour la moyenne !");
                break;
            case 3:
                commentaire.setText("Bien ! Vous connaissez plutot bien votre sujet.");
                break;
            case 4:
                commentaire.setText("Bravo ! Presque parfait !");
                break;
            case 5:
                commentaire.setText("Score parfait !");
                break;
        }
    }

    public void goToThemes (View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
