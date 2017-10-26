package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import Model.Model;
import Model.Queue;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JTextField;

public class GUI extends JFrame implements Observer {

	private JPanel contentPane;
	private Model theModel;
	private Tile[][] tiles;
	private JLabel [] queueTiles;
	private JLabel lblTimeDesc;
	private JLabel lblMovesDesc;
	private JLabel lblTimeLeft ;
	private JLabel lblMovesLeft;
	private JLabel lblScoreDesc;
	private JLabel lblScore;
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
	public GUI(Model model) {
		super();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setBounds(100, 100, 640, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		this.theModel=model;
		this.theModel.addObserver(this);
		
		JPanel headerPanel = new JPanel();
		headerPanel.setBounds(10, 11, 604, 29);
		contentPane.add(headerPanel);
		headerPanel.setLayout(null);
		
		lblTimeDesc = new JLabel("Time Left (s)");
		lblTimeDesc.setHorizontalAlignment(SwingConstants.CENTER);
		lblTimeDesc.setFont(new Font("Chiller", Font.BOLD | Font.ITALIC, 17));
		lblTimeDesc.setBounds(10, 0, 96, 29);
		headerPanel.add(lblTimeDesc);
		
		lblMovesDesc = new JLabel("Moves Left");
		lblMovesDesc.setHorizontalAlignment(SwingConstants.CENTER);
		lblMovesDesc.setFont(new Font("Chiller", Font.BOLD | Font.ITALIC, 17));
		lblMovesDesc.setBounds(212, 0, 96, 29);
		headerPanel.add(lblMovesDesc);
		
		lblTimeLeft = new JLabel("--");
		lblTimeLeft.setHorizontalAlignment(SwingConstants.CENTER);
		lblTimeLeft.setFont(new Font("Chiller", Font.BOLD | Font.ITALIC, 17));
		lblTimeLeft.setBounds(106, 0, 96, 29);
		lblTimeLeft.setBorder(new LineBorder(Color.BLACK));
		headerPanel.add(lblTimeLeft);
		
		lblMovesLeft = new JLabel("--");
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
		
		lblScoreDesc = new JLabel("Score:");
		lblScoreDesc.setHorizontalAlignment(SwingConstants.CENTER);
		lblScoreDesc.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 17));
		lblScoreDesc.setBounds(6, 6, 65, 29);
		optionPanel.add(lblScoreDesc);
		
		lblScore = new JLabel("0");
		lblScore.setHorizontalAlignment(SwingConstants.CENTER);
		lblScore.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 17));
		lblScore.setBounds(82, 6, 65, 29);
		optionPanel.add(lblScore);
		
		JPanel queueTilesPanel = new JPanel();
		queueTilesPanel.setBounds(0, 138, 153, 241);
		sidePanel.add(queueTilesPanel);
		
		queueTilesPanel.setLayout(new GridLayout(5, 1, 0, 0));
		
		Queue<Integer> modelQueueTiles = theModel.getTilesQueue();
		this.queueTiles = new JLabel[5];
		for(int i=0;i<5;i++) {
			queueTiles[i]=new JLabel(modelQueueTiles.getElement(i).toString());
			queueTiles[i].setHorizontalAlignment(SwingConstants.CENTER);
			queueTiles[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
			queueTilesPanel.add(queueTiles[i]);
		}
		//System.out.println(modelQueueTiles.getElement(0).toString());
		
		
		JPanel boardPanel = new JPanel();
		boardPanel.setBounds(10, 40, 441, 390);
		
		boardPanel.setLayout(new GridLayout(9, 9));
		Integer [][]modelTiles=theModel.getTiles();
		tiles = new Tile[9][9];
		for(int i=0;i<9;i++) {
			for(int j=0;j<9;j++) {
				tiles[i][j]=new Tile(i,j);
				tiles[i][j].addActionListener(new TilesClickListener());
			}
		}
		for(int i=1;i<8;i++){
			for(int j=1;j<8;j++){
				tiles[i][j].setText(modelTiles[i][j].toString());
				tiles[i][j].setEnabled(false);
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

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		//first update the tiles from the model
		Integer [][]modelTiles=theModel.getTiles();
		for(int i=0;i<tiles.length;i++){
			for(int j=0;j<tiles[i].length;j++){
				String text=modelTiles[i][j].toString();
				if(text.equals("-1")){
					tiles[i][j].setText("");
					tiles[i][j].setEnabled(true);
				}
				else{
					tiles[i][j].setText(modelTiles[i][j].toString());
				}
			}
		}
		//update queue of tiles from the model
		Queue<Integer> modelQueueTiles = theModel.getTilesQueue();
		for(int i=0;i<queueTiles.length;i++){
			queueTiles[i].setText(modelQueueTiles.getElement(i).toString());
		}
		//update the score board
		int modelScore=theModel.getScore();
		lblScore.setText(Integer.toString(modelScore));
		//update the moves left
		int modelMovesLeft=theModel.getRemainingMoves();
		lblMovesLeft.setText(Integer.toString(modelMovesLeft));
	}
	
	public class TilesClickListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Tile t=(Tile)e.getSource();
			theModel.updateTilesinBoard(t.getCoord());
			//System.out.println("Tile clicked");
		}
		
	}
}
