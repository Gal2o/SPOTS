<template>
  <div id="app">
    <notifications></notifications>
    <router-view @login-submit="Login" />
  </div>
</template>


<script>
import axios from "axios";

const SERVER_URL = "http://localhost:8080/";
export default {
  name: "login",
  data() {
    return {
      isLogined: false,
    };
  },
  methods: {
    setCookie(UserInfo) {
      console.log(this);
      this.$cookies.set("UserInfo", UserInfo);
    },
    Login(loginInfo) {
      console.log(loginInfo, "last");
      const loginData = new FormData();
      loginData.append("email", loginInfo.email);
      loginData.append("password", loginInfo.password);
      console.log(loginData);
      axios
        .post(SERVER_URL + "login/", loginData)
        .then((res) => {
          console.log(res);
          if (res.data == "") {
            alert("잘 못된 정보입니다.");
          } else {
            const Userdata = res.data;
            this.setCookie(Userdata);
            this.isLogined = true;
            this.$router.push({ name: "SPOTs" });
          }
        })
        .catch((err) => {
          alert("실패했습니다.");
          console.log(err);
        });
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