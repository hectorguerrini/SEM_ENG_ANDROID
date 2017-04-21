package semanaeng.studio.com.semanadeengenhariamaua;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.app.ListActivity;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BufferedHeader;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.*;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class diurno extends AppCompatActivity{

    private Button back;
    public ArrayList<String> items = new ArrayList<>();
    private ProgressBar mProgress;
    private int mProgressStatus = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diurno);

        mProgress = (ProgressBar) findViewById(R.id.progressBar);
        TextView titulo = (TextView) findViewById(R.id.text_semana);

        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/agency_fb.ttf");
        titulo.setTypeface(font);


        back = (Button) findViewById(R.id.button_back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(diurno.this, MainActivity.class));
            }
        });



        new acessoRest().execute("http://sample-env-4.drxhpt9fid.us-east-1.elasticbeanstalk.com/webresources/app/curso/listarDiurno");




    }

    public class acessoRest extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... params) {
            String c = GET.GET(params[0]);
            String curso = json.json(c);
            return curso;
        }

        @Override
        protected void onPostExecute(String s) {
            mProgress.setVisibility(View.VISIBLE);
            ListView lista = (ListView) findViewById(R.id.listaDiurno);
            items.add(s);

            MyAdapter adapter = new MyAdapter(diurno.this,items);
            lista.setAdapter(adapter);
            mProgress.setVisibility(View.GONE);
        }


    }


    public class MyAdapter extends ArrayAdapter {
        Context context;
        int[] images = {
                R.drawable.logosemana3
        };
        ArrayList<String> mytitles;

        public MyAdapter(Context c,ArrayList<String> titles)
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

            myImage.setImageResource(images[position]);
            myTitle.setText(mytitles.get(position));

            return row;
        }
    }




}
