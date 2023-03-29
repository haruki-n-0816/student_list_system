<template>
    <div style="text-align:center">
        <h1>研修生 新規登録</h1>
        <form @submit.prevent="createComfirm">
            <label>名前</label>
            <input type="text" v-model="userName" />
            <br>
            <label>メールアドレス</label>
            <input type="email" v-model="userMailAddress">
            <br>
            <label>プロフィール画像</label>
            <input type="file" accept="image/*" @change="uplodeImage" required>
            <br>
            <b-button type="submit" pill variant="secondary" size="lg">確認</b-button>
            <b-button pill variant="secondary" size="lg" router-link to="/userIndex">キャンセル</b-button>
        </form>
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
        createComfirm() {
            const reader = new FileReader();
            reader.readAsDataURL(this.userImage);
            reader.onload = () => {
                this.$router.push({
                    name: 'userCreateConfirm',
                    params: {
                        userName: this.userName,
                        userMailAddress: this.userMailAddress,
                        userImage: reader.result,
                    },
                });
            };
        },
    },
};
</script>