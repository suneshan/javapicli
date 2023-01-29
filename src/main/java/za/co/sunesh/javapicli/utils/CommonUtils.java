package za.co.sunesh.javapicli.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import za.co.sunesh.javapicli.pojos.Address;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static java.nio.charset.StandardCharsets.UTF_8;

public class CommonUtils {

    public static String readFileFromResource(String filename) {
        CommonUtils commonUtils = new CommonUtils();
        InputStream inputStream = commonUtils.getFileFromResourceAsStream(filename);
        return new BufferedReader(new InputStreamReader(inputStream))
                .lines().collect(Collectors.joining("\n"));
    }

    private InputStream getFileFromResourceAsStream(String fileName) {
        // The class loader that loaded the class
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
        // the stream holding the file content
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return inputStream;
        }
    }

    public static List<Address> jsonStringToAddressList(String data) {
        Address[] address = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            address = mapper.readValue(data, Address[].class);
        } catch (JsonProcessingException jpe) {
            throw new RuntimeException("unable to convert Json to Object: "+jpe.getMessage());
        }
        return Arrays.asList(address);
    }

    public static boolean isInteger(String s) {
        if(s == null || s.isEmpty()) return false;
        for(int i = 0; i < s.length(); i++) {
            if(Character.digit(s.charAt(i), 10) < 0) return false;
        }
        return true;
    }
}
