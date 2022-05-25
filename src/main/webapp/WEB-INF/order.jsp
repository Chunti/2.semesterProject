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

        <c:forEach items="${sessionScope.orders}" var="item" varStatus="loop">

            <div class="container"  >
                <div class="card" style="width:400px">
                    <div class="card-body">
                        <c:if test="${sessionScope.user.role == 1 }">
                            <h4 class="card-title">Order number: ${item.orderId} <br>Name: ${item.fullName} <br> Phone Number: ${item.phone}</h4>
                            <p class="card-test"> Carport length: ${item.length} Carport width: ${item.width}
                                <c:if test="${item.shedLength != 0}"><br> Shed length: ${item.shedLength}</c:if>
                                <br> Material: ${item.material} </p>
                            <form method="post">
                                <button type="submit" name="edit" value="${loop.index}">Edit</button>
                                <button type="submit" name="delete" value="${item.orderId}">Delete</button>
                            </form>
                        </c:if>
                        <c:if test="${sessionScope.user.role == 0 }">
                        <h4 class="card-title">Order number: ${item.orderId}</h4>
                        <p class="card-test"> Carport length: ${item.length} Carport width: ${item.width}
                            <c:if test="${item.price != 0 }">
                            <p class="card-test"> Price: ${item.price}
                                <br>
                                <form method="post">
                                    <button type="submit" name="details" value="${loop.index}">Details</button>
                                </form>

                            </c:if>

                        </c:if>
                    </div>
                </div>
            </div>
        </c:forEach>

    </jsp:body>

</t:pagetemplate>