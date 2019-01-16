package zadaci;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

import java.io.IOException;

public class Zadatak1KreiranjeTabela {

    public static void main(String[] args) {

        ConnectionSource connectionSource = null;

        try {

            connectionSource = new JdbcConnectionSource(Konstante.DATABASE_URL);

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
