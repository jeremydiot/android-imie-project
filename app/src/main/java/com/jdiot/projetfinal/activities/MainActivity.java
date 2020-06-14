package com.jdiot.projetfinal.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jdiot.projetfinal.R;
import com.jdiot.projetfinal.adapters.VehicleAdapter;
import com.jdiot.projetfinal.bdd.AppDatabaseHelper;
import com.jdiot.projetfinal.bdd.DTO.VehicleDTO;
import com.jdiot.projetfinal.pojo.Vehicle;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {


    public static final String WS_URL = "http://s519716619.onlinehome.fr/exchange/madrental/get-vehicules.php";
    public static final String TAG_ERROR = "VEHICLE_WS_ERROR";

    private Switch switchFav;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // instantiate layout elements
        recyclerView = findViewById(R.id.vehicle_list);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        switchFav = findViewById(R.id.fav_display_switch);


        // display vehicle list
        displayVehicleList();

        // switch button display favorites listener
        switchFav.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                displayVehicleList();
            }
        });

    }

    @Override
    protected void onResume() {
        displayVehicleList();

        super.onResume();
    }

    private void displayVehicleList(){

        final AppCompatActivity activity = this;

        final AsyncHttpClient client = new AsyncHttpClient();

        // vehicle list for database required
        if(switchFav.isChecked() == true){

            // get list from database
            List<VehicleDTO> vehicleDTOList = AppDatabaseHelper.getDatabase(this).vehicleDAO().getListVehicles();

            // transform to list of pojo
            List<Vehicle> vehicleList = new ArrayList<>();
            for (int i = 0; i<vehicleDTOList.size(); i++){
                Log.d("TAG", "displayVehicleList: "+vehicleDTOList.size());
                Vehicle vehicle = Vehicle.fromDTO(vehicleDTOList.get(i));
                vehicleList.add(vehicle);
            }

            // display list
            VehicleAdapter vehicleAdapter = new VehicleAdapter(vehicleList,this);
            recyclerView.setAdapter(vehicleAdapter);

        // vehicle list for webservice required
        }else{
            // send http request to webservice
            client.get(this, WS_URL, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                    // if http request succeed parse results to pojo
                    Gson gson = new Gson();
                    Vehicle[] vehicles = gson.fromJson(new String(responseBody), Vehicle[].class);
                    List<Vehicle> vehicleList = Arrays.asList(vehicles);

                    // display list
                    VehicleAdapter vehicleAdapter = new VehicleAdapter(vehicleList,activity);
                    recyclerView.setAdapter(vehicleAdapter);


                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                    Log.e(TAG_ERROR, error.toString());


                    // if http request missed show error
                    Toast.makeText(activity,activity.getResources().getString(R.string.ws_connection_error),Toast.LENGTH_LONG).show();

                    VehicleAdapter vehicleAdapter = new VehicleAdapter(new ArrayList<Vehicle>(),activity);
                    recyclerView.setAdapter(vehicleAdapter);

                }
            });
        }
    }
}