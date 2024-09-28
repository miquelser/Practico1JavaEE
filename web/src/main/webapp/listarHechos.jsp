<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<%@ page import="java.util.List" %>
<%@ page import="tse.practico1.models.HechosModel" %>
<% List<HechosModel> hechos = (List<HechosModel>)request.getAttribute("hechos"); %>



<!DOCTYPE html>
<html>
<head>
    <title>Listar Hechos</title>
</head>
<body>
    <h1>Lista de Hechos</h1>
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
        <form action="eliminar-hecho" method="post" style="display:inline;">
        <%for(HechosModel h : hechos){%>
        <tr>
            <td><%=h.getNumero() %></td>
            <td><%=h.getDescripcion() %></td>
            <td><%=h.getFecha() %></td>
            <td><%=h.getDescripcion() %></td>
            <td><%=h.getCalificacion() %></td>
            <td><%=h.getEstado() %></td>

                <input type="hidden" name="numero" value="<%= h.getNumero()%>" />
                <input type="submit" value="Eliminar" />

        </tr>

        <%} %>
        </form>
        </tbody>
    </table>
    <hr/>
    <a href="index.jsp">Volver al índice</a>
</body>
</html>