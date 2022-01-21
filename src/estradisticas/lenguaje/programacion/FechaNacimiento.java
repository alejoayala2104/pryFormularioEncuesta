package estradisticas.lenguaje.programacion;

public class FechaNacimiento {
	
	private int dia;
	private int mes;
	private int año;
	
	public FechaNacimiento(int dia, int mes, int año) {
		this.dia = dia;
		this.mes = mes;
		this.año = año;
	}

	public int getDia() {
		return dia;
	}

	public int getMes() {
		return mes;
	}

	public int getAño() {
		return año;
	}	
}
