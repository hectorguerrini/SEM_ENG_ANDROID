package semanaeng.studio.com.semanadeengenhariamaua;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hecto on 19/04/2017.
 */

public class acessoRest extends AsyncTask<String, Void, String> {

    private ListView listView;
    private String url = "http://sample-env-4.drxhpt9fid.us-east-1.elasticbeanstalk.com/webresources/app/curso/listarDiurno";
    Context context;
    private static ArrayList<String> item = new ArrayList<>();

    @Override
    protected String doInBackground(String... params) {

        return GET.GET(url);
    }

    @Override
    protected void onPostExecute(String s) {
        //String curso = "";
        try {
            /*
            JSONObject jsonObject = new JSONObject(s);
            curso = jsonObject.getString("codigo") + " ";

            curso+= jsonObject.getString("empresa") + " ";

            curso+= jsonObject.getString("nome") + " ";

            curso+= jsonObject.getString("sala") + " ";

            curso+= jsonObject.getString("periodo");
            */



        } catch (Exception e) {
            e.printStackTrace();
        }

        //lista.setAdapter(custom);
    }
}
