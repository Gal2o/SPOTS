<template class="text-center">
  <div>
    <base-header class="pb-8 pt-md-8 mb-4 text-center">
      <!-- Card stats -->
      <div class="row">
        <div class="col-xl-3 col-lg-6">
          <stats-card
            title="이번주 최대 승률팀"
            type="gradient-red"
            sub-title="성남FC"
            icon="ni ni-active-40"
            class="mb-4 mb-xl-0"
          >
            <template slot="footer">
              <span class="text-success mr-2">
                <i class="fa fa-arrow-up">순위 업! 1등</i>
              </span>
              <span class="text-nowrap"
                >승률 {{ this.maxrate }}%</span
              >
            </template>
          </stats-card>
        </div>
        <div class="col-xl-3 col-lg-6">
          <stats-card
            title="이번주 최소 승률팀"
            type="gradient-orange"
            sub-title="서울 노원 유나이티드 FC"
            icon="ni ni-chart-pie-35"
            class="mb-4 mb-xl-0"
          >
            <template slot="footer">
              <span class="text-danger mr-2">
                <i class="fa fa-arrow-down"></i> 분발하세요!
              </span>
              <span class="text-nowrap"
                >승률 {{ this.minrate }}%</span
              >
            </template>
          </stats-card>
        </div>
        <div class="col-xl-3 col-lg-6">
          <stats-card
            title="이번주 최다 승리팀"
            type="gradient-green"
            sub-title="수원 삼성 블루윙즈"
            icon="ni ni-money-coins"
            class="mb-4 mb-xl-0"
          >
            <template slot="footer">
              <span class="text-success mr-2">
                <i class="fa fa-arrow-up"></i> 대단해요!
              </span>
              <span class="text-nowrap"
                >승리 수 {{ this.maxwin }}</span
              >
            </template>
          </stats-card>
        </div>
        <div class="col-xl-3 col-lg-6">
          <stats-card
            title="이번주 최다 패배팀"
            type="gradient-info"
            sub-title="양주시민축구단"
            icon="ni ni-chart-bar-32"
            class="mb-4 mb-xl-0"
          >
            <template slot="footer">
              <span class="text-danger mr-2">
                <i class="fa fa-arrow-down"></i> 화이팅!
              </span>
              <span class="text-nowrap"
                >패배수 {{ this.maxlose }}</span
              >
            </template>
          </stats-card>
        </div>
      </div>
    </base-header>
    <div class="container-fluid mt-4 ">
      <div class="row">
      <div class="col-6 text-center" >
      <base-button @click="freespot = true">개인 랭킹</base-button>
      </div>
      <div class="col-6 text-center">
      <base-button @click="freespot = false">팀 랭킹</base-button>
      </div>
      </div>
      </div>
    <div class="container-fluid mt-4" v-if="freespot == false">
      <div class="row">
        <div class="col">
          <trank-table title="Light Table"></trank-table>
        </div>
      </div>
    </div>
    <div class="container-fluid mt-4" v-if="freespot == true">
      <div class="row">
        <div class="col">
          <frank-table title="Light Table"></frank-table>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import teamRanking from "./Tables/teamRanking";
import freeRanking from "./Tables/freeRanking";

export default {
  name: "tables",
  created() {
    this.$axios
      .get(this.$SERVER_URL + "rank/")
      .then((res) => {
        this.FreerankData = res.data;
        for (var a=0; a<this.FreerankData.length; a++){
          if ( this.maxwin < this.FreerankData[a].team_win ){
            this.maxwin = this.FreerankData[a].team_win
          }
          if ( this.maxrate < this.FreerankData[a].team_rate ){
            this.maxrate = this.FreerankData[a].team_rate
          }
          if ( this.maxlose < this.FreerankData[a].team_lose) {
            this.maxlose = this.FreerankData[a].team_lose
          }
          if ( this.minrate > this.FreerankData[a].team_rate ){
            this.minrate = this.FreerankData[a].team_rate
          }
        }
      })
      .catch((err) => {
        console.log(err);
      });
  },
  components: {
    "trank-table": teamRanking,
    "frank-table": freeRanking,
  },
  data() {
    return {
      FreerankData: [],
      freespot : true,
      minrate : 100,
      maxwin : 0,
      maxlose : 0,
      maxrate : 0,
    };
  },
  methods: {},
  mounted() {},
};
</script>
<style></style>
