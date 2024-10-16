<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Agregar Hecho</title>
    <style>
        .success { color: green; }
        .error { color: red; }
    </style>
</head>
<body>
<h1>Agregar Hecho</h1>

<!-- Mostrar mensaje de éxito -->
<% if (request.getAttribute("mensaje") != null) { %>
<div class="success"><%= request.getAttribute("mensaje") %></div>
<% } %>

<!-- Mostrar mensaje de error -->
<% if (request.getAttribute("mensajeError") != null) { %>
<div class="error"><%= request.getAttribute("mensajeError") %></div>
<% } %>

<form action="agregar-hecho" method="post">
    <label for="descripcion">Descripción:</label>
    <input type="text" id="descripcion" name="descripcion" required />
    <br/>

    <label for="fecha">Fecha (yyyy-MM-dd):</label>
    <input type="text" id="fecha" name="fecha" required />
    <br/>

    <label for="clasificacion">Clasificación:</label>
    <select id="clasificacion" name="clasificacion" required>
        <option value="Politica">Política</option>
        <option value="Economia">Economía</option>
        <option value="Tecnologia">Tecnología</option>
        <option value="Salud">Salud</option>
        <option value="Deportes">Deportes</option>
    </select>
    <br/>

    <input type="submit" value="Agregar Hecho" />
</form>

<hr/>
<a href="index.jsp">Volver al índice</a>
</body>
</html>
