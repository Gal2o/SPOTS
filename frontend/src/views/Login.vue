<template>
        <div class="row justify-content-center">
            <div class="col-lg-5 col-md-7">
                <div class="card bg-secondary shadow border-0">
                    <div class="card-body px-lg-5 py-lg-5">
                        <div class="text-center text-muted mb-4">
                            <h6>로그인 사항을 입력해주세요.</h6>
                        </div>
                        <form role="form">
                            <label class="ml-3 text-warning text-sm" v-if="!valid.email">이메일 형식과 다릅니다.</label>
                            <base-input class="input-group mb-3"
                                        alternative
                                        placeholder="이메일"
                                        addon-left-icon="ni ni-email-83"
                                        :valid="valid.email"
                                        v-model="model.email"
                                        @keydown.enter="emitLoginSubmit">
                            </base-input>

                            <label class="ml-3 text-warning text-sm" v-if="!valid.password">비밀번호는 8글자 이상이어야 합니다.</label>
                            <base-input class="input-group"
                                        placeholder="비밀번호"
                                        alternative
                                        type="password"
                                        addon-left-icon="ni ni-lock-circle-open"
                                        :valid="valid.password"
                                        v-model="model.password"
                                        @keydown.enter="emitLoginSubmit">
                            </base-input>
                            <div class="text-center">
                                <base-button :disabled="!isPossible" type="primary" class="my-4" @click="emitLoginSubmit" >로그인하기</base-button>
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
                isPossible: false,
                valid: {
                    email: false,
                    password: false,
                }
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
            emitLoginSubmit() {
                if(this.isPossible === true) {
                    this.$emit('login-submit', this.model)
                }
            }, 
            validCheck(model) {
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
                if (this.valid.email && this.valid.password) {
                    this.isPossible = true
                } else {
                    this.isPossible = false
                }
            },
        },    
    }
</script>
<style>
</style>
