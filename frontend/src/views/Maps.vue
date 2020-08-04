<template>
  <div>
    <base-header type="gradient-success" class="pb-6 pb-4 pt-5 pt-md-8">
    </base-header>

    <div class="container-fluid mt-4">
      <div class="row">
        <div
          class="col-xl-4 col-lg-6 mt-4"
          v-for="stadiumdata in stadiumDatas"
          v-bind:key="stadiumdata"
        >
          <stats-card
            :title="stadiumdata.address"
            type="gradient-red"
            :sub-title="stadiumdata.place_name"
            icon="ni ni-active-40"
            class="mb-4 mb-xl-0"
          >
            <template slot="footer">
              <span class="text-success mr-2"> 가격 </span>
              <span class="text-nowrap">{{ stadiumdata.price }}원</span>
            </template>
          </stats-card>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import axios from "axios";
const SERVER_URL = "http://localhost:8080/";

export default {
  created() {
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
  data() {
    return {
      stadiumDatas: [],
    };
  },
};
</script>
<style></style>
