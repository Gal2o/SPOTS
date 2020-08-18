<template>
    <div class="row justify-content-center">
        <div class="col-lg-5 col-md-7">
            <div class="card bg-secondary shadow border-0">                
                <div class="card-body px-lg-5 py-lg-5">
                    <div class="text-center text-muted mb-4">
                        <small>회원가입을 위해 정보를 입력해주세요.</small>
                    </div>
                    <form role="form">

                        <label class="ml-3 text-warning text-sm" v-if="!valid.name">이름은 3글자 이상이어야합니다.</label>
                        <base-input class="input-group mb-3"
                                    placeholder="이름"
                                    alternative
                                    addon-left-icon="ni ni-hat-3"
                                    :valid="valid.name"
                                    v-model="model.name">
                        </base-input>

                        <label class="ml-3 text-warning text-sm" v-if="!valid.email">이메일 형식과 다릅니다.</label>
                        <base-input class="input-group mb-3"
                                    alternative
                                    placeholder="이메일"
                                    addon-left-icon="ni ni-email-83"
                                    :valid="valid.email"
                                    :disabled="isCertify"
                                    v-model="model.email">
                        </base-input>
                        <div class="text-center" v-if="!isSend">
                            <base-button type="primary" class="my-4" @click="sendEmail()" :disabled="!valid.email">인증번호 발송</base-button>
                        </div>

                        <base-input class="input-group mb-3"
                                    placeholder="인증번호"
                                    addon-left-icon="ni ni-email-83"
                                    v-if="isSend && !isCertify"
                                    v-model="checkMailNum">
                        </base-input>
                        <div class="text-center" v-if="isSend && !isCertify">
                            <base-button type="primary" class="my-4" @click="checkNum()">인증번호 확인</base-button>
                        </div>

                        <label class="ml-3 text-warning text-sm" v-if="!valid.password">비밀번호는 8글자 이상이어야 합니다.</label>
                        <base-input class="input-group mb-3"
                                    placeholder="비밀번호"
                                    alternative
                                    type="password"
                                    addon-left-icon="ni ni-lock-circle-open"
                                    :valid="valid.password"
                                    v-model="model.password">
                        </base-input>
                        <label class="ml-3 text-warning text-sm" v-if="valid.password && !valid.passwordcheck">패스워드와 일치하지 않습니다.</label>
                        <base-input class="input-group mb-3"
                                    placeholder="비밀번호 확인"
                                    alternative
                                    type="password"
                                    addon-left-icon="ni ni-lock-circle-open"
                                    :valid="valid.passwordcheck"
                                    v-if="valid.password"
                                    v-model="model.passwordcheck">
                        </base-input>
                        <div class="text-center">
                            <base-button :disabled="!isCertify && !isPossible" type="primary" class="my-4" @click="Signup()">계정 생성</base-button>
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

        <modal :show.sync="modals.certify">
            <h2 class="text-center">인증에 성공하셨습니다.</h2>
            <template slot="footer">
                <div class="col-12 d-flex flex-row justify-content-center">
                    <base-button class="text-center" type="primary" @click="modals.certify=false">닫기</base-button>
                </div>
            </template>
        </modal>

        <modal :show.sync="modals.notcertify"
            gradient="danger"
            modal-classes="modal-danger modal-dialog-centered">
            <div class="py-3 text-center">
                <h2 class="text-white mt-4">인증에 실패하셨습니다!</h2>
                <h4 class="text-white mt-4">다시 이메일로 인증 번호를 받아 인증을 진행해주세요.</h4>
            </div>

            <template slot="footer">
                <div class="col-12 d-flex flex-row justify-content-center">
                    <base-button type="white" class="text-center" @click="modals.notcertify = false">다시 인증하기</base-button>
                </div>
            </template>
        </modal>

        <modal :show.sync="modals.senderror"
            gradient="danger"
            modal-classes="modal-danger modal-dialog-centered">
            <div class="py-3 text-center">
                <h2 class="text-white mt-4">이메일이 잘 못 입력되었습니다!</h2>
                <h4 class="text-white mt-4">이메일을 다시 입력해주세요.</h4>
            </div>

            <template slot="footer">
                <div class="col-12 d-flex flex-row justify-content-center">
                    <base-button type="white" @click="modals.senderror = false">다시하기</base-button>
                </div>
            </template>
        </modal>
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
            isCertify: false,
            isSend: false,
            isPossible: false,
            modals: {
                certify: false,
                notcertify: false,
                senderror: false,
            },
            valid: {
                name: false,
                email: false,
                password: false,
                passwordcheck: false,
            },
        }
    },
    watch: {
        model: {
            deep: true,
            handler() {                
                console.log('watch')
                this.validCheck(this.model) 
            }
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
        sendEmail() {
            var Senddata = new FormData();
            Senddata.append('email', String(this.model.email));
            console.log('what', this.model.email)
            this.$axios.post(this.$SERVER_URL+'user/email/', Senddata)
                .then(res => {
                   this.mailNum = res.data
                   this.isSend = true
                })
                .catch(err => {
                    this.modals.senderror = true
                    console.log(err);
                })
        },
        checkNum(){
            if(this.mailNum == this.checkMailNum){
                this.modals.certify = true
                this.isCertify = true
            } else {
                this.modals.notcertify = true
                this.isSend = false
            }
        },
        validCheck(model) {
            if (model.name.length >= 3) {
                this.valid.name = true
            } else {
                this.valid.name = false
            }
            if (model.email.indexOf("@") != -1 && model.email.lastIndexOf(".com") != -1) {
                this.valid.email = true
            } else {
                this.valid.email = false
            }
            if (model.password.length > 7) {
                this.valid.password = true
            } else {
                this.valid.password = false
            }
            if (model.passwordcheck == model.password) {
                this.valid.passwordcheck = true
            } else {
                this.valid.passwordcheck = false
            }
            if (this.valid.name && this.valid.email && this.valid.password && this.valid.passwordcheck) {
                this.isSignup = true
            } else {
                this.isPossible = false
            }
        },
    },
}
</script>
<style>
</style>
