package com.coding.challenge.contact.list;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.File;
import java.io.IOException;
import java.net.URL;

@ExtendWith(SpringExtension.class)
public class CommonUnitTestHelper {

    ObjectMapper mapper= new ObjectMapper();
    protected <T> T loadFromFile(String fileNameWithPath, Class<T> pojoClass){
        T resultObject= null;
        try{
            resultObject= mapper.readValue(loadFile(fileNameWithPath), pojoClass);
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  resultObject;
    }

    private File loadFile (String fileNameWithPattern){
        try{
            ClassLoader classLoader= getClass().getClassLoader();
            URL url= classLoader.getResource(fileNameWithPattern);
            return new File(url.getFile());
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
