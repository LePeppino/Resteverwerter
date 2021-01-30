package de.prog3.proj2021;

/*
*
* Abschlussprojekt Programmierung III WiSe 2020/21
* Prüfer:   Prof. Dr.-Ing. Rainer Roosmann
*
* Autoren:  Giuseppe Buccellato,    MatNr. 889000
*           Eric Walter,            MatNr. 883921
*
* Titel:    Food Scout - Resteverwerter App
*
* */

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import de.prog3.proj2021.fragments.FragmentFavorites;
import de.prog3.proj2021.fragments.FragmentHome;
import de.prog3.proj2021.fragments.FragmentPicker;
import de.prog3.proj2021.fragments.FragmentShoppingList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initiate BottomNavBar
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            item -> { //OnNavigationItemSelectedListener lambda expression
                Fragment selectedFragment = null;

                switch(item.getItemId()){
                    case R.id.nav_home:
                        selectedFragment = new FragmentHome();
                        break;
                    case R.id.nav_picker:
                        selectedFragment = new FragmentPicker();
                        break;
                    case R.id.nav_shopping_list:
                        selectedFragment = new FragmentShoppingList();
                        break;
                    case R.id.nav_favourites:
                        selectedFragment = new FragmentFavorites();
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        selectedFragment).commit();

                return true;
            };


}