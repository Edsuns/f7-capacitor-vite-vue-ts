package io.github.edsuns.bridge;

import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.core.graphics.ColorUtils;

import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

/**
 * Created by Edsuns@qq.com on 2021/10/16.
 */
@CapacitorPlugin(name = "NavigationBar")
public class NavigationBar extends Plugin {
    @PluginMethod
    public void setBackgroundColor(PluginCall call) {
        try {
            final int color = Color.parseColor(call.getString("color"));
            final Window window = getActivity().getWindow();
            final boolean isLight = ColorUtils.calculateLuminance(color) > 0.5;

            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    window.setNavigationBarColor(color);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        View decorView = window.getDecorView();
                        int visibilityFlags = decorView.getSystemUiVisibility();
                        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
                        if (isLight) {
                            decorView.setSystemUiVisibility(visibilityFlags | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
                        } else {
                            decorView.setSystemUiVisibility(visibilityFlags & ~View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
                        }
                    }
                    call.resolve();
                }
            });
        } catch (Exception e) {
            call.errorCallback(e.getMessage());
        }
    }
}
