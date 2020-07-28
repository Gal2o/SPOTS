<template>
  <div id="app">
    <notifications></notifications>
    <router-view @login-submit="Login" />
  </div>
</template>
<script>
  import axios from 'axios'

  const SERVER_URL = 'http://localhost:8080/'
  export default {
    name: 'login',
    data() {
      return {
        isLogined : false
      }
    },
    methods: {
      setCookie(uid) {
        console.log(typeof(uid))
        this.$cookies.set('uid', uid)
      },
      Login(loginInfo) {
        console.log(loginInfo, 'last')
        const loginData = new FormData();
        loginData.append('email', loginInfo.email);
        loginData.append('password', loginInfo.password);
        console.log(loginData)
        axios.post(SERVER_URL + 'login/', loginData)
            .then(res => {
                console.log(res)
                const Loginuid = String(res.data.uid)
                this.setCookie(Loginuid)
                this.isLogined = true
            })
            .catch(err => {
                alert('실패했습니다.')
                console.log(err)
            })
      },
    },      
  }
</script>