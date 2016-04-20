import java.io.*;
import java.util.*;

//UVa 462 - Bridge Hand Evaluator
class Main {

	static BufferedReader br;
	static StringTokenizer st = new StringTokenizer("");
	
	public static void main(String args[]) throws IOException{
		br = new BufferedReader(new InputStreamReader(System.in));
		//br = new BufferedReader(new FileReader("src/input.txt"));
		int[][] cards;
		char[] suits = {'S', 'H', 'D', 'C'};
		String line, card;
		int suit, points;
		while((line = br.readLine()) != null){
			cards = new int[4][6];
			st = new StringTokenizer(line);
			points = 0;
			boolean allstopped = true;
			for(int i = 0; i < 13; i++){
				card = st.nextToken();
				suit = 0;
				switch(card.charAt(1)){
					case 'S': suit = 0; break;
					case 'H': suit = 1; break;
					case 'D': suit = 2; break;
					case 'C': suit = 3; break;
				}
				cards[suit][5]++;
				switch(card.charAt(0)){
					case 'A': cards[suit][0]++; break; //ace
					case 'K': cards[suit][1]++; break; //king
					case 'Q': cards[suit][2]++; break; //queen
					case 'J': cards[suit][3]++; break; //jack
					default: cards[suit][4]++; break; //other
				}
			}
			for(int i = 0; i < 4; i++) //Points rule 1
				for(int j = 0; j < cards.length; j++)
					points += (4-i)*cards[j][i];
			
			boolean thisstopped;
			for(int i = 0; i < cards.length; i++){ //Rules 2, 3, 4 and stopped suits
				thisstopped = false;
				if(cards[i][0] > 0) thisstopped = true; //this suit is stopped
				if(cards[i][1] > 0)
					if(cards[i][5]-cards[i][1] <= 0) 
						points -= cards[i][1]; //Rule 2
					else
						thisstopped = true;
				if(cards[i][2] > 0)
					if(cards[i][5]-cards[i][2] <= 1)
						points -= cards[i][2]; //Rule 3
					else
						thisstopped = true;
				if(cards[i][3] > 0 && cards[i][5]-cards[i][3] <= 2)	points -= cards[i][3]; //Rule 4
				if(!thisstopped) allstopped = false;
			}
			
			if(points >= 16 && allstopped){
				System.out.println("BID NO-TRUMP");
				continue;
			}
			int suitmostcards = 0;
			for(int i = 0; i < cards.length; i++){ //Rules 5, 6, 7
				if(cards[i][5] == 2) points++; //Rule 5
				if(cards[i][5] == 1) points+=2; //Rule 6
				if(cards[i][5] == 0) points+=2; //Rule 7
				if(cards[i][5] > cards[suitmostcards][5]) suitmostcards = i; //suit with most cards
			}
			if(points<14)
				System.out.println("PASS");
			else
				System.out.println("BID "+suits[suitmostcards]);
		}
	}
	
}
