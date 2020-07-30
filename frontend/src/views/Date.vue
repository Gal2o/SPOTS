<template>
  <div>
    <base-header type="gradient-success" class="pb-6 pb-4 pt-5 pt-md-8">
      <div class="container-fluid d-flex align-items-center">
        <div class="row">
          <div class="col-lg-12 col-md-10">
            <h1 class="display-2 text-white">일정 관리</h1>
            <p class="text-white mt-0 mb-5">
              유저님의 일정관리 페이지입니다.
              <br />본인의 최근 대전 기록과 향후 일정을 확인할 수 있습니다.
            </p>
          </div>
        </div>
      </div>
    </base-header>
    <div class="container mt-4 align-items-center">
      <div class="card">
        <div class="border-0 card-header">
          <h3 class="mb-0">대전 기록</h3>
        </div>

        <el-table
          class="table-responsive table-flush"
          header-row-class-name="thead-light"
          :data="projects"
        >
          <el-table-column label="제목" min-width="310px" prop="name" sortable>
            <template v-slot="{row}">
              <div class="media align-items-center">
                <a href="#" class="avatar rounded-circle mr-3">
                  <img alt="Image placeholder" :src="row.img" />
                </a>
                <div class="media-body">
                  <span class="font-weight-600 name mb-0 text-sm">{{row.title}}</span>
                </div>
              </div>
            </template>
          </el-table-column>
          <el-table-column label="Budget" prop="budget" min-width="140px" sortable></el-table-column>

          <el-table-column label="Status" min-width="170px" prop="status" sortable>
            <template v-slot="{row}">
              <badge class="badge-dot mr-4" type>
                <i :class="`bg-${row.statusType}`"></i>
                <span class="status">{{row.status}}</span>
              </badge>
            </template>
          </el-table-column>

          <el-table-column label="Users" min-width="190px"></el-table-column>

          <el-table-column label="Completion" prop="completion" min-width="260px" sortable>
            <template v-slot="{row}">
              <div class="align-items-center">
                <span class="completion mr-2">{{row.completion}}%</span>
              </div>
            </template>
          </el-table-column>
          <el-table-column min-width="180px"></el-table-column>
        </el-table>

        <div class="card-footer py-4 d-flex justify-content-end">
          <base-pagination v-model="currentPage" :total="50"></base-pagination>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { Table, TableColumn } from "element-ui";
export default {
  components: {
    [Table.name]: Table,
    [TableColumn.name]: TableColumn,
  },
  data() {
    return {
      projects: [
        {
          img:
            "https://demos.creative-tim.com/vue-argon-dashboard-pro/img/theme/bootstrap.jpg",
          title: "Argon Design System",
          budget: "$2500 USD",
          status: "pending",
          statusType: "warning",
          completion: 30,
        },
        {
          img:
            "https://demos.creative-tim.com/vue-argon-dashboard-pro/img/theme/angular.jpg",
          title: "Angular Now UI Kit PRO",
          budget: "$1800 USD",
          status: "completed",
          statusType: "success",
          completion: 100,
        },
        {
          img:
            "https://demos.creative-tim.com/vue-argon-dashboard-pro/img/theme/sketch.jpg",
          title: "Black Dashboard",
          budget: "$3150 USD",
          status: "delayed",
          statusType: "danger",
          completion: 72,
        },
        {
          img:
            "https://demos.creative-tim.com/vue-argon-dashboard-pro/img/theme/react.jpg",
          title: "React Material Dashboard",
          budget: "$4400 USD",
          status: "on schedule",
          statusType: "info",
          completion: 90,
        },
        {
          img:
            "https://demos.creative-tim.com/vue-argon-dashboard-pro/img/theme/vue.jpg",
          title: "Vue Paper UI Kit PRO",
          budget: "$2200 USD",
          status: "completed",
          statusType: "success",
          completion: 100,
        },
      ],
      currentPage: 1,
    };
  },
};
</script>