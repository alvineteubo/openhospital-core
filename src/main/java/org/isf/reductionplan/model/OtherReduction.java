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
