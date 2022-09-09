package fr.ldnr.nmb.zooproject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.Nullable;

import java.util.List;

public class AnimalsActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animals_layout);

        Button addAnimalButton = findViewById(R.id.animal_save_btn);
        addAnimalButton.setOnClickListener(addAnimalListener);

        Spinner speciesSpinner = findViewById(R.id.animal_specie_input);
        ZooOpenHelper zooOpenHelper = new ZooOpenHelper(this);
        List<String> species = zooOpenHelper.getAllSpeciesName();
        ArrayAdapter<String> speciesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, species);
        speciesSpinner.setAdapter(speciesAdapter);

        Spinner enclosureSpinner = findViewById(R.id.animal_box_input);
        List<String> enclosures = zooOpenHelper.getAllBoxesName();
        ArrayAdapter<String> enclosureAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, enclosures);
        enclosureSpinner.setAdapter(enclosureAdapter);
    }

    private final View.OnClickListener addAnimalListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //TODO
            Log.d("AnimalActivity", "onClick: Animal saved");
        }
    };

}
