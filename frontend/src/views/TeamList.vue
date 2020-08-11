<template>
  <div>
    <base-header type="gradient-success" class="pb-6 pb-4 pt-5 pt-md-8">
      <!-- Card stats -->
      <div class="d-flex flex-row justify-content-between">
        <div class="d-flex flex-row align-items-center">
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
        <div>
          <router-link :to="{ name: '팀 프로필'}">
            <base-button v-if="haveTeam" type="success" size="lg">
              <h2 class="text-white">나의 팀 프로필</h2>
            </base-button>
          </router-link>
          <base-button v-if="!haveTeam" type="success" size="lg" @click="modals.create = true">
            <h2 class="text-white">팀 만들기</h2>
          </base-button>
        </div>
      </div>
    </base-header>
    <div class="container-fluid mt-4">
      <div class="row">
        <div class="col">
          <trank-table title="Light Table"></trank-table>
        </div>
      </div>
    </div>

    <modal :show.sync="modals.create"
      body-classes="p-0"
      modal-classes="modal-dialog-centered modal-sm">
      <card type="secondary" shadow
                  header-classes="bg-white pb-5"
                  body-classes="px-lg-5 py-lg-5"
                  class="border-0">
        <template>
          <div class="text-muted text-center mb-3">
            <h2>팀 만들기</h2>
          </div>
        </template>
        <template>
          <div class="text-center text-muted mb-4">
            <span class="text-dark">팀 생성을 위해 아래 항목을 꼼꼼히 기입해주세요.</span>
          </div>
          <form role="form">
            <div class="mb-3">
              <label>팀명</label>
              <base-input alternative
                addon-left-icon="ni ni-paper-diploma"
                v-model="teamData.nickname">
              </base-input>
            </div>
            <div class="mb-3">
              <base-dropdown class="mr-3">
                <base-button slot="title" type="secondary" class="dropdown-toggle">
                  시(도) : {{ teamData.statePick }}
                </base-button>
                <a
                  class="dropdown-item"
                  v-for="sData in teamData.stateDatas"
                  v-bind:key="sData"
                  @click="choiceS(sData)"
                  >{{ sData.state_name }}</a
                >
              </base-dropdown>
            </div>
            <div class="mb-3">
              <base-dropdown class="mr-3">
                <base-button slot="title" type="secondary" class="dropdown-toggle">
                  구(시) : {{ teamData.cityPick }}
                </base-button>
                <a
                  class="dropdown-item"
                  v-for="cData in teamData.cityDatas"
                  v-bind:key="cData"
                  @click="choiceC(cData)"
                  >{{ cData.city_name }}</a
                >
              </base-dropdown>
            </div>
            <div class="mb-3">
              <div class="bg-white border rounded px-1">
                <h3 class="align-middle m-1">주소 : {{ teamData.statePick }} {{ teamData.cityPick }}</h3>
              </div>
            </div>
            <div class="mb-3">
              <div class="text-center d-flex flex-row justify-content-between">
                <base-button size="lg">
                  <h2 class="text-white" @click="createTeam">신청하기</h2>
                </base-button>
                <base-button type="neutral" @click="modals.create = false">닫기</base-button>
              </div>
            </div>
          </form>
        </template>
      </card>
    </modal>
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
        this.teamData.stateDatas = res.data;
        this.teamData.stateDatas.unshift({
          city_code: null,
          city_name: null,
          dong_code: null,
          dong_name: null,
          state_code: "0000000000",
          state_name: "전체",
        })
        this.teamData.statePick = this.teamData.stateDatas[0].state_name
        console.log('o', this.teamData)
      })
      .catch((err) => {
        console.log(err);
      });
    if (this.$cookies.isKey("UserInfo")) {
      this.isLogined = true
      console.log('pp',this.$cookies.get("UserInfo"))
      if (this.$cookies.get("UserInfo").team_uid > 0) {
        console.log('pp',this.$cookies.get("UserInfo").team_uid)
        this.haveTeam = true
      }
    }
  },
  components: {
    "trank-table": teamList,
  },
  data() {
    return {
      cityDatas: "",
      stateDatas: "",
      cityN: "도",
      stateN: "시",
      isLogined: false,
      haveTeam: false,   
      modals: {
        create: false,
      },
      teamData: {
        nickname: "",
        stateDatas: [],
        cityDatas: [],
        statePick: "",
        cityPick: "시(도)를 선택해 주세요.",
        pickCode: "",
      },   
    };
  },
  methods: {
    choice1(state) {
      this.cityN = state.state_name;
      this.choicestate(state.state_code);
    },
    choice2(city) {
      this.stateN = city.city_name;
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
    choiceS(state) {
      console.log('state',state.state_name)
      this.teamData.statePick = state.state_name
      if (state.state_code == "0000000000") {
        this.teamData.cityPick = "시(도)를 선택해 주세요.";
        this.teamData.cityDatas = [];
      } else {
        var Cityform = new FormData()
        Cityform.append("state_code", state.state_code);
        axios.post(SERVER_URL + "cityList", Cityform)
          .then((res) => {
            this.teamData.cityDatas = res.data;
            console.log('city', this.teamData.cityDatas)
          })
          .catch((err) => {
            console.log(err);
          });
      }
    },
    choiceC(city) {
      this.teamData.cityPick = city.city_name
      this.teamData.pickCode = city.city_code
      console.log(city)
    },
    createTeam() {
      var createData = new FormData()
      createData.append('team_name', this.teamData.nickname)
      createData.append('city_code', this.teamData.pickCode)
      axios.post(SERVER_URL + "team/regist/", createData)
          .then((res) => {
            console.log(res)
            this.modals.create = false
            this.$router.push({ name: "팀 프로필" })
          })
          .catch((err) => {
            console.log(err);
          });
    }
  },
  mounted() {},
};
</script>
<style></style>
