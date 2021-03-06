package semanaeng.studio.com.semanadeengenhariamaua.activity;

import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;


import semanaeng.studio.com.semanadeengenhariamaua.R;

public class detalhes extends AppCompatActivity {
    private Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        TextView titulo = (TextView) findViewById(R.id.text_semana);
        TextView textoTop = (TextView) findViewById(R.id.text_top_detalhe);
        TextView textoDet = (TextView) findViewById(R.id.text_detalhe);
        ImageView imagemview = (ImageView) findViewById(R.id.image_detalhes);
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/agency_fb.ttf");
        titulo.setTypeface( font );

        back = (Button) findViewById(R.id.button_back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            String texto = bundle.getString("dadosT");
            String textod = bundle.getString("dadosD");
            String image = bundle.getString("Imagem");
            if(!image.equals("null")){
                Glide.with(getApplicationContext()).load(image).into(imagemview);
            }else{
                Glide.with(getApplicationContext()).load(R.drawable.logosemana3).into(imagemview);
            }
            textoTop.setText(texto);
            textoDet.setText(textod);

            Log.d("teste",image);
        }



    }
}
