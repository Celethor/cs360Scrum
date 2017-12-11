package view;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Game;

public class SplashGui extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel contentPane;
	Container headerLabel;
	
	public SplashGui() {
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
		btnPlayTimed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new Gui(Game.getGame("timed"));
				setVisible(false);	
			}
		});
		
		JButton btnPlayUntimed = new JButton("Play Untimed");
		btnPlayUntimed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new Gui(Game.getGame("untimed"));
				setVisible(false);	
			}
		});
		btnPlayUntimed.setBounds(362, 238, 127, 29);
		contentPanel.add(btnPlayUntimed);
		
		JLabel backgroundLabel = new JLabel("New label");
		backgroundLabel.setBounds(0, 0, 633, 359);
		backgroundLabel.setVisible(true);
		contentPanel.add(backgroundLabel);
		backgroundLabel.setIcon(new ImageIcon(SplashGui.class.getResource("grade-1-addition-and-subtraction-1-638.jpg")));
		
		this.setVisible(true);
		
		this.setVisible(true);
		
		this.add(contentPanel);  //this.getContentPane().add(contentPanel);
		
	}
}
