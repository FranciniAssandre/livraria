package br.ufscar.dc.dsw.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import br.ufscar.dc.dsw.domain.Editora;
import br.ufscar.dc.dsw.domain.Livro;

public class LivroDAO extends GenericDAO {

    public void insert(Livro livro) {
        String sql = "INSERT INTO Livro (titulo, autor, ano, preco, editora_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = this.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, livro.getTitulo());
            st.setString(2, livro.getAutor());
            st.setInt(3, livro.getAno());
            st.setFloat(4, livro.getPreco());
            st.setLong(5, livro.getEditora().getId());
            st.executeUpdate();
        } catch (SQLException e) { throw new RuntimeException(e); }
    }

    public List<Livro> getAll() {
        List<Livro> lista = new ArrayList<>();
        String sql = "SELECT * from Livro l, Editora e where l.EDITORA_ID = e.ID order by l.id";
        try (Connection conn = this.getConnection(); Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Editora ed = new Editora(rs.getLong("editora_id"), rs.getString("cnpj"), rs.getString("nome"));
                lista.add(new Livro(rs.getLong("id"), rs.getString("titulo"), rs.getString("autor"), rs.getInt("ano"), rs.getFloat("preco"), ed));
            }
        } catch (SQLException e) { throw new RuntimeException(e); }
        return lista;
    }

    public void delete(Livro livro) {
        String sql = "DELETE FROM Livro where id = ?";
        try (Connection conn = this.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {
            st.setLong(1, livro.getId());
            st.executeUpdate();
        } catch (SQLException e) { throw new RuntimeException(e); }
    }

    public void update(Livro livro) {
        String sql = "UPDATE Livro SET titulo = ?, autor = ?, ano = ?, preco = ?, editora_id = ? WHERE id = ?";
        try (Connection conn = this.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, livro.getTitulo());
            st.setString(2, livro.getAutor());
            st.setInt(3, livro.getAno());
            st.setFloat(4, livro.getPreco());
            st.setLong(5, livro.getEditora().getId()); // Corrigido de setFloat para setLong
            st.setLong(6, livro.getId());
            st.executeUpdate();
        } catch (SQLException e) { throw new RuntimeException(e); }
    }

    public Livro get(Long id) {
        String sql = "SELECT * from Livro l, Editora e where l.id = ? and l.EDITORA_ID = e.ID";
        try (Connection conn = this.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {
            st.setLong(1, id);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    Editora ed = new EditoraDAO().get(rs.getLong("editora_id"));
                    return new Livro(id, rs.getString("titulo"), rs.getString("autor"), rs.getInt("ano"), rs.getFloat("preco"), ed);
                }
            }
        } catch (SQLException e) { throw new RuntimeException(e); }
        return null;
    }
}