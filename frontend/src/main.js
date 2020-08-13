/*!

=========================================================
* Vue Argon Dashboard - v1.0.0
=========================================================

* Product Page: https://www.creative-tim.com/product/argon-dashboard
* Copyright 2019 Creative Tim (https://www.creative-tim.com)
* Licensed under MIT (https://github.com/creativetimofficial/argon-dashboard/blob/master/LICENSE.md)

* Coded by Creative Tim

=========================================================

* The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

*/
import Vue from 'vue'
import App from './App.vue'
import router from './router'
import './registerServiceWorker'
import ArgonDashboard from './plugins/argon-dashboard'
import VueCookie from 'vue-cookies'
import axios from 'axios'

Vue.config.productionTip = false

Vue.prototype.$axios = axios;
const SERVER_URL = "http://localhost:8080/spots/";
Vue.prototype.$SERVER_URL = SERVER_URL;

Vue.use(ArgonDashboard)
Vue.use(VueCookie)
new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
