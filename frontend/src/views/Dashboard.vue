<template>
  <div>
    <base-header class="pb-8 pt-md-8 text-center">
      <!-- Card stats -->
      <h2 class="mb-4 text-white">SP<i class="ni ni-pin-3 text-red"></i>T 검색</h2>
      <div class="row d-flex flex-row justify-content-center">
        <base-dropdown class="mr-3">
          <base-button slot="title" type="secondary" class="dropdown-toggle">
            {{
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
            {{
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
            {{
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
        
        <form class="navbar-search navbar-search-dark form-inline mr-3 d-flex">
          <div class="form-group mb-0">
            <base-input placeholder="검색" v-model="keyword"></base-input>
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
        this.cityDatas.unshift({
          state_code: null,
          state_name: "전체",
        })
      })      
  },
  components: {
    ProjectsTable,
    TeamTable,
  },
  data() {
    return {
      cityDatas: "",
      stateDatas: "",
      dongDatas: "",
      cityN: "시(도)",
      stateN: "구(시)",
      dongN: "동",
      sidolist: [],
      citycode: null,
      statecode: null,
      dongcode: null,
      keyword: null,
      bgImg:"img/background/goal.jpg"
    };
  },
  methods: {
    choice1(state) {
      this.cityN = state.state_name;
      this.citycode = state.state_code;
      this.stateN = "구(시)"
      this.dongN = "동"
      this.choicestate(state.state_code);
    },
    choice2(city) {
      this.stateN = city.city_name;
      this.statecode = city.city_code;
      this.dongN = "동"
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
      if (b == "null") {
        stateForm.append("or 1=1", b);
        this.statecode = null;
        this.dongcode = null;
      } else {
        stateForm.append("where state_code like '" + b.substring(0,2) + "%'", b);
      }
      this.$axios
        .post(this.$SERVER_URL + "cityList", stateForm)
        .then((res) => {
          this.stateDatas = res.data;
          this.stateDatas.unshift({
            city_code: null,
            city_name: "전체",
          })
        })
       
    },
    choicedong(c) {
      const dongForm = new FormData();
      c = String(c);
      dongForm.append("city_code", c);
      if (c == "null") {
        dongForm.append("or 1=1", c);
        this.dongcode = null;
      } else {
        dongForm.append("where city_code like '" + c.substring(0,4) + "%'", c);
      }
      this.$axios
        .post(this.$SERVER_URL + "dongList", dongForm)
        .then((res) => {
          this.dongDatas = res.data;
          this.dongDatas.unshift({
            dong_code: null,
            dong_name: "전체",
          })
        })
        
    },
    checksido() {
      this.sidolist = [];
      this.sidolist.push(this.citycode);
      this.sidolist.push(this.statecode);
      this.sidolist.push(this.dongcode);
      if(this.keyword == "")
        this.keyword = null;

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

}
h1{
  margin-bottom:0px;
}
h2{
  margin-bottom:0px;
}
h3{
  margin-bottom:0px;
}
h4{
  margin-bottom:0px;
}
h5{
  margin-bottom:0px;
}
h6{
  margin-bottom:0px;
}
</style>