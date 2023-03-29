<template>
    <div style="text-align:center">
        <h1>研修生 編集 確認</h1>
        <form @submit.prevent="createComplete">
            <p>下記の情報に編集します。よろしいですか?</p>
            <label>ID {{ userIdConfirm }}</label>
            <br>
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
            userIdConfirm: '',
            userNameConfirm: '',
            userMailAddressConfirm: '',
            userImageConfirm: null
        }
    },
    mounted() {
        this.userIdConfirm = this.$route.params.userId,
        this.userNameConfirm = this.$route.params.userName;
        this.userMailAddressConfirm = this.$route.params.userMailAddress;
        this.userImageConfirm = this.$route.params.userImage;
    },
    methods: {
        async createComplete() {
            const formData = new FormData();
            formData.append("userIdConfirm", this.userIdConfirm);
            formData.append("userNameConfirm", this.userNameConfirm);
            formData.append("userMailAddressConfirm", this.userMailAddressConfirm);
            if (this.userImageConfirm) {
                const blob = await fetch(this.userImageConfirm).then(r => r.blob());
                formData.append("userImageConfirm", blob, "newfilename.jpg");
            }

            try {
                const response = await axios.post('http://localhost:8081/update_complete', formData,
                    {
                        headers: {
                            "Content-Type": "multipart/form-data",
                        },
                    }
                );
                console.log(response.data);
            } catch (error) {
                console.error(error);
                alert("編集エラーが発生しました。データベースを確認してください");
            }
            this.$router.push({
                    name: 'userIndex'
                })
        }
    }
};
</script> 