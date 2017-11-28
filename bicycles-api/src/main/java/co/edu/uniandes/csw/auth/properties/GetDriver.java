/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.auth.properties;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 *
 * @author Asistente
 */
public class GetDriver {

    private static String chrome = "chromeDriv.zip";
    
    public GetDriver() {
    }

    public static void main(String[] args) throws IOException {

        String s;
        String prop = System.getProperty("user.dir");
        // using the Runtime exec method:

        Process p;
        p = Runtime.getRuntime().exec("setx AUTH0_PROPERTIES " + prop + "\\src\\main\\java\\co\\edu\\uniandes\\csw\\auth\\properties\\auth0.properties");

        BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));

        BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

        // read the output from the command
        Logger.getAnonymousLogger().info("Here is the standard output of the command:\n");
        while ((s = stdInput.readLine()) != null) {
            Logger.getAnonymousLogger().info(s);
        }
        // read any errors from the attempted command
        Logger.getAnonymousLogger().info("Here is the standard error of the command (if any):\n");
        while ((s = stdError.readLine()) != null) {
            Logger.getAnonymousLogger().info(s);
        }

        URL website = new URL("https://chromedriver.storage.googleapis.com/2.30/chromedriver_win32.zip");
        ReadableByteChannel rbc = Channels.newChannel(website.openStream());
        FileOutputStream fos = new FileOutputStream(chrome);
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        fos.close();
        String zipFilePath = chrome;
        String destDir = "drivers";

        try {
            unzip(zipFilePath, destDir);

        } catch (Exception ex) {
            Logger.getAnonymousLogger().info(ex.getMessage());
            Logger.getAnonymousLogger().info("file already exist");
        }
        File zip = new File(chrome);
        Files.delete(zip.toPath());
    }

    private static void unzip(String zipFilePath, String destDir) {
        File dir = new File(destDir);
        // create output directory if it doesn't exist
        if (!dir.exists()) {
            dir.mkdirs();
        } else {
            Logger.getAnonymousLogger().info("Error: directorio existe");
        }
        FileInputStream fis;
        //buffer for read and write data to file
        byte[] buffer = new byte[1024];
        try {
            fis = new FileInputStream(zipFilePath);
            ZipInputStream zis = new ZipInputStream(fis);
            ZipEntry ze = zis.getNextEntry();
            while (ze != null) {
                String fileName = ze.getName();
                File newFile = new File(destDir + File.separator + fileName);
                Logger.getAnonymousLogger().info("Unzipping to " + newFile.getAbsolutePath());
                //create directories for sub directories in zip
                new File(newFile.getParent()).mkdirs();
                FileOutputStream fos = new FileOutputStream(newFile);
                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
                fos.close();
                //close this ZipEntry
                zis.closeEntry();
                ze = zis.getNextEntry();
            }
            //close last ZipEntry
            zis.closeEntry();
            zis.close();
            fis.close();
        } catch (IOException e) {
            Logger.getAnonymousLogger().info(e.getMessage());
        }

    }

}
