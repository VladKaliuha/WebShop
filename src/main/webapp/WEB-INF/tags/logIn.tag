<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${pageContext.request.locale}"/>
<fmt:setBundle basename="localization"/>

<c:choose>
    <c:when test="${empty user}">
        <li class="nav-item">
            <a class="nav-link" href="/sign-in"><fmt:message key="navbar.link.sign.in"/></a>
        </li>
    </c:when>

    <c:when test="${not empty user}">
        <div>
            <img src="${user.icon}" style="height:30pt" class="rounded-circle" />
        </div>
        <li class="nav-item">
            <a class="nav-link" href="#">${user.firstName} ${user.lastName}</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="\logout"><fmt:message key="navbar.link.logout"/></a>
        </li>
    </c:when>
</c:choose>