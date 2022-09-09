package fr.ldnr.nmb.zooproject;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

public class AnnuaireActivity extends Activity {

    private long start, end;
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annuaire);
        if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            Log.i("AnnuaireActivity", "onCreate: permission not granted");
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);

        } else {
            Log.i("AnnuaireActivity", "onCreate: permission granted");
            count();
        }
    }

    //function to time the activity
    @Override
    protected void onStart() {
        super.onStart();
        start = System.currentTimeMillis();
        writeLog("AnnuaireActivity started");
        Log.d("AnnuaireActivity", "onStart: AnnuaireActivity started");
    }

    @Override
    protected void onStop() {
        super.onStop();
        end = System.currentTimeMillis();
        int deltaTime = (int) ((end - start) / 1_000);
        writeLog("AnnuaireActivity close after : " + deltaTime + 's');
        Log.d("AnnuaireActivity", "onStop: AnnuaireActivity close after : "
                + deltaTime + "s");
    }

    //function to write log to files in internal storage
    private void writeLog(String message) {
        try {
            OutputStream os = openFileOutput("annuaire.log.txt", MODE_APPEND);
            Writer w = new OutputStreamWriter(os, StandardCharsets.UTF_8);
            w.write(message + "\n");
            w.close();
            //Log.d("AnnuaireActivity", "writeLog: Log écrit");
        } catch (Exception e) {
            Log.e("AnnuaireActivity", "writeLog:", e);
        }
    }

    //function to count the number of times the activity is called and saved it in external file
    private void count() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File repertoire = Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DOWNLOADS);

            File counterFile = new File(repertoire, "zoo_count.txt");
            try {
                if (!counterFile.exists()) {
                    FileReader fileReader = new FileReader(counterFile);
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String line = bufferedReader.readLine();
                    Log.d("Annuaire Activity", "count:  " + line);
                    count = Integer.parseInt(line);
                    fileReader.close();
                }
                    count++;

                    FileWriter fileWriter = new FileWriter(counterFile, false);
                    fileWriter.write(count);
                    fileWriter.close();
                    Log.d("AnnuaireActivity", "count: file updated");

            } catch (Exception e) {
                Log.e("AnnuaireActivity", "count: ", e);
            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            count();
            Log.i("AnnuaireActivity", "onRequestPermissionsResult: permission granted");
        } else {
            Log.i("AnnuaireActivity", "onRequestPermissionsResult: permission not granted");
            Toast.makeText(this, "Permission non accordée : enregistrement impossible", Toast.LENGTH_LONG).show();
        }
    }
}