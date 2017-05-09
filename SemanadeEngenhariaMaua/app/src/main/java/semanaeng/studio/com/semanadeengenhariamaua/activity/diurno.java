package semanaeng.studio.com.semanadeengenhariamaua.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Interpolator;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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

public class diurno extends AppCompatActivity{

    private Button back;
    private ArrayList<String> itemsList = new ArrayList<>();
    private ArrayList<String> itemsTD = new ArrayList<>();
    private ArrayList<String> itemsTDD = new ArrayList<>();
    private ArrayList<String> itemsTE = new ArrayList<>();
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



        new acessoRest().execute("http://www.mocky.io/v2/58fe8ee11100000f01f5fdb9");




    }

    public class acessoRest extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... params) {

            String c = GET.GET(params[0]);
            json j = new json(c);
            if (c != null) {
                itemsList = j.jsonList();
                itemsTD = j.jsonDetalhesT();
                itemsTDD = j.jsonDetalhesD();
                itemsTE = j.jsonEmpresaList();
            }
            else {
                itemsList = null;
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            mProgress.setVisibility(View.VISIBLE);
            lista = (ListView) findViewById(R.id.listaDiurno);
            TextView nc = (TextView) findViewById(R.id.text_noconn);
            if (itemsList != null){
                MyAdapter adapter = new MyAdapter(diurno.this,itemsTE,itemsList);
                lista.setAdapter(adapter);
                lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(diurno.this,detalhes.class);
                        intent.putExtra("dadosT",itemsTD.get(position));
                        intent.putExtra("dadosD",itemsTDD.get(position));
                        startActivity(intent);

                    }
                });

            }else{
                Log.i("teste", "sem conect");
                nc.setText("Sem Conexao");
            }

            mProgress.setVisibility(View.GONE);


        }


    }

    public class MyAdapter extends ArrayAdapter {
        Context context;
        int[] images = {
                R.drawable.logosemana3,
                R.drawable.logosemana3
        };
        ArrayList<String> empresa;
        ArrayList<String> curso;

        public MyAdapter(Context c, ArrayList<String> empresa,ArrayList<String> curso)
        {
            super(c,R.layout.view,empresa);
            this.context=c;
            this.empresa=empresa;
            this.curso=curso;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = inflater.inflate(R.layout.view,parent,false);
            ImageView Image = (ImageView) row.findViewById(R.id.imagelista);
            TextView Empresa = (TextView) row.findViewById(R.id.textolista);
            TextView Curso = (TextView) row.findViewById(R.id.texto2lista);
            Image.setImageResource(images[0]);
            Empresa.setText(empresa.get(position));
            Curso.setText(curso.get(position));

            return row;
        }
    }




}
