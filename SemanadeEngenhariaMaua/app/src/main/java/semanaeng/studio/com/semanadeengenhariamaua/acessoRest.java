package semanaeng.studio.com.semanadeengenhariamaua;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * Created by hecto on 16/04/2017.
 */

public class acessoRest extends AsyncTask<String, Void, Void>{
    final HttpClient httpClient = new DefaultHttpClient();

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(String... params) {
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }
}
