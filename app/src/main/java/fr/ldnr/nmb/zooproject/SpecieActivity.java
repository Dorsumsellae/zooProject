package fr.ldnr.nmb.zooproject;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SpecieActivity extends Activity {

    String specieName;
    Integer specieAgeMax = null;
    Button searchButton;
    String resultString;
    private Handler uiHandler;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.specie_layout);
        Log.d("SpecieActivity", "onCreate: ");

        Button submitButton = findViewById(R.id.specie_form_submit);
        submitButton.setOnClickListener(submitListener);

        searchButton = findViewById(R.id.specie_search_form_submit);
        searchButton.setOnClickListener(searchListener);

        uiHandler = new Handler(Looper.getMainLooper());
    }

    private View.OnClickListener submitListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            confirm();
        }
    };

    private View.OnClickListener searchListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            search();
        }
    };

    private void confirm() {
        getSpecieNameAndAgeMax();
        Log.d("SpecieActivity", "Nom de l'espèce : " + specieName + " Age max : " + specieAgeMax);
        if (specieName != null) {
            save();
            Toast.makeText(this, "Espèce sauvegardé", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Nom de l'espèce obligatoire", Toast.LENGTH_SHORT).show();
        }
    }

    //Search a specie on Wikipedia
    private void search() {
        getSpecieNameAndAgeMax();
        Log.d("SpecieActivity", "Nom de l'espèce : " + specieName + " Age max : " + specieAgeMax);
        if (specieName != null) {
            Toast.makeText(this, "Recherche de l'espèce sur Wikipedia", Toast.LENGTH_SHORT).show();
            TextView specieResult = findViewById(R.id.specie_search_form_tv);
            specieResult.setText(".....");
            searchButton.setEnabled(false);
            ExecutorService executorService = Executors.newFixedThreadPool(2);
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    searchSpecieOnWikipedia();
                }
            });
        } else {
            Toast.makeText(this, "Nom de l'espèce obligatoire", Toast.LENGTH_SHORT).show();
        }
    }

    private void save() {
        Log.d("SpecieActivity", "save: Name : " + specieName + " AgeMax " + specieAgeMax);

        //save to database
        ZooOpenHelper zooOpenHelper = new ZooOpenHelper(this);
        zooOpenHelper.insertSpecie(specieName, specieAgeMax);
    }


    //function to get the specie name and age max from form
    public void getSpecieNameAndAgeMax() {
        EditText inputSpecieName = findViewById(R.id.specie_name_input);
        EditText inputSpecieAgeMax = findViewById(R.id.specie_agemax_input);

        specieName = inputSpecieName.getText().toString();
        if (!inputSpecieAgeMax.getText().toString().isEmpty()) {
            specieAgeMax = Integer.parseInt(inputSpecieAgeMax.getText().toString());
        }
    }

    //function to search a specie on Wikipedia
    public void searchSpecieOnWikipedia() {
        Log.i("SpecieActivity", "searchSpecieOnWikipedia: Début de la recherche");
        try {
            final String request = "https://fr.wikipedia.org/w/api.php?action=query&" +
                    "format=json&prop=extracts&exintro=&explaintext=&titles="
                    + URLEncoder.encode(specieName, "UTF-8");
            URLConnection connection = new URL(request).openConnection();
            InputStream response = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new java.io.InputStreamReader(response, StandardCharsets.UTF_8));
            String result = "", ligne;

            while ((ligne = reader.readLine()) != null) {
                result += (ligne + "\n");
            }
            //Log.i("SpecieActivity", "searchSpecieOnWikipedia: " + result);
            JSONObject root = new JSONObject(result);
            JSONObject query = root.getJSONObject("query");
            JSONObject pages = query.getJSONObject("pages");
            String pageId = pages.keys().next();
            if(pageId.equals("-1")){
                Log.i("SpecieActivity", "searchSpecieOnWikipedia: Aucun résultat");
                resultString = "Aucun résultat";
                return;
            }
            JSONObject page = pages.getJSONObject(pageId);
            String extract = page.getString("extract");
            Log.i("SpecieActivity", "searchSpecieOnWikipedia: " + extract);
            uiHandler.post(new Runnable() {
                @Override
                public void run() {
                    TextView specieResult = findViewById(R.id.specie_search_form_tv);
                    specieResult.setText(Html.fromHtml(extract));
                    searchButton.setEnabled(true);
                }
            });

            response.close();
        } catch (Exception e) {
            Log.e("SpecieActivity", "searchSpecieOnWikipedia: ", e);
        }
        Log.i("SpecieActivity", "searchSpecieOnWikipedia: Fin de la recherche");

    }


}
