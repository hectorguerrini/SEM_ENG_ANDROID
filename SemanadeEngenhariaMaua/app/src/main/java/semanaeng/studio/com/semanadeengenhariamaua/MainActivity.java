package semanaeng.studio.com.semanadeengenhariamaua;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuItemWrapperICS;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

@SuppressWarnings("ALL")
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Button diurno;
    private Button noturno;
    private Button palestra;
    private Button cronograma;
    private Button patrocinadores;
    private Button sobre;
    private DrawerLayout nDrawer;
    private ActionBarDrawerToggle nActionBarDrawerToggle;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barra_de_navegacao);

        toolbar = (Toolbar) findViewById(R.id.toolbarPrincipal);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        diurno = (Button) findViewById(R.id.button_di);
        noturno = (Button) findViewById(R.id.button_no);
        palestra = (Button) findViewById(R.id.button_palestra);
        cronograma = (Button) findViewById(R.id.button_cronograma);
        patrocinadores = (Button) findViewById(R.id.button_patro);
        sobre = (Button) findViewById(R.id.button_sobre);
        TextView titulo = (TextView) findViewById(R.id.text_semana);
        nDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/agency_fb.ttf");
        titulo.setTypeface( font );
        Typeface font2 = Typeface.createFromAsset(getAssets(), "fonts/cooperlight.ttf");
        diurno.setTypeface( font2 );
        noturno.setTypeface( font2 );
        palestra.setTypeface( font2 );
        cronograma.setTypeface( font2 );
        patrocinadores.setTypeface( font2 );
        sobre.setTypeface( font2 );

        nActionBarDrawerToggle = new ActionBarDrawerToggle(this, nDrawer,toolbar, R.string.open,R.string.close);

        nDrawer.setDrawerListener(nActionBarDrawerToggle);
        nActionBarDrawerToggle.syncState();


        diurno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, diurno.class));
            }
        });
        noturno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, noturno.class));
            }
        });
        palestra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, palestra.class));
            }
        });

        cronograma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, cronograma.class));
            }
        });
        patrocinadores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, patrocinadores.class));
            }
        });
        sobre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, sobre.class));
            }
        });


        navigationView.setNavigationItemSelectedListener(this);

    }
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_notificacao) {
            // Handle the camera action
        } else if (id == R.id.nav_mapa) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
