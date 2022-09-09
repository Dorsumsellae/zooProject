package fr.ldnr.nmb.zooproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class SpecieActivity extends Activity {

    String specieName;
    Integer specieAgeMax = null;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.specie_layout);
        Log.d("SpecieActivity", "onCreate: ");

        Button submitButton = findViewById(R.id.specie_form_submit);
        submitButton.setOnClickListener(submitListener);
    }

    private View.OnClickListener submitListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            confirm();
        }
    };

    private void confirm(){
        getSpecieNameAndAgeMax();
        Log.d("SpecieActivity", "Nom de l'espèce : " + specieName + " Age max : " + specieAgeMax);
        if (specieName != null) {
            save();
            Toast.makeText(this, "Espèce sauvegardé", Toast.LENGTH_SHORT).show();
        }else{
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
    public void getSpecieNameAndAgeMax(){
        EditText inputSpecieName = findViewById(R.id.specie_name_input);
        EditText inputSpecieAgeMax = findViewById(R.id.specie_agemax_input);

        specieName = inputSpecieName.getText().toString();
        if(!inputSpecieAgeMax.getText().toString().isEmpty()){
            specieAgeMax = Integer.parseInt(inputSpecieAgeMax.getText().toString());
        }
    }




}
