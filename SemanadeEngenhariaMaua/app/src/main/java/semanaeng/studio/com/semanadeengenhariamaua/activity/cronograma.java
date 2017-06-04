package semanaeng.studio.com.semanadeengenhariamaua.activity;


import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import semanaeng.studio.com.semanadeengenhariamaua.R;
import uk.co.senab.photoview.PhotoViewAttacher;

public class cronograma extends AppCompatActivity {

    private Button back;
    private ImageView cronograma;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_cronograma);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        TextView titulo = (TextView) findViewById(R.id.text_semana);

        cronograma = (ImageView) findViewById(R.id.image_cronograma);
        PhotoViewAttacher photoViewAttacher = new PhotoViewAttacher(cronograma);
        photoViewAttacher.update();

        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/agency_fb.ttf");
        titulo.setTypeface( font );

        back = (Button) findViewById(R.id.button_back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }
}
