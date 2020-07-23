<template>
  <div>
    <base-header type="gradient-success" class="pb-6 pb-8 pt-5 pt-md-8">
      <!-- Card stats -->
      <div class="row">
        <base-dropdown class="mr-3">
          <base-button slot="title" type="secondary" class="dropdown-toggle">
            시
          </base-button>
          <a class="dropdown-item" href="#">서울</a>
          <a class="dropdown-item" href="#">경기</a>
          <a class="dropdown-item" href="#">부산</a>
        </base-dropdown>

        <base-dropdown class="mr-3">
          <base-button slot="title" type="secondary" class="dropdown-toggle">
            구
          </base-button>
          <a class="dropdown-item" href="#">성북</a>
          <a class="dropdown-item" href="#">강북</a>
          <a class="dropdown-item" href="#">용산</a>
        </base-dropdown>

        <form
          class="navbar-search navbar-search-dark form-inline mr-3 d-none d-md-flex"
        >
          <div class="form-group mb-0">
            <base-input
              placeholder="검색"
              class="input-group-alternative"
              alternative=""
              addon-right-icon="fas fa-search"
            >
            </base-input>
          </div>
        </form>
      </div>
    </base-header>
    <div class="container-fluid mt--7">
      <div class="row">
        <div class="col">
          <projects-table title="자유 SPOT"></projects-table>
        </div>
      </div>
      <div class="row mt-5">
        <div class="col">
          <projects-table type="dark" title="팀 SPOT"></projects-table>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import ProjectsTable from "./Tables/ProjectsTable";
export default {
  components: {
    ProjectsTable,
  },
  data() {
    return {};
  },
  methods: {},
  mounted() {},
};
</script>
<style></style>
