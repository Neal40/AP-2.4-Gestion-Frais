package model.DTO;




public class TypeFrais {

	private String idTypeFrais;
	private String libelleTypeFrais;
	private float montantTypeFrais;

	        
	        public TypeFrais(String unIdTypeFrais , String unLibelleTypeFrais, float unMontantTypeFrais ) {
	        	idTypeFrais = unIdTypeFrais;
	        	libelleTypeFrais = unLibelleTypeFrais;
	        	montantTypeFrais = unMontantTypeFrais;
	         }


			public String getIdTypeFrais() {
				return idTypeFrais;
			}


			public void setIdTypeFrais(String idTypeFrais) {
				this.idTypeFrais = idTypeFrais;
			}


			public String getLibelleTypeFrais() {
				return libelleTypeFrais;
			}


			public void setLibelleTypeFrais(String libelleTypeFrais) {
				this.libelleTypeFrais = libelleTypeFrais;
			}


			public float getMontantTypeFrais() {
				return montantTypeFrais;
			}


			public void setMontantTypeFrais(float montantTypeFrais) {
				this.montantTypeFrais = montantTypeFrais;
			}
	        

	   
	        
	    }


