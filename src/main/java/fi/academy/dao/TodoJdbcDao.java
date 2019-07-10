package fi.academy.dao;

import fi.academy.Todo;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.net.URLEncoder;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Qualifier("jdbc")
public class TodoJdbcDao implements TodoDao {
    private Connection yhteys;

    public TodoJdbcDao() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        System.out.println("Ajuri ladattu");

        yhteys = DriverManager.getConnection("jdbc:postgresql://localhost:5432/todosovellus", "postgres", "tuomassql");
        System.out.println("Yhteys saatu");
    }

    @Override
    public List<Todo> haeKaikki() {
        String sql = "SELECT * FROM todolista";
        List<Todo> haetut = new ArrayList<>();
        try (PreparedStatement pstmt = yhteys.prepareStatement(sql)) {
            for (ResultSet rs = pstmt.executeQuery(); rs.next(); ) {
                Todo t = new Todo();
                t.setId(rs.getInt("id"));
                t.setTehtava(rs.getString("tehtava"));
                t.setVastuuhenkilo(rs.getString("vastuuhenkilo"));
                haetut.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.EMPTY_LIST;
        }
        return haetut;
    }

    @Override
    public Optional<Todo> haeIdlla(int id) throws SQLException {
        PreparedStatement haeYksi = yhteys.prepareStatement("SELECT FROM todolista WHERE (id =?)");
        haeYksi.executeUpdate();
        return Optional.empty();
    }


    @Override
    public int lisaa(@RequestBody Todo tehtava) throws SQLException {
        String tehtavanLisays = tehtava.getTehtava();
        String sqlKomento = "INSERT INTO todolista (tehtava) VALUES (?)";
        PreparedStatement lisaaUusiTodo = yhteys.prepareStatement(sqlKomento);
        lisaaUusiTodo.setString(1, tehtavanLisays);
        lisaaUusiTodo.executeUpdate();
        return tehtava.getId();
    }

    @Override
    public void poista(int id) throws SQLException {
        PreparedStatement poistaTaulukosta = yhteys.prepareStatement("DELETE FROM todolista WHERE (id =?)");
        poistaTaulukosta.setInt(1, id);
        poistaTaulukosta.executeUpdate();
    }

    @Override
    public boolean muuta(int id, Todo tiedot) {
        return false;
    }
}
