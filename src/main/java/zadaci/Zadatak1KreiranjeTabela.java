package zadaci;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import model.Avion;
import model.Roba;

import java.io.IOException;

public class Zadatak1KreiranjeTabela {

    static Dao<Avion,Integer> avionDao;
    static Dao<Roba,Integer> robaDao;

    public static void main(String[] args) {

        ConnectionSource connectionSource = null;

        try {

            connectionSource = new JdbcConnectionSource(Konstante.DATABASE_URL);

            TableUtils.dropTable(connectionSource,Roba.class,true);
            TableUtils.dropTable(connectionSource,Avion.class,true);

            TableUtils.createTable(connectionSource,Avion.class);
            TableUtils.createTable(connectionSource,Roba.class);



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
