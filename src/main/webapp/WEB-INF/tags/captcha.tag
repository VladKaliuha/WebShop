<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div>
    <div>
        <img src="/captcha?token_id=${token_id}">
    </div>
    <input data-rule="captcha" type="text" name="captcha" id="captcha"
        class="form-control input-sm" placeholder="*Captcha">
</div>
<div>
    <div>
        <input type="hidden" id="token_id" name="token_id" value="${token_id}"/>
    </div>
</div>
