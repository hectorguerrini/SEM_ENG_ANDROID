package semanaeng.studio.com.semanadeengenhariamaua;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
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

    private ImageButton back;
    Context context;
    public ArrayList<String> items = new ArrayList<>();
    String curso = "";
    public diurno(){

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diurno);

        ListView lista = (ListView) findViewById(R.id.listaDiurno);
        TextView titulo = (TextView) findViewById(R.id.text_semana);

        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/agency_fb.ttf");
        titulo.setTypeface(font);

        context = this;

        back = (ImageButton) findViewById(R.id.button_back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(diurno.this, MainActivity.class));
            }
        });


        try {
            String c = new acessoRest().execute().get();
            Log.i("teste",c);
            JSONObject jsonObject = new JSONObject(c);
            curso = jsonObject.getString("codigo") + " ";

            curso+= jsonObject.getString("empresa") + " ";

            curso+= jsonObject.getString("nome") + " ";

            curso+= jsonObject.getString("sala");
            items.add(curso);
            Log.i("teste",items.get(0));
        } catch (InterruptedException e){
            e.printStackTrace();
        } catch (ExecutionException e){
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        MyAdapter adapter = new MyAdapter(this,items);
        lista.setAdapter(adapter);

    }




    class MyAdapter extends ArrayAdapter {
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
