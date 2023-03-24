<template>
    <div>
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
        </table>
    </div>
</template>
<script>

</script>