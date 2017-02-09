package com.my;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.my.logic.ThumbnailConvertor;

public class Test extends JPanel {

   public void paint(Graphics g) {
      Image img = createImageWithText();
      g.drawImage(img, 20,20,this);
   }

   private Image createImageWithText(){
      BufferedImage bufferedImage = new BufferedImage(200,200,BufferedImage.TYPE_INT_RGB);
      
     
    
      Graphics g = bufferedImage.getGraphics();

      g.drawString("www.tutorialspoint.com", 20,20);
      g.drawString("www.tutorialspoint.com", 20,40);
      g.drawString("www.tutorialspoint.com", 20,60);
      g.drawString("www.tutorialspoint.com", 20,80);
      g.drawString("www.tutorialspoint.com", 20,100);
      
      return bufferedImage;
   }
   
   public static void main(String[] args) {
      JFrame frame = new JFrame();
      frame.getContentPane().add(new Test());

      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(200, 200);
     // frame.setVisible(true);
      
      ThumbnailConvertor thumbnailConvertor = new ThumbnailConvertor();
      try {
		thumbnailConvertor.transform("C:\\Website\\photos\\tmpFiles\\pic.jpg", "C:\\Website\\photos\\tmpFiles\\thumb.jpg", 150, 150, 100);
	} catch (Exception e) {
		e.printStackTrace();
	}
   }
}
