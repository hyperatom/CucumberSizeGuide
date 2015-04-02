package sizeguide;

import com.google.gson.Gson;

import java.io.*;
import java.util.Date;

public class FileWriter {

    public static void createGenerationDateFile() {
        try {
            writeGenerationTimeFile(Config.GENERATED_DATE_FILE);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void writeFile(String filePath, String contents)
            throws IOException
    {

        createFile(filePath);

        PrintWriter writer = new PrintWriter(filePath, "UTF-8");
        writer.println(contents);
        writer.close();
    }

    private static void writeGenerationTimeFile(String filePath)
            throws IOException
    {
        writeFile(filePath, new Gson().toJson(new Date().getTime()));
    }

    private static Boolean createFile(String filePath)
            throws IOException
    {
        File file = new File(filePath);
        return file.createNewFile();
    }
}
