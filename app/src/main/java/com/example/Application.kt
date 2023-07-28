package com.example

import android.app.Application
import com.example.customkeyboard.FontsOverride


class Application : Application() {
    override fun onCreate() {
        super.onCreate()
        /*FontsOverride.setDefaultFont(this, "MONOSPACE", "MyFontAsset2.ttf");
    FontsOverride.setDefaultFont(this, "SERIF", "MyFontAsset3.ttf");
    FontsOverride.setDefaultFont(this, "SANS_SERIF", "MyFontAsset4.ttf");*/
    }
}