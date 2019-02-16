/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zadanie5_3;

import java.awt.geom.Point2D;
import static java.lang.Thread.sleep;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author lesze
 */
public class Lotnisko {
    private String kod;
    private Point2D.Double polozenie;
    private Lock l1, l2;
   
    public Lotnisko(String kod, Point2D.Double polozenie) {
        this.kod = kod;
        this.polozenie = polozenie;
        this.l1 = new ReentrantLock();
        this.l2 = new ReentrantLock();
    }
    
    public String getKod()
    {
        return kod;
    }
    
    public Point2D.Double getPolozenie()
    {
        return polozenie;
    }
    
    public void Start(Samolot s) throws InterruptedException {
        s.setStatus("Samolot " + s.getKod() + " oczekuje na start z " + this.kod);
        sleep(2000);
        l1.lock();
        try {
            s.setStatus("Samolot " + s.getKod() + " startuje z " + this.kod);
            sleep(2000);
        } finally {
            l1.unlock();
        }
    }
    
    public void Ladowanie(Samolot s) throws InterruptedException {
        s.setStatus("Samolot " + s.getKod() + " oczekuje na lądowanie na " + this.kod);
        sleep(2000);
        l1.lock();
        try {
            s.setStatus("Samolot " + s.getKod() + " ląduje na " + this.kod);
            sleep(2000);
        } finally {
            l1.unlock();
        }
    }
    
    public void Tankowanie(Samolot s) throws InterruptedException {
        s.setStatus("Samolot " + s.getKod() + " oczekuje na tankowanie na " + this.kod);
        sleep(2000);
        l2.lock();
        try {
            s.setStatus("Samolot " + s.getKod() + " tankuje na " + this.kod);
            sleep(2000);
        } finally {
            l2.unlock();
        }
    }
}
