package fr.ldnr.nmb.zooproject;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class VeterinaireTest {

    @Test
    public void testVeterinaires(){
        System.out.println("**** Vétérinaires : ");
        String nom1 = " fredeguande ";
        String debut1 = nom1.substring(5,8);
        System.out.println(debut1);
        String nom1WithoutSpace =  nom1.trim();
        String nom1WithFirstLetterUpper = nom1WithoutSpace.substring(0,1).toUpperCase()
                                                            + nom1WithoutSpace.substring(1);
        System.out.println("**** Transformed string : "+ nom1WithFirstLetterUpper);
    }

    @Test
    public void testOverture(){
        System.out.println("**** Ouverture : ");
        Calendar maintenant = Calendar.getInstance();
        Calendar lundiMatin = Calendar.getInstance();
        lundiMatin.set(2022, 7,29, 8, 0,0 );
        lundiMatin.add(Calendar.HOUR,1);//ouverture a 9h
        System.out.println("Lundi Matin : "+ lundiMatin.getTime());
        System.out.println(lundiMatin.get(Calendar.HOUR_OF_DAY));

        long timeToOpen = lundiMatin.getTimeInMillis() - maintenant.getTimeInMillis();
        int hourToOpen = (int)(timeToOpen / (3_600_000));
        int minutesToOpen = (int)((timeToOpen % (3_600_000))/60000);
        System.out.println("Temps avant ouverture : "+hourToOpen+" h et "+minutesToOpen+" mn");
    }

    @Test
    public void testExamen(){
        System.out.println("**** Examens");
        ArrayList<String> subjects = new ArrayList<String>();
        subjects.add("Bob Merlan");
        subjects.add("Anne Chevre");
        subjects.add("Carl Lion");
        System.out.println("Il y a "+subjects.size()+ "sujets.");
        System.out.println("Premier sujets :  "+subjects.get(0));
        int count =0;
        for (String subject : subjects){
            count ++;
            System.out.println("n°"+ count +" : "+ subject);
        }

        HashMap<String, Integer> ages = new HashMap<String, Integer>();
        ages.put("Anne Chevre", 6);
        ages.put("Bob Merlan", 2);
        ages.put("Carl Lion", 6);
        System.out.println("Age de Anne : "+ ages.get("Anne Chevre"));
    }
}
