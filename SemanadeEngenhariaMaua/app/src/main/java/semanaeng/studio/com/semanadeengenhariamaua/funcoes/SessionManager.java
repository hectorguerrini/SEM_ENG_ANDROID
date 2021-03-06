package semanaeng.studio.com.semanadeengenhariamaua.funcoes;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by hecto on 02/06/2017.
 */

public class SessionManager {
    // LogCat tag
    private static String TAG = SessionManager.class.getSimpleName();

    // Shared Preferences
    SharedPreferences pref;

    SharedPreferences.Editor editor;
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "LoginSemana";

    private static final String KEY_IS_LOGGEDIN = "isLoggedIn";


    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setLogin(boolean isLoggedIn,String email) {

        editor.putBoolean(KEY_IS_LOGGEDIN, isLoggedIn);
        editor.putString("Email",email);
        // commit changes
        editor.commit();

        Log.d(TAG, "User login session modified!");
    }


    public boolean isLoggedIn(){
        return pref.getBoolean(KEY_IS_LOGGEDIN, false);
    }
}