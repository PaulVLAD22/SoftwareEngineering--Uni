package com.university.ilock.utils;

public class Constants {
    public static double MinimumConfidenceLevel = 0.5;
    public static int wrongInputTimeout = 30; //seconds
    public static int wrongInputCallThePolice = 9;

    public static String CallThePoliceMail = "Security Alert!! <br/>" +
            "There were too many failed attempts to unlock the device. <br/>" +
            "The authorities were called and the device has been blocked. <br/>" +
            "Contact support for more information at support@ilock.com";

}
