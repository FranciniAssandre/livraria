<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Livraria Virtual</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { padding-top: 70px; }
        footer { background-color: #f8f9fa; }
    </style>
</head>

<body class="d-flex flex-column h-100">
<header>
    <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">Gerenciamento de Livros</a>
        </div>
    </nav>
</header>

<main class="flex-shrink-0">
    <div class="container">
        <ul class="nav nav-tabs nav-fill mb-4">
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/">Menu Principal</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/livros/cadastro">Adicionar Novo Livro</a>
            </li>
        </ul>

        <div class="row mb-3 text-center">
            <h3>Lista de Livros</h3>
        </div>

        <div class="table-responsive">
            <table class="table table-striped table-hover table-bordered">
                <thead class="table-dark">
                <tr>
                    <th>ID</th>
                    <th>Título</th>
                    <th>Editora</th>
                    <th>Autor</th>
                    <th>Ano</th>
                    <th>Preço</th>
                    <th colspan="2">Ações</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="livro" items="${requestScope.listaLivros}">
                    <tr>
                        <td>${livro.id}</td>
                        <td>${livro.titulo}</td>
                        <td>${livro.editora.nome}</td>
                        <td>${livro.autor}</td>
                        <td>${livro.ano}</td>
                        <td>
                            <fmt:formatNumber value="${livro.preco}" type="currency" currencySymbol="R$" />
                        </td>
                        <td>
                            <a class="btn btn-sm btn-primary" href="${pageContext.request.contextPath}/livros/edicao?id=${livro.id}">Editar</a>
                        </td>
                        <td>
                            <a class="btn btn-sm btn-danger" href="${pageContext.request.contextPath}/livros/remocao?id=${livro.id}"
                               onclick="return confirm('Tem certeza de que deseja excluir este item?');">
                                Excluir
                            </a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</main>

<footer class="footer mt-auto py-3 fixed-bottom border-top">
    <div class="container text-center">
        <span class="text-muted">UFSCar - Departamento de Computação</span>
    </div>
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>