package semanaeng.studio.com.semanadeengenhariamaua.activity;

import android.content.Context;

import android.content.Intent;
import android.content.pm.ActivityInfo;

import android.graphics.Typeface;
import android.os.AsyncTask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import semanaeng.studio.com.semanadeengenhariamaua.funcoes.GET;
import semanaeng.studio.com.semanadeengenhariamaua.R;
import semanaeng.studio.com.semanadeengenhariamaua.funcoes.json;

import semanaeng.studio.com.semanadeengenhariamaua.modelo.mVisita;


public class visita extends AppCompatActivity{

    private Button back;
    private ArrayList<mVisita> visita = new ArrayList<>();
    private ProgressBar mProgress;
    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visita);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mProgress = (ProgressBar) findViewById(R.id.progressBar);
        TextView titulo = (TextView) findViewById(R.id.text_semana);

        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/agency_fb.ttf");
        titulo.setTypeface(font);


        back = (Button) findViewById(R.id.button_back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();

            }
        });


        new listarVisitas().execute("https://ancient-bastion-16380.herokuapp.com/api.php?table=visita");

    }

    public class listarVisitas extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... params) {

            String c = GET.GET(params[0]);
            json j = new json(c);
            if (c != null) {
                visita = j.jsonVisita();
            }
            else {
                visita = null;
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            mProgress.setVisibility(View.VISIBLE);
            lista = (ListView) findViewById(R.id.listaVisita);
            TextView nc = (TextView) findViewById(R.id.text_noconn);
            if (visita != null){
                MyAdapter adapter = new MyAdapter(visita.this,visita);
                lista.setAdapter(adapter);
                lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String top = visita.get(position).getCodigo()+" - " + visita.get(position).getEmpresa()+
                                "\nLocal: "+visita.get(position).getLocal()+
                                "\nData: "+visita.get(position).getData()+" Horario: "+
                                visita.get(position).getHoraInicio()+" às "+visita.get(position).getHoraFim();
                        Intent intent = new Intent(visita.this,detalhes.class);
                        intent.putExtra("dadosT",top);
                        intent.putExtra("Imagem",visita.get(position).getImagem());
                        startActivity(intent);
                    }
                });


            }else{
                Log.i("teste", "sem conect");
                nc.setText("Erro na Conexão com Servidor");
            }

            mProgress.setVisibility(View.GONE);


        }


    }

    public class MyAdapter extends ArrayAdapter {
        Context context;

        ArrayList<mVisita> visita;



        public MyAdapter(Context c, ArrayList<mVisita> visita)
        {
            super(c,R.layout.view,visita);
            this.context=c;
            this.visita=visita;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            if (convertView == null) {

                convertView = inflater.inflate(R.layout.view,parent,false);

            }

            myHolder holder = new myHolder(convertView);
            convertView.setTag(holder);

            Picasso.with(context).load(visita.get(position).getImagem()).into(holder.Image, new Callback() {
                @Override
                public void onSuccess() {
                    Log.d("testeS",visita.get(position).getImagem());
                }

                @Override
                public void onError() {
                    Log.d("testeError",visita.get(position).getImagem());
                }
            });

            //Glide.with(context).load(itemsIm.get(position)).into(holder.Image);
            holder.Empresa.setText(visita.get(position).getCodigo()+" - "+visita.get(position).getEmpresa());
            String top = "Local: "+visita.get(position).getLocal()+"\nData: "+visita.get(position).getData()+" Horario: "+
                    visita.get(position).getHoraInicio()+" às "+visita.get(position).getHoraFim();
            holder.Curso.setText(top);

            return convertView;
        }

    }





}
