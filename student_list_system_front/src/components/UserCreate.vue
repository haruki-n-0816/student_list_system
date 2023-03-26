<template>
    <div style="text-align:center">
        <h1>研修生 新規登録</h1>
        <form @submit.prevent="handleSubmit">
            <label>名前</label>
            <input type="text" v-model="userName"/>
            <label>メールアドレス</label>
            <input type="email" v-model="userMailAddress">
            <label>プロフィール画像</label>
            <input type="file" accept="image/png" @change="uplodeImage" required>
        </form>
        <b-button type="submit" pill variant="secondary" size="lg">確認</b-button>
        <b-button pill variant="secondary" size="lg" router-link to="/userIndex">キャンセル</b-button>
    </div>
</template>
<script>
export default {
    data() {
        return {
            userName: '',
            userMailAddress: '',
            userImage: null,
        }
    },
    methods: {
        uplodeImage(event) {
            this.userImage = event.target.files[0];
        },
        handleSubmit() {
            this.$router.push({
                name: "userCreateConfirm",
                params: {
                    userName: this.userName,
                    userMailAddress: this.userMailAddress,
                    userImage: this.userImage,
                },
            });
        },
    },
};
</script>