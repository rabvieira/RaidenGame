package game.Mecanics;

import java.util.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;

import game.Objects.Player;

/**
 * Classe responsavel por gerenciar o score do jogo. Gerencia o score do Player, armazenamento e ordenaçao(topScores) deste.  
 */
public class ScoreManager{

	public Player p;
	public List<Player> order = new ArrayList<Player>();

	public ScoreManager(Player p) {
		this.p = p;
	}

	public void registerPlayerScore(){
		try{
			String s = new String("");
			s +=  (String)JOptionPane.showInputDialog(null,"Digite seu nome") + "," + p.getScore() + "\n";
			readScoreFile(s);
			writeScoreFile();
		}catch (Exception e){
			System.err.println("Error: " + e.getMessage());
		}
	}

	public void writeScoreFile() throws IOException {
		String s;
		s = new String("");
		for(int i = 0;i < order.size();i++){
			s+= order.get(i).getName() + "," + order.get(i).getScore() + "\n";
		}
		FileWriter ofstream = new FileWriter("score.txt");
		BufferedWriter out = new BufferedWriter(ofstream);
		out.write(s);
		out.close();
	}

	public void readScoreFile(String s) throws FileNotFoundException,
	IOException {
		order.clear();
		FileReader ifstream = new FileReader("score.txt");
		BufferedReader in = new BufferedReader(ifstream);
		String line;

		while((line = in.readLine()) != null)
			s += line + "\n";
		StringTokenizer fields = new StringTokenizer(s,",\n");
		
		while(fields.hasMoreTokens()) {

			String key = fields.nextToken();
			int val = Integer.parseInt(fields.nextToken());
			Player p2 = new Player(key,val);
			order.add(p2);
		} 

		in.close();
		Collections.sort(order);
	}

	public void actualizeScoreFile() throws Throwable{
		order.clear();
		String s = new String();
		FileReader ifstream = new FileReader("score.txt");
		BufferedReader in = new BufferedReader(ifstream);
		String line;

		try {
			while((line = in.readLine()) != null)
				s += line + "\n";
		} catch (IOException e) {
			e.printStackTrace();
		}
		StringTokenizer fields = new StringTokenizer(s,",\n");

		while(fields.hasMoreTokens()) {
			String key = fields.nextToken();
			int val = Integer.parseInt(fields.nextToken());
			Player p2 = new Player(key,val);
			order.add(p2);
		} 
		
		in.close();
		Collections.sort(order);
	}
}
