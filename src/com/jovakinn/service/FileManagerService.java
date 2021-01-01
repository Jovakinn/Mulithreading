package com.jovakinn.service;

import com.jovakinn.core.NewThread;
import java.io.*;
import java.nio.file.Files;

public class FileManagerService extends Thread implements Serializable {

    private static final String MAIN_DIR = System.getProperty("user.dir");
    private static final String SEP = System.getProperty("file.separator");

    private static final String FILES_DIR = getFilesDir();

    private static String getFilesDir() {
        File filePath = new File(MAIN_DIR + SEP + "files");
        if (!filePath.isDirectory()){
            filePath.mkdir();
        }
        return MAIN_DIR + SEP + "files" + SEP;
    }

    public static byte[] readBytes(String fileName){
        File file = new File(FILES_DIR + fileName);
        if (file.exists()){
            try {
                byte[] bytes = Files.readAllBytes(file.toPath());
                return bytes;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public synchronized static void writeBytes(String fileName, byte[] bytes){
        try (FileOutputStream fileOutputStream = new FileOutputStream(FILES_DIR + fileName);
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream)){

            bufferedOutputStream.write(bytes);
            bufferedOutputStream.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void copyFile(String fromFile, String toFile){
        byte[] bytes = FileManagerService.readBytes(fromFile);
        FileManagerService.writeBytes(toFile, bytes);
    }

    // works without threads...

//    public static void writeObject(String fileName, Object newThread) throws NotSerializableException {
//        try (FileOutputStream fileOutputStream = new FileOutputStream(FILES_DIR + fileName);
//            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)){
//
//            objectOutputStream.writeObject(newThread);
//            objectOutputStream.flush();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public static NewThread readObjectBytes (String fileName){
        try (FileInputStream fileInputStream = new FileInputStream(FILES_DIR + fileName);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)){

            NewThread newThread = (NewThread) objectInputStream.readObject();
            return newThread;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void writeText(String fileName, String text, boolean append){
        try (FileWriter fileWriter = new FileWriter(FILES_DIR + fileName, append))
        {
            fileWriter.write(text + "\n");
            fileWriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String readText(String fileName){
        String outText = "";

        String line;
        try (FileReader fileReader = new FileReader(FILES_DIR + fileName);
             BufferedReader bufferedReader = new BufferedReader(fileReader)){
            while ((line = bufferedReader.readLine()) != null){
                outText += line + "\n";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return outText;
    }
}

