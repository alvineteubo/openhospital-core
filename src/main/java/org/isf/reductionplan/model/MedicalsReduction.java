package org.isf.reductionplan.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotNull;

import org.isf.exa.model.Exam;
import org.isf.medicals.model.Medical;
import org.isf.utils.db.Auditable;

@Entity
@Table(name = "medicals_reduction")
public class MedicalsReduction extends Auditable<String> {

	@EmbeddedId
	private MedicalsReductionId id;

	@ManyToOne
	@MapsId("reductionplanId")
	@JoinColumn(name = "MR_RP_ID")
	private ReductionPlan reductionPlan;

	@ManyToOne
	@MapsId("medicalId")
	@JoinColumn(name = "MR_MDSR_ID")
	private Medical medical;

	@Column(name = "MR_REDUCTIONRATE")
	private double reductionRate;

	/**
	 * Lock control
	 */
	@Version
	@Column(name = "MR_LOCK")

	@NotNull	private Integer lock;

	@Column(name = "MR_DELETED", columnDefinition = "char(1) default 'N'")
	private char deleted = 'N'; // flag record deleted ; values are 'Y' OR 'N' default is 'N'

	@Transient
	private volatile int hashCode;

	public MedicalsReduction() {
	}

	public MedicalsReduction(MedicalsReductionId id, ReductionPlan reductionPlan, Medical medical, double reductionRate) {
		this.id = id;
		this.reductionPlan = reductionPlan;
		this.medical = medical;
		this.reductionRate = reductionRate;
	}
	public MedicalsReduction(ReductionPlan reductionPlan, Medical medical, double reductionRate) {
		this.reductionPlan = reductionPlan;
		this.medical = medical;
		this.reductionRate = reductionRate;
	}


	public MedicalsReductionId getId() {
		return id;
	}

	public void setId(MedicalsReductionId id) {
		this.id = id;
	}

	public ReductionPlan getReductionPlan() {
		return reductionPlan;
	}

	public void setReductionPlan(ReductionPlan reductionPlan) {
		this.reductionPlan = reductionPlan;
	}

	public Medical getMedical() {
		return medical;
	}

	public void setMedical(Medical medical) {
		this.medical = medical;
	}

	public double getReductionRate() {
		return reductionRate;
	}

	public void setReductionRate(double reductionRate) {
		this.reductionRate = reductionRate;
	}

}
