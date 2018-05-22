package com.example.johan.multi_quiz;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class DifficultyActivity extends AppCompatActivity {

    int theme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty);

        Intent intentGet = getIntent(); //getIntent
        theme = intentGet.getIntExtra("category", 0);
        Log.i("intent categorie", "theme : " + theme);

        File jsonFile = new File(getCacheDir(), "quiz.json");
        boolean deleteFile = jsonFile.delete();

    }

    public void goToEasy(View view){
        if(isOnline()){
            Intent intent = new Intent(this, WaitActivity.class); //putIntent
            String url = "https://opentdb.com/api.php?amount=5&category="+theme+"&difficulty=easy&type=multiple";
            intent.putExtra("category",2); //2 pour Quiz et 1 pour ListeView
            GetQuizServices.startActionQuiz(this, url);
            startActivity(intent);
        }else{
            Toast.makeText(getApplicationContext(),"Veuillez vous connecter à internet",Toast.LENGTH_LONG).show();
        }

    }

    public void goToMedium(View view){
        if(isOnline()) {
            Intent intent = new Intent(this, WaitActivity.class); //putIntent
            String url = "https://opentdb.com/api.php?amount=5&category=" + theme + "&difficulty=medium&type=multiple";
            intent.putExtra("category",2); //2 pour Quiz et 1 pour ListeView
            GetQuizServices.startActionQuiz(this, url);
            startActivity(intent);
        }else{
            Toast.makeText(getApplicationContext(),"Veuillez vous connecter à internet",Toast.LENGTH_LONG).show();
        }
    }

    public void goToHard(View view){
        if(isOnline()) {
            Intent intent = new Intent(this, WaitActivity.class); //putIntent
            String url = "https://opentdb.com/api.php?amount=5&category=" + theme + "&difficulty=medium&type=multiple";
            intent.putExtra("category",2); //2 pour Quiz et 1 pour ListeView
            GetQuizServices.startActionQuiz(this, url);
            startActivity(intent);
        }else{
            Toast.makeText(getApplicationContext(),"Veuillez vous connecter à internet",Toast.LENGTH_LONG).show();
        }
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }
}
