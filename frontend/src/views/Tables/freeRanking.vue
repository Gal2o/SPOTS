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
        :data="FreerankData"
      >
        <template slot="columns">
          <th>이름</th>
          <th>승리</th>
          <th>무승부</th>
          <th>패배</th>
          <th>득점</th>
          <th>도움</th>
        </template>

        <template slot-scope="{ row }">
          <th scope="row">
            <div class="media align-items-center">
              <div class="media-body">
                <span class="name mb-0 text-sm"> {{ row.nickname }}</span>
              </div>
            </div>
          </th>
          <td>
            <span class="status">{{ row.win }}</span>
          </td>
          <td>
            <span class="status">{{ row.draw }}</span>
          </td>
          <td>{{ row.lose }}</td>
          <td>
            <span class="status">{{ row.goal }}</span>
          </td>
           <td>
            <span class="status">{{ row.assist }}</span>
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
const SERVER_URL = "http://localhost:8080/spots/";

export default {
  name: "frank-table",
  components: {},
  created() {
    this.$axios
      .get(SERVER_URL + "rank/free")
      .then((res) => {
        this.FreerankData = res.data;
        console.log('res123',this.FreerankData);
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
      title: "개인 랭킹",
    };
  },
  methods: {},
};
</script>
<style></style>
