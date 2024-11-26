package org.isf.reductionplan.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class MedicalsReductionId implements Serializable {

	@Column(name = "MR_RP_ID")
	private int reductionplanId;

	@Column(name = "MR_MDSR_ID")
	private Integer medicalId;

	public MedicalsReductionId() {
	}

	public MedicalsReductionId(int reductionplanId, Integer medicalId) {
		this.reductionplanId = reductionplanId;
		this.medicalId = medicalId;
	}

	public int getReductionplanId() {
		return reductionplanId;
	}

	public void setReductionplanId(int reductionplanId) {
		this.reductionplanId = reductionplanId;
	}

	public Integer getMedicalId() {
		return medicalId;
	}

	public void setMedicalId(Integer medicalId) {
		this.medicalId = medicalId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(reductionplanId, medicalId);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		MedicalsReductionId that = (MedicalsReductionId) o;
		return reductionplanId == that.reductionplanId &&
						Objects.equals(medicalId, that.medicalId);
	}

}
