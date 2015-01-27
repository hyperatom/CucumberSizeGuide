package sizeguide;

import com.google.gson.Gson;

import java.io.*;
import java.util.Date;

public class FileWriter {

    public static void createGenerationDateFile() {
        String filePath = "./web/";
        String fileName = "generated.json";

        try {
            createFile(filePath, fileName);
            writeFile(filePath, fileName);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void writeFile(String filePath, String fileName)
            throws FileNotFoundException, UnsupportedEncodingException {

        PrintWriter writer = new PrintWriter(filePath + fileName, "UTF-8");
        writer.println(new Gson().toJson(new Date().getTime()));
        writer.close();
    }

    private static Boolean createFile(String filePath, String fileName)
            throws IOException {

        File file = new File(filePath, fileName);
        return file.createNewFile();
    }
}
