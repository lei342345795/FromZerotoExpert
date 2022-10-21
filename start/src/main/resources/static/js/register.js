function submitForm() {
    var username = document.getElementById("username");
    var password = document.getElementById("password");
    if (username.value == null || password.value == null || username.value.length === 0 ||
        password.value.length === 0) {
        alert("用户名或密码不能为空")
        return;
    } else if (username.value.length > 8) {
        alert("用户名长度不应超过8位")
        return;
    } else if (password.value.length < 8 || password.value.length > 12) {
        alert("密码长度应在8-14位")
        return;
    }
    axios({
        method: "post",
        url: "http://localhost:8080/FromZerotoExpert/Register",
        data:
            {
                userName: username.value,
                userPwd: password.value
            }
    }).then(function (res) {
        if (res.data.code == 0) {
            alert("注册成功");
            username.value = null;
            password.value = null;
        } else {
            alert("注册失败，原因：" + res.data.message)
        }
    })
}