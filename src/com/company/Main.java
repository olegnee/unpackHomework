package com.company;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        openZip("C:\\Games\\savegames/zip_output.zip", "C:\\Games\\savegames");
        System.out.println(openProgress("C:\\Games\\savegames/save.dat"));


    }
    static void openZip(String path, String unpackPath) throws FileNotFoundException {
        try (ZipInputStream zis = new ZipInputStream(new FileInputStream("zip_output.zip"))) {
            ZipEntry entry;
            String name;
            while ((entry = zis.getNextEntry()) != null) {
                name = entry.getName();
                FileOutputStream fout = new FileOutputStream(name);
                for(int i = zis.read(); i != -1; i = zis.read()) {
                    fout.write(i);
                }
                fout.flush();
                zis.closeEntry();
                fout.close();
            }

        } catch (IOException e) {
            e.getMessage();
        }
    }
    static GameProgress openProgress(String path) {
        GameProgress somePlayer = null;
        try (FileInputStream fis = new FileInputStream(path); ObjectInputStream ois = new ObjectInputStream(fis)) {
            somePlayer = (GameProgress) ois.readObject();
        } catch (IOException e) {
            e.getMessage();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return somePlayer;
    }
}
