//
//  MainActivity.java
//  android/app/src/main/java
//
//  Created by Stephen Birarda on 1/26/15.
//  Copyright 2015 High Fidelity, Inc.
//
//  Distributed under the Apache License, Version 2.0.
//  See the accompanying file LICENSE or http://www.apache.org/licenses/LICENSE-2.0.html
//

package io.highfidelity.questInterface;

import android.os.Bundle;
import android.view.WindowManager;
import org.qtproject.qt5.android.bindings.QtActivity;
import android.content.Intent;
import java.lang.String;
import java.util.Set;
import java.util.Iterator;
import android.util.Log;

import io.highfidelity.utils.HifiUtils;

public class MainActivity extends QtActivity {
    private native void nativeOnCreate();
    private native void nativeOnDestroy();
    private native void nativeOnPause();
    private native void nativeOnResume();

    public static final String EXTRA_ARGS = "args";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.isLoading = true;
        super.keepInterfaceRunning = true;

        Intent intent = getIntent();
        if(intent.hasExtra(EXTRA_ARGS)){
            String args = intent.getStringExtra((EXTRA_ARGS));
            intent.putExtra("applicationArguments", args);
        }

        super.onCreate(savedInstanceState);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        HifiUtils.upackAssets(getAssets(), getCacheDir().getAbsolutePath());
        nativeOnCreate();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (!super.isLoading) {
            nativeOnPause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        nativeOnResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        nativeOnDestroy();
    }
}
