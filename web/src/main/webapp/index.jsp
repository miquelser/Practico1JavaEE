<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- index.jsp -->
<!DOCTYPE html>
<html>
<head>
    <title>Índice</title>
</head>
<body>
    <h1>Bienvenido al Gestor de Hechos</h1>
    <ul>
        <li><a href="${pageContext.request.contextPath}/listar-hechos">Listar Hechos</a></li>
        <li><a href=${pageContext.request.contextPath}/buscar-hechos.jsp">Buscar Hechos</a></li>
        <li><a href=${pageContext.request.contextPath}/agregarHecho.jsp">Agregar Hecho</a></li>
    </ul>
</body>
</html>