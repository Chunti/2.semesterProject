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
            <div class="card" style="width:800px">
                <div class="card-body">

                    <h4 class="card-title" >Order number: ${sessionScope.editOrder.orderId} <br>Name: ${sessionScope.editOrder.fullName} <br> Phone Number: ${sessionScope.editOrder.phone}</h4>
                    <p class="card-test"> Carport length: ${sessionScope.editOrder.length} Carport width: ${sessionScope.editOrder.width}
                        <c:if test="${sessionScope.editOrder.shedLength != 0}"><br> Shed length: ${sessionScope.editOrder.shedLength} Shed width ${sessionScope.editOrder.shedWidth}</c:if>
                </div>
            </div>
        </div>


        <label for="length">Carport længde</label>
        <br>
        <select name="length" id="length">
            <c:forEach var="items" begin="0" end="18" varStatus="loop">
                <option value="${240+(loop.index*30)}">${240+(loop.index*30)} cm</option>
            </c:forEach>


        </select>
        <br>

        <label for="width">Carport bredde</label>
        <br>
        <select name="width" id="width">
            <c:forEach var="items" begin="0" end="12" varStatus="loop">
                <option value="${240+(loop.index*30)}">${240+(loop.index*30)} cm</option>
            </c:forEach>
        </select>
        <br>



        <legend>Tilkøb et redskabsskur:</legend>

        <input type="checkbox" id="shed" name="shed" value="true">
        <label for="shed"> Ja tak</label><br>




        <label for="shedlength">Carport længde</label>
        <br>
        <select name="shedlength" id="shedlength">
            <c:forEach var="items" begin="0" end="18" varStatus="loop">
                <option value="${150+(loop.index*30)}">${150+(loop.index*30)} cm</option>
            </c:forEach>

        </select>


            <button type="submit" name="ok" value="1">Update</button>


    </jsp:body>

</t:pagetemplate>
