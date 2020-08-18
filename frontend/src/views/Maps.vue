<template>
  <div>
    <base-header class="pb-8 pt-md-8 mb-4 text-center">
     <h2 class="mb-4 text-white"></h2>
      <div class="row justify-content-center">
          <div class="form-group mb-0">
          </div>
      </div>
    </base-header>

    <div class="container-fluid">
      <div class="row">
        <div
          class="col-xl-4 col-lg-6 mt-4"
          v-for="(stadiumdata,i) in stadiumDatas"
          v-bind:key="i"
          @click="modal1 = true"
        >
          <stats-card
            :title="stadiumdata.address"
            type="gradient-red"
            :sub-title="stadiumdata.place_name"
            class="mb-4 mb-xl-0 card"
          >
          <img :src="'img/stadium/' + stadiumdata.place_name + '.jpg'" class="card-img-top" style="width:300x; height:180px;" alt="경기장 사진"/>
            <template slot="footer">
              <h2 class="text mr-2"> {{stadiumdata.place_name}}</h2>
              <div class="row justify-content-between">
              <h4 class="text-success mx-3">  {{stadiumdata.address}} </h4>
              <h4 class="text-warning mx-4">{{ stadiumdata.price }}원</h4>
              </div>
            </template>
          </stats-card>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  created() {
    this.$axios
      .get(this.$SERVER_URL + "place/list")
      .then((rest) => {
        this.stadiumDatas = rest.data;
      })
   
  },
  data() {
    return {
      stadiumDatas: [],
      modal1:false,
    };
  },
};
</script>
<style></style>
