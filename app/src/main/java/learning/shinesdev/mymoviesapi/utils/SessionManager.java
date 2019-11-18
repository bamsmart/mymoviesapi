package learning.shinesdev.mymoviesapi.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

@SuppressWarnings("ALL")
public class SessionManager {
    private static final String PREF_NAME = "PREF_LANG";
    private static final String KEY_LANGUAGE = "keyLang";
    final SharedPreferences pref;
    final Editor editor;
    final Context context;
    final int PRIVATE_MODE = 0;

    public SessionManager(Context context) {
        this.context = context;
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public String getPrevLang() {
        return pref.getString(KEY_LANGUAGE, "");
    }

    public void setPrevLang(String email) {
        editor.putString(KEY_LANGUAGE, email);
        editor.commit();
    }


}
