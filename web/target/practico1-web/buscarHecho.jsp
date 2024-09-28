<%@ page import="tse.practico1.models.HechosModel" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<% List<HechosModel> hechos = (List<HechosModel>)request.getAttribute("hechos"); %>


<%-- --%>
<!DOCTYPE html>
<html>
<head>
    <title>Buscar Hechos</title>
</head>
<body>
    <h1>Buscar Hechos</h1>
    <form action="buscar-hechos" method="get">
        <label for="buscar">Buscar:</label>
        <input type="text" id="buscar" name="buscar" />
        <input type="submit" value="Buscar" />
    </form>
    <hr/>
    <h2>Resultados</h2>
    <table border="1">
        <thead>
            <tr>
                <th>Numero</th>
                <th>Descripción</th>
                <th>Fecha</th>
                <th>Calificación</th>
                <th>Estado</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
        <%for(HechosModel h : hechos){%>
        <tr>
            <td><%=h.getNumero() %></td>
            <td><%=h.getDescripcion() %></td>
            <td><%=h.getFecha() %></td>
            <td><%=h.getDescripcion() %></td>
            <td><%=h.getCalificacion() %></td>
            <td><%=h.getEstado() %></td>

            <form action="eliminar-hecho" method="post" style="display:inline;">
                <input type="hidden" name="numero" value="${hecho.numero}" />
                <input type="submit" value="Eliminar" />
            </form>
        </tr>

        <%} %>


        </tbody>
    </table>
    <hr/>
    <a href="index.jsp">Volver al índice</a>
</body>
</html>