<template>
  <div>
    <base-header type="gradient-success" class="pb-4 pt-3 pt-md-6">
      <div class="row d-flex flex-row justify-content-between">
        <div class="col-7">
          <card title="Room information" class="mb-4 mb-xl-0">
            <h2>{{ RoomData.title }}</h2>
          </card>
          <h3>담당 매니저 : </h3>
        </div>

        <div>
          <router-link to="/dashboard">
            <base-button class="mb-4 mb-xl-0 p-4" type="danger">
              <div class="row">
                <i class="ni ni-bold-left ni-2x"></i>
                <h2 class="text-white">방 나가기</h2>
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
          :data="RedtableDatas"
        >
          <template slot="columns">
            <th>유저 명</th>
            <th>포지션</th>
          </template>

          <template slot-scope="{ row }">
            <th scope="row">
              <span class="name mb-0 text-sm text-default">{{ row.name }}</span>
            </th>
            <td>
              <base-button
                slot="title"
                v-if="row.name != isMine"
              >{{ row.position }}</base-button>
              <div class="row d-flex justify-content-center" v-if="row.name == isMine">
                <base-dropdown>
                  <base-button slot="title" class="dropdown-toggle">{{ myTeam }}</base-button>
                  <a
                    class="dropdown-item"
                    v-for="teamitem in teamList"
                    :key="teamitem"
                    @click="TeamChange(teamitem.name)"
                  >{{ teamitem.name }}</a>
                </base-dropdown>
                <base-dropdown v-if="myTeam == 'RED'">
                  <base-button slot="title" class="dropdown-toggle">{{ myPosition }}</base-button>
                  <a
                    class="dropdown-item"
                    v-for="positonitem in RedpostionList"
                    :key="positonitem"
                    @click="PositionChange(positonitem.name)"
                  >{{ positonitem.name }}</a>
                </base-dropdown>
                <base-dropdown v-if="myTeam == 'BLUE'">
                  <base-button slot="title" class="dropdown-toggle">{{ row.position }}</base-button>
                  <a
                    class="dropdown-item"
                    v-for="positonitem in BluepostionList"
                    :key="positonitem"
                    @click="PositionChange(positonitem.name)"
                  >{{ positonitem.name }}</a>
                </base-dropdown>
                <base-button type="info" @click="modals.changeCheck = true">변경</base-button>
              </div>
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
            <th>포지션</th>
          </template>

          <template slot-scope="{ row }">
            <th scope="row">
              <span class="name mb-0 text-sm text-default">{{ row.name }}</span>
            </th>
            <td>
              <base-button
                slot="title"
                v-if="row.name != isMine"
              >{{ row.position }}</base-button>
              <div class="row d-flex justify-content-center" v-if="row.name == isMine">
                <base-dropdown>
                  <base-button slot="title" class="dropdown-toggle">{{ myTeam }}</base-button>
                  <a
                    class="dropdown-item"
                    v-for="teamitem in teamList"
                    :key="teamitem"
                    @click="TeamChange(teamitem.name)"
                  >{{ teamitem.name }}</a>
                </base-dropdown>
                <base-dropdown>
                  <base-button slot="title" class="dropdown-toggle">{{ row.position }}</base-button>
                  <a
                    class="dropdown-item"
                    v-for="positonitem in BluepostionList"
                    :key="positonitem"
                    @click="PositionChange(positonitem.name)"
                  >{{ positonitem.name }}</a>
                </base-dropdown>
                <base-button type="info" @click="modals.changeCheck = true">변경</base-button>
              </div>
            </td>
          </template>
        </base-table>
      </div>
    </div>

    <div class="d-flex flex-row justify-content-between mr-5">
      <base-button class="ml-3" type="secondary">
        <router-link :to="{ name: '매니저 평가', params: { uid: this.RoomData.uid }}">
          <h4>매니저 평가</h4>
        </router-link>
      </base-button>
      <base-button v-if="!isLogined" type="success" @click="modals.loginalert = true">
        <h4 class="text-white">입장하기</h4>
      </base-button>
      <base-button v-if="isLogined && !this.isEnter" type="success" @click="modals.entermessage = true">
        <h4 class="text-white">입장하기</h4>
      </base-button>
      <base-button v-if="isLogined && this.isEnter && !this.isHeader" type="success" @click="modals.outalert = true">
        <h4 class="text-white">취소하기</h4>
      </base-button>
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
            <medium>입장 준비</medium>
          </div>
        </template>
        <template>
          <div class="text-center text-muted mb-4">
            <small>팀과 포지션을 선택해주세요.</small>
          </div>
          <form role="form">
            <div class="d-flex justify-content-center">
              <base-dropdown class="mr-3">
                <base-button slot="title" type="secondary" class="dropdown-toggle">{{ this.myTeam }}</base-button>
                <a
                  class="dropdown-item"
                  v-for="teamitem in teamList"
                  :key="teamitem"
                  @click="TeamChange(teamitem.name)"
                >{{ teamitem.name }}</a>
              </base-dropdown>

              <base-dropdown class="mr-3" v-if="myTeam === 'RED'">
                <base-button
                  slot="title"
                  type="secondary"
                  class="dropdown-toggle"
                >{{ this.myPosition }}</base-button>
                <a
                  class="dropdown-item"
                  v-for="positonitem in RedpostionList"
                  :key="positonitem"
                  @click="PositionChange(positonitem.name)"
                >{{ positonitem.name }}</a>
              </base-dropdown>
              <base-dropdown class="mr-3" v-if="myTeam === 'BLUE'">
                <base-button
                  slot="title"
                  type="secondary"
                  class="dropdown-toggle"
                >{{ this.myPosition }}</base-button>
                <a
                  class="dropdown-item"
                  v-for="positonitem in BluepostionList"
                  :key="positonitem"
                  @click="PositionChange(positonitem.name)"
                >{{ positonitem.name }}</a>
              </base-dropdown>
            </div>
            <div class="text-center text-muted mb-4">
              <small>
                포지션을 선택하였으면 결제를 해주세요.
                <br />결제 금액은
                <big style="font-size:30px;" class="text-warning">
                  <b>{{ RoomData.price }}원</b>
                </big> 입니다.
              </small>
            </div>
            <div class="text-center">
              <base-button type="success" class="my-4 mr-4" @click="SearchPosition">결제하기</base-button>
              <base-button type="secondary" @click="modals.entermessage = false">닫기</base-button>
            </div>
          </form>
        </template>
      </card>
    </modal>

    <modal :show.sync="modals.changeCheck">
      <h4 slot="header" class="modal-title" id="modal-title-default">포지션을 변경하시겠습니까?</h4>

      <p>포지션을 정말로 바꿀것인지 확인해주십시오.</p>

      <template slot="footer">
          <base-button type="primary" @click="SearchPosition">변경하기</base-button>
          <base-button type="link" class="ml-auto" @click="modals.changeCheck = false">취소
          </base-button>
      </template>
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
        <base-button type="white" @click="deleteUser">취소하기</base-button>
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
import axios from "axios";

const SERVER_URL = "http://localhost:8080/";

export default {
  name: "freematchroom",
  components: {},
  data() {
    return {
      isMine: "",
      isStart: false,
      isEnter: false,
      isHeader: false,
      RoomData: Object,
      RedtableDatas: [],
      BluetableDatas: [],
      isLogined: false,
      myTeam: "RED",
      myRealTeam: 0,
      myPosition: "선택해주세요",
      myPosUid: 0,
      myOriginUid: 0,
      RedpostionList: [
        { name: "랜덤" },
        { name: "공격수" },
        { name: "미드필더" },
        { name: "수비수" },
        { name: "골키퍼" },
      ],
      BluepostionList: [
        { name: "랜덤" },
        { name: "공격수" },
        { name: "미드필더" },
        { name: "수비수" },
        { name: "골키퍼" },
      ],
      postionList: [
        { name: "랜덤" },
        { name: "공격수" },
        { name: "미드필더" },
        { name: "수비수" },
        { name: "골키퍼" },
      ],
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
      modals: {
        loginalert: false,
        entermessage: false,
        changeCheck: false,
        outalert: false,
      },
    };
  },
  methods: {
    PositionChange(name) {
      console.log(this.BluepostionList)
      if (name == "랜덤") {
        if (this.myTeam == "RED") {
          this.myPosition = this.RedpostionList[Math.floor(
            Math.random() * (this.RedpostionList.length - 1)
          )].name
        } else {
          this.myPosition = this.BluepostionList[Math.floor(
            Math.random() * (this.BluepostionList.length - 1)
          )].name
        }
      } else {
        this.myPosition = name;
      }
    },
    TeamChange(name) {
      this.myTeam = name;
    },
    SearchPosition() {
      console.log('first',this)
      var entry_uid = 0;
      if (this.myTeam === "RED") {
        entry_uid = this.RoomData.home_matching_entry_uid;
      } else {
        entry_uid = this.RoomData.away_matching_entry_uid;
      }
      const entryUid = new FormData();
      entryUid.append("team_entry_uid", entry_uid);
      axios
        .post(SERVER_URL + "FreeMatchRoom/entrylist/", entryUid)
        .then((res) => {
          console.log(this.myPosition)
          if (this.myPosition === "공격수") {
            if (res.data.striker1_uid == 0) {
              this.myPosUid = 1;
            } else if (res.data.striker2_uid == 0) {
              this.myPosUid = 2;
            } else if (res.data.striker3_uid == 0) {
              this.myPosUid = 3;
            } else if (res.data.striker4_uid == 0) {
              this.myPosUid = 4;
            }
          } else if (this.myPosition === "미드필더") {
            if (res.data.midfielder1_uid == 0) {
              this.myPosUid = 5;
            } else if (res.data.midfielder2_uid == 0) {
              this.myPosUid = 6;
            } else if (res.data.midfielder3_uid == 0) {
              this.myPosUid = 7;
            } else if (res.data.midfielder4_uid == 0) {
              this.myPosUid = 8;
            }
          } else if (this.myPosition === "수비수") {
            if (res.data.defender1_uid == 0) {
              this.myPosUid = 9;
            } else if (res.data.defender2_uid == 0) {
              this.myPosUid = 10;
            } else if (res.data.defender3_uid == 0) {
              this.myPosUid = 11;
            } else if (res.data.defender4_uid == 0) {
              this.myPosUid = 12;
            }
          } else if (this.myPosition === "골키퍼") {
            if (res.data.goalkeeper_uid == 0) {
              this.myPosUid = 13;
            }
          }
          console.log('change',this.myPosUid)
        })
          .then(() => {
            console.log(this.isEnter)
            if (this.isEnter) {
              console.log('go')
              this.SearchMyPosition()
            } else {   
              console.log('out')           
              this.CreditGo()
            }
          })
        .catch((err) => {
          console.log(err);
        });
    },
    SearchMyPosition() {
      console.log('dd', this)
      var entry_uid = 0;
      if (this.myTeam === "RED") {
        entry_uid = this.RoomData.home_matching_entry_uid;
      } else {
        entry_uid = this.RoomData.away_matching_entry_uid;
      }
      const entryUid = new FormData();
      entryUid.append("team_entry_uid", entry_uid);
      axios
        .post(SERVER_URL + "FreeMatchRoom/entrylist/", entryUid)
        .then((res) => {
          console.log(this.myPosition)
          var Myuid = this.$cookies.get('UserInfo').uid
          if (res.data.striker1_uid == Myuid) {
            this.myOriginUid = 1;
          } else if (res.data.striker2_uid == Myuid) {
            this.myOriginUid = 2;
          } else if (res.data.striker3_uid == Myuid) {
            this.myOriginUid = 3;
          } else if (res.data.striker4_uid == Myuid) {
            this.myOriginUid = 4;
          } else if (res.data.midfielder1_uid == Myuid) {
            this.myOriginUid = 5;
          } else if (res.data.midfielder2_uid == Myuid) {
            this.myOriginUid = 6;
          } else if (res.data.midfielder3_uid == Myuid) {
            this.myOriginUid = 7;
          } else if (res.data.midfielder4_uid == Myuid) {
            this.myOriginUid = 8;
          } else if (res.data.defender1_uid == Myuid) {
            this.myOriginUid = 9;
          } else if (res.data.defender2_uid == Myuid) {
            this.myOriginUid = 10;
          } else if (res.data.defender3_uid == Myuid) {
            this.myOriginUid = 11;
          } else if (res.data.defender4_uid == Myuid) {
            this.myOriginUid = 12;
          } else if (res.data.goalkeeper_uid == Myuid) {
            this.myOriginUid = 13;
          }
          console.log('!change',this.myOriginUid)
        })
          .then(() => {
            console.log('go')
            this.UserChange()
          })
        .catch((err) => {
          console.log(err);
        });
    },
    UserChange() {
      if (this.myPosUid != 0) {
        var myTeam_uid = 0
        if (this.myTeam == "RED") {
          myTeam_uid = this.RoomData.home_matching_entry_uid
        } else {
          myTeam_uid = this.RoomData.away_matching_entry_uid
        }
        const ChangeInfo = new FormData();
        ChangeInfo.append("uid", this.$cookies.get("UserInfo").uid);
        ChangeInfo.append("positionnum_before", this.myOriginUid);
        ChangeInfo.append("positionnum_after", this.myPosUid);
        ChangeInfo.append("team_entry_uid_after", myTeam_uid);
        ChangeInfo.append("team_entry_uid_before", this.myRealTeam)
        axios
          .post(SERVER_URL + "FreeMatchRoom/entry/change/", ChangeInfo)
          .then(res => {
            console.log('good',res)
            this.modals.changeCheck= false
            this.$router.push({ name: '자유 SPOT', params: { uid: this.RoomData.uid }})
          })
          .catch(err => {
            console.log(err);
          });
      }
    },
    CreditGo() {
      console.log('end',this.myPosUid)
      const roomPrice = String(this.RoomData.price)
      if (this.myPosUid != 0) {
        var myTeam_uid = 0
        if (this.myTeam == "RED") {
          myTeam_uid = this.RoomData.home_matching_entry_uid
        } else {
          myTeam_uid = this.RoomData.away_matching_entry_uid
        }
        const EnterInfo = new FormData();
        EnterInfo.append("uid", this.$cookies.get("UserInfo").uid);
        EnterInfo.append("positionnum", this.myPosUid);
        EnterInfo.append("team_entry_uid", myTeam_uid);
        EnterInfo.append('price', roomPrice)
        EnterInfo.append("room_uid", this.RoomData.uid)
        axios
          .post(SERVER_URL + "kakaoPay/", EnterInfo)
          .then(res => {
            console.log(res)
            window.location.replace(res.data)
          })
          .catch(err => {
            console.log(err);
          });
      }
    },
    deleteUser() {
      var entry_uid = 0;
      if (this.myTeam === "RED") {
        entry_uid = this.RoomData.home_matching_entry_uid;
      } else {
        entry_uid = this.RoomData.away_matching_entry_uid;
      }
      const entryUid = new FormData();
      entryUid.append("team_entry_uid", entry_uid);
      axios
        .post(SERVER_URL + "FreeMatchRoom/entrylist/", entryUid)
        .then((res) => {
          console.log(this.myPosition)
          var Myuid = this.$cookies.get('UserInfo').uid
          if (res.data.striker1_uid == Myuid) {
            this.myOriginUid = 1;
          } else if (res.data.striker2_uid == Myuid) {
            this.myOriginUid = 2;
          } else if (res.data.striker3_uid == Myuid) {
            this.myOriginUid = 3;
          } else if (res.data.striker4_uid == Myuid) {
            this.myOriginUid = 4;
          } else if (res.data.midfielder1_uid == Myuid) {
            this.myOriginUid = 5;
          } else if (res.data.midfielder2_uid == Myuid) {
            this.myOriginUid = 6;
          } else if (res.data.midfielder3_uid == Myuid) {
            this.myOriginUid = 7;
          } else if (res.data.midfielder4_uid == Myuid) {
            this.myOriginUid = 8;
          } else if (res.data.defender1_uid == Myuid) {
            this.myOriginUid = 9;
          } else if (res.data.defender2_uid == Myuid) {
            this.myOriginUid = 10;
          } else if (res.data.defender3_uid == Myuid) {
            this.myOriginUid = 11;
          } else if (res.data.defender4_uid == Myuid) {
            this.myOriginUid = 12;
          } else if (res.data.goalkeeper_uid == Myuid) {
            this.myOriginUid = 13;
          }
          console.log('!change',this.myOriginUid)
        })
          .then(() => {
              const ChangeInfo = new FormData();
              ChangeInfo.append("uid", this.$cookies.get("UserInfo").uid);
              ChangeInfo.append("positionnum_before", this.myOriginUid);
              ChangeInfo.append("team_entry_uid_before", this.myRealTeam)
              axios
                .post(SERVER_URL + "FreeMatchRoom/entry/cancel/", ChangeInfo)
                .then(res => {
                  console.log('good',res)
                  this.modals.outalert = false
                  this.$router.push({ name: '자유 SPOT', params: { uid: this.RoomData.uid }})
                })
                .catch(err => {
                  console.log(err);
                });
            })
        .catch((err) => {
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
            this.isEnter = true
            this.myRealTeam = this.RoomData.home_matching_entry_uid
            this.myPosition = newPosition
          }
          console.log('red5', this.RedtableDatas)
        })
        .catch(err => {
              console.log(err)
        });
    },
    RedListChange(position) {
      var newPosition = new String("");
      if (position.indexOf("striker") != -1) {
        newPosition = "공격수"
      } else if (position.indexOf("mid") != -1) {
        newPosition = "미드필더"
      } else if (position.indexOf("defend") != -1) {
        newPosition = "수비수"
      } else if (position.indexOf("goal") != -1) {
        newPosition = "골키퍼"
      }
      if (!this.RedpostionList.find(v => v.name == newPosition)) {     
        var Redsub = new Object
        Redsub.name = newPosition
        this.RedpostionList.push(Redsub)
      }
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
            this.myRealTeam = this.RoomData.home_matching_entry_uid
            this.myPosition = newPosition
          }
          console.log('blue5',this.BluetableDatas)
        })
        .catch(err => {
              console.log(err)
        });
    },
    BlueListChange(position) {
      var newPosition = new String("");
      if (position.indexOf("striker") != -1) {
        newPosition = "공격수"
      } else if (position.indexOf("mid") != -1) {
        newPosition = "미드필더"
      } else if (position.indexOf("defend") != -1) {
        newPosition = "수비수"
      } else if (position.indexOf("goal") != -1) {
        newPosition = "골키퍼"
      }
      if (!this.BluepostionList.find(v => v.name == newPosition)) {   
        var Bluesub = new Object
        Bluesub.name = newPosition
        this.BluepostionList.push(Bluesub)
      }
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
        .then(() => {
          console.log('ente',this.isEnter)
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