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
