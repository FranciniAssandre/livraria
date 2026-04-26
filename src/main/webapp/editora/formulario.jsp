<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${editora != null ? 'Editar' : 'Nova'} Editora</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container py-5">
<div class="row justify-content-center">
    <div class="col-md-6 border p-4 shadow-sm bg-light">
        <h3 class="text-center mb-4">${editora != null ? 'Edição de Editora' : 'Cadastro de Editora'}</h3>

        <form action="${pageContext.request.contextPath}/editoras/${editora != null ? 'atualizacao' : 'insercao'}" method="post">

            <c:if test="${editora != null}">
                <input type="hidden" name="id" value="${editora.id}">
            </c:if>

            <div class="mb-3">
                <label class="form-label">CNPJ</label>
                <input type="text" name="cnpj" class="form-control" value="${editora.CNPJ}" required>
            </div>

            <div class="mb-3">
                <label class="form-label">Nome da Editora</label>
                <input type="text" name="nome" class="form-control" value="${editora.nome}" required>
            </div>

            <div class="d-flex justify-content-between">
                <a href="lista" class="btn btn-secondary">Cancelar</a>
                <button type="submit" class="btn btn-success">Salvar</button>
            </div>
        </form>
    </div>
</div>
</body>
</html>