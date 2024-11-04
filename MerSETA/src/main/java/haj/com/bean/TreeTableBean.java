package haj.com.bean;

import java.io.Serializable;
import java.util.Locale;

// TODO: Auto-generated Javadoc
/**
 * The Class AmountBean.
 */
public class TreeTableBean implements Serializable {
	private String name;

	private String size;

	private String type;
	
	private String style;

	public TreeTableBean(String name, String size, String type) {
		super();
		this.name = name;
		this.size = size;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	// Eclipse Generated hashCode and equals
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((size == null) ? 0 : size.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		TreeTableBean other = (TreeTableBean) obj;
		if (name == null) {
			if (other.name != null) return false;
		} else if (!name.equals(other.name)) return false;
		if (size == null) {
			if (other.size != null) return false;
		} else if (!size.equals(other.size)) return false;
		if (type == null) {
			if (other.type != null) return false;
		} else if (!type.equals(other.type)) return false;
		return true;
	}

	@Override
	public String toString() {
		return name;
	}

	public int compareTo(TreeTableBean document) {
		return this.getName().compareTo(document.getName());
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}
}
