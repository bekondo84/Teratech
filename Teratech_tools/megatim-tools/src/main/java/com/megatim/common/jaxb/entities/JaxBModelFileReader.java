/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megatim.common.jaxb.entities;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author DEV_4
 */
public class JaxBModelFileReader {

    public static Elements readConfigFromFile(String config_file) throws JAXBException {

        JAXBContext jc = JAXBContext.newInstance("com.megatim.common.jaxb.entities");

        Unmarshaller unmarshaller = jc.createUnmarshaller();

        ModelConfig config = (ModelConfig) unmarshaller.unmarshal(new java.io.File(config_file));

        return config.getElements();
    }

    public static Elements readConfigFromFile(java.io.File config_file) throws JAXBException {

        JAXBContext jc = JAXBContext.newInstance("com.megatim.common.jaxb.entities");

        Unmarshaller unmarshaller = jc.createUnmarshaller();

        ModelConfig config = (ModelConfig) unmarshaller.unmarshal(config_file);

        return config.getElements();
    }

    public static Principalscreen readScreenFromFile(java.io.File config_file) throws JAXBException {

        JAXBContext jc = JAXBContext.newInstance("com.megatim.common.jaxb.entities");

        Unmarshaller unmarshaller = jc.createUnmarshaller();

        FrameConfig config = (FrameConfig) unmarshaller.unmarshal(config_file);

        return config.getPrincipalscreen();
    }

    public static Principalscreen readScreenFromFile(String config_file) throws JAXBException {

        JAXBContext jc = JAXBContext.newInstance("com.megatim.common.jaxb.entities");

        Unmarshaller unmarshaller = jc.createUnmarshaller();

        FrameConfig config = (FrameConfig) unmarshaller.unmarshal(new java.io.File(config_file));

        return config.getPrincipalscreen();
    }
}
