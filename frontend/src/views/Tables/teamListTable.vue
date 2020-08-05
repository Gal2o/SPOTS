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
            <base-button v-if="isLogined" type="success" size="s" @click="modals.entermessage = true">
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
            <h1>팀 정보</h1>
          </div>
        </template>
        <template>
          <table>
            <tr>
              <th>팀명</th>
              <th></th>
            </tr>
            <tr>
              <th>팀소개</th>
              <th></th>
            </tr>
            <tr>
              <th>인원</th>
              <th></th>
            </tr>
            <tr>
              <th>팀전적</th>
              <th></th>
            </tr>
            <tr>
              <th>승률</th>
              <th></th>
            </tr>
            <tr>
              <th>팀 멤버</th>
            </tr>
          </table>
          <!-- isLogined를 신청상태인지확인 -->
          <div class="text-center ">
            <base-button v-if="isLogined" type="success" size="lg" @click="modals.joinTeam = true">
              <h2 class="text-white">가입하기</h2>
            </base-button>
            <base-button v-if="!isLogined" type="success" size="lg" @click="modals.entermessage = true">
              <h2 class="text-white">가입취소</h2>
            </base-button>
          </div>
        </template>
      </card>
    </modal> 

    <modal :show.sync="modals.joinTeam">
      <div class="py-3 text-center">
        <h2>팀 가입신청</h2>
        <br><br><h1>가입신청서</h1>
        <p>팀명 : </p> <p></p>
        <p>유저명 : </p> <p></p>
        <p>가입신청 내용 : </p>
        <textarea rows="4" style="width:100%; resize:none"></textarea>
      </div>
      <div class="text-center ">
        <base-button size="lg" @click="modals.joinTeam = true">
          <h2 class="text-white">신청완료</h2>
        </base-button>
      </div>

      <template slot="footer">

      </template>
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
    }
    axios
      .get(SERVER_URL + "team/list")
      .then((res) => {
        this.FreerankData = res.data;
        console.log(this.FreerankData);
      })
      .catch((err) => {
        console.log(err);
      });
  },
  props: {
    type: {
      type: String,
    },
  },
  data() {
    return {
      FreerankData: [],
      title: "팀 리스트",
       modals: {
        loginalert: false,
        entermessage: false,
        joinTeam: false,
      }
    };
  },
  methods: {},
};
</script>
<style></style>
