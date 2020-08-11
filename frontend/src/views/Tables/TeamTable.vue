<template>
  <div class="card shadow" :class="type === 'dark' ? 'bg-default' : ''">
    <div
      class="card-header border-0"
      :class="type === 'dark' ? 'bg-transparent' : ''"
    >
      <div class="row align-items-center">
        <div class="col">
          <h3 class="mb-0" :class="type === 'dark' ? 'text-white' : ''">
            {{ title }}
          </h3>
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
        :data="TeamTable"
      >
        <template slot="columns">
          <th>제목</th>
          <th>날짜</th>
          <th>주소</th>
          <th>유저 수</th>
          <th>대기 상태</th>
          <th></th>
        </template>

        <template slot-scope="{ row }">
          <th scope="row">
            <div class="media align-items-center">
              <!-- <a href="#" class="avatar rounded-circle mr-3">
                <img alt="Image placeholder" :src="row.img" />
              </a>-->
              <div class="media-body">
                <span class="name mb-0 text-sm">{{ row.title }}</span>
              </div>
            </div>
          </th>
          <td class="time">{{ row.matching_date }}</td>
          <td>
            <span class="status">{{ row.place }}</span>
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
                name: '팀 SPOT',
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
      class="card-footer d-flex justify-content-end"
      :class="type === 'dark' ? 'bg-transparent' : ''"
    >
     <div class="col-md-4">
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
    </div>
    <div>
      <base-pagination :page-count="parseInt(TeamtableData.length/5)+1" v-model="pagination"></base-pagination>
    </div>
  </div>
</template>
<script>
import axios from "axios";
const SERVER_URL = "http://localhost:8080/";

export default {
  name: "team-table",
  created() {
    if (this.$cookies.isKey("UserInfo")) {
      this.isLogined = true;
    }
    axios
      .get(SERVER_URL + "TeamMatchAll/")
      .then((res) => {
        this.TeamtableData = res.data;
        this.TeamTable = this.TeamtableData.slice(0,5);
      })
      .catch((err) => {
        console.log(err);
      });
  },
  props: {
    type: {
      type: String,
    },
    title: String,
  },
  data() {
    return {
      TeamtableData: [],
      TeamTable: [],
      notEnter: false,
      pagination: 1,
    };
  },
  watch: {
    sidolist: function () {
      this.sidoinfo();
    },
    pagination: function () {
      // console.log(this.FreeTable);
      this.selectpage();
    },
  },
  methods: {
    selectpage() {
      this.TeamTable = [],
      this.TeamTable = this.TeamtableData.slice(
        (this.pagination - 1) * 5,
        (this.pagination - 1) * 5 + 5
      );
    }
  },
};
</script>
<style></style>
