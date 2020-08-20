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
                    <img :src=imgurl class="rounded-circle" />
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

                  <router-link to="/profile">
                  <h3 class="mb-0 ml-3">내 정보 </h3>
                  </router-link>
                  <p class="mx-2 mb-0">/</p>
                  <router-link to="/Teamprofile">
                  <h3 class="mb-0">팀 프로필</h3>
                  </router-link>

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
                            v-for="(sData,i) in teamData.stateDatas"
                            v-bind:key="i"
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
                            v-for="(cData,i) in teamData.cityDatas"
                            v-bind:key="i"
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
                        class="form-control form-control-alternative text-default"
                        placeholder="A few words about you ..."
                        v-model="model.team_intro"
                      >
                        간략히 팀을 소개해 주세요
                      </textarea>
                  </div>
                </div>
                <div class="d-flex justify-content-around mt-3">
                  <base-button type="info" size="lg" @click="ModifyTeam" v-if="isHeader">
                    <h3 class="text-white mb-0">팀정보 수정</h3>
                  </base-button>
                  <base-button type="success" size="lg" @click="modals.applyList = true" v-if="isHeader">
                    <h3 class="text-white mb-0">가입신청서 확인</h3>
                  </base-button>
                  <base-button type="danger" size="lg" @click="modals.teamout = true" v-if="!isHeader">
                    <h3 class="text-white mb-0">탈퇴</h3>
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
                <base-button type="primary" size="sm">
                  <h2 class="text-white" @click="JoinTeam(row.uid)">승인</h2>
                </base-button>
                <base-button type="danger" size="sm" @click="rejectApply(row.uid)">
                  <h2 class="text-white">거절</h2>
                </base-button>
              </td>
            </template>
          </base-table>
        </div>
      </card>
    </modal>

    <modal :show.sync="modals.modifysucess">
      <h2 slot="header" class="modal-title text-center">변경 성공</h2>

      <p>입력하신 정보가 성공적으로 변경되었습니다.</p>

      <template slot="footer">
        <base-button type="link" class="mx-auto" @click="modalSwitch(4)">닫기</base-button>
      </template>
    </modal>

    <modal
        :show.sync="modals.teamout"
        gradient="danger"
        modal-classes="modal-danger modal-dialog-centered"
      >
      <div class="py-3 text-center">
        <i class="ni ni-bell-55 ni-3x"></i>
        <h4 class="heading mt-4">정말 팀을 탈퇴하시겠습니까?</h4>
        <p>팀을 탈퇴하시면 팀리스트 페이지로 이동합니다.<br>
        탈퇴하지 않는다면 아니오를 눌러 주세요.</p>
      </div>

      <template slot="footer">
        <router-link to="/login">
          <base-button type="white" @click="OutTeam">탈퇴하기</base-button>
        </router-link>
        <base-button
          type="link"
          text-color="white"
          class="ml-auto"
          @click="modals.teamout = false"
        >아니오</base-button>
      </template>
    </modal>
  </div>
</template>
<script>
export default {
  name: "user-profile",
  data() {
    return {
      isHeader: false,
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
      imgurl: "",
      player: [],
      apply: [],
      applyPlayer: [],
      modals: {
        applyList: false,
        applyDetail: false,
        modifysucess: false,
        teamout: false,
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
    if (this.$cookies.get("UserInfo").team_uid != 1) {
    const data = new FormData();
    data.append("uid", this.$cookies.get("UserInfo").team_uid);
    this.$axios
      .post(this.$SERVER_URL + "/team/detail", data)
      .then((res) => {
        this.model = res.data;
        if (res.data == undefined){
          this.model.team_name = "매니저"
        }
        if (res.data.captain_uid == this.$cookies.get('UserInfo').uid) {
          this.isHeader = true
        }
      })  

    this.$axios
      .post(this.$SERVER_URL + "/team/userList", data)
      .then((res) => {
        this.player = res.data;
      })  
     
    this.$axios
      .post(this.$SERVER_URL + "team/applyList/", data)
      .then((res) => {
   
        let applySub = res.data;
        for(var i=0; i < applySub.length; i++) {
          var j = i
          var userForm = new FormData()
          userForm.append('uid', applySub[i].user_uid)
          this.$axios.post(this.$SERVER_URL + 'user/detail2/', userForm)
            .then(res => {
              var applyData = res.data
              applyData.comment = applySub[j].comment
           
              this.applyPlayer.push(applyData)
            })
         
        }
      })  
    
    this.$axios
      .get(this.$SERVER_URL + "stateList")
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
   
    
    var logonum = ((this.$cookies.get("UserInfo").team_uid-52)%40)+1
    this.imgurl = 'img/teamLogo/club_logo ('+ logonum +').jpg'
    }
    else {
      this.model.team_name = "매니저"
    }
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
      } else if (switchData == 4) {
        this.modals.modifysucess = false
          .then(window.location.reload())
      }

    },
    pickData() {      
      if (this.model != undefined){
      var dummyState = this.model.city_code.slice(0, 2) + "00000000"

      for(var i=0; i < this.teamData.stateDatas.length; i++) {
        if (this.teamData.stateDatas[i].state_code == dummyState) {
          this.teamData.statePick = this.teamData.stateDatas[i].state_name
        }
      }

      var Cityform = new FormData()
      Cityform.append("state_code", dummyState);
      this.$axios.post(this.$SERVER_URL + "cityList", Cityform)
        .then((res) => {
          this.teamData.cityDatas = res.data;
          
          for(var i=0; i < this.teamData.stateDatas.length; i++) {
            if (this.teamData.cityDatas[i].city_code == this.model.city_code) {
              this.teamData.cityPick = this.teamData.cityDatas[i].city_name
            }
          }
        })
      
      }
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
    ModifyTeam() {
      var modifyForm = new FormData()
      modifyForm.append('captain_uid', this.model.captain_uid)
      modifyForm.append('city_code', this.model.city_code)
      modifyForm.append('player_num', this.model.player_num)
      modifyForm.append('team_draw', this.model.team_draw)
      modifyForm.append('team_intro', this.model.team_intro)
      modifyForm.append('team_lose', this.model.team_lose)
      modifyForm.append('team_name', this.model.team_name)
      modifyForm.append('team_rate', this.model.team_rate)
      modifyForm.append('team_win', this.model.team_win)
      modifyForm.append('uid', this.model.uid)
      this.$axios.post(this.$SERVER_URL + 'team/modify/', modifyForm)
        .then(() => {
  
          this.modals.modifysucess = true
        })
  
    },
    OutTeam() {
   
      var OutForm = new FormData()
      OutForm.append('uid', this.$cookies.get('UserInfo').uid)
      OutForm.append('team_uid', this.model.uid)
      this.$axios.post(this.$SERVER_URL + 'user/outTeam', OutForm)
        .then(() => {          
          var userform = new FormData()
          userform.append('uid', this.$cookies.get('UserInfo').uid)
          this.$axios.post(this.$SERVER_URL + "user/detail2/", userform)
            .then((res) => {
              console.log(res.data)
              this.$cookies.remove('UserInfo')
              this.$cookies.set('UserInfo', res.data)
              console.log(this.$cookies.get('UserInfo'))
              this.$router.push({ name: "팀 리스트" })
            })
            .catch((err) => {
              console.log(err);
            });
        })
       
    },
    JoinTeam(wantUser) {
      
      var JoinForm = new FormData()
      JoinForm.append('uid', wantUser)
      JoinForm.append('team_uid', this.model.uid)
      this.$axios.post(this.$SERVER_URL + 'user/joinTeam', JoinForm)
        .then(() => {
 
          this.modals.applyList = false
        })
        
    },
    rejectApply(wantUser) {
      var rejectForm = new FormData()
      rejectForm.append('uid', wantUser)
      rejectForm.append('team_uid', this.model.uid)
      this.$axios.post(this.$SERVER_URL + 'user/rejectTeam', rejectForm)
        .then(() => {
     
          this.modals.applyList = false
        })
     
    },
  },
};
</script>
<style type="text/css">
            @font-face {
    font-family: 'paybooc-Bold';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-07@1.0/paybooc-Bold.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}
            
div{
 font-family: 'paybooc-Bold';

}
h1{
  margin-bottom:0px;
}
h2{
  margin-bottom:0px;
}
h3{
  margin-bottom:0px;
}
h4{
  margin-bottom:0px;
}
h5{
  margin-bottom:0px;
}
h6{
  margin-bottom:0px;
}
</style>