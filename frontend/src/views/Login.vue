<template>
        <div class="row justify-content-center">
            <div class="col-lg-5 col-md-7">
                <div class="card bg-secondary shadow border-0">
                    <div class="card-body px-lg-5 py-lg-5">
                        <div class="text-center text-muted mb-4">
                            <h6>로그인 사항을 입력해주세요.</h6>
                        </div>
                        <form role="form">
                            <base-input class="input-group-alternative mb-3"
                                        placeholder="이메일"
                                        addon-left-icon="ni ni-email-83"
                                        v-model="model.email">
                            </base-input>

                            <base-input class="input-group-alternative"
                                        placeholder="비밀번호"
                                        type="password"
                                        addon-left-icon="ni ni-lock-circle-open"
                                        v-model="model.password"
                                        @keyup.enter="emitLoginSubmit">
                            </base-input>
                            <div class="text-center">
                                <base-button type="primary" class="my-4" @click="emitLoginSubmit" >로그인하기</base-button>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="row mt-3">
                    <div class="col-12 text-right">
                        <router-link to="/register" class="text-light"><h6>회원가입하기</h6></router-link>
                    </div>
                </div>
            </div>
        </div>
</template>
<script>
    export default {
        name: 'login',
        data() {
            return {
                model: {
                    email: '',
                    password: '',
                },
                error: {
                    email: false,
                    password: false
                },
                isSubmit: false,
            }
        },
        methods: {
            emitLoginSubmit() {
                console.log(this.model)
                this.$emit('login-submit', this.model)
            }, 
        },    
    }
</script>
<style>
</style>
