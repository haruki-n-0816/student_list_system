<template>
    <div class="text-center">
        <h1>新規登録 確認</h1>
        <form @submit.prevent="createComplete">
            <div class="my-4">
                下記の情報で新規登録をします。よろしいですか?
            </div>
            <div class="mb-3">
                <label>名前:</label>
                <span>{{ confirmData.name }}</span>
            </div>
            <div class="mb-3">
                <label>メールアドレス:</label>
                <span>{{ confirmData.email }}</span>
            </div>
            <div v-if="confirmData.profileImage" class="mb-3">
                <label>プロフィール画像</label>
                <img :src="confirmData.profileImage" width="100" height="100" />
            </div>
            <button type="submit" class="btn btn-primary rounded-pill btn-lg">
                登録
            </button>
            <b-button pill variant="secondary" size="lg" to="/userIndex">キャンセル</b-button>
        </form>
    </div>
</template>
<script>
import axios from "axios";

export default {
    data() {
        return {
            confirmData: {
                name: "",
                email: "",
                profileImage: "",
            },
        };
    },
    created() {
        // queryパラメーターからデータを取得する
        const { confirmName, confirmMailAddress } = this.$route.query;
        this.confirmData.name = confirmName;
        this.confirmData.email = confirmMailAddress;

        // 画像のデータを取得する
        axios
            .get("/api/getImage")
            .then((response) => {
                this.confirmData.profileImage = response.data;
            })
            .catch((error) => {
                console.error(error);
            });
    },
    methods: {
        createComplete() {
            // 登録処理を実行する
            // axios.post("/api/createUser", this.confirmData).then((response) => {
            //     // 完了画面に遷移する
            //     this.$router.push("/complete");
            // });
        },
    },
};
</script> 