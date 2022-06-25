package basicStudy;

import java.util.Scanner;

public class OOPstudy_Test {

	public static void main(String[] args) {
		OOPstudy student = new OOPstudy();
		student.studentName = "권세민";
		student.address = "부산";
		student.showStudentInfo();
		
		OOPstudy student2 = new OOPstudy();
		student2.studentName = "장재영";
		student2.address = "부산";
		student2.showStudentInfo();

	}

}
