<%--
  Orders page
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Orders</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style_orders.css">
</head>
<body>
<br>
<h1>Заказы</h1>

<table border="1" class="orders-table">
    <tr>
        <th>#</th>
        <th>Вид сервиса</th>
        <th>Вид ремонта</th>
        <th>Вид одежды</th>
        <th>Стоимость</th>
        <th>Заказ готов</th>
    </tr>

    <c:forEach var="order" items="${orders}" varStatus="i">
        <c:choose>
            <c:when test="${(i.count) % 2 == 0}">
                <tr class="even">
            </c:when>
            <c:otherwise>
                <tr class="odd">
            </c:otherwise>
        </c:choose>
            <td>${order.id}</td>
            <td>${order.serviceType}</td>
            <td>${order.repairType}</td>
            <td>${order.clotherType}</td>
            <td>${order.cost}</td>
            <td>${order.complete}</td>
            <td><a href="<c:url value='/edit?orderid=${order.id}'/>">Изменить</a>
            </td>
            <td><a href="<c:url value='/remove?orderid=${order.id}'/>">Удалить</a></td>
        </tr>
    </c:forEach>
</table>


<h1>Добавить заказ</h1>


<c:set var="order" value="${order}"/>


<div class="order-dto_js" data-serviceTypeId="${order.serviceTypeId}" data-repairType="${order.repairTypeId}" data-clotherType="${order.clotherTypeId}" hidden>order-dto</div>

<form class="addForm_js" action="add">
    <table>
        <c:if test="${!empty order.id}">
            <tr>
                <td>
                    id
                </td>
                <td>
                    <input type="text" name="orderId" value="${order.id}" readonly/>
                </td>
            </tr>
        </c:if>
        <tr>
            <td>
                Тип сервиса
            </td>
            <td>
                <select name="serviceType" class="selectService">
                    <option value="1">Ремонт</option>
                    <option value="2">Пошив</option>
                </select>

            </td>
        </tr>
        <tr>
            <td>
                Тип ремонта
            </td>
            <td>
                <select name="repairType" class="selectRepairType">
                    <option value="1">Укоротить</option>
                    <option value="2">Покрасить</option>
                    <option value="3">Сварить</option>
                    <option value="4">Обрезать</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>
                Тип Одежды
            </td>
            <td>
                <select name="clotherType">
                    <option value="1">Рубашка</option>
                    <option value="2">Джинсы</option>
                    <option value="3">Кофта</option>
                    <option value="4">Блузка</option>
                    <option value="5">Носки</option>
                </select>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <c:if test="${!empty order.id}">
                    <input type="submit" name="action"
                           value="Изменить"/>
                </c:if>
                <c:if test="${empty order.id}">
                    <input type="submit" name="action"
                           value="Добавить"/>
                </c:if>
            </td>
        </tr>
    </table>
</form>
</body>
<script src="${pageContext.request.contextPath}/js/main.js"></script>
</html>

