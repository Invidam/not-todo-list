<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%--    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.css">--%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/signin.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/navbar-fixed-top.css">
    <title>profile</title>
</head>
<body>
<header>
    <!-- Fixed navbar -->
    <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">Fixed navbar</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarCollapse">
                <ul class="navbar-nav me-auto mb-2 mb-md-0">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="#">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Link</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Disabled</a>
                    </li>
                </ul>
                <div class="col-md-3 text-end">
                    <button type="button" onclick="location='login.jsp'" class="btn btn-outline-primary me-2">Login</button>
                    <button type="button" onclick="location='signup.jsp'" class="btn btn-primary">Sign-up</button>
                </div>
            </div>
        </div>
    </nav>
</header>

<div class="container">

    <form class="form-signin"  action="/user" method="post">
        <input type="hidden" name="_method" value="PUT"/>
        <h2 class="form-signin-heading">Info</h2>
        <label for="account" class="sr-only">Account</label>
        <input type="text" id="account" name="account" class="form-control" placeholder="Account" required value="${requestScope.user.account}" autofocus>
        <label for="password" class="sr-only">Password</label>
        <input type="password"  id="password"  name="password"  class="form-control" placeholder="Password" value="${requestScope.user.password}"  required>
        <label for="nickname" class="sr-only">Nickname</label>
        <input type="text" id="nickname"  name="nickname" class="form-control" placeholder="Nickname" value="${requestScope.user.nickname}"  required>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Save</button>
    </form>

</div> <!-- /container -->

<script>

</script>
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<%--<script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>--%>
<%--<script src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>--%>
</body>
</html>


<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<!DOCTYPE html>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <meta http-equiv="content-type" content="application/json; charset=UTF-8"/>--%>
<%--    <meta name="viewpoint" content="width=device-width" initial-scale="1">--%>
<%--    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.css">--%>
<%--    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">--%>
<%--    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/signin.css">--%>
<%--    <title>signup</title>--%>
<%--</head>--%>
<%--<body>--%>
<%--signup--%>

<%--<div class="container">--%>
<%--    &lt;%&ndash;    action="/user" method="post"&ndash;%&gt;--%>
<%--    <form class="form-signin">--%>
<%--        <h2 class="form-signin-heading">Please sign up</h2>--%>
<%--        <label for="account" class="sr-only">Account</label>--%>
<%--        <input type="text" id="account" class="form-control" placeholder="Account" required autofocus>--%>
<%--        <label for="password" class="sr-only">Password</label>--%>
<%--        <input type="password" id="password" class="form-control" placeholder="Password" required>--%>
<%--        <label for="nickname" class="sr-only">Nickname</label>--%>
<%--        <input type="text" id="nickname" class="form-control" placeholder="Nickname" required>--%>
<%--        <button id="submit" class="btn btn-lg btn-primary btn-block">Sign in</button>--%>
<%--    </form>--%>

<%--</div> <!-- /container -->--%>

<%--<script>--%>
<%--    function signup() {--%>
<%--        const account = document.querySelector("#account").value;--%>
<%--        const password = document.querySelector("#password").value;--%>
<%--        const nickname = document.querySelector("#nickname").value;--%>
<%--        console.log("SIGNUP START");--%>
<%--        console.log(account,password,nickname);--%>

<%--        fetch("/signup", {--%>
<%--            method: "POST",--%>
<%--            redirect: "follow",--%>
<%--            headers: {--%>
<%--                "Content-Type": "application/json",--%>
<%--            },--%>
<%--            body: JSON.stringify({--%>
<%--                account: account,--%>
<%--                password: password,--%>
<%--                nickname: nickname--%>
<%--            })--%>
<%--        }).then(res => {--%>
<%--            console.log(res);--%>
<%--            if(res.status === 200 || res.status === 302) {--%>
<%--                alert("회원가입 성공");--%>
<%--                window.location.href = res.url;--%>
<%--            }--%>
<%--            else alert("회원가입 실패");--%>
<%--        }).catch(err => alert(err))--%>
<%--    }--%>
<%--    function signup2() {--%>
<%--        const xmlHttpRequest = new XMLHttpRequest();--%>
<%--        const account = document.querySelector("#account").value;--%>
<%--        const password = document.querySelector("#password").value;--%>
<%--        const nickname = document.querySelector("#nickname").value;--%>
<%--        const data = JSON.stringify({--%>
<%--            account: account,--%>
<%--            password: password,--%>
<%--            nickname: nickname--%>
<%--        });--%>
<%--        xmlHttpRequest.open("POST","/signup",true);--%>
<%--        xmlHttpRequest.setRequestHeader("Content-Type","application/json");--%>
<%--        xmlHttpRequest.send(data);--%>

<%--    }--%>
<%--    document.querySelector("#submit").addEventListener("click",(event) => {--%>
<%--        event.preventDefault();--%>
<%--        signup();--%>
<%--    });--%>
<%--</script>--%>
<%--<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->--%>
<%--&lt;%&ndash;<script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>&ndash;%&gt;--%>
<%--<script src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>--%>
<%--</body>--%>
<%--</html>--%>
