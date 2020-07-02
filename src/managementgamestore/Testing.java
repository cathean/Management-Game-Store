/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managementgamestore;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author cathean
 */
public class Testing {
    
    public static void main(String[] args)
    {     
        Image image = null;
        try {
            URL url = new URL("https://static.metacritic.com/images/products/games/9/e4c7dbc585abaa821cfabfd32507b834-98.jpg");
            image = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JFrame frame = new JFrame();
        frame.setSize(236, 306);
        frame.add(new JLabel(new ImageIcon(image)));
        frame.setVisible(true);                 
    }
}
