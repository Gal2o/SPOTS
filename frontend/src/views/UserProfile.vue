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
            <h1 class="display-2 text-white">{{ model.username }}의 프로필</h1>
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
                    <img :src="imgurl" class="rounded-circle" />
                  </a>
                </div>
              </div>
            </div>
            <div class="card-header text-center border-0 pt-8 pt-md-4 pb-0"></div>
            <div class="card-body pt-0 pt-md-4">
              <div class="row">
                <div class="col">
                  <div class="card-profile-stats d-flex justify-content-center mt-md-5 pb-0">
                    <div>
                      <span class="heading">{{ model.win }}</span>
                      <span class="description">승</span>
                    </div>
                    <div>
                      <span class="heading">{{ model.draw }}</span>
                      <span class="description">무</span>
                    </div>
                     <div>
                      <span class="heading">{{ model.lose }}</span>
                      <span class="description">패</span>
                    </div>
                  </div>
                </div>
              </div>
              <div class="row">
                <div class="col">
                  <div class="card-profile-stats d-flex justify-content-center pt-0 mt-0">
                    <div>
                      <span class="heading">{{ model.goal }}</span>
                      <span class="description">득점</span>
                    </div>
                     <div>
                      <span class="heading">{{ model.assist }}</span>
                      <span class="description">도움</span>
                    </div>
                  </div>
                </div>
              </div>
              <div class="text-center">
                <h3>이름 : {{ name }}</h3>
                <div>
                  <i class="ni education_hat mr-2"></i>
                  이메일 : {{ email }}
                </div>
              </div>
            </div>
          </div>
        </div>

        <div class="col-xl-8 order-xl-1">
          <card shadow type="secondary">
            <div slot="header" class="bg-white border-0">
              <div class="row align-items-center">
                <router-link to="/profile">
                  <h3 class="mb-0 ml-3">내 정보 </h3>
                  </router-link>
                  <p class="mx-2 mb-0">/</p>
                  <router-link to="/Teamprofile">
                  <h3 class="mb-0">팀 프로필</h3>
                  </router-link>
              </div>
            </div>
            <template>
              <form >
                <div class="pl-lg-4">
                  <div class="row">
                    <div class="col-lg-6">
                      <base-input
                        alternative
                        label="이름"
                        placeholder="이름을 입력해주세요"
                        input-classes="form-control-alternative"
                        v-model="model.username"
                       :disabled="validated ? disabled : ''"
                      />
                    </div>
                  </div>
                </div>
                <div class="pl-lg-4">
                  <!-- <base-input 
                    alternative
                    label="주소">
                  <div class="row">
                    
                    <base-dropdown class="mx-3">
                    <base-button slot="title" type="secondary" class="dropdown-toggle">
                      시(도) : {{
                      this.cityN
                      }}
                    </base-button>
                    <a
                      class="dropdown-item"
                      v-for="cityData in cityDatas"
                      v-bind:key="cityData"
                      @click="choice1(cityData)"
                    >{{ cityData.state_name }}</a>
                  </base-dropdown>

                  <base-dropdown class="mx-4">
                    <base-button slot="title" type="secondary" class="dropdown-toggle">
                      구(시) : {{
                      this.stateN
                      }}
                    </base-button>
                    <a
                      class="dropdown-item"
                      v-for="stateData in stateDatas"
                      v-bind:key="stateData"
                      @click="choice2(stateData)"
                    >{{ stateData.city_name }}</a>
                  </base-dropdown>    
                  </div>
                 </base-input> -->
                  <div class="row">
                    <div class="col-lg-4">
                      <base-input
                        alternative
                        label="비밀번호"
                        placeholder="비밀번호"
                        type="password"
                        input-classes="form-control-alternative"
                        v-model="model.password"
                      />
                    </div>
                    <div class="col-lg-4">
                      <base-input
                        alternative
                        label="비밀번호 확인"
                        type="password"
                        placeholder="비밀번호 확인"
                        input-classes="form-control-alternative"
                        v-model="model.passwordcheck"
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
                        placeholder="간략히 자기를 소개해 주세요"
                        v-model="model.comment"
                      >
                      </textarea>
                    </base-input>
                  </div>
                </div>
                <div class="text-center">
                <router-link to="/profile">
                <base-button class="btn" @click="submitinfo">내정보 수정</base-button>
                </router-link>
                <modal :show.sync="this.al" gradient="danger" class="text-center">
                   <div class="py-3 text-center mb-0">
                     <h3 class="text-white mb-3">실패! 비밀번호가 다릅니다. 다시 적어주세요!</h3>
                     <base-button size="sm" type="secondary" @click="al = false">닫기</base-button>
                   </div>
                </modal>
                 <modal :show.sync="this.al2" gradient="danger" class="text-center">
                   <div class="py-3 text-center mb-0">
                     <h3 class="text-white mb-3">실패! 비밀번호가 비워져있습니다. 넣어주세요!</h3>
                     <base-button size="sm" type="secondary" @click="al2 = false">닫기</base-button>
                   </div>
                </modal>
                </div>
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
        goal: "",
        assist: "",
        win : "",
        draw: "",
        lose : "",
        comment: "",
        password: "",
        passwordcheck: "",
      },
      name : "",
      email: "",
      imgurl: "",
      validated:false,
       cityDatas: null,
      stateDatas: null,
      cityN: null,
      stateN: null,
       citycode: null,
      statecode: null,
      al:false,
      al2:false,
      binarray:[],
    };
  },
  created() {
    const data = new FormData();
    data.append("uid", this.$cookies.get("UserInfo").uid);
    this.$axios
      .post(this.$SERVER_URL + "user/detail2", data)
      .then((res) => {
        this.model.username = res.data.nickname
        this.model.email = res.data.email
        this.email = res.data.email
        var logonum = ((this.$cookies.get("UserInfo").uid)%24)+1
        this.imgurl = 'img/userLogo/'+ logonum +'.png'
        this.model.win = res.data.win
        this.model.draw = res.data.draw
        this.model.lose = res.data.lose
        this.model.goal = res.data.goal
        this.model.assist = res.data.assist
        this.model.comment = res.data.comment
        this.name = res.data.nickname
      })

  },
  methods : {
    choice1(state) {
      this.cityN = state.state_name;
      this.citycode = state.state_code;
      this.choicestate(state.state_code);
    },
    choice2(city) {
      this.stateN = city.city_name;
      this.statecode = city.city_code;
    },
    choicestate(b) {
      const stateForm = new FormData();
      b = String(b);
      stateForm.append("state_code", b);
      this.$axios
        .post(this.$SERVER_URL + "cityList", stateForm)
        .then((res) => {
          this.stateDatas = res.data;
        })
    },
    submitinfo() {
      if (this.model.password == this.model.passwordcheck && this.model.password!=""){
        const infoForm = new FormData();
        infoForm.append("password", this.model.password)
        infoForm.append("nickname",this.model.username)
        infoForm.append("uid",this.$cookies.get("UserInfo").uid)
        infoForm.append('comment', this.model.comment)
        
        this.$axios
          .post(this.$SERVER_URL + "user/modify", infoForm)
          .then(res=>{
            this.binarray = res.data
            this.$router.push({ name: "SPOTs"})
          })
      }else if (this.model.password != this.model.passwordcheck) {
        this.al = true;
      }
      else if(this.model.password ==""){
          this.al2 = true;
      }
      
      
      
    },
  }
};
</script>
<style></style>
