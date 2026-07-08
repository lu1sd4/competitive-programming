import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class LonesomeKnight1197 {

    BufferedReader br;
    StringTokenizer st;
    
    public LonesomeKnight1197(){
        
    }
    
    private void resolver() throws IOException{
        br = new BufferedReader(new InputStreamReader(System.in));
        //br = new BufferedReader(new InputStreamReader(new FileInputStream("src/input.txt")));
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        String posicion;
        int columna;
        int fila;
        int casillas; 
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            posicion = st.nextToken();
            columna = (int)posicion.charAt(0) - 96;
            fila = (int)posicion.charAt(1) - 48;
            casillas = 0;
            if(fila+2 <= 8){
                if(columna+1 < 9)
                    casillas++;
                if(columna-1 > 0)
                    casillas++;
            }
            if(fila-2 >= 1){
                if(columna+1 < 9)
                    casillas++;
                if(columna-1 > 0)
                    casillas++;
            }
            if(columna-2 >= 1){
                if(fila+1 < 9)
                    casillas++;
                if(fila-1 > 0)
                    casillas++;
            }
            if(columna+2 <= 8){
                if(fila+1 < 9)
                    casillas++;
                if(fila-1 > 0)
                    casillas++;
            }
            System.out.println(casillas);
        }
    }
    
    private void correr() throws IOException{
        resolver();
    }
    
    public static void main(String args[]) throws IOException{
        new LonesomeKnight1197().correr();
    }
}