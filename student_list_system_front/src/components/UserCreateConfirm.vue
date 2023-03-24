<template>
    <div style="text-align: center;">
        <h1>新規登録 確認</h1>
        <form method="post" action="create_complete">
            <div style="margin-top: 20px; margin-bottom: 20px;">下記の情報で新規登録をします。よろしいですか?</div>
            <div>
                <label>名前:</label>
                <label>{{ confirmName }}</label>
            </div>
            <div>
                <label>メールアドレス:</label>
                <label>{{ confirmMailAddress }}</label>
                
            </div>
            <!-- <div>
                <label>プロフィール画像</label>
                <img th:src="'data:image/png;base64,' +${confirmProfileImage}" width="100" height="100">
                <input type="hidden" name="profileImage" th:value="${confirmProfileImage}" />
            </div> -->
            <input class="btn btn-primary rounded-pill btn-lg" type="submit" value="登録" />
            <b-button pill variant="secondary" size="lg" router-link to="/userIndex">キャンセル</b-button>
        </form>
    </div>
</template>
<script>
export default {
    props:{
        confirmName:{
            type: String,
            required: true
        },
        confirmMailAddress:{
            type: String,
            required: true
        }
    },
    data() {
        return {
            inputValue: this.inputValue
        }
    },
    watch: {
        inputValue(newValue) {
            this.$emit('input-value-changed', newValue)
        }
    }
}
</script>