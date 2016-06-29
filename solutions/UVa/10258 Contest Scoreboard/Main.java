import java.io.*;
import java.util.*;

//UVa 10258 - Contest Scoreboard
class Main {

	static BufferedReader br;
	static PrintWriter pw;
	static StringTokenizer st = new StringTokenizer("");
	
	static class Contestant implements Comparable<Contestant>{
		
		public Contestant(int teamNumber){
			this.teamNumber = teamNumber;
			problemsSolved = 0;
			timePenalty = 0;
			solvedProblem = new boolean[10];
			pendantTimePenalty = new int[10];
		}
		
		public boolean[] solvedProblem;
		public int teamNumber;
		public int problemsSolved;
		public int timePenalty;
		public int[] pendantTimePenalty;
		
		@Override
		public String toString(){
			return teamNumber + " " + problemsSolved + " " + timePenalty;
		}
		
		public void solveProblem(int problem, int time){
			if(!solvedProblem[problem]){
				problemsSolved++;
				timePenalty += time + pendantTimePenalty[problem];
				solvedProblem[problem] = true;
			}
		}
		
		public void tryProblem(int problem){
			pendantTimePenalty[problem] += 20;
		}
		
		public int compareTo(Contestant c2){
			if(problemsSolved > c2.problemsSolved)		return -1;
			else if(problemsSolved < c2.problemsSolved) return 1;
			else {
				if(timePenalty < c2.timePenalty) 		return -1;
				else if(timePenalty > c2.timePenalty) 	return 1;
				else{
					if(teamNumber < c2.teamNumber)		return -1;
					else								return 1;
				}
			}
		}
		
	}
	
	public static void main(String[] args) throws IOException{
		
		br = new BufferedReader(new InputStreamReader(System.in));
		pw = new PrintWriter(System.out);
		//br = new BufferedReader(new FileReader("src/input.txt"));
		
		int maxn = 101;
		Contestant[] allContestants;
		boolean[] hasTried;
		ArrayList<Contestant> triedContestants;
		String line, L;
		int contestant, problem, time;
		
		int t = Integer.parseInt(br.readLine());
		br.readLine();
		while(t-- > 0){
			allContestants = new Contestant[maxn];
			hasTried = new boolean[maxn];
			for(int i = 0; i < maxn; i++)
				allContestants[i] = new Contestant(i);
			triedContestants = new ArrayList<Contestant>();
			while((line = br.readLine()) != null && !line.isEmpty()){
				st = new StringTokenizer(line);
				contestant = nextInt();
				problem = nextInt();
				time = nextInt();
				L = st.nextToken();
				Contestant c = allContestants[contestant];
				if(!hasTried[contestant]){
					triedContestants.add(c);
					hasTried[contestant] = true;
				}
				switch(L){
					case "C":
						c.solveProblem(problem, time);
						break;
					case "I":
						c.tryProblem(problem);
						break;
					default:
						break;
				}
			}
			Collections.sort(triedContestants);
			for(Contestant d : triedContestants)
				pw.println(d);
			if(t >= 1) pw.println();
		}
		
		br.close();
		pw.close();
		
	}
	
	static int nextInt() throws IOException{
		return Integer.parseInt(st.nextToken());
	}
	
}
