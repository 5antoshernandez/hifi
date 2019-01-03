//
//  Created by Bradley Austin Davis on 2018/11/20
//  Copyright 2013-2018 High Fidelity, Inc.
//
//  Distributed under the Apache License, Version 2.0.
//  See the accompanying file LICENSE or http://www.apache.org/licenses/LICENSE-2.0.html
//
package io.highfidelity.frameplayer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import org.qtproject.qt5.android.bindings.QtActivity;

import io.highfidelity.oculus.OculusMobileActivity;


public class FramePlayerActivity extends QtActivity {
    private native void nativeOnCreate();
    private boolean launchedQuestMode = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        System.loadLibrary("framePlayer");
        super.onCreate(savedInstanceState);
        nativeOnCreate();
    }

    public void launchOculusActivity() {
        startActivity(new Intent(this, OculusMobileActivity.class));
        launchedQuestMode = true;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (launchedQuestMode) {
            Log.w("QQQ_Qt", "FramePlayerActivity::onResume, forwarding to OculusMobileActivity");
            startActivity(new Intent(this, OculusMobileActivity.class));
        }
    }
}
