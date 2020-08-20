<template>
  <div>
     <base-header class="pb-5 pt-md-8 mb-4">
      <div class="row d-flex flex-row justify-content-between">
        <div class="col-7">
          <card title="Room information" class="mb-4 mb-xl-0">
            <h2 class="mb-0">{{ RoomData.title }}</h2>
          </card>
          <h3 class="text-white mt-2">담당 매니저 : {{ Manager.nickname }}</h3>
        </div>

        <div>
          <router-link to="/dashboard">
            <base-button class="mb-xl-0 p-4" outline type="primary">
              <div class="row">
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

    <div class="d-flex flex-row-reverse justify-content-between ">
      <base-button class="ml-3" type="secondary" v-if="isManager">
        <router-link :to="{ name: '매니저 평가', params: { uid: this.RoomData.uid }}">
          <h4 class="mb-0">매니저 평가</h4>
        </router-link>
      </base-button>
      <base-button class = "mx-3" v-if="!isRoomFull && isHeader && !isCaptain && isFullteam" type="success" @click="modals.entermessage = true">
        <h4 class="text-white mb-0">입장하기</h4>
      </base-button>
      <base-button class = "mx-3" type="success" v-if="isHeader && isEnter && !isCaptain" @click="modals.outalert = true">
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
      <modal :show.sync="modals.problem" gradient="danger" class="text-center">
        <div class="py-3 text-center mb-0">
          <h3 class="text-white mb-3">문제가 발생하였습니다. 메인페이지로 돌아갑니다.</h3>
          <base-button size="sm" type="secondary" @click="modals.problem = false">닫기</base-button>
        </div>
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
      isFullteam: false,
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
        problem : false,
      },
    };
  },
  computed: {},
  methods: {
    createFunction() {
      if (this.$cookies.isKey("UserInfo")) {
        this.isLogined = true;
        this.isMine = this.$cookies.get("UserInfo").uid;
      }    
      const TeamRoomData = new FormData();
      TeamRoomData.append("uid", this.$route.params.uid);
      this.$axios.post(this.$SERVER_URL + "TeamMatchRoom/", TeamRoomData)
        .then((res) => {
          if (res.data == "") {
            this.problem = true;
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
            var kuid = this.$cookies.get("UserInfo").uid
            if (res.data[0].head_uid == kuid) {
              this.isHeader = true
              var headData = new FormData();
              headData.append('uid', kuid)
              this.$axios.post(this.$SERVER_URL + "team/detail/", headData)
                .then(res => {
                  if (res.data.player_num >= 11) {
                    this.isFullteam = true
                  }
                })
              this.isCaptain = true
            }
            this.team.Red = []
            this.team.Blue = []
       
            this.RedTeamData()
            this.BlueTeamData()
            this.FullRoomCheck()
            this.TeamHeadCheck()
          }
        })
     
    },
    RedTeamData() {
     
      var RedData = new FormData();
      RedData.append('uid', this.RoomData.home_team_uid)
      this.$axios.post(this.$SERVER_URL + "team/detail/", RedData)
      .then((res) => {
  
        this.team.Red.push(res.data)
        if (this.team.Red[0].uid == this.$cookies.get("UserInfo").team_uid) {
          this.isEnter = true
        }
      })

    },
    BlueTeamData() {
   
      var BlueData = new FormData();
      BlueData.append('uid', this.RoomData.away_team_uid)
      this.$axios.post(this.$SERVER_URL + "team/detail/", BlueData)
      .then((res) => {
      
        this.team.Blue.push(res.data)

        if (this.team.Blue[0].uid == this.$cookies.get("UserInfo").team_uid) {
          this.isEnter = true
        }
      })
     
    },
    FullRoomCheck() {
      var Home_uid = this.RoomData.home_team_uid
      var Away_uid = this.RoomData.away_team_uid
      if (Home_uid > 0 && Away_uid > 0) {
        this.isRoomFull = true
      }
  
    },
    TeamHeadCheck() {
      var TeamList = []
      var nowhere = new FormData()
      nowhere.append('where', "")
      this.$axios.post(this.$SERVER_URL + "team/list", nowhere)
        .then((res) => {
          TeamList = res.data;
          for(var i=0; i < TeamList.length; i++) {
           
            if (TeamList[i].captain_uid == this.$cookies.get("UserInfo").uid) {
              this.isHeader = true
            }
          }
        })
    
    },
    OutTeam() {
  
      var TeamOutUid = new FormData();
      TeamOutUid.append("team_matching_uid", this.RoomData.uid)
      TeamOutUid.append("away_team_uid", this.$cookies.get("UserInfo").team_uid)
      this.$axios.post(this.$SERVER_URL + "TeamMatchRoom/cancel/", TeamOutUid)
        .then(() => {
         
          this.modals.outalert = false
          this.createFunction()
        })
       
    },
    CreditGo() {
  
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
           
            window.location.replace(res.data)
          })
       
      }
    },
  },
  mounted() {},
  created() {
    this.createFunction()    
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