package org.altbeacon.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.altbeacon.API.APIManager;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.MonitorNotifier;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;
import org.altbeacon.beaconreference.databinding.FragmentBeaconBinding;
import org.altbeacon.models.getInscripcions.Beacon;
import org.altbeacon.models.getInscripcions.InscripcionsExample;
import org.altbeacon.models.getInscripcions.InscripcionsInscripcion;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import java.sql.Timestamp;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

    private List<InscripcionsInscripcion> lInscripcions = new ArrayList<>();

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
        
        obtenirDadesBeacons();

        String prova = formarJson("1", new Timestamp(System.currentTimeMillis()));

        // configurar beacons
        beaconManager = BeaconManager.getInstanceForApplication(getContext());
        beaconManager.getBeaconParsers().add(new BeaconParser().
                setBeaconLayout("m:2-3=beac,i:4-19,i:20-21,i:22-23,p:24-24,d:25-25"));
        allBeaconsRegion = new Region("all-beacons-region", null, null, null);
        RangeNotifier rn = (beacons, region) -> {
            if (beacons.size() > 0) {
//                Log.i("BEACON", "The first beacon I see is about " + beacons.iterator().next().getId1() + " meters away.");

                // Enviar a l'api
                // En el moment en el que es troba el beacon es grava el timestamp:
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//                Iterator<org.altbeacon.beacon.Beacon> iteBeacons = beacons.iterator();
////                while(beacons.iterator().hasNext()) {
////                    beaconTrobat(iteBeacons.next().getId1().toString(), timestamp);
////                }

                beaconTrobat(beacons.iterator().next().getId1().toString(), timestamp);


            }
        };
        beaconManager.addRangeNotifier(rn);


        mBinding.btnStart.setOnClickListener(v -> {
            beaconManager.startRangingBeacons(allBeaconsRegion);
            beaconManager.startMonitoring(wildcardRegion);
//            Log.d("XXX", );
            mBinding.btnStop.setEnabled(true);
            mBinding.btnStart.setEnabled(false);
        });

        mBinding.btnStop.setOnClickListener(v -> {
            beaconManager.stopRangingBeacons(allBeaconsRegion);
            beaconManager.stopMonitoring(wildcardRegion);
            mBinding.btnStop.setEnabled(false);
            mBinding.btnStart.setEnabled(true);
        });

        mBinding.btnStop.setEnabled(false);

        return mBinding.getRoot();
    }

    private void obtenirDadesBeacons() {
        APIManager.getInstance().getAllInscripcions(new Callback<InscripcionsExample>() {
            @Override
            public void onResponse(Call<InscripcionsExample> call, Response<InscripcionsExample> response) {
                lInscripcions =  response.body().getInscripcions();
                Log.d("XXX", "Code: " + lInscripcions.get(0).getBeacons().getBea_code());
            }

            @Override
            public void onFailure(Call<InscripcionsExample> call, Throwable t) {
                Log.d("ERROR", "Obtenir inscripcions - " + t.getMessage());
            }
        });
    }

    private void beaconTrobat(String idTrobada, Timestamp timestamp) {
        Log.d("XXX", "ID: " + idTrobada + " Timestamp: " + timestamp);
        String text = mBinding.txvLog.getText().toString();
        mBinding.txvLog.setText(text + "\n" + "S'ha trobat el beacon amb id: " + idTrobada);
        if(lInscripcions.size() > 0) {
            for (InscripcionsInscripcion ii : lInscripcions){
                if(ii.getBeacons() != null) {
                    if(ii.getBeacons().getBea_code().equals(idTrobada)) {
                        String json = formarJson(ii.getInsId().toString(), timestamp);

                        APIManager.getInstance().postInscripcio(json, new Callback<InscripcionsExample>() {
                            @Override
                            public void onResponse(Call<InscripcionsExample> call, Response<InscripcionsExample> response) {
                                Log.d("XXX", "Inscripció pujada");
                            }

                            @Override
                            public void onFailure(Call<InscripcionsExample> call, Throwable t) {
                                Log.d("ERROR", "Inscripció pujada");
                            }
                        });
                    }
                }

            }
        }

    }

    private String formarJson(String inscripcioId, Timestamp timestamp) {
        JSONObject json = new JSONObject();
        try {
            json.put("reg_ins_id", inscripcioId);
            json.put("reg_chk_id", checkpoint_id);
            json.put("reg_temps", timestamp);

            return json.toString();

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