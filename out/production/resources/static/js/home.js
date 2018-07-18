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
                    alert(response.data.access_token);
                    alert(response.data.refresh_token);
                    alert("로그인에 성공하셨습니다.");
                    window.location.href = 'http://127.0.0.1:8082/myproject/index';
                }
            });
        }
    }
});