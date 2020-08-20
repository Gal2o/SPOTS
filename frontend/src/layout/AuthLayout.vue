<template>
    <div class="main-content bg-default">
        <!-- Navbar -->
        <base-nav class="navbar-top navbar-horizontal navbar-dark"
                  containerClasses="px-4 container"
                  expand>
            <router-link slot="brand" class="navbar-brand" to="/dashboard">
                <img src="img/brand/SPOTs/SPOTs_white.png"/>
            </router-link>

            <template v-slot="{closeMenu}">
                <!-- Collapse header -->
                <div class="navbar-collapse-header d-md-none">
                    <div class="row">
                        <div class="col-6 collapse-brand">
                            <router-link to="/dashboard">
                                <img src="img/brand/SPOTs/SPOTs_dark.jpg">
                            </router-link>
                        </div>
                        <div class="col-6 collapse-close">
                            <button type="button"
                                    @click="closeMenu"
                                    class="navbar-toggler"
                                    aria-label="Toggle sidenav">
                                <span></span>
                                <span></span>
                            </button>
                        </div>
                    </div>
                </div>
                <!-- Navbar items -->
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item">
                        <router-link class="nav-link nav-link-icon" to="/dashboard">
                            <i class="ni ni-planet"></i>
                            <span class="nav-link-inner--text">SPOTs</span>
                        </router-link>
                    </li>
                    <li class="nav-item">
                        <router-link class="nav-link nav-link-icon" to="/register">
                            <i class="ni ni-circle-08"></i>
                            <span class="nav-link-inner--text">회원가입</span>
                        </router-link>
                    </li>
                    <li class="nav-item">
                        <router-link class="nav-link nav-link-icon" to="/login">
                            <i class="ni ni-key-25"></i>
                            <span class="nav-link-inner--text">로그인</span>
                        </router-link>
                    </li>
                </ul>
            </template>
        </base-nav>
        <!-- Header -->
        <div class="header bg-gradient-success py-7 py-lg-8">
            <div class="container">
                <div class="header-body text-center mb-7">
                    <div class="row justify-content-center">
                        <div class="col-lg-5 col-md-6">
                            <h1 class="text-white">환영합니다!</h1>
                            <p class="text-lead text-white">SPOTs를 이용하기 위해 로그인해주세요. <br>
                            계정이 없다면 회원가입 후 이용가능합니다.</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="separator separator-bottom separator-skew zindex-100">
                <svg x="0" y="0" viewBox="0 0 2560 100" preserveAspectRatio="none" version="1.1"
                     xmlns="http://www.w3.org/2000/svg">
                    <polygon class="fill-default" points="2560 0 2560 100 0 100"></polygon>
                </svg>
            </div>
        </div>
        <!-- Page content -->
        <div class="container mt--8 pb-5">
            <slide-y-up-transition mode="out-in" origin="center top">
                <router-view @login-submit="Login"></router-view>
            </slide-y-up-transition>
        </div>
        <footer class="py-5">
            <div class="container">
                <div class="row align-items-center justify-content-xl-between">
                    <div class="col-xl-6">
                        <div class="copyright text-center text-xl-left text-muted">
                            &copy; {{year}} <a href="https://lab.ssafy.com/s03-webmobile2-sub3/s03p13a408" class="font-weight-bold ml-1"
                                               target="_blank">제작 : 일검사회</a>
                        </div>
                    </div>
                    <div class="col-xl-6">
                        <ul class="nav nav-footer justify-content-center justify-content-xl-end">
                            <li class="nav-item">
                                <router-link to="/">
                                    <a href="#" class="nav-link" target="_blank">SPOTs</a>
                                </router-link>
                            </li>
                            <li class="nav-item">
                                <a href="https://lab.ssafy.com/s03-webmobile2-sub3/s03p13a408" class="nav-link" target="_blank">일검사회</a>
                            </li>
                            <li class="nav-item">
                                <a href="https://github.com/creativetimofficial/argon-dashboard/blob/master/LICENSE.md"
                                   class="nav-link" target="_blank">MIT 라이센스</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </footer>
    </div>
</template>
<script>
  import { SlideYUpTransition } from 'vue2-transitions'

  export default {
    name: 'auth-layout',
    components: {
      SlideYUpTransition
    },
    data() {
        return {
            year: new Date().getFullYear(),
            showMenu: false
        }
    },
    methods: {
        Login(loginInfo) {
            this.$emit('login-submit', loginInfo)
        },
    },
  }
</script>
<style>
</style>
