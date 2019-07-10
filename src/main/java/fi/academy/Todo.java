package fi.academy;

import java.sql.Date;

public class Todo {
    private int id;
    private String tehtava;
    private String vastuuhenkilo;
    //private Date aloituspaiva;

    public Todo() {
    }

    public Todo (String tehtava, String vastuuhenkilo) {
        this.tehtava = tehtava;
        this.vastuuhenkilo = vastuuhenkilo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTehtava() {
        return tehtava;
    }

    public void setTehtava(String tehtava) {
        this.tehtava = tehtava;
    }

    public String getVastuuhenkilo() {
        return vastuuhenkilo;
    }

    public void setVastuuhenkilo(String vastuuhenkilo) {
        this.vastuuhenkilo = vastuuhenkilo;
    }

//    public Date getAloituspaiva() {
//        return aloituspaiva;
//    }
//
//    public void setAloituspaiva(Date aloituspaiva) {
//        this.aloituspaiva = aloituspaiva;
//    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", tehtava='" + tehtava + '\'' +
                ", vastuuhenkilo='" + vastuuhenkilo + '\'' +
                '}';
    }
}

