package semanaeng.studio.com.semanadeengenhariamaua.sidebar;

import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import semanaeng.studio.com.semanadeengenhariamaua.R;
import semanaeng.studio.com.semanadeengenhariamaua.funcoes.GET;
import semanaeng.studio.com.semanadeengenhariamaua.funcoes.json;

public class tema extends AppCompatActivity {
    private Button back;
    private ArrayList<String> jsonTema = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tema);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        TextView titulo = (TextView) findViewById(R.id.text_semana);


        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/agency_fb.ttf");
        titulo.setTypeface( font );

        back = (Button) findViewById(R.id.button_back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        new acessDbTema().execute("https://ancient-bastion-16380.herokuapp.com/api.php?table=tema");

    }
    public class acessDbTema extends AsyncTask<String, Void,String> {


        @Override
        protected String doInBackground(String... params) {
            String c = GET.GET(params[0]);

            json j = new json(c);
            if (c != null) {
                jsonTema = j.jsonTema();

            }
            else {
                jsonTema = null;
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            TextView temaText = (TextView) findViewById(R.id.text_tema);

            temaText.setText(jsonTema.get(0));

        }
    }
}
