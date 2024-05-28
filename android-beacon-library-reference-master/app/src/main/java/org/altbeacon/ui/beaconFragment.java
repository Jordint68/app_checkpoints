package org.altbeacon.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.MonitorNotifier;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;
import org.altbeacon.beaconreference.databinding.FragmentBeaconBinding;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link beaconFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class beaconFragment
        extends Fragment
        implements MonitorNotifier {
    private static final String CLAUCURSA = "Cursa";
    private static final String CLAUCIRCUIT = "Circuit";
    private static final String CLAUCATEGORIA = "Categoria";
    private static final String CLAUCHECKPOINT = "Checkpoint";

    private FragmentBeaconBinding mBinding;

    private String cursa_id = "";
    private String circuit_id = "";
    private String categoria_id = "";
    private String checkpoint_id = "";

    // beacons
    public static final Region wildcardRegion = new Region("wildcardRegion", null, null, null);
    Region allBeaconsRegion;
    BeaconManager beaconManager;

    public beaconFragment() {}

    public static beaconFragment newInstance(String param1, String param2) {
        beaconFragment fragment = new beaconFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            cursa_id = bundle.getString(CLAUCURSA);
            circuit_id = bundle.getString(CLAUCIRCUIT);
            categoria_id = bundle.getString(CLAUCATEGORIA);
            checkpoint_id = bundle.getString(CLAUCHECKPOINT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentBeaconBinding.inflate(getLayoutInflater());

        // configurar beacons
        beaconManager = BeaconManager.getInstanceForApplication(getContext());
        beaconManager.getBeaconParsers().add(new BeaconParser().
                setBeaconLayout("m:2-3=beac,i:4-19,i:20-21,i:22-23,p:24-24,d:25-25"));
        allBeaconsRegion = new Region("all-beacons-region", null, null, null);
        RangeNotifier rn = (beacons, region) -> {
            if (beacons.size() > 0) {
//                Log.i("BEACON", "The first beacon I see is about " + beacons.iterator().next().getId1() + " meters away.");

                // Enviar a l'api
                beaconTrobat(beacons.iterator().next().getId1().toString());

            }
        };
        beaconManager.addRangeNotifier(rn);


        mBinding.btnStart.setOnClickListener(v -> {
            beaconManager.startRangingBeacons(allBeaconsRegion);
            beaconManager.startMonitoring(wildcardRegion);
        });

        mBinding.btnStop.setOnClickListener(v -> {
            beaconManager.stopRangingBeacons(allBeaconsRegion);
            beaconManager.stopMonitoring(wildcardRegion);
        });

        return mBinding.getRoot();
    }

    private void beaconTrobat(String idTrobada) {
        formarJson();
    }

    private void formarJson() {
        JSONObject json = new JSONObject();

        try {
            json.put("reg_ins_id", "a");
        } catch (JSONException e) {
            Log.d("ERROR", "Error al formar json " + e.getMessage());
            throw new RuntimeException();
        }
    }

    @Override
    public void didEnterRegion(Region region) {

    }

    @Override
    public void didExitRegion(Region region) {

    }

    @Override
    public void didDetermineStateForRegion(int state, Region region) {

    }
}