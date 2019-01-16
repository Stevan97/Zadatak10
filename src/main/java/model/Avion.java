package model;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "avion")
public class Avion {

public static final String POLJE_OZNAKA="oznaka";
public static final String POLJE_RASPON="raspon";

@DatabaseField(generatedId = true)
    private int id;

@DatabaseField(columnName = "oznaka",canBeNull = false)
    private String oznaka;

@DatabaseField(columnName = "raspon",canBeNull = false)
    private int raspon;

@ForeignCollectionField(foreignFieldName = "avion")
    ForeignCollection<Roba> robe;

public Avion() {

}

    public Avion(String oznaka, int raspon) {
        this.oznaka = oznaka;
        this.raspon = raspon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOznaka() {
        return oznaka;
    }

    public void setOznaka(String oznaka) {
        this.oznaka = oznaka;
    }

    public int getRaspon() {
        return raspon;
    }

    public void setRaspon(int raspon) {
        this.raspon = raspon;
    }

    public ForeignCollection<Roba> getRobe() {
        return robe;
    }

    public void setRobe(ForeignCollection<Roba> robe) {
        this.robe = robe;

}

    @Override
    public String toString() {
        return "Avion{" +
                "id=" + id +
                ", oznaka='" + oznaka + '\'' +
                ", raspon=" + raspon +
                '}';
    }
}
