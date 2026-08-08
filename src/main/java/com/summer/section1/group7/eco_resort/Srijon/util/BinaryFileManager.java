package com.summer.section1.group7.eco_resort.Srijon.util;

import java.io.*;
import java.util.ArrayList;

public class BinaryFileManager {

    private static final String DATA_DIR = "data";

    static {
        File dir = new File(DATA_DIR);
        if (!dir.exists()) dir.mkdirs();
    }

    @SuppressWarnings("unchecked")
    public static <T> ArrayList<T> loadList(String filename) {
        // try data/ first (writable)
        File file = new File(DATA_DIR, filename);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                Object obj = ois.readObject();
                if (obj instanceof ArrayList) {
                    return (ArrayList<T>) obj;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // fallback: try classpath resource (read-only)
        InputStream is = BinaryFileManager.class.getResourceAsStream("/" + filename);
        if (is != null) {
            try (ObjectInputStream ois = new ObjectInputStream(is)) {
                Object obj = ois.readObject();
                if (obj instanceof ArrayList) {
                    return (ArrayList<T>) obj;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return new ArrayList<>();
    }

    public static <T> void saveList(String filename, ArrayList<T> list) throws IOException {
        File file = new File(DATA_DIR, filename);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(list);
            oos.flush();
        }
    }
}
