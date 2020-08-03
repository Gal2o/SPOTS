<template>
  <div>
    <base-header
      class="header pb-4 pt-5 pt-lg-8 d-flex align-items-center"
      style="min-height: 600px; background-size: cover; background-position: center top;"
    >
      <!-- Mask -->
      <span class="mask bg-gradient-success opacity-8"></span>
      <!-- Header container -->
      <div class="container-fluid d-flex align-items-center">
        <div class="row">
          <div class="col-lg-12 col-md-10">
            <h1 class="display-2 text-white">{{ username }}의 프로필</h1>
            <p class="text-white mt-0 mb-5">
              유저님의 프로필 페이지입니다.
              <br />본인의 정보 확인 및 수정이 가능합니다.
            </p>
          </div>
        </div>
      </div>
    </base-header>

    <div class="container-fluid mt--7">
      <div class="row">
        <div class="col-xl-4 order-xl-2 mb-5 mb-xl-0">
          <div class="card card-profile shadow">
            <div class="row justify-content-center">
              <div class="col-lg-3 order-lg-2">
                <div class="card-profile-image">
                  <a href="#">
                    <img src="img/theme/team-4-800x800.jpg" class="rounded-circle" />
                  </a>
                </div>
              </div>
            </div>
            <div class="card-header text-center border-0 pt-8 pt-md-4 pb-0 pb-md-4"></div>
            <div class="card-body pt-0 pt-md-4">
              <div class="row">
                <div class="col">
                  <div class="card-profile-stats d-flex justify-content-center mt-md-5">
                    <div>
                      <span class="heading">22</span>
                      <span class="description">친구</span>
                    </div>
                    <div>
                      <span class="heading">10</span>
                      <span class="description">자유 SPOT</span>
                    </div>
                    <div>
                      <span class="heading">89</span>
                      <span class="description">팀 SPOT</span>
                    </div>
                  </div>
                </div>
              </div>
              <div class="text-center">
                <h3>이름 : {{ model.username }}</h3>
                <div>
                  <i class="ni education_hat mr-2"></i>
                  이메일 : {{ model.email }}
                </div>
              </div>
            </div>
          </div>
        </div>

        <div class="col-xl-8 order-xl-1">
          <card shadow type="secondary">
            <div slot="header" class="bg-white border-0">
              <div class="row align-items-center">
                <div class="col-8">
                  <h3 class="mb-0">내 정보</h3>
                </div>
              </div>
            </div>
            <template>
              <form @submit.prevent>
                <div class="pl-lg-4">
                  <div class="row">
                    <div class="col-lg-6">
                      <base-input
                        alternative
                        label="이름"
                        placeholder="이름을 입력해주세요"
                        input-classes="form-control-alternative"
                        v-model="model.username"
                      />
                    </div>
                    <div class="col-lg-6">
                      <base-input
                        alternative
                        label="이메일"
                        placeholder="이메일을 입력해주세요"
                        input-classes="form-control-alternative"
                        v-model="model.email"
                      />
                    </div>
                  </div>
                </div>
                <div class="pl-lg-4">
                  <div class="row">
                    <div class="col-md-12">
                      <base-input
                        alternative
                        label="상세 주소"
                        placeholder="상세 주소를 입력해주세요"
                        input-classes="form-control-alternative"
                        v-model="model.address"
                        readonly
                      />
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-lg-4">
                      <base-input
                        alternative
                        label="시"
                        placeholder="시"
                        input-classes="form-control-alternative"
                        v-model="model.city"
                      />
                    </div>
                    <div class="col-lg-4">
                      <base-input
                        alternative
                        label="구"
                        placeholder="구"
                        input-classes="form-control-alternative"
                        v-model="model.country"
                      />
                    </div>
                  </div>
                </div>
                <div class="pl-lg-4">
                  <div class="form-group">
                    <base-input alternative label="자기 소개">
                      <textarea
                        rows="4"
                        class="form-control form-control-alternative"
                        placeholder="A few words about you ..."
                      >
간략히 자기를 소개해 주세요
                      </textarea>
                    </base-input>
                  </div>
                </div>
                <a href="#!" class="btn btn-info center">내정보 수정</a>
              </form>
            </template>
          </card>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  name: "user-profile",
  data() {
    return {
      model: {
        username: "",
        email: "",
        firstName: "",
        lastName: "",
        address: "",
        city: "",
        country: "",
        zipCode: "",
        about: "",
      },
    };
  },
  created() {
    const userInfo = this.$cookies.get("UserInfo")
    this.model.username = userInfo.nickname
    this.model.email = userInfo.email
  }
};
</script>
<style></style>
