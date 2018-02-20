/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.core.files;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kerem.core.CommonTools;
import com.kerem.core.FileHelper;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import org.apache.commons.io.IOUtils;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

/**
 *
 * @author Commercial_2
 */
@Path("/resource")
public class UploadFileRSImpl  implements UploadFileRS{

    @Override
    public Response uploadFiletemporal(MultipartFormDataInput input, HttpHeaders headers) {
         //To change body of generated methods, choose Tools | Templates.
        Gson gson =new Gson();
        //Type predType = ;
        List<String> names = new ArrayList<String>();
        if(headers.getRequestHeader("names")!=null){
            names = gson.fromJson(headers.getRequestHeader("names").get(0),new TypeToken<List<String>>(){}.getType());
        }    
        String fileName = "";
//
		Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
		List<InputPart> inputParts = uploadForm.get("resources");
//                System.out.println(UploadFileRSImpl.class.toString()+" ======================== "+inputParts);
                int index = 0 ;
		for (InputPart inputPart : inputParts) {
		 try {
                       MultivaluedMap<String, String> header = inputPart.getHeaders();
			fileName = getFileName(header);
                        //convert the uploaded file to inputstream
			InputStream inputStream = inputPart.getBody(InputStream.class,null);

			byte [] bytes = IOUtils.toByteArray(inputStream);

			if(names!=null&&names.size()>index){
                            //constructs upload file path
                            fileName = FileHelper.getTemporalDirectory()+File.separator+names.get(index);
//                            System.out.println(UploadFileRSImpl.class.toString()+" ========================  ===== "+fileName);                            
                            writeFile(bytes,fileName);                        
                        }			
			index++;
//			System.out.println("Done");
		  } catch (IOException e) {
			 Response.serverError().build();
		  }

		}

		return Response.status(200)
		    .entity("uploadFile is called, Uploaded file name : " + fileName).build();
    }
    
    @Override
    public Response uploadFile(MultipartFormDataInput input,HttpHeaders headers) {
        //To change body of generated methods, choose Tools | Templates.
        Gson gson =new Gson();
        //Type predType = ;
        List<String> names = new ArrayList<String>();
        if(headers.getRequestHeader("names")!=null){
            names = gson.fromJson(headers.getRequestHeader("names").get(0),new TypeToken<List<String>>(){}.getType());
        }    
        String fileName = "";
//
		Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
		List<InputPart> inputParts = uploadForm.get("resources");
//                System.out.println(UploadFileRSImpl.class.toString()+" ======================== "+inputParts);
                int index = 0 ;
		for (InputPart inputPart : inputParts) {
		 try {
                       MultivaluedMap<String, String> header = inputPart.getHeaders();
			fileName = getFileName(header);
                        //convert the uploaded file to inputstream
			InputStream inputStream = inputPart.getBody(InputStream.class,null);

			byte [] bytes = IOUtils.toByteArray(inputStream);

			if(names!=null&&names.size()>index){
//                            System.out.println(UploadFileRSImpl.class.toString()+" ======================== "+bytes+" ===== "+names.get(index));
                            //constructs upload file path
                            fileName = FileHelper.getStaticDirectory()+File.separator+names.get(index);
                            writeFile(bytes,fileName);                        
                        }			
			index++;
//			System.out.println("Done");
		  } catch (IOException e) {
			 Response.serverError().build();
		  }

		}

		return Response.status(200)
		    .entity("uploadFile is called, Uploaded file name : " + fileName).build();
        
    }
    
    @Override
    public Response downloadImageFile(String filename) {
        try {
            //To change body of generated methods, choose Tools | Templates.
            File fichier = new File(FileHelper.getStaticDirectory()+File.separator+filename);
//            System.out.println(UploadFileRSImpl.class.toString()+" ==== "+fichier.getAbsolutePath());
            if(!fichier.exists()||!fichier.isFile()){
                fichier = new File(FileHelper.getStaticDirectory()+File.separator+"avatar.png");
            }
            return CommonTools.getImage(fichier);
        } catch (IOException ex) {
             Response.serverError().build();
        }
        return Response.noContent().build();
    }
    @Override
    public Response downloadImageFile2(String filename, String name) {
        //To change body of generated methods, choose Tools | Templates.
         try {
            //To change body of generated methods, choose Tools | Templates.
            File fichier = new File(FileHelper.getStaticDirectory()+File.separator+filename);
//            System.out.println(UploadFileRSImpl.class.toString()+" ==== "+fichier.getAbsolutePath());
            if(!fichier.exists()||!fichier.isFile()){
                fichier = new File(FileHelper.getStaticDirectory()+File.separator+"avatar.png");
            }
            return CommonTools.getImage(fichier,name);
        } catch (IOException ex) {
             Response.serverError().build();
        }
        return Response.noContent().build();
    }

    
    @Override
    public Response downloadPdfFile(String filename,String name) {
        //To change body of generated methods, choose Tools | Templates.
        try{
                String resourceDir = FileHelper.getStaticDirectory()+File.separator+filename;
                File file = new File(resourceDir);
                if(file.exists()){
                    return CommonTools.getPdf(file,name);
                }else{
                    return Response.noContent().build();
                }//end if(file.exists())
        }catch(Exception ex){
            throw new WebApplicationException(ex, Response.serverError().build());
        }
    }

    @Override
    public Response downloadTextFile(String filename,String name) {
       //To change body of generated methods, choose Tools | Templates.
         try{
                String resourceDir = FileHelper.getStaticDirectory()+File.separator+filename;
                File file = new File(resourceDir);
                if(file.exists()){
                    return CommonTools.getText(file,name);
                }else{
                    return Response.noContent().build();
                }//end if(file.exists())
        }catch(Exception ex){
            throw new WebApplicationException(ex, Response.serverError().build());
        }
    }

    /**
     * 
     * @param filename
     * @param name
     * @return 
     */
    @Override
    public Response downloadFile(String filename, String name) {
        //To change body of generated methods, choose Tools | Templates.
          try{
                String resourceDir = FileHelper.getStaticDirectory()+File.separator+filename;
                File file = new File(resourceDir);
                if(file.exists()){
                    return CommonTools.getStream(file,name);
                }else{
                    return Response.noContent().build();
                }//end if(file.exists())
        }catch(Exception ex){
            throw new WebApplicationException(ex, Response.serverError().build());
        }
    }
   
  
     /**
	 * header sample
	 * {
	 * 	Content-Type=[image/png],
	 * 	Content-Disposition=[form-data; name="file"; filename="filename.extension"]
	 * }
	 **/
	//get uploaded filename, is there a easy way in RESTEasy?
	private String getFileName(MultivaluedMap<String, String> header) {

		String[] contentDisposition = header.getFirst("Content-Disposition").split(";");

		for (String filename : contentDisposition) {
			if ((filename.trim().startsWith("filename"))) {

				String[] name = filename.split("=");

				String finalFileName = name[1].trim().replaceAll("\"", "");
				return finalFileName;
			}
		}
		return "unknown";
	}

	//save to somewhere
        /**
         * 
         * @param content
         * @param filename
         * @throws IOException 
         */
	private void writeFile(byte[] content, String filename) throws IOException {

		File file = new File(filename);

		if (!file.exists()) {
			file.createNewFile();
		}

		FileOutputStream fop = new FileOutputStream(file);

		fop.write(content);
		fop.flush();
		fop.close();

	}

        private void writeFile(InputStream content, String filename) throws IOException {
                int read = 0;
                byte[] bytes = new byte[1024];
		File file = new File(filename);

		if (!file.exists()) {
			file.createNewFile();
		}

		FileOutputStream fop = new FileOutputStream(file);

		while((read=content.read(bytes))!=-1){
                    fop.write(bytes, 0, read);
                }
		fop.flush();
		fop.close();

	}

    @Override
    public void deleteFile(String filename) {
        //To change body of generated methods, choose Tools | Templates.
        File fichier = new File(FileHelper.getStaticDirectory()+File.separator+filename);
//            System.out.println(UploadFileRSImpl.class.toString()+" ==== "+fichier.getAbsolutePath());
        if(fichier.exists()&&fichier.isFile()){
            fichier.delete();
        }
    }

    @Override
    public void deleteTempFile(String filename) {
        //To change body of generated methods, choose Tools | Templates.
        File fichier = new File(FileHelper.getTemporalDirectory()+File.separator+filename);
//            System.out.println(UploadFileRSImpl.class.toString()+" ==== "+fichier.getAbsolutePath());
        if(fichier.exists()&&fichier.isFile()){
            fichier.delete();
        }
    }    
}
