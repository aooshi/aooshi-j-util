package org.aooshi.j.util.reader;

import java.io.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class LineCacheReader {
    private Set<String> lineSet = Collections.synchronizedSet(new HashSet<String>());
    private String filePath = null;

    /**
     * get file path
     * @return
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * get line set
     * @return
     */
    public Set<String> getSet() {
        return this.lineSet;
    }

    public LineCacheReader(String filepath)
    {
        this.filePath = filepath;

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
            Set<String> lineSet = Collections.synchronizedSet(new HashSet<String>());

            try {
                fis = new FileInputStream(file);
                isr = new InputStreamReader(fis,"UTF-8");
                br = new BufferedReader(isr);
                //
                String line = null;
                while ((line = br.readLine()) != null) {
                    if (line != null && line != "") {
                        lineSet.add(line.trim());
                    }
                }
                br.close();

                //
                this.lineSet = lineSet;
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

        return result;
    }

}
