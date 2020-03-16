<!DOCTYPE html>
<html lang="en">

<head>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
        <%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt"%>
            <c:set var="title" value="Cart" />
            <%@ include file="/WEB-INF/jspf/head.jspf"%>

                <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
                <script src="http://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
                <link rel="stylesheet" type="text/css" href="/style/sidebar.css" />
                <link rel="stylesheet" type="text/css" href="/style/shopping.css" />
</head>

<header>
    <%@ include file="/WEB-INF/jspf/header.jspf"%>
</header>

<body>
    <br><br><br><br>
    <div class="row">
        <div class="col"></div>
        <div class="col-8">
            <table class="table">
                <c:choose>
                    <c:when test="${empty cart.productsFromCart}">
                        <br><br><br><br>
                        <h1 class="font-weight-bold text-center">Cart is empty!</h1>
                    </c:when>

                    <c:when test="${not empty cart.productsFromCart}">
                        <thead class="thead-light">
                            <tr>
                                <th scope="col">#</th>
                                <th scope="col">Product</th>
                                <th scope="col">Count</th>
                                <th scope="col">Price</th>
                                <th scope="col"><input class="btn btn-danger" type="button" onclick="clearCart()" value="Clear cart"></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="item" items="${cart.productsFromCart}" varStatus="loop">
                                <tr>
                                    <th scope="row">${loop.index+1}</th>
                                    <td class="w-50">${item.product.name}</td>
                                    <td class="w-25">
                                        <input class="btn btn-primary" onclick="changeCount(${item.product.id}, 'm')" type="button" value="-">
                                        <input type="number" class="button w-25" value="${item.count}" min="1" type="number">
                                        <input class="btn btn-primary" onclick="changeCount(${item.product.id}, 'p')" type="button" value="+">
                                    </td>
                                    <td>${item.totalPrice}</td>
                                    <td><input class="btn btn-danger" type="button" onclick="changeCount(${item.product.id}, 'd')" value="x"></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </c:when>
                </c:choose>
            </table>
            <form method="POST" action="/cart">
                <div class="orderData" align="center">
                    <c:choose>

                        <c:when test="${empty user}">
                            <a class="btn btn-dark" href="/sign-in" role="button">Sign in</a>
                        </c:when>

                        <c:when test="${not empty user and not empty requestScope.cartItems}">
                            <br><br>
                            <h6 class="text-left">Payment method: </h6>
                            <select id="payment" class="form-control">
                            <option>Card payment</option>
                            <option>Cash payment</option>
                          </select>
                            <br>
                            <h6 class="text-left">Delivery method: </h6>
                            <select id="delivery" class="form-control">
                              <option>Nova poshta</option>
                              <option>Ukrposhta</option>
                            </select>
                            <br>
                            <h6 class="text-left">Your payment card: </h6>
                            <input name="card" onkeyup="validCard()" id="card" class="form-control input-sm" placeholder="xxxx-xxxx-xxxx-xxxx">
                            <div class="alertBox" id="card_err" style="display: none">
                                <div class="alert alert-danger">
                                    <strong>!</strong> Card numbmer should consist only number(16pi)
                                </div>
                            </div>
                            <br>
                            <input id="createOrder" type="submit" onclick="validCard()" value="Create order" class="btn btn-info btn-block bg-dark">
                        </c:when>

                    </c:choose>
                </div>
            </form>
        </div>
        <div class="col"></div>
    </div>
    <br><br><br>
    <footer>
        <%@ include file="/WEB-INF/jspf/footer.jspf"%>
    </footer>
</body>

<script>
    function changeCount(id, act) {
        $.ajax({
            type: "PUT",
            url: "/cart?product_id="+id+"&action="+act,
            data: {
                'product_id': id,
                'action': act
            },
            success: function() {
                $(".table").load(" .table");
                $(".orderData").load(" .orderData");
            }
        });
    }

    function clearCart() {
        $.ajax({
            type: "DELETE",
            url: "/cart",
            success: function() {
                $(".table").load(" .table");
                $(".orderData").load(" .orderData");
            }
        });
    }

    function validCard() {
        let value = document.getElementById('card').value;
        var regex = /^\d{16}$/
        valid = value.match(regex);

        if (valid) {
            document.getElementById("card_err").style.display = "none";
            document.getElementById('card').classList.remove("border-danger");
            document.getElementById('card').classList.add("border-success");
            return true;
        } else {
            document.getElementById("card_err").style.display = "block";
            document.getElementById('card').classList.remove("border-success");
            document.getElementById('card').classList.add("border-danger");
            event.preventDefault();
        }
        return false;
    }
</script>

</html>