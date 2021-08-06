package org.selenium.pom.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.selenium.pom.objects.BillingAddress;

import java.io.IOException;
import java.io.InputStream;

public class JacksonsUtils {

    public static <T> T deserializeJson(String fileName , Class<T> T) throws IOException {
        InputStream is = JacksonsUtils.class.getClassLoader().getResourceAsStream(fileName);
        ObjectMapper objMapper = new ObjectMapper();
        return objMapper.readValue(is,T);

    }
}
