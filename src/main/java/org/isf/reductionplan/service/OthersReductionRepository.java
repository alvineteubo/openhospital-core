package org.isf.reductionplan.service;

import java.util.List;

import javax.print.attribute.standard.MediaSize;

import org.isf.operation.model.Operation;
import org.isf.pricesothers.model.PricesOthers;
import org.isf.reductionplan.model.MedicalsReduction;
import org.isf.reductionplan.model.OperationsReduction;
import org.isf.reductionplan.model.OtherReduction;
import org.isf.reductionplan.model.OtherReductionId;
import org.isf.reductionplan.model.ReductionPlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OthersReductionRepository extends JpaRepository<OtherReduction, OtherReductionId> {
	List<OtherReduction> findByReductionPlan(ReductionPlan reductionPlan);
	void deleteByReductionPlan(ReductionPlan reductionPlan);
	OtherReduction findByReductionPlanAndPricesOthers(ReductionPlan reductionPlan, PricesOthers pricesOthers);
}
