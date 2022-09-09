package fr.ldnr.nmb.zooproject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

public class HomeActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);
        readNewsFromFile();

        Button map_btn = findViewById(R.id.map_btn);
        map_btn.setOnClickListener(btnMapListener);

        Button annuaire_btn = findViewById(R.id.annuaire_btn);
        annuaire_btn.setOnClickListener(btnAnnuaireListener);

        Button alert_btn = findViewById(R.id.alert_btn);
        alert_btn.setOnClickListener(btnAlertListener);

        Button specie_btn = findViewById(R.id.specie_btn);
        specie_btn.setOnClickListener(btnSpecieListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.acceuil, menu);
        //read from shared preferences
        SharedPreferences preferences = getSharedPreferences("zoo", MODE_PRIVATE);
        boolean sendIsChecked = preferences.getBoolean("sendIsChecked", false);
        MenuItem sendItem = menu.findItem(R.id.menu_send);
        sendItem.setChecked(sendIsChecked);
        return true;
    }

    @Override
    public boolean onMenuItemSelected(int featureId, @NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_map:
                toMap();
                return true;
            case R.id.menu_alert:
                toAlertActivity();
                return true;
            case R.id.menu_animal:
                toAnimalActivity();
                return true;
            case R.id.menu_send:
                item.setChecked(!item.isChecked());
            case R.id.menu_specie:
                toSpecieActivity();

                //save to shared preferences
                SharedPreferences sp = getSharedPreferences("zoo", MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putBoolean("sendIsChecked", item.isChecked());
                editor.commit();

                return true;
            default:
                return false;
        }

    }

    private final View.OnClickListener btnAnnuaireListener = view -> {
        toAnnuaireActivity();
    };

    private final View.OnClickListener btnMapListener = view -> {
        Log.i("HomeActivity", "onClick");
        toMap();
    };

    private final View.OnClickListener btnAlertListener = view -> {
        Log.i("HomeActivity", "onClick");
        toAlertActivity();
    };
    private final View.OnClickListener btnSpecieListener = view -> {
        toSpecieActivity();
    };


    public void toMap() {
        Intent i = new Intent(this, MapActivity.class);
        startActivity(i);
    }
    public void toAlertActivity() {
        Intent i = new Intent(this, AlertActivity.class);
        startActivity(i);
    }
    public void toAnnuaireActivity() {
        Intent i = new Intent(this, AnnuaireActivity.class);
        startActivity(i);
    }
    public void toSpecieActivity() {
        Intent i = new Intent(this, SpecieActivity.class);
        startActivity(i);
    }

    public void toAnimalActivity() {
        Intent i = new Intent(this, AnimalsActivity.class);
        startActivity(i);
    }

    private void readNewsFromFile() {
        TextView newsTV = findViewById(R.id.tvNews);
        StringBuilder news = new StringBuilder();
        String line;
        try {
            InputStream is = getAssets().open("news.txt");
            Reader r = new InputStreamReader(is, StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(r);
            while ((line = br.readLine()) != null) news.append("\n").append(line);
            is.close();
        } catch (Exception ex) {
            Log.e("HomeActivity", "readNewsFromFile: ", ex);
        }
        newsTV.setText(news.toString());
    }
}
