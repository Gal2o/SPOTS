<template>
    <nav class="navbar navbar-vertical fixed-left navbar-expand-md navbar-light bg-white" id="sidenav-main">
        <div class="container-fluid">

            <!--Toggler-->
            <navbar-toggle-button @click.native="showSidebar">
                <span class="navbar-toggler-icon"></span>
            </navbar-toggle-button>
            <router-link class="navbar-brand" to="/">
                <img :src="logo" class="navbar-brand-img" alt="...">
            </router-link>

            <slot name="mobile-right">
                <ul class="nav align-items-center d-md-none">                    
                    <base-dropdown v-if="isLogined" class="nav-item" position="right">
                        <a slot="title" class="nav-link" href="#" role="button">
                            <div class="media align-items-center">
                              <span class="avatar avatar-sm rounded-circle">
                                <img alt="Image placeholder" :src="imgurl">
                              </span>
                            </div>
                        </a>

                        <div class=" dropdown-header noti-title">
                            <h6 class="text-overflow m-0">환영합니다!</h6>
                        </div>
                        <router-link to="/profile" class="dropdown-item">
                            <i class="ni ni-single-02"></i>
                            <span>프로필</span>
                        </router-link>
                        <div class="dropdown-divider"></div>
                        <a @click="Logout" class="dropdown-item">
                            <i class="ni ni-user-run"></i>
                            <span>로그아웃</span>
                        </a>
                    </base-dropdown>
                </ul>
            </slot>
            <slot></slot>
            <div v-show="$sidebar.showSidebar" class="navbar-collapse collapse show" id="sidenav-collapse-main">

                <div class="navbar-collapse-header d-md-none">
                    <div class="row">
                        <div class="col-6 collapse-brand">
                            <router-link to="/">
                                <img :src="logo">
                            </router-link>
                        </div>
                        <div class="col-6 collapse-close">
                            <navbar-toggle-button @click.native="closeSidebar"></navbar-toggle-button>
                        </div>
                    </div>
                </div>

                <ul class="navbar-nav">
                    <slot name="links">
                    </slot>
                </ul>
            </div>
            </div>
    </nav>
</template>
<script>
  import NavbarToggleButton from '@/components/NavbarToggleButton'

  export default {
    name: 'sidebar',
    components: {
      NavbarToggleButton
    },
    data() {
      return {
        isLogined: false,
        imgurl: "",
      }
    },
    props: {
      logo: {
        type: String,
        default: 'img/brand/SPOTs/SPOTs_dark.jpg',
        description: 'Sidebar app logo'
      },
      autoClose: {
        type: Boolean,
        default: true,
        description: 'Whether sidebar should autoclose on mobile when clicking an item'
      }
    },
    provide() {
      return {
        autoClose: this.autoClose
      };
    },
    methods: {
      closeSidebar() {
        this.$sidebar.displaySidebar(false)
      },
      showSidebar() {
        this.$sidebar.displaySidebar(true)
      },
      Logout() {
      this.$axios.post(this.$SERVER_URL + 'logout/', null)
        .then(() => {
          this.$cookies.remove('UserInfo')
          this.isLoggedIn = false
          this.$emit('logoutSuc', this.isLoggedIn)
          this.$router.push({ name: 'login'})
        })
      },
    },
    beforeDestroy() {
      if (this.$sidebar.showSidebar) {
        this.$sidebar.showSidebar = false;
      }
    },
    created() {
      if (this.$cookies.isKey('UserInfo')) {
        this.isLogined = true
      }
      var logonum = ((this.$cookies.get("UserInfo").uid)%24)+1
      this.imgurl = 'img/userLogo/'+ logonum +'.png'
    }
  };
</script>