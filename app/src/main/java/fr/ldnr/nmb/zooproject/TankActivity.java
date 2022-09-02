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

import androidx.annotation.Nullable;

public class TankActivity extends Activity {

    public final static String TIME_TANK_KEY = "deltaTime";

    private long start, end, timePopcorn = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new TankView(this));
        Log.i("TankActivity", "onCreate: ");
        start = System.currentTimeMillis();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getActionMasked() == MotionEvent.ACTION_UP){
            end = System.currentTimeMillis();
            long deltaTime = end - start - timePopcorn;
            Intent i = new Intent(this, PopCornActivity.class);
            i.putExtra(TIME_TANK_KEY, deltaTime);
            startActivityForResult(i, 0);
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        timePopcorn = data.getLongExtra(PopCornActivity.TIME_POPCORN_KEY,0);
    }

    public class TankView extends View{

        public TankView(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            Bitmap bmp = BitmapFactory.decodeResource(getResources(),R.drawable.tank);
            canvas.drawBitmap(bmp, 0,0, null);
        }
    }
}
