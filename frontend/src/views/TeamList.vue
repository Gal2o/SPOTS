<template>
  <div>
     <base-header class="pb-8 pt-md-8 mb-4 text-center">
      <!-- Card stats -->
      <h2 class="mb-4 text-white">팀 검색</h2>
      <div class="d-flex flex-row justify-content-center">
        <div class="d-flex flex-row align-items-center">
          <base-dropdown class="mr-3">
            <base-button slot="title" type="secondary" class="dropdown-toggle">{{
              this.cityN
            }}</base-button>
            <a
              class="dropdown-item"
              v-for="(cityData, i) in cityDatas"
              v-bind:key="i"
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
              v-for="(stateData, i) in stateDatas"
              v-bind:key="i"
              @click="choice2(stateData)"
              >{{ stateData.city_name }}</a
            >
          </base-dropdown>

          <form
            class="navbar-search navbar-search-dark form-inline mr-3 d-flex"
          >
            <div class="form-group mb-0">
              <base-input
                placeholder="검색"
                class="input-group-alternative"
                alternative
                addon-right-icon="fas fa-search"
                v-model="searchWord"
                @keydown.enter="searchTeam"
                @click="searchTeam"
              ></base-input>
            </div>
          </form>
        </div>
        <div>
       </div>
      </div> 
    </base-header><div class="container-fluid mt-4 mb-0 text-center">
      <router-link :to="{ name: '팀 프로필'}">
            <base-button v-if="haveTeam" block type="warning" size="lg">
              <h2 class="text-white">나의 팀 프로필 보기!</h2>
            </base-button>
          </router-link>
          <base-button v-if="isLogined && !haveTeam" block type="info" size="lg" @click="modals.create = true">
            <h2 class="text-white mb-0">팀 만들기!</h2>
          </base-button>
          <base-button v-if="!isLogined" block type="info" size="lg" @click="modals.loginalert = true">
            <h2 class="text-white mb-0">팀 만들기!</h2>
          </base-button>
        </div>
    <div class="container-fluid mt-4">
      <div class="row">
        <div class="col">
          <trank-table title="Light Table" :FreerankData="FreerankData"></trank-table>
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
                  v-for="(sData,i) in teamData.stateDatas"
                  v-bind:key="i"
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
                  v-for="(cData,i) in teamData.cityDatas"
                  v-bind:key="i"
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

    <modal
          :show.sync="modals.loginalert"
          gradient="danger"
          modal-classes="modal-danger modal-dialog-centered"
        >
          <div class="py-3 text-center">
            <i class="ni ni-bell-55 ni-3x"></i>
            <h4 class="heading mt-4">로그인을 하셔야 이용할 수 있습니다.</h4>
            <p>로그인 페이지로 이동하셔서 로그인을 먼저 진행해주세요.</p>
          </div>

          <template slot="footer">
            <router-link to="/login">
              <base-button type="white">로그인하기</base-button>
            </router-link>
            <base-button
              type="link"
              text-color="white"
              class="ml-auto"
              @click="modals.loginalert = false"
            >닫기</base-button>
          </template>
        </modal>
  </div>
</template>

<script>
import teamList from "./Tables/teamListTable";

export default {
  name: "dashboard",
  created() {
    this.$axios
      .get(this.$SERVER_URL + "stateList")
      .then((res) => {
        this.cityDatas = res.data;
        this.teamData.stateDatas = res.data;
        this.teamData.stateDatas.unshift({
          dong_code: null,
          dong_name: null,
          state_code: "0000000000",
          state_name: "전체",
        })
        this.teamData.statePick = this.teamData.stateDatas[0].state_name

      })
     
    if (this.$cookies.isKey("UserInfo")) {
      this.isLogined = true
  
      if (this.$cookies.get("UserInfo").team_uid > 0) {
  
        this.haveTeam = true
      }
    }
    var nowhere = new FormData()
    nowhere.append('where', "")
    this.$axios.post(this.$SERVER_URL + "team/list", nowhere)
      .then((res) => {
        this.FreerankData = res.data;
        for (var a=0; a<this.FreerankData.length; a++){
        this.FreerankData[a].team_rate = Math.ceil(this.FreerankData[a].team_rate)
      }
     
      })
    
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
      searchWord: "",   
      modals: {
        create: false,
        loginalert: false,
      },
      teamData: {
        nickname: "",
        stateDatas: [],
        cityDatas: [],
        statePick: "",
        cityPick: "시(도)를 선택해 주세요.",
        pickCode: "",
      },   
      FreerankData:[],
    };
  },
  methods: {
    choice1(state) {
      this.cityN = state.state_name;
      this.stateN = "시"
      this.choicestate(state.state_code);
    },
    choice2(city) {
      this.stateN = city.city_name;
      this.TeamList("where city_code like '"+city.city_code.substring(0,4)+"%'");
    },


    choicestate(b) {
      const stateForm = new FormData();
      b = String(b);
      stateForm.append("state_code", b);
      if (b == "0000000000") {
        this.TeamList("");
      }else{
        this.TeamList("where city_code like '"+b.substring(0,2)+"%'");
      }
      this.$axios
        .post(this.$SERVER_URL + "cityList", stateForm)
        .then((res) => {
          this.stateDatas = res.data;
        })
       
    },
    choiceS(state) {
      
      this.teamData.statePick = state.state_name
      if (state.state_code == "0000000000") {
        this.teamData.cityPick = "시(도)를 선택해 주세요.";
        this.teamData.cityDatas = [];
      } else {
        var Cityform = new FormData()
        Cityform.append("state_code", state.state_code);
        this.$axios.post(this.$SERVER_URL + "cityList", Cityform)
          .then((res) => {
            this.teamData.cityDatas = res.data;
         
          })
         
      }
    },
    choiceC(city) {
      this.teamData.cityPick = city.city_name
      this.teamData.pickCode = city.city_code
      
    },
    createTeam() {
      var createData = new FormData()
      createData.append('team_name', this.teamData.nickname)
      createData.append('city_code', this.teamData.pickCode)
      createData.append('captain_uid', this.$cookies.get('UserInfo').uid)
      this.$axios.post(this.$SERVER_URL + "team/regist/", createData)
          .then(() => {
            
            this.modals.create = false
            var userform = new FormData()
            userform.append('uid', this.$cookies.get('UserInfo').uid)
            this.$axios.post(this.$SERVER_URL + "user/detail2/", userform)
              .then((res) => {
             
                this.$cookies.remove('UserInfo')
                this.$cookies.set('UserInfo', res.data)
               
                this.$router.push({ name: "팀 프로필" })
              })
              
          })
         
    },
    TeamList(where) {
      var WhereData = new FormData();
      WhereData.append('where', where)
      this.$axios.post(this.$SERVER_URL + "team/list/", WhereData)
        .then(res => {
      
          this.FreerankData = res.data;
        })
        
    },
    searchTeam() {
      var subList = []
      for(var i=0; i < this.FreerankData.length; i++) {
        if (this.FreerankData[i].team_name.indexOf(this.searchWord) != -1) {
          subList.push(this.FreerankData[i])
        }
      }
      this.FreerankData = subList
      this.searchWord = ""
    },
  },
  mounted() {},
};
</script>
<style></style>
