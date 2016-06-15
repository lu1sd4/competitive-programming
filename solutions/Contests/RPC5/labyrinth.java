import java.io.*;
import java.util.*;

public class labyrinth {
    
    static StringTokenizer st;
    
    public static class Node {
        public ArrayList<Node> neighbors;
        public boolean visited;
        public char type;
        public int distance;
        
        public Node(char type){
            this.type = type;
            visited = false;
            neighbors = new ArrayList<>();
            visited = false;
            distance = 0;
        }
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //br = new BufferedReader(new FileReader("src/input.txt"));
        
        Node start, end;
        Node[][][] nodes;
        String line;
        int x, y, z;
        st = new StringTokenizer(br.readLine());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        z = Integer.parseInt(st.nextToken());
        while(x != 0 && y != 0 && z != 0){
            start = end = null;
            nodes = new Node[x][y][z];
            for(int i = 0; i < z; i++){
                for(int j = 0; j < x; j++){
                    line = br.readLine();
                    for(int k = 0; k < y; k++){
                        char c = line.charAt(k);
                        if(c != '#'){
                            Node n = new Node(c);
                            if(c == 'S')
                                start = n;
                            if(c == 'E')
                                end = n;
                            
                            nodes[j][k][i] = n;
                        }
                    }
                }
                br.readLine();
            }
            for(int i = 0; i < z; i++)
                for(int j = 0; j < x; j++)
                    for(int k = 0; k < y; k++){
                        Node n = nodes[j][k][i];
                        if(n != null){
                            
                            int[] mov = {-1,1};
                            for(int a = 0; a < mov.length; a++){
                                
                                if(j + mov[a] >= 0 && j + mov[a] < x )
                                    if(nodes[j+mov[a]][k][i] != null)
                                        n.neighbors.add(nodes[j+mov[a]][k][i]);
                                
                                if(k + mov[a] >= 0 && k + mov[a] < y )
                                    if(nodes[j][k+mov[a]][i] != null)
                                        n.neighbors.add(nodes[j][k+mov[a]][i]);
                                
                            }
                            
                            if(n.type == '-'){
                                if(i-1 >= 0 && nodes[j][k][i-1] != null && nodes[j][k][i-1].type == '-')
                                    n.neighbors.add(nodes[j][k][i-1]);
                                if(i+1 < z && nodes[j][k][i+1] != null && nodes[j][k][i+1].type == '-')
                                    n.neighbors.add(nodes[j][k][i+1]);
                            }
                        }
                    }
            
            
            Queue<Node> q = new LinkedList<>();
            start.visited = true;
            q.add(start);
            boolean found = false;
            while(!q.isEmpty() && !found){
                Node a = q.poll();
                for(Node n : a.neighbors){
                    if(!n.visited){
                        n.distance = a.distance + 1;
                        if(n == end)
                            found = true;
                        n.visited = true;
                        q.add(n);
                    }
                }
            }            
            
            if(found)
                System.out.println(end.distance);
            else
                System.out.println("-1");
            line = br.readLine();
            st = new StringTokenizer(line);
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            z = Integer.parseInt(st.nextToken());
        }
        
    }
    
}
