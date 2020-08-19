<template>
  <div id="app">
    <notifications></notifications>
    <router-view @login-submit="Login" :key="$route.fullPath"/>
    <modal :show.sync="modal.loginerror" gradient="danger" class="text-center">
        <div class="py-3 text-center mb-0">
          <h3 class="text-white mb-3">아이디나 비밀번호가 틀렸습니다. 다시 적어주세요!</h3>
          <base-button size="sm" type="secondary" @click="modal.loginerror = false">닫기</base-button>
        </div>
    </modal>

    <modal :show.sync="modal.blacklist" gradient="danger" class="text-center">
        <div class="py-3 text-center mb-0">
          <h3 class="text-white mb-3">신고로 정지된 계정입니다.</h3>
          <base-button size="sm" type="secondary" @click="modal.blacklist = false">닫기</base-button>
        </div>
    </modal>
  </div>
</template>


<script>
export default {
  name: "login",
  data() {
    return {
      isLogined: false,
      modal: {
        loginerror: false,
        blacklist: false,
      },
    };
  },
  methods: {
    setCookie(UserInfo) {
      this.$cookies.set("UserInfo", UserInfo);
    },
    Login(loginInfo) {
     
      const loginData = new FormData();
      loginData.append("email", loginInfo.email);
      loginData.append("password", loginInfo.password);
  
      this.$axios
        .post(this.$SERVER_URL + "login/", loginData)
        .then((res) => {
         
          if (res.data == "") {
            this.modal.loginerror = true;
          } else if (res.data.blacklist >= 5) {
            this.modal.blacklist = true;
          } else {
            const Userdata = res.data;
            this.setCookie(Userdata);
            this.isLogined = true;
            this.$router.push({ name: "SPOTs" });
          }
        })
        
    },
    logoutSuccess(logoutData) {
      this.isLogined = logoutData;
    },
  },
  created() {
    if (this.$cookies.isKey("UserInfo")) {
      this.isLogined = true;
    }
  },
};
</script>
