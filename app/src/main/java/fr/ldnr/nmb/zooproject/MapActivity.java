package fr.ldnr.nmb.zooproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MapActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MapView(this));
        Log.i("MapActivity", "onCreate finished");
        //Les toasts ne sont pas configurables seul deux durées sont possibles
        Toast.makeText(this, "Hello World !", Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if ((event.getActionMasked() == MotionEvent.ACTION_UP) &&
                (event.getX() < Resources.getSystem().getDisplayMetrics().widthPixels/2.0)){
            Intent i = new Intent(this, TankActivity.class);
            startActivity(i);
        }
        if (event.getActionMasked() == MotionEvent.ACTION_UP){
            Log.d("TankActivity", "X :"+ event.getX()+" ---- Y :"+
                    event.getY());
        }
        return true;
    }

    public class MapView extends View{

        public MapView(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            Bitmap bmp = BitmapFactory.decodeResource(getResources(),R.drawable.map);
            canvas.drawBitmap(bmp, 0,0, null);
        }
    }
}
