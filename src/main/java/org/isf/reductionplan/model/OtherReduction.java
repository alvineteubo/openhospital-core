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

import org.isf.pricesothers.model.PricesOthers;
import org.isf.utils.db.Auditable;

@Entity
@Table(name = "OH_OTHERREDUCTION")
public class OtherReduction extends Auditable<String> {

	@EmbeddedId
	private OtherReductionId id;

	@ManyToOne
	@MapsId("reductionplanId")
	@JoinColumn(name = "OTR_RP_ID")
	private ReductionPlan reductionPlan;

	@ManyToOne
	@MapsId("pricesOthersId")
	@JoinColumn(name = "OTR_OTH_ID")
	private PricesOthers pricesOthers;

	@Column(name = "OTR_REDUCTIONRATE")
	private double reductionRate;

	public OtherReduction() {
	}

	public OtherReduction(OtherReductionId id, ReductionPlan reductionPlan, PricesOthers pricesOthers, double reductionRate) {
		this.id = id;
		this.reductionPlan = reductionPlan;
		this.pricesOthers = pricesOthers;
		this.reductionRate = reductionRate;
	}

	// Getters et setters
	public OtherReductionId getId() {
		return id;
	}

	public void setId(OtherReductionId id) {
		this.id = id;
	}

	public ReductionPlan getReductionPlan() {
		return reductionPlan;
	}

	public void setReductionPlan(ReductionPlan reductionPlan) {
		this.reductionPlan = reductionPlan;
	}

	public PricesOthers getPricesOthers() {
		return pricesOthers;
	}

	public void setPricesOthers(PricesOthers pricesOthers) {
		this.pricesOthers = pricesOthers;
	}

	public double getReductionRate() {
		return reductionRate;
	}

	public void setReductionRate(double reductionRate) {
		this.reductionRate = reductionRate;
	}

}