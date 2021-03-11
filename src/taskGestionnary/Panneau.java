package taskGestionnary;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;
 
public class Panneau extends JPanel { 
	  public void paintComponent(Graphics g){
		    Font font = new Font("Courier", Font.BOLD, 20);
		    g.setFont(font); 
		    g.setColor(Color.black); 
		    g.drawString("Task Gestionary", 92,48);       
	  }
}