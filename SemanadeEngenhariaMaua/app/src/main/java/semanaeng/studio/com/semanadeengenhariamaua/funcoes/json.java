package semanaeng.studio.com.semanadeengenhariamaua.funcoes;

import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;

import semanaeng.studio.com.semanadeengenhariamaua.modelo.mCurso;
import semanaeng.studio.com.semanadeengenhariamaua.modelo.mPalestra;
import semanaeng.studio.com.semanadeengenhariamaua.modelo.mVisita;
import semanaeng.studio.com.semanadeengenhariamaua.modelo.patrocinador;
import semanaeng.studio.com.semanadeengenhariamaua.modelo.rank;

/**
 * Created by hecto on 20/04/2017.
 */

public class json {

    public String c ;

    public json(String c){
        this.c = c;
    }
    public ArrayList<mCurso> jsonCurso(){
        ArrayList<mCurso> cursos = new ArrayList<>();
        try {
            JSONArray cursor = new JSONArray(c);
            JSONObject jsonObject;

            for (int i = 0; i< cursor.length();i++){
                jsonObject = new JSONObject(cursor.getString(i));
                mCurso c = new mCurso();

                c.setId(jsonObject.getInt("id_curso"));
                c.setCodigo(jsonObject.getString("codigo_curso"));
                c.setEmpresa(jsonObject.getString("nome_empresa"));
                c.setNome(jsonObject.getString("nome_curso"));
                c.setSala(jsonObject.getString("sala_curso"));
                c.setPeriodo(jsonObject.getString("periodo_curso"));
                c.setDescricao(jsonObject.getString("descricao_curso"));
                c.setImagem(jsonObject.getString("imagem_curso"));

                cursos.add(c);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return cursos;
    }
    public ArrayList<rank> jsonRank(){
        Gson g = new Gson();

        ArrayList<rank> ranking = new ArrayList<>();

        try {
            JSONArray cursor = new JSONArray(c);

            JSONObject jsonObject;


            for (int i = 0; i < cursor.length(); i++) {
                jsonObject = new JSONObject(cursor.getString(i));
                rank r = new rank();
                r.setPosicao(i+1);
                r.setNome(jsonObject.getString("nome_participante"));
                r.setPontos(jsonObject.getInt("pontos_participante"));

                ranking.add(r);

            }

        } catch (Exception e) {
            System.out.println("Erro ao listar todos os clientes: " + e);
        }
        Log.i("testeJsonx",g.toJson(ranking));
        return ranking;
    }
    public ArrayList<mPalestra> jsonPalestra(){
        Gson g = new Gson();
        ArrayList<mPalestra> palestra = new ArrayList<>();
        try {
            JSONArray cursojson = new JSONArray(c);

            JSONObject jsonObject;
            for (int i = 0; i < cursojson.length(); i++) {
                jsonObject = new JSONObject(cursojson.getString(i));
                mPalestra p = new mPalestra();
                p.setId(jsonObject.getInt("id_palestra"));
                p.setCodigo(jsonObject.getString("codigo_palestra"));
                p.setEmpresa(jsonObject.getString("nome_empresa"));
                p.setNome(jsonObject.getString("nome_palestra"));
                p.setSala(jsonObject.getString("sala_palestra"));
                p.setData(jsonObject.getString("dia_palestra"));
                p.setHora(jsonObject.getString("hora_palestra"));
                p.setDescricao(jsonObject.getString("descricao_palestra"));
                p.setImagem(jsonObject.getString("imagem_palestra"));
                palestra.add(p);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i("testeJsonx",g.toJson(palestra));
        return palestra;
    }
    public ArrayList<mVisita> jsonVisita(){
        Gson g = new Gson();
        ArrayList<mVisita> visita = new ArrayList<>();
        try {
            JSONArray cursojson = new JSONArray(c);

            JSONObject jsonObject;
            for (int i = 0; i < cursojson.length(); i++) {
                jsonObject = new JSONObject(cursojson.getString(i));
                mVisita p = new mVisita();
                p.setId(jsonObject.getInt("id_visita"));
                p.setCodigo(jsonObject.getString("codigo_visita"));
                p.setEmpresa(jsonObject.getString("nome_empresa"));
                p.setLocal(jsonObject.getString("local_visita"));
                p.setData(jsonObject.getString("data_visita"));
                p.setHoraInicio(jsonObject.getString("hora_inicio_visita"));
                p.setHoraFim(jsonObject.getString("hora_fim_visita"));
                p.setImagem(jsonObject.getString("imagem_visita"));
                visita.add(p);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i("testeJsonx",g.toJson(visita));
        return visita;
    }
    public  ArrayList<String> jsonRecrutamentoStatus(){
        String objetocurso = "";

        ArrayList<String> cursos = new ArrayList<String>();
        try {
            JSONArray cursojson = new JSONArray(c);

            JSONObject jsonObject;
            for (int i = 0; i < cursojson.length(); i++) {
                jsonObject = new JSONObject(cursojson.getString(i));
                objetocurso += "Nosso Processo Seletivo está\n";
                objetocurso += jsonObject.getString("status");
                cursos.add(objetocurso);

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



            }



        } catch (Exception e) {
            System.out.println("Erro ao listar todos os clientes: " + e);
        }
        Log.i("testeJsonx",g.toJson(patrociandor));
        return patrociandor;
    }

}
