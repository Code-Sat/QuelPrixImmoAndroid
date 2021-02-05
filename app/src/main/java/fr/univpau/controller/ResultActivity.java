package fr.univpau.controller;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import fr.univpau.R;
import fr.univpau.model.ResultAdapter;
import fr.univpau.model.StringHolder;
import fr.univpau.model.UrlValueHolder;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class ResultActivity extends AppCompatActivity {

    ResultAdapter mViewAdapter;
    ListView listViewResult;
    TextView titleTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Log.i("INFO", " Activity Result created.");

        listViewResult = findViewById(R.id.activity_result_list_view);
        titleTextView = findViewById(R.id.activity_result_title);

        this.configureToolbar();
        /*try {
            this.setListView();
        } catch (JSONException e) {
            e.printStackTrace();
        }*/
        try {
            getDataStored();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    private void configureToolbar(){
        //Get the toolbar (Serialize)
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //Set the toolbar
        setSupportActionBar(toolbar);
        //Get a support ActionBar corresponding to this toolbar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Résultat de recherche");
    }

    private void getDataStored() throws IOException, JSONException {

        Intent intent = getIntent();
        UrlValueHolder valueHolder = (UrlValueHolder) intent.getSerializableExtra("result");

        FileInputStream fis = ResultActivity.this.openFileInput("dataFromApi.json");
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader bufferedReader = new BufferedReader(isr);
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            sb.append(line);
        }
        valueHolder.setJsonHolder(String.valueOf(sb));

        setListView(valueHolder);
        //Log.i("DATA", "Value holder " + valueHolder.getJsonHolder());
    }

    private void setListView(UrlValueHolder valueHolder) throws JSONException {

        String strJsonRes = valueHolder.getJsonHolder();
        JSONArray jsonArray = new JSONArray(strJsonRes);
        
        ArrayList<StringHolder> resultArray = new ArrayList<StringHolder>();

        /*
        *  NEW SOLUCE WITH COLLECTION
        * */
        JSONObject jsonObject = new JSONObject();

        ArrayList<JSONObject> arrayJson = new ArrayList<JSONObject>();
        for (int i = 0; i < jsonArray.length(); i++) {
            arrayJson.add(jsonArray.getJSONObject(i).getJSONObject("properties"));

        }

        Collections.sort(arrayJson, new Comparator<JSONObject>() {

            @Override
            public int compare(JSONObject lhs, JSONObject rhs) {
                // TODO Auto-generated method stub

                try {
                    return (lhs.getString("Name").toLowerCase().compareTo(rhs.getString("Name").toLowerCase()));
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    return 0;
                }
            }
        });
        Log.i("TEST", "Latitude : " + valueHolder.getLatitude());
        Log.i("TEST", "Longitude: " + valueHolder.getLongitude());
        Log.i("TEST", "Value Holder json : " + valueHolder.getJsonHolder());
        Log.i("TEST", "Value typelocal : " + valueHolder.getType_local());

        //Json to ArrayList with some filter
        /*for (int i = 0; i < jsonArray.length(); i++) {

            StringHolder stringHolder = new StringHolder();
            JSONObject jsonObject = jsonArray.getJSONObject(i).getJSONObject("properties");

            //1:Condition to get only selected type_local (house/apartment)
            if( valueHolder.getType_local().equals(jsonObject.optString("type_local"))) {

                float roomNumber = Float.valueOf(jsonObject.getString("nombre_pieces_principales"));

                //Filter with the numbers of room number selected between [1,7]
                if(roomNumber >= valueHolder.getValues().get(0) && roomNumber < valueHolder.getValues().get(1) ) {
                    stringHolder.setResultList0(jsonObject.optString("numero_voie") + " " + jsonObject.optString("type_voie") + " " + jsonObject.optString("voie") + ", " + jsonObject.optString("code_postal") + " " + jsonObject.optString("commune"));
                    stringHolder.setResultList1(jsonObject.optString("type_local") + " " + jsonObject.getInt("nombre_pieces_principales") + " pièces de " + jsonObject.getInt("surface_relle_bati") + "m²");
                    stringHolder.setResultList2("Vendu à " + jsonObject.getInt("valeur_fonciere") + " - " + jsonObject.getInt("valeur_fonciere") / jsonObject.getInt("surface_relle_bati") + "€/m²");
                    resultArray.add(stringHolder);

                }else if(valueHolder.getValues().get(1) == 8.0) {
                    stringHolder.setResultList0(jsonObject.optString("numero_voie") + " " + jsonObject.optString("type_voie") + " " + jsonObject.optString("voie") + ", " + jsonObject.optString("code_postal") + " " + jsonObject.optString("commune"));
                    stringHolder.setResultList1(jsonObject.optString("type_local") + " " + jsonObject.getInt("nombre_pieces_principales") + " pièces de " + jsonObject.getInt("surface_relle_bati") + "m²");
                    stringHolder.setResultList2("Vendu à " + jsonObject.optInt("valeur_fonciere") + " - " + jsonObject.optInt("valeur_fonciere") / jsonObject.optInt("surface_relle_bati") + "€/m²");
                    resultArray.add(stringHolder);
                }
            }
        }*/

        if(resultArray.size() < 2) titleTextView.setText(resultArray.size() + " bien dans un rayon de " + valueHolder.getDistance() + " mètres.");
        else titleTextView.setText(resultArray.size() + " biens dans un rayon de " + valueHolder.getDistance() + " mètres.");
        Log.i("INFO", "ArrayList initialize done.");

        if( mViewAdapter == null ) {
            mViewAdapter = new ResultAdapter( this, R.layout.result_row, resultArray);
            listViewResult.setAdapter(mViewAdapter);
        }
        else {
            mViewAdapter.clear();
            mViewAdapter.addAll(resultArray);
            mViewAdapter.notifyDataSetChanged();
        }
    }
}