package com.togapp.sampledrawer;

import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, FragmentCallback{

    //private AppBarConfiguration mAppBarConfiguration;
    Fragment1 fragment1;
    Fragment2 fragment2;
    Fragment3 fragment3;

    DrawerLayout drawer;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();

        getSupportFragmentManager().beginTransaction().add(R.id.container, fragment1).commit();

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
       /*mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawerLayout)
                .build();*/
       //NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
       //NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
       //NavigationUI.setupWithNavController(navigationView, navController);
    }

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
    */
    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item){
        int id = item.getItemId();

        if (id == R.id.menu1){
            Toast.makeText(this, "첫 번째 메뉴 선택됨", Toast.LENGTH_LONG).show();
            onFragmentSelected(0,null);
        } else if (id == R.id.menu2){
            Toast.makeText(this, "두 번째 메뉴 선택됨", Toast.LENGTH_LONG).show();
            onFragmentSelected(1,null);
        } else if (id == R.id.menu3){
            Toast.makeText(this, "세 번째 메뉴 선택됨", Toast.LENGTH_LONG).show();
            onFragmentSelected(2,null);
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentSelected(int position, Bundle bundle){
        Fragment curFragment = null;

        if (position == 0){
            curFragment = fragment1;
            toolbar.setTitle("첫 번째 화면");
        } else if (position == 1){
            curFragment = fragment2;
            toolbar.setTitle("두 번째 화면");
        } else if (position == 2){
            curFragment = fragment3;
            toolbar.setTitle("세 번째 화면");
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.container, curFragment).commit();

    }


}