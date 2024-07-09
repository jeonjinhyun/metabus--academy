<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.Date, java.text.SimpleDateFormat" %>

<%
    Date today = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 E요일 a hh시 mm분 ss초");

    String output = sdf.format(today);
%>
<h3><%= output%></h3>
