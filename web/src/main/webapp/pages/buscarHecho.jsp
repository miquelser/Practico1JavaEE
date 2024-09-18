<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Buscar Hechos</title>
</head>
<body>
    <h1>Buscar Hechos</h1>
    <form action="buscar-hechos" method="post">
        <label for="tipo">Tipo:</label>
        <input type="text" id="tipo" name="tipo" />
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
            <c:forEach var="hecho" items="${hechos}">
                <tr>
                    <td>${hecho.numero}</td>
                    <td>${hecho.descripcion}</td>
                    <td>${hecho.fecha}</td>
                    <td>${hecho.calificacion}</td>
                    <td>${hecho.estado}</td>
                    <td>
                        <form action="eliminar-hecho" method="post" style="display:inline;">
                            <input type="hidden" name="numero" value="${hecho.numero}" />
                            <input type="submit" value="Eliminar" />
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <hr/>
    <a href="index.jsp">Volver al índice</a>
</body>
</html>