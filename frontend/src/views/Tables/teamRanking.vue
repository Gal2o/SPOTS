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
        class="table table-striped align-items-center table-flush"
        :class="type === 'dark' ? 'table-dark' : ''"
        :thead-classes="type === 'dark' ? 'thead-dark' : 'thead-light'"
        tbody-classes="list"
        :data="TeamrankData"
      >
        <template slot="columns">
          <th>순위</th>
          <th>팀 이름</th>
          <th>승리</th>
          <th>무승부</th>
          <th>패배</th>
          <th>승률</th>
        </template>

        <template slot-scope="{ row }">
          <th scope="row">
            <div class="media align-items-center">
              <div class="media-body">
                <span class="name mb-0 text-sm"> {{ row.rank }}</span>
              </div>
            </div>
          </th>
          <td scope="row">
            <div class="media align-items-center">
              <div class="media-body">
                <span class="name mb-0 text-sm"> {{ row.team_name }}</span>
              </div>
            </div>
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
        </template>
      </base-table>
    </div>
    <div
      class="card-footer d-flex justify-content-between"
      :class="type === 'dark' ? 'bg-transparent' : ''"
    ></div>
  </div>
</template>
<script>
export default {
  name: "trank-table",
  components: {},
  created() {
    this.$axios
      .get(this.$SERVER_URL + "rank/")
      .then((res) => {
        this.TeamrankData = res.data;
        for(var i=0; i < this.TeamrankData.length; i++) {
          this.TeamrankData[i].rank = i+1
          console.log(this.TeamrankData[i])
        } 
      })
     
  },
  props: {
    type: {
      type: String,
    },
  },
  data() {
    return {
      TeamrankData: [],
      title: "팀 랭킹",
    };
  },
  methods: {},
};
</script>
<style></style>
