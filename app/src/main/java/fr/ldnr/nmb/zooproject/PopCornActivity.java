package fr.ldnr.nmb.zooproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.Random;

public class PopCornActivity extends Activity {

    public final static String TIME_POPCORN_KEY = "deltaTimePopcorn";
    private long start, end;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new PopCornView(this));
        Log.i("PopCornActivity", "onCreate");
        long deltaTime = getIntent().getLongExtra(TankActivity.TIME_TANK_KEY, 0)/1000;
        if(deltaTime>0){
            String[] species = getResources().getStringArray(R.array.popcorn_species);
            String second = getResources().getQuantityString(R.plurals.popcorn_seconds,(int)deltaTime);
            int index = new Random().nextInt(species.length);
            Log.i("popcornActivity", "index =  "+ index);
            String alertPopcorn = getString(R.string.alert_popcorn,species[index], deltaTime, second);
            Toast.makeText(this, alertPopcorn, Toast.LENGTH_LONG).show();
        }
        start = System.currentTimeMillis();
    }

    @Override
    public void onBackPressed() {
        end = System.currentTimeMillis();
        long deltaTime = end - start;
        Intent i = new Intent();
        i.putExtra(TIME_POPCORN_KEY, deltaTime);
        setResult(0, i);
        super.onBackPressed();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getActionMasked() == MotionEvent.ACTION_UP){
            Intent i = new Intent(this, MapActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
        }
        return true;
    }

    public class PopCornView extends View {

        public PopCornView(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            Bitmap bmp = BitmapFactory.decodeResource(getResources(),R.drawable.pop_corn);
            canvas.drawBitmap(bmp, 0,0, null);
        }
    }
}
