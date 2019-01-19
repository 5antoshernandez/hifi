package io.highfidelity.questInterface;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

import java.util.Iterator;
import java.util.Set;

public class PermissionsChecker extends Activity {
    private static final int REQUEST_PERMISSIONS = 20;
    private static final String TAG = PermissionsChecker.class.getName();
    private static final String[] REQUIRED_PERMISSIONS = new String[]{
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.RECORD_AUDIO,
        Manifest.permission.CAMERA
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestAppPermissions(REQUIRED_PERMISSIONS,REQUEST_PERMISSIONS);
    }

    public void requestAppPermissions(final String[] requestedPermissions,
                                      final int requestCode) {
        int permissionCheck = PackageManager.PERMISSION_GRANTED;
        boolean shouldShowRequestPermissionRationale = false;
        for (String permission : requestedPermissions) {
            permissionCheck = permissionCheck + checkSelfPermission(permission);
            shouldShowRequestPermissionRationale = shouldShowRequestPermissionRationale || shouldShowRequestPermissionRationale(permission);
        }
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            System.out.println("Permission was not granted. Ask for permissions");
            if (shouldShowRequestPermissionRationale) {
                requestPermissions(requestedPermissions, requestCode);
            } else {
                requestPermissions(requestedPermissions, requestCode);
            }
        } else {
            System.out.println("Launching the other activity..");
            launchActivityWithPermissions();
        }
    }

    private void launchActivityWithPermissions() {
        Bundle xtras = getIntent().getExtras();

        if(xtras!= null){

            Set<String> keys= xtras.keySet();
            Iterator<String> keyIt = ((Set) keys).iterator();

            while(keyIt.hasNext()){
                String key = keyIt.next();
                Log.e("CMD ARGS","[" + key + "=" + xtras.get(key)+"]");
            }
        }
        else
        {
            Log.e("CMD ARGS","CMD ARGS:: NO EXTRAS");
        }

        Intent tt= new Intent(this, MainActivity.class);


        if(xtras!=null){
            tt.putExtras(xtras);
        }

        startActivity(tt);
        finish();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        int permissionCheck = PackageManager.PERMISSION_GRANTED;
        for (int permission : grantResults) {
            permissionCheck = permissionCheck + permission;
        }
        if ((grantResults.length > 0) && permissionCheck == PackageManager.PERMISSION_GRANTED) {
            launchActivityWithPermissions();
        } else if (grantResults.length > 0) {
            System.out.println("User has deliberately denied Permissions. Launching anyways");
            launchActivityWithPermissions();
        }
    }
}
