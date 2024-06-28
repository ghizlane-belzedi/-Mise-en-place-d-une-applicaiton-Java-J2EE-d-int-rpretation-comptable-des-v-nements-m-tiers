package ma.inetum.brique.bean;

import java.util.LinkedList;
import java.util.List;

public class FluxEditView {

	private Long fluxId;
	public Long getFluxId() {
		return fluxId;
	}
	public void setFluxId(Long fluxId) {
		this.fluxId = fluxId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public List<FluxField> getFields() {
		return fields;
	}
	public void setFields(List<FluxField> fields) {
		this.fields = fields;
	}
	private String code;
	private String nom;
	 

	public static class FluxField implements Comparable<FluxField>{
		private Long id;
		private String Code;
		private String description;
		private int ordre;
		private boolean visible;
		public FluxField(Long id, String code, String description, int ordre, boolean visible) {
			super();
			this.id = id;
			this.Code = code;
			this.description = description;
			this.ordre = ordre;
			this.visible = visible;
		}
		@Override
		public int compareTo(FluxField o) {
			if(!o.isVisible() && !this.visible )
				return 0;
			if(!o.visible)
				return -1;
			if(!visible)
				return 1;
			return -1 * (o.ordre - this.ordre );
		}
		@Override
		public boolean equals(Object o) {
			if (o == this) { 
	            return true; 
	        } 
	  
	        if (!(o instanceof FluxField)) {
	            return false; 
	        } 
	          
	        FluxField p = (FluxField) o; 
	          
	        return Long.compare(id, p.id) == 0; 
		}
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getCode() {
			return Code;
		}
		public void setCode(String code) {
			Code = code;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public int getOrdre() {
			return ordre;
		}
		public void setOrdre(int ordre) {
			this.ordre = ordre;
		}
		public boolean isVisible() {
			return visible;
		}
		public void setVisible(boolean visible) {
			this.visible = visible;
		}
		
		
	}
	private List<FluxField> fields = new LinkedList<>();
}

