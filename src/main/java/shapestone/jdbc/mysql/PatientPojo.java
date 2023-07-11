package shapestone.jdbc.mysql;

public class PatientPojo {

	private String name;
	private int id;
	private int age;
	private String gender;
	private String address;
	private String diagnosis;
	private String treatment;
	private String dateOfTreatment;
	private int cost;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	public String getTreatment() {
		return treatment;
	}

	public void setTreatment(String treatment) {
		this.treatment = treatment;
	}

	public String getDateOfTreatment() {
		return dateOfTreatment;
	}

	public void setDateOfTreatment(String dateOfTreatment) {
		this.dateOfTreatment = dateOfTreatment;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	@Override
	public String toString() {
		return "PatientPojo [name=" + name + ", id=" + id + ", age=" + age + ", gender=" + gender + ", address="
				+ address + ", diagnosis=" + diagnosis + ", treatment=" + treatment + ", dateOfTreatment="
				+ dateOfTreatment + ", cost=" + cost + "]";
	}

	

	
	}


