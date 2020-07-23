<template>
  <div>
    <base-header type="gradient-success" class="pb-6 pb-8 pt-5 pt-md-8">
      <div class="row">
        <div class="col-9">
            <card title="Room information" class="mb-4 mb-xl-0">
                <h2>방 제목</h2>      
            </card>
        </div>
        <div class="col-3">
            <card title="Back icon" class="mb-4 mb-xl-0">
                <div class="row">
                    <div class="col"><i class="ni ni-bold-left ni-2x"></i></div>
                    <div class="col"><h2>나가기</h2></div>
                </div>
            </card>
        </div>
      </div>              
    </base-header>

    <div class="container-fluid mt--7">
        <div class="row">
            <div class="col-lg-6">
                <base-table class="table table-dark">
                    <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">First</th>
                            <th scope="col">Last</th>
                            <th scope="col">Handle</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <th scope="row">1</th>
                            <td>Mark</td>
                            <td>Otto</td>
                            <td>@mdo</td>
                        </tr>
                        <tr>
                            <th scope="row">2</th>
                            <td>Jacob</td>
                            <td>Thornton</td>
                            <td>@fat</td>
                        </tr>
                        <tr>
                            <th scope="row">3</th>
                            <td>Larry</td>
                            <td>the Bird</td>
                            <td>@twitter</td>
                        </tr>
                    </tbody>
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
    return {};
  },
  methods: {},
  mounted() {},
};
</script>
<style></style>
