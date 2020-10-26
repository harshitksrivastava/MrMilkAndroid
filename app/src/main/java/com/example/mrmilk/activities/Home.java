package com.example.mrmilk.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.mrmilk.R;
import com.example.mrmilk.activities.Fragments.HomeFragment;
import com.example.mrmilk.activities.Fragments.OrderFragment;
import com.example.mrmilk.activities.Fragments.SupportFragment;
import com.example.mrmilk.activities.Fragments.VacationFragment;
import com.example.mrmilk.activities.Fragments.WalletFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle mDrawerToggle;
    private FirebaseAuth mAuth;
    private FirebaseUser mCurrentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        toolbar  = findViewById(R.id.toolbar);
        toolbar.setTitleTextAppearance(this,R.style.cursiveFontFamily);
        setSupportActionBar(toolbar);

        mAuth = FirebaseAuth.getInstance();
        mCurrentUser = mAuth.getCurrentUser();


        mDrawerLayout = findViewById(R.id.drawerLayout);
        mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout,toolbar, R.string.open,R.string.close);


        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setItemIconTintList(null);
        showFragments(new HomeFragment());

    }


    @Override
    protected void onStart() {
        super.onStart();
        if(mCurrentUser == null){
            Intent loginIntent = new Intent(Home.this,MainActivity.class);
            loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(loginIntent);
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.wallet){
            showFragments(new WalletFragment());
        }

    return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
       int id = item.getItemId();

       if(id==R.id.my_subscription) {
           showFragments(new HomeFragment());
       }
       else if(id == R.id.my_orders){
           showFragments(new VacationFragment());
       }
       else if(id == R.id.my_wallet){
           showFragments(new OrderFragment());
       }
       else if(id == R.id.help_and_support){
           showFragments(new WalletFragment());
       }
       else if(id == R.id.logout){
           mAuth.signOut();
           Intent loginIntent = new Intent(Home.this,MainActivity.class);
           startActivity(loginIntent);
           finish();
       }

       mDrawerLayout.closeDrawer(GravityCompat.START);
       return true;

    }


    private void showFragments(Fragment fragment){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame_layout, fragment);
        ft.commit();

    }

    @Override
    public void onBackPressed() {

        if(mDrawerLayout.isDrawerOpen(GravityCompat.START)){
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }

    }
}
