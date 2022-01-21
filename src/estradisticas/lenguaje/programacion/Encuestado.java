package estradisticas.lenguaje.programacion;

public class Encuestado {

	private String docID;
	private String tipoDoc;
	private String universidad;
	private String sexo;
	private FechaNacimiento fechaNac;
	private String email;
	
	public Encuestado(String docID, String tipoDoc, String universidad, String sexo, FechaNacimiento fechaNac,
			String email) {
		this.docID = docID;
		this.tipoDoc = tipoDoc;
		this.universidad = universidad;
		this.sexo = sexo;
		this.fechaNac = fechaNac;
		this.email = email;
	}

	public String getUniversidad() {
		return universidad;
	}

	public String getSexo() {
		return sexo;
	}

	public FechaNacimiento getFechaNac() {
		return fechaNac;
	}
}
