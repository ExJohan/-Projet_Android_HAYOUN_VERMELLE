package com.example.johan.multi_quiz;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        File jsonFile = new File(getCacheDir(), "quiz.json");
        boolean deleteFile = jsonFile.delete();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_settings:
                if(isOnline()){
                    Intent intent = new Intent(this, WaitActivity.class); //putIntent
                    String url = "https://opentdb.com/api.php?amount=20";
                    intent.putExtra("category",1);
                    GetQuizServices.startActionQuiz(this, url);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(),"Veuillez vous connecter à internet",Toast.LENGTH_LONG).show();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void goToSports(View view){
        Intent intent = new Intent(this, DifficultyActivity.class);
        intent.putExtra("category",21);
        startActivity(intent);
    }

    public void goToMusique(View view){
        Intent intent = new Intent(this, DifficultyActivity.class);
        intent.putExtra("category",12);
        startActivity(intent);
    }

    public void goToHistoire(View view){
        Intent intent = new Intent(this, DifficultyActivity.class);
        intent.putExtra("category",23);
        startActivity(intent);
    }

    public void goToJeux(View view){
        Intent intent = new Intent(this, DifficultyActivity.class);
        intent.putExtra("category",15);
        startActivity(intent);
    }

    public void goToInformatique(View view){
        Intent intent = new Intent(this, DifficultyActivity.class);
        intent.putExtra("category",18);
        startActivity(intent);
    }

    public void goToAnimaux(View view){
        Intent intent = new Intent(this, DifficultyActivity.class);
        intent.putExtra("category",27);
        startActivity(intent);
    }

    public void goToMangas(View view){
        Intent intent = new Intent(this, DifficultyActivity.class);
        intent.putExtra("category",31);
        startActivity(intent);
    }

    public void goToBD(View view){
        Intent intent = new Intent(this, DifficultyActivity.class);
        intent.putExtra("category",29);
        startActivity(intent);
    }

    public void goToFilms(View view){
        Intent intent = new Intent(this, DifficultyActivity.class);
        intent.putExtra("category",11);
        startActivity(intent);
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