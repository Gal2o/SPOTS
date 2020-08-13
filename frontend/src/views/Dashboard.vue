<template>
  <div>
    <base-header type="gradient-success" class="pb-6 pb-4 pt-5 pt-md-8">
      <!-- Card stats -->
      <div class="row">
        <base-dropdown class="mr-3">
          <base-button slot="title" type="secondary" class="dropdown-toggle">
            시(도) : {{
            this.cityN
            }}
          </base-button>
          <a
            class="dropdown-item"
            v-for="cityData in cityDatas"
            v-bind:key="cityData"
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
            v-for="stateData in stateDatas"
            v-bind:key="stateData"
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
            v-for="dongData in dongDatas"
            v-bind:key="dongData"
            @click="choice3(dongData)"
          >{{ dongData.dong_name }}</a>
        </base-dropdown>

        <form class="navbar-search navbar-search-dark form-inline mr-3 d-none d-md-flex">
          <div class="form-group mb-0">
            <base-input placeholder="검색" class="input-group-alternative" v-model="keyword"></base-input>
            <base-button class="ml-3" type="success" icon="ni ni-bold-right" @click="checksido"></base-button>
          </div>
        </form>
      </div>
    </base-header>
    <div class="container-fluid">
      <div class="row">
        <div class="col">
          <projects-table :sidolist="sidolist" title="자유 SPOT"></projects-table>
        </div>
      </div>
      <div class="row mt-5">
        <div class="col">
          <team-table type="dark" title="팀 SPOT"></team-table>
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
<style></style>
