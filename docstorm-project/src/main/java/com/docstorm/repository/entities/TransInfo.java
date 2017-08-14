package com.docstorm.repository.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Table(name = "trans_info", uniqueConstraints = { @UniqueConstraint(columnNames = { "language_code", "document_id" }) })
@Entity
public class TransInfo extends DocTransInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "language_code", nullable = false)
	private String languageCode;

	@Column(name = "name", nullable = false)
	private String name;

	@OneToMany()
	private List<Body> bodies;

	public String getLanguageCode() {
		return this.languageCode;
	}

	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Body> getBodies() {
		return bodies;
	}

	public void setBodies(List<Body> bodies) {
		this.bodies = bodies;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((languageCode == null) ? 0 : languageCode.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		TransInfo other = (TransInfo) obj;
		if (languageCode == null) {
			if (other.languageCode != null)
				return false;
		} else if (!languageCode.equals(other.languageCode))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TransInfo [LanguageCode=" + languageCode + ", name=" + name + ", bodies=" + bodies + "]";
	}

}
