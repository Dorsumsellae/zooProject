package fr.ldnr.nmb.zooproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class AlertActivity extends Activity {

    public void send(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.alert_title);
        builder.setMessage(R.string.alert_confimation);
        builder.setIcon(R.mipmap.ic_launcher_round);
        builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.d("SpecieActivity", "onClick: Yes");
                confirm();
            }
        });
        builder.setNegativeButton(android.R.string.no, null);
        builder.show();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alert_layout);
        String[] places = getResources().getStringArray(R.array.places);
        ArrayAdapter<String> aa = new ArrayAdapter<String>(
                this, android.R.layout.simple_dropdown_item_1line, places);
        AutoCompleteTextView inputPlace = findViewById(R.id.alert_lieu_input);
        inputPlace.setAdapter(aa);
    }

    private void confirm() {
        EditText inputIntitule = findViewById(R.id.alert_intitule_input);
        EditText inputInfos = findViewById(R.id.alert_informations_input);
        AutoCompleteTextView inputPlace = findViewById(R.id.alert_lieu_input);
        EditText[] inputs = {inputIntitule, inputInfos, inputPlace};

        SharedPreferences preferences = getSharedPreferences("zoo", MODE_PRIVATE);
        boolean sendIsChecked = preferences.getBoolean("sendIsChecked", false);

        CheckBox isUrgentCB = findViewById(R.id.alert_urgent_CB);

        String alertIntitule = inputIntitule.getText().toString();
        String message = "Intitulé incorecte";

        if (!sendIsChecked) {
            message = "Envoi désactivé";
        } else if ((alertIntitule.length() > 5 && alertIntitule.length() < 40)) {
            boolean isUrgent = isUrgentCB.isChecked();
            message = "Envoi " + alertIntitule;
            if (isUrgent) {
                message += "!";
                isUrgentCB.setChecked(false);
            }
            resetEditText(inputs);
        }
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    private void resetEditText(EditText[] inputs) {
        for (EditText input :
                inputs) {
            input.setText("");
        }
    }
}
