package com.jdiot.projetfinal.fragments;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jdiot.projetfinal.R;
import com.jdiot.projetfinal.bdd.AppDatabaseHelper;
import com.jdiot.projetfinal.bdd.DTO.VehicleDTO;
import com.jdiot.projetfinal.pojo.Vehicle;

import org.parceler.Parcels;

public class DetailFragment extends Fragment {

    public static final String FRAGMENT_TAG = "FRAGMENT_TAG";
    public static final String VEHICLE_PARAM = "VEHICLE_PARAM";

    private TextView tvName;
    private TextView tvAvailable;
    private TextView tvPrice;
    private TextView tvPromotion;
    private TextView tvAge;
    private TextView tvCo2caterory;
    private TextView tvEquipment;
    private TextView tvOption;
    private Switch switchFav;

    Vehicle vehicle;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_detail, container, false);

        tvName = v.findViewById(R.id.frag_name);
        tvAvailable = v.findViewById(R.id.frag_available);
        tvPrice = v.findViewById(R.id.frag_price);
        tvPromotion = v.findViewById(R.id.frag_promotion);
        tvAge = v.findViewById(R.id.frag_age);
        tvCo2caterory = v.findViewById(R.id.frag_c02category);
        tvEquipment = v.findViewById(R.id.frag_equipment);
        tvOption = v.findViewById(R.id.frag_option);
        switchFav = v.findViewById(R.id.fav_switch);

        String jsonVehicle = "";

        if(getArguments() != null){
            jsonVehicle = getArguments().getString(VEHICLE_PARAM);
        }

        Gson gson = new Gson();
        vehicle = gson.fromJson(jsonVehicle, Vehicle.class);

        tvName.setText(vehicle.nom);
        if(vehicle.disponible != 0){
            tvAvailable.setText(v.getResources().getString(R.string.yes));
        }
        tvPrice.setText(Integer.toString(vehicle.prixjournalierbase)+v.getResources().getString(R.string.prefix_vehicle_price));
        tvPromotion.setText(Integer.toString(vehicle.promotion)+v.getResources().getString(R.string.money_symbol));
        tvAge.setText(Integer.toString(vehicle.agemin));
        tvCo2caterory.setText(vehicle.categorieco2);
        tvEquipment.setText(vehicle.equipmentsToString());
        tvOption.setText(vehicle.optionsToString(v.getResources().getString(R.string.money_symbol)));


        switchFav.setChecked(AppDatabaseHelper.getDatabase(getActivity()).vehicleDAO().getVehicleById(vehicle.id).size() > 0);


        final Activity activity = getActivity();

        switchFav.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                VehicleDTO vehicleDTO = vehicle.toDTO();

                if (isChecked == true){
                    AppDatabaseHelper.getDatabase(activity).vehicleDAO().insert(vehicleDTO);
                }else {
                    AppDatabaseHelper.getDatabase(activity).vehicleDAO().delete(vehicleDTO);
                }
            }
        });

        return v;
    }
}