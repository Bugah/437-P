package br.unicamp.mc437.client.datatypes;

import java.io.Serializable;

public class CategoriasSubCategorias  implements Serializable {

	
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private int id;
		private SubCategoria subCategoria;
		private Categoria categoria;
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public SubCategoria getSubCategoria() {
			return subCategoria;
		}
		public void setSubCategoria(SubCategoria subCategoria) {
			this.subCategoria = subCategoria;
		}
		public Categoria getCategoria() {
			return categoria;
		}
		public void setCategoria(Categoria categoria) {
			this.categoria = categoria;
		}
		
		
}
