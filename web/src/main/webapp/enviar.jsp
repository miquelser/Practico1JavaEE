<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Enviar Mensaje</title>
</head>
<body>
<h1>Enviar Mensaje</h1>

<%-- Mostrar mensaje de éxito --%>
<% if (request.getAttribute("mensaje") != null) { %>
<div style="color: green;"><%= request.getAttribute("mensaje") %></div>
<% } %>

<%-- Mostrar mensaje de error --%>
<% if (request.getAttribute("mensajeError") != null) { %>
<div style="color: red;"><%= request.getAttribute("mensajeError") %></div>
<% } %>

<form action="enviar-mensaje" method="post">
    <label for="descripcion">Descripción:</label>
    <input type="text" id="descripcion" name="descripcion" required />
    <br/>
    <label for="fecha">Fecha (yyyy-MM-dd):</label>
    <input type="text" id="fecha" name="fecha" required />
    <br/>
    <label for="clasificacion">Clasificación:</label>
    <select id="clasificacion" name="clasificacion" required>
        <option value="Politica">Política</option>
        <option value="Economía">Economía</option>
        <option value="Tecnología">Tecnología</option>
        <option value="Salud">Salud</option>
        <option value="Deportes">Deportes</option>
    </select>
    <br/>
    <input type="submit" value="Enviar Mensaje" />
</form>

<hr/>
<a href="index.jsp">Volver al índice</a>
</body>
</html>
