package app.news.agoda.com.util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

public class PermissionUtils {

    private static final int REQUEST_PERMISSION_INTERNET = 100;

    public static boolean checkInternetPermission(final Context context) {
        int currentAPIVersion = Build.VERSION.SDK_INT;
        boolean requestPermission = true;
        if (currentAPIVersion >= android.os.Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(context,
                    Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context,
                        Manifest.permission.INTERNET)) {
                    ActivityCompat.requestPermissions((Activity) context,
                            new String[]{Manifest.permission.INTERNET}, REQUEST_PERMISSION_INTERNET);
                } else {
                    ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.INTERNET},
                            REQUEST_PERMISSION_INTERNET);
                }
                requestPermission = false;
            } else {
                requestPermission = true;
            }
        }
        return requestPermission;
    }
}
