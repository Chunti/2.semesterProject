<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
        Din stykliste:
    </jsp:attribute>

    <jsp:attribute name="footer">
        Stykliste og tegning
    </jsp:attribute>

    <jsp:body>

        <table >
            <tr>
                <th>Materiale</th>
                <th>Længde</th>
                <th>Antal</th>
                <th>Enhed</th>
                <th>Beskrivelse</th>
            </tr>
            <c:forEach items="${sessionScope.bomitems}" var="item" varStatus="loop">

                <tr>
                    <td>${item.name}</td>
                    <td> <c:if test="${item.length != 0}">${item.length}</c:if></td>
                    <td>${item.amount}</td>
                    <td>${item.unit}</td>
                    <td>${item.description}</td>
                </tr>

            </c:forEach>

        </table>

        <br>

        <div>
            <h2>Tegning</h2>

            <p>Her indsættes en tegning:</p>

                ${requestScope.svgdrawing}
        </div>


    </jsp:body>

</t:pagetemplate>
