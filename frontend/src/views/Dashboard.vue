<template>
  <div>
    <base-header type="gradient-success" class="pb-6 pb-4 pt-5 pt-md-8 text-center">
      <!-- Card stats -->
      <h2 class="mb-4 text-white">SP<i class="ni ni-pin-3 text-red"></i>T 검색</h2>
      <div class="row justify-content-center">
        <base-dropdown class="mr-3">
          <base-button slot="title" type="secondary" class="dropdown-toggle">
            시(도) : {{
            this.cityN
            }}
          </base-button>
          <a
            class="dropdown-item"
            v-for="(cityData, i) in cityDatas"
            v-bind:key="i"
            @click="choice1(cityData)"
          >{{ cityData.state_name }}</a>
        </base-dropdown>

        <base-dropdown class="mr-3">
          <base-button slot="title" type="secondary" class="dropdown-toggle">
            구(시) : {{
            this.stateN
            }}
          </base-button>
          <a
            class="dropdown-item"
            v-for="(stateData,i) in stateDatas"
            v-bind:key="i"
            @click="choice2(stateData)"
          >{{ stateData.city_name }}</a>
        </base-dropdown>

        <base-dropdown class="mr-3">
          <base-button slot="title" type="secondary" class="dropdown-toggle">
            동 : {{
            this.dongN
            }}
          </base-button>
          <a
            class="dropdown-item"
            v-for="(dongData,i) in dongDatas"
            v-bind:key="i"
            @click="choice3(dongData)"
          >{{ dongData.dong_name }}</a>
        </base-dropdown>

        <form class="navbar-search navbar-search-dark form-inline mr-3 d-none d-md-flex">
          <div class="form-group mb-0">
            <base-input placeholder="검색"  v-model="keyword"></base-input>
            <base-button class="ml-3" type="secondary" @click="checksido">검색</base-button>
          </div>
        </form>
      </div>
    </base-header>
    <div class="container-fluid">
      <div class="row mt-5">
        <div class="col">
          <projects-table :sidolist="sidolist" title="자유 SPOT"></projects-table>
        </div>
      </div>
      <div class="row mt-5">
        <div class="col">
          <team-table :sidolist="sidolist" type="dark" title="팀 SPOT"></team-table>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import ProjectsTable from "./Tables/ProjectsTable";
import TeamTable from "./Tables/TeamTable";

export default {
  name: "dashboard",
  created() {
    this.$axios
      .get(this.$SERVER_URL + "stateList")
      .then((res) => {
        this.cityDatas = res.data;
      })
      .catch((err) => {
        console.log(err);
      });
  },
  components: {
    ProjectsTable,
    TeamTable,
  },
  data() {
    return {
      cityDatas: null,
      stateDatas: null,
      dongDatas: null,
      cityN: null,
      stateN: null,
      dongN: null,
      sidolist: [],
      citycode: null,
      statecode: null,
      dongcode: null,
      keyword: null,
    };
  },
  methods: {
    choice1(state) {
      this.cityN = state.state_name;
      this.citycode = state.state_code;
      this.choicestate(state.state_code);
    },
    choice2(city) {
      this.stateN = city.city_name;
      this.statecode = city.city_code;
      this.choicedong(city.city_code);
    },
    choice3(dong) {
      this.dongcode = dong.dong_code;
      this.dongN = dong.dong_name;
    },

    choicestate(b) {
      const stateForm = new FormData();
      b = String(b);
      stateForm.append("state_code", b);
      this.$axios
        .post(this.$SERVER_URL + "cityList", stateForm)
        .then((res) => {
          this.stateDatas = res.data;
        })
        .catch((err) => {
          console.log(err);
        });
    },
    choicedong(c) {
      const dongForm = new FormData();
      c = String(c);
      dongForm.append("city_code", c);
      this.$axios
        .post(this.$SERVER_URL + "dongList", dongForm)
        .then((res) => {
          this.dongDatas = res.data;
        })
        .catch((err) => {
          console.log(err);
        });
    },
    checksido() {
      this.sidolist = [];
      this.sidolist.push(this.citycode);
      this.sidolist.push(this.statecode);
      this.sidolist.push(this.dongcode);
      this.sidolist.push(this.keyword);
    },
  },
  mounted() {},
};
</script>
<style type="text/css">
            @font-face {
    font-family: 'paybooc-Bold';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-07@1.0/paybooc-Bold.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}
            
div{
 font-family: 'paybooc-Bold';
 font-size: 30px;
}
h2{
  margin-bottom:0px;
}</style>