
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
  <meta name="description" content="">
  <meta name="author" content="">
  <title>Fixed Top Navbar Example for Bootstrap</title>

  <!-- Bootstrap core CSS -->
  <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href="${pageContext.request.contextPath}/resources/css/navbar-fixed-top.css" rel="stylesheet">

  <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->

  <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
</head>

<body>

<!-- Fixed navbar -->
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
        <% if(session.getAttribute("UserAccount") != null) {%>
          <span class="nav-item text-white col-md-4">
              Hello, <% String nickname = (String) session.getAttribute("UserAccount"); %> <%=nickname %>!
          </span>
          <div class="col-md-3 text-end">
            <button type="button" onclick="location='/logout'" class="btn btn-light btn-outline-primary me-2">Logout</button>
            <button type="button" onclick="location='/user/profile'" class="btn btn-primary">Profile</button>
          </div>
        <%} else {%>
          <span class="nav-item text-white col-md-4">
              Hello, Unknown!
          </span>
          <div class="col-md-3 text-end">
            <button type="button" onclick="location='/login'" class="btn btn-light btn-outline-primary me-2">Login</button>
            <button type="button" onclick="location='/signup'" class="btn btn-primary">Sign-up</button>
          </div>
        <%} %>
      </div>
    </div>
  </nav>
</header>

<div class="container">
  <h1>CONTENT</h1>

</div> <!-- /container -->

</body>
</html>
