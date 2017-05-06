package steven.apifacbook.ultis;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import steven.apifacbook.common.Common;
import steven.apifacbook.model.UserInfo;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

/**
 * Created by TruongNV on 3/17/2017.
 */

public class SharePreferenceUtils {
    private static SharePreferenceUtils mIntent = null;
//    private static final String SHARE_NAME = BuildConfig.APPLICATION_ID;

    private Context context;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private Gson gson;


    public static SharePreferenceUtils getIntent(Context context) {
        if (mIntent == null)
            mIntent = new SharePreferenceUtils(context);
        return mIntent;
    }


    public SharePreferenceUtils(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences("TestFacbook", Context.MODE_PRIVATE);
        editor = preferences.edit();
        gson = new Gson();
        mIntent = this;
    }

    public void saveIdUser(String number) {
        editor.putString("User_id", number);
        editor.commit();
    }

    public String getIdUser() {
        String id = preferences.getString("User_id", "");
        return id;
    }
    public void saveNameUser(String name) {
        editor.putString("Name_User", name);
        editor.commit();
    }

    public String getNameUser() {
        String name = preferences.getString("Name_User", "");
        return name;
    }



}
