<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<li class="nav-item">
    <a class="nav-link" href="${requestScope['javax.servlet.forward.request_uri']}?lang=en">EN</a>
</li>
<h4>|</h4>
<li class="nav-item">
    <a class="nav-link" href="${requestScope['javax.servlet.forward.request_uri']}?lang=ru">RU</a>
</li>