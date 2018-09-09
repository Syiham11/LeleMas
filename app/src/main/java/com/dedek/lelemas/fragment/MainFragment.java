package com.dedek.lelemas.fragment;

import android.arch.lifecycle.Lifecycle;
import android.graphics.Bitmap;
import android.support.v4.app.Fragment;

import yalantis.com.sidemenu.interfaces.ScreenShotable;

public class MainFragment extends ContentFragment{
    @Override
    public Lifecycle getLifecycle() {
        return super.getLifecycle();
    }

    @Override
    public void takeScreenShot() {

    }

    @Override
    public Bitmap getBitmap() {
        return null;
    }
}
