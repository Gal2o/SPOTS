<template>
    <div class="d-flex justify-content-center">
        <div>
            <h1 class="text-center">결재가 완료 되었습니다.</h1>
        </div>
        <div>
            <button @click="Entry">확인</button>
        </div>
    </div>
</template>

<script>
import axios from "axios";

const SERVER_URL = "http://localhost:8080/";

export default {
    methods: {
        Entry() {
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
    },
};
</script>