<template>
  <div>
    <base-header type="gradient-success" class="pb-6 pb-8 pt-5 pt-md-8">
      <div class="row">
        <div class="col-9">
            <card title="Room information" class="mb-4 mb-xl-0">
                <h2>방 제목</h2>      
            </card>
        </div>
        <div class="col-1"></div>
        <div class="col-2">
            <card title="Back icon" class="mb-4 mb-xl-0">
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

    <div class="container-fluid mt--7">
        <div class="row">
            <div class="col-lg-6">
                <base-table
        class="table align-items-center table-flush"
        :class="type === 'dark' ? 'table-dark' : ''"
        :thead-classes="type === 'dark' ? 'thead-dark' : 'thead-light'"
        tbody-classes="list"
        :data="tableData"
      >
        <template slot="columns">
          <th>유저 네임</th>
          <th>포지션</th>
        </template>

        <template slot-scope="{ row }">
          <th scope="row">
            <div class="name">
                <span class="name mb-0 text-sm">{{ row.name }}</span>
            </div>
          </th>
          <td class="position">
            {{ row.position }}
          </td>
          <td>
            <span class="status">{{ row.place }}</span>
          </td>
          <td>
            {{ row.numberofuser }}
          </td>

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
        </div>
    </div>
  </div>
</template>
<script>
export default {
  components: {
  },
  data() {
    return {
        tableData: [
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
<style></style>
