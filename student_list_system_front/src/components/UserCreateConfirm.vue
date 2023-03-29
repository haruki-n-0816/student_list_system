<template>
    <div style="text-align:center">
        <h1>新規登録 確認</h1>
        <form @submit.prevent="createComplete">
            <p>下記の情報で新規登録をします。よろしいですか?</p>
            <label>名前:{{ userNameConfirm }}</label>
            <br>
            <label>メールアドレス:{{ userMailAddressConfirm }}</label>
            <br>
            <label>プロフィール画像</label>
            <img :src="userImageConfirm" width="100" height="100" />
            <br>
            <b-button type="submit" pill variant="primary" size="lg">登録</b-button>
            <b-button pill variant="secondary" size="lg" router-link to="/userIndex">キャンセル</b-button>
        </form>
    </div>
</template>
<script>
import axios from 'axios';

export default {
    data() {
        return {
            userNameConfirm: '',
            userMailAddressConfirm: '',
            userImageConfirm: null
        }
    },
    mounted() {
        this.userNameConfirm = this.$route.params.userName;
        this.userMailAddressConfirm = this.$route.params.userMailAddress;
        this.userImageConfirm = this.$route.params.userImage;
    },
    methods: {
        async createComplete() {
            const formData = new FormData();
            formData.append("userNameConfirm", this.userNameConfirm);
            formData.append("userMailAddressConfirm", this.userMailAddressConfirm);
            if (this.userImageConfirm) {
                const blob = await fetch(this.userImageConfirm).then(r => r.blob());
                formData.append("userImageConfirm", blob, "newfilename.jpg");
            }

            try {
                const response = await axios.post('http://localhost:8081/create_complete', formData,
                    {
                        headers: {
                            "Content-Type": "multipart/form-data",
                        },
                    }
                );
                console.log(response.data);
            } catch (error) {
                console.error(error);
                alert("登録エラーが発生しました。データベースを確認してください");
            }
            this.$router.push({
                    name: 'userIndex'
                })
        }
    }
};
</script> 