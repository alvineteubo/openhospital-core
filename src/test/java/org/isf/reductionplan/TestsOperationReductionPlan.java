/*
 * Open Hospital (www.open-hospital.org)
 * Copyright © 2006-2024 Informatici Senza Frontiere (info@informaticisenzafrontiere.org)
 *
 * Open Hospital is a free and open source software for healthcare data management.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * https://www.gnu.org/licenses/gpl-3.0-standalone.html
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
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


