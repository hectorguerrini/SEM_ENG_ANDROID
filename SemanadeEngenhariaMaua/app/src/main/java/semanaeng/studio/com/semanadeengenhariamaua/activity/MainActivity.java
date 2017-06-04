package semanaeng.studio.com.semanadeengenhariamaua.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import semanaeng.studio.com.semanadeengenhariamaua.R;
import semanaeng.studio.com.semanadeengenhariamaua.funcoes.SessionManager;
import semanaeng.studio.com.semanadeengenhariamaua.sidebar.contato;
import semanaeng.studio.com.semanadeengenhariamaua.sidebar.mapa;
import semanaeng.studio.com.semanadeengenhariamaua.sidebar.ranking;
import semanaeng.studio.com.semanadeengenhariamaua.sidebar.recrutamento;
import semanaeng.studio.com.semanadeengenhariamaua.sidebar.sobre;
import semanaeng.studio.com.semanadeengenhariamaua.sidebar.tema;

@SuppressWarnings("ALL")
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Button diurno;
    private Button noturno;
    private Button palestra;
    private Button cronograma;
    private Button patrocinadores;
    private Button logout;
    private DrawerLayout nDrawer;
    private ActionBarDrawerToggle nActionBarDrawerToggle;
    private Toolbar toolbar;
    private SessionManager session;

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

        TextView titulo = (TextView) findViewById(R.id.text_semana);
        nDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        logout = (Button) findViewById(R.id.button_navbar);

        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/agency_fb.ttf");
        titulo.setTypeface( font );
        Typeface font2 = Typeface.createFromAsset(getAssets(), "fonts/cooperlight.ttf");
        diurno.setTypeface( font2 );
        noturno.setTypeface( font2 );
        palestra.setTypeface( font2 );
        cronograma.setTypeface( font2 );
        patrocinadores.setTypeface( font2 );

        session = new SessionManager(getApplicationContext());
        if (!session.isLoggedIn()) {
            session.setLogin(false);
            startActivity(new Intent(MainActivity.this, semanaeng.studio.com.semanadeengenhariamaua.activity.login.class));
            finish();
        }

        nActionBarDrawerToggle = new ActionBarDrawerToggle(this, nDrawer,toolbar, R.string.open,R.string.close);

        nDrawer.setDrawerListener(nActionBarDrawerToggle);
        nActionBarDrawerToggle.syncState();


        diurno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, semanaeng.studio.com.semanadeengenhariamaua.activity.diurno.class));
            }
        });
        noturno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, semanaeng.studio.com.semanadeengenhariamaua.activity.noturno.class));
            }
        });
        palestra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, semanaeng.studio.com.semanadeengenhariamaua.activity.palestra.class));
            }
        });

        cronograma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, semanaeng.studio.com.semanadeengenhariamaua.activity.cronograma.class));
            }
        });
        patrocinadores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, semanaeng.studio.com.semanadeengenhariamaua.activity.patrocinadores.class));
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session.setLogin(false);
                startActivity(new Intent(MainActivity.this, semanaeng.studio.com.semanadeengenhariamaua.activity.login.class));
                finish();
            }
        });

        navigationView.setNavigationItemSelectedListener(this);

    }
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_recrutamento) {
            startActivity(new Intent(MainActivity.this, recrutamento.class));
        } else if (id == R.id.nav_mapa) {
            startActivity(new Intent(MainActivity.this, mapa.class));
        } else if (id == R.id.nav_tema){
            startActivity(new Intent(MainActivity.this, tema.class));
        } else if (id == R.id.nav_sobre){
            startActivity(new Intent(MainActivity.this, sobre.class));
        } else if (id == R.id.nav_ranking){
            startActivity(new Intent(MainActivity.this, ranking.class));
        } else if (id == R.id.nav_contato){
            startActivity(new Intent(MainActivity.this, contato.class));
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
