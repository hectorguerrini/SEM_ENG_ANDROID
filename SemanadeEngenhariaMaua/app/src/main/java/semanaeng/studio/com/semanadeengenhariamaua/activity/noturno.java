package semanaeng.studio.com.semanadeengenhariamaua.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import semanaeng.studio.com.semanadeengenhariamaua.funcoes.GET;
import semanaeng.studio.com.semanadeengenhariamaua.R;
import semanaeng.studio.com.semanadeengenhariamaua.funcoes.json;

public class noturno extends AppCompatActivity {

    private Button back;
    public ArrayList<String> items = new ArrayList<>();
    private ProgressBar mProgress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noturno);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mProgress = (ProgressBar) findViewById(R.id.progressBar);
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




    }
    public class acessoRest extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... params) {
            String c = GET.GET(params[0]);


            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            mProgress.setVisibility(View.VISIBLE);
            ListView lista = (ListView) findViewById(R.id.listaDiurno);


            MyAdapter adapter = new MyAdapter(noturno.this,items);
            lista.setAdapter(adapter);
            mProgress.setVisibility(View.GONE);
        }


    }


    public class MyAdapter extends ArrayAdapter {
        Context context;
        int[] images = {
                R.drawable.logosemana3,
                R.drawable.logosemana3
        };
        ArrayList<String> mytitles;

        public MyAdapter(Context c, ArrayList<String> titles)
        {
            super(c,R.layout.view,titles);
            this.context=c;
            this.mytitles=titles;

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = inflater.inflate(R.layout.view,parent,false);
            ImageView myImage = (ImageView) row.findViewById(R.id.imagelista);
            TextView myTitle = (TextView) row.findViewById(R.id.textolista);

            myImage.setImageResource(images[0]);
            myTitle.setText(mytitles.get(position));

            return row;
        }
    }
}
