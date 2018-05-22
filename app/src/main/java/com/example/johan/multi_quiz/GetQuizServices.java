package com.example.johan.multi_quiz;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static android.content.ContentValues.TAG;

public class GetQuizServices extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this

    private static final String ACTION_QUIZ = "com.example.johan.multi_quiz.action.QUIZ";


    public GetQuizServices() {
        super("GetQuizServices");
    }

    // TODO: Customize helper method
    public static void startActionQuiz(Context context, String theUrl) {
        Intent intent = new Intent(context, GetQuizServices.class);
        intent.setAction(ACTION_QUIZ);
        intent.putExtra("theURL", theUrl);


        context.startService(intent);
    }

    // TODO: Customize helper method

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_QUIZ.equals(action)) {
                String theUrl = intent.getStringExtra("theURL");
                handleActionQuiz(theUrl);
            }
        }
    }

    private void handleActionQuiz(String theUrl){
        // TODO: Handle action Quiz
        URL url = null;
        try{
            url = new URL(theUrl);
            Log.i("URL de getServices" , theUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            if(HttpURLConnection.HTTP_OK == conn.getResponseCode()){
                copyInputStreamToFile(conn.getInputStream(),
                        new File(getCacheDir(), "quiz.json"));
            }
        } catch (MalformedURLException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    private void copyInputStreamToFile(InputStream in, File file){
        try {
            OutputStream out = new FileOutputStream(file);
            byte[] buf = new byte[1024];
            int len;
            while((len=in.read(buf))>0){
                out.write(buf,0,len);
            }
            out.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
