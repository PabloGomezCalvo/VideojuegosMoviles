package es.ucm.gdv.holamundo;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            AssetManager assetManager = this.getAssets();
            InputStream inputStream = assetManager.open("logo.png");
            _sprite = BitmapFactory.decodeStream(inputStream);
        }
        catch (IOException ioe) {

        }
        _renderView = new MyView(this);
        setContentView(_renderView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        _renderView.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        _renderView.pause();
    }

    class MyView extends SurfaceView implements Runnable{
        public MyView(Context context) {
            super(context);
            if (_sprite != null)
                _imageWidth = _sprite.getWidth();
        }

        public void resume(){
            if(!_running){
                _running = true;
                _runningthread = new Thread(this);//el run del objeto pasado como parametro
                _runningthread.start();//inicia la hebra y en paralelo incia el run
            }//if(!_running)
        }

        protected void pause(){
                _running = false;
                while(true) {
                    try {
                        _runningthread.join();//esperar hasta que la hebra termine
                        break;
                    } catch (InterruptedException ie) {
                    }
                }
        }

        @Override
        public void run() {
            long _lastFrameTime = System.nanoTime();
            SurfaceHolder sh = getHolder();
            while(_running){
                long currentTime = System.nanoTime();
                long nanoElapsedTime = currentTime - _lastFrameTime;
                _lastFrameTime = currentTime;
                double elapsedTime = (double) nanoElapsedTime / 1.0e9;
                update(elapsedTime);
                //render
                while(!sh.getSurface().isValid())
                    ;//esperar a que nos de la superficie de pintado correcta
                Canvas canvas = getHolder().lockCanvas();
                render(canvas);
                getHolder().unlockCanvasAndPost(canvas);//limita el numero de frames por segundo
            }//while()
        }// run()
        //logica
        protected void update(double deltaTime){
            _x += _incX * deltaTime;

            if (_x < 0) {
                _x = -_x;
                _incX *= -1;
            }
            else if (_x > getWidth() - _imageWidth) {
                _x = 2*(getWidth() - _imageWidth) - _x;
                _incX *= -1;
            }
        }
        protected void render(Canvas c){
            if(_sprite != null){
                c.drawColor(0xFF0000FF);
                c.drawBitmap(_sprite, (int) _x, 100, null);
            }
        }

        double _x = 0;
        int _incX = 50;
        int _imageWidth;
        volatile boolean _running = false;//volatile es para que el compilador no le ponga un valor por defecto
        //cuando ese valor no se cambia en el metodo y en cambio se cambia desde otra hebra

    }//cierre del MyView
    Thread _runningthread;
    MyView _renderView;
    Bitmap _sprite;
}
