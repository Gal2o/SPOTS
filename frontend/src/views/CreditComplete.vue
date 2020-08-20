<template>
    <div class="row justify-content-center">
        <div class="col-lg-5 col-md-7">
            <div class="card bg-secondary shadow border-0">
                <div class="card-body px-lg-5 py-lg-5">
                    <div class="text-center text-muted mb-4">
                        <h1 class="text-center">결재가 완료 되었습니다.</h1>
                    </div>
                    <div class="text-center text-muted mb-4">
                        <h3 class="text-center">확인을 누르시면 결제 이전 페이지로 이동합니다.</h3>
                    </div>
                    <div class="text-center text-muted mb-4">
                        <base-button @click="returnPage">확인</base-button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    methods: {
        returnPage() {
            var bepage = this.$route.params.room_uid
            if (bepage == 0) {
                this.$router.push({ name: 'SPOTs' })
            } else {
                if (this.$route.params.team_entry_uid != 0){
                    this.$router.push({ name: '자유 SPOT', params: { uid: this.$route.params.room_uid }})
                }
                else {
                    this.$router.push({ name: '팀 SPOT', params: { uid: this.$route.params.room_uid }})
                }
            }            
        }
    },
    created() {
        if (this.$route.params.team_entry_uid != 0){
        
        const EnterInfo = new FormData();
        EnterInfo.append("uid", this.$route.params.uid);
        EnterInfo.append("positionnum", this.$route.params.positionnum);
        EnterInfo.append("team_entry_uid", this.$route.params.team_entry_uid);
        this.$axios.post(this.$SERVER_URL + "FreeMatchRoom/entry/", EnterInfo)
        
        } else {
            if (this.$route.params.positionnum == -1) {
                const joinInfo = new FormData();
                joinInfo.append("uid", this.$route.params.uid);
                joinInfo.append("room_uid", this.$route.params.room_uid);
                this.$axios.post(this.$SERVER_URL + "TRoomJoin/", joinInfo)
                   
            }
        }
    },
};
</script>