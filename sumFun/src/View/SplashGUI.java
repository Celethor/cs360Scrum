package View;

import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.sun.glass.ui.Window;

import Model.Model;
import View.GUI;

import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JTextArea;
import java.awt.FlowLayout;
import java.awt.Dimension;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class SplashGUI extends JFrame {
	
	JPanel contentPane;
	Container headerLabel;
	
	public SplashGUI() {
		setTitle("Sum Fun");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(654, 412);
		//getContentPane().setLayout(null);
		
		JPanel contentPanel = new JPanel();
		contentPanel.setOpaque(false);
		contentPanel.setBounds(0, 0, 632, 363);
		
		contentPanel.setLayout(null);
		contentPanel.setVisible(true);
		
		JLabel lblSumFun = new JLabel("Sum Fun");
		lblSumFun.setFont(new Font("Poor Richard", Font.BOLD, 50));
		lblSumFun.setBounds(15, 225, 190, 95);
		contentPanel.add(lblSumFun);
		
		JButton btnPlayTimed = new JButton("Play Timed");
		btnPlayTimed.setBounds(362, 291, 127, 29);
		contentPanel.add(btnPlayTimed);
		
		JButton btnPlayUntimed = new JButton("Play Untimed");
		btnPlayUntimed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				new GUI(new Model("untimed"));
				setVisible(false);	
			}
		});
		btnPlayUntimed.setBounds(362, 238, 127, 29);
		contentPanel.add(btnPlayUntimed);
		
		JLabel backgroundLabel = new JLabel("New label");
		backgroundLabel.setBounds(0, 0, 633, 359);
		backgroundLabel.setVisible(true);
		contentPanel.add(backgroundLabel);
		backgroundLabel.setIcon(new ImageIcon(SplashGUI.class.getResource("grade-1-addition-and-subtraction-1-638.jpg")));
		
		this.setVisible(true);
		
		this.setVisible(true);
		
		this.add(contentPanel);  //this.getContentPane().add(contentPanel);
		
	}

public static void main(String[] args) {
  new SplashGUI();
}
}
