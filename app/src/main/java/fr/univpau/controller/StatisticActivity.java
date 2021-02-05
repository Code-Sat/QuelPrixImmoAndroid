package fr.univpau.controller;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import fr.univpau.R;
import fr.univpau.model.UrlValueHolder;

import android.os.Bundle;
import android.util.Log;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class StatisticActivity extends AppCompatActivity {

    UrlValueHolder valueHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);

        this.configureToolbar();
        try {
            this.getData();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    private void getData() throws IOException, JSONException {

        valueHolder = new UrlValueHolder();
        FileInputStream fis = StatisticActivity.this.openFileInput("dataFromApi.json");
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader bufferedReader = new BufferedReader(isr);
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            sb.append(line);
        }
        valueHolder.setJsonHolder(String.valueOf(sb));
        Log.i("DATA",valueHolder.getJsonHolder() );

        createGraph();
    }

    private void configureToolbar(){
        //Get the toolbar (Serialise)
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //Set the toolbar
        setSupportActionBar(toolbar);
        //Get a support ActionBar corresponding to this toolbar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Graphique des recherches");

    }
    private void createGraph() throws JSONException {

        //DATA
        String strJsonRes = valueHolder.getJsonHolder();
        JSONArray jsonArray = new JSONArray(strJsonRes);
        int[] nbHouse = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        int indexDiv = 50000;
        String typeLocal = "Appartement";
        String jsonTypeLocal = "";
        
        int arrayIndex = 0;
        Log.i("INFO", "Taille de Json :" +  String.valueOf(jsonArray.length()) );

        //See every item(house/apartment) of the json file
        for (int i = 0; i < jsonArray.length(); i++) {

            JSONObject jsonObject = jsonArray.getJSONObject(i).getJSONObject("properties");
            jsonTypeLocal = jsonObject.getString("type_local");
            Log.i("DEBUG", String.valueOf(typeLocal.equals(jsonObject.getString("type_local"))) + " type_local :  " + jsonObject.getString("type_local") );
            //1:Condition to get only selected type_local (house/apartment)
            if(!jsonTypeLocal.equals("")) {
                if (typeLocal.equals(jsonObject.getString("type_local"))) {

                    Log.i("tabIndex", "tabindexxxxxxxxxxxxxxx : " + arrayIndex);
                    Log.i("DEBUG", String.valueOf(typeLocal.equals(jsonObject.getString("type_local"))) );
                    //Check sector price / index = sectorNumber
                    //arrayIndex = jsonObject.optInt("valeur_fonciere") / indexDiv;

                    //nbHouse[arrayIndex] += 1;
                    Log.i("INDEX", "pos index : " + i);
                }
            }
        }
       // Log.i("DEBUG", nbHouse.toString());
        //Log.i("DEBUG", "Array index : " + String.valueOf(arrayIndex) );


        GraphView graph = (GraphView) findViewById(R.id.graph);
        BarGraphSeries<DataPoint> series = new BarGraphSeries<DataPoint>(new DataPoint[] {
                //y : nombre de biens
                // x prix des biens
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6)
        });
        graph.addSeries(series);
    }
}