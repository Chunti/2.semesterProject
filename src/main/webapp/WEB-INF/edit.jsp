<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
         Welcome to the frontpage
    </jsp:attribute>

    <jsp:attribute name="footer">
        Welcome to the frontpage
    </jsp:attribute>

    <jsp:body>

        <div style="margin-top: 3em;margin-bottom: 3em;">
            Klik her for at se en <a href="${pageContext.request.contextPath}/SVGServlet">SVG tegning</a>
        </div>

        <div class="container"  >
            <div class="card" style="width:400px">
                <div class="card-body">
                    <h4 class="card-title" >Order number: ${sessionScope.editOrder.orderId} <br>Name: ${sessionScope.editOrder.fullName} <br> Phone Number: ${sessionScope.editOrder.phone}</h4>
                    <p class="card-test"> Carport length: ${sessionScope.editOrder.length} Carport width: ${sessionScope.editOrder.width}
                        <c:if test="${sessionScope.editOrder.shedLength != 0}"><br> Shed length: ${sessionScope.editOrder.shedLength}</c:if>
                        <br>Price:${sessionScope.price} Offerprice: ${sessionScope.offerPrice}
                </div>
            </div>
        </div>

        <input id="number" type="number" name="number" value="${sessionScope.offerPrice}" min="${sessionScope.price}">
        <button type="submit" name="ok" value="1">Give offer</button>

    </jsp:body>

</t:pagetemplate>
