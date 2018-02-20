/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megatim.common.export;

import com.megatim.common.jaxb.entities.Fileline;
import com.megatim.common.jaxb.entities.Linecolumn;
import com.megatim.common.jaxb.entities.Temporalfile;
import java.io.*;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.transform.stream.StreamSource;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.milyn.Smooks;
import org.milyn.csv.CSVBinding;
import org.milyn.csv.CSVBindingType;
import org.milyn.csv.CSVReaderConfigurator;
import org.milyn.payload.JavaResult;


/**
 *
 * @author DEV_4
 */
public class ParserHelper {
    
  
    /**
     * Conversion du fichier cvsFile en temporalFile
     * @param file
     * @param cvsFile
     * @param fields
     * @param clazz 
     */
    public static Temporalfile cvsToJAXBParser(File cvsFile , String fields , Class<?> clazz) throws IllegalArgumentException, IllegalAccessException{
        
        //Creation du fichier Temporaire000000000000000
        Temporalfile fichier = new Temporalfile();
        
        //Verification de la validite du fichier cvs
        if(cvsFile==null){
            return fichier;
        }
        
        //verification de la validite de la liste des champs
        if(fields==null||fields.trim().isEmpty()) return fichier;
        //System.out.println(fields);
        //verification de la validite du champs clazz
        if(clazz==null)  return fichier;
//        System.out.println(ParserHelper.class.getSimpleName()+"   *******************************************************  ");
//        int index = 0 ;
        //Creation instance parseur            
        Smooks smooks = new Smooks();
        
        //Configuration de l'instance
        CSVReaderConfigurator config = new CSVReaderConfigurator(fields);
//        config.setQuoteChar('"');
        config.setSeparatorChar(',');
        config.setBinding(new CSVBinding("item", clazz, CSVBindingType.LIST));
        smooks.setReaderConfig(config);
        //Creation d'un conteneur de resultat
        JavaResult result = new JavaResult();
        smooks.filterSource(new StreamSource(cvsFile), result);
        
        //Recuperation de la liste des données
        List items = (List) result.getBean("item");        
       
        System.out.println(" *******************************************************  "+items);
        //Construction du temporalfile
        if(items==null||items.isEmpty()) {
//            return fichier;
            //Verifier que le separateur n'est pas plutôt ;
            config = new CSVReaderConfigurator(fields);
//            config.setQuoteChar('"');
            config.setSeparatorChar(';');
            config.setBinding(new CSVBinding("item", clazz, CSVBindingType.LIST));
            smooks.setReaderConfig(config);
            //Creation d'un conteneur de resultat
            result = new JavaResult();
            smooks.filterSource(new StreamSource(cvsFile), result);

            //Recuperation de la liste des données
            items = (List) result.getBean("item");       
            System.out.println(" *******************************************************  "+items);
            //Verifier de resultat des données
            if(items==null)  return fichier;
            
        }
        System.out.println(" *******************************************************  "+items);
        int index = 0 ;
        
        //Parcours de ligne
        for(Object item : items){
            
            Fileline line = rowToJAXB(item, index);
            
            fichier.getFileline().add(line);
            
            index = index + 1 ;
        }
        
        //System.out.println(items);
        
        return fichier;
    }
    
    /**
     * Conversion d'un fichier XML en Fichier Excel
     * @param file
     * @param excelFile
     */
    public static void jaxbToExcelParser(Temporalfile file , File excelFile) throws IOException, InvalidFormatException{
        
        //Lecture du fichier fichier Original
        Workbook workbook = WorkbookFactory.create(excelFile);
        
        Sheet sheet = workbook.getSheet("Feuil1");
        
        int index = 0 ;
        
        //Traitement des lignes de notre fichiers
        for(Fileline ligne : file.getFileline()){
            
            Row row = sheet.createRow(index);
            
            int cellIndex = 0 ;
            //Creation des colonnes de la ligne            
            for(Linecolumn column : ligne.getLinecolumn()){
                Cell cell = row.createCell(cellIndex);
                if(column.getType()==0)
                              cell.setCellValue(column.getValue());
                else if(column.getType()==1)
                              cell.setCellValue(new Double(column.getValue()));
                else if(column.getType()==2)
                             cell.setCellValue(new Boolean(column.getValue()));
                else if(column.getType()==3)
                             cell.setCellValue(new Date(column.getValue()));
                
                cell.setCellType(getValueType(cell.getCellType()));
                cellIndex++;
            }
        }
        
        
    }
  
  /**
   * Conversion du contenu d'un fichier Excel en Object JAXB
   * @param excepFile
     * @return 
   * @throws IOException
   * @throws InvalidFormatException 
   */
  public static Temporalfile excelToJAXBParser(File excepFile) throws IOException, InvalidFormatException{
      
      //Creation du fichier Temporaire000000000000000
      Temporalfile fichier = new Temporalfile();
      //Creation du  Workbook
        Workbook workbook = WorkbookFactory.create(excepFile);
       
        //Selection de la feuille N°:1
        Sheet sheet = workbook.getSheetAt(0);
        
        //Lecture des données du Fichier
        int index = 0 ;
        
        Row row = sheet.getRow(index);
        
        while(row!=null){
            
            Fileline ligne = rowToJAXB(row , index);
            
            fichier.getFileline().add(ligne);
            
            index = index + 1 ;
            
            row = sheet.getRow(index);
        }
        
        return fichier;
  }
  
  /**
   * Conversion d'un object en FileLine
   * @param row
   * @return 
   */
  private static Fileline rowToJAXB(Object row ,int lineIndex) throws IllegalArgumentException, IllegalAccessException{
      
      Fileline line = new Fileline();
      
      //Si un object null est transmi
      if(row==null)  return null ;
      
      //Liste des champs de l'object
      Field[] fields = row.getClass().getDeclaredFields();
      
      int index = 0 ;
      
      //Construction des colonnes
      for(Field field : fields){
          
          field.setAccessible(true);
          
          Linecolumn column = new Linecolumn();
          
          column.setValue(String.valueOf(field.get(row)));
          
          column.setName(field.getName());
          
          column.setType(EnumJavaType.STRING.ordinal());
          
          column.setRow(lineIndex+1);
            
          column.setColumn(index+1);

          line.getLinecolumn().add(column);            

          index = index+1 ;
      }
      return line ;
  }
  /**
   * Conversion d'une ligne Excel en Ligne du fichier XML
   * @param row
   * @return 
   */
  private static Fileline rowToJAXB(Row row , int lineIndex){
      
      Fileline line = new Fileline();
      
      int index = 0 ;
      
      String value = getCellValue(row.getCell(index));

      while(value!=null){
          
            Linecolumn column = new Linecolumn();
             
            column.setValue(value);
            
            column.setType(getCellType(row.getCell(index)).ordinal());
            
            column.setRow(lineIndex+1);
            
            column.setColumn(index+1);
            
            line.getLinecolumn().add(column);            
            
            index = index+1 ;
            
            value = getCellValue(row.getCell(index));
      }
      
      return line ;
  }
  
  /**0:String
   * 1:Numeric
   * 2:boolean
   * 3:Date
   * 4:Formula
   * 5:BLANK
   * -1:ERROR
   * 
   * @param row
   * @param index
   * @return 
   */
    private static EnumJavaType getCellType(Cell cell){
         //Cell cell = row.getCell(index);
         int cellType = cell.getCellType();
         
         switch(cellType){
             
             case Cell.CELL_TYPE_BLANK : 
                      return EnumJavaType.UNKNOW;
             case Cell.CELL_TYPE_ERROR :
                     return EnumJavaType.UNKNOW;
             case Cell.CELL_TYPE_FORMULA :
                     return EnumJavaType.UNKNOW;
             case Cell.CELL_TYPE_BOOLEAN :
                     return EnumJavaType.BOOLEAN;
             case Cell.CELL_TYPE_NUMERIC :
                     return EnumJavaType.NUMERIC;
             default: return EnumJavaType.STRING;
         }
    }
    
    /**0:String
   * 1:Numeric
   * 2:boolean
   * 3:Formula
   * 4:BLANK
   * -1:ERROR
   * 
   * @param row
   * @param index
   * @return 
   */
    private static int getValueType(int type){
         
        if(type==(EnumJavaType.UNKNOW.ordinal())){
            return Cell.CELL_TYPE_BLANK;
        }else if(type==(EnumJavaType.BOOLEAN.ordinal())){
             return Cell.CELL_TYPE_BOOLEAN;
        }else if(type==(EnumJavaType.DATE.ordinal())){
             return Cell.CELL_TYPE_STRING;
        }else if(type==(EnumJavaType.NUMERIC.ordinal())){
             return Cell.CELL_TYPE_NUMERIC;
        }else if(type==(EnumJavaType.STRING.ordinal())){
             return Cell.CELL_TYPE_STRING;
        }
        return Cell.CELL_TYPE_ERROR;
    }
    
    //public static int getValueType(C) 
    
    /**
     * 
     * @param cell
     * @return 
     */
    private static String getCellValue(Cell cell){
        
        if(cell==null)
                   return null ;
        
        int cellType = cell.getCellType();
         
         switch(cellType){
             
             case Cell.CELL_TYPE_BLANK : 
                              return "";
             case Cell.CELL_TYPE_ERROR :
                     return null;
             case Cell.CELL_TYPE_FORMULA :
                     return "";
             case Cell.CELL_TYPE_BOOLEAN :
                     return ""+cell.getBooleanCellValue();
             case Cell.CELL_TYPE_NUMERIC :                     
                  return getStringRepresentation(cell.getNumericCellValue());
             default: return cell.getStringCellValue();
         }
    }
    
    private static String getStringRepresentation(double value){
        Double dValue = new Double(value);
        Long lvalue = dValue.longValue();
        
        if(lvalue.doubleValue()==value){
            return lvalue.toString();
        }else{
            return dValue.toString();
        }
    }
    
    public static void main(String[] args){
        
        try {
            final String sourceName = "D:\\Classeur1.xlsx";
            
            File file = new File(sourceName);
            
            Temporalfile fichier = excelToJAXBParser(file);
            
             System.out.println("========================================================================================");
            for(Fileline line : fichier.getFileline()){
                for(Linecolumn col :line.getLinecolumn()){
                    System.out.println(col.getValue()+"---- "+col.getType());
                }
                System.out.println("");
            }
            System.out.println("========================================================================================");
        } catch (IOException ex) {
            Logger.getLogger(ParserHelper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidFormatException ex) {
            Logger.getLogger(ParserHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
