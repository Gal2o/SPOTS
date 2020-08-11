<template>
    <div class="row justify-content-center">
        <div class="col-lg-5 col-md-7">
            <div class="card bg-secondary shadow border-0">
                <div class="card-header bg-transparent pb-5">
                    <div class="text-muted text-center mt-2 mb-3">
                        <small>소셜 회원가입</small>
                    </div>
                    <div class="btn-wrapper text-center">
                        <a href="#" class="btn btn-neutral btn-icon">
                            <span class="btn-inner--icon"><img src="img/icons/common/kakao.svg"></span>
                            <span class="btn-inner--text">카카오</span>
                        </a>
                        <a href="#" class="btn btn-neutral btn-icon">
                            <span class="btn-inner--icon"><img src="img/icons/common/google.svg"></span>
                            <span class="btn-inner--text">구글</span>
                        </a>
                    </div>
                </div>
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

                        <base-input class="input-group-alternative mb-3"
                                    placeholder="생년월일6자리 ex)850101"
                                    addon-left-icon="ni ni-calendar-grid-58"
                                    v-model="model.birthday">
                        </base-input>

                        <base-input class="input-group-alternative"
                                    placeholder="비밀번호"
                                    type="password"
                                    addon-left-icon="ni ni-lock-circle-open"
                                    v-model="model.password">
                        </base-input>
                        <base-input class="input-group-alternative"
                                    placeholder="비밀번호 확인"
                                    type="password"
                                    addon-left-icon="ni ni-lock-circle-open"
                                    v-model="model.passwordcheck">
                        </base-input>

                        <div class="row my-4">
                            <div class="col-12">
                                <base-checkbox class="custom-control-alternative">
                                    <span class="text-muted"><a href="#!">약관</a>에 동의합니다!</span>
                                </base-checkbox>
                            </div>
                        </div>
                        <div class="text-center">
                            <base-button type="primary" class="my-4" @click="Signup()">계정 생성</base-button>
                        </div>
                    </form>
                </div>
            </div>
            <div class="row mt-3">
                <div class="col-6">
                    <a href="#" class="text-light">
                        <small>비밀번호를 잊으셨나요?</small>
                    </a>
                </div>
                <div class="col-6 text-right">
                    <router-link to="/login" class="text-light">
                        <small>로그인하러 가기</small>
                    </router-link>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
import axios from 'axios'

    export default {
        name: 'register',
        data() {
            return {
                model: {
                    name: '',
                    email: '',
                    birthday: '',
                    password: '',
                    passwordcheck: '',
                }
            }
        },
        methods: {
            Signup() {
                const SignData = new FormData();
                SignData.append('nickname', this.model.name);
                SignData.append('email', this.model.email);
                SignData.append('password', this.model.password);
                SignData.append('birthday', this.model.birthday);
                axios.post('http://localhost:8080/user/signUp/', SignData)
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
            },
        },
    }
</script>
<style>
</style>
