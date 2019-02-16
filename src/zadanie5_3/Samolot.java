/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zadanie5_3;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lesze
 */
public class Samolot extends Thread {
    private int kod;
    private String status;
    private double odleglosc, predkosc;
    private Lotnisko l1, l2;
    
    public Samolot(int kod, Lotnisko l1, Lotnisko l2){
        this.kod = kod;
        this.l1 = l1;
        this.l2 = l2;
        odleglosc = l1.getPolozenie().distance(l2.getPolozenie());
        predkosc = 10;
    }
    
    public int getKod() {
        return kod;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    
    public void run() {
        while(true) {
            //start
            try { 
                l1.Start(this);
            } catch (InterruptedException ex) {
                Logger.getLogger(Samolot.class.getName()).log(Level.SEVERE, null, ex);
            }

            //lot
            while(odleglosc > 0) {
                try {
                    setStatus("Samolot "+ kod + " leci z " + l1.getKod() + " do " + l2.getKod());
                    sleep(1000);
                    odleglosc = odleglosc - predkosc;
                } catch (InterruptedException ex) {
                    Logger.getLogger(Samolot.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            odleglosc = l1.getPolozenie().distance(l2.getPolozenie());

            //ladowanie
            try { 
                l2.Ladowanie(this);
            } catch (InterruptedException ex) {
                Logger.getLogger(Samolot.class.getName()).log(Level.SEVERE, null, ex);
            }

            //tankowanie
            try { 
                l2.Tankowanie(this);
            } catch (InterruptedException ex) {
                Logger.getLogger(Samolot.class.getName()).log(Level.SEVERE, null, ex);
            }

            //start
            try { 
                l2.Start(this);
            } catch (InterruptedException ex) {
                Logger.getLogger(Samolot.class.getName()).log(Level.SEVERE, null, ex);
            }

            //lot
            while(odleglosc > 0) {
                try {
                    setStatus("Samolot "+ kod + " leci z " + l2.getKod() + " do " + l1.getKod());
                    sleep(1000);
                    odleglosc = odleglosc - predkosc;
                } catch (InterruptedException ex) {
                    Logger.getLogger(Samolot.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            odleglosc = l1.getPolozenie().distance(l2.getPolozenie());

            //ladowanie
            try { 
                l1.Ladowanie(this);
            } catch (InterruptedException ex) {
                Logger.getLogger(Samolot.class.getName()).log(Level.SEVERE, null, ex);
            }

            //tankowanie
            try { 
                l1.Tankowanie(this);
            } catch (InterruptedException ex) {
                Logger.getLogger(Samolot.class.getName()).log(Level.SEVERE, null, ex);
            }            
        }

    }
   
}

