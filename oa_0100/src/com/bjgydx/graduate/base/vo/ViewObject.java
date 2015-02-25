package com.bjgydx.graduate.base.vo;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 
 * @author KAI
 *
 * @param <K>
 */
public abstract class ViewObject <K extends Serializable> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected K id;
	
	public abstract K getId();

	@Override
	public int hashCode() {
		if(this.getId() == null) {
			return super.hashCode();
		}
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((this.getId() == null) ? 0 : this.getId().hashCode());
		return result;
	}
	
	
	/**
	 * check the equality of two {@link ViewObject} by id.
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null) {
			return false;
		}

		ViewObject<K> other;
		try {
			other = (ViewObject<K>) obj;
		} catch (ClassCastException e) {
			return false;
		}

		// check if both are entity (with id)
		if (this.getId() != null && other.getId() != null) {
			return this.getId().equals(other.getId());
		}
		return false;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("id", this.getId())
				.toString();
	}
	
	

}
