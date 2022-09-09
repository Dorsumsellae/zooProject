package fr.ldnr.nmb.zooproject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

public class AnimalsActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animals_layout);
        Button addAnimalButton = findViewById(R.id.animal_save_btn);
        addAnimalButton.setOnClickListener(addAnimalListener);
    }

    private final View.OnClickListener addAnimalListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //TODO
            Log.d("AnimalActivity", "onClick: Animal saved");
        }
    };

}
