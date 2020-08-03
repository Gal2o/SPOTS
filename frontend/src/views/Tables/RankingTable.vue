<template>
  <div class="card shadow" :class="type === 'dark' ? 'bg-default' : ''">
    <div class="card-header border-0" :class="type === 'dark' ? 'bg-transparent' : ''">
      <div class="row align-items-center">
        <div class="col">
          <h3 class="mb-0" :class="type === 'dark' ? 'text-white' : ''">{{ title }}</h3>
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
          <th></th>
          <th></th>
          <th></th>
          <th></th>
          <th></th>
          <th></th>
        </template>

        <template slot-scope="{ row }">
          <th scope="row">
            <div class="media align-items-center">
              <div class="media-body">
                <span class="name mb-0 text-sm">{{ row.title }}</span>
              </div>
            </div>
          </th>
          <td class="time">{{ row.matching_date }}</td>
          <td>
            <span class="status">{{ row.dong_name }}</span>
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
                    slot-scope="{focus, blur}"
                    @on-open="focus"
                    @on-close="blur"
                    :config="{allowInput: true}"
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
                  >{{ stadiumData.place_name }}</a>
                </base-dropdown>
                <div class="text-center">
                  <base-button type="success" @click="getSpot" class="my-4 mr-4">친구 모아보기</base-button>
                  <base-button type="secondary" @click="modals = false">닫기</base-button>
                </div>
              </form>
            </template>
          </card>
        </modal>
      </div>
      <base-pagination total="30"></base-pagination>
    </div>
  </div>
</template>
<script>
import axios from "axios";
import flatPicker from "vue-flatpickr-component";
import "flatpickr/dist/flatpickr.css";

const SERVER_URL = "http://localhost:8080/";

export default {
  name: "ranking-table",
  components: { flatPicker },
  created() {
    axios
      .get(SERVER_URL + "FreeMatchMain/")
      .then((res) => {
        this.FreetableData = res.data;
      })
      .catch((err) => {
        console.log(err);
      });
    axios
      .get(SERVER_URL + "place/list")
      .then((rest) => {
        console.log(rest.data);
        this.stadiumDatas = rest.data;
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
      dates: {
        simple: "2020-07-30",
      },
      FreetableData: [],
      modals: false,
      stadiumN: "경기장을 골라주세요",
      stadiumDatas: [],
      title: "",
      placeuid: 0,
      placeprice: 0,
      placecode: 0,
      userInfo: Object,
    };
  },
  methods: {
    choice1(stadium) {
      this.stadiumN = stadium.place_name;
      this.placeuid = stadium.place_uid;
      this.placeprice = stadium.placeprice;
      this.placecode = stadium.code;
    },
    getSpot() {
      this.userInfo = this.$cookies.get("UserInfo");
      const makeData = new FormData();
      makeData.append("title", this.title);
      makeData.append("matching_date", this.dates.simple);
      makeData.append("place_uid", this.placeuid);
      makeData.append("place_price", this.placeprice);
      makeData.append("place_code", this.placecode);
      makeData.append("uid", this.userInfo.uid);
      axios
        .post(SERVER_URL + "FRoomCreate/", makeData)
        .then((res) => {
          console.log(res);
          if (res.data == "") {
            alert("제대로 입력해주세요.");
          } else {
            this.$router.push({ name: "freematchroom" });
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
};
</script>
<style></style>
