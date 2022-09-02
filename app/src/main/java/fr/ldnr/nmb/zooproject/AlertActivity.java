package fr.ldnr.nmb.zooproject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class AlertActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alert_layout);
    }

    public void send(View view) {
        EditText inputIntitule = findViewById(R.id.alert_intitule_input);
        String alertIntitule = inputIntitule.getText().toString();
        Toast.makeText(this, "Envoi " + alertIntitule, Toast.LENGTH_LONG).show();
    }
}
