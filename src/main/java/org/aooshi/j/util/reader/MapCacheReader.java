package org.aooshi.j.util.reader;

import java.io.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MapCacheReader {

    private Map<String,String> lineMap = Collections.synchronizedMap(new HashMap<String,String>());
    private String filePath = null;
    private String separator = "";

    /**
     * get file path
     * @return
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * get separator
     * @return
     */
    public String getSeparator() {
        return separator;
    }

    /**
     * get map
     * @return
     */
    public Map<String,String> getMap() {
        return this.lineMap;
    }

    public MapCacheReader(String filepath, String separator)
    {
        this.filePath = filepath;
        this.separator = separator;
        //
        this.load();
    }

    public boolean load()
    {
        boolean result = false;

        File file = new File(this.filePath);
        if (file.exists())
        {
            BufferedReader br = null;
            InputStreamReader isr = null;
            FileInputStream fis = null;
            Map<String,String> lineMap = Collections.synchronizedMap(new HashMap<String,String>());

            try {
                fis = new FileInputStream(file);
                isr = new InputStreamReader(fis,"UTF-8");
                br = new BufferedReader(isr);
                //
                String line = null;
                while ((line = br.readLine()) != null) {
                    if (line != null && line != "") {
                        String[] items = line.split(this.separator);
                        if (items.length == 2) {
                            lineMap.put(items[0].trim(), items[1].trim());
                        }
                    }
                }
                br.close();

                //
                this.lineMap = lineMap;
                result = true;

            } catch (Exception e) {
                try {
                    br.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                try {
                    isr.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                try {
                    br.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }

        return  result;
    }

}
