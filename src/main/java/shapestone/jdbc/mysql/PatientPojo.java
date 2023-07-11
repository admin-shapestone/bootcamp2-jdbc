package shapestone.jdbc.mysql;

public class PatientPojo {

	private String name;
	private int patientId;
	private int age;
	private String gender;
	private String address;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getpatientId() {
		return patientId;
	}

	public void setpatientId(int patinetid) {
		this.patientId = patinetid;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "PatientPojo [name=" + name + ", patientId=" + patientId + ", age=" + age + ", gender=" + gender + ", address="
				+ address + "]";
	}

}
