package semanaeng.studio.com.semanadeengenhariamaua.sidebar;


import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.preference.PreferenceGroup;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import semanaeng.studio.com.semanadeengenhariamaua.R;
import semanaeng.studio.com.semanadeengenhariamaua.funcoes.GET;
import semanaeng.studio.com.semanadeengenhariamaua.funcoes.json;

public class recrutamento extends AppCompatActivity {
    private ProgressBar progressBar ;
    private Button back;
    private ArrayList<String> recStatus = new ArrayList<>();
    private ArrayList<String> recData = new ArrayList<>();
    private Button ps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recrutamento);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        TextView titulo = (TextView) findViewById(R.id.text_semana);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/agency_fb.ttf");
        titulo.setTypeface( font );

        back = (Button) findViewById(R.id.button_back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }

        });

        ps = (Button) findViewById(R.id.button_rec_site);
        ps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://semanamaua.com.br/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        new acessDb().execute("https://ancient-bastion-16380.herokuapp.com/api.php?table=recrutamento");


    }


    public class acessDb extends AsyncTask<String, Void,String>{
        String periodo = "Processo Seletivo de ";

        @Override
        protected String doInBackground(String... params) {
            String c = GET.GET(params[0]);

            json j = new json(c);
            if (c != null) {
                recStatus = j.jsonRecrutamentoStatus();
                recData = j.jsonRecrutamentoData();
            }
            else {
                recStatus = null;
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            progressBar.setVisibility(View.VISIBLE);
            TextView status = (TextView) findViewById(R.id.text_rec1);
            TextView data = (TextView) findViewById(R.id.text_recData);

            status.setText(recStatus.get(0));
            data.setText(periodo+recData.get(1));
            progressBar.setVisibility(View.GONE);
        }
    }

}
