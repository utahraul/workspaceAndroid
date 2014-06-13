package com.atrium.formulario;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * Proceso de captura de datos para su posterior tratamiento en otra actividad.
 * 
 * @author Profesor
 * 
 */
public class Formulario extends Activity {

	// PROPIEDADES DEL FORMULARIO
	private EditText texto_nombre;
	private EditText texto_apellido;
	private CheckBox carnet;
	private RadioGroup estado_civil;
	private List<Object> datos;
	private String resultado_bd;
	private TextView etiqueta_respuesta;

	// AUXILIAR PARA EL TRATAMIENTO CON LA BD.
	private Gestion_BaseDatos gestion_bd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_formulario);

		// CREAMOS/ARRANCAMOS LA BASE DE DATOS
		gestion_bd = new Gestion_BaseDatos(this, "DBformulario", null, 1);

		// BUSCARLOS COMPONENTES DEL FORMULARIO
		texto_nombre = (EditText) findViewById(R.id.TxtNombre);
		texto_apellido = (EditText) findViewById(R.id.TxtApellido);
		carnet = (CheckBox) findViewById(R.id.Chkcarnet);
		estado_civil = (RadioGroup) findViewById(R.id.Grupo);

		// ETIQUETA PAERA MOSTRAR EL RESULTADO DE LA OPERACION
		etiqueta_respuesta = (TextView) findViewById(R.id.respuesta);

		// BOTONES PARA DETECTAR LAS ORDENES DEL USUARIO
		Button boton_alta = (Button) findViewById(R.id.boton_alta);
		Button boton_baja = (Button) findViewById(R.id.boton_baja);
		Button boton_modificacion = (Button) findViewById(R.id.boton_modificacion);
		Button boton_consulta = (Button) findViewById(R.id.boton_consulta);

		// ***** AREA DE EVENTOS *****
		boton_alta.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View boton) {
				// CARGAMOS DATOS DEL FORMULARIO
				datos = leer_Formulario();
				resultado_bd = gestion_bd.alta_Formulario(datos);
				// MOSTRAMOS EL RESULTADO DEL PROCESO EN LA BASE DE DATOS
				etiqueta_respuesta.setText(resultado_bd);
				Log.i("traza", datos.toString());
			}
		});
		boton_baja.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View boton) {
				// CARGAMOS DATOS DEL FORMULARIO
				datos = leer_Formulario();
				resultado_bd = gestion_bd.baja_Formulario(datos);
				// MOSTRAMOS EL RESULTADO DEL PROCESO EN LA BASE DE DATOS
				etiqueta_respuesta.setText(resultado_bd);
				Log.i("traza", datos.toString());
			}
		});
		boton_modificacion.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View boton) {
				// CARGAMOS DATOS DEL FORMULARIO
				datos = leer_Formulario();
				resultado_bd = gestion_bd.modificacion_formulario(datos);
				// MOSTRAMOS EL RESULTADO DEL PROCESO EN LA BASE DE DATOS
				etiqueta_respuesta.setText(resultado_bd);
				Log.i("traza", datos.toString());
			}
		});

		boton_consulta.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View boton) {
				// CARGAMOS DATOS DEL FORMULARIO
				datos = leer_Formulario();
				List<Object> datos_consulta = gestion_bd
						.consulta_formulario(datos);
				if (datos_consulta != null) {
					cargar_Formulario(datos_consulta);
				}
			}
		});
	}

	/**
	 * Proceso de carga del formulario con los datos obtenidos en la consulta
	 * 
	 * @param datos
	 */
	private void cargar_Formulario(List<Object> datos) {
		// CARGAMOS EL FORMULARIO CON LOS DATOS OBTENIDOS EN EL LA
		// CONSULTA
		texto_nombre.setText(String.valueOf(datos.get(0)));
		texto_apellido.setText(String.valueOf(datos.get(1)));
		if (((Integer) datos.get(3)).intValue() == 1) {
			// TIENE CARNET
			carnet.setChecked(true);
		} else {
			// NO TIENEN CARNET
			carnet.setChecked(false);
		}
		// CONVERSION DEL VALOR ESTADO CIVIL
		String estado_civil = (String) datos.get(2);
		if (estado_civil.equals("casado")) {
			((RadioButton) findViewById(R.id.casado)).setChecked(true);
		}
		if (estado_civil.equals("divorciado")) {
			((RadioButton) findViewById(R.id.divorciado)).setChecked(true);
		}

		if (estado_civil.equals("soltero")) {
			((RadioButton) findViewById(R.id.soltero)).setChecked(true);
		}

		if (estado_civil.equals("viudo")) {
			((RadioButton) findViewById(R.id.viudo)).setChecked(true);
		}
		if (estado_civil.equals("sin estado")) {
			((RadioButton) findViewById(R.id.casado)).setChecked(false);
			((RadioButton) findViewById(R.id.divorciado)).setChecked(false);
			((RadioButton) findViewById(R.id.soltero)).setChecked(false);
			((RadioButton) findViewById(R.id.viudo)).setChecked(false);
		}
	}

	/**
	 * Proceso de captura de datos del formulario.
	 * 
	 * @return
	 */
	private List<Object> leer_Formulario() {
		// RECOGER VALORES TECLEADOS EN EL FORMULARIO
		// CONVERSION DEL VALOR ESTADO CIVIL
		int chequeado = estado_civil.getCheckedRadioButtonId();
		String opcion_chequeada;
		switch (chequeado) {
		case R.id.casado:
			opcion_chequeada = "casado";
			break;
		case R.id.divorciado:
			opcion_chequeada = "divorciado";
			break;
		case R.id.soltero:
			opcion_chequeada = "soltero";
			break;
		case R.id.viudo:
			opcion_chequeada = "viudo";
			break;
		default:
			opcion_chequeada = "sin estado";
			break;
		}
		// CARGA EN VARIABLES
		String nombre = texto_nombre.getText().toString();
		String apellidos = texto_apellido.getText().toString();
		String estado_civil = opcion_chequeada;
		// CONVERSION DE CARNET
		Boolean carnet_elegido = new Boolean(carnet.isChecked());
		// PASO A LA COLECCION DE DE DATOS
		List<Object> lista_datos = new ArrayList<Object>();
		lista_datos.add(nombre);
		lista_datos.add(apellidos);
		lista_datos.add(estado_civil);
		lista_datos.add(carnet_elegido);
		return lista_datos;
	}
}
