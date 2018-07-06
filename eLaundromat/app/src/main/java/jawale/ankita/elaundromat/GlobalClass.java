package jawale.ankita.elaundromat;

import android.content.Context;
import android.content.SharedPreferences;

public class GlobalClass {
    public static Context context;
    public static String location = "";
    public static String numberrOfClothes = "";
    public static String washType = "Color Wash";
    public static String washSelector = "Quick Wash";
    public static boolean dryerSelected = false;
    public static String dryerType = "";
    public static String specialInstruction = "";
    public static String dryerTime = "";
    public static double washCost = 0.0;
    public static double dryCost = 0.0;
    public static double totalCOst = 0.0;
    public static boolean isLoggedIn = false;

    public static SharedPreferences pref ;

    public static void initSharedPref(Context context){
        pref = context.getSharedPreferences("eLaundromat", 0); // 0 - for private mode
    }

    public static void storeString(String key_name, String value){
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key_name, value);
        editor.commit();
    }

    public static void storeBoolean(String key_name, boolean value){
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(key_name, value);
    }

    public static String retrieveString(String key_name){
        String text = pref.getString(key_name, "");
        return text;
    }

    public static boolean retrieveBoolean(String key_name){
        boolean text = pref.getBoolean(key_name, false);
        return text;
    }

}
