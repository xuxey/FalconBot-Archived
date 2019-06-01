package com.xuxe.octaveBot;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class FalconConstants
{
    final public static String BROWNIE_TABLE = "users";
    final public static String TAGS_TABLE = "taglist";
    final public static String MERRIAM_ICON_URL = "https://www.merriam-webster.com/assets/mw/static/app-standalone-images/MW_logo.png";

    public static String getMerriamKey()
    {
        try
        {
            Properties properties = new Properties();
            FileReader fileReader = new FileReader("config.properties");
            properties.load(fileReader);
            MERRIAM_KEY = properties.getProperty("merriamkey");
            fileReader.close();
            properties.clear();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return MERRIAM_KEY;
    }

    private static String MERRIAM_KEY;
}
