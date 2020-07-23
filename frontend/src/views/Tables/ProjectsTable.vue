<template>
  <div class="card shadow" :class="type === 'dark' ? 'bg-default' : ''">
    <div
      class="card-header border-0"
      :class="type === 'dark' ? 'bg-transparent' : ''"
    >
      <div class="row align-items-center">
        <div class="col">
          <h3 class="mb-0" :class="type === 'dark' ? 'text-white' : ''">
            {{ title }}
          </h3>
        </div>
        <div class="col text-right">
          <base-button type="primary" size="sm">더보기</base-button>
        </div>
      </div>
    </div>

    <div class="table-responsive">
      <base-table
        class="table align-items-center table-flush"
        :class="type === 'dark' ? 'table-dark' : ''"
        :thead-classes="type === 'dark' ? 'thead-dark' : 'thead-light'"
        tbody-classes="list"
        :data="tableData"
      >
        <template slot="columns">
          <th>제목</th>
          <th>날짜</th>
          <th>주소</th>
          <th>유저 수</th>
          <th>대기 상태</th>
          <th></th>
        </template>

        <template slot-scope="{ row }">
          <th scope="row">
            <div class="media align-items-center">
              <a href="#" class="avatar rounded-circle mr-3">
                <img alt="Image placeholder" :src="row.img" />
              </a>
              <div class="media-body">
                <span class="name mb-0 text-sm">{{ row.title }}</span>
              </div>
            </div>
          </th>
          <td class="time">
            {{ row.time }}
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

    <div
      class="card-footer d-flex justify-content-end"
      :class="type === 'dark' ? 'bg-transparent' : ''"
    >
      <base-pagination total="30"></base-pagination>
    </div>
  </div>
</template>
<script>
export default {
  name: "projects-table",
  props: {
    type: {
      type: String,
    },
    title: String,
  },
  data() {
    return {
      tableData: [
        {
          img: "img/theme/bootstrap.jpg",
          title: "너만 오면 고",
          time: "2020/08/02",
          place: "서울시 성북구",
          numberofuser: "11/22",
          wait: "대기중",
        },
        {
          img: "img/theme/angular.jpg",
          title: "초보도 환영 ㅎㅎ",
          time: "2020/08/23",
          place: "서울시 용산구",
          numberofuser: "17/22",
          wait: "대기중",
        },
        {
          img: "img/theme/sketch.jpg",
          title: "구로구 메시 대기중",
          time: "2020/08/30",
          place: "서울시 구로구",
          numberofuser: "20/22",
          wait: "대기중",
        },
        {
          img: "img/theme/react.jpg",
          title: "여자분도 환영 놀러오세요",
          time: "2020/09/05",
          place: "서울시 종로구",
          numberofuser: "21/22",
          wait: "대기중",
        },
        {
          img: "img/theme/vue.jpg",
          title: "중고수 방",
          time: "2020/09/13",
          place: "서울시 강북구",
          numberofuser: "14/22",
          wait: "대기중",
        },
      ],
    };
  },
};
</script>
<style></style>
