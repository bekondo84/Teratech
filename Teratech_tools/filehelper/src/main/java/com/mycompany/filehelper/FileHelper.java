package com.mycompany.filehelper;

/*
 * Copyright 2001-2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.codehaus.plexus.util.FileUtils;

/**
 * Goal which touches a timestamp file.
 *
 * @goal generate
 *
 * @phase process-classes
 */
@Mojo(name = "jilehelper")
public class FileHelper
        extends AbstractMojo {

    
    /**
     * Type operation
     *
     * @parameter
     *
     */
    @Parameter
    private String typeOperation;
    
    /**
     * Location of the directory.
     *
     * @parameter
     *
     */
    @Parameter
    private String source;

    /**
     * Location of the directory.
     *
     * @parameter
     */
    @Parameter
    private String destination;
    
    /**
     * directory name.
     *
     * @parameter
     */
    @Parameter
    private String directoryName;
    
    /**
     * Exclude files name.
     *
     * @parameter
     */
    @Parameter
    private String excludes;


    /**
     *
     * @throws MojoExecutionException
     */
    public void execute(){
        
        //Definition
        File sourceDir = null;
        File destinationDir = null;
        File dirName = null;
        
        if(typeOperation.equalsIgnoreCase("copyDirectory")){
            sourceDir = new File(source);
            destinationDir = new File(destination);
            
            try {
                //On copie
                FileUtils.copyDirectoryStructure(sourceDir, destinationDir);FileUtils.copyDirectory(sourceDir, destinationDir, source, source);
            } catch (IOException ex) {
                Logger.getLogger(FileHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(typeOperation.equalsIgnoreCase("copyFile")){
            sourceDir = new File(source);
            destinationDir = new File(destination);
            
            try {
                //On copie
                FileUtils.copyFile(sourceDir, destinationDir);
            } catch (IOException ex) {
                Logger.getLogger(FileHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(typeOperation.equalsIgnoreCase("createDirectory")){
            dirName = new File(directoryName);
            
            //On Creer un repertoire
            dirName.mkdir();        
        }else if(typeOperation.equalsIgnoreCase("deleteDirectory")){
            dirName = new File(directoryName);
            
            try {
                
                //On Supprime un repertoire
                FileUtils.deleteDirectory(dirName);
            } catch (IOException ex) {
                Logger.getLogger(FileHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(typeOperation.equalsIgnoreCase("deleteSelectedFileByExtension")){
            dirName = new File(directoryName);
                
            for(File file : dirName.listFiles()){

                for(String exclude : excludes.split(",")){

                    if(file.getName().endsWith(exclude)){
                        file.delete();
                        break;
                    }
                }
            } 
        }
    }
}