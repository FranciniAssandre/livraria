package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.EditoraDAO;
import br.ufscar.dc.dsw.domain.Editora;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/editoras/*")
public class EditoraController extends HttpServlet {

    private EditoraDAO dao;

    @Override
    public void init() {
        dao = new EditoraDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        if (action == null) action = "";

        try {
            switch (action) {
                case "/cadastro": apresentaFormCadastro(request, response); break;
                case "/insercao": insere(request, response); break;
                case "/remocao": remove(request, response); break;
                case "/edicao": apresentaFormEdicao(request, response); break;
                case "/atualizacao": atualize(request, response); break;
                default: lista(request, response); break;
            }
        } catch (RuntimeException e) { throw new ServletException(e); }
    }

    private void lista(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Editora> listaEditoras = dao.getAll();
        request.setAttribute("listaEditoras", listaEditoras);
        request.getRequestDispatcher("/editora/lista.jsp").forward(request, response);
    }

    private void apresentaFormCadastro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/editora/formulario.jsp").forward(request, response);
    }

    private void apresentaFormEdicao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Editora editora = dao.get(id);
        request.setAttribute("editora", editora);
        request.getRequestDispatcher("/editora/formulario.jsp").forward(request, response);
    }

    private void insere(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String cnpj = request.getParameter("cnpj");
        String nome = request.getParameter("nome");
        dao.insert(new Editora(cnpj, nome));
        response.sendRedirect("lista");
    }

    private void atualize(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        Long id = Long.parseLong(request.getParameter("id"));
        String cnpj = request.getParameter("cnpj");
        String nome = request.getParameter("nome");
        dao.update(new Editora(id, cnpj, nome));
        response.sendRedirect("lista");
    }

    private void remove(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        dao.delete(new Editora(id));
        response.sendRedirect("lista");
    }
}