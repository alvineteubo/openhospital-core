package org.isf.reductionplan.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class OtherReductionId implements Serializable {

	@Column(name = "OTR_RP_ID")
	private int reductionplanId;

	@Column(name = "OTR_OTH_ID")
	private int pricesOthersId;

	public OtherReductionId() {
	}

	public OtherReductionId(int reductionplanId, int pricesOthersId) {
		this.reductionplanId = reductionplanId;
		this.pricesOthersId = pricesOthersId;
	}

	// Getters et Setters
	public int getReductionplanId() {
		return reductionplanId;
	}

	public void setReductionplanId(int reductionplanId) {
		this.reductionplanId = reductionplanId;
	}

	public int getPricesOthersId() {
		return pricesOthersId;
	}

	public void setPricesOthersId(int pricesOthersId) {
		this.pricesOthersId = pricesOthersId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		OtherReductionId that = (OtherReductionId) o;
		return reductionplanId == that.reductionplanId &&
						pricesOthersId == that.pricesOthersId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(reductionplanId, pricesOthersId);
	}

}
