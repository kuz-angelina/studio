<%--
  Login page
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="login">
    <h1>Авторизация</h1>
    <form action="login" method="post" class="js-form">
        <label>
            <input type="text" name="login" placeholder="Имя пользователя"/>
        </label>
        <label>
            <input type="password" name="password" placeholder="Пароль"/>
        </label>
        <button type="submit" class="btn btn-primary btn-block btn-large">Авторизоваться</button>
    </form>
</div>

<div class="b-container _hide" id="b-container_js" hidden>
</div>
<div class="b-popup" id="error_js" hidden>
    <div class="b-popup-content">
        ${requestScope.msg}
        <br>
        <a href="javascript:hideErrorMsg()">Ок</a>
    </div>
</div>

<div id="var_error_js" hidden data-msg="${requestScope.msg}"></div>
<script src="${pageContext.request.contextPath}/js/login.js"></script>
</body>
</html>