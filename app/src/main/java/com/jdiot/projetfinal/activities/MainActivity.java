package com.jdiot.projetfinal.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jdiot.projetfinal.R;
import com.jdiot.projetfinal.adapters.VehicleAdapter;
import com.jdiot.projetfinal.pojo.Vehicle;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.util.Arrays;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {


    public static final String WS_URL = "http://s519716619.onlinehome.fr/exchange/madrental/get-vehicules.php";
    public static final String TAG_ERROR = "VEHICLE_WS_ERROR";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RecyclerView recyclerView = findViewById(R.id.vehicle_list);


        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        final AppCompatActivity activity = this;

        final AsyncHttpClient client = new AsyncHttpClient();

        client.get(this, WS_URL, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                String response = new String(responseBody);

                Gson gson = new Gson();
                Vehicle[] vehicles = gson.fromJson(response, Vehicle[].class);
                List<Vehicle> vehicleList = Arrays.asList(vehicles);

                VehicleAdapter vehicleAdapter = new VehicleAdapter(vehicleList,activity);
                recyclerView.setAdapter(vehicleAdapter);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e(TAG_ERROR, error.toString());

                Toast.makeText(activity,activity.getResources().getString(R.string.ws_connection_error),Toast.LENGTH_LONG).show();

            }
        });
    }
}