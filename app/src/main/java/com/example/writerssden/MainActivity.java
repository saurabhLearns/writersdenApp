package com.example.writerssden;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    TextView header_user;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    MenuItem first;
    MenuItem second;
    MenuItem logout;
    TextView cardcontent1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        firebaseAuth=FirebaseAuth.getInstance();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View header=navigationView.getHeaderView(0);
        Menu menu = navigationView.getMenu();
        header_user=header.findViewById(R.id.main_header_user);
        first=menu.findItem(R.id.nav_camera);
        second=menu.findItem(R.id.new_account);
        logout=menu.findItem(R.id.mainlogout);
        cardcontent1=findViewById(R.id.cardcontent1);



        if (firebaseAuth.getCurrentUser()==null){
            first.setTitle("Login");
            second.setTitle("Create New Account");
            header_user.setText("login");
            logout.setVisible(false);
        }
        else {
            firebaseUser=firebaseAuth.getCurrentUser();
            header_user.setText(firebaseUser.getEmail());
            first.setTitle("Profile");
            first.setIcon(R.drawable.ic_account_circle_black_24dp);
            second.setTitle("Create New Post");
            second.setIcon(R.drawable.ic_menu_send);
            logout.setVisible(true);
        }
        navigationView.setNavigationItemSelectedListener(this);
        cardcontent1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent article = new Intent(MainActivity.this, ArticleActivity.class);
                MainActivity.this.startActivity(article);
            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (firebaseAuth.getCurrentUser()==null){
            if (id == R.id.nav_camera) {
                Intent loginpage = new Intent(MainActivity.this, LoginActivity.class);
                MainActivity.this.startActivity(loginpage);
            } else if (id == R.id.new_account) {
                Intent regpage = new Intent(MainActivity.this, RegisterActivity.class);
                MainActivity.this.startActivity(regpage);
            }
        }
        else{
            if (id == R.id.nav_camera) {
                Intent loginpage = new Intent(MainActivity.this, ProfActivity.class);
                MainActivity.this.startActivity(loginpage);

            } else if (id == R.id.new_account) {
                Intent regpage = new Intent(MainActivity.this, SubmitActivity.class);
                MainActivity.this.startActivity(regpage);
            }
            else if (id==R.id.mainlogout){
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                Toast.makeText(MainActivity.this, "Logged out!", Toast.LENGTH_LONG).show();

            }
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
