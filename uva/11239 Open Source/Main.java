import java.io.*;
import java.util.*;

// UVa 11239 - Open Source
public class Main {
	
	static class Project implements Comparable<Project>{
		String name;
		Set<String> students;
		public Project(String name){
			this.name = name;
			students = new HashSet<>();
		}
		public void addStudent(String student){
			students.add(student);
		}
		public void removeStudent(String student){
			students.remove(student);
		}
		@Override
		public int compareTo(Project b) {
			if(this.students.size() > b.students.size()) return 1;
			if(this.students.size() < b.students.size()) return -1;
			return b.name.compareTo(this.name);
		}
		@Override
		public String toString(){
			return name+" "+students.size();
		}
		
	}
	
	BufferedReader br;
	StringTokenizer st;
	PrintWriter pw;
	
	public static void main(String args[]) throws Exception{
		new Main().solve();
	}

	void solve() throws Exception{
		br = new BufferedReader(new InputStreamReader(System.in));
		//br = new BufferedReader(new FileReader("src/input"));
		pw = new PrintWriter(System.out);
		
		Map<String, Project> projects;
		TreeSet<Project> sortedProjects;
		Map<String, String> students;
		String line = br.readLine();
		while(!line.equals("0")){
			projects = new HashMap<>();
			students = new HashMap<>();
			sortedProjects = new TreeSet<>();
			String currentProject = "";
			while(!line.equals("1")){
				if(Character.isUpperCase(line.charAt(0))){
					currentProject = line;
					projects.put(currentProject, new Project(currentProject));
				} else {
					if(students.containsKey(line) && !students.get(line).equals(currentProject))
						projects.get(students.get(line)).removeStudent(line);
					else{
						students.put(line, currentProject);
						projects.get(currentProject).addStudent(line);
					}
				}
				line = br.readLine();
			}
			for(String s : projects.keySet())
				sortedProjects.add(projects.get(s));
			while(!sortedProjects.isEmpty())				
				pw.println(sortedProjects.pollLast());
			line = br.readLine();
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
