package com.expandenegocio.veonegocio.activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;




import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.Toast;


import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.expandenegocio.veonegocio.R;


import java.util.TreeMap;


public class MainActivity extends ActionBarActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {
    private SliderLayout mDemoSlider;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mDemoSlider = (SliderLayout) findViewById(R.id.slider);
        TreeMap<String, Integer> fotos_presentacion = new TreeMap<>();
        fotos_presentacion.put("Presentacion1", R.drawable.presentacion1);
        fotos_presentacion.put("Presentacion2", R.drawable.presentacion2);
        fotos_presentacion.put("Presentacion3", R.drawable.presentacion3);
        fotos_presentacion.put("Presentacion4", R.drawable.presentacion4);


        for (String name : fotos_presentacion.keySet()) {
            TextSliderView textSliderView = new TextSliderView(MainActivity.this);

            textSliderView

                    .image(fotos_presentacion.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(MainActivity.this);


            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra", name);

            mDemoSlider.addSlider(textSliderView);
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);
        mDemoSlider.addOnPageChangeListener(MainActivity.this);


    }


    @Override
    public void onStop() {
        // To prevent a memory leak on rotation, make sure to call stopAutoCycle() on the slider before activity or fragment is destroyed
        mDemoSlider.stopAutoCycle();
        super.onStop();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        // Toast.makeText(this, slider.getBundle().get("extra") + "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        Log.d("Slider Demo", "Page Changed: " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    /**
     * Función para comprobar si hay conexión a Internet
     *
     * @param context
     * @return boolean
     */
    public static boolean compruebaConexion(Context context) {

        boolean connected = false;

        ConnectivityManager connec = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        // Recupera todas las redes (tanto móviles como wifi)
        NetworkInfo[] redes = connec.getAllNetworkInfo();

        for (int i = 0; i < redes.length; i++) {
            // Si alguna red tiene conexión, se devuelve true
            if (redes[i].getState() == NetworkInfo.State.CONNECTED) {
                connected = true;
            }
        }

        return connected;
    }


    public void irAEnlaceTerminosDeServicio(View view) {
        Intent intent = new Intent("ActivityServicio");
        startActivity(intent);


    }

    public void registro(View view) {

        if (!compruebaConexion(this)) {
            Toast.makeText(getBaseContext(), "Necesaria conexión a internet ", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent("ActivitySinConexion");
            startActivity(intent);
        } else {
            Toast.makeText(getBaseContext(), "Conectado a internet ", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent("ActivityInicioSesion");
            startActivity(intent);

        }



    }


    public void irAEnlaceTerminosDePrivacidad(View view) {
        Intent intent = new Intent("ActivityPrivacidad");
        startActivity(intent);
    }

    public void consultaro(View view) {
    //    Intent intent = new Intent("ActivityConsultas");
    //    startActivity(intent);

        Intent intent = new Intent("ActivityAltaUsuario");
        startActivity(intent);

    }
}
