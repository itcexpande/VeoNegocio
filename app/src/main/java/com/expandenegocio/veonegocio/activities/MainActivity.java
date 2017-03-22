package com.expandenegocio.veonegocio.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.expandenegocio.veonegocio.R;
/*
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void comprobar(View view) {

        EditText usuario = (EditText) this.findViewById(R.id.edit_nombre_usuario);
        EditText contrasena = (EditText) this.findViewById(R.id.edit_contraseña);
       // Toast.makeText(this, usuario.getText(), Toast.LENGTH_LONG).show();
       // Toast.makeText(this, contrasena.getText(), Toast.LENGTH_LONG).show();
        TextView recoger1 = (TextView) this.findViewById(R.id.respuesta1);
        recoger1.setText( usuario.getText());

        TextView recoger2 = (TextView) this.findViewById(R.id.respuesta2);
        recoger1.setText( contrasena.getText());



        //  comprobar si usuario y contraseña estan en base de datos
        // si existe usuario  hacer algo ,nueva activity
        // si no exixte  lanzar activity de registro




    }

    public void lanzarPresentacion(View view) {
        Intent intent=new Intent("AcivityPresentacion");
        startActivity(intent);
    }

    public void lanzarSinConexion(View view) {
        Intent intent=new Intent("AcivitySinConexion");
        startActivity(intent);
    }

    public void lanzarRegistro(View view) {
        Intent intent=new Intent("AcivityRegistro");
        startActivity(intent);

    }

    public void resolverBotonPresentacion(View view) {
    }
}
*/
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

import java.util.HashMap;

//public class MainActivity extends AppCompatActivity {
public class MainActivity extends ActionBarActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {
    private SliderLayout mDemoSlider;
    private GestoraImagenes gestora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gestora = new GestoraImagenes(this);
        String from[] = Imagen.getClaves();
      // aqui hay que mtere las imagenes del ENUM Imagenes


        mDemoSlider = (SliderLayout) findViewById(R.id.slider);
        HashMap<String,String> url_maps = new HashMap();
        url_maps.put("Big Bang Theory", "http://tvfiles.alphacoders.com/100/hdclearart-10.png");
        url_maps.put("House of Cards", "http://cdn3.nflximg.net/images/3093/2043093.jpg");
        url_maps.put("House of Cards2", "http://cdn3.nflximg.net/images/3093/2043093.jpg");






        for (String name : url_maps.keySet()) {
            TextSliderView textSliderView = new TextSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image( url_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(MainActivity.this);

            //add your extra information
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
        ListView l = (ListView) findViewById(R.id.transformes);
        l.setAdapter(new TransformerAdapter(this));
        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                mDemoSlider.setPresetTransformer(((TextView) view).getText().toString());
            }
        });
    }


    @Override
    public void onStop() {
        // To prevent a memory leak on rotation, make sure to call stopAutoCycle() on the slider before activity or fragment is destroyed
        mDemoSlider.stopAutoCycle();
        super.onStop();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        Toast.makeText(this, slider.getBundle().get("extra") + "", Toast.LENGTH_SHORT).show();
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
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    */
}
