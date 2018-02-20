/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megatim.common.export;

import com.megatim.common.annotationsprocessor.ValidateAndFillBeans;
import com.megatim.common.clients.Messages;
import com.megatim.common.clients.Notification;
import com.megatim.common.clients.NotificationType;
import com.megatim.common.utilities.PrincipalFrame;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Ewane Sama A
 * @since 06/10/2016
 * @param <T>
 */
public class ExcelExportation<T> {
    
    private List<String> columnNames =null;

    public List<String> getColumnNames() {
        return columnNames;
    }

    public void setColumnNames(List<String> columnNames) {
        this.columnNames = columnNames;
    }

    public ExcelExportation() {
    }

    /**
     * This method export all the fields in the object class to an excel file.
     * That is .xlsx
     *
     * @param values : The list of object to be exported
     * @param outpitFileName : The name of the output file
     * @throws FileNotFoundException
     * @throws IllegalAccessException
     */
    public void exportToExcel2013(List<T> values, String outpitFileName) throws FileNotFoundException, IllegalAccessException {

        if (outpitFileName == null) {
            throw new ExportationException("exportation.exportToExcel1.outputFileName.nullPointerException");
        } else if (values.isEmpty()) {
            throw new ExportationException("exportation.exportToExcel1.values.nullPointerException");
        } else {
            //Blank workbook       
            XSSFWorkbook workbook = new XSSFWorkbook();

            //Create a blank sheet
            XSSFSheet sheet = createXSSFSheet(workbook);

            //      createHeader(sheet, attributeNames);
            int rowcount = 0;
            createHeader(sheet, getColumnNames()) ; 

            for (T t : values) {
                Row row = sheet.createRow(++rowcount);
                writeCellValues(t, row);

            }
            FileOutputStream outputStream = new FileOutputStream(outpitFileName.concat(".xlsx"));
            try {
                workbook.write(outputStream);
            } catch (IOException ex) {
                ex.getMessage();
            }

        }

    }

    /**
     * This method export all the fields in the object class to an excel file.
     * That is .xls
     *
     * @param values : The list of object to be exported
     * @param outpitFileName : The name of the output file
     * @throws FileNotFoundException
     * @throws IllegalAccessException
     */
    public void exportToExcel2007(List<T> values, String outpitFileName) throws FileNotFoundException, IllegalAccessException {

        if (outpitFileName == null) {
            throw new ExportationException("exportation.exportToExcel1.outputFileName.nullPointerException");
        } else if (values.isEmpty()) {
            throw new ExportationException("exportation.exportToExcel1.values.nullPointerException");
        } else {
            //Blank workbook       
            HSSFWorkbook workbook = new HSSFWorkbook();

            //Create a blank sheet
            HSSFSheet sheet = createHSSFSheet(workbook);

            //      createHeader(sheet, attributeNames);
            int rowcount = 0;
            createHeader(sheet, getColumnNames()) ; 

            for (T t : values) {
                Row row = sheet.createRow(++rowcount);
                writeCellValues(t, row);

            }
            FileOutputStream outputStream = new FileOutputStream(outpitFileName.concat(".xlsx"));
            try {
                workbook.write(outputStream);
            } catch (IOException ex) {
                ex.getMessage();
            }

        }

    }

    /**
     * This method creates the all the Cells with the data to be exported
     *
     * @param _source
     * @param row
     * @throws IllegalAccessException
     */
    private static void writeCellValues(Object _source, Row row) throws IllegalAccessException {
        if (_source != null && row != null) {
            //getting all fields in the source class
            Field[] sourceFieldNames = ValidateAndFillBeans.getObjectDeclaredFields(_source.getClass());
            Cell cell;
            //creating the cell index
            int index = 0;
            //accessing all fields and retrieving the values
            for (Field field : sourceFieldNames) {
                //getting the fields name
                String fieldName = field.getName();
                //making the fields accessible for used
                field.setAccessible(true);
                //Testing the field type so as to set the cell type to that of the field
                createCell(field, row, _source, index);
                index++;
                //end of for
            }
            //end of if
        } else {
            throw new ExportationException("exportation.writeCellValues.parameter.nullPointerException");
        }

    }

    /**
     * Creates the different cells with thier values
     *
     * @param field
     * @param row
     * @param _source
     * @param index
     * @throws IllegalAccessException
     */
    private static void createCell(Field field, Row row, Object _source, int index) throws IllegalAccessException {
        if (field == null || row == null || _source == null) {
            throw new ExportationException("exportation.createCell.parameter.nullPointerException");
        } else {
            Cell cell;
            //making the fields accessible for used
            field.setAccessible(true);

            //Testing the field type so as to set the cell type to that of the field
            if (field.getType().equals(String.class)) {

                //creating the cell and assigning a value to it
                cell = row.createCell(index);
                cell.setCellValue((String) field.get(_source));
            } else if (field.getType().equals(Double.class) /**
                     * || field.getType().equals(double.class)*
                     */
                    ) {
                //creating the cell and assigning a value to it
                cell = row.createCell(index);
                cell.setCellValue((Double) field.get(_source));
            } else if (field.getType().equals(Integer.class) || field.getType().equals(int.class)) {
                //creating the cell and assigning a value to it
                cell = row.createCell(index);
                cell.setCellValue((Integer) field.get(_source));
            } else if (field.getType().equals(Date.class) || field.getType().equals(Calendar.class)) {
                //creating the cell and assigning a value to it
                cell = row.createCell(index);
                cell.setCellValue((String.valueOf(field.get(_source))));

            } else if (field.getType().equals(Long.class) || field.getType().equals(long.class)) {
                //creating the cell and assigning a value to it
                cell = row.createCell(index);
                cell.setCellValue((Long) field.get(_source));

            } else if (field.getType().equals(Short.class) || field.getType().equals(short.class)) {
                //creating the cell and assigning a value to it
                cell = row.createCell(index);
                cell.setCellValue((Short) field.get(_source));

            } else {
                //creating the cell and assigning a value to it
                cell = row.createCell(index);
                cell.setCellValue(String.valueOf(field.get(_source)));
            }
            //end of else
        }
    }

    /**
     * This method creates an excel sheet
     *
     * @param workbook
     * @return : Returns a createdsheet
     */
    private XSSFSheet createXSSFSheet(XSSFWorkbook workbook) {
        //Create a blank sheet
        XSSFSheet sheet = workbook.createSheet("Sheet 1");
        return sheet;
    }

    /**
     *
     * @param workbook
     * @return
     */
    private HSSFSheet createHSSFSheet(HSSFWorkbook workbook) {
        //Create a blank sheet
        HSSFSheet sheet = workbook.createSheet("Sheet 1");
        return sheet;
    }

    /**
     * This method export all the fields in the object class to an excel file.
     * That is .xls
     *
     * @param values : The list of object to be exported
     * @param outpitFileName : The name of the output file
     * @param attributeName : The list of fields to be exported
     * @throws FileNotFoundException
     * @throws IllegalAccessException
     */
    public void exportToExcel2007(List<T> values, String outpitFileName, Map<String, String> attributeName) throws FileNotFoundException, IllegalAccessException {

        if (outpitFileName == null) {
           Messages.Messages(PrincipalFrame.principalScreen, true, NotificationType.ERROR, "Nom du Fichier vide", "Entrer le nom du fichier","");
         //   throw new ExportationException("exportation.exportToExcel.outputdirectory.nullPointerException");
        } else if (attributeName.isEmpty()) {
             Messages.Messages(PrincipalFrame.principalScreen, true, NotificationType.ERROR, "Aucune colonne n'a été passé", "Renseignez les colonnes","");
        //    throw new ExportationException("exportation.exportToExcel.attributeName.nullPointerException");
        } else if (values.isEmpty()) {
            Messages.Messages(PrincipalFrame.principalScreen, true, NotificationType.ERROR, "Données inexistantes", "Aucune Valeur n'existe dans la BD","");
//            throw new ExportationException("exportation.exportToExcel.values.nullPointerException");
        } else {
            String extension = null;

            //Blank workbook
            HSSFWorkbook workbook = new HSSFWorkbook();

            //Create a blank sheet
            HSSFSheet sheet = createHSSFSheet(workbook);
            extension = ".xls";

            int rowcount = 0;
            createHeader(sheet, getColumnNames());

            for (T t : values) {
                Row row = sheet.createRow(++rowcount);
                writeCellValues(t, row, attributeName);

            }
            FileOutputStream outputStream = new FileOutputStream(outpitFileName.concat(extension));
            try {
                workbook.write(outputStream);
            } catch (IOException ex) {
                ex.getMessage();
            }
        }
    }

    /**
     * This method export all the fields in the object class to an excel file.
     * That is .xlsx
     *
     * @param values : The list of object to be exported
     * @param outpitFileName : The name of the output file
     * @param attributeName : The list of fields to be exported
     * @throws FileNotFoundException
     * @throws IllegalAccessException
     */
    public void exportToExcel2013(List<T> values, String outpitFileName, Map<String, String> attributeName) throws FileNotFoundException, IllegalAccessException {

        if (outpitFileName == null) {
           Messages.Messages(PrincipalFrame.principalScreen, true, NotificationType.ERROR, "Nom du Fichier vide", "Entrer le nom du fichier","");
         //   throw new ExportationException("exportation.exportToExcel.outputdirectory.nullPointerException");
        } else if (attributeName.isEmpty()) {
             Messages.Messages(PrincipalFrame.principalScreen, true, NotificationType.ERROR, "Aucune colonne n'a été passé", "Renseignez les colonnes","");
        //    throw new ExportationException("exportation.exportToExcel.attributeName.nullPointerException");
        } else if (values.isEmpty()) {
             Messages.Messages(PrincipalFrame.principalScreen, true, NotificationType.ERROR, "Données inexistantes", "Aucune Valeur n'existe dans la BD","");
//            throw new ExportationException("exportation.exportToExcel.values.nullPointerException");
        } else {
            String extension = null;

            //Blank workbook
            XSSFWorkbook workbook = new XSSFWorkbook();

            //Create a blank sheet
            XSSFSheet sheet = createXSSFSheet(workbook);
            extension = ".xlsx";
            
            int rowcount = 0;
             createHeader(sheet, getColumnNames()) ;       
             
            for (T t : values) {
                Row row = sheet.createRow(++rowcount);
             
                writeCellValues(t, row, attributeName);

            }
            FileOutputStream outputStream = new FileOutputStream(outpitFileName.concat(extension));
            try {
                workbook.write(outputStream);
            } catch (IOException ex) {
                ex.getMessage();
            }
        }
    }
    
    

    /**
     * This method writes all the values from the _source object with the
     * specific row and the map ofall the fields to be exported
     *
     * @param _source
     * @param row
     * @param _fieldNames
     * @throws IllegalAccessException
     */
    private static void writeCellValues(Object _source, Row row, Map<String, String> _fieldNames) throws IllegalAccessException {
      //  System.out.println("outside ========");
        if (_source != null || row != null || _fieldNames != null) {
   
            //getting all declared fields in _source Object
            Field[] _fields = ValidateAndFillBeans.getObjectDeclaredFields(_source.getClass()); /**_source.getClass().getDeclaredFields();  **/
            int index = 0;
            
            //accessing all declared Fields
            for (Field _field : _fields) {
                //obtaining fields name
                String fieldName = _field.getName();

                //making sure the fieldName is not empty
                if (fieldName != null) {
                    if (_fieldNames.get(fieldName) == (fieldName)) {

                        createCell(_field, row, _source, index);
                        index++;
                        //end of if to compare
                    } else {
                     
                    }
                    //end of fieldName check
                } else {
      
                }

                //end of for loop
            }

            //end of if
        } else {
            throw new ExportationException("exportation.writeCellValues.parameter.nullPointerException");
            //end of else
        }
    }

    private void createHeader(Sheet sheet, List<String> columnNames) {

        if (sheet == null) {
            throw new ExportationException("exportation.createHeader.parameter.nullPointerException");
            //end of if
        } else if (columnNames == null) {

        } else {

            CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
            Font font = sheet.getWorkbook().createFont();
            font.setBoldweight((short) 20);
            font.setFontHeightInPoints((short) 16);
            cellStyle.setFont(font);

            Row row = sheet.createRow(0);
            Cell cell;
            for (int i = 0; i < columnNames.size(); i++) {
                cell = row.createCell(i);
                cell.setCellStyle(cellStyle);
                cell.setCellValue(columnNames.get(i));
            }
            int index = 0;
            //end of else
        }

    }
    
    /**
     *
     * @param values
     * @param outputFileName
     * @param ee
     * @throws java.lang.IllegalAccessException
     * @throws java.io.FileNotFoundException
     */
    public void exportToCSVORText(List<T> values, String outputFileName, ExportationExtention ee, Map<String, String> attributeName) throws IllegalAccessException, FileNotFoundException{
        if (values != null || outputFileName != null || attributeName != null) {
            FileOutputStream fos = null;
            String extention;
            if (ee.equals(ee.TXT)) {
                extention = ".txt";
                fos = new FileOutputStream(outputFileName+extention);
            }
            else{
                extention = ".csv";
                fos = new FileOutputStream(outputFileName+extention);
            }
                  
            StringBuffer data = new StringBuffer();
            for (T value : values) {
                writeToCSV(value, data, fos, attributeName);
   //             System.out.println("====== ========== =========");
   //             System.out.println(value);
            }
        }
        else{
            Messages.Messages(PrincipalFrame.principalScreen, true, NotificationType.ERROR, "Erreur d'exportation", "Aucune Valeur n'est passée .");
          //  throw new ExportationException("exportation.exportToOthers.parameters.nullPointerException");
        }
        
    }
    
    /**
     * 
     * @param _source
     * @param data
     * @param fos
     * @throws IllegalAccessException 
     */
    private void writeToCSV(Object _source, StringBuffer data, FileOutputStream fos, Map<String, String> attributes) throws IllegalAccessException{
        if (_source != null && fos != null && !attributes.isEmpty()) {
            try {
                String separator =",";
                Field[] _fields = ValidateAndFillBeans.getObjectDeclaredFields(_source.getClass());;
                
                for (Field _field : _fields) {
                    String fieldName = _field.getName();
                    _field.setAccessible(true);
                    if (!fieldName.isEmpty()) {
                        _field.setAccessible(true);
                          String v = attributes.get(fieldName);
                          if (v != null) {
                              if (v.equals(fieldName)) {
                                
//                                  System.out.println("=========== =========== ========= ============= =========");
 //                                 System.out.println(String.valueOf(_field.get(_source)+separator));

                                data.append(String.valueOf(_field.get(_source)+separator));
                    }else{ }
                        }
                      
                    }
                    
                    
                }
                data.append("\n");
                fos.write(data.toString().getBytes());
            } catch (IOException ex) {
                Logger.getLogger(ExcelExportation.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{}
        
    }

    
}