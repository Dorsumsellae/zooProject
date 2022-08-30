package fr.ldnr.nmb.zooproject;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

public class TankActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new TankView(this));
        Log.i("TankActivity", "onCreate: ");
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
