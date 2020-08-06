<template>
    <div class="d-flex justify-content-center">
        <div class="col">
            <h1 class="text-center">결재가 완료 되었습니다.</h1>
        </div>
        <div class="col">
            <base-button @click="returnPage">확인</base-button>
        </div>
    </div>
</template>

<script>
import axios from "axios";

const SERVER_URL = "http://localhost:8080/";

export default {
    methods: {
        returnPage() {
            this.$router.push({ name: '자유 SPOT', params: { uid: this.$route.params.room_uid }})
        }
    },
    created() {
        console.log(this)
        const EnterInfo = new FormData();
        EnterInfo.append("uid", this.$route.params.uid);
        EnterInfo.append("positionnum", this.$route.params.positionnum);
        EnterInfo.append("team_entry_uid", this.$route.params.team_entry_uid);
        axios.post(SERVER_URL + "FreeMatchRoom/entry/", EnterInfo)
            .then((res) => {
                console.log(res);
            })
            .catch((err) => {
                console.log(err);
            });
    },
};
</script>