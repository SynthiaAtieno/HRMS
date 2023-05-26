package com.example.erpnext.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.erpnext.R;
import com.example.erpnext.fragments.AttendanceFragment;
import com.example.erpnext.fragments.HomeFragment;
import com.example.erpnext.fragments.LeaveFragment;
import com.example.erpnext.fragments.MarkAttendanceFragment;
import com.example.erpnext.fragments.ProfileFragment;
import com.example.erpnext.services.ApiClient;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottom_nav);
        replaceFragment(new HomeFragment());

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.drawer_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        navigationView.setNavigationItemSelectedListener(this);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home:
                    replaceFragment(new HomeFragment());
                    return true;

                case R.id.attendance:
                    replaceFragment(new AttendanceFragment());
                    return true;

                case R.id.leave:
                    replaceFragment(new LeaveFragment());
                    return true;

                case R.id.profile:
                    replaceFragment(new ProfileFragment());
                    return true;

                case R.id.mark_attendance:
                    replaceFragment(new MarkAttendanceFragment());
                    return true;
            }
            return true;
        });


    }


    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }

  /*  @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.bottom_nav, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                replaceFragment(new HomeFragment());
                Toast.makeText(this, "Home is clicked", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.attendance:
                replaceFragment(new AttendanceFragment());
                Toast.makeText(this, "attendance is clicked", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.leave:
                replaceFragment(new LeaveFragment());
                Toast.makeText(this, "Leave is clicked", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.profile:
                replaceFragment(new ProfileFragment());
                Toast.makeText(this, "Profile is clicked", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.mark_attendance:
                replaceFragment(new MarkAttendanceFragment());
                Toast.makeText(this, "Mark attendance is clicked", Toast.LENGTH_SHORT).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
*/


    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return true;
    }
}