import { registerPlugin } from "@capacitor/core";

export interface NavigationBar {
    /**
     * Set background color of navigation bar, will change icon color automatically.
     * Can only be called on Android.
     * @param options color
     */
    setBackgroundColor(options: { color: string }): void
}

const navigationBar = registerPlugin<NavigationBar>('NavigationBar');

export default navigationBar;