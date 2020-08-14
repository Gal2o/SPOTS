<template>
  <div class="card shadow" :class="type === 'dark' ? 'bg-default' : ''">
    <div class="card-header border-0" :class="type === 'dark' ? 'bg-transparent' : ''">
      <div class="row align-items-center">
        <div class="col">
          <h3 class="mb-0" :class="type === 'dark' ? 'text-white' : ''">{{ tabletitle }}</h3>
        </div>
        <div class="col text-right">
          <base-button type="primary" size="sm">더보기</base-button>
        </div>
      </div>
    </div>

    <div class="table-responsive">
      <base-table
        class="table align-items-center table-flush"
        :class="type === 'dark' ? 'table-dark' : ''"
        :thead-classes="type === 'dark' ? 'thead-dark' : 'thead-light'"
        tbody-classes="list"
        :data="FreeTable"
      >
        <template slot="columns">
          <th>제목</th>
          <th>날짜</th>
          <th>주소</th>
          <th>유저 수</th>
          <th>대기 상태</th>
          <th></th>
        </template>

        <template slot-scope="{ row }" >
          <th scope="row">
            <div class="media align-items-center">
              <div class="media-body">
                <router-link
              :to="{
                name: '자유 SPOT',
                params: { uid: row.uid },
              }"
              v-if="isLogined"
            >
                <span class="name mb-0 text-sm" >{{ row.title }}</span>
                </router-link>
              </div>
            </div>
          </th>
          <td class="time">{{ row.matching_date }}</td>
          <td>
            <span class="status">{{ row.dong_code }}</span>
          </td>
          <td>{{ row.numberofuser }}</td>

          <td>
            <div class="d-flex align-items-center">
              <span class="completion mr-2">{{ row.wait }}</span>
            </div>
          </td>

          <td class="text-right">
            <router-link
              :to="{
                name: '자유 SPOT',
                params: { uid: row.uid },
              }"
              v-if="isLogined"
            >
              <base-button type="success">입장하기</base-button>
            </router-link>
            <base-button type="success" v-if="!isLogined" @click="notEnter = true">입장하기</base-button>
          </td>
        </template>
      </base-table>
    </div>
    <div
      class="card-footer d-flex justify-content-between"
      :class="type === 'dark' ? 'bg-transparent' : ''"
    >
      <div class="col-md-4">
        <base-button type="secondary" @click="modals = true">자유SPOT 만들기</base-button>

        <modal :show.sync="modals" body-classes="p-0" modal-classes="modal-dialog modal-md">
          <card
            type="secondary"
            shadow
            header-classes="bg-white pb-5"
            body-classes="px-lg-5 py-lg-5"
            class="border-0"
          >
            <template>
              <div class="text-muted text-center mb-3">
                <medium>자유 SPOT 만들기</medium>
              </div>
            </template>
            <template>
              <div class="text-center text-muted mb-4">
                <small>정보를 입력해주세요</small>
              </div>
              <form role="form">
                <base-input alternative class="mb-3" v-model="title" placeholder="제목을 적어주세요"></base-input>
                <base-input addon-left-icon="ni ni-calendar-grid-58">
                  <flat-picker
                    slot-scope="{ focus, blur }"
                    @on-open="focus"
                    @on-close="blur"
                    :config="{ allowInput: true }"
                    class="form-control datepicker"
                    v-model="dates.simple"
                  ></flat-picker>
                </base-input>
                <base-dropdown class="mr-3">
                  <base-button
                    slot="title"
                    type="secondary"
                    class="dropdown-toggle"
                  >{{ this.stadiumN }}</base-button>
                  <a
                    class="dropdown-item"
                    v-for="stadiumData in stadiumDatas"
                    v-bind:key="stadiumData"
                    @click="choice1(stadiumData)"
                  >{{ stadiumData.place_name }}({{ stadiumData.address }})</a>
                </base-dropdown>
                <div class="text-center">
                  <base-button type="success" @click="getSpot" class="my-4 mr-4">친구 모아보기</base-button>
                  <base-button type="secondary" @click="modals = false">닫기</base-button>
                </div>
              </form>
            </template>
          </card>
        </modal>

        <modal
          :show.sync="isCredit"
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
                    <base-button slot="title" type="secondary" class="dropdown-toggle">{{ myTeam }}</base-button>
                    <a
                      class="dropdown-item"
                      v-for="teamitem in teamList"
                      :key="teamitem"
                      @click="TeamChange(teamitem.name)"
                    >{{ teamitem.name }}</a>
                  </base-dropdown>

                  <base-dropdown class="mr-3">
                    <base-button
                      slot="title"
                      type="secondary"
                      class="dropdown-toggle"
                    >{{ this.myPosition }}</base-button>
                    <a
                      class="dropdown-item"
                      v-for="positonitem in postionList"
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
                      <b>{{ roomprice }}원</b>
                    </big> 입니다.
                  </small>
                </div>
                <div class="text-center">
                  <base-button type="success" class="my-4 mr-4" @click="CreditGo">결제하기</base-button>
                  <base-button type="secondary" @click="isCredit = false">닫기</base-button>
                </div>
              </form>
            </template>
          </card>
        </modal>

        <modal
          :show.sync="notEnter"
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
              @click="notEnter = false"
            >닫기</base-button>
          </template>
        </modal>
      </div>
      <div>
        <base-pagination :page-count="parseInt(FreetableData.length/5)+1" v-model="pagination"></base-pagination>
      </div>
    </div>
  </div>
</template>
<script>
import flatPicker from "vue-flatpickr-component";
import "flatpickr/dist/flatpickr.css";

export default {
  name: "projects-table",
  components: { flatPicker },
  created() {
    if (this.$cookies.isKey("UserInfo")) {
      this.isLogined = true;
    }
    this.$axios
      .get(this.$SERVER_URL + "FreeMatchAll/")
      .then((res) => {
        this.FreetableData = res.data;
        console.log('free3',this.FreetableData)
        for (var a=0; a<this.FreetableData.length; a++){
          if (this.FreetableData[a].mvp == 1 ){
            this.FreetableData.splice(a,1)    
          }
        }
        this.FreeTable = this.FreetableData.slice(0, 5);
      })
      .catch((err) => {
        console.log(err);
      });
    this.$axios
      .get(this.$SERVER_URL + "place/list")
      .then((rest) => {
        console.log(rest.data);
        this.stadiumDatas = rest.data;
      })
      .catch((err) => {
        console.log(err);
      });
  },
  watch: {
    sidolist: function () {
      console.log("check", this.sidolist);
      this.sidoinfo();
    },
    pagination: function () {
      // console.log(this.FreeTable);
      this.selectpage();
    },
  },
  props: {
    type: {
      type: String,
    },
    sidolist: [],
  },

  data() {
    return {
      dates: {
        simple: "2020-08-08",
      },
      isLogined: false,
      tabletitle: "자유 SPOT",
      FreetableData: [],
      modals: false,
      notEnter: false,
      stadiumN: "경기장을 골라주세요",
      stadiumDatas: [],
      title: "",
      placeuid: 0,
      placeprice: 1,
      homeuid:0,
      roomprice:1,
      roomuid:0,
      placecode: 0,
      userInfo: Object,
      pagination: 1,
      FreeTable: [],
      isCredit: false,
      myTeam: 'RED',
      myPosition: "선택해주세요",
      myPosUid: 0,
      teamList: [{ name: "RED" }, { name: "BLUE" }],
      postionList: [
        { name: "랜덤" },
        { name: "공격수" },
        { name: "미드필더" },
        { name: "수비수" },
        { name: "골키퍼" },
      ],
    };
  },

  methods: {
    choice1(stadium) {
      this.stadiumN = stadium.place_name;
      this.placeuid = stadium.place_uid;
      this.placeprice = 8000;
      this.placecode = stadium.code;
    },
    getSpot() {
      if (this.$cookies.isKey("UserInfo")) {
        this.userInfo = this.$cookies.get("UserInfo");
        console.log("uid", this.userInfo.uid);
        console.log("date",typeof(this.dates.simple));
        const makeData = new FormData();
        makeData.append("title", this.title);
        makeData.append("matching_date", this.dates.simple);
        makeData.append("place_uid", 0);
        makeData.append("price", this.placeprice);
        makeData.append("dong_code", this.placecode);
        makeData.append("head_uid", this.userInfo.uid);
        if (this.title != "" && this.placecode != 0) {
          this.$axios
            .post(this.$SERVER_URL + "FRoomCreate/", makeData)
            .then((res) => {
              console.log("chcek", res);
              this.modals = false
              this.isCredit = true
              this.homeuid= res.data[res.data.length-1].home_matching_entry_uid
              this.roomprice = res.data[res.data.length-1].price
              this.roomuid = res.data[res.data.length-1].uid
            })
            .catch((err) => {
              console.log(err);
            });
        } else {
          alert("방 이름과 경기장 모두를 선택해 주세요");
        }
      } else {
        alert("로그인이 필요합니다! 로그인 해주세요.");
      }
    },
    sidoinfo() {
      const sidoData = new FormData();
      // console.log("dfdfdf");
      // console.log("dfdfd", this.sidolist);
      sidoData.append("doe", this.sidolist[0]);
      sidoData.append("si", this.sidolist[1]);
      sidoData.append("dong", this.sidolist[2]);
      sidoData.append("word", this.sidolist[3]);
      this.$axios.post(this.$SERVER_URL + "FreeMatchAll", sidoData).then((res) => {
        this.FreetableData = res.data;
      });
    },
    selectpage() {
      this.FreeTable = [];
      this.FreeTable = this.FreetableData.slice(
        (this.pagination - 1) * 5,
        (this.pagination - 1) * 5 + 5
      );
      console.log(this.FreeTable);
    },
    PositionChange(name) {
      console.log(this.BluepostionList)
      if (name == "랜덤") {
        this.myPosition = this.postionList[Math.floor(
          Math.random() * (this.RedpostionList.length - 1)
        )].name
      } else {
        this.myPosition = name;
      }
    },
    TeamChange(name) {
      this.myTeam = name;
    },
    CreditGo() {
      console.log('end',this.myPosUid)
      if (this.myPosition === "공격수") {
        this.myPosUid = 1;
      } else if (this.myPosition === "미드필더") {
        this.myPosUid = 5;
      } else if (this.myPosition === "수비수") {
        this.myPosUid = 9;
      } else if (this.myPosition === "골키퍼") {
        this.myPosUid = 13;
      }
      const EnterInfo = new FormData();
      const rprice = String(this.roomprice)
      EnterInfo.append("uid", this.$cookies.get("UserInfo").uid);
      EnterInfo.append("positionnum", this.myPosUid);
      EnterInfo.append("team_entry_uid", this.homeuid);
      EnterInfo.append('price', rprice)
      EnterInfo.append("room_uid", this.roomuid)
      console.log("uid", this.$cookies.get("UserInfo").uid)
      console.log("positionnum", this.myPosUid)
      console.log("team_entry_uid", this.homeuid)
      console.log('price', rprice)
      console.log("room_uid", this.roomuid)
      this.$axios
        .post(this.$SERVER_URL + "kakaoPay/", EnterInfo)
        .then(res => {
          console.log(res)
          window.location.replace(res.data)
        })
        .catch(err => {
          console.log(err);
        });
    },
  },
};
</script>
<style></style>
