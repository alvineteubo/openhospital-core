package org.isf.reductionplan.service;

import java.util.List;

import org.isf.medicals.model.Medical;
import org.isf.reductionplan.model.ExamsReduction;
import org.isf.reductionplan.model.MedicalsReduction;
import org.isf.reductionplan.model.MedicalsReductionId;
import org.isf.reductionplan.model.ReductionPlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalsReductionRepository extends JpaRepository<MedicalsReduction, MedicalsReductionId> {
	List<MedicalsReduction> findByReductionPlan(ReductionPlan reductionPlan);
	void deleteByReductionPlan(ReductionPlan reductionPlan);
	MedicalsReduction findByReductionPlanAndMedical(ReductionPlan reductionPlan, Medical medical);
}
