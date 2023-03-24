<template>
    <div style="text-align:center">
        <h1>研修生 新規登録</h1>
        <form>
            <label>名前</label>
            <input type="text" v-model="userName" />
            <font color="red">
                <div>
                    <span v-if="!isValidEmail">{{ errorMessage }}</span>
                </div>
            </font>
            <label>メールアドレス：</label>
            <input type="email" v-model="userMailAddress">
            <div style="text-align:center">
                <label>プロフィール画像</label>
                <input type="file" name="profileImage" accept="image/png" @change="onImageChange">
            </div>
        </form>
        <div style="text-align:center">
            <b-button pill variant="secondary" size="lg" @click="submitForm" :disabled="isDisabled">確認</b-button>
            <b-button pill variant="secondary" size="lg" router-link to="/userIndex">キャンセル</b-button>
        </div>
    </div>
</template>
<script>
import axios from 'axios'
export default {
    data() {
        return {
            userName: '',
            userMailAddress: '',
            selectedFile: null,
        }
    },
    computed: {
        isValidEmail() {
            const reg = /^[A-Za-z0-9]{1}[A-Za-z0-9_.-]*@{1}[A-Za-z0-9_.-]{1,}.[A-Za-z0-9]{1,}$/;
            return this.userMailAddress && reg.test(this.userMailAddress);
        },
        errorMessage() {
            if (!this.userMailAddress) {
                return 'メールアドレスが未入力です。';
            } else {
                return 'メールアドレスの形式が不正です。';
            }
        },
        isDisabled() {
            return !this.isValidEmail || !this.selectedFile;
        },
    },
    methods: {
        onImageChange(event) {
            this.selectedFile = event.target.files[0];
        },
        async submitForm() {
            const formData = new FormData();
            formData.append('file', this.selectedFile);
            formData.append('name', this.userName);
            formData.append('email', this.userMailAddress);
            try {
                const response = await axios.post('create_complete', formData);
                this.$router.push({ path: '/userCreateConfirm', query: response.data });
            } catch (error) {
                console.error(error);
                alert('エラーが発生しました。');
            }
        },
    },
};
</script>