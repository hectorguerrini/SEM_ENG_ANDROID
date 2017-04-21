package semanaeng.studio.com.semanadeengenhariamaua;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by hecto on 20/04/2017.
 */

public class json {



    public static String json(String c){
        String curso = "";
        try {
            JSONObject jsonObject = new JSONObject(c);
            curso = jsonObject.getString("codigo") + " ";

            curso+= jsonObject.getString("empresa") + " ";

            curso+= jsonObject.getString("nome") + " ";

            curso+= jsonObject.getString("sala");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return curso;
    }
}
