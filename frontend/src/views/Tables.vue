<template>
  <div>
    <base-header type="gradient-success" class="pb-6 pb-4 pt-5 pt-md-8">
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
                >승률 {{ this.FreerankData[0].team_rate }}%</span
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
                >승률 {{ this.FreerankData[19].team_rate }}%</span
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
                >승리 수 {{ this.FreerankData[1].team_win }}</span
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
                >승률 {{ this.FreerankData[8].team_lose }}</span
              >
            </template>
          </stats-card>
        </div>
      </div>
    </base-header>
    <div class="container-fluid mt-4">
      <div class="row">
        <div class="col">
          <trank-table title="Light Table"></trank-table>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import teamRanking from "./Tables/teamRanking";
import axios from "axios";
const SERVER_URL = "http://localhost:8080/spots/";

export default {
  name: "tables",
  created() {
    axios
      .get(SERVER_URL + "rank/")
      .then((res) => {
        this.FreerankData = res.data;
        console.log(this.FreerankData);
      })
      .catch((err) => {
        console.log(err);
      });
  },
  components: {
    "trank-table": teamRanking,
  },
  data() {
    return {
      FreerankData: [],
    };
  },
  methods: {},
  mounted() {},
};
</script>
<style></style>
