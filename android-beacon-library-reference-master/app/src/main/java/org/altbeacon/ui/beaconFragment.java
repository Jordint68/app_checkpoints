package org.altbeacon.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.altbeacon.beaconreference.R;
import org.altbeacon.beaconreference.databinding.FragmentBeaconBinding;
import org.altbeacon.beaconreference.databinding.FragmentInfoCursaBinding;
import org.altbeacon.models.Cursa;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link beaconFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class beaconFragment extends Fragment {
    private static final String CLAUCURSA = "Cursa";
    private static final String CLAUCIRCUIT = "Circuit";
    private static final String CLAUCATEGORIA = "Categoria";
    private static final String CLAUCHECKPOINT = "Checkpoint";

    private FragmentBeaconBinding mBinding;


    private String cursa_id = "";
    private String circuit_id = "";
    private String categoria_id = "";
    private String checkpoint_id = "";


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




        return mBinding.getRoot();
    }
}