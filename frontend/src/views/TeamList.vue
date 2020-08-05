<template>
  <div>
    <base-header type="gradient-success" class="pb-6 pb-4 pt-5 pt-md-8">
      <!-- Card stats -->
      <div class="row">
        <base-dropdown class="mr-3">
          <base-button slot="title" type="secondary" class="dropdown-toggle">{{
            this.cityN
          }}</base-button>
          <a
            class="dropdown-item"
            v-for="cityData in cityDatas"
            v-bind:key="cityData"
            @click="choice1(cityData)"
            >{{ cityData.state_name }}</a
          >
        </base-dropdown>

        <base-dropdown class="mr-3">
          <base-button slot="title" type="secondary" class="dropdown-toggle">{{
            this.stateN
          }}</base-button>
          <a
            class="dropdown-item"
            v-for="stateData in stateDatas"
            v-bind:key="stateData"
            @click="choice2(stateData)"
            >{{ stateData.city_name }}</a
          >
        </base-dropdown>

        <base-dropdown class="mr-3">
          <base-button slot="title" type="secondary" class="dropdown-toggle">{{
            this.dongN
          }}</base-button>
          <a
            class="dropdown-item"
            v-for="dongData in dongDatas"
            v-bind:key="dongData"
            @click="choice3(dongData)"
            >{{ dongData.dong_name }}</a
          >
        </base-dropdown>

        <form
          class="navbar-search navbar-search-dark form-inline mr-3 d-none d-md-flex"
        >
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
    <div class="container-fluid mt-4">
      <div class="row">
        <div class="col">
          <trank-table title="Light Table"></trank-table>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import teamList from "./Tables/teamListTable";
import axios from "axios";
const SERVER_URL = "http://localhost:8080/";

export default {
  name: "dashboard",
  created() {
    axios
      .get(SERVER_URL + "stateList")
      .then((res) => {
        this.cityDatas = res.data;
      })
      .catch((err) => {
        console.log(err);
      });
  },
  components: {
    "trank-table": teamList,
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
    choice1(state) {
      this.cityN = state.state_name;
      this.choicestate(state.state_code);
    },
    choice2(city) {
      this.stateN = city.city_name;
      this.choicedong(city.city_code);
    },
    choice3(dong) {
      this.dongN = dong.dong_name;
    },

    choicestate(b) {
      const stateForm = new FormData();
      b = String(b);
      stateForm.append("state_code", b);
      axios
        .post(SERVER_URL + "cityList", stateForm)
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
      axios
        .post(SERVER_URL + "dongList", dongForm)
        .then((res) => {
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
