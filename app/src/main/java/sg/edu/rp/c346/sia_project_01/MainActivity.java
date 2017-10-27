package sg.edu.rp.c346.sia_project_01;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the toolbar view inside the activity layout
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("DigiStop");

        final FragmentManager fm = getSupportFragmentManager();

        final FragmentTransaction ft = fm.beginTransaction();

        final Fragment f1 = new AttractionsFragment();
        final Fragment f2 = new HotelFragment();
        final Fragment f3 = new homeFragment();


        ft.replace(R.id.frame1, f3);
        ft.commit();

        String [] list = new String[] { "English", "Bahasa Melayu" };

        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_attraction:
                        final FragmentTransaction ft = fm.beginTransaction();
                        ft.replace(R.id.frame1, f1);
                        ft.commit();
                }

                switch (item.getItemId()) {
                    case R.id.action_home:
                        final FragmentTransaction ft = fm.beginTransaction();
                        ft.replace(R.id.frame1, f3);
                        ft.commit();
                }

                switch (item.getItemId()) {
                    case R.id.action_hotel:
                        final FragmentTransaction ft = fm.beginTransaction();
                        ft.replace(R.id.frame1, f2);
                        ft.commit();
                }

                switch (item.getItemId()) {
                    case R.id.action_maps:

                }

                switch (item.getItemId()) {
                    case R.id.action_voucher:

                }



                return true;
            }

        });


    }
}
