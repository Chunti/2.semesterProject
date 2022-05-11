<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
             About
    </jsp:attribute>

    <jsp:attribute name="footer">
            About
    </jsp:attribute>

    <jsp:body>

        <p>You have requested to buy a carport. Your orderID is : ${sessionScope.orderId}<br>
            One from our sales department will call you as soon a posible.</p>

    </jsp:body>
</t:pagetemplate>