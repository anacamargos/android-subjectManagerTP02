package com.example.ana.subjectmanager;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.SubMenu;
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
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FragmentManager fragmentManager;
    Menu menu;
    LinkedList<String> anotherSubjects = new LinkedList<>(Arrays.asList("Banco de Dados", "Calculo I", "Cálculo II", "Cálculo III", "Algebra Linear", "Matemática Discreta", "Estatística", "AED 1", "AED 3", "LP", "PAA", "IA", "Compiladores", "PID", "Comp. Paralela", "Comp. Gráfica", "Otimização"));
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_addsubject, null);
                final EditText subjectNameEt = (EditText) mView.findViewById(R.id.eTSubjectName);
                Button inserirBtn = (Button) mView.findViewById(R.id.btnInserir);

                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                dialog.show();

                inserirBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!subjectNameEt.getText().toString().isEmpty() ) {
                            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
                            menu = navigationView.getMenu();
                            String subject = subjectNameEt.getText().toString();
                            menu.add(subject);
                            dialog.dismiss();
                            Toast.makeText(MainActivity.this, "Inserido com sucesso!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Favor preencher todos os campos", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                //mBuilder.setView(mView);
                //AlertDialog dialog = mBuilder.create();
                //dialog.show();

                /*NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
                menu = navigationView.getMenu();
                String subject = anotherSubjects.poll();
                menu.add(subject);*/

                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        //.setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
/*
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
*/
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        //int id = item.getItemId();

        fragmentManager = getSupportFragmentManager();
        menu = navigationView.getMenu();
        for ( int i = 0; i < menu.size(); i ++ ) {
            if (item.getTitle().equals(menu.getItem(i).getTitle())) {
                FragmentTransaction ft = fragmentManager.beginTransaction();//.replace(R.id.coordinator_layout, new TransitionFragment()).commit();
                Bundle args = new Bundle();
                args.putString("subjectName",item.getTitle().toString());
                TransitionFragment fragment = new TransitionFragment();
                fragment.setArguments(args);
                ft.replace(R.id.frame_layout, fragment);
                ft.commit();
                break;
            }
        }
        /*
        if (id == R.id.nav_camera) {
            fragmentManager.beginTransaction().replace(R.id.coordinator_layout, new TransitionFragment()).commit();
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        }*/
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
