package com.example.johan.multi_quiz;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

public class QuizActivity extends AppCompatActivity {

    JSONObject quizObject;
    int score;
    int nbrQuest;
    String question;
    TextView txtQuestion;
    RadioButton rda, rdb, rdc, rdd;
    String good , false1, false2, false3;
    Button butNext;
    JSONArray quizArray;
    JSONObject obj;
    boolean continuer;
    Random r;
    int rand;
    String url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);


        int score =0;
        int nbrQuest=0;
        //Intent intent = getIntent();
        //url = intent.getStringExtra("theurl");
        //Log.i("url", "Le lien : "+ url);
        //GetQuizServices.startActionQuiz(this, url);

        continuer = false;
        quizObject = getQuizFromFile();
        txtQuestion = findViewById(R.id.question);
        rda=(RadioButton)findViewById(R.id.radio0);
        rdb=(RadioButton)findViewById(R.id.radio1);
        rdc=(RadioButton)findViewById(R.id.radio2);
        rdd=(RadioButton)findViewById(R.id.radio3);
        butNext=(Button)findViewById(R.id.next);
        theLoop();
    }
    public JSONObject getQuizFromFile() {
        try {
            InputStream is = new FileInputStream(getCacheDir() + "/" + "quiz.json");
            String result = InputStreamOperations.InputStreamToString(is); //On converti le cache en String
            try {
                quizObject = new JSONObject(result);
                JSONArray array = new JSONArray(quizObject.getString("results"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return quizObject;
    }

    public void theLoop() {
        try {
            quizArray = new JSONArray(quizObject.getString("results"));
            obj = new JSONObject(quizArray.getString(nbrQuest));
            setQuestionView();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void setQuestionView(){
        try {
            r = new Random();
            rand = r.nextInt(4 - 0) + 0;
            JSONArray incorrectArray = new JSONArray(obj.getString("incorrect_answers"));

            //On récupère les éléments qui nous interessent dans l'array et on "corrige" les caractères speciaux
            question = obj.getString("question");
            question = convertString(question);

            txtQuestion.setText(question);

            good =obj.getString("correct_answer");
            good = convertString(good);

            false1=incorrectArray.getString(0);
            false1=convertString(false1);
            false2=incorrectArray.getString(1);
            false2=convertString(false2);
            false3=incorrectArray.getString(2);
            false3=convertString(false3);

            Log.i("random",""+rand);
            rda.setText("");
            rdb.setText("");
            rdc.setText("");
            rdd.setText("");

            if(rand == 0) {
                rda.setText(good);
                rdb.setText(false1);
                rdc.setText(false2);
                rdd.setText(false3);
            }
            else if(rand == 1) {
                rda.setText(false1);
                rdb.setText(good);
                rdc.setText(false2);
                rdd.setText(false3);
            }
           else if(rand == 2) {
                rda.setText(false1);
                rdb.setText(false2);
                rdc.setText(good);
                rdd.setText(false3);
            }
            else if(rand == 3) {
                rda.setText(false1);
                rdb.setText(false2);
                rdc.setText(false3);
                rdd.setText(good);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void ButtonNext(View view){
            if(!continuer) {
                RadioGroup grp = (RadioGroup) findViewById(R.id.radioGroup1);
                final RadioButton answer = (RadioButton) findViewById(grp.getCheckedRadioButtonId());
                if(answer.getText().equals(good)){
                    txtQuestion.setText("Bravo vous avez trouvé la bonne réponse !");
                    score++;
                }else{
                    txtQuestion.setText("Dommage, la bonne réponse était : "+ good);
                }
                nbrQuest++;
                continuer = true;
            }else{
                continuer = false;
                if(nbrQuest >= 5){
                    Intent intent = new Intent(this, ResultActivity.class);
                    intent.putExtra("score",score);
                    startActivity(intent);
                }else {
                    theLoop();
                }
            }
        }
    public String convertString(String question) {
        question = question.replaceAll("&quot;", "\"");
        question = question.replaceAll("&#039;", "\'");
        question = question.replaceAll("&amp;", "&");
        question = question.replaceAll("&eacute;", "é");
        question = question.replaceAll("&ntilde;", "n");
        question = question.replaceAll("&aacute;", "à");
        question = question.replaceAll("&lt;", "<");
        question = question.replaceAll("&gt;", ">");
        question = question.replaceAll("&ldquo;", "\"");
        question = question.replaceAll("&rdquo;", "\"");
        question = question.replaceAll("&hellip;", "...");
        question = question.replaceAll("&Uuml;", "U");
        question = question.replaceAll("&micro;", "u");
        return question;
    }
}
