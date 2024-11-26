package org.isf.reductionplan;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.isf.operation.model.Operation;
import org.isf.reductionplan.model.OperationsReduction;
import org.isf.reductionplan.model.OperationsReductionId;
import org.isf.reductionplan.model.ReductionPlan;
import org.junit.Test;

public class TestsOperationReductionPlan {



		private String operationCode = "OP123";
		private double reductionRate = 25.0;
		private int reductionPlanId = 1;

		public OperationsReduction setupOperationReduction(String operationCode, int reductionPlanId, double reductionRate) {
			OperationsReduction operationReduction = new OperationsReduction();

			// Configurer Operation
			Operation operation = new Operation();
			operation.setCode(operationCode);

			// Configurer ReductionPlan
			ReductionPlan reductionPlan = new ReductionPlan();
			reductionPlan.setId(reductionPlanId);

			// Configurer l'ID composite
			OperationsReductionId id = new OperationsReductionId(reductionPlanId, operationCode);

			// Associer les entités
			operationReduction.setId(id);
			operationReduction.setOperation(operation);
			operationReduction.setReductionPlan(reductionPlan);
			operationReduction.setReductionRate(reductionRate);

			return operationReduction;
		}

		// Vérification des attributs d'une opération réduction
		public void checkOperationReductionAttributes(OperationsReduction operationReduction, String expectedOperationCode, int expectedReductionPlanId, double expectedReductionRate) {
			assertThat(operationReduction).isNotNull();
			assertThat(operationReduction.getOperation().getCode()).isEqualTo(expectedOperationCode);
			assertThat(operationReduction.getReductionPlan().getId()).isEqualTo(expectedReductionPlanId);
			assertThat(operationReduction.getReductionRate()).isEqualTo(expectedReductionRate);
		}

		// Test pour valider une liste d'OperationsReduction
		@Test
		public void testOperationsReductionList() {
			String operationCode1 = "OP123";
			String operationCode2 = "OP124";
			int reductionPlanId = 1;
			double reductionRate = 25.0;

			// Configuration de deux OperationsReduction
			OperationsReduction operationReduction1 = setupOperationReduction(operationCode1, reductionPlanId, reductionRate);
			OperationsReduction operationReduction2 = setupOperationReduction(operationCode2, reductionPlanId, reductionRate);

			List<OperationsReduction> operations = List.of(operationReduction1, operationReduction2);

			// Vérification
			assertThat(operations).hasSize(2);
			checkOperationReductionAttributes(operations.get(0), operationCode1, reductionPlanId, reductionRate);
			checkOperationReductionAttributes(operations.get(1), operationCode2, reductionPlanId, reductionRate);
		}
	}



