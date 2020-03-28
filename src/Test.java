import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
//		System.out.println("Enter no of courses in sem1");
//		int no=scanner.nextInt();
//		Double[] courses=new Double[no];
//		double sum = 0;
//		for (int i = 0; i < courses.length; i++) {
//			System.out.println("Enter marks of course"+i);
//			courses[i]=scanner.nextDouble();
//			sum+=courses[i];
//		}
////		for (Double double1 : courses) {
////			System.out.println(double1);
////		}
//		System.out.println("sum is "+sum);
//		System.out.println("average is "+ (sum/no));

		Student[] students =new Student[2];
		students[0]=new Student("dasad", 10, "sdsad");
		students[1]=new Student("dasad", 10, "34sd");
		
		for (Student student : students) {
			student.toString();
		}
		
	}
}
