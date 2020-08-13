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
          <modal :show.sync="modal1" body-classes="p-0" modal-classes="modal-dialog modal-md">
          <card
            type="secondary"
            shadow
            header-classes="bg-white pb-5"
            body-classes="px-lg-5 py-lg-5"
            class="border-0"
          >
            <template >
              <div class="text-muted text-center mb-3">
                <medium>경기장 세부정보</medium>
              </div>
            </template>
            <template>
              <div class="text-center text-muted mb-4">
                <small>세부 사항</small>
              </div>
              <form role="form">
                <div class="text-center">
                  <base-button class="mt-3" type="secondary" @click="modal1 = false">닫기</base-button>
                </div>
              </form>
            </template>
          </card>
        </modal>
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
      modal1:false,
    };
  },
};
</script>
<style></style>
