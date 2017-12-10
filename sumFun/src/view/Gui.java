package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;

import java.util.concurrent.atomic.AtomicInteger;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import model.Coordinates;
import model.Game;
import model.Leaderboard;
import model.Queue;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.font.TrueTypeFont;
import java.awt.GridBagLayout;
import javax.swing.BoxLayout;

public class Gui extends JFrame implements Observer{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3279980216475310138L;
	private JPanel contentPane;
	private JPanel boardPanel;
	private JPanel headerPanel;
	private Game theGame;
	private Leaderboard[] leaderBoard = new Leaderboard[2];
	private Tile[][] tiles;
	private JLabel [] queueTiles;
	//private JButton[] queueTiles;
	private JLabel lblMovesLeft;
	private JLabel lblMovesInt;
	private JLabel lblScoreDesc;
	private JLabel lblScore;
	private JLabel lblGameStatus;
	private JLabel lblPoints;
	private JLabel lblPointsDesc;
	private JMenu fileMenu;
	private JPanel eastPanel;
	//private JMenuItem saveGameOpt;
	//private JMenuItem loadGameOpt;
	private JMenu helpMenu;
	private JMenuItem refreshOpt;
	private JMenuItem witchCraftOpt;
	private JMenuItem hintOpt;
	private TilesClickListener click;
	private boolean witchCraft=false;
	private boolean hints;
	private Coordinates hintCoord;
	private static Color defaultColor;
	ImageIcon icon[];
	
	private static String[] colorScheme = {"#000000","#B3B5AB","#FFFFFF","#FFFF00","#FF9700","#FF5733","#FF00B9","#FF0051","#00FFCD","#FF0000"};
	public Gui(Game game) {
		super();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setBounds(100, 100, 720, 510);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		icon = new ImageIcon[10];
		for(int i = 0; i<10;i++) {
			icon[i]= new ImageIcon("FontNumbers/"+i+".png");
		}
		
		String filename="/digital_7/digital-7.ttf";//this is for testing normally we would store the font file in our app (knows as an embedded resource), see this for help on that http://stackoverflow.com/questions/13796331/jar-embedded-resources-nullpointerexception/13797070#13797070

		Font font=new Font("Helvetica",Font.BOLD,34);
//		try {
//			font = Font.createFont(Font.TRUETYPE_FONT, new File(filename));
//		} catch (FontFormatException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		font = font.deriveFont(Font.BOLD,28);
//
//		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
//		ge.registerFont(font);
		GraphicsEnvironment ge = 
		         GraphicsEnvironment.getLocalGraphicsEnvironment();
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, new File("digital-7.ttf"));
			
		     ge.registerFont(font);
		     font=Font.createFont(Font.TRUETYPE_FONT, new File("RADIOLAND.TTF"));
		     ge.registerFont(font);
		} catch (IOException|FontFormatException e) {
		     System.out.println("Font error");
		}
		
		fileMenu = new JMenu("File");
		fileMenu.setFont(new Font("Helvetica", Font.BOLD, 14));
		fileMenu.setHorizontalAlignment(SwingConstants.CENTER);
		menuBar.add(fileMenu);
		
		/*saveGameOpt = new JMenuItem("Save Game");
		saveGameOpt.setHorizontalAlignment(SwingConstants.CENTER);
		saveGameOpt.setFont(new Font("Helvetica", Font.PLAIN, 14));
		saveGameOpt.addActionListener(new SaveClickListener());*/
		
		JMenuItem newGameOpt = new JMenuItem("New Game");
		newGameOpt.setFont(new Font("Helvetica", Font.PLAIN, 14));
		newGameOpt.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				if(game.getGameType().equals("timed")) {
					game.pauseTime();
					
				}
				int res=JOptionPane.showConfirmDialog(null,"All progress in this game will be lost. Are you sure?","Warning",JOptionPane.YES_NO_OPTION);
				if(res==JOptionPane.YES_OPTION){
					try {

					    String soundName = "Resources/hasta.aiff";    
					    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
					    //AudioPlayer.player.start(audioInputStream);
					    Clip clip = AudioSystem.getClip();
					    clip.open(audioInputStream);
					    clip.start();
					} catch(Exception e) {
						e.printStackTrace();
					}

					dispose();
					Game.clear(); //clear the instance of game object
					new SplashGui();
				} else {
					game.resumeTime();
				}
			}
			
		});
		fileMenu.add(newGameOpt);
		//fileMenu.add(saveGameOpt);;
		
		/*loadGameOpt = new JMenuItem("Load Game");
		loadGameOpt.setHorizontalAlignment(SwingConstants.CENTER);
		loadGameOpt.setFont(new Font("Helvetica", Font.PLAIN, 14));
		fileMenu.add(loadGameOpt);*/
		
		helpMenu = new JMenu("Help");
		helpMenu.setFont(new Font("Helvetica", Font.BOLD, 14));
		menuBar.add(helpMenu);
		
		refreshOpt = new JMenuItem("Refresh Queue");
		refreshOpt.setFont(new Font("SansSerif", Font.BOLD, 14));
		refreshOpt.addActionListener(new RefreshQueueClickListener()); 
		helpMenu.add(refreshOpt);
		
		witchCraftOpt = new JMenuItem("Witch Craft");
		witchCraftOpt.setHorizontalAlignment(SwingConstants.CENTER);
		witchCraftOpt.setFont(new Font("SansSerif", Font.BOLD, 14));
		witchCraftOpt.addActionListener(new WitchCraftClickListener());
		helpMenu.add(witchCraftOpt);
		
		hintOpt = new JMenuItem("Hint");
		hintOpt.setHorizontalAlignment(SwingConstants.CENTER);
		hintOpt.setFont(new Font("SansSerif", Font.BOLD, 14));
		hintOpt.addActionListener(new HintClickListener());
		helpMenu.add(hintOpt);
		
		contentPane = new JPanel();
		//contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		this.theGame = game;
		this.theGame.addObserver(this);
		this.leaderBoard[0] = new Leaderboard("untimed");
		if(this.theGame.getGameType().equals("timed")) {
			this.leaderBoard[1] = new Leaderboard("timed");
		}
		
		JPanel northPanel = new JPanel();
		northPanel.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		//northPanel.setBackground(Color.GRAY);
		getContentPane().add(northPanel, BorderLayout.NORTH);
		northPanel.setLayout(new BorderLayout(0, 0));
		
		headerPanel = new JPanel();
		headerPanel.setBorder(new LineBorder(new Color(0, 0, 0), 0, true));
		
		if(game.getGameType().equals("untimed")) {
			lblMovesLeft = new JLabel("Moves Left : ");
		} else {
			lblMovesLeft = new JLabel("Time Left : ");
		}
		lblMovesLeft.setOpaque(true);
		lblMovesLeft.setHorizontalAlignment(SwingConstants.CENTER);
		lblMovesLeft.setForeground(Color.WHITE);
		lblMovesLeft.setFont(new Font("Helvetica", Font.BOLD | Font.ITALIC, 26));
		//lblMovesLeft.setBackground(Color.GRAY);
		headerPanel.add(lblMovesLeft);
		
		if(game.getGameType().equals("untimed")) {
			lblMovesInt = new JLabel("50");
		} else {
			lblMovesInt=new JLabel("3:00");
		}


		
		//lblMovesInt.setBackground(Color.white);
		lblMovesInt.setForeground(Color.RED);
		lblMovesInt.setOpaque(true);
		//lblMovesInt.setFont(new Font("Helvetica", Font.BOLD | Font.ITALIC, 34));
		lblMovesInt.setFont(font.deriveFont(Font.BOLD,24));
		lblMovesInt.setVerticalAlignment(SwingConstants.BOTTOM);
		headerPanel.add(lblMovesInt);
		northPanel.add(headerPanel, BorderLayout.CENTER);
		headerPanel.setLayout(new GridLayout(0, 3, 0, 0));
		
		lblGameStatus = new JLabel("Welcome");
		lblGameStatus.setOpaque(true);
		lblGameStatus.setForeground(new Color(255, 255, 0));
		lblGameStatus.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 16));
		JPanel gameStatusPanel=new JPanel();
		gameStatusPanel.setLayout(new BorderLayout(0, 0));
		//gameStatusPanel.setBackground(Color.DARK_GRAY);
		gameStatusPanel.add(lblGameStatus);
		headerPanel.add(gameStatusPanel);
		
		//timer for Game Status
		gameStatusPanel.setSize(new Dimension(250,30));
		final int labelWidth = gameStatusPanel.getWidth();
		final AtomicInteger labelPadding = new AtomicInteger();
		Timer timer = new Timer(30, new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        lblGameStatus.setBorder(new EmptyBorder(0, labelPadding.getAndIncrement() % labelWidth, 0, 30));
		    }
		});
		timer.start();
		
		eastPanel = new JPanel();
		eastPanel.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		eastPanel.setBackground(Color.BLACK);
		getContentPane().add(eastPanel, BorderLayout.EAST);
		eastPanel.setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel optionPanel = new JPanel();
		optionPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		optionPanel.setBackground(Color.BLACK);
		eastPanel.add(optionPanel);
		optionPanel.setLayout(new GridLayout(1, 1, 0, 0));
		
		JPanel scoresPanel = new JPanel();
		scoresPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		optionPanel.add(scoresPanel);
		scoresPanel.setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel pointsPanel = new JPanel();
		pointsPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		scoresPanel.add(pointsPanel);
		pointsPanel.setLayout(new GridLayout(2, 1, 0, 0));
		
		lblPointsDesc = new JLabel("Points:");
		lblPointsDesc.setOpaque(true);
		//lblPointsDesc.setBackground(Color.GRAY);
		lblPointsDesc.setForeground(Color.WHITE);
		lblPointsDesc.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 20));
		pointsPanel.add(lblPointsDesc);
		
		lblPoints = new JLabel("0");
		lblPoints.setHorizontalAlignment(SwingConstants.CENTER);
		lblPoints.setOpaque(true);
//		lblPoints.setFont(new Font("Helvetica", Font.BOLD | Font.ITALIC, 24));
		lblPoints.setFont(font.deriveFont(Font.BOLD, 30));
		lblPoints.setVerticalAlignment(SwingConstants.CENTER);
		lblPoints.setForeground(Color.decode("#FF00FF"));
		//lblPoints.setBackground(Color.GRAY);
		pointsPanel.add(lblPoints);
		
		JPanel totalScorePanel = new JPanel();
		totalScorePanel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		scoresPanel.add(totalScorePanel);
		totalScorePanel.setLayout(new GridLayout(2, 1, 0, 0));
		
		lblScoreDesc = new JLabel("Score:");
		lblScoreDesc.setOpaque(true);
		lblScoreDesc.setHorizontalAlignment(SwingConstants.CENTER);
		lblScoreDesc.setForeground(Color.WHITE);
		lblScoreDesc.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 20));
		//lblScoreDesc.setBackground(Color.GRAY);
		totalScorePanel.add(lblScoreDesc);
		
		lblScore = new JLabel("0");
		lblScore.setOpaque(true);
		lblScore.setHorizontalAlignment(SwingConstants.CENTER);
		lblScore.setForeground(Color.decode("#00B2FF"));
//		lblScore.setFont(new Font("Helvetica", Font.BOLD | Font.ITALIC, 28));
		//lblScore.setBackground(Color.GRAY);
		lblScore.setFont(font.deriveFont(Font.BOLD, 30));
		//lblScore.setVerticalAlignment(SwingConstants.BOTTOM);
		totalScorePanel.add(lblScore);
		
//		JPanel panel = new JPanel();
//		panel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
//		optionPanel.add(panel);
//		panel.setLayout(new BorderLayout(0, 0));
		
		
		//lblGameStatus.setBackground(Color.GRAY);
		//panel.add(lblGameStatus);
		
		JPanel queueTilesPanel = new JPanel();
		queueTilesPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		queueTilesPanel.setForeground(Color.WHITE);
		queueTilesPanel.setBackground(Color.GRAY);
		eastPanel.add(queueTilesPanel);
		queueTilesPanel.setLayout(new GridLayout(5, 1, 0, 0));
		Queue<Integer> modelQueueTiles = theGame.getTilesQueue();
		this.queueTiles = new JLabel[5];
		for(int i=0;i<5;i++) {
			queueTiles[i]=new JLabel(modelQueueTiles.getElement(i).toString());
			queueTiles[i].setHorizontalAlignment(SwingConstants.CENTER);
			queueTiles[i].setBorder(BorderFactory.createLineBorder(Color.YELLOW));
			queueTiles[i].setForeground(Color.CYAN);
			queueTiles[i].setBackground(Color.black);
			queueTiles[i].setOpaque(true);
			queueTilesPanel.add(queueTiles[i]);
		}
		this.queueTiles[0].setFont(new Font("Helvetica",Font.BOLD|Font.ITALIC,38));
		this.queueTiles[0].setForeground(Color.green);
		this.boardPanel = new JPanel();
		boardPanel.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		//boardPanel.setBackground(Color.LIGHT_GRAY);
		getContentPane().add(boardPanel, BorderLayout.CENTER);
		boardPanel.setLayout(new GridLayout(9, 9));
		Integer [][]modelTiles= theGame.getTiles();
		tiles = new Tile[9][9];
		click=new TilesClickListener();
		
		
		
		for(int i=0;i<9;i++) {
			for(int j=0;j<9;j++) {
				tiles[i][j]=new Tile(i,j);
				tiles[i][j].setOpaque(true);
				tiles[i][j].setForeground(Color.white);
				tiles[i][j].setFont(new Font("Helvetica", Font.PLAIN, 20));
				tiles[i][j].setFocusable(false);
//				tiles[i][j].addActionListener(new TilesClickListener());
				tiles[i][j].addMouseListener(click);
				tiles[i][j].addMouseListener(new MouseActionTiles());
				defaultColor=tiles[i][j].getBackground();
			}
		}
		for(int i=1;i<8;i++){
			for(int j=1;j<8;j++){
				int fontSchemeKey=modelTiles[i][j];
				//tiles[i][j].setOpaque(true);
				//tiles[i][j].setText(modelTiles[i][j].toString());
				//tiles[i][j].setForeground(Color.decode(colorScheme[colorSchemeKey]));
				tiles[i][j].setIcon(icon[fontSchemeKey]);
				tiles[i][j].setEnabled(true);
				tiles[i][j].setDisabledIcon(icon[fontSchemeKey]);
			}
		}
		//tiles[0][0].setIcon(icon);
		//add all buttons to the boardPanel
		for(int i=0;i<9;i++) {
			for(int j=0;j<9;j++) {
				boardPanel.add(tiles[i][j]);
			}
		}
		setTitle("Sum Fun "+game.getGameType().toUpperCase()+" Game");
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("grade-1-addition-and-subtraction-1-638.jpg")));
		hints=false;
		AudioStream as;
//		try {
//
//		    String soundName = "Resources/bond.au";    
//		    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
//		    //AudioPlayer.player.start(audioInputStream);
//		    Clip clip = AudioSystem.getClip();
//		    clip.open(audioInputStream);
//		    clip.start();
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
		
		setVisible(true);
	}
	
public void update(Observable arg0, Object arg1) {
		
		
		boolean gameOver= theGame.isGameOver();
		boolean gameWon= theGame.isWon();
		String gameType=theGame.getGameType();
		if(gameType.equals("timed")) {
			//update the remaining time
			lblMovesInt.setText(theGame.getRemainingTime());
		}
		
		//first update the tiles from the model
		Integer [][]modelTiles= theGame.getTiles();
		for(int i=0;i<tiles.length;i++){
			for(int j=0;j<tiles[i].length;j++){
				String text=modelTiles[i][j].toString();
				if(text.equals("-1")){
					tiles[i][j].setIcon(null);
					tiles[i][j].setEnabled(true);
				} else{
					int fontSchemeKey=modelTiles[i][j];
					tiles[i][j].setIcon(icon[fontSchemeKey]);
					//tiles[i][j].setEnabled(false);
					tiles[i][j].setDisabledIcon(icon[fontSchemeKey]);
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
		} else{
			for(int i=0;i<queueTiles.length;i++){
			queueTiles[i].setText(modelQueueTiles.getElement(i).toString());
			}
		}
		
		//update the score board
		int modelScore= theGame.getScore();
		lblScore.setText(Integer.toString(modelScore));
		lblPoints.setText(Integer.toString(theGame.getPoints()));
		if(theGame.isBonusMove()) {
			//play a sound or animation
			
			lblGameStatus.setText("Great Move! Bonus Points Earned");
			
			try {
				
				
						    String soundName = "Resources/tada.wav";    
						    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
						    //AudioPlayer.player.start(audioInputStream);
						    Clip clip = AudioSystem.getClip();
						    clip.open(audioInputStream);
						    clip.start();
						} catch(Exception e) {
							e.printStackTrace();
						}
			theGame.setBonusMove(false);
		}
		else {
			lblGameStatus.setText("");
		}
		if(gameType.equals("untimed")){
			//update the moves left
			int modelMovesLeft= theGame.getRemainingMoves();
			lblMovesInt.setText(Integer.toString(modelMovesLeft));
		}
		
		if(gameOver){
			for(int i=0;i<tiles.length;i++){
				for(int j=0;j<tiles[i].length;j++){
					tiles[i][j].setEnabled(false);
				}
			}
			lblGameStatus.setText("Game Over! Loser!");
			lblGameStatus.setForeground(Color.RED);
			
			//show high score list
			showHighScores();
			//disable refreshqueue
			refreshOpt.setEnabled(false);
			return;
		} else if(gameWon){
			for(int i=0;i<tiles.length;i++){
				for(int j=0;j<tiles[i].length;j++){
					tiles[i][j].setEnabled(false);
				}
			}
			//Stop Timer when game is finished if applicable.... is a bug fix 
			if(theGame.getGameType().equals("timed")) {
				theGame.stopTimer();
			}
			lblGameStatus.setText("Game Won! Legend!");
			lblGameStatus.setForeground(Color.GREEN);
			
//			//check if new TIME .If so, prompt for name input //TIMED only
//			if(leaderBoard[0].checkScore(theGame.getScore())) {
//				newHighScorePrompt();
//			}
//			
//			//check if new high score.If so, prompt for name input //UNTIMED & timed
//			if(leaderBoard[0].checkScore(theGame.getScore())) {
//				newHighScorePrompt();
//			}	
			
			newHighScorePrompt();
			
			
			//ask if want to see high scores			
			showHighScores();
			
			//disable refreshqueue
			refreshOpt.setEnabled(false);
			
			return;
		}
	}
	public void newHighScorePrompt() {
		boolean newTime;
		boolean newScore;
		
		
		
		String time = "";
		if(theGame.getGameType().equals("timed")) {
			time = (180 - Integer.parseInt(theGame.getRemainingTime())) + "";
		}
		
		//prompt for name
		String name = JOptionPane.showInputDialog(null, "Please enter your name:", "New High Score!", JOptionPane.PLAIN_MESSAGE);
		
		//case for cancel on JOtionPane
		if(name == null) {
			name="NoName";
		}
		
		//take out non alpha-numeric
		name.replace(" ", "");
		name.replace(".", "");
		
		
		//add score to leaderBoard
		leaderBoard[0].addScore(name, theGame.getScore());
		if(theGame.getGameType().equals("timed")) {
			leaderBoard[1].addScore(name, theGame.getScore(), time);
		}
		leaderBoard[0].saveScores();
		leaderBoard[1].saveScores();
		
		
	}

	public void showHighScores() {
		JTextArea leaderText = new JTextArea(30,10);
		leaderText.setText("\nHigh Scores\n\nName\tScore\tDate\n" + leaderBoard.toString());
		leaderText.setBackground(Color.BLACK);
		leaderText.setForeground(Color.WHITE);
		leaderText.setEnabled(false);
		//leaderText.setEnabled(false);
		
		//remover tiles and add textarea
		for(int i=0;i<9;i++) {
			for(int j=0;j<9;j++) {
				boardPanel.remove(tiles[i][j]);
			}
		}
		
		this.boardPanel.setLayout(new GridLayout(1,1));
		this.boardPanel.add(leaderText);
		
		
		//change text at end to new game stuff
		lblMovesLeft.setText("Please Select File->New Game to start a new game!");
		headerPanel.remove(lblMovesInt);
		headerPanel.setLayout(new GridLayout(1,1));
		contentPane.remove(eastPanel);
		//setVisible(true);
		setVisible(true);
		repaint();	
	}
	public class SaveClickListener implements ActionListener{


		public void actionPerformed(ActionEvent arg0) {
			
			//String fileName=JOptionPane.showInputDialog("Enter the File name without any extension");
			//theGame.save(fileName);
		}
		
	}
	public class WitchCraftClickListener implements ActionListener{

		
		public void actionPerformed(ActionEvent arg0) {
				witchCraft=true;
				
				AudioStream as;
				try {

				    String soundName = "Resources/offer.aiff";    
				    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
				    //AudioPlayer.player.start(audioInputStream);
				    Clip clip = AudioSystem.getClip();
				    clip.open(audioInputStream);
				    clip.start();
				} catch(Exception e) {
					e.printStackTrace();
				}

				witchCraftOpt.setEnabled(false);
		}
	}
public class HintClickListener implements ActionListener{

		
		public void actionPerformed(ActionEvent arg0) {
				Coordinates coord=theGame.getBestPlacement();
				hints=true;
				tiles[coord.getRow()][coord.getCol()].setBackground(Color.YELLOW);
				hintCoord=coord;
				if(theGame.getHints()<=0) {
					if(theGame.getHints()==0) {
						AudioStream as;
						try {

						    String soundName = "Resources/force.aiff";    
						    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
						    //AudioPlayer.player.start(audioInputStream);
						    Clip clip = AudioSystem.getClip();
						    clip.open(audioInputStream);
						    clip.start();
						} catch(Exception e) {
							e.printStackTrace();
						}
					}
					hintOpt.setEnabled(false);
				}
		}
	}
	public class RefreshQueueClickListener implements ActionListener{

		
		public void actionPerformed(ActionEvent arg0) {
			AudioStream as;
			try {

			    String soundName = "Resources/actLikeMan.aiff";    
			    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
			    //AudioPlayer.player.start(audioInputStream);
			    Clip clip = AudioSystem.getClip();
			    clip.open(audioInputStream);
			    clip.start();
			} catch(Exception e) {
				e.printStackTrace();
			}
				refreshOpt.setEnabled(false);
				theGame.refreshQueue();	
		}
	}

	public class TilesClickListener implements MouseListener{


		@Override
		public void mouseClicked(MouseEvent arg0) {
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			if(witchCraft==true) {
				for(int i=0;i<tiles.length;i++) {
					for(int j=0;j<tiles[i].length;j++) {
						if(theGame.getTiles()[i][j]==-1)
							tiles[i][j].setEnabled(false);
						else {
							tiles[i][j].setEnabled(true);
//							tiles[i][j].setForeground(Color.black);
						}
						Coordinates x=((Tile)arg0.getSource()).getCoord();
						int tileVal=theGame.getTiles()[x.getRow()][x.getCol()];
						//if(Integer.toString(theGame.getTiles()[i][j]).equals((Tile)arg0.getSource()).getCoord()) {
						if(theGame.getTiles()[i][j]==tileVal) {
							tiles[i][j].setBackground(Color.BLUE);
						}
					}
				}
			}
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			for(int i=0;i<tiles.length;i++) {
				for(int j=0;j<tiles[i].length;j++) {
					tiles[i][j].setBackground(null);
				}
			}
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			if(witchCraft==true) {
				theGame.witchCraft(((Tile)arg0.getSource()).getCoord());
				witchCraft=false;
			}
			else {
				Tile t=(Tile)arg0.getSource();
				theGame.updateTilesinBoard(t.getCoord());
				if(hints==true) {
					hints=false;
				}
				
			}
			//witchCraft=false;
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	public class MouseActionTiles implements MouseListener{

	
		public void mouseClicked(MouseEvent arg0) {
		
			
			
		}

	
		public void mouseEntered(MouseEvent arg0) {
			
			Tile t=(Tile)arg0.getSource();
			t.setPreferredSize(new Dimension(20,20));
			//if tile is an empty tile, turn background of tile green on hover
			if(t.isEnabled()) {
					if(theGame.getTiles()[t.getCoord().getRow()][t.getCoord().getCol()]==-1) {
						t.setBackground(Color.GREEN);
					}
						
				if(hints==true) {
						tiles[hintCoord.getRow()][hintCoord.getCol()].setBackground(Color.YELLOW);
				}
				
			}
		
		}

	
		public void mouseExited(MouseEvent arg0) {
		
			Tile t=(Tile)arg0.getSource();
			
			//upon leaving a hover turns green background of tile back to the default
			if(t.getBackground().equals(Color.GREEN)) {
				t.setBackground(null);
				if(hints==true) {
//					if(t.getCoord().equals(hintCoord))
						tiles[hintCoord.getRow()][hintCoord.getCol()].setBackground(Color.YELLOW);
				}
			}
			
		}

	
		public void mousePressed(MouseEvent arg0) {
		
			
		}

	
		public void mouseReleased(MouseEvent arg0) {
			
		}
		
	}


	/*public static void main(String[]args){
		new NewGUI(Game.getGame("untimed"));
	}*/
}
