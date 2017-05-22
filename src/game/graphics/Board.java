package game.graphics;
import game.Mecanics.GameLogic;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe construtora do frame principal.
 */
public class Board {

	private JFrame frame;
	private AnimationBoard a = new AnimationBoard();

	/**
	 * Lança a aplicaçao.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Board window = new Board();
					window.frame.setLocation(0,0);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Cria a Aplicacao.
	 */
	public Board() {
		initialize();
	}

	/**
	 * Inicializa os componentes do frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 571,720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.SOUTH);
		
		JButton btnInicio = new JButton("New Game");
		btnInicio.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnInicioConfig();
			}
		});	
		panel.add(btnInicio);
		
		JButton btnResumeGame = new JButton("Resume Game");
		btnResumeGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnResumeGameConfig();
			}
		});	
		panel.add(btnResumeGame);
		
		JButton btnTopscores = new JButton("Records");
		btnTopscores.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnTopScoresConfig();
			}
		});	
		panel.add(btnTopscores);
		
		JButton btnTutorial = new JButton("Tutorial");
		btnTutorial.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnTutorialConfig();
			}
		});	
		panel.add(btnTutorial);
		
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnExitConfig();
			}
		});	
		panel.add(btnExit);
		frame.getContentPane().add(a, BorderLayout.CENTER);
		
		frame.setResizable(false);
		MovimentThread t = new MovimentThread(a);
		t.start();
	}
	
	private void btnInicioConfig() {
		a.logics = new GameLogic();
		a.requerirFoco();
		a.logics.b1.setDy(2);
		a.logics.b2.setDy(2);
		a.logics.drawInformation = 1;
		a.logics.ingame = true;
		for(int i = 0; i < a.logics.eManager.getActualEnemyNumber(); i++)
			a.logics.eManager.e[i].outOfScreen = true;
	}
	
	private void btnTopScoresConfig(){
		a.logics.drawInformation = 3;
	}
	
	private void btnResumeGameConfig(){
		if(a.logics.ingame){
			a.requerirFoco();
			a.logics.drawInformation = 1;
		}
	}
	
	private void btnExitConfig(){
		JOptionPane.showMessageDialog(null, "Saindo do Jogo");
		System.exit(0);
	}
	
	private void btnTutorialConfig(){
		a.logics.drawInformation = 4;
	}
}


