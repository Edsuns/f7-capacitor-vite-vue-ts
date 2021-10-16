import { f7 } from 'framework7-vue';
import { Device } from 'framework7/types';
import { getDevice } from 'framework7/lite-bundle';
import { StatusBar, Style } from '@capacitor/status-bar';

import NavigationBar from './capacitor/navigation-bar'

interface Theme {
    init(): void
    sync(): void
    isDark(): boolean
    setTheme(dark: boolean): void
}

const theme: Theme = {
    init() {
        // Add listener for dark-mode changes
        const media: MediaQueryList = window.matchMedia('(prefers-color-scheme: dark)');
        const listener = (event: MediaQueryListEvent) => {
            this.setTheme(event.matches);
        }
        if (typeof media.addEventListener === 'function') {
            media.addEventListener('change', listener);
        } else {
            media.addListener(listener);
        }
        // Apply theme immediately
        this.setTheme(media.matches);
    },
    sync() {
        this.setTheme(this.isDark());
    },
    isDark(): boolean {
        return window.matchMedia && window.matchMedia('(prefers-color-scheme: dark)').matches;
    },
    setTheme(dark: boolean) {
        const device: Device = getDevice();
        // Apply status bar style for android
        if (device.android) {
            const colorOption = { color: dark ? '#111111' : '#F7F7F8' };
            StatusBar.setStyle({ style: dark ? Style.Dark : Style.Light });
            StatusBar.setBackgroundColor(colorOption);
            NavigationBar.setBackgroundColor(colorOption);
        }
        // Apply styles
        const $ = f7.$;
        const $html = $('html');
        $html.removeClass('theme-dark theme-light').addClass(`theme-${dark ? 'dark' : 'light'}`);
    }
}

export default theme;