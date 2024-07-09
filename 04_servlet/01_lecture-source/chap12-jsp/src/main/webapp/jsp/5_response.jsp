<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        String menuName = (String) request.getAttribute("menuName");
        int amount = (Integer) request.getAttribute("amount");
        int orderPrice = (Integer) request.getAttribute("orderPrice");
    %>
<%--    <h3>주문하신 음식 : <%=menuName%> </h3>
    <h3>주문하신 수량 : <%=amount%> </h3>
    <h2>결제하실 최종 금액 : <%=orderPrice%> </h2>--%>

<%--    expression 문법 : 프론트 개발자가 자바를 모르는 상태로 개발을 하기 위해서 사용--%>
<%--    scope를 사용하지 않으면 가장 가까운 값읅 가져온다.--%>
    <h3>주문하신 음식 : ${menuName}</h3>
    <h3>주문하신 수량 : ${amount}</h3>
    <h3>결제하실 최종 금액 : ${requestScope.orderPrice}</h3>
</body>
</html>
