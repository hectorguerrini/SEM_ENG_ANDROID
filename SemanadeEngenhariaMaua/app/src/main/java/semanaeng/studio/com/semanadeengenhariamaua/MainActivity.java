package semanaeng.studio.com.semanadeengenhariamaua;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button diurno;
    private Button noturno;
    private Button palestra;
    private Button cronograma;
    private Button patrocinadores;
    private Button sobre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barra_de_navegacao);



        diurno = (Button) findViewById(R.id.button_di);
        noturno = (Button) findViewById(R.id.button_no);
        palestra = (Button) findViewById(R.id.button_palestra);
        cronograma = (Button) findViewById(R.id.button_cronograma);
        patrocinadores = (Button) findViewById(R.id.button_patro);
        sobre = (Button) findViewById(R.id.button_sobre);

        TextView titulo = (TextView) findViewById(R.id.text_semana);


        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/agency_fb.ttf");
        titulo.setTypeface( font );
        Typeface font2 = Typeface.createFromAsset(getAssets(), "fonts/cooperlight.ttf");
        diurno.setTypeface( font2 );
        noturno.setTypeface( font2 );
        palestra.setTypeface( font2 );
        cronograma.setTypeface( font2 );
        patrocinadores.setTypeface( font2 );
        sobre.setTypeface( font2 );

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
    }
}
