import java.io.*;
import java.util.*;
import java.math.*;
import java.text.SimpleDateFormat;

//UVa 11947 - Cancer or Scorpio
class Main {

    static BufferedReader br;
    static StringTokenizer st = new StringTokenizer("");
    static PrintWriter pw;
    
    public static void main(String[] args) throws Exception{
        
        br = new BufferedReader(new InputStreamReader(System.in));
        pw = new PrintWriter(System.out);
        //br = new BufferedReader(new FileReader("src/input"));
        
        int t = nextInt();
        String line;
        int month, day, year;
        for(int tc = 1; tc <= t; tc++){
        	
        	line = br.readLine();
        	month = Integer.parseInt(line.substring(0, 2));
        	day = Integer.parseInt(line.substring(2, 4));
        	year = Integer.parseInt(line.substring(4));
        	GregorianCalendar gc = new GregorianCalendar(year, month-1, day);
        	gc.add(GregorianCalendar.DAY_OF_YEAR, 40*7);
        	SimpleDateFormat fmt = new SimpleDateFormat("MM/dd/yyyy");
            fmt.setCalendar(gc);
            String dateFormatted = fmt.format(gc.getTime());
            month = gc.get(GregorianCalendar.MONTH) + 1;
            day = gc.get(GregorianCalendar.DAY_OF_MONTH);
        	pw.println(tc+" "+dateFormatted+" "+getSign(month*100 + day));
        }
        
        br.close();
        pw.close();
        
    }
    
    static String getSign(int doy){
    	if(doy >= 1223)
    		return "capricorn";
    	else if(doy >= 1123)
    		return "sagittarius";
    	else if(doy >= 1024)
    		return "scorpio";
    	else if(doy >= 924)
    		return "libra";
    	else if(doy >= 822)
    		return "virgo";
    	else if(doy >= 723)
    		return "leo";
    	else if(doy >= 622)
    		return "cancer";
    	else if(doy >= 522)
    		return "gemini";
    	else if(doy >= 421)
    		return "taurus";
    	else if(doy >= 321)
    		return "aries";
    	else if(doy >= 220)
    		return "pisces";
    	else if(doy >= 121)
    		return "aquarius";
    	else
    		return "capricorn";
    }
    
    static String next() throws Exception{
        while(st == null || !st.hasMoreTokens()){
            try {
                st = new StringTokenizer(br.readLine());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return st.nextToken();
    }
    
    static int nextInt() throws Exception{
        return Integer.parseInt(next());
    }
    
}