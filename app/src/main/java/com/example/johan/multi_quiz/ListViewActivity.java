package com.example.johan.multi_quiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

public class ListViewActivity extends AppCompatActivity {

    JSONObject quizObject, obj;
    JSONArray quizArray;
    int nbrQuiz;
    String theString;

    private ListView mListView;
    private String[] listQuiz = new String[20];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        quizObject = getQuizFromFile();
        nbrQuiz = 0;

        for(nbrQuiz=0; nbrQuiz<20; nbrQuiz++){
            try {
                quizArray = new JSONArray(quizObject.getString("results"));
                obj = new JSONObject(quizArray.getString(nbrQuiz));
                //JSONArray incorrectArray = new JSONArray(obj.getString("incorrect_answers"));
                theString = obj.getString("question");
                theString = convertString(theString);
                listQuiz[nbrQuiz] = theString;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        mListView = (ListView) findViewById(R.id.listView);

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(ListViewActivity.this,
                android.R.layout.simple_list_item_1, listQuiz);
        mListView.setAdapter(adapter);
    }

    public JSONObject getQuizFromFile() {
        try {
            InputStream is = new FileInputStream(getCacheDir() + "/" + "quiz.json");
            String result = InputStreamOperations.InputStreamToString(is);
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
