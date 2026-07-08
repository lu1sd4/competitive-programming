import java.io.*;
import java.util.*;

public class display{
	
	BufferedReader br;
	StringTokenizer st;
	PrintWriter pw;
	
	public static void main(String args[]) throws Exception{
		new display().solve();
	}
	
	HashMap<Character, Integer> commandIndex = new HashMap<>();
	
	char[][] transChars = { // transChars[charIndex][state] = resultChar
			 //0   90   180  270  mirr	 0	 90	  180  270				
			{ 'x', 'x', 'x', 'x',       'x', 'x', 'x', 'x' },
			{ 'o', 'o', 'o', 'o',       'o', 'o', 'o', 'o' },
			{ '^', '<', 'v', '>',       '^', '<', 'v', '>' },
			{ 'v', '>', '^', '<',       'v', '>', '^', '<' },
			{ '<', 'v', '>', '^',       '>', '^', '<', 'v' },
			{ '>', '^', '<', 'v',       '<', 'v', '>', '^' },
			{ '-', '|', '-', '|',       '-', '|', '-', '|' },
			{ '|', '-', '|', '-',       '|', '-', '|', '-' },
			{ '/', '\\', '/', '\\',     '\\', '/', '\\', '/' },
			{ '\\', '/', '\\', '/',     '/', '\\', '/', '\\' }
	};
	int or0 = 0, or90 = 1, or180 = 2, or270 = 3, mir0 = 4, mir90 = 5, mir180 = 6, mir270 = 7;
	int[][] transTrans = { // transTrans[currentState][transformation] = nextState
			//Orig		RotL	RotR	FlipV	FlipH	MainD	AntiD		 
	  /*or0*/{or0,		or90,	or270,	mir0, 	mir180,	mir90,	mir270},
	 /*or90*/{or90,		or180,	or0,	mir270,	mir90,	mir0,	mir180},
	/*or180*/{or180,	or270,	or90,	mir180,	mir0,	mir270,	mir90},
	/*or270*/{or270,	or0,	or180,	mir90, 	mir270,	mir180,	mir0},
	  /*mi0*/{mir0,		mir90,	mir270,	or0, 	or180,	or90,	or270},
	 /*mi90*/{mir90,	mir180,	mir0,	or270, 	or90,	or0,	or180},
	/*mi180*/{mir180,	mir270,	mir90,	or180, 	or0,	or270,	or90},
	/*mi270*/{mir270,	mir0,	mir180,	or90, 	or270,	or180,	or0},
	};
	
	int[] charIndex = new int[200];
	
	void solve() throws Exception{
		br = new BufferedReader(new InputStreamReader(System.in));
		//br = new BufferedReader(new FileReader("src/input"));
		pw = new PrintWriter(System.out);
		//pw = new PrintWriter(new File("src/output"));
		
		charIndex['x'] = 0;
		charIndex['o'] = 1;
		charIndex['^'] = 2;
		charIndex['v'] = 3;
		charIndex['<'] = 4;
		charIndex['>'] = 5;
		charIndex['-'] = 6;
		charIndex['|'] = 7;
		charIndex['/'] = 8;
		charIndex['\\'] = 9;
		
		commandIndex.put('<', 1);
		commandIndex.put('>', 2);
		commandIndex.put('|', 3);
		commandIndex.put('-', 4);
		commandIndex.put('\\', 5);
		commandIndex.put('/', 6);
		
		String line;
		int n;
		while((line = br.readLine()) != null){
			n = Integer.parseInt(line);
			char[][] board = new char[n][n];
			for(int i = 0; i < n; i++)
				board[i] = lineAsCharArray();
			line = br.readLine();
			int finalstate = getFinalState(line);
			//printBoard(board);
			//pw.println();
			transformBoard(board, finalstate);			
			printBoard(board);
		}
		
		br.close();
		pw.close();		
	}
	
	// flip main diag
	void flipMD(char[][] board){
		char aux;
		for(int i = 0; i < board.length-1; i++)
			for(int j = i+1; j < board.length; j++){
				aux = board[i][j];
				board[i][j] = board[j][i];
				board[j][i] = aux;
			}
	}

	// flip vertical
	void flipV(char[][] board){
		char aux;
		for(int i = 0; i < board.length; i++)
			for(int j = 0; j < board.length/2; j++){
				aux = board[i][j];
				board[i][j] = board[i][board.length-j-1];
				board[i][board.length-j-1] = aux;
			}
	}
	
	void transformBoard(char[][] board, int trans){
		// every transformation can be composed from flip main diag and flip vertical
		// https://en.wikipedia.org/wiki/Dihedral_group
		
		// transform positions
		if(trans == or0) return;
		if(trans == or90){
			flipV(board);
			flipMD(board);
		}
		if(trans == or180){
			flipV(board);
			flipMD(board);
			flipV(board);
			flipMD(board);
		}
		if(trans == or270){
			flipMD(board);
			flipV(board);
		}
		if(trans == mir0){
			flipV(board);
		}
		if(trans == mir90){
			flipMD(board);
		}
		if(trans == mir180){
			flipMD(board);
			flipV(board);
			flipMD(board);
		}
		if(trans == mir270){
			flipV(board);
			flipMD(board);
			flipV(board);
		}
		// Transform chars (shapes)
		for(int i = 0; i < board.length; i++)
			for(int j = 0; j < board.length; j++)
				board[i][j] = transChars[charIndex[board[i][j]]][trans];
	}
	
	void printBoard(char[][] board){
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board.length; j++)
				pw.print(board[i][j]);
			pw.println();
		}
	}
	
	int getFinalState(String commands) throws Exception{
		int ans = or0, commandKey;
		tokenize(commands);
		char command;
		while(st.hasMoreTokens()){
			command = next().charAt(0);
			commandKey = commandIndex.get(command);
			ans = transTrans[ans][commandKey];
		}
		return ans;
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
