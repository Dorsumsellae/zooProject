package fr.ldnr.nmb.zooproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

        Button button_map = findViewById(R.id.button_map);
        button_map.setOnClickListener(btnMapListener);
    }

    public void toMap() {
        Intent i = new Intent(this, MapActivity.class);
        startActivity(i);
    }

    private final View.OnClickListener btnMapListener = view -> {
        Log.i("HomeActivity", "onClick");
        toMap();
    };

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
