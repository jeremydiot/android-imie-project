package com.jdiot.projetfinal.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.jdiot.projetfinal.R;
import com.jdiot.projetfinal.fragments.DetailFragment;
import com.jdiot.projetfinal.pojo.Vehicle;

import org.parceler.Parcels;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_VEHICLE = "EXTRA_VEHICLE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // fragment :
        DetailFragment fragment = new DetailFragment();
        // fragment manager :
        FragmentManager fragmentManager = getSupportFragmentManager();
        // transaction :
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.detail_fragment_container, fragment, DetailFragment.FRAGMENT_TAG);
        fragmentTransaction.commit();

        Bundle bundle = new Bundle();
        bundle.putString(DetailFragment.VEHICLE_PARAM,getIntent().getStringExtra(EXTRA_VEHICLE));
        fragment.setArguments(bundle);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}