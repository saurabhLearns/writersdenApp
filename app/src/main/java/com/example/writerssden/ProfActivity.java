package com.example.writerssden;

import android.content.Intent;
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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Button signout;
    TextView user;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    TextView header_user;
    MenuItem first;
    MenuItem second;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prof);
        Toolbar toolbar = (Toolbar) findViewById(R.id.proftoolbar);
        setSupportActionBar(toolbar);
        //toolbar.setTitle("Profile");
        firebaseAuth=FirebaseAuth.getInstance();

        user=findViewById(R.id.profileWelcome);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View header=navigationView.getHeaderView(0);
        Menu menu = navigationView.getMenu();
        header_user=header.findViewById(R.id.prof_header_user);
        first=menu.findItem(R.id.nav_camera);
        second=menu.findItem(R.id.new_account);



        firebaseUser=firebaseAuth.getCurrentUser();
        header_user.setText(firebaseUser.getEmail());
        first.setTitle("Home");
        first.setIcon(R.drawable.ic_home_black_24dp);
        second.setTitle("Create New Post");
        second.setIcon(R.drawable.ic_menu_send);
        user.setText(firebaseUser.getEmail());
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            startActivity(new Intent(ProfActivity.this, MainActivity.class));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.prof, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();
            if (id == R.id.nav_camera) {
                Intent loginpage = new Intent(ProfActivity.this, MainActivity.class);
                ProfActivity.this.startActivity(loginpage);

            } else if (id == R.id.new_account) {
                Intent regpage = new Intent(ProfActivity.this, SubmitActivity.class);
                ProfActivity.this.startActivity(regpage);
            }
            else if (id == R.id.logout){
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(ProfActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                Toast.makeText(ProfActivity.this, "Logged out!", Toast.LENGTH_LONG).show();
            }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
