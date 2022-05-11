<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="../error.jsp" isErrorPage="false"%>

<t:pagetemplate>
    <jsp:attribute name="header">
        Bestil din carport

    </jsp:attribute>

    <jsp:attribute name="footer">
        Bestil din carport
    </jsp:attribute>

    <jsp:body>

        <form method="post">
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

            <label for="height">Carport højde</label>
            <br>
            <select name="height" id="height">
                <c:forEach var="items" begin="0" end="5" varStatus="loop">
                    <option value="${190+(loop.index*30)}">${190+(loop.index*30)} cm</option>
                </c:forEach>
            </select>
            <br>
            <br>


            <fieldset>
                <legend>Tilkøb et redskabsskur:</legend>
                <div>
                    <input type="checkbox" id="shed" name="shed" value="true">
                    <label for="shed">Ja tak</label>
                </div>
            </fieldset>



            <label for="shedlength">Carport længde</label>
            <br>
            <select name="shedlength" id="shedlength">
                <c:forEach var="items" begin="0" end="18" varStatus="loop">
                    <option value="${150+(loop.index*30)}">${150+(loop.index*30)} cm</option>
                </c:forEach>


            </select>
            <br>

            <label for="shedwidth">Carport bredde</label>
            <br>
            <select name="shedwidth" id="shedwidth">
                <c:forEach var="items" begin="0" end="17" varStatus="loop">
                    <option value="${210+(loop.index*30)}">${210+(loop.index*30)} cm</option>
                </c:forEach>
            </select>

            <br>
            <br>

            <input type="submit" id="button" value="Put i kurv" >
        </form>


    </jsp:body>

</t:pagetemplate>