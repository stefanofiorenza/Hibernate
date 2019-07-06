package com.knits.jpa.orm.demos;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.sql.Blob;
import java.sql.Clob;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialClob;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import com.knits.jpa.common.utils.BasicDao;
import com.knits.jpa.common.utils.DemoCommonUtil;
import com.knits.jpa.orm.model.EntityWithAdvancedTypes;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Demo02AdvancedTypes {

	private static final String PU_NAME="JPA-210-TypesMapping-PU";
	
	private static final String RESOURCE_NAME_BINARY="/large.jpg";
	private static final String RESOURCE_NAME_CHARS="/textMockData.json";

	private static final String DESTINATION_PATH_IMG="C:/Temp/JPA200/JPA200-DemoFile.jpg";
	private static final String DESTINATION_PATH_JSON="C:/Temp/JPA200/JPA200-DemoChars.json";
	private static final String DESTINATION_PATH_STRING="C:/Temp/JPA200/JPA200-DemoString.txt";
	
	
	private static final String TEST_STRING ="Some test data";
	
	public static void main(String[] args) {

			try {
				
			BasicDao<EntityWithAdvancedTypes> dao = DemoCommonUtil.createBasicDao(EntityWithAdvancedTypes.class,PU_NAME);
			
			EntityWithAdvancedTypes entity = mockEntity();
			Long savedId =dao.save(entity);
			
			EntityWithAdvancedTypes foundInDb= dao.findById(EntityWithAdvancedTypes.class, savedId);
			
			log.info("Found IN DB: {}",foundInDb.toString());
			
			saveToFile(foundInDb.getBinaryField(),DESTINATION_PATH_STRING);		
			saveToFile(foundInDb.getBlobField().getBinaryStream(),DESTINATION_PATH_IMG);
			saveToFile(foundInDb.getClobField().getCharacterStream(),DESTINATION_PATH_JSON);
			
			DemoCommonUtil.closeFactory();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	private static EntityWithAdvancedTypes mockEntity() throws Exception{
		
		
		EntityWithAdvancedTypes entity = new EntityWithAdvancedTypes();
		entity.setBinaryField(TEST_STRING.getBytes(Charsets.UTF_8));
		entity.setBlobField(dataAsBlob());
		entity.setClobField(dataAsClob());
		return entity;
	}
	
	private static Clob dataAsClob() throws Exception{
		 InputStream inputStream = readAsIsFromClasspath(RESOURCE_NAME_CHARS);
		 char [] dataAsChars =IOUtils.toCharArray(inputStream,Charsets.UTF_8);
		 return new SerialClob(dataAsChars);
	}


	private static Blob dataAsBlob () throws Exception{
		  InputStream inputStream = readAsIsFromClasspath(RESOURCE_NAME_BINARY);
		  byte [] dataAsBytes=IOUtils.toByteArray(inputStream);
		  return new SerialBlob(dataAsBytes);
	}
	
	private static void saveToFile (InputStream is, String destinationPath) throws Exception {
		byte[] data =IOUtils.toByteArray(is);
		FileUtils.writeByteArrayToFile(new File(destinationPath), data);
	}
	
	private static void saveToFile(byte [] data, String destinationPath) throws Exception {
		FileUtils.writeByteArrayToFile(new File(destinationPath), data);
	}
	
	
	private static void saveToFile (Reader charAsStream, String destinationPath) throws Exception {
		InputStream is = IOUtils.toInputStream(IOUtils.toString(charAsStream), Charsets.UTF_8);		
		saveToFile(is,destinationPath);
	}
	
	
	 private static InputStream readAsIsFromClasspath(String path) {
		 InputStream inputStream = Demo02AdvancedTypes.class.getResourceAsStream(path);
		  if(inputStream==null) {
			  throw new IllegalStateException("File "+path+" Not available");
		  }
		  return inputStream;
	 }
	 
	 
	

}
