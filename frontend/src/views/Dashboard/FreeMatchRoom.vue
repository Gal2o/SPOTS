<template>
  <div>
    <base-header type="gradient-success" class="pb-4 pt-3 pt-md-6">
      <div class="row d-flex flex-row justify-content-between">
        <div class="col-7">
          <card title="Room information" class="mb-4 mb-xl-0">
            <h2>{{ RoomData.title }}</h2>
          </card>
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
          :data="tableDatas"
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
              <base-dropdown>
                <base-button slot="title" class="dropdown-toggle">{{ row.position }}</base-button>
                <a class="dropdown-item" @click="PositionChange('랜덤')">랜덤</a>
                <a class="dropdown-item" @click="PositionChange('공격수')">공격수</a>
                <a class="dropdown-item" @click="PositionChange('수비수')">수비수</a>
                <a class="dropdown-item" @click="PositionChange('미드필더')">미드필더</a>
                <a class="dropdown-item" @click="PositionChange('골키퍼')">골키퍼</a>
              </base-dropdown>
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
          :data="othertableDatas"
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
              <base-dropdown>
                <base-button slot="title" class="dropdown-toggle">{{ row.position }}</base-button>
                <a class="dropdown-item" v-for="positonitem in postionList" :key="positonitem">{{ positonitem.name }}</a>
              </base-dropdown>
            </td>
          </template>
        </base-table>
      </div>
    </div>

    <div class="d-flex flex-row justify-content-end mr-5">
      <base-button v-if="!isLogined" type="success" size="lg" @click="modals.loginalert = true">
        <h2 class="text-white">입장하기</h2>
      </base-button>
      <base-button v-if="isLogined" type="success" size="lg" @click="modals.entermessage = true">
        <h2 class="text-white">입장하기</h2>
      </base-button>
    </div>

    <modal :show.sync="modals.loginalert"
            gradient="danger"
            modal-classes="modal-danger modal-dialog-centered">

        <div class="py-3 text-center">
            <i class="ni ni-bell-55 ni-3x"></i>
            <h4 class="heading mt-4">로그인을 하셔야 이용할 수 있습니다.</h4>
            <p>로그인 페이지로 이동하셔서 로그인을 먼저 진행해주세요.</p>
        </div>

        <template slot="footer">
          <router-link to="/login">
            <base-button type="white">로그인하기</base-button>
          </router-link>
            <base-button type="link"
                          text-color="white"
                          class="ml-auto"
                          @click="modals.loginalert = false">
                닫기
            </base-button>
        </template>
    </modal>

    <modal :show.sync="modals.entermessage" body-classes="p-0" modal-classes="modal-dialog modal-md">
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
                <small>포지션을 선택해주세요.</small>
              </div>
              <form role="form">
                <div class="d-flex justify-content-center">                
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
                  <small>포지션을 선택하였으면 결재를 해주세요.</small>
                </div>
                <div class="text-center">
                  <router-link to="/dashboard/FreeMatch">
                    <base-button type="success" class="my-4 mr-4">결재하기</base-button>
                  </router-link>
                  <base-button type="secondary" @click="modals.entermessage = false">닫기</base-button>
                </div>
              </form>
            </template>
          </card>
        </modal>
  </div>
</template>
<script>
import axios from "axios";

const SERVER_URL = "http://localhost:8080/";

export default {
  components: {},
  data() {
    return {
      head_uid: 1,
      RoomData: Object,
      tableDatas: [
        {
          name: "SPOTs관리자",
          position: "랜덤",
        },
        {
          name: "SPOTs테스터",
          position: "수비수",
        },
      ],
      othertableDatas: [
        {
          name: "SPOTs테스터2",
          position: "공격수",
        },
        {
          name: "SPOTs테스터3",
          position: "골키퍼",
        },
      ],
      isLogined: false,
      myPosition: '랜덤',
      postionList: [
        {name: '랜덤'},
        {name: '공격수'},
        {name: '미드필더'},
        {name: '수비수'},
        {name: '골키퍼'},
      ],
      modals: {
        loginalert: false,
        entermessage: false,
      }
    }
  },
  methods: {
    PositionChange(name) {
      this.myPosition = name
    },
  },
  mounted() {},
  created() {
    if (this.$cookies.isKey("UserInfo")) {
      this.isLogined = true
    }
    const FreeRoomData = new FormData();
    FreeRoomData.append("uid", this.head_uid);
    axios.post(SERVER_URL + "FreeMatchRoom/", FreeRoomData)
      .then(res => {
        console.log(res)
        if (res.data == "") {
          alert('문제가 발생하였습니다. 메인페이지로 돌아갑니다.');
          this.$router.push({ name: "SPOTs" });
        }
        else {
          this.RoomData = res.data[0]
          console.log(this.RoomData)
        }
      })
      .catch(err => {
        console.log(err);
        alert('문제가 발생하였습니다. 메인페이지로 돌아갑니다.');
        this.$router.push({ name: "SPOTs" });
      })  
  }
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
