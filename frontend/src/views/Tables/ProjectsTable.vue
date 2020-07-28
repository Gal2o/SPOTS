<template>
  <div class="card shadow" :class="type === 'dark' ? 'bg-default' : ''">
    <div class="card-header border-0" :class="type === 'dark' ? 'bg-transparent' : ''">
      <div class="row align-items-center">
        <div class="col">
          <h3 class="mb-0" :class="type === 'dark' ? 'text-white' : ''">{{ title }}</h3>
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
        :data="FreetableData"
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
              <a href="#" class="avatar rounded-circle mr-3">
                <img alt="Image placeholder" :src="row.img" />
              </a>
              <div class="media-body">
                <span class="name mb-0 text-sm">{{ row.title }}</span>
              </div>
            </div>
          </th>
          <td class="time">{{ row.time }}</td>
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
            <router-link to="/dashboard/FreeMatch">
              <base-button type="success">입장하기</base-button>
            </router-link>
          </td>
        </template>
      </base-table>
    </div>

    <div
      class="card-footer d-flex justify-content-end"
      :class="type === 'dark' ? 'bg-transparent' : ''"
    >
      <base-pagination total="30"></base-pagination>
    </div>
  </div>
</template>
<script>
import axios from "axios";
const SERVER_URL = "http://localhost:8080/";

export default {
  name: "projects-table",
  created() {
    axios
      .get(SERVER_URL + "FreeMatchMain/")
      .then((res) => {
        console.log(res);
        this.FreetableData = res;
      })
      .catch((err) => {
        console.log(err);
      });

    axios
      .get(SERVER_URL + "TeamMatchMain/")
      .then((res) => {
        console.log(res);
        this.TeamtableData = res;
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
      FreetableData: [],
      TeamtableData: [],
    };
  },
};
</script>
<style></style>
