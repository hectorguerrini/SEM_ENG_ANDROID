package semanaeng.studio.com.semanadeengenhariamaua;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by hecto on 18/04/2017.
 */

public class GET {

    public static String GET(String url){
        InputStream inputStream = null;
        String result = "";
        String line = "";
        try {
            // create HttpClient
            HttpClient httpclient = new DefaultHttpClient();
            // make GET request to the given URL
            HttpResponse httpResponse = httpclient.execute(new HttpGet(url));
            // receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();
            // convert inputstream to string
            /*if(inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Did not work!";
            */
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            while ((line = reader.readLine()) != null){
                result = result + line;
            }
            inputStream.close();
        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());


        }
        return result;
    }
}
