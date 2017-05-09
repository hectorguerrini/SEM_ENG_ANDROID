package semanaeng.studio.com.semanadeengenhariamaua.funcoes;

import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

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

                objetocurso += jsonObject.getString("codigo_curso") + " ";
                objetocurso += jsonObject.getString("nome_empresa") + " ";
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

                objetocurso += jsonObject.getString("datai") + " a ";
                objetocurso += jsonObject.getString("dataf");
                cursos.add(objetocurso);
                Log.i("teste", objetocurso);
                objetocurso = " ";
            }



        } catch (JSONException e) {
            e.printStackTrace();
        }

        return cursos;
    }

}
