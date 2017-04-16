package semanaeng.studio.com.semanadeengenhariamaua;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

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

public class diurno extends AppCompatActivity {

    private ImageButton back;
    private ResultSet rs;
    private String sql;
    private databaseconect db;
    private String teste = "teste 2";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diurno);

        TextView titulo = (TextView) findViewById(R.id.text_semana);
        TextView idbd = (TextView) findViewById(R.id.idCursobd);
        idbd.setText(teste);
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/agency_fb.ttf");
        titulo.setTypeface( font );

        back = (ImageButton) findViewById(R.id.button_back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(diurno.this, MainActivity.class));
            }
        });
        new acessoRest().execute("http://10.10.10.106:8080/WebService/webresources/app/curso/listarrtodos");
    }

    public static String GET(String url){
        InputStream inputStream = null;
        String result = "";
        String line = "";
        StringBuilder builder = new StringBuilder();
        try {
            // create HttpClient
            HttpClient httpclient = new DefaultHttpClient();
            // make GET request to the given URL
            HttpResponse httpResponse = httpclient.execute(new HttpGet(url));
            // receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();
            // convert inputstream to string
            /*if(inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Did not work!";
            */
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            while ((line = reader.readLine()) != null){
                builder.append(line);

            }
            inputStream.close();
            } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());


        }
        return builder.toString();
    }



    private class acessoRest extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            InputStream inputStream = null;
            String result = "";
            String line = "";
            try {
                // create HttpClient
                HttpClient httpclient = new DefaultHttpClient();
                // make GET request to the given URL
                HttpResponse httpResponse = httpclient.execute(new HttpGet(params[0]));
                // receive response as inputStream
                inputStream = httpResponse.getEntity().getContent();
                // convert inputstream to string
            /*if(inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Did not work!";
            */
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                while ((line = reader.readLine()) != null){
                    result = result + line;
                    Log.i("teste", result);
                }
                inputStream.close();
            } catch (Exception e) {
                Log.d("InputStream", e.getLocalizedMessage());


            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            String curso = "";
            try {
                TextView idbd = (TextView) findViewById(R.id.idCursobd);
                JSONObject jsonObject = new JSONObject(result);
                curso+= "Nome do Curso: " + jsonObject.getString("nome") + "\n";
                Log.i("teste",curso);
                curso+= "Nome da Empresa: " + jsonObject.getString("empresa") + "\n";
                Log.i("teste",curso);
                curso+= "Nome do Curso: " + jsonObject.getString("sala") + "\n";
                Log.i("teste",curso);
                idbd.setText(curso.toString());

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }


}
