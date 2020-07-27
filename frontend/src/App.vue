<template>
  <div id="app">
    <notifications></notifications>
    <router-view @login-submit="Login" />
  </div>
</template>
<script>
  import axios from 'axios'

  export default {
    name: 'login',
    data() {
      return {}
    },
    methods: {
      setCookie(uid) {
          this.$cookies.set('uid', uid)
      },
      Login(loginInfo) {
        console.log(loginInfo, 'last')
        console.log(loginInfo.email)
        console.log(loginData)
        const loginData = new FormData();
        loginData.append('email', loginInfo.email);
        loginData.append('password', loginInfo.password);
        console.log(loginData)
        axios.post('http://localhost:8080/login', loginData)
            .then(res => {
                console.log(res)
                this.$router.push({ name: 'SPOTs' })
            })
            .catch(err => {
                alert('실패했습니다.')
                console.log(err)
            })
      },
    },      
  }
</script>