<template>
  <div>
    <base-header
      class="header pb-4 pt-5 pt-lg-8 d-flex align-items-center"
      style="min-height: 600px; background-size: cover; background-position: center top;"
    >
      <!-- Mask -->
      <span class="mask bg-gradient-success opacity-8"></span>
      <!-- Header container -->
      <div class="container-fluid d-flex align-items-center">
        <div class="row">
          <div class="col-lg-12 col-md-10">
            <h1 class="display-2 text-white">팀 {{ model.team_name }}</h1>
            <p class="text-white mt-0 mb-5">팀의 정보 확인 및 수정이 가능합니다.</p>
          </div>
        </div>
      </div>
    </base-header>

    <div class="container-fluid mt--7">
      <div class="row">
        <div class="col-xl-4 order-xl-2 mb-5 mb-xl-0">
          <div class="card card-profile shadow">
            <div class="row justify-content-center">
              <div class="col-lg-3 order-lg-2">
                <div class="card-profile-image">
                  <a href="#">
                    <img src="img/theme/team-4-800x800.jpg" class="rounded-circle" />
                  </a>
                </div>
              </div>
            </div>
            <div class="card-header text-center border-0 pt-8 pt-md-4 pb-0 pb-md-4"></div>
            <div class="card-body pt-0 pt-md-4">
              <div class="row">
                <div class="col">
                  <div class="card-profile-stats d-flex justify-content-center mt-md-5">
                    <div>
                      <span class="heading">{{ model.team_win }}</span>
                      <span class="description">승</span>
                    </div>
                    <div>
                      <span class="heading">{{ model.team_draw }}</span>
                      <span class="description">무</span>
                    </div>
                    <div>
                      <span class="heading">{{ model.team_lose }}</span>
                      <span class="description">패</span>
                    </div>
                  </div>
                </div>
              </div>
              <div class="text-center">
                <h3>{{model.team_name }}</h3>
                <div>
                  인원 : {{ model.player_num }}
                </div>
              </div>
            </div>
          </div>
          <div class="card card-profile shadow">
            <div class="card-body pt-0 pt-md-4">
              <div class="text-center">
                <h3>선수 리스트</h3>
                <base-table
                  class="table align-items-center table-flush table-borderless"
                  tbody-classes="list"
                  :data="player"
                >
                  <template slot="columns">
                    <th>이름</th>
                    <th>승리</th>
                    <th>무승부</th>
                    <th>패배</th>
                  </template>

                  <template slot-scope="{ row }">
                    <th>
                      <span class="status">{{ row.nickname }}</span>
                    </th>
                    <td>
                      <span class="status">{{ row.win }}</span>
                    </td>
                    <td>
                      <span class="status">{{ row.draw }}</span>
                    </td>
                    <td>
                      <span class="status">{{ row.lose }}</span>
                    </td>
                  </template>
                </base-table>
              </div>
            </div>
          </div>
        </div>

        <div class="col-xl-8 order-xl-1">
          <card shadow type="secondary">
            <div slot="header" class="bg-white border-0">
              <div class="row align-items-center">
                <div class="col-8">
                  <h3 class="mb-0">팀 정보</h3>
                </div>
              </div>
            </div>
            <template>
              <form @submit.prevent>
                <div class="pl-lg-4">
                  <div class="row">
                    <div class="col-lg-6 my-1">
                      <label><b>팀명</b></label>
                      <div class="bg-white border rounded px-1">
                        <h3 class="align-middle m-2">{{ model.team_name }}</h3>
                      </div>
                    </div>
                    <div class="col-lg-6 my-1">
                      <label><b>승률</b></label>
                      <div class="bg-white border rounded px-1">
                        <h3 class="align-middle m-2">{{ model.team_rate }}%</h3>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="pl-lg-4">
                  <div class="row">
                    <div class="col-lg-6 my-1">
                      <label><b>시(도)</b></label>
                      <div>
                        <base-dropdown class="mr-3">
                          <base-button slot="title" type="white" class="dropdown-toggle">
                            {{ teamData.statePick }}
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
                    </div>
                    <div class="col-lg-6 my-1">
                      <label><b>구(시)</b></label>
                      <div>
                        <base-dropdown class="mr-3">
                          <base-button slot="title" type="white" class="dropdown-toggle">
                            {{ teamData.cityPick }}
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
                    </div>
                    <div class="col-12 my-1">
                      <label><b>주소</b></label>
                      <div class="bg-white border rounded px-1">
                        <h3 class="align-middle m-2">{{ teamData.statePick }} {{ teamData.cityPick }}</h3>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="pl-lg-4">
                  <div class="form-group my-1">
                    <label><b>팀 소개</b></label>
                      <textarea
                        rows="4"
                        class="form-control form-control-alternative"
                        placeholder="A few words about you ..."
                        v-model="model.team_intro"
                      >
                        간략히 팀을 소개해 주세요
                      </textarea>
                  </div>
                </div>
                <div class="d-flex justify-content-around mt-3">
                  <base-button type="info" size="lg">
                    <h3 class="text-white mb-0">팀정보 수정</h3>
                  </base-button>
                  <base-button type="success" size="lg" @click="modals.applyList = true">
                    <h3 class="text-white mb-0">가입신청서 확인</h3>
                  </base-button>
                </div>
              </form>
            </template>
          </card>
        </div>
      </div>
    </div>
    <modal :show.sync="modals.applyList"
      body-classes="p-0"
      modal-classes="modal-dialog-centered modal-lg">
      <card type="secondary" shadow
                  header-classes="bg-white pb-5"
                  body-classes="px-lg-5 py-lg-5"
                  class="border-0">
        <template>
          <div class="btn-wrapper text-center d-flex flex-row justify-content-end mb-3">
            <base-button type="neutral" @click="modalSwitch(3)">
                닫기
            </base-button>
          </div>
          <div class="text-center text-muted mb-4">
              <h1>가입신청서 리스트</h1>
            </div>
        </template>
        <div class="table-responsive">
          <base-table
            class="table align-items-center table-flush"
            tbody-classes="list"
            :data="applyPlayer"
          >
            <template slot="columns">
              <th>이름</th>
              <th>경기수</th>
              <th>승률</th>
              <th>골 / 도움</th>
              <th>한마디</th>
              <th></th>
            </template>

            <template slot-scope="{ row }">
              <th scope="row">
                <div class="media align-items-center">
                  <div class="media-body">
                    <span class="name mb-0 text-sm" @onclick:> {{ row.nickname }}</span>
                  </div>
                </div>
              </th>
              <td>
                <span class="status">{{ row.win + row.lose + row.draw}}</span>
              </td>
              <td>
                <span class="status">{{ row.rate }}%</span>
              </td>
              <td>
                <span class="status">{{ row.goal }} / {{ row.assist }}</span>
              </td> 
              <td>
                <span class="status">{{ row.comment }}</span>
              </td>              
              <td class="text-right">
                <base-button type="primary" size="sm" @click="showTeam(row)">
                  <h2 class="text-white">승인</h2>
                </base-button>
                <base-button type="danger" size="sm" @click="showTeam(row)">
                  <h2 class="text-white">취소</h2>
                </base-button>
              </td>
            </template>
          </base-table>
        </div>
      </card>
    </modal>
  </div>
</template>
<script>
import axios from "axios";
const SERVER_URL = "http://localhost:8080/";
export default {
  name: "user-profile",
  data() {
    return {
      model: {
        team_name: "",
        team_intro: "",
        team_rate: "",
        team_win: "",
        team_lose: "",
        team_draw: "",
        captain_uid: "",
        city_code: "",
        player_num: "",
      },
      player: Object,
      apply: [],
      applyPlayer: [],
      modals: {
        applyList: false,
        applyDetail: false,
      },
      teamData: {
        stateDatas: [],
        cityDatas: [],
        statePick: "",
        cityPick: "",
        pickCode: "",
      },
    };
  },
  created() {
    const data = new FormData();
    data.append("uid", this.$cookies.get("UserInfo").team_uid);
    axios
      .post(SERVER_URL + "/team/detail", data)
      .then((res) => {
        this.model = res.data;
      })  
      .catch((err) => {
        console.log(err);
      });
    axios
      .post(SERVER_URL + "/team/userList", data)
      .then((res) => {
        console.log(res.data);
        this.player = res.data;
      })  
      .catch((err) => {
        console.log(err);
      });
    axios
      .post(SERVER_URL + "team/applyList/", data)
      .then((res) => {
        console.log('apply',res.data);
        let applySub = res.data;
        for(var i=0; i < applySub.length; i++) {
          var j = i
          var userForm = new FormData()
          userForm.append('uid', applySub[i].user_uid)
          axios.post(SERVER_URL + 'user/detail2/', userForm)
            .then(res => {
              var applyData = res.data
              applyData.comment = applySub[j].comment
              console.log('applyData', applyData);
              this.applyPlayer.push(applyData)
            })
            .catch(err => {
              console.log(err)
            })
        }
      })  
      .catch((err) => {
        console.log(err);
      });
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
        });
        this.pickData()
      })
      .catch((err) => {
        console.log(err);
      });
  },
  methods: {
    showTeam(TeamInfo) {
      this.teamData = TeamInfo
      this.modals.teamInfo = true
    },
    modalSwitch(switchData) {
      if (switchData == 1) {
        this.modals.applyList = false
        this.modals.applyDetail = true
      } else if (switchData == 2) {
        this.modals.applyList = true
        this.modals.applyDetail = false
      } else if (switchData == 3) {
        this.modals.applyList = false
        this.modals.applyDetail = false
      }
      console.log(this)
    },
    pickData() {      
      var dummyState = this.model.city_code.slice(0, 2) + "00000000"
      console.log('dummy', dummyState)
      for(var i=0; i < this.teamData.stateDatas.length; i++) {
        if (this.teamData.stateDatas[i].state_code == dummyState) {
          this.teamData.statePick = this.teamData.stateDatas[i].state_name
        }
      }
      console.log('o', this.teamData)
      var Cityform = new FormData()
      Cityform.append("state_code", dummyState);
      axios.post(SERVER_URL + "cityList", Cityform)
        .then((res) => {
          this.teamData.cityDatas = res.data;
          console.log('city', this.teamData.cityDatas)
          for(var i=0; i < this.teamData.stateDatas.length; i++) {
            if (this.teamData.cityDatas[i].city_code == this.model.city_code) {
              this.teamData.cityPick = this.teamData.cityDatas[i].city_name
            }
          }
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
      console.log('C',city)
    },
  },
};
</script>
<style></style>
