package io.github.edsuns;

import android.os.Bundle;

import com.getcapacitor.BridgeActivity;

import io.github.edsuns.bridge.NavigationBar;

public class MainActivity extends BridgeActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerPlugin(NavigationBar.class);
    }
}
