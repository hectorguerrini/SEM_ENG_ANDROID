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

import com.google.gson.Gson;

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
    private ArrayList<String> classeA= new ArrayList<>();
    Gson g = new Gson();
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
            String ob ;
            int tier = 0;
            String c = GET.GET(params[0]);
            json j = new json(c);
            if (c != null) {
                p = j.listarPatrocinadores();

                for (int i = 0; i < p.size(); i++){
                    Log.i("testeDoi",p.get(i).getNome());

                    if(p.get(i).getTier() == 1)
                    {
                        if(tier == 0) {
                            classeA.add("Classe A");
                            tier++;
                        }
                        ob=p.get(i).getNome();
                        classeA.add(ob);
                    }
                    if(p.get(i).getTier() == 2){
                        if(tier == 1) {
                            classeA.add("Classe B");
                            tier++;
                        }
                        ob=p.get(i).getNome();
                        classeA.add(ob);
                    }
                    if(p.get(i).getTier() == 3){
                        if(tier == 2) {
                            classeA.add("Classe C");
                            tier++;
                        }
                        ob=p.get(i).getNome();
                        classeA.add(ob);
                    }
                    if(p.get(i).getTier() == 4){
                        if(tier == 3) {
                            classeA.add("Classe D");
                            tier++;
                        }
                        ob=p.get(i).getNome();
                        classeA.add(ob);
                    }
                    if(p.get(i).getTier() == 5){
                        if(tier == 4) {
                            classeA.add("Classe E");
                            tier++;
                        }
                        ob=p.get(i).getNome();
                        classeA.add(ob);
                    }
                    ob=null;
                    Log.i("teste",g.toJson(classeA));

                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            mProgress.setVisibility(View.VISIBLE);
            lista = (ListView) findViewById(R.id.listaPatrocinadores);
            TextView nc = (TextView) findViewById(R.id.text_noconn);
            if (classeA!= null){
                MyAdapter adapter = new MyAdapter(patrocinadores.this,classeA/*,classeB,classeC,classeD,classeE*/);
                lista.setAdapter(adapter);
            }else{
                Log.i("teste", "sem conect");
                nc.setText("Sem Conexao");
            }

            mProgress.setVisibility(View.GONE);
        }

    }

    public class MyAdapter extends ArrayAdapter {
        Context context;

        ArrayList<String> A;
        ArrayList<String> B;
        ArrayList<String> C;
        ArrayList<String> D;
        ArrayList<String> E;

        public MyAdapter(Context c,ArrayList<String> A/*,ArrayList<String> B,ArrayList<String> C,ArrayList<String> D,ArrayList<String> E*/)
        {
            super(c,R.layout.view,A);
            this.context=c;

            this.A=A;
            /*this.B=B;
            this.C=C;
            this.D=D;
            this.E=E;*/

        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = inflater.inflate(R.layout.view_patrocinadores,parent,false);
            TextView nome = (TextView) row.findViewById(R.id.texto_lista_patrocinadores);
            nome.setText(A.get(position));
                //nome.setText("CLASSE A");

            /*nome.setText("CLASSE B");
            nome.setText(B.get(position));
            nome.setText("CLASSE C");
            nome.setText(C.get(position));
            nome.setText("CLASSE D");
            nome.setText(D.get(position));
            nome.setText("CLASSE E");
            nome.setText(E.get(position));*/


            return row;
        }
    }
}
