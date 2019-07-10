package fi.academy.dao;

import fi.academy.Todo;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface TodoDao {
    List<Todo> haeKaikki();
    Optional<Todo> haeIdlla(int id) throws SQLException;
    int lisaa (Todo tehtava) throws SQLException;
    void poista(int id) throws SQLException;
    boolean muuta(int id, Todo tiedot);

   //void haeIdlla(int id) throws SQLException;
}
