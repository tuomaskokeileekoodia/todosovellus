package fi.academy;

import fi.academy.dao.TodoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/todonettisivu")
public class TodoController {
    private TodoDao dao;

    @Autowired
    public TodoController(TodoDao dao) {
        this.dao = dao;
    }

    @GetMapping("")
    public List<Todo> listaaKaikki() {
        List<Todo> kaikki = dao.haeKaikki();
        System.out.printf("Haetaan listaa, alkioita: %d kpl\n", kaikki.size());
        return kaikki;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> etsi(@PathVariable(name = "id", required = true) int id) throws SQLException {
        var haettu = dao.haeIdlla(id);
        if(!haettu.isPresent()) {
            return ResponseEntity
                 .status(HttpStatus.NOT_FOUND)
                   // .body(new Virheviesti(String.format("Id %d ei ole olemassa", id)));
        .body((String.format("Id %d ei ole olemassa", id)));
        }

        return ResponseEntity.ok(haettu.get());
    }

//    @PostMapping("")
//    public ResponseEntity<?> lisaaTodo(@RequestBody Todo uusi) throws SQLException {
//        System.out.println("Luodaan uutta: " + uusi);
//        int id = dao.lisaa(uusi);
//        System.out.println("Onnistui");
//        URI location = ServletUriComponentsBuilder
//                .fromCurrentRequest()
//                .path("/{id}")
//                .buildAndExpand(id)
//                .toUri();
//        return ResponseEntity.created(location).body(uusi);
//    }

    //Tämän pystyi toteuttamaan yksinkertaisemmin, palaa tähän jos jää aikaa
    @PostMapping("")
    public ResponseEntity<?> lisaaTodo(@RequestBody Todo uusi) {
        System.out.println("Luodaan uutta: " + uusi);
        int id = 0;
        try {
            id = dao.lisaa(uusi);
        }catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Onnistui");
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
        return ResponseEntity.created(location).body(uusi);
    }

    @DeleteMapping("/{id}")
    public void poista(@PathVariable int id) throws SQLException {
        dao.poista(id);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<?> saveResource(@RequestBody Todo todo, @PathVariable("id") String id) {
//        for (Todo t: ???) {
//            if (t.getId() == Integer.parseInt(id)) {
//                t.setTehtava(todo.getTehtava());
//                t.setVastuuhenkilo(todo.getVastuuhenkilo());
//            }
//        }
//        return ResponseEntity.ok("Tehtavaa tai vastuuhenkiloa muutettu");
//    }


}
