package com.helpfull.egg.enums;

<<<<<<< HEAD
public enum Interes {
 
	SALIDAS(1,"salidas a pasear"), MUSICA(2, "tipo de musica"), COCINAR(3,"cocinar dulce o salado"), 
	LECTURA(4,"leer libros o revistas o diarios"), HABLAR(4, "hablar o cantar"), ESCUCHAR(5,"escuchar"), 
	COMPARTIR(6,"compartir"), MATE(7,"tomar mate"), REGALOS(8,"te gustan los regalos"), COMER(9, "comidas dulces o saladas");
	
	private Integer codigo;
	private String valor;
	
	private Interes(Integer codigo, String valor) {
		this.codigo = codigo;
		this.valor = valor;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
	
=======
@E
public enum Interes {
	FUTBOL, MATE, CINE, CAFE, MUSICA
>>>>>>> amigo/familiar_acargo
}
