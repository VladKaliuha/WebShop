<!DOCTYPE html>
<html lang="en">

<head>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
        <c:set var="title" value="Sign In" />
        <%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
            <%@ taglib tagdir="/WEB-INF/tags" prefix="mytag" %>
</head>

<header>
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
</header>

<body>
    <br><br><br><br><br><br><br><br><br><br>
    <section id="cover">
        <div id="cover-caption">
            <div id="container" class="container">
                <div class="row">
                    <div class="col-sm-10 offset-sm-1 text-center">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h2 class="panel-title">Please sign up</h2><br>
                            </div>
                            <div class="panel-body">
                                <form role="form" method="post" action="/sign-in">
                                    <div class="form-group">
                                        <input data-rule="email" type="email" name="email" id="email" value="${email}" class="form-control input-sm" placeholder="Email*">
                                        <div class="alertBox" id="emailAlertBox" style="display: none">
                                            <div class="alert alert-danger">
                                                <strong>!</strong> Can contains a-zA-Z0-9 and symbols. Should contains @
                                            </div>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <input data-rule="password" type="password" name="password" id="password" class="form-control input-sm" placeholder="Password*">
                                        <div class="alertBox" id="passwordAlertBox" style="display: none">
                                            <div class="alert alert-danger">
                                                <strong>!</strong> Can contains a-zA-Z0-9. Min 6ch
                                            </div>
                                        </div>
                                    </div>
                                    <c:forEach var="message" items="${requestScope.errors}">
                                        <tr>
                                            <tr><span style="color: red;">
                                                <c:out value="${message}" /></span><br></tr>
                                        </tr>
                                    </c:forEach>
                                    <br>
                                    <input type="submit" onclick="validForm()" value="Sign In" class="btn btn-info btn-block bg-dark">
                                    <br>New customer? <a href="/sign-up">Registration</a>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <footer>
        <%@ include file="/WEB-INF/jspf/footer.jspf" %>
    </footer>
</body>

</html>