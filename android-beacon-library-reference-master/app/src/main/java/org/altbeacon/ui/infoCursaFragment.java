package org.altbeacon.ui;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nostra13.universalimageloader.core.ImageLoader;

import org.altbeacon.adapters.CategoriesAdapter;
import org.altbeacon.adapters.CheckpointsAdapter;
import org.altbeacon.adapters.CircuitsAdapter;
import org.altbeacon.beaconreference.R;
import org.altbeacon.beaconreference.databinding.FragmentInfoCursaBinding;
import org.altbeacon.models.Category;
import org.altbeacon.models.Checkpoint;
import org.altbeacon.models.Circuit;
import org.altbeacon.models.Cursa;
import org.altbeacon.selectedListeners.ICategoriaSelectedListener;
import org.altbeacon.selectedListeners.ICheckpointSelectedListener;
import org.altbeacon.selectedListeners.ICircuitSelectedListener;

import java.util.ArrayList;
import java.util.List;


public class infoCursaFragment
        extends Fragment
        implements ICircuitSelectedListener, ICategoriaSelectedListener, ICheckpointSelectedListener {

    private static final String CLAUCURSA = "Cursa";
    private static final String CLAUCIRCUIT = "Circuit";
    private static final String CLAUCATEGORIA = "Categoria";
    private static final String CLAUCHECKPOINT = "Checkpoint";
    private Cursa cursa;
    private FragmentInfoCursaBinding mBinding;
    private CircuitsAdapter mCircuitsAdapter;
    private CategoriesAdapter mCategoriesAdapter;
    private CheckpointsAdapter mCheckpointsAdapter;

    private Context mContext;

    private List<Circuit> lCircuits = new ArrayList<>();
    private List<Category> lCats = new ArrayList<>();
    private List<Checkpoint> lCheckpoints = new ArrayList<>();

    private infoCursaFragment thisFragment = this;


    private Circuit circuitSeleccionat = null;
    private Category categoriaSeleccionada =  null;
    private Checkpoint checkpointSeleccionat =  null;


    public infoCursaFragment() {
        // Required empty public constructor
    }
    public static infoCursaFragment newInstance(String param1, String param2) {
        infoCursaFragment fragment = new infoCursaFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            cursa = (Cursa) bundle.getSerializable(CLAUCURSA);

            lCircuits = cursa.getCircuits();
        }
    }

    private void mostrarDades() {
        String imgUrl = cursa.getCurFoto();
        ImageLoader.getInstance().displayImage(imgUrl, mBinding.imvFoto);
        mBinding.txvNom.setText(cursa.getCurNom());
        mBinding.txvLloc.setText(cursa.getCurLloc());
//        mBinding.txvDataInici.setText(cursa.getCurDataInici());
//        mBinding.txvDataFi.setText(cursa.getCurDataFi());
//        mBinding.txvLink.setText(cursa.getCurWeb());

        mBinding.txvTitolCheckpoints.setVisibility(View.INVISIBLE);
        mBinding.txvTitolCategories.setVisibility(View.INVISIBLE);
        mBinding.txvTitolCircuits.setVisibility(View.INVISIBLE);
        mBinding.rcvCheckpoints.setVisibility(View.INVISIBLE);
        mBinding.rcvCategories.setVisibility(View.INVISIBLE);
        mBinding.rcvCircuits.setVisibility(View.INVISIBLE);


        mBinding.btnParticipar.setOnClickListener(view -> {
            funcBtnParticipar();
        });
    }

    private void funcBtnParticipar() {
        if ((circuitSeleccionat != null) && (categoriaSeleccionada != null) && (checkpointSeleccionat != null)) {
            NavController nav = NavHostFragment.findNavController(thisFragment);
            Bundle bundle = new Bundle();

            // Ha de ser la id
            bundle.putString(CLAUCATEGORIA, categoriaSeleccionada.getCategoria().getCatId().toString());
            bundle.putString(CLAUCIRCUIT, circuitSeleccionat.getCirId().toString());
            bundle.putString(CLAUCURSA, cursa.getCurId().toString());
            bundle.putString(CLAUCHECKPOINT, checkpointSeleccionat.getChkId().toString());

            nav.navigate(R.id.action_infoCursaFragment_to_beaconFragment, bundle);
        } else {
            mostrar_circuits();
            mBinding.btnParticipar.setEnabled(false);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentInfoCursaBinding.inflate(getLayoutInflater());

        mContext = this.getContext();
//
//        Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
//        if (toolbar != null) {
//            ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
//            if (((AppCompatActivity) getActivity()).getSupportActionBar() != null) {
//                ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Informació de la cursa");
//                ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//                toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        NavController nav = NavHostFragment.findNavController(thisFragment);
//                        nav.navigate(R.id.action_infoCursaFragment_to_navigation_home);
//                    }
//                });
//            }
//        }
        mostrarDades();



        return mBinding.getRoot();
    }

    /*
        Funcions per mostrar les dades dels circuits i les categories
     */

    private void mostrar_circuits() {
        mBinding.txvTitolCircuits.setVisibility(View.VISIBLE);
        mBinding.rcvCircuits.setVisibility(View.VISIBLE);

        mBinding.rcvCircuits.setLayoutManager(new LinearLayoutManager(infoCursaFragment.this.getContext(), LinearLayoutManager.VERTICAL, false));
        mBinding.rcvCircuits.setHasFixedSize(true);
        mCircuitsAdapter = new CircuitsAdapter(mContext, lCircuits, thisFragment);
        mBinding.rcvCircuits.setAdapter(mCircuitsAdapter);
    }


    private void mostrarBoto() {
        if ((circuitSeleccionat != null) && (categoriaSeleccionada != null) && (checkpointSeleccionat != null)) {
            mBinding.btnParticipar.setEnabled(true);
        }
    }

    @Override
    public void onCategoriaSelected(Category c) {
        categoriaSeleccionada = c;


        mostrarBoto();
    }

    private void mostrarCategories() {
        mBinding.txvTitolCategories.setVisibility(View.VISIBLE);
        mBinding.rcvCategories.setVisibility(View.VISIBLE);

        lCats = circuitSeleccionat.getCategories();

        mBinding.rcvCategories.setLayoutManager(new LinearLayoutManager(infoCursaFragment.this.getContext(), LinearLayoutManager.VERTICAL, false));
        mBinding.rcvCategories.setHasFixedSize(true);
        mCategoriesAdapter = new CategoriesAdapter(mContext, lCats, thisFragment);
        mBinding.rcvCategories.setAdapter(mCategoriesAdapter);
    }


    @Override
    public void onCircuitSelected(Circuit c) {
        circuitSeleccionat = c;

        mostrarCheckpoints();
        mostrarCategories();
        mostrarBoto();
    }

    private void mostrarCheckpoints() {
        lCheckpoints = circuitSeleccionat.getCheckpoints();

        mBinding.txvTitolCheckpoints.setVisibility(View.VISIBLE);
        mBinding.rcvCheckpoints.setVisibility(View.VISIBLE);

        mBinding.rcvCheckpoints.setLayoutManager(new LinearLayoutManager(infoCursaFragment.this.getContext(), LinearLayoutManager.VERTICAL, false));
        mBinding.rcvCheckpoints.setHasFixedSize(true);
        mCheckpointsAdapter = new CheckpointsAdapter(mContext, lCheckpoints, thisFragment);
        mBinding.rcvCheckpoints.setAdapter(mCheckpointsAdapter);
    }

    @Override
    public void onCheckpointSelected(Checkpoint c) {
        checkpointSeleccionat = c;

        mostrarBoto();
    }
}