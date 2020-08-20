<template>
  <footer class="footer px-4">
    <div class="row align-items-center justify-content-lg-between">
      <div class="col-lg-6">
        <div class="copyright text-center text-lg-left text-muted">
          © {{year}} <a href="https://lab.ssafy.com/s03-webmobile2-sub3/s03p13a408" class="font-weight-bold ml-1" target="_blank">제작 : 일검사회</a>
        </div>
      </div>
      <div class="col-lg-6">
        <ul class="nav nav-footer justify-content-center justify-content-lg-end">
          <li class="nav-item">
            <router-link to="/">
              <a href="#" class="nav-link" target="_blank">SPOTs</a>
            </router-link>
          </li>
          <li class="nav-item">
            <a href="https://lab.ssafy.com/s03-webmobile2-sub3/s03p13a408" class="nav-link" target="_blank">일검사회</a>
          </li>
          <li class="nav-item">
            <a href="https://www.creative-tim.com/license" class="nav-link" target="_blank">템플릿 라이센스</a>
          </li>
        </ul>
      </div>
    </div>
  </footer>
</template>
<script>
export default {
  data() {
    return {
      year: new Date().getFullYear()
    };
  }
};
</script>
<style></style>
