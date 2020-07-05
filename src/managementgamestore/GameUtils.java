/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managementgamestore;

import com.google.gson.JsonArray;
import java.util.ArrayList;

/**
 *
 * @author ivans
 */
public class GameUtils {
    public static String[] JArrayToArray(JsonArray JArr) {
        ArrayList<String> list = new ArrayList<>();
        for(int i = 0; i < JArr.size(); i++) {
            list.add(JArr.get(i).getAsString());
        }
        return list.toArray(new String[list.size()]);
    }
}
