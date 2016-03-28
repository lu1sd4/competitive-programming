
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class basicgeometry {

    public static void main(String[] args) throws IOException{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        double A, B;
        StringTokenizer st;
        double alpha;
        double sectionArea;
        double radius;
        double triangleArea;
        double requestedArea;
        while((line = br.readLine()) != null){
            st = new StringTokenizer(line);
            A = Double.parseDouble(st.nextToken());
            B = Double.parseDouble(st.nextToken());
            if(A!=B){
                if(B > A){
                    double aux = A;
                    A = B;
                    B = aux;
                }
                radius = A+B;
                alpha = 2 * Math.acos(Math.pow(A-B, 2)/(Math.pow(A, 2)-Math.pow(B, 2)));
                alpha = Math.toDegrees(alpha);
                sectionArea = (alpha/360.0)*Math.PI*Math.pow(radius, 2);
                triangleArea = (A-B)*Math.sqrt(Math.pow(A+B, 2) - Math.pow(A-B, 2));
                requestedArea = sectionArea - triangleArea - Math.PI*Math.pow(B, 2);
            } else {
                requestedArea = Math.PI*Math.pow(B, 2);
            }
            System.out.printf("%.3f\n", requestedArea);
        }
    
    }
    
}
