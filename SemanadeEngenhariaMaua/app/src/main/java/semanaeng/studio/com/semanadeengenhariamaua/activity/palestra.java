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


import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import semanaeng.studio.com.semanadeengenhariamaua.R;
import semanaeng.studio.com.semanadeengenhariamaua.funcoes.GET;
import semanaeng.studio.com.semanadeengenhariamaua.funcoes.json;
import semanaeng.studio.com.semanadeengenhariamaua.modelo.mPalestra;

public class palestra extends AppCompatActivity {

    private Button back;
    private ArrayList<mPalestra> Palestras = new ArrayList<>();
    private ProgressBar mProgress;
    private ListView lista;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palestra);

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

        new acessDBP().execute("https://ancient-bastion-16380.herokuapp.com/api.php?table=palestra");

    }

    public class acessDBP extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... params) {
            String c = GET.GET(params[0]);
            json j = new json(c);
            if (c != null) {
                Palestras = j.jsonPalestra();
            }
            else {
                Palestras = null;
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            mProgress.setVisibility(View.VISIBLE);
            lista = (ListView) findViewById(R.id.listaPalestra);
            TextView nc = (TextView) findViewById(R.id.text_noconn);
            if (Palestras != null){
                MyAdapter adapter = new MyAdapter(palestra.this,Palestras);
                lista.setAdapter(adapter);
                lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String top = Palestras.get(position).getCodigo()+" - " + Palestras.get(position).getEmpresa()+": "+
                                Palestras.get(position).getNome()+"\nAuditório: "+Palestras.get(position).getSala()+
                                "\nData: "+Palestras.get(position).getData()+" Hora: "+Palestras.get(position).getHora();
                        Intent intent = new Intent(palestra.this,detalhes.class);
                        intent.putExtra("dadosT",top);
                        intent.putExtra("dadosD",Palestras.get(position).getDescricao());
                        intent.putExtra("Imagem",Palestras.get(position).getImagem());
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


        ArrayList<mPalestra> palestra;


        public MyAdapter(Context c, ArrayList<mPalestra> palestra)
        {
            super(c,R.layout.view,palestra);
            this.context=c;
            this.palestra=palestra;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            if (convertView == null) {

                convertView = inflater.inflate(R.layout.view,parent,false);

            }

            myHolder holder = new myHolder(convertView);
            convertView.setTag(holder);

            Picasso.with(context).load(palestra.get(position).getImagem()).into(holder.Image, new Callback() {
                @Override
                public void onSuccess() {
                    Log.d("testeS",palestra.get(position).getImagem());
                }

                @Override
                public void onError() {
                    Log.d("testeError",palestra.get(position).getImagem());
                }
            });

            //Glide.with(context).load(image.get(position)).into(holder.Image);
            holder.Empresa.setText(palestra.get(position).getEmpresa());
            holder.Curso.setText(palestra.get(position).getNome()+"\nSala: "+palestra.get(position).getSala());

            return convertView;
        }
    }

}
