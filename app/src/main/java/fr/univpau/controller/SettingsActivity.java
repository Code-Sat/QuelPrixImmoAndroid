package fr.univpau.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import fr.univpau.R;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.material.slider.LabelFormatter;
import com.google.android.material.slider.RangeSlider;
import com.google.android.material.slider.Slider;

import java.util.List;
import java.util.Locale;

public class SettingsActivity extends AppCompatActivity {

    Slider sliderDistance;
    RangeSlider rangeSliderRoom;
    Button btnValidate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        this.configureToolbar();
        this.addListener();
    }

    private void addListener() {
        rangeSliderRoom = findViewById(R.id.activity_settings_slider_room_number);
        sliderDistance = findViewById(R.id.activity_settings_slider_distance);
        btnValidate = findViewById(R.id.activity_settings_button_validate);

        //TO CUSTOMIZE THE LAST LABEL OF THE RANGESLIDER
        rangeSliderRoom.setLabelFormatter(new LabelFormatter() {
            @NonNull
            @Override
            public String getFormattedValue(float value) {
                //It is just an example
                if (value == 8.0f)
                    return "Illimité";
                return String.format(Locale.FRENCH, "%.0f", value);
            }
        });

        btnValidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savePreferences();
                finish();
            }
        });

        rangeSliderRoom.addOnChangeListener(new RangeSlider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull RangeSlider slider, float value, boolean fromUser) {
                SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(SettingsActivity.this);
                SharedPreferences.Editor ed = pref.edit();
                List<Float> aux = rangeSliderRoom.getValues();
                ed.putFloat("roomMin",  aux.get(0));
                ed.putFloat("roomMax", aux.get(1));
                Log.i("TEST", rangeSliderRoom.getValues().toString());
            }
        });

        Log.i("TEST", "Value of values : " + rangeSliderRoom.getValues());

        sliderDistance.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                /*SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(SettingsActivity.this);
                SharedPreferences.Editor ed = pref.edit();
                float val = sliderDistance.getValue();
                ed.putString("distancePreference", String.valueOf(val));
                Log.i("TEST", String.valueOf(sliderDistance.getValue()));*/
            }
        });
    }

    private void configureToolbar(){
        //Get the toolbar (Serialise)
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //Set the toolbar
        setSupportActionBar(toolbar);
        //Get a support ActionBar corresponding to this toolbar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setTitle("Paramètres de l'application");
    }
    private void savePreferences() {

        SharedPreferences pref = this.getSharedPreferences("ios", MODE_PRIVATE);
        SharedPreferences.Editor ed = pref.edit();

        float val = sliderDistance.getValue();
        ed.putString("distancePreference", String.valueOf(val));

        List<Float> aux = rangeSliderRoom.getValues();
        ed.putFloat("roomMin",  aux.get(0));
        ed.putFloat("roomMax", aux.get(1));

        ed.commit();
    }

}