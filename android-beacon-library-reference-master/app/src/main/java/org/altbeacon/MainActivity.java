package org.altbeacon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.altbeacon.beaconreference.R;
import org.altbeacon.ui.cursesFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // NAVIGATION
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);


        // UNIVERSAL IMAGE LOADER:
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
        ImageLoader.getInstance().init(config);

//        // Comprueba si el fragment_container ya tiene un fragmento
//        if (savedInstanceState == null) {
//            // Crea una instancia del fragmento de inicio
//            Fragment inicioFragment = new cursesFragment();
//
//            // Inserta el fragmento de inicio
//            FragmentManager fragmentManager = getSupportFragmentManager();
//            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//            fragmentTransaction.add(R.id.nav_host_fragment_activity_main, inicioFragment);
//            fragmentTransaction.commit();
//        }
    }
}