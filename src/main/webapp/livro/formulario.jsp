<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${livro != null}">
    <input type="hidden" name="id" value="${livro.id}" />
</c:if>

<div class="col-md-12">
    <label for="titulo" class="form-label">Título</label>
    <input type="text" class="form-control" id="titulo" name="titulo" value="${livro.titulo}" required>
</div>

<div class="col-md-12">
    <label for="autor" class="form-label">Autor</label>
    <input type="text" class="form-control" id="autor" name="autor" value="${livro.autor}" required>
</div>

<div class="col-md-12">
    <label for="editora" class="form-label">Editora</label>
    <select class="form-select" name="editora" required>
        <c:forEach var="editora" items="${listaEditoras}">
            <option value="${editora.id}" ${editora.id == livro.editora.id ? 'selected' : ''}>
                    ${editora.nome}
            </option>
        </c:forEach>
    </select>
</div>

<div class="col-md-6">
    <label for="ano" class="form-label">Ano</label>
    <input type="number" class="form-control" id="ano" name="ano" value="${livro.ano}" required>
</div>

<div class="col-md-6">
    <label for="preco" class="form-label">Preço</label>
    <input type="text" class="form-control" id="preco" name="preco" value="${livro.preco}" required>
</div>

<div class="col-12 text-center mt-4">
    <button type="submit" class="btn btn-primary">Salvar</button>
</div>