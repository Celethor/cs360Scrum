package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JTextField;

public class GUI extends JFrame {

	private JPanel contentPane;
	private JTable queueTable;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	/**
	 * Create the frame.
	 */
	public GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setBounds(100, 100, 640, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JPanel headerPanel = new JPanel();
		headerPanel.setBounds(10, 11, 604, 29);
		contentPane.add(headerPanel);
		headerPanel.setLayout(null);
		
		JLabel lblTimeDesc = new JLabel("Time Left (s)");
		lblTimeDesc.setHorizontalAlignment(SwingConstants.CENTER);
		lblTimeDesc.setFont(new Font("Chiller", Font.BOLD | Font.ITALIC, 17));
		lblTimeDesc.setBounds(10, 0, 96, 29);
		headerPanel.add(lblTimeDesc);
		
		JLabel lblMovesDesc = new JLabel("Moves Left");
		lblMovesDesc.setHorizontalAlignment(SwingConstants.CENTER);
		lblMovesDesc.setFont(new Font("Chiller", Font.BOLD | Font.ITALIC, 17));
		lblMovesDesc.setBounds(212, 0, 96, 29);
		headerPanel.add(lblMovesDesc);
		
		JLabel lblTimeLeft = new JLabel("--");
		lblTimeLeft.setHorizontalAlignment(SwingConstants.CENTER);
		lblTimeLeft.setFont(new Font("Chiller", Font.BOLD | Font.ITALIC, 17));
		lblTimeLeft.setBounds(106, 0, 96, 29);
		lblTimeLeft.setBorder(new LineBorder(Color.BLACK));
		headerPanel.add(lblTimeLeft);
		
		JLabel lblMovesLeft = new JLabel("--");
		lblMovesLeft.setFont(new Font("Chiller", Font.BOLD | Font.ITALIC, 17));
		lblMovesLeft.setHorizontalAlignment(SwingConstants.CENTER);
		lblMovesLeft.setBounds(308, 0, 96, 29);
		lblMovesLeft.setBorder(new LineBorder(Color.BLACK));
		headerPanel.add(lblMovesLeft);
		
		JPanel sidePanel = new JPanel();
		sidePanel.setBounds(461, 40, 153, 390);
		contentPane.add(sidePanel);
		sidePanel.setLayout(null);
		
		JPanel optionPanel = new JPanel();
		optionPanel.setBounds(0, 6, 153, 126);
		sidePanel.add(optionPanel);
		optionPanel.setLayout(null);
		
		JLabel lblScore = new JLabel("Score:");
		lblScore.setHorizontalAlignment(SwingConstants.CENTER);
		lblScore.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 17));
		lblScore.setBounds(6, 6, 65, 29);
		optionPanel.add(lblScore);
		
		JLabel label = new JLabel("0");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 17));
		label.setBounds(82, 6, 65, 29);
		optionPanel.add(label);
		
		JPanel queueTilesPanel = new JPanel();
		queueTilesPanel.setBounds(0, 138, 153, 241);
		sidePanel.add(queueTilesPanel);
		
		queueTable = new JTable();
		queueTilesPanel.add(queueTable);
		
		JPanel boardPanel = new JPanel();
		boardPanel.setBounds(10, 40, 441, 390);
		
		boardPanel.setLayout(new GridLayout(9, 9));
		
		JButton[][] tiles = new JButton[9][9];
		for(int i=0;i<9;i++) {
			for(int j=0;j<9;j++) {
				tiles[i][j]=new JButton();
			}
		}
		//add all buttons to the boardPanel
		for(int i=0;i<9;i++) {
			for(int j=0;j<9;j++) {
				boardPanel.add(tiles[i][j]);
			}
		}
		contentPane.add(boardPanel);
		setVisible(true);
	}
}
