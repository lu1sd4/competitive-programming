import java.io.*;
import java.util.*;

public class election {

    static BufferedReader br;
    static StringTokenizer st;
    
    public static void main(String[] args) throws IOException{
        
        br = new BufferedReader(new InputStreamReader(System.in));
        //br = new BufferedReader(new FileReader("src/input.txt"));
        
        int t = Integer.parseInt(br.readLine());
        int a, b;
        
        double[] porcentajes;
        double[] votosTotales;
        double vTotal;
        
        while(t-- > 0){
            vTotal = 0;
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            
            porcentajes = new double[a];
            votosTotales = new double[a];
            
            while(b-- > 0){
                double[] porcEstado = new double[a];
                st = new StringTokenizer(br.readLine());
                for(int i = 0; i < a; i++)
                    porcEstado[i] = Double.parseDouble(st.nextToken());
                double vEstado = Double.parseDouble(st.nextToken());
                for(int i = 0; i < a; i++)
                    votosTotales[i] += vEstado * (porcEstado[i]/100);
                vTotal += vEstado;
            }
            
            boolean segundaRonda = true;
            
            int iMayor=0, iSegundo=0;
            
            for(int i = 0; i < porcentajes.length; i++){
                porcentajes[i] = (votosTotales[i]/vTotal)*100;
                
                if(porcentajes[i] >= porcentajes[iMayor]){
                    iSegundo = iMayor;
                    iMayor = i;
                } else if (porcentajes[i] > porcentajes[iSegundo]){
                    iSegundo = i;
                }
                
                if(porcentajes[i] >= 50.1){
                    segundaRonda = false;
                    System.out.println((i+1)+" "+(int)votosTotales[i]);
                }
            }
            
            if(segundaRonda){
                
                if(votosTotales[iMayor] == votosTotales[iSegundo]){
                    if(iMayor > iSegundo){
                        System.out.println((iSegundo+1)+" "+(int)votosTotales[iSegundo]);
                        System.out.println((iMayor+1)+" "+(int)votosTotales[iMayor]);
                    } else {
                        System.out.println((iMayor+1)+" "+(int)votosTotales[iMayor]);
                        System.out.println((iSegundo+1)+" "+(int)votosTotales[iSegundo]);
                    }
                } else {
                    System.out.println((iMayor+1)+" "+(int)votosTotales[iMayor]);
                    System.out.println((iSegundo+1)+" "+(int)votosTotales[iSegundo]);
                }
                
            }
            
            if(t!=0)System.out.println("");
            
        }
        
    }
    
}
