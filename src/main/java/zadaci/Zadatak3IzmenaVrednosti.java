package zadaci;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import model.Avion;
import model.Roba;

import java.io.IOException;
import java.util.List;

public class Zadatak3IzmenaVrednosti {

    static Dao<Avion,Integer> avionDao;
    static Dao<Roba,Integer> robaDao;

    public static void main(String[] args) {

        ConnectionSource connectionSource = null;

        try {
            connectionSource = new JdbcConnectionSource(Konstante.DATABASE_URL);

            avionDao = DaoManager.createDao(connectionSource,Avion.class);
            robaDao = DaoManager.createDao(connectionSource,Roba.class);

            List<Roba> listaRoba = robaDao.queryForAll();
            System.out.println("Prikaz svih roba: ");
            for (Roba r: listaRoba) {
                System.out.println(r);
            }

            List<Roba> zaIzmenu = robaDao.queryForEq(Roba.POLJE_OPIS,"Plasticna stolica");
            zaIzmenu.get(0).setOpis("Drvena stolica");
            robaDao.update(zaIzmenu.get(0));

            listaRoba = robaDao.queryForAll();
            System.out.println("Prikaz POSLE IZMENE roba: ");
            for (Roba r: listaRoba) {
                System.out.println(r);
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connectionSource != null) {
                try {
                    connectionSource.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

}

