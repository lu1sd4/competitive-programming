import java.io.*;
import java.util.*;

// UVa 790 - Head Judge Headache
public class Main{
	
	BufferedReader br;
	StringTokenizer st;
	PrintWriter pw;
	
	public static void main(String args[]) throws Exception{
		new Main().solve();
	}
	
	static class Team implements Comparable<Team>{
		int no;
		HashMap<Character, Integer> triedProblems;
		HashSet<Character> solvedProblems;
		int penalty;
		public Team(int _no){
			no = _no;
			triedProblems = new HashMap<>();
			solvedProblems = new HashSet<>();
			penalty = 0;
		}
		public void processSubmision(char problem, int mins, char result){
			if(!triedProblems.containsKey(problem))
				triedProblems.put(problem, 0);
			if(!solvedProblems.contains(problem)){
				if(result == 'Y'){			
					penalty += mins + 20*triedProblems.get(problem);
					solvedProblems.add(problem);
				} else if(result == 'N')
					triedProblems.put(problem, triedProblems.get(problem)+1);
			}
		}
		@Override
		public int compareTo(Team other){
			if(solvedProblems.size() > other.solvedProblems.size()) return -1;
			if(solvedProblems.size() < other.solvedProblems.size()) return 1;
			if(penalty < other.penalty) return -1;
			if(penalty > other.penalty) return 1;
			return Integer.compare(no, other.no);
		}
		@Override
		public String toString(){
			if(solvedProblems.isEmpty())
				return String.format("%5d",no);
			return String.format("%5d%5d%11d", no, solvedProblems.size(), penalty);
		}
		public boolean differentScore(Team other){
			return this.solvedProblems.size() != other.solvedProblems.size() || this.penalty != other.penalty; 
		}
	}
	
	static class Submission implements Comparable<Submission>{
		int mins;
		char result;
		char problem;
		int team;
		public Submission(int _team, char _problem, int _mins, char _result){
			mins = _mins;
			problem = _problem;
			result = _result;
			team = _team;
		}
		@Override
		public int compareTo(Submission other){
			if(mins != other.mins) return Integer.compare(mins, other.mins);
			return Character.compare(result, other.result);
		}
		@Override
		public String toString(){
			return team+" "+mins+" "+result+" "+problem;
		}
	}
	
	void solve() throws Exception{
		br = new BufferedReader(new InputStreamReader(System.in));
		//br = new BufferedReader(new FileReader("src/input"));
		pw = new PrintWriter(System.out);
		
		Map<Integer, Team> teams;
		List<Team> board;
		PriorityQueue<Submission> submissions;
		Team ct;
		String line, time;
		int no, rank, mins, max;
		char problem, result;
		int tc = lineAsInt();
		skipLine();
		while(tc-- > 0){
			max = Integer.MIN_VALUE;
			teams = new HashMap<>();
			board = new ArrayList<>();			
			submissions = new PriorityQueue<>();
			while((line = br.readLine()) != null && !line.isEmpty()){				
				tokenize(line);
				no = nextInt();
				max = Math.max(no, max);
				problem = next().charAt(0);
				time = next();			
				StringTokenizer stm = new StringTokenizer(time,":");
				mins = Integer.parseInt(stm.nextToken())*60 + Integer.parseInt(stm.nextToken());				
				result = next().charAt(0);
				submissions.add(new Submission(no, problem, mins, result));				
			}
			while(!submissions.isEmpty()){
				Submission cs = submissions.poll();
				if(!teams.containsKey(cs.team)){
					ct = new Team(cs.team);
					teams.put(cs.team, ct);
					board.add(ct);
				} else
					ct = teams.get(cs.team);
				ct.processSubmision(cs.problem, cs.mins, cs.result);
			}
			for(int i = 1; i < max; i++)
				if(!teams.containsKey(i))
					board.add(new Team(i));
			Collections.sort(board);
			rank = 1;
			pw.printf("%s %s %s %s\n", "RANK", "TEAM", "PRO/SOLVED", "TIME");
			for(int i = 0; i < board.size(); i++){
				pw.printf("%4d%s\n", rank, board.get(i).toString());
				if(i+1 < board.size() && board.get(i).differentScore(board.get(i+1)))
					rank=(i+2);				
			}
			if(tc > 0)pw.println();
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
