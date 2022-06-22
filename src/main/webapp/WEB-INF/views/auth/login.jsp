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
    <title>login</title>
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

    <form class="form-signin"  action="/login" method="post">
        <h2 class="form-signin-heading">Please login</h2>
        <label for="account" class="sr-only">Account</label>
        <input type="text" id="account" name="account" class="form-control" placeholder="Account" required autofocus>
        <label for="password" class="sr-only">Password</label>
        <input type="password"  id="password"  name="password"  class="form-control" placeholder="Password" required>
        <button class="btn btn-lg btn-primary btn-block" type="submit">log in</button>
    </form>

</div>

<script>

</script>
</body>
</html>

