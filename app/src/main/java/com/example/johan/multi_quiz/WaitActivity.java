package com.example.johan.multi_quiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class WaitActivity extends AppCompatActivity {

    int category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait);
        Intent intentGet = getIntent(); //getIntent
        category = intentGet.getIntExtra("category", 0);
        Button nextBut = findViewById(R.id.quiznext);
        File file = new File(getCacheDir(), "quiz.json");
        TextView waitText = findViewById(R.id.wait);
        while(!file.exists()) {
            nextBut.setClickable(false);
            nextBut.setText("Veuillez patienter");
            waitText.setText("Quiz en cours de telechargement");
            }
        nextBut.setClickable(true);
        nextBut.setText("Continuer");
        if(category ==2) {
            waitText.setText("Quiz prêt !");
        }else if(category ==1) {
            waitText.setText("Liste prête !");
        }
        Toast.makeText(getApplicationContext(),"Service téléchargé",Toast.LENGTH_LONG).show();
        }

    public void goToQuiz(View view){
        if(category == 2) {
            Intent intent = new Intent(this, QuizActivity.class); //putIntent
            startActivity(intent);
        }else if(category == 1){
            Intent intent = new Intent(this, ListViewActivity.class); //putIntent
            startActivity(intent);
        }
    }
}
