package Utilities;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class FileOperation {
    private  String downloadDir="C:\\Users\\SoftLaptop\\Downloads";

    public  File waitForFileDownload(String fileExtension, int timeoutSeconds) {
        long endTime = System.currentTimeMillis() + (timeoutSeconds * 1000);

        while (System.currentTimeMillis() < endTime) {
            File[] files = new File(downloadDir).listFiles((dir, name) ->
                    name.toLowerCase().endsWith(fileExtension.toLowerCase()));

            if (files != null && files.length > 0) {
                // Return most recent file
                Arrays.sort(files, (f1, f2) ->
                        Long.compare(f2.lastModified(), f1.lastModified()));
                return files[0];
            }
            sleep(1000);
        }
        return null;
    }

    private void sleep(int millis) {
        try { Thread.sleep(millis); }
        catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }

    public void cleanClonedFiles(String fileExtension) throws IOException {
        File downloadFolder = new File(downloadDir);
        File[] files = downloadFolder.listFiles((dir, name) ->
                name.toLowerCase().endsWith( fileExtension.toLowerCase()));

        if (files != null) {
            for (File file : files) {
                FileUtils.forceDelete(file);
            }
        }
    }

    public  File getLastDownloadedFile( String extension) {
        File directory = new File(downloadDir);

        // Get all files with the given extension in the directory
        File[] files = directory.listFiles((dir, name) -> name.endsWith(extension));

        if (files == null || files.length == 0) {
            return null; // No files found
        }

        // Sort files by last modified date, descending
        Arrays.sort(files, Comparator.comparingLong(File::lastModified).reversed());

        // Return the most recent file
        return files[0];
    }

    public  boolean checkFileInZip( String Extention) throws IOException {
        String Path=downloadDir+"\\"+getLastDownloadedFile("zip").getName();
        try (ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(Path))) {
            ZipEntry entry;

            // Iterate through the ZIP entries
            while ((entry = zipInputStream.getNextEntry()) != null) {
                if (entry.getName().endsWith(Extention)) {
                    return true; // File found
                }
            }
        }
        return false; // File not found
    }

}
