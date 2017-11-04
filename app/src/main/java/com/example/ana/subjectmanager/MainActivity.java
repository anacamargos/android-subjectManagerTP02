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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FragmentManager fragmentManager;
    Menu menu;
    NavigationView navigationView;
    String nomeMenuArquivo = "menu.txt";

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
                            //menu = navigationView.getMenu();
                            String subject = subjectNameEt.getText().toString();
                            if ( naoTemNoMenu(subject)) {
                                addNoFile(subject);
                                menu.add(subject);
                                readFile();
                                dialog.dismiss();
                                Toast.makeText(MainActivity.this, "Inserido com sucesso!", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(MainActivity.this, "Essa matéria já foi inserida", Toast.LENGTH_SHORT).show();
                            }
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



        readAndUpdate();
        readFile();


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
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void writeFile ( ) {
        try {
            FileOutputStream fos = openFileOutput(nomeMenuArquivo, MODE_PRIVATE);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fos);
            menu = navigationView.getMenu();
            for (int i = 0; i < menu.size(); i ++ ) {
                outputStreamWriter.write(menu.getItem(i).getTitle().toString()+ "\n");
            }
            outputStreamWriter.flush();
            outputStreamWriter.close();
        } catch (Exception e ) {
            e.printStackTrace();
            Toast.makeText(this,"Erro ao salvar arquivo", Toast.LENGTH_SHORT).show();
        }
    }

    public void readFile ( ) {
        File file = getApplicationContext().getFileStreamPath(nomeMenuArquivo);
        String lineFromFile;

        if(file.exists()) {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(openFileInput(nomeMenuArquivo)));
                while ((lineFromFile = reader.readLine()) != null) {
                    //StringTokenizer tokens = new StringTokenizer(lineFromFile);
                    System.out.println(lineFromFile);
                }
            } catch ( IOException e ) {
                e.printStackTrace();
            }
        }
    }

    public void addNoFile ( String adicional ) {
        try {
            FileOutputStream fos = openFileOutput(nomeMenuArquivo, MODE_APPEND);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fos);
            outputStreamWriter.write(adicional + "\n");
            outputStreamWriter.flush();
            outputStreamWriter.close();
        } catch (Exception e ) {
            e.printStackTrace();
            Toast.makeText(this,"Erro ao salvar arquivo", Toast.LENGTH_SHORT).show();
        }
    }

    public void readAndUpdate () {
        File file = getApplicationContext().getFileStreamPath(nomeMenuArquivo);
        String lineFromFile;
        menu = navigationView.getMenu();
        if(file.exists()) {
            try {

                BufferedReader reader = new BufferedReader(new InputStreamReader(openFileInput(nomeMenuArquivo)));
                lineFromFile = reader.readLine();
                while (lineFromFile != null) {
                    menu.add(lineFromFile);
                    lineFromFile = reader.readLine();
                }
            } catch ( IOException e ) {
                e.printStackTrace();
            }
        } else {
            try {
                FileOutputStream fos = openFileOutput(nomeMenuArquivo, MODE_APPEND);
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fos);
                outputStreamWriter.write("AED I\n");
                outputStreamWriter.write("AED II\n");
                outputStreamWriter.write("AED III\n");
                outputStreamWriter.flush();
                outputStreamWriter.close();

                menu.add("AED I");
                menu.add("AED II");
                menu.add("AED III");

            } catch (IOException e ) {
                e.printStackTrace();
            }

        }
    }

    public void onStop () {
        super.onStop();
        readFile();
        //deleteFile();
        writeFile();
    }

    public void deleteFile () {
        File file = new File(getFilesDir(),nomeMenuArquivo);
        if (file.exists()) {
            deleteFile(nomeMenuArquivo);
        }
    }

    public boolean naoTemNoMenu (String adicional) {
        boolean retorno = true;
        menu = navigationView.getMenu();
        if (menu.size() == 0) {
            retorno = true;
        } else {
            for (int i = 0; i < menu.size(); i ++) {
                if ( adicional.equals(menu.getItem(i).getTitle().toString())) {
                    retorno = false;
                }
            }
        }

        return retorno;
    }

}
