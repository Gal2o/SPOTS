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
            <h1 class="display-2 text-white">{{ model.team_name }}의 프로필</h1>
            <p class="text-white mt-0 mb-5">
              팀의 프로필 페이지입니다.
              <br />팀의 정보 확인 및 수정이 가능합니다.
            </p>
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
                <div class="row">
                  <div class="col">
                    <div class="card-profile-stats d-flex justify-content-center">
                      <div>
                        <span class="description">이름</span>
                        <span class="heading">{{ player.nickname }}</span>
                      </div>
                      <div>
                        <span class="description">승</span>
                        <span class="heading">{{ player.win }}</span>
                      </div>
                      <div>
                        <span class="description">무</span>
                        <span class="heading">{{ player.draw }}</span>
                      </div>
                      <div>
                        <span class="description">패</span>
                        <span class="heading">{{ player.lose }}</span>
                      </div>
                    </div>
                  </div>
                </div>
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
                    <div class="col-lg-6">
                      <base-input
                        alternative
                        label="팀명"
                        input-classes="form-control-alternative"
                        v-model="model.team_name"
                        readonly
                      />
                    </div>
                    <div class="col-lg-6">
                      <base-input
                        alternative
                        label="승률"
                        placeholder="이메일을 입력해주세요"
                        input-classes="form-control-alternative"
                        v-model="model.team_rate"
                        readonly
                      />
                    </div>
                  </div>
                </div>
                <div class="pl-lg-4">
                  <div class="row">
                    <div class="col-lg-4">
                      <base-input
                        alternative
                        label="시"
                        placeholder="시"
                        input-classes="form-control-alternative"
                        v-model="model.city"
                      />
                    </div>
                    <div class="col-lg-4">
                      <base-input
                        alternative
                        label="구"
                        placeholder="구"
                        input-classes="form-control-alternative"
                        v-model="model.country"
                      />
                    </div>
                  </div>
                </div>
                <div class="pl-lg-4">
                  <div class="form-group">
                    <base-input alternative label="팀 소개">
                      <textarea
                        rows="4"
                        class="form-control form-control-alternative"
                        placeholder="A few words about you ..."
                        v-model="model.team_intro"
                      >
간략히 팀을 소개해 주세요
                      </textarea>
                    </base-input>
                  </div>
                </div>
                <a href="#!" class="btn btn-info center">팀정보 수정</a>
              </form>
            </template>
          </card>
        </div>
      </div>
    </div>
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
  }
};
</script>
<style></style>
