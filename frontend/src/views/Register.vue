<template>
    <div class="row justify-content-center">
        <div class="col-lg-5 col-md-7">
            <div class="card bg-secondary shadow border-0">                
                <div class="card-body px-lg-5 py-lg-5">
                    <div class="text-center text-muted mb-4">
                        <small>회원가입을 위해 정보를 입력</small>
                    </div>
                    <form role="form">

                        <base-input class="input-group-alternative mb-3"
                                    placeholder="이름"
                                    addon-left-icon="ni ni-hat-3"
                                    v-model="model.name">
                        </base-input>

                        <base-input class="input-group-alternative mb-3"
                                    placeholder="이메일"
                                    addon-left-icon="ni ni-email-83"
                                    v-model="model.email">
                        </base-input>
                        <base-button type="primary" class="my-4" @click="sendEmail()">인증번호 발송</base-button>

                        <base-input class="input-group-alternative mb-3"
                                    placeholder="인증번호"
                                    addon-left-icon="ni ni-email-83"
                                    v-model="checkMailNum">
                        </base-input>
                        <base-button type="primary" class="my-4" @click="checkNum()">인증번호 확인</base-button>

                        <base-input class="input-group-alternative mb-3"
                                    placeholder="비밀번호"
                                    type="password"
                                    addon-left-icon="ni ni-lock-circle-open"
                                    v-model="model.password">
                        </base-input>
                        <base-input class="input-group-alternative mb-3"
                                    placeholder="비밀번호 확인"
                                    type="password"
                                    addon-left-icon="ni ni-lock-circle-open"
                                    v-model="model.passwordcheck">
                        </base-input>
                        <div class="text-center">
                            <base-button type="primary" class="my-4" @click="Signup()">계정 생성</base-button>
                        </div>
                    </form>
                </div>
            </div>
            <div class="row mt-3">
                <div class="col-12 text-right">
                    <router-link to="/login" class="text-light">
                        <small>로그인하러 가기</small>
                    </router-link>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
export default {
    name: 'register',
    data() {
        return {
            model: {
                name: '',
                email: '',
                password: '',
                passwordcheck: '',
            },
            mailNum: '',
            checkMailNum:'',
        }
    },
    methods: {
        Signup() {
            const SignData = new FormData();
            SignData.append('nickname', this.model.name);
            SignData.append('email', this.model.email);
            SignData.append('password', this.model.password);
            if (this.model.password == this.model.passwordcheck){
            this.$axios.post(this.$SERVER_URL+'user/signUp/', SignData)
                .then(res => {
                    if (res.data == "") {
                        alert('빈칸없이 적어주세요.')
                    }
                    else {
                        this.$router.push({ name: 'login'})
                    }                        
                })
                .catch(err => {
                    alert('잘못 입력하셨습니다. 입력 확인해주세요');
                    console.log(err);
                })
            }
            else {
                alert('비밀번호가 동일하지 않습니다. 다시 적어주세요')
                this.model.password = "",
                this.model.passwordcheck = ""
            }
        },
        sendEmail(){
            const data = new FormData();
            data.append('email', this.model.email);
            this.$axios.post(this.$SERVER_URL+'user/email/', data)
                .then(res => {
                   this.mailNum = res.data
                })
                .catch(err => {
                    alert('잘못 입력하셨습니다. 입력 확인해주세요');
                    console.log(err);
                })
        },
        checkNum(){
            if(this.mailNum == this.checkMailNum){
                alert("인증되었습니다");
            }
        },
    },
}
</script>
<style>
</style>
