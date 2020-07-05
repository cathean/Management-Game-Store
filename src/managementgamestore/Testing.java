/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managementgamestore;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import java.awt.Image;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import static org.mentaregex.Regex.match;

/**
 *
 * @author cathean
 */
public class Testing {
    public DBManager dbm = DBManager.getInstance();
    
    
    public static int hashCode(String s) {
        return s != null ? s.hashCode() * 3 : 0;  // PRIME = 31 or another prime number.
    }
    
    public static int hashCode2(String string) {
        return Hashing.murmur3_32()
                .newHasher()
                .putString(string, Charsets.UTF_8)
                .hash().asInt();
    } 
    
    public static void main(String[] args)
    {     
        System.out.println(hashCode2("The Elder Scroll Syrim: Ubisoft"));
        
        Image image = null;
        try {
            URL url = new URL("https://static.metacritic.com/images/products/games/9/e4c7dbc585abaa821cfabfd32507b834-98.jpg");
            image = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
            
        String[] arr = match("Tomb Raider", "/(\\b[a-zA-Z])/g" );
        String matchesName = String.join("", match("Tomb Raider", "/(\\b[a-zA-Z])/g" ));
        System.out.println(hashCode2("SotTR"));
    

        JFrame frame = new JFrame();
        frame.setSize(236, 306);
        frame.add(new JLabel(new ImageIcon(image)));
        frame.setVisible(true);                 
    }
}
