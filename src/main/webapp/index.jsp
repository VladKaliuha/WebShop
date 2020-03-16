<!DOCTYPE html>
<html lang="en">
<head>
    <c:set var="title" value="Home"/>
    <%@ include file="/WEB-INF/jspf/head.jspf" %>
</head>

<header>
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
</header>

<body>
<img src="\image\electricCar.jpg" class="img-fluid" alt="Responsive image">
<br>
<div class="jumbotron">
    <h1 class="display-4">Welcome to MyShop</h1>
    <p class="lead">Information</p>
    <br><br><br><br>
    <p class="lead">
        <a class="btn btn-primary btn-lg" href="/shopping"
           role="button">Start shopping</a>
    </p>
</div>
</body>
<footer>
    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
</footer>
</html>
