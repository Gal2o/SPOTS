<template>
  <div>
    <base-header type="gradient-success" class="pb-6 pb-8 pt-5 pt-md-8">
      <!-- Card stats -->
      <div class="row">
        <base-dropdown class="mr-3">
          <base-button slot="title" type="secondary" class="dropdown-toggle">{{ this.cityN }}</base-button>
          <a
            class="dropdown-item"
            v-for="cityData in cityDatas"
            v-bind:key="cityData"
            @click="choice1(cityData)"
          >{{ cityData.city_name }}</a>
        </base-dropdown>

        <base-dropdown class="mr-3">
          <base-button slot="title" type="secondary" class="dropdown-toggle">{{ this.stateN }}</base-button>
          <a
            class="dropdown-item"
            v-for="stateData in stateDatas"
            v-bind:key="stateData"
            @click="choice2(stateData)"
          >{{ stateData.state_name }}</a>
        </base-dropdown>

        <base-dropdown class="mr-3">
          <base-button slot="title" type="secondary" class="dropdown-toggle">{{ this.dongN }}</base-button>
          <a
            class="dropdown-item"
            v-for="dongData in dongDatas"
            v-bind:key="dongData"
            @click="choice3(dongData)"
          >{{ dongData.dong_name }}</a>
        </base-dropdown>

        <form class="navbar-search navbar-search-dark form-inline mr-3 d-none d-md-flex">
          <div class="form-group mb-0">
            <base-input
              placeholder="검색"
              class="input-group-alternative"
              alternative
              addon-right-icon="fas fa-search"
            ></base-input>
          </div>
        </form>
      </div>
    </base-header>
    <div class="container-fluid mt--7">
      <div class="row">
        <div class="col">
          <projects-table title="자유 SPOT"></projects-table>
        </div>
      </div>
      <div class="row mt-5">
        <div class="col">
          <projects-table type="dark" title="팀 SPOT"></projects-table>
        </div>
      </div>
    </div>
  </div>
</template>


<script>
import ProjectsTable from "./Tables/ProjectsTable";
import axios from "axios";
const SERVER_URL = "http://localhost:8080/";

export default {
  name: "dashboard",
  created() {
    axios
      .get(SERVER_URL + "cityList")
      .then((res) => {
        console.log(res);
        this.cityDatas = res.data;
      })
      .catch((err) => {
        console.log(err);
      });
  },
  components: {
    ProjectsTable,
  },
  data() {
    return {
      cityDatas: "",
      stateDatas: "",
      dongDatas: "",
      cityN: "도",
      stateN: "시",
      dongN: "동",
    };
  },
  methods: {
    choice1(city) {
      this.cityN = city.city_name;
      console.log("check");
      this.choicestate(city.city_code);
    },
    choice2(state) {
      this.stateN = state.state_name;
      console.log("check");
      this.choicedong(state.state_code);
    },
    choice3(dong) {
      this.dongN = dong.dong_name;
      console.log("check");
    },

    choicestate(b) {
      const stateForm = new FormData();
      b = String(b);
      stateForm.append("city_code", b);
      axios
        .post(SERVER_URL + "stateList", stateForm)
        .then((res) => {
          console.log(res);
          this.stateDatas = res.data;
        })
        .catch((err) => {
          console.log(err);
        });
    },
    choicedong(c) {
      const dongForm = new FormData();
      c = String(c);
      dongForm.append("state_code", c);
      axios
        .post(SERVER_URL + "dongList", dongForm)
        .then((res) => {
          console.log(res);
          this.dongDatas = res.data;
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
  mounted() {},
};
</script>
<style></style>
