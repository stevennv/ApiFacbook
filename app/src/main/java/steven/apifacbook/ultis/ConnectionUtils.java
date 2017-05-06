package steven.apifacbook.ultis;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by TruongNV on 4/22/2017.
 */

public class ConnectionUtils {
    public static boolean isOnline(Context context)
    {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo!=null && networkInfo.isAvailable() && networkInfo.isConnected());
    }
}
