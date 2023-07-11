package shapestone.jdbc.mysql;

public class TreatmentPojo {
	private int treatmentId;
	private int patientId;
	private String diagnosis;
	private String treatment;
	private String dateOfTreatment;
	private double cost;

	public int getTreatmentId() {
		return treatmentId;
	}

	public void setTreatmentId(int treatmentId) {
		this.treatmentId = treatmentId;
	}

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
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

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	@Override
	public String toString() {
		return "TreatmentPojo [treatmentId=" + treatmentId + ", patientId=" + patientId + ", diagnosis=" + diagnosis
				+ ", treatment=" + treatment + ", dateOfTreatment=" + dateOfTreatment + ", cost=" + cost + "]";
	}

}
