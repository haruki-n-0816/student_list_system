<template>
    <div>
        <div id="overflow"></div>
        <div class="mx-auto h1" style="text-align:center; margin-top:20px;margin-bottom:20px;">
            研修生一覧
        </div>
        <div style="text-align:right">
            <b-button pill variant="primary" size="lg" router-link to="/userCreate">+</b-button>
        </div>
        <table class="table" style="margin-top:20px; margin-bottom:20px;">
            <thead>
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">名前</th>
                    <th scope="col">メールアドレス</th>
                    <th scope="col">画像</th>
                    <th scope="col">削除</th>
                    <th scope="col">編集</th>
                </tr>
            </thead>
            <tr v-for="user in paginatedUsers" :key="user.id">
                <td>{{ user.id }}</td>
                <td>{{ user.name }}</td>
                <td>{{ user.mailAddress }}</td>
                <td><img :src="user.profileImagePath" alt="プロフィール画像" width="50" height="50"></td>
                <td>
                    <img src="/icon/trashbox.png" alt="削除" style="cursor: pointer;"
                        v-on:click="deleteCheck({ id: user.id, name: user.name, mailAddress: user.mailAddress })">
                </td>
                <td><img src="/icon/pencil.png" alt="編集" style="cursor: pointer;" v-on:click="update({ id: user.id, name: user.name, mailAddress: user.mailAddress})"></td>
            </tr>
        </table>
        <div class="d-flex justify-content-center">
            <b-pagination v-model="currentPage" :total-rows="users.length" :per-page="pageSize" :limit="9" aria-controls="my-table" />
        </div>
    </div>
</template>
<script>
import axios from 'axios';
import $ from 'jquery';

export default {
    data() {
        return {
            users: [],
            currentPage: 1,
            pageSize: 10,
        }
    },
    mounted() {
        axios.get('http://localhost:8081/users')
            .then(response => {
                this.users = response.data;
            })
            .catch(error => {
                console.log(error);
            });
    },
    methods: {
        deleteCheck(user) {
            const overflowTargetElements = document.getElementById("overflow");
            const overflow = `
                <div id="conf" style="text-align:center; z-index: 10;">
                    <p>ID:<block id="userId"></block></p>
                    <p>名前:<block id="userName"></block></p>
                    <p>メールアドレス:<block id="userMailAddress"></block></p>
                    <p>こちらのデータを削除します。本当によろしいですか?</p>
                    <div class="btns" style="text-align:center">
                        <button type="button" id="userDelete" class="btn btn-danger rounded-pill btn-lg" v-on:click="userDelete(user)">削除</button>
                        <button type="button" id="cancel" class="btn btn-secondary rounded-pill btn-lg" v-on:click="cancel()">キャンセル</button>
                    </div>
                </div>`;

            overflowTargetElements.insertAdjacentHTML('afterbegin', overflow);
            overflowTargetElements.style.zIndex = "10";

            const overflowCss = document.querySelectorAll("#overflow");
            overflowCss.forEach(el => {
                Object.assign(el.style, {
                    width: "100vw",
                    height: "100vh",
                    backgroundColor: "rgba(0, 0, 0, 0.2)",
                    top: "0%",
                    left: "0%",
                    position: "absolute",
                    display: "none",
                });
            });

            const confCss = document.getElementById("conf");
            Object.assign(confCss.style, {
                zIndex: "10",
                background: "#FFF",
                padding: "20px",
                position: "absolute",
                top: "50%",
                left: "50%",
                transform: "translate(-50%, -50%)",
            });

            const userId = document.getElementById("userId");
            const userName = document.getElementById("userName");
            const userMailAddress = document.getElementById("userMailAddress");

            userId.textContent = user.id;
            userName.textContent = user.name;
            userMailAddress.textContent = user.mailAddress;

            const deleteButton = document.querySelector("#userDelete");
            deleteButton.addEventListener("click", this.userDelete);

            const cancelButton = document.querySelector("#cancel");
            cancelButton.addEventListener("click", this.cancel);

            $("#overflow").show();
        },
        async userDelete() {
            const userId = document.getElementById("userId");
            const id = userId.textContent;

            try {
                const response = await axios.get('http://localhost:8081/delete_complete', {
                    params: {
                        id: id
                    }
                });
                console.log(response.data);

                const index = this.users.findIndex(user => Number(user.id) === Number(id));
                if (index !== -1) {
                    this.users.splice(index, 1);
                }
                $("#conf").remove();
                $("#overflow").hide();
            } catch (error) {
                console.error(error);
                alert("削除エラーが発生しました。データベースを確認してください");
                $("#conf").remove();
                $("#overflow").hide();
            }
        },
        cancel() {
            $("#conf").remove();
            $("#overflow").hide();
        },
        update(user){
            this.$router.push({
                    name: 'userUpdate',
                    params: {
                        userId: user.id,
                        userName: user.name,
                        userMailAddress: user.mailAddress,
                    },
                });
        }
        
    },
    computed: {
        paginatedUsers() {
            const startIndex = (this.currentPage - 1) * this.pageSize;
            const endIndex = startIndex + this.pageSize;
            return this.users.slice(startIndex, endIndex);
        },
        totalPages() {
            return Math.ceil(this.users.length / this.pageSize);
        }
    }
}
</script>