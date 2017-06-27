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
import semanaeng.studio.com.semanadeengenhariamaua.modelo.mCurso;



public class diurno extends AppCompatActivity{

    private Button back;
    private ArrayList<mCurso> Cursos = new ArrayList<>();
    private ProgressBar mProgress;
    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diurno);

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



        new acessoRest().execute("https://ancient-bastion-16380.herokuapp.com/api.php?table=curso&&periodo=diurno");




    }

    public class acessoRest extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... params) {

            String c = GET.GET(params[0]);
            json j = new json(c);
            if (c != null) {
                Cursos = j.jsonCurso();
            }
            else {
                Cursos = null;
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            mProgress.setVisibility(View.VISIBLE);
            lista = (ListView) findViewById(R.id.listaDiurno);
            TextView nc = (TextView) findViewById(R.id.text_noconn);
            if (Cursos != null){
                MyAdapter adapter = new MyAdapter(diurno.this,Cursos);
                lista.setAdapter(adapter);
                lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String top = Cursos.get(position).getCodigo()+" - " + Cursos.get(position).getEmpresa()+": "+
                                Cursos.get(position).getNome()+"\nSala: "+Cursos.get(position).getSala();

                        Intent intent = new Intent(diurno.this,detalhes.class);
                        intent.putExtra("dadosT",top);
                        intent.putExtra("dadosD",Cursos.get(position).getDescricao());
                        intent.putExtra("Imagem",Cursos.get(position).getImagem());
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

        ArrayList<mCurso> curso;



        public MyAdapter(Context c, ArrayList<mCurso> curso)
        {
            super(c,R.layout.view,curso);
            this.context=c;
            this.curso=curso;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            if (convertView == null) {

                convertView = inflater.inflate(R.layout.view,parent,false);

            }

            myHolder holder = new myHolder(convertView);
            convertView.setTag(holder);

            if (!curso.get(position).getImagem().equals("null")) {
                Picasso.with(context).load(curso.get(position).getImagem()).into(holder.Image, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {
                        Picasso.with(context).load(R.drawable.logosemana3).into(holder.Image);
                    }
                });
            }else{
                Picasso.with(context).load(R.drawable.logosemana3).into(holder.Image);
            }

            //Glide.with(context).load(itemsIm.get(position)).into(holder.Image);
            holder.Empresa.setText(curso.get(position).getEmpresa());
            holder.Curso.setText(curso.get(position).getNome());

            return convertView;
        }

    }





}
