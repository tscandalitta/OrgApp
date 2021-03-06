package com.example.proyectoandroidtdp.Main;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.proyectoandroidtdp.CRC.GenerarCRCFragment;
import com.example.proyectoandroidtdp.CRC.VerificarCRCFragment;
import com.example.proyectoandroidtdp.CambioDeBase.CambioDeBaseFragment;
import com.example.proyectoandroidtdp.Hamming.GenerarHammingFragment;
import com.example.proyectoandroidtdp.Hamming.VerificarHammingFragment;
import com.example.proyectoandroidtdp.GeneradorRangos.RangosFragment;
import com.example.proyectoandroidtdp.OperacionesAritmeticas.OperacionAritmeticaFragment;
import com.example.proyectoandroidtdp.R;
import com.example.proyectoandroidtdp.redondeo.RedondeoFragment;
import com.example.proyectoandroidtdp.SistemasDeRepresentacion.SistemasDeRepresentacionFragment;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Context context;

    private static final String PREFS_NAME = "prefs";
    private static final String PREF_DARK_THEME = "dark_theme";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        boolean useDarkTheme = preferences.getBoolean(PREF_DARK_THEME, false);

        if(useDarkTheme) {
            setTheme(R.style.AppTheme_Dark_NoActionBar);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(this);

        FragmentManager fragmentmanager = getSupportFragmentManager();
        fragmentmanager.beginTransaction().replace(R.id.contenedor,new INICIOFragment()).commit();

        FloatingActionButton botonInfo = findViewById(R.id.botonInfo);
        context = this;
        botonInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cambioDeActivity = new Intent ( context, ActivityAyuda.class);
                startActivity(cambioDeActivity);
            }
        });
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {
            case R.id.action_settings:
                startActivity(new Intent(this, SettingsActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        FragmentManager fragmentmanager = getSupportFragmentManager();

        Fragment fragmentNuevo = null;
        switch (id){
            case R.id.nav_sistemas_de_representacion:
                getSupportActionBar().setTitle(R.string.sistemas_de_representacion);
                fragmentNuevo = new SistemasDeRepresentacionFragment();
                break;
            case R.id.nav_cambio_de_base:
                getSupportActionBar().setTitle(R.string.cambio_de_base);
                fragmentNuevo = new CambioDeBaseFragment();
                break;
            case R.id.nav_generar_crc:
                getSupportActionBar().setTitle(R.string.generar_crc);
                fragmentNuevo = new GenerarCRCFragment();
                break;
            case R.id.nav_verificar_crc:
                getSupportActionBar().setTitle(R.string.verificar_crc);
                fragmentNuevo = new VerificarCRCFragment();
                break;
            case R.id.nav_generar_hamming:
                getSupportActionBar().setTitle("Generar Hamming");
                fragmentNuevo = new GenerarHammingFragment();
                break;
            case R.id.nav_verificar_hamming:
                getSupportActionBar().setTitle("Verificar Hamming");
                fragmentNuevo = new VerificarHammingFragment();
                break;
            case R.id.nav_inicio:
                getSupportActionBar().setTitle(R.string.app_name);
                fragmentNuevo = new INICIOFragment();
                break;
            case R.id.nav_operaciones_Arimetica:
                getSupportActionBar().setTitle(R.string.operaciones_aritmeticas);
                fragmentNuevo = new OperacionAritmeticaFragment();
                break;
            case R.id.nav_redondeo:
                getSupportActionBar().setTitle(R.string.redondeo);
                fragmentNuevo = new RedondeoFragment();
                break;
            case R.id.nav_rangos:
                fragmentNuevo = new RangosFragment();
                getSupportActionBar().setTitle("Rangos");
                break;
        }

        fragmentmanager.beginTransaction().replace(R.id.contenedor,fragmentNuevo).commit();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
