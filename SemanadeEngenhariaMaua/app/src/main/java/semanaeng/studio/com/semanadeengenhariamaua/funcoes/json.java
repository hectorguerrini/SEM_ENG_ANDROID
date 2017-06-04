package semanaeng.studio.com.semanadeengenhariamaua.funcoes;

import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;

import semanaeng.studio.com.semanadeengenhariamaua.modelo.patrocinador;

/**
 * Created by hecto on 20/04/2017.
 */

public class json {

    public String c ;

    public json(String c){
        this.c = c;
    }
    public  ArrayList<String> jsonEmpresaList(){
        String objetocurso = "";

        ArrayList<String> cursos = new ArrayList<String>();
        try {
            JSONArray cursojson = new JSONArray(c);

            JSONObject jsonObject;
            for (int i = 0; i < cursojson.length(); i++) {
                jsonObject = new JSONObject(cursojson.getString(i));

                objetocurso += jsonObject.getString("nome_empresa");
                cursos.add(objetocurso);
                Log.i("teste", objetocurso);
                objetocurso = " ";
            }



        } catch (JSONException e) {
            e.printStackTrace();
        }

        return cursos;
    }
    public  ArrayList<String> jsonList(){
        String objetocurso = "";

        ArrayList<String> cursos = new ArrayList<String>();
        try {
            JSONArray cursojson = new JSONArray(c);

            JSONObject jsonObject;
            for (int i = 0; i < cursojson.length(); i++) {
                jsonObject = new JSONObject(cursojson.getString(i));

                objetocurso += jsonObject.getString("nome_curso") + " ";
                objetocurso += "Sala: " + jsonObject.getString("sala_curso");
                cursos.add(objetocurso);
                Log.i("teste", objetocurso);
                objetocurso = " ";
            }



        } catch (JSONException e) {
            e.printStackTrace();
        }

        return cursos;
    }
    public  ArrayList<String> jsonDetalhesT(){
        String objetocurso = "";

        ArrayList<String> cursos = new ArrayList<String>();
        try {
            JSONArray cursojson = new JSONArray(c);

            JSONObject jsonObject;
            for (int i = 0; i < cursojson.length(); i++) {
                jsonObject = new JSONObject(cursojson.getString(i));

                objetocurso += jsonObject.getString("codigo_curso") + " - ";
                objetocurso += jsonObject.getString("nome_empresa") + ": ";
                objetocurso += jsonObject.getString("nome_curso") + "\n";
                objetocurso += "Sala: " + jsonObject.getString("sala_curso");
                cursos.add(objetocurso);
                Log.i("teste", objetocurso);
                objetocurso = " ";
            }



        } catch (JSONException e) {
            e.printStackTrace();
        }

        return cursos;
    }
    public  ArrayList<String> jsonDetalhesD(){
        String objetocurso = "";

        ArrayList<String> cursos = new ArrayList<String>();
        try {
            JSONArray cursojson = new JSONArray(c);

            JSONObject jsonObject;
            for (int i = 0; i < cursojson.length(); i++) {
                jsonObject = new JSONObject(cursojson.getString(i));

                objetocurso += jsonObject.getString("descricao_curso");
                cursos.add(objetocurso);
                Log.i("teste", objetocurso);
                objetocurso = " ";
            }



        } catch (JSONException e) {
            e.printStackTrace();
        }

        return cursos;
    }
    public  ArrayList<String> jsonRecrutamentoStatus(){
        String objetocurso = "";

        ArrayList<String> cursos = new ArrayList<String>();
        try {
            JSONArray cursojson = new JSONArray(c);

            JSONObject jsonObject;
            for (int i = 0; i < cursojson.length(); i++) {
                jsonObject = new JSONObject(cursojson.getString(i));
                objetocurso += "Nosso Processo Seletivo esta\n";
                objetocurso += jsonObject.getString("status");
                cursos.add(objetocurso);
                Log.i("teste", objetocurso);
                objetocurso = " ";
            }



        } catch (JSONException e) {
            e.printStackTrace();
        }

        return cursos;
    }
    public  ArrayList<String> jsonRecrutamentoData(){
        String objetocurso = "";

        ArrayList<String> cursos = new ArrayList<String>();
        try {
            JSONArray cursojson = new JSONArray(c);

            JSONObject jsonObject;
            for (int i = 0; i < cursojson.length(); i++) {
                jsonObject = new JSONObject(cursojson.getString(i));

                objetocurso += jsonObject.getString("data_inicial") + " a ";
                objetocurso += jsonObject.getString("data_final");
                cursos.add(objetocurso);
                Log.i("teste", objetocurso);
                objetocurso = " ";
            }



        } catch (JSONException e) {
            e.printStackTrace();
        }

        return cursos;
    }
    public  ArrayList<String> jsonTema(){
        String objetocurso = "";

        ArrayList<String> cursos = new ArrayList<String>();
        try {
            JSONArray cursojson = new JSONArray(c);

            JSONObject jsonObject;
            for (int i = 0; i < cursojson.length(); i++) {
                jsonObject = new JSONObject(cursojson.getString(i));

                objetocurso += jsonObject.getString("tema");
                cursos.add(objetocurso);
                Log.i("teste", objetocurso);
                objetocurso = " ";
            }



        } catch (JSONException e) {
            e.printStackTrace();
        }

        return cursos;
    }

    public  ArrayList<String> jsonEmpresaPalestraList(){
        String objetocurso = "";

        ArrayList<String> cursos = new ArrayList<String>();
        try {
            JSONArray cursojson = new JSONArray(c);

            JSONObject jsonObject;
            for (int i = 0; i < cursojson.length(); i++) {
                jsonObject = new JSONObject(cursojson.getString(i));

                objetocurso += jsonObject.getString("nome_empresa");
                cursos.add(objetocurso);
                Log.i("teste", objetocurso);
                objetocurso = " ";
            }



        } catch (JSONException e) {
            e.printStackTrace();
        }

        return cursos;
    }
    public  ArrayList<String> jsonListPalestra(){
        String objetocurso = "";

        ArrayList<String> cursos = new ArrayList<String>();
        try {
            JSONArray cursojson = new JSONArray(c);

            JSONObject jsonObject;
            for (int i = 0; i < cursojson.length(); i++) {
                jsonObject = new JSONObject(cursojson.getString(i));

                objetocurso += jsonObject.getString("nome_palestra") + " ";
                objetocurso += "Sala: " + jsonObject.getString("sala_palestra");
                cursos.add(objetocurso);
                Log.i("teste", objetocurso);
                objetocurso = " ";
            }



        } catch (JSONException e) {
            e.printStackTrace();
        }

        return cursos;
    }
    public  ArrayList<String> jsonDetalhesPalestraT(){
        String objetocurso = "";

        ArrayList<String> cursos = new ArrayList<String>();
        try {
            JSONArray cursojson = new JSONArray(c);

            JSONObject jsonObject;
            for (int i = 0; i < cursojson.length(); i++) {
                jsonObject = new JSONObject(cursojson.getString(i));

                objetocurso += jsonObject.getString("codigo_palestra") + " - ";
                objetocurso += jsonObject.getString("nome_empresa") + ": ";
                objetocurso += jsonObject.getString("nome_palestra") + "\n";
                objetocurso += "Sala: " + jsonObject.getString("sala_palestra");
                cursos.add(objetocurso);
                Log.i("teste", objetocurso);
                objetocurso = " ";
            }



        } catch (JSONException e) {
            e.printStackTrace();
        }

        return cursos;
    }
    public  ArrayList<String> jsonDetalhesPalestraD(){
        String objetocurso = "";

        ArrayList<String> cursos = new ArrayList<String>();
        try {
            JSONArray cursojson = new JSONArray(c);

            JSONObject jsonObject;
            for (int i = 0; i < cursojson.length(); i++) {
                jsonObject = new JSONObject(cursojson.getString(i));

                objetocurso += jsonObject.getString("descricao_palestra");
                cursos.add(objetocurso);
                Log.i("teste", objetocurso);
                objetocurso = " ";
            }



        } catch (JSONException e) {
            e.printStackTrace();
        }

        return cursos;
    }

    public ArrayList<patrocinador> listarPatrocinadores(){
        Gson g = new Gson();

        ArrayList<patrocinador> patrociandor = new ArrayList<>();

        try {
            JSONArray cursor = new JSONArray(c);

            JSONObject jsonObject;


            for (int i = 0; i < cursor.length(); i++) {
                jsonObject = new JSONObject(cursor.getString(i));
                patrocinador p = new patrocinador();
                p.setId(jsonObject.getInt("id_patrocinador"));
                p.setNome(jsonObject.getString("nome_patrocinador"));
                p.setTier(jsonObject.getInt("tier_patrocinador"));
                p.setImagem(jsonObject.getString("imagem_patrocinador"));

                patrociandor.add(p);

                Log.i("testeJsonx",g.toJson(patrociandor));

            }



        } catch (Exception e) {
            System.out.println("Erro ao listar todos os clientes: " + e);
        }
        Log.i("testeJsonx",g.toJson(patrociandor));
        return patrociandor;
    }

}
