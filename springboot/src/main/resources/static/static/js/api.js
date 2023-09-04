function askVerifyCode() {
    get('http://localhost/api/auth/verify-code', {
        email: $("#email").val()
    }, function (data) {
        alert(data.reason)
    })
}

function register() {
    post('http://localhost/api/auth/register', {
        verify: $("#verify").val(),
        username: $("#username").val(),
        password: $("#password").val(),
        email: $("#email").val(),
    }, function (data) {
        if (data.code === 200) {
            window.location = "http://localhost/login.html"
        } else {
            alert(data.reason)
        }
    })
}

function login() {
    post('http://localhost/api/auth/login', {
        username: $("#username").val(),
        password: $("#password").val()
    }, function (data) {
        if (data.code === 200) {
            window.location = "http://localhost/index.html"
        } else {
            alert(data.reason)
        }
    })
}

function initUserInfo() {
    get('http://localhost/api/user/info', function (data) {
        if (data.code === 200) {
            alert("登录成功,欢迎" + data.data.username + "进入图书管理系统！")
        } else {
            alert(data.reason)
            window.location = "http://localhost/login.html"
        }
    })
}

function logout() {
    get('http://localhost/api/auth/logout', function (data) {
        if (data.code === 200) {
            window.location = "http://localhost/login.html"
        }
    })
}

function get(url, success){
    $.ajax({
        type: "get",
        url: url,
        async: true,
        dataType: 'json',
        xhrFields: {
            withCredentials: true
        },
        success: success
    });
}

function post(url, data, success){
    $.ajax({
        type: "post",
        url: url,
        async: true,
        data: data,
        dataType: 'json',
        xhrFields: {
            withCredentials: true
        },
        success: success
    });
}