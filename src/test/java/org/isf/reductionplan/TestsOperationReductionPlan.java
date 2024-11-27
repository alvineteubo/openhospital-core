package org.isf.reductionplan;

import static org.assertj.core.api.Assertions.assertThat;

import org.isf.operation.model.Operation;
import org.isf.reductionplan.model.OperationsReduction;
import org.isf.reductionplan.model.OperationsReductionId;
import org.isf.reductionplan.model.ReductionPlan;

public class TestsOperationReductionPlan {

	private double reductionRate = 30.0;

	public OperationsReduction setupOperationReduction(OperationsReductionId operationsReductionId, Operation operation, ReductionPlan reductionPlan,
					boolean usingConstructor) {
		if (usingConstructor) {

			return new OperationsReduction(operationsReductionId, reductionPlan, operation, reductionRate);
		} else {
			OperationsReduction operationsReduction = new OperationsReduction();
			operationsReduction.setOperation(operation);
			operationsReduction.setReductionRate(reductionRate);
			operationsReduction.setReductionPlan(reductionPlan);
			operationsReduction.setId(operationsReductionId);

			return operationsReduction;
		}
	}

	public void checkOperationReductionAttributes(OperationsReduction operationsReduction, String expectedOperationCode, int expectedReductionPlanId,
					double expectedReductionRate) {
		assertThat(operationsReduction).isNotNull();
		assertThat(operationsReduction.getOperation().getCode()).isEqualTo(expectedOperationCode);
		assertThat(operationsReduction.getReductionPlan().getId()).isEqualTo(expectedReductionPlanId);
		assertThat(operationsReduction.getReductionRate()).isEqualTo(expectedReductionRate);
	}
}



