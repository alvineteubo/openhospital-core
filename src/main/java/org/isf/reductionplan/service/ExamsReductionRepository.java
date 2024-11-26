package org.isf.reductionplan.service;

import java.util.List;

import org.isf.exa.model.Exam;
import org.isf.reductionplan.model.ExamsReduction;
import org.isf.reductionplan.model.ExamsReductionId;
import org.isf.reductionplan.model.ReductionPlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamsReductionRepository extends JpaRepository<ExamsReduction, ExamsReductionId> {

	List<ExamsReduction> findByReductionPlan(ReductionPlan reductionPlan);
	void deleteByReductionPlan(ReductionPlan reductionPlan);
	ExamsReduction findByReductionPlanAndExam(ReductionPlan reductionPlan, Exam exam);


}


