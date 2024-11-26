package org.isf.reductionplan.service;

import java.util.List;

import org.isf.exa.model.Exam;
import org.isf.operation.model.Operation;
import org.isf.reductionplan.model.ExamsReduction;
import org.isf.reductionplan.model.OperationsReduction;
import org.isf.reductionplan.model.OperationsReductionId;
import org.isf.reductionplan.model.ReductionPlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationsReductionRepository extends JpaRepository<OperationsReduction, OperationsReductionId> {

	List<OperationsReduction> findByReductionPlan(ReductionPlan reductionPlan);
	void deleteByReductionPlan(ReductionPlan reductionPlan);
	OperationsReduction findByReductionPlanAndOperation(ReductionPlan reductionPlan, Operation operation);
}
