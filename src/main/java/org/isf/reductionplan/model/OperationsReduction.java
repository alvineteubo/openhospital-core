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

package org.isf.reductionplan.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import org.isf.operation.model.Operation;
import org.isf.utils.db.Auditable;

@Entity
@Table(name = "OH_OPERATIONSREDUCTION")
public class OperationsReduction extends Auditable<String> {

	@EmbeddedId
	private OperationsReductionId id;

	@ManyToOne
	@MapsId("reductionplanId")
	@JoinColumn(name = "OPR_RP_ID")
	private ReductionPlan reductionPlan;

	@ManyToOne
	@MapsId("operationId")
	@JoinColumn(name = "OPR_OPE_ID_A")
	private Operation operation;

	@Column(name = "OPR_REDUCTIONRATE")
	private double reductionRate;

	// Constructeurs
	public OperationsReduction() {
	}

	public OperationsReduction(OperationsReductionId id, ReductionPlan reductionPlan, Operation operation, double reductionRate) {
		this.id = id;
		this.reductionPlan = reductionPlan;
		this.operation = operation;
		this.reductionRate = reductionRate;
	}

	// Getters et Setters
	public OperationsReductionId getId() {
		return id;
	}

	public void setId(OperationsReductionId id) {
		this.id = id;
	}

	public ReductionPlan getReductionPlan() {
		return reductionPlan;
	}

	public void setReductionPlan(ReductionPlan reductionPlan) {
		this.reductionPlan = reductionPlan;
	}

	public Operation getOperation() {
		return operation;
	}

	public void setOperation(Operation operation) {
		this.operation = operation;
	}

	public double getReductionRate() {
		return reductionRate;
	}

	public void setReductionRate(double reductionRate) {
		this.reductionRate = reductionRate;
	}


}