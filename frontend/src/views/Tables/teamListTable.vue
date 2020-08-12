<template>
  <div class="card shadow" :class="type === 'dark' ? 'bg-default' : ''">
    <div
      class="card-header border-0"
      :class="type === 'dark' ? 'bg-transparent' : ''"
    >
      <div class="row align-items-center">
        <div class="col">
          <h3 class="mb-0" :class="type === 'dark' ? 'text-white' : ''">
            {{ this.title }}
          </h3>
        </div>
      </div>
    </div>

    <div class="table-responsive">
      <base-table
        class="table align-items-center table-flush"
        :class="type === 'dark' ? 'table-dark' : ''"
        :thead-classes="type === 'dark' ? 'thead-dark' : 'thead-light'"
        tbody-classes="list"
        :data="FreerankData"
      >
        <template slot="columns">
          <th>팀 이름</th>
          <th>인원</th>
          <th>승리</th>
          <th>무승부</th>
          <th>패배</th>
          <th>승률</th>
          <th></th>
        </template>

        <template slot-scope="{ row }">
          <th scope="row">
            <div class="media align-items-center">
              <div class="media-body">
                <span class="name mb-0 text-sm" @onclick:> {{ row.team_name }}</span>
              </div>
            </div>
          </th>
          <td>
            <span class="status">{{ row.player_num }}</span>
          </td>
          <td>
            <span class="status">{{ row.team_win }}</span>
          </td>
          <td>
            <span class="status">{{ row.team_draw }}</span>
          </td>
          <td>{{ row.team_lose }}</td>
          <td>
            <span class="status">{{ row.team_rate }}%</span>
          </td>
          <td class="text-right">
            <base-button v-if="isLogined" type="success" size="s" @click="showTeam(row)">
              <h2 class="text-white">더보기</h2>
            </base-button>
            <base-button v-if="!isLogined" type="success" size="lg" @click="modals.loginalert = true">
              <h2 class="text-white">입장하기</h2>
            </base-button>
          </td>
        </template>
      </base-table>
    </div>
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

    <modal :show.sync="modals.teamInfo" body-classes="p-0" modal-classes="modal-dialog modal-md">
      <card
        type="secondary"
        shadow
        header-classes="bg-white pb-5"
        body-classes="px-lg-5 py-lg-5"
        class="border-0"
      >
        <template>
          <div class="text-muted text-center mb-3">
            <h1>팀 정보</h1>
          </div>
        </template>
        <template>
          <table class="m-3">
            <tr>
              <th>팀명 : {{ teamData.team_name }}</th>
            </tr>
            <tr>
              <th>팀소개 : {{ teamData.team_intro }}</th>
            </tr>
            <tr>
              <th>인원 : {{ teamData.player_num }}명</th>
            </tr>
            <tr>
              <th>팀전적 : {{ teamData.team_win }}승 {{ teamData.team_draw }}무 {{ teamData.team_lose }}패</th>
            </tr>
            <tr>
              <th>승률 : {{ teamData.team_rate }}%</th>
            </tr>
            <tr>
              <th>팀장 : {{ headPlayer }} </th>
            </tr>
          </table>
          <!-- isLogined를 신청상태인지확인 -->
          <div class="text-center d-flex flex-row justify-content-between">
            <base-button v-if="isLogined && !haveTeam" type="success" size="lg" @click="modalSwitch(1)">
              <h2 class="text-white">가입하기</h2>
            </base-button>
            <base-button type="secondary" size="lg" @click="modals.teamInfo = false">
              <h2 class="text-dark">닫기</h2>
            </base-button>
          </div>
        </template>
      </card>
    </modal> 

    <modal :show.sync="modals.joinTeam"
      body-classes="p-0"
      modal-classes="modal-dialog-centered modal-sm">
      <card type="secondary" shadow
                  header-classes="bg-white pb-5"
                  body-classes="px-lg-5 py-lg-5"
                  class="border-0">
        <template>
          <div class="btn-wrapper text-center d-flex flex-row justify-content-end mb-3">
            <base-button type="neutral" @click="modalSwitch(2)">
                뒤로가기
            </base-button>

            <base-button type="neutral" @click="modalSwitch(3)">
                닫기
            </base-button>
          </div>
          <div class="text-muted text-center mb-3">
            <h2>팀 가입신청</h2>
          </div>
        </template>
        <template>
          <div class="text-center text-muted mb-4">
            <h1>가입신청서</h1>
          </div>
          <form role="form">
            <div class="mb-3">
              <label>팀명</label>
              <base-input alternative
                addon-left-icon="ni ni-paper-diploma"
                v-model="apply.teamname"
                readonly>
              </base-input>
            </div>
            <div class="mb-3">
              <label>가입신청 내용</label>              
              <textarea alternative
                rows="4" style="width:100%; resize:none"
                v-model="apply.comment"
                class="rounded">
              </textarea>
              <div class="text-center d-flex flex-row justify-content-center">
                <base-button size="lg" @click="ApplyGo">
                  <h2 class="text-white">신청하기</h2>
                </base-button>
              </div>
            </div>
          </form>
        </template>
      </card>
    </modal>


    <div
      class="card-footer d-flex justify-content-between"
      :class="type === 'dark' ? 'bg-transparent' : ''"
    ></div>
  </div>
</template>
<script>
import axios from "axios";
const SERVER_URL = "http://localhost:8080/";

export default {
  name: "frank-table",
  components: {},
  created() {
    if (this.$cookies.isKey("UserInfo")) {
      this.isLogined = true
      console.log('pp',this.$cookies.get("UserInfo"))
      if (this.$cookies.get("UserInfo").team_uid > 0) {
        console.log('pp',this.$cookies.get("UserInfo").team_uid)
        this.haveTeam = true
      }
    }
    this.TeamList("")
  },
  props: {
    type: {
      type: String,
    },
  },
  data() {
    return {
      isLogined: false,
      haveTeam: false,
      FreerankData: [],
      title: "팀 리스트",
      teamData: Object,
      modals: {
        loginalert: false,
        teamInfo: false,
        joinTeam: false,
      },
      headPlayer: "",
      apply: {
        teamname: "",
        teamuid: 0,
        comment: "",
      },
    };
  },
  methods: {
    showTeam(TeamInfo) {
      this.teamData = TeamInfo
      this.apply.teamname = this.teamData.team_name
      this.apply.teamuid = this.teamData.uid
      this.modals.teamInfo = true
      const team_uid = new FormData();
      team_uid.append("uid", this.teamData.uid);
      axios.post(SERVER_URL + "/team/userList", team_uid)
        .then((res) => {
          console.log('user',res.data);
          var player = res.data;
          for(var i=0; i < player.length; i++) {
            if (this.teamData.captain_uid == player[i].uid) {
              this.headPlayer = player[i].nickname
            }
          }
          console.log(this.playerList)
        })  
        .catch((err) => {
          console.log(err);
        });
    },
    modalSwitch(switchData) {
      if (switchData == 1) {
        this.modals.teamInfo = false
        this.modals.joinTeam = true
      } else if (switchData == 2) {
        this.modals.teamInfo = true
        this.modals.joinTeam = false
      } else if (switchData == 3) {
        this.modals.teamInfo = false
        this.modals.joinTeam = false
      }
      console.log(this)
    },
    ApplyGo() {
      console.log(this.apply)
      var ApplyData = new FormData();
      ApplyData.append('team_uid', this.apply.teamuid)
      ApplyData.append('user_uid', this.$cookies.get('UserInfo').uid)
      ApplyData.append('comment', this.apply.comment)
      axios.post(SERVER_URL + "user/applyTeam/", ApplyData)
        .then(res => {
          console.log(res)
          this.modals.joinTeam = false
        })
        .catch(err => {
          console.log(err)
        })
    }
  },
};
</script>
<style></style>
