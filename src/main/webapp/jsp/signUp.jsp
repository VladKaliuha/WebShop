<!DOCTYPE html>
<html lang="en">

<head>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
        <c:set var="title" value="Sign Up" />
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
                                <form role="form" enctype="multipart/form-data" method="post" action="/sign-up">
                                    <div class="row">
                                        <div class="col-xs-6 col-sm-6 col-md-6">
                                            <div class="form-group">
                                                <input data-rule="name" type="text" name="first_name" id="first_name" value="${first_name}" class="form-control input-sm" placeholder="Name*">
                                                <div class="alertBox" id="first_nameAlertBox" style="display: none">
                                                    <div class="alert alert-danger">
                                                        <strong>!</strong> Should contains only letters
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="col-xs-6 col-sm-6 col-md-6">
                                            <div class="form-group">
                                                <input data-rule="name" type="text" name="last_name" id="last_name" value="${last_name}" class="form-control input-sm" placeholder="Last Name*">
                                                <div class="alertBox" id="last_nameAlertBox" style="display: none">
                                                    <div class="alert alert-danger">
                                                        <strong>!</strong> Should contains only letters
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <input data-rule="email" type="email" name="email" id="email" value="${email}" class="form-control input-sm" placeholder="Email*">
                                        <div class="alertBox" id="emailAlertBox" style="display: none">
                                            <div class="alert alert-danger">
                                                <strong>!</strong> Can contains a-zA-Z0-9 and symbols. Should contains @
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row" data-rule="password">
                                        <div class="col-xs-6 col-sm-6 col-md-6">
                                            <div class="form-group">
                                                <input data-rule="password" type="password" name="password" id="password" class="form-control input-sm" placeholder="Password*">
                                                <div class="alertBox" id="passwordAlertBox" style="display: none">
                                                    <div class="alert alert-danger">
                                                        <strong>!</strong> Can contains a-zA-Z0-9. Min 6ch
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-xs-6 col-sm-6 col-md-6">
                                            <div class="form-group">
                                                <input data-rule="password" type="password" name="cpassword" id="cpassword" class="form-control input-sm" placeholder="Confirm Password*">

                                                <div class="alertBox" id="cpasswordAlertBox" style="display: none">
                                                    <div class="alert alert-danger">
                                                        <strong>!</strong> Can contains a-zA-Z0-9. Min 6ch
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row" data-rule="password">
                                        <div class="col-xs-6 col-sm-6 col-md-6">
                                            <div class="form-group">
                                                <mytag:captcha />
                                            </div>
                                        </div>
                                        <div class="col-xs-6 col-sm-6 col-md-6">
                                            <div class="custom-control custom-radio">
                                                <div class="input-group-prepend">
                                                    <div class="input-group-text">
                                                        <input type="checkbox" id="mailing">
                                                        <span>Do you want to email?</span>
                                                    </div>
                                                </div>
                                                <br> Choose an icon: <input type="file" name="icon" accept=".jpg, .jpeg, .png">
                                            </div>
                                        </div>
                                    </div>

                                    <c:forEach var="message" items="${requestScope.errors}">
                                        <tr>
                                            <tr><span style="color: red;">
                                                <c:out value="${message}" /></span><br></tr>
                                        </tr>
                                    </c:forEach>

                                    <input type="submit" onclick="validForm()" value="Sign Up" class="btn btn-info btn-block bg-dark">

                                    <div class="alertBox" id="differentPasswords" style="display: none">
                                        <div class="alert alert-danger">
                                            <strong>!</strong> Different passwords
                                        </div>
                                    </div>
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