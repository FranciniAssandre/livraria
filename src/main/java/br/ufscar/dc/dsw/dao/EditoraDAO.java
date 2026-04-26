package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import br.ufscar.dc.dsw.domain.Editora;

public class EditoraDAO extends GenericDAO {

    public List<Editora> getAll() {
        List<Editora> lista = new ArrayList<>();
        String sql = "SELECT * FROM Editora ORDER BY id";
        try (Connection conn = this.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Editora(rs.getLong("id"), rs.getString("cnpj"), rs.getString("nome")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    public Editora get(Long id) {
        String sql = "SELECT * FROM Editora WHERE id = ?";
        try (Connection conn = this.getConnection();
             PreparedStatement st = conn.prepareStatement(sql)) {
            st.setLong(1, id);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return new Editora(rs.getLong("id"), rs.getString("cnpj"), rs.getString("nome"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}