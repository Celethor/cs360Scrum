package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import model.Game;
import model.Queue;

public class Gui extends JFrame implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 482262672798928690L;
	private JPanel contentPane;
	private Game theGame;
	private Tile[][] tiles;
	private JLabel [] queueTiles;
	private JLabel lblDesc;
	private JLabel lblLeft;
	private JLabel lblScoreDesc;
	private JLabel lblScore;
	private JLabel lblGameStatus;
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
	public Gui(Game game) {
		super();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setBounds(100, 100, 640, 480);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		this.theGame = game;
		this.theGame.addObserver(this);
		
		JPanel headerPanel = new JPanel();
		headerPanel.setBackground(Color.BLACK);
		headerPanel.setBounds(10, 11, 604, 29);
		contentPane.add(headerPanel);
		headerPanel.setLayout(null);
		
		lblDesc = new JLabel("Moves Left : ");
		lblDesc.setBackground(Color.BLACK);
		lblDesc.setForeground(Color.WHITE);
		lblDesc.setOpaque(true);
		lblDesc.setHorizontalAlignment(SwingConstants.CENTER);
		lblDesc.setFont(new Font("Chiller", Font.BOLD | Font.ITALIC, 20));
		lblDesc.setBounds(212, 0, 96, 29);
		headerPanel.add(lblDesc);
		
		if(theGame.getGameType().equals("untimed")){
			lblLeft = new JLabel("50");
		} else{
			lblLeft=new JLabel("3:00");
		}
		lblLeft.setBackground(Color.BLACK);
		lblLeft.setForeground(Color.RED);
		lblLeft.setOpaque(true);
		lblLeft.setFont(new Font("Chiller", Font.BOLD | Font.ITALIC, 20));
		lblLeft.setHorizontalAlignment(SwingConstants.CENTER);
		lblLeft.setBounds(308, 0, 96, 29);
		lblLeft.setBorder(new LineBorder(Color.BLACK));
		headerPanel.add(lblLeft);
		
		
		//System.out.println(modelQueueTiles.getElement(0).toString());
		
		
		JPanel boardPanel = new JPanel();
		boardPanel.setBounds(10, 40, 441, 390);
		
		boardPanel.setLayout(new GridLayout(9, 9));
		Integer [][]modelTiles= theGame.getTiles();
		tiles = new Tile[9][9];
		for(int i=0;i<9;i++) {
			for(int j=0;j<9;j++) {
				tiles[i][j]=new Tile(i,j);
				tiles[i][j].setOpaque(true);
				tiles[i][j].setForeground(Color.white);
				tiles[i][j].setFocusable(false);
				tiles[i][j].addActionListener(new TilesClickListener());
				tiles[i][j].addMouseListener(new MouseActionTiles());
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
		
		JPanel sidePanel = new JPanel();
		sidePanel.setBackground(Color.BLACK);
		sidePanel.setBounds(461, 40, 153, 390);
		contentPane.add(sidePanel);
		sidePanel.setLayout(null);
		
		JPanel optionPanel = new JPanel();
		optionPanel.setBackground(Color.BLACK);
		optionPanel.setBounds(0, 6, 153, 126);
		sidePanel.add(optionPanel);
		optionPanel.setLayout(null);
		
		lblScoreDesc = new JLabel("Score:");
		lblScoreDesc.setBackground(Color.BLACK);
		lblScoreDesc.setForeground(Color.BLUE);
		lblScoreDesc.setOpaque(true);
		lblScoreDesc.setHorizontalAlignment(SwingConstants.CENTER);
		lblScoreDesc.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 17));
		lblScoreDesc.setBounds(6, 6, 65, 29);
		optionPanel.add(lblScoreDesc);
		
		lblScore = new JLabel("0");
		lblScore.setBackground(Color.BLACK);
		lblScore.setForeground(Color.YELLOW);
		lblScore.setOpaque(true);
		lblScore.setHorizontalAlignment(SwingConstants.CENTER);
		lblScore.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 17));
		lblScore.setBounds(82, 6, 65, 29);
		optionPanel.add(lblScore);
		
		lblGameStatus = new JLabel("Game in Progress");
		lblGameStatus.setBackground(Color.BLACK);
		lblGameStatus.setForeground(new Color(255, 140, 0));
		lblGameStatus.setOpaque(true);
		lblGameStatus.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblGameStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lblGameStatus.setBounds(10, 68, 133, 29);
		optionPanel.add(lblGameStatus);
		
		JPanel queueTilesPanel = new JPanel();
		queueTilesPanel.setForeground(Color.WHITE);
		queueTilesPanel.setBackground(Color.BLACK);
		queueTilesPanel.setBounds(0, 138, 153, 241);
		sidePanel.add(queueTilesPanel);
		Queue<Integer> modelQueueTiles = theGame.getTilesQueue();
		this.queueTiles = new JLabel[5];
		for(int i=0;i<5;i++) {
			queueTiles[i]=new JLabel(modelQueueTiles.getElement(i).toString());
			queueTiles[i].setHorizontalAlignment(SwingConstants.CENTER);
			queueTiles[i].setBorder(BorderFactory.createLineBorder(Color.YELLOW));
			queueTiles[i].setForeground(Color.CYAN);
			queueTiles[i].setBackground(Color.black);
			queueTilesPanel.add(queueTiles[i]);
		}
		
		queueTilesPanel.setLayout(new GridLayout(5, 1, 0, 0));
		contentPane.add(boardPanel);
		setVisible(true);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		
		boolean gameOver= theGame.isGameOver();
		boolean gameWon= theGame.isWon();
		String gameType=theGame.getGameType();
		if(gameType.equals("timed")) {
			;
		}
		//update the remaining time
		lblLeft.setText(theGame.getRemainingTime());
		
		//first update the tiles from the model
		Integer [][]modelTiles= theGame.getTiles();
		for(int i=0;i<tiles.length;i++){
			for(int j=0;j<tiles[i].length;j++){
				String text=modelTiles[i][j].toString();
				if(text.equals("-1")){
					tiles[i][j].setText("");
					tiles[i][j].setEnabled(true);
				}
				else{
					tiles[i][j].setText(modelTiles[i][j].toString());
					tiles[i][j].setEnabled(false);
				}
			}
		}
		//update queue of tiles from the model
		Queue<Integer> modelQueueTiles = theGame.getTilesQueue();
		if(gameWon||gameOver){
			for(int i=0;i<queueTiles.length-1;i++){
				queueTiles[i].setText(modelQueueTiles.getElement(i).toString());
			}
			queueTiles[4].setText("");
		}
		else{
			//Queue<Integer> modelQueueTiles = theGame.getTilesQueue();
			for(int i=0;i<queueTiles.length;i++){
			queueTiles[i].setText(modelQueueTiles.getElement(i).toString());
			}
		}
		//update the score board
		int modelScore= theGame.getScore();
		lblScore.setText(Integer.toString(modelScore));
		if(gameType.equals("untimed")){
			//update the moves left
			int modelMovesLeft= theGame.getRemainingMoves();
			lblLeft.setText(Integer.toString(modelMovesLeft));
		}
		
		if(gameOver){
			for(int i=0;i<tiles.length;i++){
				for(int j=0;j<tiles[i].length;j++){
					tiles[i][j].setEnabled(false);
				}
			}
			lblGameStatus.setText("Game Over! Loser!");
			lblGameStatus.setForeground(Color.RED);
			return;
		}
		else if(gameWon){
			for(int i=0;i<tiles.length;i++){
				for(int j=0;j<tiles[i].length;j++){
					tiles[i][j].setEnabled(false);
				}
			}
			lblGameStatus.setText("Game Won! Legend!");
			lblGameStatus.setForeground(Color.GREEN);
			return;
		}
	}
	
	public class TilesClickListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			Tile t=(Tile)e.getSource();
			theGame.updateTilesinBoard(t.getCoord());
			//System.out.println("Tile clicked");
		}
		
	}
	public class MouseActionTiles implements MouseListener{

		public void mouseClicked(MouseEvent arg0) {
		
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			
			Tile t=(Tile)arg0.getSource();
			
			//if tile is an empty tile, turn background of tile green on hover
			if(t.isEnabled()) {
				t.setBackground(Color.GREEN);
			}
		}


		public void mouseExited(MouseEvent arg0) {
			Tile t=(Tile)arg0.getSource();
			
			//upon leaving a hover turns green background of tile back to the default
			if(t.getBackground().equals(Color.GREEN)) {
				t.setBackground(null);
			}
			
		}


		public void mousePressed(MouseEvent arg0) {

			
		}

		public void mouseReleased(MouseEvent arg0) {
			
		}
		
	}
}
