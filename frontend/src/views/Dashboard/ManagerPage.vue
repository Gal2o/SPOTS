<template>
  <div class="justify-content-between">
    <base-header type="gradient-success" class="pb-4 pt-3 pt-md-6">
      <div class="row d-flex flex-row justify-content-between">
        <div class="col-7">
          <card title="Room information" class="mb-4 mb-xl-0">
            <h2>{{ RoomData.title }}</h2>
          </card>
          <h3>담당 매니저 : 이동옥</h3>
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
          :data="RedtableDatas"
        >
          <template slot="columns">
            <th>유저 명</th>
            <th>기록 및 평가</th>
          </template>

          <template slot-scope="{ row }">
            <th scope="row">
              <span class="name mb-0 text-sm text-secondary">{{ row.name }}</span>
            </th>
            <td class="row justify-content-between ml-4 mr-4">
              <button  class="btn-secondary ml-3 btn-lg" > 득점 : {{ rgoal }} <base-dropdown>
                  <a class="dropdown-item" v-for="sc in score" 
                  v-bind:key="sc" @click="inputrgoal(sc)" >{{sc}}</a>
              </base-dropdown></button>

              <button class="btn-secondary btn-lg">도움 : {{ rassist }}<base-dropdown>
              <a class="dropdown-item" v-for="sc in score" 
              v-bind:key="sc" @click="inputrassist(sc)">{{sc}}</a></base-dropdown></button>
              
              <base-button block type="secondary" size="lg" class="col-4 mr-3"  @click="modal1 = true">신고
              </base-button>
              <modal :show.sync="modal1" body-classes="p-0" modal-classes="modal-dialog modal-md">
          <card
            type="secondary"
            shadow
            header-classes="bg-white pb-5"
            body-classes="px-lg-5 py-lg-5"
            class="border-0"
          >
            <template>
              <div class="text-muted text-center mb-3">
                <medium>블랙리스트 신고하기</medium>
              </div>
            </template>
            <template>
              <div class="text-center text-muted mb-4">
                <small>신고 인원과 사유를 선택해주세요</small>
              </div>
              <form role="form">
                <base-input alternative class="mb-3" v-model="title" placeholder="제목을 적어주세요"></base-input>
                <div class="text-center">
                  <base-button type="secondary" @click="modal1 = false">닫기</base-button>
                </div>
              </form>
            </template>
          </card>
        </modal>
            </td>
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
          tbody-classes="list"
          :data="BluetableDatas"

        >
          <template slot="columns">
            <th>유저 명</th>
            <th>기록 및 평가</th>
          </template>

          <template slot-scope="{ row }">
            <th scope="row">
              <span class="name mb-0 text-sm text-default">{{ row.name }}</span>
            </th>
            <td class="row justify-content-between ml-4 mr-4">
              <button  class="btn-secondary ml-3 btn-lg" > 득점 : {{ bgoal }} <base-dropdown>
                  <a class="dropdown-item" v-for="sc in score" 
                  v-bind:key="sc" @click="inputbgoal(sc)" >{{sc}}</a>
              </base-dropdown></button>

              <button class="btn-secondary btn-lg">도움 : {{ bassist }}<base-dropdown>
              <a class="dropdown-item" v-for="sc in score" 
              v-bind:key="sc" @click="inputbassist(sc)">{{sc}}</a></base-dropdown></button>
              
              <base-button block type="secondary" size="lg" class="col-4 mr-3">신고
              </base-button>
            </td>
          </template>
        </base-table>
      </div>
    </div>
    <div class=" d-flex justify-content-between">
    <base-button type="success" size="lg" class="col-2 ml-4">오늘의 MVP 선정</base-button>
    <base-button type="warning" size="lg" class="col-2 mr-4">평가 및 경기 마치기</base-button> 
  </div>
  </div>
</template>
<script>
import axios from "axios";

const SERVER_URL = "http://localhost:8080/";

export default {
  name: "freematchroom",
  components: {},
  data() {
    return {
      RoomData: Object,
      RedtableDatas: [],
      BluetableDatas: [],
      myTeam: "RED",
      myPosUid: 0,
      teamList: [{ name: "RED" }, { name: "BLUE" }],
      posRedList: [],
      posBlueList: [],
      posNameList: [
        "defender1_uid",
        "defender2_uid",
        "defender3_uid",
        "defender4_uid",
        "goalkeeper_uid",
        "midfielder1_uid",
        "midfielder2_uid",
        "midfielder3_uid",
        "midfielder4_uid",
        "striker1_uid",
        "striker2_uid",
        "striker3_uid",
        "striker4_uid",
      ],
      bgoal:0,
      bassist:0,
      rgoal:0,
      rassist:0,
      score:[1,2,3,4,5,6,7,8,9,10],
      modal1:false,
     };
  },
  methods: {
    inputrgoal(sc) {
      this.rgoal = sc
    },
    inputrassist(sc) {
      this.rassist = sc
    },
    inputbgoal(sc) {
      this.bgoal = sc
    },
    inputbassist(sc) {
      this.bassist = sc
    },
   
    UserEnter() {
      this.SearchPosition
      console.log('entry',this.myPosUid)
      if (this.myPosUid != 0) {
        const EnterInfo = new FormData();
        EnterInfo.append("uid", this.$cookies.get("UserInfo").uid);
        EnterInfo.append("positionnum", this.myPosUid);
        EnterInfo.append("team_entry_uid", this.myTeam);
        axios
          .post(SERVER_URL + "FreeMatchRoom/entrylist/", EnterInfo)
          .then((res) => {
            console.log(res);
          })
          .catch((err) => {
            console.log(err);
          });
      }
    },
    CreditGo() {
      const roomPrice = String(this.RoomData.price)
      const Price = new FormData();
      Price.append('price', roomPrice)      
      axios
        .post(SERVER_URL + "kakaoPay/", Price)
        .then(res => {
          console.log(res)
          window.open(res.data, "결제창")
        })
        .catch(err => {
          console.log(err);
        });
    },
    RedTeamList() {
      const Team_entry_uid = new FormData();
      Team_entry_uid.append(
        "team_entry_uid",
        this.RoomData.home_matching_entry_uid
      );
      axios
        .post(SERVER_URL + "FreeMatchRoom/entrylist/", Team_entry_uid)
        .then((res) => {
          console.log("red2", res.data);
          this.posRedList.push(res.data.defender1_uid);
          this.posRedList.push(res.data.defender2_uid);
          this.posRedList.push(res.data.defender3_uid);
          this.posRedList.push(res.data.defender4_uid);
          this.posRedList.push(res.data.goalkeeper_uid);
          this.posRedList.push(res.data.midfielder1_uid);
          this.posRedList.push(res.data.midfielder2_uid);
          this.posRedList.push(res.data.midfielder3_uid);
          this.posRedList.push(res.data.midfielder4_uid);
          this.posRedList.push(res.data.striker1_uid);
          this.posRedList.push(res.data.striker2_uid);
          this.posRedList.push(res.data.striker3_uid);
          this.posRedList.push(res.data.striker4_uid);
          this.RedpostionList = [];
          var Redsub = new Object
          Redsub.name = "랜덤"
          this.RedpostionList.push(Redsub)
          for(var i=0; i < this.posRedList.length; i++) { 
            console.log(i, this.posRedList[i], this.posNameList[i])           
            if (this.posRedList[i] != 0) {
              this.RedTeamUser(this.posRedList[i], this.posNameList[i])
            } else {
              this.RedListChange(this.posNameList[i])
            }
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
    RedTeamUser(uid, name) {
      console.log('res3', uid)
      const usid = new FormData()
      usid.append('uid', uid)
      axios.post(SERVER_URL + 'user/detail2/', usid)
        .then(res => {
          console.log('red4', res)
          var newPosition = new String("");
          if (name.indexOf("striker") != -1) {
            newPosition = "공격수"
          } else if (name.indexOf("mid") != -1) {
            newPosition = "미드필더"
          } else if (name.indexOf("defend") != -1) {
            newPosition = "수비수"
          } else if (name.indexOf("goal") != -1) {
            newPosition = "골키퍼"
          }
          var Redsub = new Object
          Redsub.position = newPosition
          Redsub.name = res.data.nickname
          this.RedtableDatas.push(Redsub)
          if (res.data.nickname == this.isMine) {
            this.myPosition = newPosition
          }
          console.log('red5', this.RedtableDatas)
        })
        .catch(err => {
              console.log(err)
        });
    },
    
    BlueTeamList() {
      const Team_entry_uid = new FormData();
      Team_entry_uid.append(
        "team_entry_uid",
        this.RoomData.away_matching_entry_uid
      );
      axios
        .post(SERVER_URL + "FreeMatchRoom/entrylist/", Team_entry_uid)
        .then((res) => {
          console.log("blue2", res.data);
          this.posBlueList.push(res.data.defender1_uid);
          this.posBlueList.push(res.data.defender2_uid);
          this.posBlueList.push(res.data.defender3_uid);
          this.posBlueList.push(res.data.defender4_uid);
          this.posBlueList.push(res.data.goalkeeper_uid);
          this.posBlueList.push(res.data.midfielder1_uid);
          this.posBlueList.push(res.data.midfielder2_uid);
          this.posBlueList.push(res.data.midfielder3_uid);
          this.posBlueList.push(res.data.midfielder4_uid);
          this.posBlueList.push(res.data.striker1_uid);
          this.posBlueList.push(res.data.striker2_uid);
          this.posBlueList.push(res.data.striker3_uid);
          this.posBlueList.push(res.data.striker4_uid);
          this.BluepostionList = [];
          var Bluesub = new Object
          Bluesub.name = "랜덤"
          this.BluepostionList.push(Bluesub)
          for(var i=0; i < this.posBlueList.length; i++) { 
            console.log('blue!',i, this.posBlueList[i], this.posNameList[i])
            if (this.posBlueList[i] != 0) {
              this.BlueTeamUser(this.posBlueList[i], this.posNameList[i])
            } else {
              this.BlueListChange(this.posNameList[i])
            }
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
    BlueTeamUser(uid, name) {
      console.log('blue3', uid)
      const usid = new FormData()
      usid.append('uid', uid)
      axios.post(SERVER_URL + 'user/detail2/', usid)
        .then(res => {
          console.log('blue4', res)
          var newPosition = new String("");
          if (name.indexOf("striker") != -1) {
            newPosition = "공격수"
          } else if (name.indexOf("mid") != -1) {
            newPosition = "미드필더"
          } else if (name.indexOf("defend") != -1) {
            newPosition = "수비수"
          } else if (name.indexOf("goal") != -1) {
            newPosition = "골키퍼"
          }
          var Bluesub = new Object
          Bluesub.position = newPosition
          Bluesub.name = res.data.nickname
          this.BluetableDatas.push(Bluesub)
          if (res.data.nickname == this.isMine) {
            this.isEnter = true
            this.myPosition = newPosition
          }
          console.log('blue5',this.BluetableDatas)
        })
        .catch(err => {
              console.log(err)
        });
    },
  },
  mounted() {},
  created() {
    if (this.$cookies.isKey("UserInfo")) {
      this.isLogined = true;
      this.isMine = this.$cookies.get("UserInfo").nickname;
    }    
    console.log("0", this);
    const FreeRoomData = new FormData();
    FreeRoomData.append("uid", this.$route.params.uid);
    axios
      .post(SERVER_URL + "FreeMatchRoom/", FreeRoomData)
      .then((res) => {
        console.log(res);
        if (res.data == "") {
          alert("문제가 발생하였습니다. 메인페이지로 돌아갑니다.");
          this.$router.push({ name: "SPOTs" });
        } else {
          this.RoomData = res.data[0];
          var kuid = this.$cookies.get("UserInfo").uid
          if (res.data[0].head_uid == kuid) {
            this.isHeader = true
          }
          console.log("1", this.RoomData);
          this.RedtableDatas = [];
          this.RedTeamList();
          this.BluetableDatas = [];
          this.BlueTeamList();
        }
      })
      .catch((err) => {
        console.log(err);
        alert("문제가 발생하였습니다. 메인페이지로 돌아갑니다.");
        this.$router.push({ name: "SPOTs" });
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