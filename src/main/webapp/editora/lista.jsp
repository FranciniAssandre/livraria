<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gerenciamento de Editoras</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container py-5">
<header class="mb-4">
    <nav class="nav nav-pills">
        <a class="nav-link" href="${pageContext.request.contextPath}/index.jsp">Menu Principal</a>
        <a class="nav-link active" href="${pageContext.request.contextPath}/editoras/cadastro">Adicionar Editora</a>
    </nav>
</header>

<h3 class="mb-4">Lista de Editoras</h3>

<table class="table table-striped table-hover border">
    <thead class="table-dark">
    <tr>
        <th>ID</th>
        <th>CNPJ</th>
        <th>Nome</th>
        <th>Ações</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="editora" items="${listaEditoras}">
        <tr>
            <td>${editora.id}</td>
            <td>${editora.cnpj}</td>
            <td>${editora.nome}</td>
            <td>
                <a href="${pageContext.request.contextPath}/editoras/edicao?id=${editora.id}" class="btn btn-sm btn-primary">Editar</a>
                <a href="${pageContext.request.contextPath}/editoras/remocao?id=${editora.id}"
                   class="btn btn-sm btn-danger" onclick="return confirm('Excluir esta editora?')">Excluir</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>