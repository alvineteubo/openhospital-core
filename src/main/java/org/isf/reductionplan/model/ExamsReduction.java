package org.isf.reductionplan.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

import org.isf.exa.model.Exam;
import org.isf.utils.db.Auditable;

@Entity
@Table(name = "OH_EXAMSREDUCTION")
public class ExamsReduction {

		@EmbeddedId
		private ExamsReductionId id;

		@ManyToOne
		@MapsId("reductionplanId")
		@JoinColumn(name = "ER_RP_ID")
		private ReductionPlan reductionPlan;

		@ManyToOne
		@MapsId("examId")
		@JoinColumn(name = "ER_EXA_ID_A")
		private Exam exam;

		@Column(name = "ER_REDUCTIONRATE")
		private double reductionRate;

		// Constructeur par défaut
		public ExamsReduction() {}

		// Constructeur avec paramètres
		public ExamsReduction(ExamsReductionId id, ReductionPlan reductionPlan, Exam exam, double reductionRate) {
			this.id = id;
			this.reductionPlan = reductionPlan;
			this.exam = exam;
			this.reductionRate = reductionRate;
		}

		// Getters et setters
		public ExamsReductionId getId() {
			return id;
		}

		public void setId(ExamsReductionId id) {
			this.id = id;
		}

		public ReductionPlan getReductionPlan() {
			return reductionPlan;
		}

		public void setReductionPlan(ReductionPlan reductionPlan) {
			this.reductionPlan = reductionPlan;
		}

		public Exam getExam() {
			return exam;
		}

		public void setExam(Exam exam) {
			this.exam = exam;
		}

		public double getReductionRate() {
			return reductionRate;
		}

		public void setReductionRate(double reductionRate) {
			this.reductionRate = reductionRate;
		}
	}


