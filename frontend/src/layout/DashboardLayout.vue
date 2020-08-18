<template>
  <div class="wrapper" :class="{ 'nav-open': $sidebar.showSidebar }">
    <side-bar
      :background-color="sidebarBackground"
      short-title="SPOTs"
      title="SPOTs"
    >
      <template slot="links">
        <sidebar-item
          class="mb-4"
          :link="{
            name: 'SPOTs',
            icon: 'ni ni-tv-2 text-primary',
            path: '/dashboard',
          }"
        />

        <sidebar-item
        class="mb-4"
          :link="{
            name: '경기장 정보',
            icon: 'ni ni-pin-3 text-orange',
            path: '/maps',
          }"
        />
        <sidebar-item
        class="mb-4"
          v-if="isLogined"
          :link="{
            name: '프로필',
            icon: 'ni ni-single-02 text-yellow',
            path: '/profile',
          }"
        />
        <sidebar-item
        class="mb-4"
          :link="{
            name: '랭킹',
            icon: 'ni ni-bullet-list-67 text-red',
            path: '/tables',
          }"
        />
          <sidebar-item
          class="mb-4"
          :link="{
            name: '팀 리스트',
            icon: 'ni ni-badge text-red',
            path: '/teamlist',
          }"
        />
        <sidebar-item
        class="mb-4"
          v-if="!isLogined"
          :link="{
            name: '로그인',
            icon: 'ni ni-key-25 text-info',
            path: '/login',
          }"
        />
        <sidebar-item
        class="mb-4"
          v-if="!isLogined"
          :link="{
            name: '회원가입',
            icon: 'ni ni-circle-08 text-pink',
            path: '/register',
          }"
        />
      </template>
    </side-bar>
    <div class="main-content" :data="sidebarBackground">
      <dashboard-navbar></dashboard-navbar>

      <div @click="toggleSidebar">
        <fade-transition :duration="200" origin="center top" mode="out-in">
          <!-- your content here -->
          <router-view :key="$route.fullPath" @logoutSuccess="logoutSuccess"></router-view>
        </fade-transition>
        <content-footer v-if="!$route.meta.hideFooter"></content-footer>
      </div>
    </div>
  </div>
</template>
<script>
import DashboardNavbar from "./DashboardNavbar.vue";
import ContentFooter from "./ContentFooter.vue";
import { FadeTransition } from "vue2-transitions";

export default {
  components: {
    DashboardNavbar,
    ContentFooter,
    FadeTransition,
  },
  data() {
    return {
      isLogined: false,
      sidebarBackground: "vue", //vue|blue|orange|green|red|primary
    };
  },
  methods: {
    toggleSidebar() {
      if (this.$sidebar.showSidebar) {
        this.$sidebar.displaySidebar(false);
      }
    },
    logoutSuccess(logoutData) {
      this.isLogined = logoutData
      this.$emit('logoutSuccess', logoutData)
    }
  },
  created() {
    if (this.$cookies.isKey('UserInfo')) {
        this.isLogined = true
    }
  },
};
</script>
<style lang="scss"></style>
