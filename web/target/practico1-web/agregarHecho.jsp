<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Agregar Hecho</title>
</head>
<body>
    <h1>Agregar Hecho</h1>
    <form action="agregar-hecho" method="post">       
        <br/> -->
        <label for="descripcion">Descripción:</label>
        <input type="text" id="descripcion" name="descripcion" required />
        <br/>
        <label for="fecha">Fecha (yyyy-MM-dd):</label>
        <input type="text" id="fecha" name="fecha" required />
        <br/>
        <label for="calificacion">Calificación:</label>
        <select id="calificacion" name="calificacion" required>
            <option value="Politica">Política</option>
            <option value="Economía">Economía</option>
            <option value="Tecnología">Tecnología</option>
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
