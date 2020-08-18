<template>
  <base-nav
    class="navbar-top navbar-dark"
    id="navbar-main"
    :show-toggle-button="false"
    expand
  >
    <form
      class="navbar-search navbar-search-dark form-inline mr-3 d-none d-md-flex ml-lg-auto"
    >
      <div class="form-group mb-0">        
      </div>
    </form>
    <ul class="navbar-nav align-items-center d-none d-md-flex">
      <li class="nav-item dropdown">
        <base-dropdown class="nav-link pr-0">
          <div v-if="!isLogined" class="media align-items-center" slot="title">
            <router-link to="/login">
              <div class="media-body ml-2 d-none d-lg-block">
                <base-button class="mb-0" type=""><h2 style="color:white;">로그인</h2></base-button>
              </div>
            </router-link>
          </div>
          <div v-if="isLogined" class="media align-items-center" slot="title">
            <span class="avatar avatar-sm rounded-circle">
              <img alt="Image placeholder" :src="imgurl" />
            </span>
            <div class="media-body ml-2 d-none d-lg-block">
              <span class="mb-0 text-sm font-weight-bold">{{ userInfo.nickname }}</span>
            </div>
          </div>

          <template>
            <div v-if="isLogined">
              <div class=" dropdown-header noti-title">
                <h6 class="text-overflow m-0">환영합니다!</h6>
              </div>
              <router-link to="/profile" class="dropdown-item">
                <i class="ni ni-single-02"></i>
                <span>프로필</span>
              </router-link>
              <router-link to="/Teamprofile" class="dropdown-item">
                <i class="ni ni-badge text"></i>
                <span>내 팀 프로필</span>
              </router-link>
              <div class="dropdown-divider"></div>
              <div @click="Logout" class="dropdown-item">
                <i class="ni ni-user-run"></i>
                <span>로그아웃</span>
              </div>
            </div>
          </template>
        </base-dropdown>
      </li>
    </ul>
  </base-nav>
</template>

<script>
export default {
  data() {
    return {
      activeNotifications: false,
      showMenu: false,
      searchQuery: "",
      isLogined: false,
      userInfo: Object,
      imgurl: "",
    };
  },
  methods: {
    toggleSidebar() {
      this.$sidebar.displaySidebar(!this.$sidebar.showSidebar);
    },
    hideSidebar() {
      this.$sidebar.displaySidebar(false);
    },
    toggleMenu() {
      this.showMenu = !this.showMenu;
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
  created() {
      if (this.$cookies.isKey('UserInfo')) {
        this.isLogined = true
        this.userInfo = this.$cookies.get('UserInfo')
        var logonum = ((this.$cookies.get("UserInfo").uid)%24)+1
    }
    
    this.imgurl = 'img/userLogo/'+ logonum +'.png'
  }
};
</script>
