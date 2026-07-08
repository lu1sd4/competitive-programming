import java.io.*;
import java.util.*;

// UVa 10698 - Football Sort
public class Main{
	
	BufferedReader br;
	StringTokenizer st;
	PrintWriter pw;
	
	public static void main(String args[]) throws Exception{
		new Main().solve();
	}
	
	static class Team implements Comparable<Team>{
		String name;
		int points;
		int goalsScored;
		int goalsAgainst;
		int gamesPlayed;
		public Team(String _name){
			name = _name;
			points = 0;
			goalsScored = 0;
			goalsAgainst = 0;
		}
		int goalDifference(){
			return goalsScored - goalsAgainst;
		}
		double pointPercentage(){
			if(gamesPlayed == 0) return 0;
			return 100.0*((double)points/(double)(gamesPlayed*3));
		}
		boolean isSamePosition(Team other){
			return samePosition(other) == -2;
		}
		int samePosition(Team other){
			if(points > other.points) return -1;
			if(points < other.points) return 1;
			if(goalDifference() > other.goalDifference()) return -1;
			if(goalDifference() < other.goalDifference()) return 1;
			if(goalsScored > other.goalsScored) return -1;
			if(goalsScored < other.goalsScored) return 1;
			return -2;
		}
		@Override
		public int compareTo(Team other){
			int same = samePosition(other);
			if(same != -2) return same;
			return name.compareToIgnoreCase(other.name);
		}
		@Override
		public String toString(){
			String pp = "N/A";
			if(gamesPlayed > 0) pp = String.format(Locale.ROOT, "%7.2f", pointPercentage());
			return String.format("%16s%4d%4d%4d%4d%4d%7s", name, points, gamesPlayed, goalsScored, goalsAgainst, goalDifference(), pp);
		}
	}
	
	void solve() throws Exception{
		br = new BufferedReader(new InputStreamReader(System.in));
		//br = new BufferedReader(new FileReader("src/input"));
		pw = new PrintWriter(System.out);
				
		readLine();
		int T = nextInt(), G = nextInt(), g1, g2;
		String line;
		Map<String, Team> nameTeam;
		ArrayList<Team> board;
		Team ct, t1, t2;
		while(T != 0 || G != 0){
			nameTeam = new HashMap<>();
			board = new ArrayList<>();
			for(int i = 0; i < T; i++){
				line = br.readLine();
				ct = new Team(line);
				nameTeam.put(line, ct);
				board.add(ct);
			}
			for(int i = 0; i < G; i++){
				readLine();
				t1 = nameTeam.get(next());				
				g1 = nextInt();				
				next();
				g2 = nextInt();
				t2 = nameTeam.get(next());
				
				t1.gamesPlayed++;
				t2.gamesPlayed++;
				
				t1.goalsScored += g1;
				t1.goalsAgainst += g2;
				t2.goalsScored += g2;
				t2.goalsAgainst += g1;
				
				if(g1 > g2)
					t1.points += 3;
				else {
					if(g2 > g1)
						t2.points += 3;
					else {
						t1.points++;
						t2.points++;
					}
				}
			}
			Collections.sort(board);
			int rank = 1;
			String rs = rank+".";
			pw.printf("%3s%s\n", rs, board.get(0).toString());
			for(int i = 1; i < T; i++){
				ct = board.get(i);
				if(ct.isSamePosition(board.get(i-1)))
					rs = " ";
				else{
					rank=(i+1);
					rs = rank+".";
				}
				pw.printf("%3s%s\n", rs, ct.toString());				
			}
			readLine();
			T = nextInt(); G = nextInt();
			if(T != 0 || G != 0) pw.println();
		}
		
		br.close();
		pw.close();
		
	}
	
    void tokenize(String s) throws Exception{
        st = new StringTokenizer(s);
    }

    int lineAsInt() throws IOException{
    	return Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
    }
    
    char[] lineAsCharArray() throws IOException{
    	return br.readLine().toCharArray();
    }

    long nextLong() throws Exception{
        return Long.parseLong(st.nextToken());
    }

    int nextInt() throws Exception{
        return Integer.parseInt(st.nextToken());
    }
    
    String next() throws Exception{
    	return st.nextToken();
    }
    
    void readLine() throws Exception{
        st = new StringTokenizer(br.readLine());
    }
    
    void skipLine() throws Exception{
    	br.readLine();
    }
	
}
