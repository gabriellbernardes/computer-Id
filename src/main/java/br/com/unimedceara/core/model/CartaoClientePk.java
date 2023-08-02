package br.com.unimedceara.core.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartaoClientePk implements Serializable{

	private static final long serialVersionUID = -7682144503669254752L;

	private BigDecimal codUnimed;
	private String dvCarteira;
	private BigDecimal codCarteira;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codCarteira == null) ? 0 : codCarteira.hashCode());
		result = prime * result + ((codUnimed == null) ? 0 : codUnimed.hashCode());
		result = prime * result + ((dvCarteira == null) ? 0 : dvCarteira.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CartaoClientePk other = (CartaoClientePk) obj;
		if (codCarteira == null) {
			if (other.codCarteira != null)
				return false;
		} else if (!codCarteira.equals(other.codCarteira))
			return false;
		if (codUnimed == null) {
			if (other.codUnimed != null)
				return false;
		} else if (!codUnimed.equals(other.codUnimed))
			return false;
		if (dvCarteira == null) {
			if (other.dvCarteira != null)
				return false;
		} else if (!dvCarteira.equals(other.dvCarteira))
			return false;
		return true;
	}
}