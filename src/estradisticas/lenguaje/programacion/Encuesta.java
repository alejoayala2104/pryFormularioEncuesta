package estradisticas.lenguaje.programacion;

public class Encuesta {

	private Encuestado encuestado;
	private String resLengProg;
	
	public Encuesta(Encuestado encuestado, String resLengProg) {
		this.encuestado = encuestado;
		this.resLengProg = resLengProg;
	}

	public Encuestado getEncuestado() {
		return encuestado;
	}

	public String getResLengProg() {
		return resLengProg;
	}
}
