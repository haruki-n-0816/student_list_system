<template>
    <div style="text-align:center">
        <h1>研修生コミュニティ</h1>
        <form @submit.prevent="">
            <label>User ID</label>
            <input type="text" placeholder="customer id" v-model="user.id"/>
            <br>
            <label>Password</label>
            <input type="password" placeholder="password" v-model="user.password"/>
            <br>
            <b-button type="submit" pill variant="primary" size="lg">ログイン</b-button>
        </form>
    </div>
</template>
<script>
export default {
    data() {
        return {
            user: {}
        };
    },
    methods: {
        doLogin() {
            this.$store.dispatch("auth", {
                userId: this.user.userId,
                userToken: 'dummy token'
            });
            this.$router.push(this.$route.query.redirect);
        }
    }
};
</script>