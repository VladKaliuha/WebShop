<!DOCTYPE html>
<html lang="en">

<head>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
        <%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt"%>
            <c:set var="title" value="Shopping" />
            <%@ include file="/WEB-INF/jspf/head.jspf"%>

                <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
                <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
                <link rel="stylesheet" type="text/css" href="/style/sidebar.css" />
                <link rel="stylesheet" type="text/css" href="/style/shopping.css" />
</head>

<header>
    <%@ include file="/WEB-INF/jspf/header.jspf"%>
</header>


<script>
    $(document).ready(function() {
        $('button').click(function() {
            $('.sidebar').toggleClass('fliph');
        });
    });
</script>

<body>
    <div class="row">
        <div class="col-18">
            <div class="sidebar left ">
                <form role="form" method="POST" action="/shopping">
                    <ul class="list-sidebar bg-defoult">
                        <li>
                            <a href="#" data-toggle="collapse" data-target="#dashboard" class="collapsed active"> <i class="fa fa-th-large"></i> <span class="nav-label"> <fmt:message key="items.by.name"/> </span> <span class="fa fa-chevron-left pull-right"></span> </a>
                            <ul class="sub-menu collapse" id="dashboard">
                                <input data-rule="name" type="text" name="product_name" id="product_name" class="form-control input-sm" placeholder="Product name">
                            </ul>
                        </li>
                        <li>
                            <a href="#" data-toggle="collapse" data-target="#products" class="collapsed active"> <i class="fa fa-bar-chart-o"></i> <span class="nav-label"><fmt:message key="items.by.category"/></span> <span class="fa fa-chevron-left pull-right"></span> </a>
                            <ul class="sub-menu collapse" id="products">
                                <c:forEach var="category" items="${categories}">
                                    ... <input type="checkbox" class="option-input radio" name="product_category" id="${category.name}" value="${category.name}">
                                    <span style="color:white"> ${category.name}</span> <br>
                                </c:forEach>
                            </ul>
                        </li>
                        <li> <a href="#" data-toggle="collapse" data-target="#tables" class="collapsed active"><i
                    								class="fa fa-table"></i> <span class="nav-label"><fmt:message key="items.by.fabricator"/></span><span
                    								class="fa fa-chevron-left pull-right"></span></a>
                            <ul class="sub-menu collapse" id="tables">
                                <c:forEach var="fabricator" items="${fabricators}">
                                    ... <input type="checkbox" class="option-input radio" name="product_fabricator" id="${fabricator.name}" value="${fabricator.name}">
                                    <span style="color:white"> ${fabricator.name}</span><br>
                                </c:forEach>
                            </ul>
                        </li>
                        <li> <a href="#" data-toggle="collapse" data-target="#e-commerce" class="collapsed active"><i
                    								class="fa fa-shopping-cart"></i> <span class="nav-label"><fmt:message key="items.by.price"/></span><span
                    								class="fa fa-chevron-left pull-right"></span></a>
                            <ul class="sub-menu collapse" id="e-commerce">
                                <input data-rule="price" name="min_price" id="min_price" class="form-control input-sm" placeholder=<fmt:message key="items.by.price.min" />>
                                <br>
                                <input data-rule="price" name="max_price" id="max_price" class="form-control input-sm" placeholder=<fmt:message key="items.by.price.max" />>
                            </ul>
                        </li>
                        <li>
                            <a href="#" data-toggle="collapse" data-target="#items" class="collapsed active"><i
                                                    class="fa fa-bar-chart-o"></i><span class="nav-label"><fmt:message key="items.items.count"/></span><span
                                                    class="fa fa-chevron-left pull-right"></span> </a>
                            <ul class="sub-menu collapse" id="items">
                                ... <input type="radio" class="option-input radio" name="items_count" id="12" value="12"><span style="color:white"> 12</span>
                                <br>... <input type="radio" class="option-input radio" name="items_count" id="16" value="16"><span style="color:white"> 16</span>
                                <br>... <input type="radio" class="option-input radio" name="items_count" id="20" value="20"><span style="color:white"> 20</span><br>
                            </ul>
                        </li>
                        <li>
                            <a href="#" data-toggle="collapse" data-target="#sort" class="collapsed active"><i
                                                     class="fa fa-bar-chart-o"></i> <span class="nav-label"><fmt:message key="items.sorting"/></span><span
                                                     class="fa fa-chevron-left pull-right"></span> </a>
                            <ul class="sub-menu collapse" id="sort">
                                ... <input type="radio" class="option-input radio" name="sorting" id="A-Z" value="A-Z"><span style="color:white"> <fmt:message key="items.name.az"/></span>
                                <br> ... <input type="radio" class="option-input radio" name="sorting" id="Z-A" value="Z-A"><span style="color:white"> <fmt:message key="items.name.za"/></span>
                                <br> ... <input type="radio" class="option-input radio" name="sorting" id="Min-Max" value="Min-Max"><span style="color:white"> <fmt:message key="items.price.min.max"/></span>
                                <br> ... <input type="radio" class="option-input radio" name="sorting" id="Max-Min" value="Max-Min"><span style="color:white"> <fmt:message key="items.price.max.min"/></span><br>
                            </ul>
                        </li>
                        <input type="submit" value=<fmt:message key="items.filter" /> class="btn btn-info btn-block bg-dark">
                    </ul>
                </form>
            </div>
        </div>

        <div class="col">
            <div id="content ">
                <br><br>
                <div class="col-md-11 products">
                    <div class="row">
                        <c:forEach var="product" items="${pageItems.products}">
                            <div class="col-sm-3">
                                <div class="product">
                                    <div class="product-img">
                                        <a href="#"><img src="${product.icon}" alt=""></a>
                                    </div>
                                    <p class="product-title">
                                        <a href="#">${product.name}</a>
                                    </p>
                                    <p class="product-desc">Fabricator: ${product.fabricator}<br> Category: ${product.category}<br></p>
                                    <input class="btn btn-primary" onclick="addToCart(${product.id})" type="button" value="${product.price}$">
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <nav aria-label="Page navigation example">
        <ul class="pagination justify-content-center" id="pagination">
            <c:forEach begin="1" end="${page_count}" varStatus="loop">
                <li><a class="page-link" id="page-link${loop.index}" onclick="pagination(${loop.index})">${loop.index}</a></li>
            </c:forEach>
        </ul>
    </nav>
    <br><br><br>
    <footer>
        <%@ include file="/WEB-INF/jspf/footer.jspf"%>
    </footer>
</body>

<script>
    $(document).ready(function() {
        document.getElementById(getParameterByName('items_count')).checked = true;
        document.getElementById(getParameterByName('product_category')).checked = true;
        document.getElementById(getParameterByName('sorting')).checked = true;
        document.getElementById(getParameterByName('product_fabricator')).checked = true;
        document.getElementById('product_name').value = getParameterByName('product_name');
        document.getElementById('min_price').value = getParameterByName('min_price');
        document.getElementById('max_price').value = getParameterByName('max_price');
    });

    function pagination(page_no) {
        newReq = window.location.href.replace(/.(page=)\d+/, '');
        if (newReq.endsWith("shopping")) {
            document.getElementById('page-link' + page_no).href = newReq + "?page=" + page_no;
        } else {
            document.getElementById('page-link' + page_no).href = newReq + "&page=" + page_no;
        }
    }

    function getParameterByName(name) {
        var match = RegExp('[?&]' + name + '=([^&]*)').exec(window.location.search);
        return match && decodeURIComponent(match[1].replace(/\+/g, ' '));
    }

    function addToCart(id) {
        $.ajax({
            type: "POST",
            url: "/shopping",
            data: {
                product_id: id
            },
            success: function() {
                alert("Product was added to cart");
            }
        });
    }
</script>

</html>