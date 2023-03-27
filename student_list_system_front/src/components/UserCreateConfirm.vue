<template>
    <div class="text-center">
        <h1>新規登録 確認</h1>
        <form @submit.prevent="createComplete">
            <p>下記の情報で新規登録をします。よろしいですか?</p>
            <label>名前:{{ $route.params.userName }}</label>
            <br>
            <label>メールアドレス:{{ $route.params.userMailAddress }}</label>
            <br>
            <label>プロフィール画像</label>
            <img :src="$route.params.userImage" width="100" height="100" />
            <br>
            <button type="submit" class="btn btn-primary rounded-pill btn-lg">登録</button>
            <b-button pill variant="secondary" size="lg" to="/userIndex">キャンセル</b-button>
        </form>
    </div>
</template>
<script>
// import axios from 'axios';

export default {
    data(){
        return{

        }
    }


};
</script> 