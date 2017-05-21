package models;
public class StudentMember extends Member {
	private int studentId;
	private String collegeName;
	
	 /**
     * Constructor for objects of class StudentMember.
     *
     * @param studentId      The member's student id is 7 digits long i.e. between 1000000 (exclusive) and
     *                        9999999 (inclusive).  If an invalid member id is entered, set the member
     *                        id to a default value of 100000.
     *
     */
	
	public StudentMember(String memberEmail, String memberName, String memberAddress, 
	        String gender, double height, double startingWeight, String chosenPackage, int studentId, String collegeName){
		
		super(memberEmail, memberName, memberAddress, gender, height, startingWeight, chosenPackage);
		 if ((studentId > 1000000) && ( studentId <= 9999999)){
	            this.studentId = studentId;
	        }
	        else{
	            this.studentId = 1000000;
	        }
		
		this.collegeName = collegeName;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	
	public String getCollegeName() {
		return collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	@Override
	public void chosenPackage(String chosenPackage) {


	}
}