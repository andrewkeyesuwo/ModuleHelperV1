import java.util.Iterator;

public class Comparator {
	public Comparator(){
		;
	}
	
	public boolean compare(String a, String b){
		a = a.trim();
		b = b.trim();
		if(a.length()!=b.length()){
			return false;
		}
		for(int i=0;i<a.length();i++){
			if(a.charAt(i)!=b.charAt(i)){
				return false;
			}
		}
		return true;
	}
	
	public boolean compare(ConditionalCourse conCourse, Course studentCourse){
		Iterator<Course> courses = conCourse.getCourses().listIterator();
		while(courses.hasNext()){
			Course course = courses.next();
			if(compare(course.getName(), studentCourse.getName())){
				return true;
			}
		}
		return false;
	}
}
