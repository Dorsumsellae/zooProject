package fr.ldnr.nmb.zooproject;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test //indique un test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void verifieZoo(){
        int a = 2000;
        double b = 1_000.000_001;
        boolean c = true;
        String d = "Hello";
        System.out.println(d+" , il y a "+a+" animaux dans notre zoo");
        if (a==2000){
            System.out.println(a);
        }else {

        }
        String e = "1234";
        a = Integer.parseInt(e); //conversion de string to int
        int count = 0;

        while(a>0) {
            count +=1;
            System.out.println("*****" + count);
            a -= 200;
        }
        System.out.println(a);

        for(int i = 0; i<3; i++){
            System.out.println(i);
        }
        System.out.println("****** Aire = "+calculeTaille(25,25)+ " m^2");
        System.out.println("****** Volume  = "+calculeTaille(25,25,25)+ " m^3");
        Poisson p1 = new Poisson("Bob","Merlan");
    }

    public double calculeTaille(double longueur, double largeur, double hauteur){
        return longueur*largeur*hauteur;
    }

    public double calculeTaille(double longueur, double largeur){
        return longueur*largeur;
    }

    public class Poisson{
        public String nom, espece;
        public Poisson(String nom, String espece){
            this.nom = nom;
            this.espece = espece;
        }
        public void afficher(){
            System.out.println(nom+ " ("+espece+")");
        }
    }
}