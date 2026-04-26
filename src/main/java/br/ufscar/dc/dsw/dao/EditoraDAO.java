package br.ufscar.dc.dsw.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import br.ufscar.dc.dsw.domain.Editora;

public class EditoraDAO extends GenericDAO {

    public void insert(Editora editora) {
        String sql = "INSERT INTO Editora (cnpj, nome) VALUES (?, ?)";
        try (Connection conn = this.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, editora.getCNPJ());
            st.setString(2, editora.getNome());
            st.executeUpdate();
        } catch (SQLException e) { throw new RuntimeException(e); }
    }

    public List<Editora> getAll() {
        List<Editora> lista = new ArrayList<>();
        String sql = "SELECT * FROM Editora ORDER BY id";
        try (Connection conn = this.getConnection(); Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Editora(rs.getLong("id"), rs.getString("cnpj"), rs.getString("nome")));
            }
        } catch (SQLException e) { throw new RuntimeException(e); }
        return lista;
    }

    public void delete(Editora editora) {
        String sql = "DELETE FROM Editora WHERE id = ?";
        try (Connection conn = this.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {
            st.setLong(1, editora.getId());
            st.executeUpdate();
        } catch (SQLException e) { throw new RuntimeException(e); }
    }

    public void update(Editora editora) {
        String sql = "UPDATE Editora SET cnpj = ?, nome = ? WHERE id = ?";
        try (Connection conn = this.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, editora.getCNPJ());
            st.setString(2, editora.getNome());
            st.setLong(3, editora.getId());
            st.executeUpdate();
        } catch (SQLException e) { throw new RuntimeException(e); }
    }

    public Editora get(Long id) {
        String sql = "SELECT * FROM Editora WHERE id = ?";
        try (Connection conn = this.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {
            st.setLong(1, id);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return new Editora(id, rs.getString("cnpj"), rs.getString("nome"));
                }
            }
        } catch (SQLException e) { throw new RuntimeException(e); }
        return null;
    }
}