package estradisticas.lenguaje.programacion;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AppEncuestaFX extends Application {

	private List<Encuesta> listaEncuestas = new ArrayList<>();
	private Map<String,Integer> lenguajesConteo = new HashMap<>();
	private ComboBox<String> cbxLenguaje;
	
	/*
	 * PROYECTO REALIZADO POR
	 * 
	 * ANDRÉS ALEJANDRO AYALA CHAMORRO 
	 * 
	 */
	
	@Override
	public void start(Stage primaryStage) throws Exception {

		//1. Creación del tabpane.
		TabPane tpnEncuesta = new TabPane();
		
		//2. Creación del tab para el formulario.
		Tab tabFormulario = new Tab("Formulario");
		tabFormulario.setContent(this.getFormulario());
		
		//3. Creación del tab para las estadísticas.
		Tab tabEstadísticas = new Tab("Estadísticas");
		tabEstadísticas.setContent(this.getEstadisticas());
		
		//4. Vincular los tabs al TabPane encuesta.
		tpnEncuesta.getTabs().addAll(tabFormulario,tabEstadísticas);
				
		//5. Vinculación del TabPane al root que será un HBox.
		HBox root = new HBox(tpnEncuesta);
		//Hacer que el tabpane se ajuste al tamaño de la ventana, contenido en el HBox.
		HBox.setHgrow(tpnEncuesta, Priority.ALWAYS);
				
		//Sección de presentación.
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/estradisticas/lenguaje/programacion/style.css").toExternalForm());
		primaryStage.setTitle("Encuesta de opinión: Lenguajes de programación");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	private GridPane getEstadisticas() {
		GridPane grpEstad = new GridPane();
		
		Text txtConsulta = new Text("Consulta por:");
		GridPane.setValignment(txtConsulta, VPos.TOP);
		GridPane.setVgrow(txtConsulta, Priority.ALWAYS);
		//Adicionando el GridPane.
		grpEstad.add(txtConsulta, 0, 0);
		
		//3.1 Controles para la consulta por universidad.
		ChoiceBox <String> choUniversidad = new ChoiceBox<>();
		choUniversidad.getItems().addAll("Universidad de Nariño","Universidad Mariana","Universidad CESMAG");
		Text txtUniversidad = new Text("Universidad");
		GridPane.setValignment(txtUniversidad, VPos.TOP);
		GridPane.setVgrow(txtUniversidad, Priority.ALWAYS);
		Button btnConU = new Button("Consultar");
		//Adicionando al GridPane
		grpEstad.add(choUniversidad, 0, 1);
		grpEstad.add(btnConU, 1, 1);
		grpEstad.add(txtUniversidad, 0, 2);
		
		//3.2 Controles para la consulta por sexo.
		ChoiceBox <String> choSexo = new ChoiceBox<>();
		choSexo.getItems().addAll("Masculino","Femenino");
		Text txtSexo = new Text("Sexo");
		GridPane.setValignment(txtSexo, VPos.TOP);
		GridPane.setVgrow(txtSexo, Priority.ALWAYS);
		Button btnConS = new Button("Consultar");
		//Adicionando al GridPane
		grpEstad.add(choSexo, 0, 3);
		grpEstad.add(btnConS, 1, 3);
		grpEstad.add(txtSexo, 0, 4);
		
		//3.3 Controles para la consulta por sexo.
		ChoiceBox <String> choRE = new ChoiceBox<>();
		choRE.getItems().addAll("16-18","19-21","22-24","25 o más");
		Text txtRangoEdad = new Text("Rango Edad");
		GridPane.setValignment(txtRangoEdad, VPos.TOP);
		GridPane.setVgrow(txtRangoEdad, Priority.ALWAYS);
		Button btnConRE = new Button("Consultar");
		//Adicionando al GridPane
		grpEstad.add(choRE, 0, 5);
		grpEstad.add(btnConRE, 1, 5);
		grpEstad.add(txtRangoEdad, 0, 6);
		
		//3.4 Controles para la presentación de las estadísticas.
		Label lblTitEst= new Label("Reporte estadísitico:");
		lblTitEst.setAlignment(Pos.BOTTOM_LEFT);
		lblTitEst.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		lblTitEst.setAlignment(Pos.BOTTOM_LEFT);
		TextArea txaSalidaEsta = new TextArea();
		//Adicionando al gridpane
		grpEstad.add(lblTitEst, 0, 7);
		grpEstad.add(txaSalidaEsta, 0, 8,2,2);
		
		//Hace que el textarea no sea editable.
		txaSalidaEsta.setEditable(false);
		
		//Tamaño prefefinido para los controles.
		choUniversidad.setMaxSize(500, 30);
		choSexo.setMaxSize(500, 30);
		choRE.setMaxSize(500, 30);		

		//ColumnConstraints en general.	
		ColumnConstraints reglaCols = new ColumnConstraints(500,700,1500);
		reglaCols.setHgrow(Priority.ALWAYS);
		grpEstad.getColumnConstraints().addAll(reglaCols);
		
		//RowConstraints en general.
		RowConstraints reglaRowsControles = new RowConstraints(30,30,30);
		reglaRowsControles.setVgrow(Priority.ALWAYS);
		//RowConstraint exclusivos para los label
		RowConstraints reglaRowsLabel = new RowConstraints(10,10,40);
		//RowConstraint para Reporte estadísticas
		RowConstraints reglaRowsReporEst = new RowConstraints(50,50,50);
		//RowConstraint para el TextArea
		RowConstraints reglaRowsTxaReporte = new RowConstraints(210,300,500);		
		grpEstad.getRowConstraints().addAll(reglaRowsLabel,reglaRowsControles,reglaRowsLabel,
				reglaRowsControles,reglaRowsLabel,reglaRowsControles,reglaRowsLabel,reglaRowsReporEst,reglaRowsTxaReporte);
		
		//Añadir espaciado
		grpEstad.setVgap(10);
		
		//Añadir margen al gridpane
		grpEstad.setPadding(new Insets(10,15,20,5));
		
		//Añadir el texto
		grpEstad.setId("texto");

		
		//3.5 Programación del botón de consulta por universidad.
		btnConU.setOnAction(event ->{
			//Validar que se seleccione algún valor del choicebox.
			if(choUniversidad.getValue()==null){
				mostrarAlerta(AlertType.ERROR, "ERROR: Universidad", "Universidad inválida", "Por favor seleccione una opción de la casilla de Universidad");
				return;
			}
			this.cbxLenguaje.getItems().forEach(lenguaje ->{
				//Inicialización de el HashMap con los lenguajes de prog
				//a 0 para su conteo.
				//Por cada lenguaje, crear una clave y asignarle un valor de 0.
				this.lenguajesConteo.put(lenguaje, 0);
			});
			String vSelUniv = choUniversidad.getValue();
			//Recorrido de la lista de encuestas, una por una para analizar.
			this.listaEncuestas.forEach(encuesta ->{				
				if(vSelUniv.equalsIgnoreCase(encuesta.getEncuestado().getUniversidad())) {
					//Obtenemos la respuesta por lenguaje de prog de la encuesta.
					String lengProgSel = encuesta.getResLengProg();
					//get() devuelve el valor de esa clave
					int cnActualLP = this.lenguajesConteo.get(lengProgSel);
					cnActualLP++;;
					//put(clave,valor)
					//Si la clave ya existe, el put() cambia el valor de la clave.
					//Sino, la crea con dicho valor.
					this.lenguajesConteo.put(lengProgSel,cnActualLP);
				}
			});
			
			StringBuffer stbEstadisticas = new StringBuffer();
			stbEstadisticas.append("Total de encuestados: " + this.listaEncuestas.size() + "\n");
			double cnEncUniSel = 0.0;
			for(Map.Entry<String,Integer> entry : this.lenguajesConteo.entrySet()) {
				//Para obtener la clave sería: entry.getKey()
				//Para obtener el valor sería: entry.getValue();
				cnEncUniSel += entry.getValue();
			}
			stbEstadisticas.append("Total de estudiantes de " + vSelUniv +
					" : " + cnEncUniSel + "\n");
			stbEstadisticas.append("---------------------------------------------\n");
			//Truco para hacer final una variable dentro de una lambda.
			final double crEncUniSelAux = cnEncUniSel;
			
			this.lenguajesConteo.forEach((kLenguaje,vConteo) ->{
				stbEstadisticas.append(kLenguaje + " ... " +Math.round((vConteo*100/crEncUniSelAux)) + "% \n");
			});
			
			txaSalidaEsta.setText(stbEstadisticas.toString());
		});
		
		
		//3.6 Programación del botón consultar según sexo.
		btnConS.setOnAction(event ->{				
			//Validar que se seleccione algún valor del choicebox.
			if(choSexo.getValue()==null){
				mostrarAlerta(AlertType.ERROR, "ERROR: Sexo", "Sexo inválido", "Por favor seleccione una opción de la casilla de Sexo");
				return;
			}
			
			this.cbxLenguaje.getItems().forEach(lenguaje ->{
				//Inicialización de el HashMap con los lenguajes de prog
				//a 0 para su conteo.
				//Por cada lenguaje, crear una clave y asignarle un valor de 0.
				this.lenguajesConteo.put(lenguaje, 0);
			});
			
			String vSelSexo = choSexo.getValue();
			//Recorrido de la lista para obtener los encuestados.
			this.listaEncuestas.forEach(encuesta->{
				if(encuesta.getEncuestado().getSexo().equals(vSelSexo)) {
					//Se obtiene el lenguaje de programación que seleccionó el encuestado
					//para usarlo como key.
					String lengProgSel = encuesta.getResLengProg();
					//Se obtiene el valor correspondiente al lenguaje que seleccionó el encuestado.
					int cnActualLP = this.lenguajesConteo.get(lengProgSel);
					//Se le suma 1 a dicho valor.
					cnActualLP++;
					//Se actualiza el nuevo valor en el lenguaje correspondiente.
					this.lenguajesConteo.put(lengProgSel,cnActualLP);
				}
			});
			
			StringBuffer stbSexo = new StringBuffer();
			stbSexo.append("Total de encuestados: " + listaEncuestas.size() + "\n");
			double cnEncSexoSel = 0.0;
			//Se obtiene el total de encuestados registrados en lenguajesConteo
			//para obtener el total de encuestados por sexo.
			for(Map.Entry<String,Integer> entry : this.lenguajesConteo.entrySet()) {
				cnEncSexoSel += entry.getValue();
			}
			stbSexo.append("Total de encuestados con sexo " + vSelSexo +
					" : " + cnEncSexoSel + "\n");
			stbSexo.append("---------------------------------------------\n");
			
			final double cnEncSexoSelAux = cnEncSexoSel;
			
			this.lenguajesConteo.forEach((kLenguaje,vConteo) ->{
				stbSexo.append(kLenguaje + " ... " +Math.round((vConteo*100/cnEncSexoSelAux)) + "% \n");
			});			
			txaSalidaEsta.setText(stbSexo.toString());			
		});
		
		//3.7 Programación del botón de consulta según rango de edad.
		btnConRE.setOnAction(event ->{
			
			//Validar que se seleccione algún valor del choicebox.
			if(choRE.getValue()==null){
				mostrarAlerta(AlertType.ERROR, "ERROR: Rango de edad", "Rango de edad inválido", "Por favor seleccione un rango de edad");
				return;
			}
			
			this.cbxLenguaje.getItems().forEach(lenguaje ->{
				//Inicialización de el HashMap con los lenguajes de prog a 0 para su conteo.
				//Por cada lenguaje, crear una clave y asignarle un valor de 0.
				this.lenguajesConteo.put(lenguaje, 0);
			});
			
			//Obtiene el rango de edad seleccionado.
			String vSelRE = choRE.getValue();
			int minRE,maxRE;			
			
			//Obtener el mínimo y el máximo del rango de edad.
			switch(vSelRE) {
			case "16-18": minRE=16; maxRE=18; break;
			case "19-21": minRE=19; maxRE=21; break;
			case "22-24": minRE=22; maxRE=24; break;
			case "25 o más": minRE=25; maxRE=Integer.MAX_VALUE; break;
			default: minRE=0; maxRE=0;break;			
			}
						
			//Se recorre la lista de encuestas para obtener los encuestados.
			this.listaEncuestas.forEach(encuesta ->{
				//Se obtiene un objeto LocalDate con la fecha de nacimiento del encuestado.
				LocalDate fechaNac = LocalDate.of(encuesta.getEncuestado().getFechaNac().getAño(),
						encuesta.getEncuestado().getFechaNac().getMes(),
						encuesta.getEncuestado().getFechaNac().getDia());
				LocalDate fechaActual = LocalDate.now();
				//Se importa la librería ChoronoUnit para calcular los años(YEARS) entre(between)
				//la fecha de nacimiento del encuestado(fechaNac) y la fecha actual del sistema(LocalDate.now()).
				long edadEncuestado = ChronoUnit.YEARS.between(fechaNac, fechaActual);
				
				//Verificar si la edad del encuestado está dentro del rango de valores seleccionado.
				if(verificarNumeroEstaEnRango(edadEncuestado, minRE, maxRE)) {
					//Se obtiene el lenguaje de programación que seleccionó el encuestado
					//para usarlo como key.
					String lengProgSel = encuesta.getResLengProg();
					//Se obtiene el valor correspondiente al lenguaje que seleccionó el encuestado.
					int cnActualLP = this.lenguajesConteo.get(lengProgSel);
					//Se le suma 1 a dicho valor.
					cnActualLP++;
					//Se actualiza el nuevo valor en el lenguaje correspondiente.
					this.lenguajesConteo.put(lengProgSel,cnActualLP);
				}
			});
			
			StringBuffer stbRE = new StringBuffer();
			stbRE.append("Total de encuestados: " + listaEncuestas.size() + "\n");
			double cnEncRESel = 0.0;
			//Se obtiene el total de encuestados registrados en lenguajesConteo
			//para obtener el total de encuestados por rango de edad.
			for(Map.Entry<String,Integer> entry : this.lenguajesConteo.entrySet()) {
				cnEncRESel += entry.getValue();
			}
			stbRE.append("Total de encuestados entre rango de edad " + vSelRE +
					" : " + cnEncRESel + "\n");
			stbRE.append("---------------------------------------------\n");
			
			final double cnEncRESelAux = cnEncRESel;
			//Se redondeó a dos decimales. Math.round() también elimina el NaN.
			this.lenguajesConteo.forEach((kLenguaje,vConteo) ->{
				stbRE.append(kLenguaje + " ... " +Math.round((vConteo*100/cnEncRESelAux)) + "% \n");
			});			
			txaSalidaEsta.setText(stbRE.toString());			
		});		
		
		return grpEstad;
	}
	
	//Método que determina si un número está dentro de un rango de enteros.
	public boolean verificarNumeroEstaEnRango(long numero,int min,int max) {
		boolean estaEnRango = false;
		if(min <= numero && numero <= max) {
			estaEnRango = true;
		}
		return estaEnRango;
	}
		
	public void mostrarAlerta(AlertType tipoAlerta,String tituloVentana,String tituloMensaje,String mensaje) {
		Alert alerta = new Alert(tipoAlerta);
		alerta.setTitle(tituloVentana);
		alerta.setHeaderText(tituloMensaje);
		alerta.setContentText(mensaje);
		alerta.showAndWait();		
	}

	private GridPane getFormulario() {
		GridPane grpForm = new GridPane();
		
		//2.1. Controles para el ingreso del documento de identidad.
		TextField txfDocId = new TextField();
		txfDocId.setMaxSize(Double.MAX_VALUE, 30);
		Text txtDocId = new Text("Documento de identidad");
		GridPane.setValignment(txtDocId, VPos.TOP);
		GridPane.setVgrow(txtDocId, Priority.ALWAYS);
		// Adicionamos al GridPane grpForm.
		grpForm.add(txfDocId, 0, 0);
		grpForm.add(txtDocId, 0, 1);
			
		//2.2. Controles para la selección del tipo de documento. 
		ChoiceBox<String> choTipoDoc = new ChoiceBox<>();
		choTipoDoc.setMaxSize(Double.MAX_VALUE, 30);
		Text txtTipoDoc = new Text("Tipo de documento");
		GridPane.setValignment(txtTipoDoc, VPos.TOP);
		GridPane.setVgrow(txtTipoDoc, Priority.ALWAYS);
		// Adición al GridPane
		grpForm.add(choTipoDoc, 1, 0);
		grpForm.add(txtTipoDoc, 1, 1);
		//Opciones
		choTipoDoc.getItems().addAll("Cédula","Tarjeta de identidad","Pasaporte");
//		Que el primero sea el que aparezca seleccionado por defecto.
//		choTipoDoc.getSelectionModel().selectFirst();
		
		//2.3. Controles para la selección de la universidad.
		ChoiceBox<String> choUniversidad = new ChoiceBox<>();
		choUniversidad.setMaxSize(Double.MAX_VALUE, 30);
		choUniversidad.getItems().addAll("Universidad de Nariño",
				"Universidad Mariana","Universidad CESMAG");
		Text txtUniversidad = new Text("Universidad");
		GridPane.setValignment(txtUniversidad, VPos.TOP);
		GridPane.setVgrow(txtUniversidad, Priority.ALWAYS);
		
		grpForm.add(choUniversidad, 2, 0);
		grpForm.add(txtUniversidad, 2, 1);
		
		//2.4. Controles para la selección del sexo.
		ChoiceBox<String> choSexo = new ChoiceBox<>();
		choSexo.setMaxSize(Double.MAX_VALUE, 30);
		choSexo.getItems().addAll("Masculino","Femenino");
		Text txtSexo = new Text("Sexo");
		GridPane.setValignment(txtSexo, VPos.TOP);
		GridPane.setVgrow(txtSexo, Priority.ALWAYS);
		grpForm.add(choSexo, 0, 2);
		grpForm.add(txtSexo, 0, 3);
		
		//2.5 Controles para la selección de la fecha de nacimiento. (Año)
		Label lblFN = new Label("Fecha de Nacimiento");		
		lblFN.setAlignment(Pos.BOTTOM_LEFT);
		ChoiceBox<Integer> choAño = new ChoiceBox<>();		
		choAño.setMaxSize(Double.MAX_VALUE, 30);
		for(int año=1970; año< 2005; año++) {
			choAño.getItems().add(año);
		}		
		Text txtAño = new Text("Año");
		grpForm.add(lblFN, 0, 4);
		grpForm.add(txtAño, 0, 6);
		choAño.setId("ano");	
		//2.6. Controles para la selección de la fecha de nacimiento. (Mes)
		ComboBox<Integer> cbxMes = new ComboBox<>();
		cbxMes.setMaxSize(Double.MAX_VALUE, 30);
		for(int mes=1;mes<=12;mes++) {
			cbxMes.getItems().add(mes);
		}
		Text txtMes = new Text("Mes");
			
		
		//2.7. Controles para la selección de la fecha de nacimiento. (Día)
		ComboBox<Integer> cbxDia = new ComboBox<>();
		cbxDia.setMaxSize(Double.MAX_VALUE, 30);
		for(int dia=1;dia<=31;dia++) {
			cbxDia.getItems().add(dia);
		}
		Text txtDia = new Text("Día");
		
		//Se agregan HBox para que el año, el mes y el día se vean juntos.
		HBox hbxFechaNacCbx = new HBox(10,choAño,cbxMes,cbxDia);
		HBox hbxFechaNacTxt = new HBox(44,txtAño,txtMes,txtDia);
		GridPane.setValignment(hbxFechaNacTxt, VPos.TOP);
		GridPane.setVgrow(hbxFechaNacTxt, Priority.ALWAYS);
		
		grpForm.add(hbxFechaNacCbx, 0, 5);
		grpForm.add(hbxFechaNacTxt, 0, 6);
		
		//2.8. Controles para la entrada del E-mail. TextField
		TextField txfEmail = new TextField();
		txfEmail.setMaxSize(Double.MAX_VALUE, 30);		
		Text txtEmail = new Text("Email");
		GridPane.setValignment(txtEmail, VPos.TOP);
		GridPane.setVgrow(txtEmail, Priority.ALWAYS);
		grpForm.add(txfEmail, 0, 7);
		grpForm.add(txtEmail, 0, 8);
		//2.9 Controles para la selección de la pregunta/respuesta. ComboBox Cadenas.
		cbxLenguaje = new ComboBox<>();
		cbxLenguaje.setMaxSize(Double.MAX_VALUE, 30);
		cbxLenguaje.getItems().addAll("Java","Python","C++","PHP","C","Go","Ruby");
		Label lblLenguaje = new Label("¿Qué lenguaje de programación prefiere para desarrollar Software?");
		lblLenguaje.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		lblLenguaje.setAlignment(Pos.BOTTOM_LEFT);
		lblLenguaje.setId("lapregunta");
		grpForm.add(lblLenguaje, 0, 9);
		grpForm.add(cbxLenguaje, 0, 10);
		
				
		//2.10. Control del botón de respuesta.
		Button btnResponder = new Button("Responder");
		btnResponder.setOnAction(event -> {

			if(txfDocId.getText()==null || choTipoDoc.getValue()==null || choUniversidad.getValue()==null
					|| choSexo.getValue()==null || choAño.getValue()==null || cbxMes.getValue()==null
					|| cbxDia.getValue()==null || txfEmail.getText()==null || cbxLenguaje.getValue()==null) {
				mostrarAlerta(AlertType.ERROR, "Campos vacíos", "Campos vacíos", "Por favor rellene todos los campos.");	
				return;
			}
			
			String vDocId = txfDocId.getText();
			String vTipoDoc = choTipoDoc.getValue();
			String vUniversidad = choUniversidad.getValue();
			String vSexo = choSexo.getValue();
			int vAñoNac = choAño.getValue();
			int vMesNac = cbxMes.getValue();
			int diaNac = cbxDia.getValue();
			String vEmail = txfEmail.getText();
			String vLengProg = cbxLenguaje.getValue();
			
			//Validación que el docId tenga al menos 5 dígitos.
			if(vDocId.trim().length() >= 5) {
				//Validacion email
				if(vEmail.lastIndexOf(".") > vEmail.indexOf("@")) {
					
					//Fecha nac
					FechaNacimiento vFN = new FechaNacimiento(diaNac, vMesNac, vAñoNac);
					Encuestado vEncuestado = new Encuestado(vDocId, vTipoDoc, vUniversidad, vSexo, vFN, vEmail);
					Encuesta vEncuesta= new Encuesta(vEncuestado, vLengProg);
					listaEncuestas.add(vEncuesta);
				}
				else {
					Alert alerta = new Alert(AlertType.ERROR);
					alerta.setTitle("ERROR Email");
					alerta.setHeaderText("Email incorrecto");
					alerta.setContentText("El email debe contener el formato con @ y un punto (.).");
					alerta.showAndWait();
				}
			}else {
				Alert alerta = new Alert(AlertType.ERROR);
				alerta.setTitle("ERROR Doc Id");
				alerta.setHeaderText("Documento de identidad inválido");
				alerta.setContentText("El documento de identidad debe tener al menos 5 dígitos.");
				alerta.showAndWait();
			}
			
		});
		grpForm.add(btnResponder, 2, 11);
		
		//Añadiendo espacio entre elementos.
		grpForm.setHgap(3);
		grpForm.setVgap(13);
		
		//Constraints para mejorar la disposición de los controles.
		
		//ColumnConstraints para la primera Columna con TextFields
		ColumnConstraints reglaCols0 = new ColumnConstraints(450,600,900);
		reglaCols0.setHgrow(Priority.ALWAYS);
		//ColumnConstraints para el resto de columnas
		ColumnConstraints reglaCols = new ColumnConstraints(180,300,500);
		reglaCols.setHgrow(Priority.ALWAYS);
		grpForm.getColumnConstraints().addAll(reglaCols0,reglaCols,reglaCols);
		
		//RowConstraints para los controles
		RowConstraints reglaRowsControles = new RowConstraints(30,30,30);
		reglaRowsControles.setVgrow(Priority.ALWAYS);
		//RowConstraints exclusivos para los label
		RowConstraints reglaRowsLabel = new RowConstraints(10,30,75);
		grpForm.getRowConstraints().addAll(reglaRowsControles,reglaRowsLabel,reglaRowsControles,reglaRowsLabel,
				reglaRowsLabel,reglaRowsControles,reglaRowsLabel,reglaRowsControles,reglaRowsLabel,reglaRowsLabel,
				reglaRowsControles);
		
		//Reduciendo el tamaño de los controles para proporcionalidad al texto que contienen.
		choSexo.setMaxWidth(180);
		//Fecha de Nacimiento
		choAño.setMaxWidth(70);
		cbxMes.setMaxWidth(70);
		cbxDia.setMaxWidth(70);		
	
		//Añadiendo margen al gridpane
		grpForm.setPadding(new Insets(10,20,20,5));

		//Colocando el botón hacia la derecha.
		GridPane.setHalignment(btnResponder, HPos.RIGHT);
		
		//Añadir el estilo a los text con la etiqueta personalizada.
		//El nodo Text no se identifica por si mismo en javafx css.
		grpForm.setId("texto");

		return grpForm;
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
