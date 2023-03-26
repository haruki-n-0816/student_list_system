<template>
    <div class="text-center">
        <h1>新規登録 確認</h1>
        <form @submit.prevent="createComplete">
            下記の情報で新規登録をします。よろしいですか?
            <label>名前:</label>
            <p>{{ $route.params.userName }}</p>
            <label>メールアドレス:</label>
            <p>{{ $route.params.userMailAddress }}</p>
            <label>プロフィール画像</label>
            <img :src="$route.params.userImage" width="100" height="100" />
            <button type="submit" class="btn btn-primary rounded-pill btn-lg">登録</button>
            <b-button pill variant="secondary" size="lg" to="/userIndex">キャンセル</b-button>
        </form>
    </div>
</template>
<script>
export default {
};
</script> 