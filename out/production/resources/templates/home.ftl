<html>
<head>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/vue@2.5.2/dist/vue.js"></script>
    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
    <title>포털개발팀 도서대여 - 로그인</title>
</head>
<body>

<div id = 'team-up-login-div'>
    <form id = "team-up-login-form" @submit.prevent="sendUserInformation">
            <label for="id">teamUp id :</label>
            <input type="text" name="id" v-model="userId" />

            <label for="password">password :</label>
            <input type="text" name="password" v-model="userPassword"/>
        <input type="submit" value="전송"/>
    </form>
</div>

</body>
<script type="text/javascript">
    var sendUser = new Vue({
        el: '#team-up-login-div',
        data: {
            userId: '',
            userPassword: ""
        },
        methods: {
            sendUserInformation : function() {
                axios({
                    method: 'post',
                    url: "http://127.0.0.1:8082/myproject/auth",
                    data: {
                        userId: sendUser.userId,
                        userPassword: sendUser.userPassword,
                    },
                }).then(function (response) {
                    if(response.data.access_token === undefined) {
                        if(response.data.error === 'id_pw_not_match') alert('아이디와 비밀번호가 올바르지 않습니다.');
                        else alert('비활성화된 계정입니다.');
                    }else {
                        alert("로그인에 성공하셨습니다.");
                        window.location.href = 'http://127.0.0.1:8082/myproject/index';
                    }
                });
            }
        }
    });
</script>
</html>