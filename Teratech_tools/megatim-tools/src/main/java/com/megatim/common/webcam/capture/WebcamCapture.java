/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megatim.common.webcam.capture;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * Classe responsable des captures à partir d'une webcam
 * @author DEV
 */
public class WebcamCapture {

    private static Webcam webcam ;

    public WebcamCapture() {
    }
    
    public static void initCameraPanel(JPanel panel, int panelHieght, int panelWidth) throws NoWebcamFoundException{
        Dimension d = new Dimension(320, 240);
        getWebcamDevice().setViewSize(d);
               WebcamPanel webcamPanel = new WebcamPanel(getWebcamDevice());
        webcamPanel.setSize(panelHieght,panelWidth);
        panel.setLayout(new BorderLayout(20, 20));
        panel.add(webcamPanel, BorderLayout.CENTER);
        panel.setVisible(true);
        //return panel ;
    }
    
        public static void initCameraPanel(JPanel panel, int panelHieght, int panelWidth, int index) throws NoWebcamFoundException{
        Dimension d = new Dimension(320, 240);
        getWebcamDevice(index).setViewSize(d);
               WebcamPanel webcamPanel = new WebcamPanel(getWebcamDevice(index));
        webcamPanel.setSize(panelHieght,panelWidth);
        panel.setLayout(new BorderLayout(20, 20));
        panel.add(webcamPanel, BorderLayout.CENTER);
        panel.setVisible(true);
      //  return panel ;
    }
    
    private static Webcam getWebcamDevice() throws NoWebcamFoundException {
        webcam = Webcam.getDefault();
        
        //Si aucune webcam trouvee
        if(webcam == null){
            throw new NoWebcamFoundException("no.webcam.found");
        }
        
        return webcam ;
    }

    private static Webcam getWebcamDevice(int index) throws NoWebcamFoundException,ArrayIndexOutOfBoundsException {
            
        //On recupere la webcam
        webcam = Webcam.getWebcams().get(index);
        
        //Si aucune webcam trouvee
        if(webcam == null){
            throw new NoWebcamFoundException("no.webcam.found");
        }
        
        return webcam ;  
    }

    public static List<String> getAllWebcamConnected(){
        
        //Declarations
        List<Webcam> webcams1 = Webcam.getWebcams();
        List<String> webcamName = new ArrayList<String>();
     
        //On Ajoute toutes les wabcams
        for (Webcam webcam : webcams1) {
            webcamName.add(webcam.getName());
        }
     
     return webcamName;
    }
        
    public static boolean capture(String format, String outputdirectory) throws IOException, NoWebcamFoundException{
        BufferedImage image = getWebcamDevice().getImage();
        if (format!=null && outputdirectory!=null) {
            ImageIO.write(image,format , new File(outputdirectory+"."+format));
            return true;
        }
        else if (outputdirectory!=null) {
            ImageIO.write(image,"jpg" , new File(outputdirectory+".jpg"));
            return true;
        }
        
        else return false;
    }
    
        public static boolean capture(String format, String outputdirectory, int index) throws IOException, NoWebcamFoundException{
        BufferedImage image = getWebcamDevice(index).getImage();
            if (index !=-1) {
                if (format!=null && outputdirectory!=null) {
            ImageIO.write(image,format , new File(outputdirectory+"."+format));
            return true;
        }
        else if (outputdirectory!=null) {
            ImageIO.write(image,"jpg" , new File(outputdirectory+".jpg"));
            return true;
        }
        
        else return false;
            }else return false;
    }
    
    public static void close(int i) throws NoWebcamFoundException{
        if (getWebcamDevice().isOpen()) {
            getWebcamDevice().close();
        }else if (getWebcamDevice(i).isOpen()) {
            getWebcamDevice(i).close();
        }
    }
    
}