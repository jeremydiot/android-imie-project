package com.jdiot.projetfinal.adapters;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.jdiot.projetfinal.R;
import com.jdiot.projetfinal.activities.DetailActivity;
import com.jdiot.projetfinal.fragments.DetailFragment;
import com.jdiot.projetfinal.pojo.Vehicle;
import com.squareup.picasso.Picasso;

import org.parceler.Parcel;
import org.parceler.Parcels;

import java.util.List;

public class VehicleAdapter extends RecyclerView.Adapter<VehicleAdapter.VehicleViewHolder>
{

    private static final String URL_IMAGE = "http://s519716619.onlinehome.fr/exchange/madrental/images/%s";


    private List<Vehicle> listeVehicle;
    private AppCompatActivity activity;

    public VehicleAdapter(List<Vehicle> listeVehicle, AppCompatActivity activity)
    {
        this.listeVehicle = listeVehicle;
        this.activity = activity;
    }
    @Override
    public VehicleViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View viewVehicle = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_vehicle, parent, false);
        return new VehicleViewHolder(viewVehicle);
    }

    @Override
    public void onBindViewHolder(VehicleViewHolder holder, int position)
    {

        holder.textViewPrice.setText(
                Integer.toString(listeVehicle.get(position).prixjournalierbase)+activity.getResources().getString(R.string.prefix_vehicle_price)
        );
        holder.textViewCo2Category.setText(
                activity.getResources().getString(R.string.prefix_vehicle_co2_category)+String.valueOf(listeVehicle.get(position).categorieco2)
        );
        holder.textViewName.setText(listeVehicle.get(position).nom);

        Picasso.with(activity)
                .load(String.format(URL_IMAGE,listeVehicle.get(position).image))
                .into(holder.imageViewVehicle);
    }
    @Override
    public int getItemCount()
    {
        return listeVehicle.size();
    }

    public void addVehicle(Vehicle vehicle){
        listeVehicle.add(0,vehicle);
        notifyItemInserted(0);
    }

    public class VehicleViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView imageViewVehicle;
        public TextView textViewPrice;
        public TextView textViewCo2Category;
        public TextView textViewName;
        public ConstraintLayout constraintLayoutVehicleItem;

        public VehicleViewHolder(View itemView)
        {
            super(itemView);

            imageViewVehicle = itemView.findViewById(R.id.vehicle_image);
            textViewCo2Category = itemView.findViewById(R.id.vehicle_co2_category);
            textViewName = itemView.findViewById(R.id.vehicle_name);
            textViewPrice = itemView.findViewById(R.id.vehicle_price);
            constraintLayoutVehicleItem = itemView.findViewById(R.id.layout_vehicle_item);

            constraintLayoutVehicleItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Vehicle vehicle = listeVehicle.get(getAdapterPosition());

                    if (activity.findViewById(R.id.cl_detail) != null) {

                        DetailFragment fragment = new DetailFragment();
                        FragmentManager fragmentManager = activity.getSupportFragmentManager();

                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.cl_detail, fragment, DetailFragment.FRAGMENT_TAG);
                        fragmentTransaction.commit();

                        Bundle bundle = new Bundle();
                        Gson gson = new Gson();

                        bundle.putString(DetailFragment.VEHICLE_PARAM,gson.toJson(vehicle));
                        fragment.setArguments(bundle);
                    }else{
                        Gson gson = new Gson();
                        String strVehicle = gson.toJson(vehicle);

                        Intent intent = new Intent(view.getContext(),DetailActivity.class);
                        intent.putExtra(DetailActivity.EXTRA_VEHICLE, strVehicle);
                        view.getContext().startActivity(intent);
                    }

                }

            });
//
//            textViewLibelleCourse.setOnClickListener(new View.OnClickListener()
//            {
//                @Override
//                public void onClick(View view)
//                {
//                    CourseDTO course = listeVehicle.get(getAdapterPosition());
//
//                    if (activity.findViewById(R.id.cl_right) != null) {
//                        DetailFragment fragment = new DetailFragment();
//                        // transaction :
//                        FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
//                        fragmentTransaction.replace(R.id.cl_right, fragment, DetailFragment.FRAGMENT_TAG);
//                        fragmentTransaction.commit();
//
//                        Bundle bundle = new Bundle();
//                        bundle.putString(DetailFragment.LIBELLE_PARAM,course.intitule);
//                        fragment.setArguments(bundle);
//
//                    } else {
//                        Intent intent = new Intent(view.getContext(),DetailActivity.class);
//                        intent.putExtra(DetailActivity.EXTRA_STRING_LIBELLE,course.intitule);
//                        view.getContext().startActivity(intent);
//
//                    }
//
//
//
//                }
//            });
        }
    }
}
