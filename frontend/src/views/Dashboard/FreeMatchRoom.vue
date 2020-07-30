<template>
  <div>
    <base-header type="gradient-success" class="pb-6 pt-5 pt-md-9">
      <div class="row d-flex flex-row justify-content-between">
        <div class="col-7">
          <card title="Room information" class="mb-4 mb-xl-0">
            <h2>방 제목</h2>
          </card>
        </div>

        <div class="row-cols">
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

    <div class="row d-flex flex-row" :class="type === 'dark' ? 'bg-default' : ''">
      <div class="table-responsive col m-2">
        <div class="text-center p-2 mb-2 bg-danger">
          <h2>Red Team</h2>
        </div>
        <base-table
          class="table align-items-center table-flush border-solid table-danger"
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
              <span class="name mb-0 text-sm text-default">{{ row.name }}</span>                
            </th>  
            <td>
              <base-dropdown>
                <base-button slot="title" class="dropdown-toggle">{{ row.position }}</base-button>
                <a class="dropdown-item" @click="PositionChange('랜덤')">랜덤</a>
                <a class="dropdown-item" @click="PositionChange('공격수')">공격수</a>
                <a class="dropdown-item" @click="PositionChange('수비수')">수비수</a>
                <a class="dropdown-item" @click="PositionChange('미드필더')">미드필더</a>
                <a class="dropdown-item" @click="PositionChange('골키퍼')">골키퍼</a>
              </base-dropdown>
            </td>
          </template>
        </base-table>
      </div>

      <div class="table-responsive col m-2">
        <div class="text-center p-2 mb-2 bg-primary">
          <h2 class="text-white">Blue Team</h2>
        </div>
        <base-table
          class="table align-items-center table-flush border-solid table-primary"
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
              <span class="name mb-0 text-sm text-default">{{ row.name }}</span>                
            </th>  
            <td>
              <base-dropdown>
                <base-button slot="title" class="dropdown-toggle">{{ row.position }}</base-button>
                <a class="dropdown-item" @click="PositionChange('랜덤')">랜덤</a>
                <a class="dropdown-item" @click="PositionChange('공격수')">공격수</a>
                <a class="dropdown-item" @click="PositionChange('수비수')">수비수</a>
                <a class="dropdown-item" @click="PositionChange('미드필더')">미드필더</a>
                <a class="dropdown-item" @click="PositionChange('골키퍼')">골키퍼</a>
              </base-dropdown>
            </td>
          </template>
        </base-table>
      </div>
    </div>

    <div class="d-flex flex-row justify-content-end mr-5">
      <card title="Room information" class="mb-4 mb-xl-0 bg-warning">
        <h2>입장하기</h2>
      </card>
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
