package Solved;
import java.io.*;
import java.util.*;

public class L {

    static BufferedReader br;
    static StringTokenizer st = new StringTokenizer("");
    static PrintWriter pw;
    
    static class Pair {
    	public int x;
    	public int y;
    	public Pair(int a, int b){
    		this.x = a;
    		this.y = b;
    	}
    }
    
    public static void main(String[] args) throws Exception{
        
        br = new BufferedReader(new InputStreamReader(System.in));
        pw = new PrintWriter(System.out);
        //br = new BufferedReader(new FileReader("src/input"));
        
        int[][] mosaic, visited;
        
        String dims;
        int h, l;
        while((dims = br.readLine()) != null){
        	st = new StringTokenizer(dims);
        	h = readInt();
        	l = readInt();
        	int minArea = Integer.MAX_VALUE, area = 0;
        	mosaic = new int[h][l];
        	visited = new int[h][l];
        	for(int i = 0; i < h; i++){
        		nextLine();
        		for(int j = 0; j < l; j++){
        			mosaic[i][j] = readInt();
        		}
        	}
        	for(int i = 0; i < h; i++){
        		for(int j = 0; j < l; j++){
        			
        			if(visited[i][j] == 0){
        				area = 0;
        				Stack<Pair> q = new Stack<>();
        				q.push(new Pair(i, j));
        				visited[i][j] = 1;
        				while(!q.isEmpty()){
        					
        					Pair current = q.pop();
        					area++;
        					int x = current.x;
        					int y = current.y;
        					
        					if(x-1 >= 0 && mosaic[x-1][y] == mosaic[x][y] && visited[x-1][y] == 0){
        						q.push(new Pair(x-1, y));
        						visited[x-1][y] = 1;
        					}
        					if(x+1 < mosaic.length && mosaic[x+1][y] == mosaic[x][y] && visited[x+1][y] == 0){
        						q.push(new Pair(x+1, y));
        						visited[x+1][y] = 1;
        					}
        					if(y-1 >= 0 && mosaic[x][y-1] == mosaic[x][y] && visited[x][y-1] == 0){
        						q.push(new Pair(x, y-1));
        						visited[x][y-1] = 1;
        					}
        					if(y+1 < mosaic[x].length && mosaic[x][y+1] == mosaic[x][y] && visited[x][y+1] == 0){
        						q.push(new Pair(x, y+1));
        						visited[x][y+1] = 1;
        					}
        					
        				}
        				if(area < minArea) minArea = area;
        				
        			}
        		}
        	}
        	pw.println(minArea);
        }
        
        
        br.close();
        pw.close();
        
    }
    
    static int lineToInt() throws IOException{
    	return Integer.parseInt(br.readLine());
    }
    
    static long readLong() throws Exception{
    	return Long.parseLong(st.nextToken());
    }
    
    static int readInt() throws Exception{
    	return Integer.parseInt(st.nextToken());
    }
    
    static void nextLine() throws Exception{
    	st = new StringTokenizer(br.readLine());
    }
    
}