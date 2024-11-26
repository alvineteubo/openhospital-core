package org.isf.reductionplan.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotNull;

import org.isf.utils.db.Auditable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "OH_REDUCTIONPLAN")
@EntityListeners(AuditingEntityListener.class)
@AttributeOverride(name = "createdBy", column = @Column(name = "RP_CREATED_BY", updatable = false))
@AttributeOverride(name = "createdDate", column = @Column(name = "RP_CREATED_DATE", updatable = false))
@AttributeOverride(name = "lastModifiedBy", column = @Column(name = "RP_LAST_MODIFIED_BY"))
@AttributeOverride(name = "active", column = @Column(name = "RP_ACTIVE"))
@AttributeOverride(name = "lastModifiedDate", column = @Column(name = "RP_LAST_MODIFIED_DATE"))
public class ReductionPlan extends Auditable<String> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "RP_ID")
	private int id;

	@NotNull
	@Column(name = "RP_DESCRIPTION")
	private String description;

	@Column(name = "RP_OPERATIONRATE")
	private double operationRate;

	@Column(name = "RP_MEDICALRATE")
	private double medicalRate;

	@Column(name = "RP_EXAMRATE")
	private double examRate;

	@Column(name = "RP_OTHERRATE")
	private double otherRate;

	@Version
	@Column(name = "RP_LOCK")
	private int lock;

	@Transient
	private volatile int hashcode;

	@OneToMany(mappedBy = "reductionPlan", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ExamsReduction> examsReduction = new ArrayList<>();


	@OneToMany(mappedBy = "reductionPlan", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<MedicalsReduction> medicalsReduction = new ArrayList<>();

	@OneToMany(mappedBy = "reductionPlan", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<OperationsReduction> operationsReductions = new ArrayList<>();

	@OneToMany(mappedBy = "reductionPlan", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<OtherReduction> otherReductions = new ArrayList<>();

	// Constructeur par défaut
	public ReductionPlan() {
		super();
	}

	// Constructeur avec paramètres
	public ReductionPlan(String description, double operationRate, double medicalRate, double examRate, double otherRate) {
		this.description = description;
		this.operationRate = operationRate;
		this.medicalRate = medicalRate;
		this.examRate = examRate;
		this.otherRate = otherRate;
	}

	// Getters et setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getOperationRate() {
		return operationRate;
	}

	public void setOperationRate(double operationRate) {
		this.operationRate = operationRate;
	}

	public double getMedicalRate() {
		return medicalRate;
	}

	public void setMedicalRate(double medicalRate) {
		this.medicalRate = medicalRate;
	}

	public double getExamRate() {
		return examRate;
	}

	public void setExamRate(double examRate) {
		this.examRate = examRate;
	}

	public double getOtherRate() {
		return otherRate;
	}

	public void setOtherRate(double otherRate) {
		this.otherRate = otherRate;
	}

	public int getLock() {
		return lock;
	}

	public void setLock(int lock) {
		this.lock = lock;
	}

	public List<ExamsReduction> getExamsReduction() {
		return examsReduction;
	}


	public void setExamsReduction(List<ExamsReduction> examsReduction) {
		this.examsReduction = examsReduction;
	}
	public List<MedicalsReduction> getMedicalsReduction() {
		return medicalsReduction;
	}
	public void addExamsReduction(ExamsReduction examsReduction) {
		this.examsReduction.add(examsReduction);
		examsReduction.setReductionPlan(this);
	}

	public void addMedicalsReduction(MedicalsReduction medicalsReduction) {
		this.medicalsReduction.add(medicalsReduction);
		medicalsReduction.setReductionPlan(this);
	}

	public List<OperationsReduction> getOperationsReductions() {
		return operationsReductions;
	}
	public void addOperationsReduction(OperationsReduction operationsReduction) {
		this.operationsReductions.add(operationsReduction);
		operationsReduction.setReductionPlan(this);
	}

	public List<OtherReduction> getOtherReductions() {
		return otherReductions;
	}

	public void addOtherReduction(OtherReduction otherReduction) {
		this.otherReductions.add(otherReduction);
		otherReduction.setReductionPlan(this);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (!(obj instanceof ReductionPlan)) return false;
		ReductionPlan other = (ReductionPlan) obj;
		return id == other.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
