package semanaeng.studio.com.semanadeengenhariamaua.activity;


import android.content.Context;

import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.Button;

import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;


import java.util.ArrayList;


import semanaeng.studio.com.semanadeengenhariamaua.R;
import semanaeng.studio.com.semanadeengenhariamaua.funcoes.GET;
import semanaeng.studio.com.semanadeengenhariamaua.funcoes.json;
import semanaeng.studio.com.semanadeengenhariamaua.modelo.patrocinador;

public class patrocinadores extends AppCompatActivity {

    private Button back;
    private ProgressBar mProgress;
    private ListView lista;
    private ArrayList<patrocinador> p = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patrocinadores);
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

        new acessPatro().execute("https://ancient-bastion-16380.herokuapp.com/api.php?table=patrocinador");

    }

    public class acessPatro extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... params) {

            String c = GET.GET(params[0]);
            json j = new json(c);
            if (c != null) {
                p = j.listarPatrocinadores();

            }else{
                p=null;
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            mProgress.setVisibility(View.VISIBLE);
            lista = (ListView) findViewById(R.id.listaPatrocinadores);
            TextView nc = (TextView) findViewById(R.id.text_noconn);
            if (p!= null){
                MyAdapter adapter = new MyAdapter(patrocinadores.this,p);
                lista.setAdapter(adapter);
            }else{
                Log.i("teste", "sem conect");
                nc.setText("Erro na Conexão com Servidor");
            }

            mProgress.setVisibility(View.GONE);
        }

    }

    public class MyAdapter extends ArrayAdapter {
        Context context;

        ArrayList<patrocinador> p;

        public MyAdapter(Context c,ArrayList<patrocinador> p)
        {
            super(c,R.layout.view,p);
            this.context=c;
            this.p=p;

        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            if (convertView == null) {

                convertView = inflater.inflate(R.layout.view_patrocinadores,parent,false);

            }

            holderPatrocinadores holder = new holderPatrocinadores(convertView);
            convertView.setTag(holder);
            if(p.get(position).getTier() == 1)
            {
                Glide.with(context).load(p.get(position).getImagem()).into(holder.Image);
                holder.Nome.setText(p.get(position).getNome());
            }else if(p.get(position).getTier() == 2){

                Glide.with(context).load(p.get(position).getImagem()).into(holder.Image);
                holder.Nome.setText(p.get(position).getNome());
            }else if(p.get(position).getTier() == 3){

                Glide.with(context).load(p.get(position).getImagem()).into(holder.Image);
                holder.Nome.setText(p.get(position).getNome());
            }else if(p.get(position).getTier() == 4){

                Glide.with(context).load(p.get(position).getImagem()).into(holder.Image);
                holder.Nome.setText(p.get(position).getNome());
            }else if(p.get(position).getTier() == 5){

                Glide.with(context).load(p.get(position).getImagem()).into(holder.Image);
                holder.Nome.setText(p.get(position).getNome());
            }

            /*Picasso.with(context).load(p.get(position).getImagem()).into(holder.Image, new Callback() {
                @Override
                public void onSuccess() {
                    Log.d("testeS",A.get(position));
                }

                @Override
                public void onError() {
                    Log.d("testeError",A.get(position));
                }
            });*/


            return convertView;
        }
    }
}
