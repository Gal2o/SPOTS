<template>
  <div>
    <base-header type="gradient-success" class="pb-4 pt-3 pt-md-6">
      <div class="row d-flex flex-row justify-content-between">
        <div class="col-7">
          <card title="Room information" class="mb-4 mb-xl-0">
            <h2 class="mb-0">{{ RoomData.title }}</h2>
          </card>
          <h3 class="mb-0">담당 매니저 : {{ Manager.nickname }}</h3>
        </div>

        <div>
          <router-link to="/dashboard">
            <base-button class="mb-4 mb-xl-0 p-4" type="danger">
              <div class="row">
                <i class="ni ni-bold-left ni-2x"></i>
                <h2 class="text-white mb-0">방 나가기</h2>
              </div>
            </base-button>
          </router-link>
        </div>
      </div>
    </base-header>

    <div class="row d-flex flex-row" :class="type === 'dark' ? 'bg-default' : ''">
      <div class="table-responsive col m-2">
        <div class="text-center p-2 mb-2 bg-danger rounded-top">
          <h2>Red Team</h2>
        </div>
        <base-table
          class="table align-items-center table-flush border-solid table-danger rounded-bottom text-center"
          :class="type === 'dark' ? 'table-dark' : ''"
          :thead-classes="type === 'dark' ? 'thead-dark' : 'thead-light'"
          tbody-classes="list"
          :data="team.Red"
        >
          <template slot="columns">
            <th>팀명</th>
          </template>

          <template slot-scope="{ row }">
            <th scope="row">
              <span class="name mb-0 text-xl text-default"><b>{{ row.team_name }}</b></span><br>
              <span class="name mb-0 text-lg text-default">소개 : {{ row.team_intro }}</span><br>
              <span class="name mb-0 text-lg text-default">{{ row.team_win }} 승 {{ row.team_draw }} 무 {{ row.team_lose }} 패</span>
            </th>
          </template>
        </base-table>
      </div>

      <div class="table-responsive col m-2">
        <div class="text-center p-2 mb-2 bg-primary rounded-top">
          <h2 class="text-white">Blue Team</h2>
        </div>
        <base-table
          class="table align-items-center table-flush border-solid table-primary rounded-bottom text-center"
          :class="type === 'dark' ? 'table-dark' : ''"
          :thead-classes="type === 'dark' ? 'thead-dark' : 'thead-light'"
          tbody-class="list"
          :data="team.Blue"
        >
          <template slot="columns">
            <th>팀명</th>
          </template>

          <template slot-scope="{ row }">
            <th scope="row">
              <span class="name mb-0 text-xl text-default"><b>{{ row.team_name }}</b></span><br>
              <span class="name mb-0 text-lg text-default">소개 : {{ row.team_intro }}</span><br>
              <span class="name mb-0 text-lg text-default">{{ row.team_win }} 승 {{ row.team_draw }} 무 {{ row.team_lose }} 패</span>
            </th>
          </template>
        </base-table>
      </div>
    </div>

    <div class="d-flex flex-row-reverse justify-content-between mr-5">
      <base-button class="ml-3" type="secondary" v-if="isManager">
        <router-link :to="{ name: '매니저 평가', params: { uid: this.RoomData.uid }}">
          <h4 class="mb-0">매니저 평가</h4>
        </router-link>
      </base-button>
      <base-button v-if="!isRoomFull && isHeader" type="success" @click="modals.entermessage = true">
        <h4 class="text-white mb-0">입장하기</h4>
      </base-button>
      <base-button type="success" v-if="isHeader && isEnter && !isCaptain" @click="modals.outalert = true">
        <h4 class="text-white mb-0">취소하기</h4>
      </base-button>
    </div>

    <modal
      :show.sync="modals.entermessage"
      body-classes="p-0"
      modal-classes="modal-dialog modal-md"
    >
      <card
        type="secondary"
        shadow
        header-classes="bg-white pb-5"
        body-classes="px-lg-5 py-lg-5"
        class="border-0"
      >
        <template>
          <div class="text-muted text-center mb-3">
            <h4>입장 준비</h4>
          </div>
        </template>
        <template>
          <div class="text-center text-muted mb-4">
            <h6>입장하시겠습니까?</h6>
          </div>
          <form role="form">
            <div class="text-center text-muted mb-4">
              <small>
                결제 금액은
                <big style="font-size:30px;" class="text-warning">
                  <b>{{ RoomData.price }}원</b>
                </big> 입니다.
              </small>
            </div>
            <div class="text-center">
              <base-button type="success" class="my-4 mr-4" @click="CreditGo">결제하기</base-button>
              <base-button type="secondary" @click="modals.entermessage = false">닫기</base-button>
            </div>
          </form>
        </template>
      </card>
    </modal>

    <modal
      :show.sync="modals.outalert"
      gradient="danger"
      modal-classes="modal-danger modal-dialog-centered"
    >
      <div class="py-3 text-center">
        <i class="ni ni-bell-55 ni-3x"></i>
        <h4 class="heading mt-4">정말 취소하시겠습니까?</h4>
        <p>취소로 인한 경기참여 관련 문제 발생 시 도움을 드리지 못 할 수도 있습니다.</p>
      </div>

      <template slot="footer">
        <base-button type="white" @click="OutTeam">취소하기</base-button>
        <base-button
          type="link"
          text-color="white"
          class="ml-auto"
          @click="modals.outalert = false"
        >닫기</base-button>
      </template>
    </modal>
  </div>
</template>
<script>
export default {
  name: "teammatchroom",
  components: {},
  props: {
    type : {type:String}
  },
  data() {
    return {
      isMine: 0,
      isStart: false,
      isEnter: false,
      isHeader: false,
      isCaptain: false,
      isLogined: false,
      isRoomFull: false,
      isManager: false,
      Manager: Object,
      RoomData: Object,
      team: {
        Red: [],
        Blue: [],
      },
      modals: {
        entermessage: false,
        changeCheck: false,
        outalert: false,
      },
    };
  },
  computed: {},
  methods: {
    RedTeamData() {
      console.log('red0',this.RoomData.home_team_uid)
      var RedData = new FormData();
      RedData.append('uid', this.RoomData.home_team_uid)
      this.$axios.post(this.$SERVER_URL + "team/detail/", RedData)
      .then((res) => {
        console.log('red1', res)
        this.team.Red.push(res.data)
        if (this.team.Red[0].uid == this.$cookies.get("UserInfo").team_uid) {
          this.isEnter = true
        }
      })
      .catch((err) => {
        console.log('red', err);
      });
    },
    BlueTeamData() {
      console.log('blue0',this.RoomData.away_team_uid)
      var BlueData = new FormData();
      BlueData.append('uid', this.RoomData.away_team_uid)
      this.$axios.post(this.$SERVER_URL + "team/detail/", BlueData)
      .then((res) => {
        console.log('blue1', res)
        this.team.Blue.push(res.data)
        console.log('blue2',this.team.Blue)
        if (this.team.Blue[0].uid == this.$cookies.get("UserInfo").team_uid) {
          this.isEnter = true
        }
      })
      .catch((err) => {
        console.log('blue', err);
      });
    },
    FullRoomCheck() {
      var Home_uid = this.RoomData.home_team_uid
      var Away_uid = this.RoomData.away_team_uid
      if (Home_uid > 0 && Away_uid > 0) {
        this.isRoomFull = true
      }
      console.log('fulltest', this.isRoomFull)
    },
    TeamHeadCheck() {
      var TeamList = []
      var nowhere = new FormData()
      nowhere.append('where', "")
      this.$axios.post(this.$SERVER_URL + "team/list", nowhere)
        .then((res) => {
          TeamList = res.data;
          for(var i=0; i < TeamList.length; i++) {
            console.log(i, TeamList)
            if (TeamList[i].captain_uid == this.$cookies.get("UserInfo").uid) {
              this.isHeader = true
            }
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
    OutTeam() {
      console.log('out')
      console.log("team_matching_uid", this.RoomData.uid)
      console.log("away_team_uid", this.$cookies.get("UserInfo").team_uid)
      var TeamOutUid = new FormData();
      TeamOutUid.append("team_matching_uid", this.RoomData.uid)
      TeamOutUid.append("away_team_uid", this.$cookies.get("UserInfo").team_uid)
      this.$axios.post(this.$SERVER_URL + "TeamMatchRoom/cancel/", TeamOutUid)
        .then((res) => {
          console.log('clear',res)
          this.modals.outalert = false
          this.$router.push({ name: "팀 SPOT", params: { uid: this.RoomData.uid } });
        })
        .catch((err) => {
          console.log(err);
        });
    },
    CreditGo() {
      console.log('end',this.myPosUid)
      const roomPrice = String(this.RoomData.price)
      if (this.myPosUid != 0) {
        const EnterInfo = new FormData();
        EnterInfo.append("uid", this.$cookies.get("UserInfo").team_uid);
        EnterInfo.append("positionnum", -1);
        EnterInfo.append("team_entry_uid", 0);
        EnterInfo.append('price', roomPrice)
        EnterInfo.append("room_uid", this.RoomData.uid)
        this.$axios
          .post(this.$SERVER_URL + "kakaoPay/", EnterInfo)
          .then(res => {
            console.log('pay123', res)
            window.location.replace(res.data)
          })
          .catch(err => {
            console.log(err);
          });
      }
    },
  },
  mounted() {},
  created() {
    if (this.$cookies.isKey("UserInfo")) {
      this.isLogined = true;
      this.isMine = this.$cookies.get("UserInfo").uid;
    }    
    console.log("0", this);
    const TeamRoomData = new FormData();
    TeamRoomData.append("uid", this.$route.params.uid);
    this.$axios.post(this.$SERVER_URL + "TeamMatchRoom/", TeamRoomData)
      .then((res) => {
        console.log('check', res);
        if (res.data == "") {
          alert("문제가 발생하였습니다. 메인페이지로 돌아갑니다.");
          this.$router.push({ name: "SPOTs" });
        } else {
          this.RoomData = res.data[0];
          var managerform = new FormData()
          managerform.append('uid', res.data[0].manager_uid)
          this.$axios.post(this.$SERVER_URL + "user/detail2/", managerform)
            .then(res => {
              this.Manager = res.data
              if (res.data.uid == this.$cookies.get('UserInfo').uid) {
                this.isManager = true
              }
            })
            .catch(err => {
              console.log(err)
            })
          var kuid = this.$cookies.get("UserInfo").uid
          if (res.data[0].head_uid == kuid) {
            this.isHeader = true
            this.isCaptain = true
          }
          console.log("1", this.RoomData);
          this.RedTeamData()
          this.BlueTeamData()
          this.FullRoomCheck()
          this.TeamHeadCheck()
        }
      })
      .catch((err) => {
        console.log('김',err);
      });
  },
};
</script>
<style>
div.vertical-line {
  width: 2px;
  background-color: black;
  height: 100%;
  float: left;
}
</style>