<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
            Order bestilt
    </jsp:attribute>

    <jsp:attribute name="footer">
            Order bestilt
    </jsp:attribute>

    <jsp:body>

        <p>Du har nu bestilt en carport. Dit orderID er : ${sessionScope.orderId}<br>
            En fra vores salgspersonnale vil kontakte dig hutigst muligt.</p>

    </jsp:body>
</t:pagetemplate>