<template>
    <div style="text-align:center">
        <h1>研修生 新規登録</h1>
        <div style="text-align:center">

            <label for="userName">名前</label>
            <input type="text" id="userName" v-model="confirmName" />
            <div style="text-align:center">
                <font color="red">
                    <div>
                        <span v-if="!isValidEmail">{{ errorMessage }}</span>
                    </div>
                </font>
                <label for="mailAddress">メールアドレス：</label>
                <input type="email" id="mailAddress" v-model="confirmMailAddress">
            </div>
            <!-- <div style="text-align:center">
                    <label>プロフィール画像</label>
                    <input type="file" name="profileImage" accept="image/png">
                </div> -->
            <UserCreateConfirm :confirmName="userName" :confirmMailAddress="mailAddress"></UserCreateConfirm>

        </div>
        <div style="text-align:center">
            <b-button pill variant="secondary" size="lg" router-link to="/userCreateConfirm">確認</b-button>
            <b-button pill variant="secondary" size="lg" router-link to="/userIndex">キャンセル</b-button>
        </div>
    </div>
</template>
<script>
export default {
    data() {
        return {
            userName:'',
            mailAddress: '',
        }
    },
    computed: {
        isValidEmail() {
            const reg = /^[A-Za-z0-9]{1}[A-Za-z0-9_.-]*@{1}[A-Za-z0-9_.-]{1,}.[A-Za-z0-9]{1,}$/;
            return this.email && reg.test(this.email);
        },
        errorMessage() {
            if (!this.email) {
                return 'メールアドレスが未入力です。';
            } else {
                return 'メールアドレスの形式が不正です。';
            }
        },
    },
    methods: {
        submitForm() {
            // フォームの送信処理
        }
    }
}
</script>