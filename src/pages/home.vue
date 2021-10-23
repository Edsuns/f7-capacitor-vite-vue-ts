<template>
  <f7-page name="home">
    <!-- Top Navbar -->
    <f7-navbar large :sliding="false">
      <f7-nav-left>
        <f7-link icon-ios="f7:menu" icon-aurora="f7:menu" icon-md="material:menu" panel-open="left"></f7-link>
      </f7-nav-left>
      <f7-nav-title sliding>f7-capacitor-vite-vue-ts</f7-nav-title>
      <f7-nav-right>
        <f7-link
          icon-ios="f7:menu"
          icon-aurora="f7:menu"
          icon-md="material:menu"
          panel-open="right"
        ></f7-link>
      </f7-nav-right>
      <f7-nav-title-large>f7-capacitor-vite-vue-ts</f7-nav-title-large>
    </f7-navbar>

    <!-- Page content-->
    <f7-block strong>
      <p>This is an example of tabs-layout application. The main point of such tabbed layout is that each tab contains independent view with its own routing and navigation.</p>

      <p>Each tab/view may have different layout, different navbar type (dynamic, fixed or static) or without navbar like this tab.</p>
    </f7-block>
    <f7-block-title>Navigation</f7-block-title>
    <f7-list>
      <f7-list-item link="/about/" title="About"></f7-list-item>
      <f7-list-item link="/form/" title="Form"></f7-list-item>
    </f7-list>

    <f7-block-title>Layout Themes</f7-block-title>
    <f7-block strong>
      <p>Framework7 comes with 2 main layout themes: Light (default) and Dark:</p>
      <f7-row>
        <f7-col width="50" class="bg-color-white theme-picker" @click="setLayoutTheme('light')">
          <f7-checkbox v-if="theme === 'light'" checked disabled />
        </f7-col>
        <f7-col width="50" class="bg-color-black theme-picker" @click="setLayoutTheme('dark')">
          <f7-checkbox v-if="theme === 'dark'" checked disabled />
        </f7-col>
      </f7-row>
    </f7-block>

    <f7-block-title>Platform Themes</f7-block-title>
    <f7-block strong>
      <f7-row>
        <f7-col width="50" class="theme-picker" @click="setPlatformTheme('ios')">
          <f7-checkbox v-if="platform === 'ios'" checked disabled />
          <f7-col class="platform">
            <i class="f7-icons">logo_apple</i>
            <i class="f7-icons">logo_ios</i>
          </f7-col>
        </f7-col>
        <f7-col width="50" class="theme-picker" @click="setPlatformTheme('md')">
          <f7-checkbox v-if="platform === 'md'" checked disabled />
          <f7-col class="platform">
            <i class="f7-icons">logo_android</i>
            <i class="f7-icons">logo_android_text</i>
          </f7-col>
        </f7-col>
      </f7-row>
    </f7-block>

    <f7-block-title>Modals</f7-block-title>
    <f7-block strong>
      <f7-row>
        <f7-col width="50">
          <f7-button fill raised popup-open="#my-popup">Popup</f7-button>
        </f7-col>
        <f7-col width="50">
          <f7-button fill raised login-screen-open="#my-login-screen">Login Screen</f7-button>
        </f7-col>
      </f7-row>
    </f7-block>

    <f7-block-title>Panels</f7-block-title>
    <f7-block strong>
      <f7-row>
        <f7-col width="50">
          <f7-button fill raised panel-open="left">Left Panel</f7-button>
        </f7-col>
        <f7-col width="50">
          <f7-button fill raised panel-open="right">Right Panel</f7-button>
        </f7-col>
      </f7-row>
    </f7-block>

    <f7-list>
      <f7-list-item
        title="Dynamic (Component) Route"
        link="/dynamic-route/blog/45/post/125/?foo=bar#about"
      ></f7-list-item>
      <f7-list-item title="Default Route (404)" link="/load-something-that-doesnt-exist/"></f7-list-item>
      <f7-list-item title="Request Data & Load" link="/request-and-load/user/123456/"></f7-list-item>
    </f7-list>
  </f7-page>
</template>
<style>
.theme-picker {
  cursor: pointer;
  padding: 30px;
  border-radius: 10px;
  box-shadow: 0px 5px 20px rgba(0, 0, 0, 0.1);
  border: 1px solid rgba(255, 255, 255, 0.2);
  box-sizing: border-box;
  position: relative;
}
.theme-picker .checkbox {
  position: absolute;
  left: 10px;
  bottom: 10px;
  opacity: 1 !important;
}
.theme-picker .platform {
  text-align: center;
}
</style>
<script>
import { getDevice } from 'framework7';
import { f7 } from 'framework7-vue';
import Theme from '../utils/theme';
export default {
  data() {
    return {
      theme: Theme.hasDarkColorScheme() ? 'dark' : 'light',
      platform: 'ios'
    }
  },
  mounted() {
    let theme = Theme.extractThemeSearch();
    if (theme === 'auto') {
      theme = getDevice().ios ? 'ios' : 'md';
    }
    this.platform = theme === 'aurora' ? 'md' : theme;
  },
  methods: {
    setLayoutTheme(theme) {
      Theme.setDark(theme === 'dark');
      this.theme = theme;
    },
    setPlatformTheme(theme) {
      if (theme !== this.platform) {
        f7.dialog.confirm(
          'Switching platform theme will refresh the page.',
          'Confirm to Refresh',
          () => Theme.replaceLocation(theme)
        );
      }
    }
  }
}
</script>