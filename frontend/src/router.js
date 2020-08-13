import Vue from "vue";
import Router from "vue-router";
import DashboardLayout from "@/layout/DashboardLayout";
import AuthLayout from "@/layout/AuthLayout";
import CreditLayout from "@/layout/CreditLayout"
Vue.use(Router);

export default new Router({
  linkExactActiveClass: "active",
  routes: [
    {
      path: "/",
      redirect: "dashboard",
      component: DashboardLayout,
      children: [
        {
          path: "/dashboard",
          name: "SPOTs",
          // route level code-splitting
          // this generates a separate chunk (about.[hash].js) for this route
          // which is lazy-loaded when the route is visited.
          component: () =>
            import(/* webpackChunkName: "demo" */ "./views/Dashboard.vue"),
        },
        {
          path: "/profile",
          name: "프로필",
          component: () =>
            import(/* webpackChunkName: "demo" */ "./views/UserProfile.vue"),
        },
        {
          path: "/Teamprofile",
          name: "팀 프로필",
          component: () =>
            import(/* webpackChunkName: "demo" */ "./views/TeamProfile.vue"),
        },
        {
          path: "/maps",
          name: "경기장 정보",
          component: () =>
            import(/* webpackChunkName: "demo" */ "./views/Maps.vue"),
        },
        {
          path: "/tables",
          name: "랭킹",
          component: () =>
            import(/* webpackChunkName: "demo" */ "./views/Tables.vue"),
        },
        {
          path: "/dashboard/FreeMatch/:uid",
          name: "자유 SPOT",
          component: () => import("./views/Dashboard/FreeMatchRoom.vue"),
        },
        {
          path: "/dashboard/FreeMatch/:uid/ManagerPage",
          name: "매니저 평가",
          component: () => import("./views/Dashboard/ManagerPage.vue"),
        },
        {
          path: "/dashboard/TeamMatch/:uid",
          name: "팀 SPOT",
          component: () => import("./views/Dashboard/TeamMatchRoom.vue"),
        },
        {
          path: "/teamlist",
          name: "팀 리스트",
          component: () => import("./views/TeamList.vue"),
        },
      ],
    },
    {
      path: "/",
      redirect: "login",
      component: AuthLayout,
      children: [
        {
          path: "/login",
          name: "login",
          component: () =>
            import(/* webpackChunkName: "demo" */ "./views/Login.vue"),
        },
        {
          path: "/register",
          name: "register",
          component: () =>
            import(/* webpackChunkName: "demo" */ "./views/Register.vue"),
        },
      ],
    },
    {
      path: "/",
      redirect: "/credit",
      component: CreditLayout,
      children: [
        {
          path: "/success/:room_uid/:uid/:positionnum/:team_entry_uid",
          name: "CreditComplete",
          component: () =>
            import("./views/CreditComplete.vue"),
        },  
      ],
    },
  ],
});
