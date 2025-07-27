package utils;

public class SystemProperties {
    public static String selenoidHost = System.getProperty("selenoid_host");
    public static String browserName = System.getProperty("browser_name","firefox");
    public static String browserSize = System.getProperty("browser_size","1920x1080");
    public static String browserVersion = System.getProperty("browser_version","124");
}
