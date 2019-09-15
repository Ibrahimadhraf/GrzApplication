package com.codesroots.osamaomar.grz.presentationn.screens.feature.getuserlocation;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.codesroots.osamaomar.grz.R;
import com.codesroots.osamaomar.grz.models.entities.AddLocation;
import com.codesroots.osamaomar.grz.models.entities.names;
import com.codesroots.osamaomar.grz.models.helper.PreferenceHelper;
import com.codesroots.osamaomar.grz.presentationn.screens.feature.home.mainfragment.MainViewModelFactory;
import com.codesroots.osamaomar.grz.presentationn.screens.feature.userlocations.UserLocationsViewModel;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class GetUserLocationActivity extends AppCompatActivity {

    TextView search,send,title;
    UserLocationsViewModel userLocationsViewModel;
    EditText location,city,country,notes,name,phone;
    int addressid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addnewaddress);
        userLocationsViewModel = ViewModelProviders.of(this, getViewModelFactory()).get(UserLocationsViewModel.class);
        search = findViewById(R.id.search);
        location = findViewById(R.id.location);
        city = findViewById(R.id.city);
        country = findViewById(R.id.country);
        country = findViewById(R.id.country);
        notes = findViewById(R.id.notes);
        send = findViewById(R.id.send);
        title = findViewById(R.id.title);
        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        addressid = getIntent().getIntExtra(names.BILLING_ID, 0);
        if (addressid>0)
        {
            title.setText(R.string.editaddress);
            userLocationsViewModel.viewLocation(addressid);
        }

        userLocationsViewModel.viewLocationMutableLiveData.observe(this,viewLocation ->
                {
                    location.setText(viewLocation.getData().getAddress());
                    city.setText(viewLocation.getData().getTown_city());
                    country.setText(viewLocation.getData().getState_country());
                    notes.setText(viewLocation.getData().getNotes());
                    name.setText(viewLocation.getData().getFirst_name());
                    phone.setText(viewLocation.getData().getPhone());
                });

        userLocationsViewModel.addLocationMutableLiveData.observe(this,addLocation ->
                {
                    try {
                        if (addLocation.isSuccess())
                            this.finish();
                    }catch (Exception e)
                    {}
                });

        userLocationsViewModel.error.observe(this, throwable ->
                {
                    Toast.makeText(GetUserLocationActivity.this, getText(R.string.error_tryagani), Toast.LENGTH_SHORT).show();
                    send.setText(getText(R.string.save));
                    send.setEnabled(true);
                });
    }


    private MainViewModelFactory getViewModelFactory() {
        return new MainViewModelFactory(getApplication());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void send(View view) {
        ((TextView) view).setText(getText(R.string.wait));
        (view).setEnabled(false);

        if (addressid>0)
            userLocationsViewModel.editUserLocation(addressid,name.getText().toString(),phone.getText().toString(),
                    location.getText().toString(), country.getText().toString(), city.getText().toString(), notes.getText().toString());

        else
        userLocationsViewModel.addUserLocation(PreferenceHelper.getUserId(),name.getText().toString(),phone.getText().toString(),
                location.getText().toString(), country.getText().toString(), city.getText().toString(), notes.getText().toString());
    }
}
