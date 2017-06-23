package semanaeng.studio.com.semanadeengenhariamaua.sidebar;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import semanaeng.studio.com.semanadeengenhariamaua.R;


import semanaeng.studio.com.semanadeengenhariamaua.funcoes.AppController;
import semanaeng.studio.com.semanadeengenhariamaua.funcoes.GET;

import semanaeng.studio.com.semanadeengenhariamaua.funcoes.json;
import semanaeng.studio.com.semanadeengenhariamaua.modelo.rank;
import semanaeng.studio.com.semanadeengenhariamaua.qrcode;


public class ranking extends AppCompatActivity {
    private Button back;
    private Button pontos;
    private ListView lista;
    private rank meuRank;
    private ProgressDialog pDialog;
    private SharedPreferences sharedPreferences;
    public static final int REQUEST_CODE = 100;
    public static final int PERMISSION_REQUEST = 200;
    private TextView test;
    private TextView myPos;
    private TextView myNome;
    private TextView myPontos;
    private ArrayList<rank> listaRank= new ArrayList<>();
    private String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        TextView titulo = (TextView) findViewById(R.id.text_semana);
        test = (TextView) findViewById(R.id.testeCode);
        myPos = (TextView) findViewById(R.id.text_my_pos);
        myNome = (TextView) findViewById(R.id.text_my_nome);
        myPontos = (TextView) findViewById(R.id.text_my_pontos);
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/agency_fb.ttf");
        titulo.setTypeface( font );

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        pontos = (Button) findViewById(R.id.button_adcionarP);
        back = (Button) findViewById(R.id.button_back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.CAMERA}, PERMISSION_REQUEST);
        }
        pontos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ranking.this, qrcode.class);
                startActivityForResult(intent,REQUEST_CODE);
            }
        });
        sharedPreferences = getSharedPreferences("LoginSemana", Context.MODE_PRIVATE);
        email = sharedPreferences.getString("Email", "");

        new listaRanking().execute("http://ancient-bastion-16380.herokuapp.com/api.php?table=ranking");
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK){
            if(data != null){

                String barcode = data.getExtras().getString("barcode");
                 try {
                     JSONObject jsonObject = new JSONObject(barcode);
                     String teste1 = jsonObject.getString("tabela") +" "+ jsonObject.getString("id");
                     Log.d("teste",teste1);
                     if(jsonObject.getString("tabela").equals("curso")){
                         String teste = jsonObject.getString("id") + " pontos: "+ jsonObject.getString("pontos");
                         Log.d("rank",teste);
                         test.setText(teste);
                         adcionarPontos(email,jsonObject.getString("pontos"),jsonObject.getString("id"),jsonObject.getString("tabela"));
                     }else if(jsonObject.getString("tabela").equals("palestra")){
                         String teste = jsonObject.getString("id") + " pontos: "+ jsonObject.getString("pontose");
                         Log.d("rank",teste);
                         test.setText(teste);
                         adcionarPontos(email,jsonObject.getString("pontos"),jsonObject.getString("id"),jsonObject.getString("tabela"));
                     }
                } catch (JSONException e) {
                    e.printStackTrace();
                }



            }
        }
    }

    public class listaRanking extends AsyncTask<String,Void,String>{
        @Override
        protected String doInBackground(String... params) {
            String c = GET.GET(params[0]);
            json j = new json(c);

            if(c != null){
                listaRank = j.jsonRank(email);
                meuRank = j.meuRank;
            }else{
                listaRank =null;
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {

            lista = (ListView) findViewById(R.id.listaRanking);

            if (listaRank!= null){
                myPos.setText(Integer.toString(meuRank.getPosicao()));
                myNome.setText(meuRank.getNome());
                myPontos.setText(Integer.toString(meuRank.getPontos()));
                MyAdapter adapter = new MyAdapter(ranking.this,listaRank);
                lista.setAdapter(adapter);
            }else{
                Log.i("teste", "sem conect");

            }


        }
    }
    public class MyAdapter extends ArrayAdapter {
        Context context;

        ArrayList<rank> rank;

        public MyAdapter(Context c,ArrayList<rank> rank)
        {
            super(c,R.layout.view,rank);
            this.context=c;
            this.rank=rank;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            if (convertView == null) {

                convertView = inflater.inflate(R.layout.view_ranking,parent,false);

            }

            TextView posicao = (TextView) convertView.findViewById(R.id.rankin_pos);
            TextView nome = (TextView) convertView.findViewById(R.id.rankin_nome);
            TextView pontos = (TextView) convertView.findViewById(R.id.ranking_pontos);

            if(position%2==0){
                posicao.setBackgroundColor(getResources().getColor(R.color.colorRankingA));
                nome.setBackgroundColor(getResources().getColor(R.color.colorRankingA));
                pontos.setBackgroundColor(getResources().getColor(R.color.colorRankingA));
            }else{
                posicao.setBackgroundColor(getResources().getColor(R.color.colorRankingB));
                nome.setBackgroundColor(getResources().getColor(R.color.colorRankingB));
                pontos.setBackgroundColor(getResources().getColor(R.color.colorRankingB));
            }
            posicao.setText(Integer.toString(rank.get(position).getPosicao()));
            nome.setText(rank.get(position).getNome());
            pontos.setText(Integer.toString(rank.get(position).getPontos()));
            return convertView;
        }
    }

    private void adcionarPontos(final String email, final String pontos,final String id,final String tabela) {
        // Tag used to cancel the request
        String tag_string_req = "req_login";

        pDialog.setMessage("Salvando Pontos ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                "http://ancient-bastion-16380.herokuapp.com/qrcode.php", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d("resposta", "Login Response: " + response.toString());
                hideDialog();
                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");

                    // Check for error node in json
                    if (!error) {
                        // user successfully logged in
                        Toast.makeText(getApplicationContext(), "Pontor Adcionados!!", Toast.LENGTH_LONG).show();

                        onResume();
                    } else {
                        // Error in login. Get the error message
                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(getApplicationContext(),
                                errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("resposta", "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);
                params.put("pontos", pontos);
                params.put("id",id);
                params.put("tabela",tabela);

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.getWindow().setBackgroundDrawable(getResources().getDrawable(R.drawable.progressdialog_style));
        pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    @Override
    protected void onResume() {
        super.onResume();
        new listaRanking().execute("http://ancient-bastion-16380.herokuapp.com/api.php?table=ranking");
    }
}
