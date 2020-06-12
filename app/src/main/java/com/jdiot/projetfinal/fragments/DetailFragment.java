package com.jdiot.projetfinal.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jdiot.projetfinal.R;
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

        String jsonVehicle = "";

        if(getArguments() != null){
            jsonVehicle = getArguments().getString(VEHICLE_PARAM);
        }

        Gson gson = new Gson();
        Vehicle vehicle = gson.fromJson(jsonVehicle, Vehicle.class);

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
        return v;
    }
}