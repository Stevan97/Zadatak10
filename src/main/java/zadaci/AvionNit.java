package zadaci;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import model.Avion;
import model.Roba;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AvionNit extends Thread {

    static Dao<Avion,Integer> avionDao;
    static Dao<Roba,Integer> robaDao;

    public static boolean dozvoljeno = true;

    public static Object sinhr = new Object();

    Avion avion = null;

    public AvionNit(Avion avion) {
        this.avion = avion;
    }

    protected void provera() {

        System.out.println("Pocinje provera opreme za Avion: " + this.avion.getId());
        try {
            sleep(0 +(int)Math.random() * 2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected void proveraPiste() {
        boolean isDozvoljeno;

        do {
            System.out.println("Avion je spreman i ceka dozvolu da poleti " + this.avion.getId());
            synchronized (sinhr) {
                if (dozvoljeno) {
                    dozvoljeno = false;
                    isDozvoljeno = true;
                } else {
                    isDozvoljeno = false;
                }
            }


            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } while (!isDozvoljeno);
    }

    protected void poleti() {

        System.out.println("Avion izlazi na pistu i polece" + this.avion.getId());


        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Avion je poleteo" + avion.getId());

        synchronized (sinhr) {
            dozvoljeno = true;
        }

    }

    public void run() {
        provera();
        proveraPiste();
        poleti();
    }

    public static void main(String[] args) {

        ConnectionSource connectionSource = null;

        try {

            connectionSource = new JdbcConnectionSource(Konstante.DATABASE_URL);

            avionDao = DaoManager.createDao(connectionSource,Avion.class);
            robaDao = DaoManager.createDao(connectionSource, Roba.class);

            ArrayList<Thread> listaThrds = new ArrayList<>();
            List<Avion> avioni = avionDao.queryForAll();
            for (Avion a: avioni) {
                Thread t = (new AvionNit(a));
                listaThrds.add(t);
                t.start();
            }

            try {
                for (Thread t: listaThrds) {
                    t.join();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Svi avionu su poleteli");

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
