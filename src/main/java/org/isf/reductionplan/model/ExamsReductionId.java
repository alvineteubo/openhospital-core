package org.isf.reductionplan.model;

import java.io.Serializable;
import java.util.Objects;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;

@Embeddable
public class ExamsReductionId implements Serializable {

	@Column(name = "ER_RP_ID")
	private int reductionplanId;

	@Column(name = "ER_EXA_ID_A")
	private String examId;

	// Constructeur par défaut
	public ExamsReductionId() {}

	// Constructeur avec paramètres
	public ExamsReductionId(int reductionplanId, String examId) {
		this.reductionplanId = reductionplanId;
		this.examId = examId;
	}

	// Getters et setters
	public int getReductionplanId() {
		return reductionplanId;
	}

	public void setReductionplanId(int reductionplanId) {
		this.reductionplanId = reductionplanId;
	}

	public String getExamId() {
		return examId;
	}

	public void setExamId(String examId) {
		this.examId = examId;
	}

	// hashCode et equals
	@Override
	public int hashCode() {
		return Objects.hash(reductionplanId, examId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		ExamsReductionId that = (ExamsReductionId) obj;
		return reductionplanId == that.reductionplanId &&
						Objects.equals(examId, that.examId);
	}
}
