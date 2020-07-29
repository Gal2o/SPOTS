<template>
  <div>
    <base-header type="gradient-success" class="pb-6 pt-5 pt-md-9">
      <div class="row">
        <div class="col-9">
          <card title="Room information" class="mb-4 mb-xl-0">
            <h2>방 제목</h2>
          </card>
        </div>

        <div class="row">
          <card title="Back icon" class="mb-4 mb-xl-0 ml-7">
            <router-link to="/dashboard">
              <div class="row">
                <i class="ni ni-bold-left ni-2x"></i>
                <h2>방 나가기</h2>
              </div>
            </router-link>
          </card>
        </div>
      </div>
    </base-header>

    <div class="row" :class="type === 'dark' ? 'bg-default' : ''">
      <div class="table-responsive col-6">
        <base-table
          class="table align-items-center table-flush border-solid"
          :class="type === 'dark' ? 'table-dark' : ''"
          :thead-classes="type === 'dark' ? 'thead-dark' : 'thead-light'"
          tbody-classes="list"
          :data="tableDatas"
        >
          <template slot="columns">
            <th>유저 명</th>
            <th>포지션</th>
          </template>

          <template slot-scope="{ row }">
            <th scope="row">
              <div class="media align-items-center">
                <a href="#" class="avatar rounded-circle mr-3">
                  <img alt="Image placeholder" :src="row.img" />
                </a>
                <div class="media-body">
                  <span class="name mb-0 text-sm">{{ tabledata }}</span>
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
          </template>
        </base-table>
      </div>

      <div class="table-responsive col-6">
        <base-table
          class="table align-items-center table-flush"
          :class="type === 'dark' ? 'table-dark' : ''"
          :thead-classes="type === 'dark' ? 'thead-dark' : 'thead-light'"
          tbody-classes="list"
          :data="tableData"
        >
          <template slot="columns">
            <th>유저 명</th>
            <th>포지션</th>
          </template>

          <template slot-scope="{ row }">
            <th scope="row">
              <div class="media align-items-center">
                <a href="#" class="avatar rounded-circle mr-3">
                  <img alt="Image placeholder" :src="row.img" />
                </a>
                <div class="media-body">
                  <span class="name mb-0 text-sm">{{ tabledata }}</span>
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
          </template>
        </base-table>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  components: {},
  data() {
    return {
      tableDatas: [
        {
          name: "SPOTs관리자",
          position: "랜덤",
        },
        {
          name: "SPOTs테스터",
          position: "수비수",
        },
      ],
    };
  },
  methods: {},
  mounted() {},
};
</script>
<style>
div.vertical-line {
  width: 2px;
  background-color: black;
  height: 100%;
  float: left;
}
</style>
