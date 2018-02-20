/*
 * Cette classe permete de gerer les fetes
 * 
 */
package com.megatim.common.fetes;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author user
 */
public class FiestEngine {
    
    //Recence toutes les fetes
    private static Map<String,String> LIST_FIESTS = new HashMap<String,String>();

    /**
     * Cette methode permet de charger toutes jour de fetes
     */
    private static void loadAllFiests(){
        
        //Les images des jours de fetes
        String feteDeFinAnnee = "0.png";
        String feteDeNoel = "1.png";
        String feteDeNouvelAn = "2.png";
        String feteDeLaJeunesse = "3.png";
        String feteDeLaStValentin = "4.png";
        String feteDeLaFemme = "5.png";
        
        //On ajoute les jours de fetes
        LIST_FIESTS.put("22/12", feteDeNoel);
        LIST_FIESTS.put("23/12", feteDeNoel);
        LIST_FIESTS.put("24/12", feteDeNoel);
        LIST_FIESTS.put("25/12", feteDeNoel);
        LIST_FIESTS.put("28/12", feteDeFinAnnee);
        LIST_FIESTS.put("29/12", feteDeFinAnnee);
        LIST_FIESTS.put("30/12", feteDeFinAnnee);
        LIST_FIESTS.put("31/12", feteDeFinAnnee);
        LIST_FIESTS.put("01/01", feteDeNouvelAn);
        LIST_FIESTS.put("02/01", feteDeNouvelAn);
        LIST_FIESTS.put("03/01", feteDeNouvelAn);
        LIST_FIESTS.put("04/01", feteDeNouvelAn);
        LIST_FIESTS.put("05/01", feteDeNouvelAn);
        LIST_FIESTS.put("06/01", feteDeNouvelAn);
        LIST_FIESTS.put("07/01", feteDeNouvelAn);
        LIST_FIESTS.put("08/02", feteDeLaJeunesse);
        LIST_FIESTS.put("09/02", feteDeLaJeunesse);
        LIST_FIESTS.put("10/02", feteDeLaJeunesse);
        LIST_FIESTS.put("11/02", feteDeLaJeunesse);
        LIST_FIESTS.put("12/02", feteDeLaStValentin);
        LIST_FIESTS.put("13/02", feteDeLaStValentin);
        LIST_FIESTS.put("14/02", feteDeLaStValentin);
        LIST_FIESTS.put("05/03", feteDeLaFemme);
        LIST_FIESTS.put("06/03", feteDeLaFemme);
        LIST_FIESTS.put("07/03", feteDeLaFemme);
        LIST_FIESTS.put("08/03", feteDeLaFemme);
    }
    
    /**
     * Cette methode teste si une date, est une date de fête 
     * @param date
     * @return 
     */
    public static boolean isFiestDay(Date date){
        SimpleDateFormat dateCourante = new SimpleDateFormat("dd/MM");
        String shortDate = dateCourante.format(new Date());
        
        //On charge toutes les dates de fetes, si c'est pas encore le cas
        if(LIST_FIESTS.isEmpty()){
            loadAllFiests();
        }
        
        //On teste si la date passee, est une date de fete
        if(LIST_FIESTS.get(shortDate) != null){
            return true;
        }else{
            return false;
        }
    }
    
    /**
     * Cette methode permet de retourner le message concernant le jour de fete
     * @param date
     * @return 
     */
    public static String getImageName(Date date){
        SimpleDateFormat dateCourante = new SimpleDateFormat("dd/MM");
        String shortDate = dateCourante.format(new Date());
        
        //On charge toutes les dates de fetes, si c'est pas encore le cas
        if(LIST_FIESTS.isEmpty()){
            loadAllFiests();
        }
        
        //On teste d'abord si le date passee, est une date fete
        if(isFiestDay(date)){
            return LIST_FIESTS.get(shortDate);
        }else{
            return "";
        }
    }
}
