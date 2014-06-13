package com.atrium.formulario;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class Gestion_BaseDatos extends SQLiteOpenHelper {
	// SQL DE CREACION DE LA TABLA
	String crear_tabla = "create table datos(nombre text,apellido text,estado_civil text,carnet integer)";
	private SQLiteDatabase db;

	// ARRANCAMOS EL MOTOR DE BASE DE DATOS
	public Gestion_BaseDatos(Context contexto, String nombre,
			CursorFactory factory, int version) {
		super(contexto, nombre, factory, version);
	}

	/**
	 * Proceso de alta.
	 * 
	 * @param datos
	 */
	public String alta_Formulario(List<Object> datos) {
		db = this.getWritableDatabase();
		// Creamos el registro a insertar como objeto ContentValues
		ContentValues nuevoRegistro = new ContentValues();
		nuevoRegistro.put("nombre", (String) datos.get(0));
		nuevoRegistro.put("apellido", (String) datos.get(1));
		nuevoRegistro.put("estado_civil", (String) datos.get(2));
		nuevoRegistro.put("carnet", ((Integer) datos.get(3)).intValue());
		String resultado = "Alta valida";
		// Insertamos el registro en la base de datos
		long respuesta = db.insert("datos", null, nuevoRegistro);
		if (respuesta == -1) {
			resultado = "error alta";
		}
		db.close();
		return resultado;
	}

	/**
	 * Proceso de baja. Es obligatorio el nombre y el apellido.
	 * 
	 * @param datos
	 * @return
	 */
	public String baja_Formulario(List<Object> datos) {
		db = this.getWritableDatabase();
		String nombre = (String) datos.get(0);
		String apellido = (String) datos.get(1);
		String datos_variables[] = { nombre, apellido };
		long resultado = db.delete("datos", "nombre=? and apellido=?",
				datos_variables);
		String mensaje = "baja correcta";
		if (resultado == -1) {
			mensaje = "error en la baja";
		}
		db.close();
		return mensaje;
	}

	/**
	 * Proceso de modificacion.
	 * 
	 * @param datos
	 * @return
	 */

	public String modificacion_formulario(List<Object> datos) {
		db = this.getWritableDatabase();
		// CREAMOS LOS DATOS DEL REGISTRO A MODIFICAR
		ContentValues nuevoRegistro = new ContentValues();
		nuevoRegistro.put("nombre", (String) datos.get(0));
		nuevoRegistro.put("apellido", (String) datos.get(1));
		nuevoRegistro.put("estado_civil", (String) datos.get(2));
		if (((Boolean) datos.get(3)).booleanValue()) {
			nuevoRegistro.put("carnet", new Integer(1));
		} else {
			nuevoRegistro.put("carnet", new Integer(0));
		}

		// CONDICION DE LA MODIFICACION
		String nombre = (String) datos.get(0);
		String apellido = (String) datos.get(1);
		String datos_variables[] = { nombre };
		String resultado = "Modificacion valida";
		long modificacion = db.update("datos", nuevoRegistro, "nombre=?",
				datos_variables);
		String mensaje = "modificacion correcta";
		if (modificacion == 0) {
			mensaje = "error en la modificacion";
		}
		db.close();
		return mensaje;
	}

	/**
	 * Proceso de consulta.
	 * 
	 * @param datos
	 * @return
	 */
	public List<Object> consulta_formulario(List<Object> datos) {
		List<Object> datos_respuesta = new ArrayList<Object>(0);
		// INSTANCIA DE LECTURA DE LA BD.
		db = this.getReadableDatabase();
		// CONDICIONES DE LA CONSULTA
		String nombre = (String) datos.get(0);
		String apellido = (String) datos.get(1);
		String datos_variables[] = { nombre, apellido };
		// CONSULTAS CON SQL Y VARIABLES
		Cursor cursor1 = db.rawQuery(
				"SELECT * FROM DATOS WHERE NOMBRE=? AND APELLIDO=?",
				datos_variables);

		// CONSULTAS CON QUERY Y VARIABLES. ADMITE ORDENACION AGRUPAMIENTO
		String[] campos = new String[] { "NOMBRE", "APELLIDO" };

		Cursor c2 = db.query("DATOS", campos, "NOMBRE=? AND APELLIDO=?",
				datos_variables, null, null, null);

		// RECORRIDO DEL CURSOR RESULTANTE
		// Nos aseguramos de que existe al menos un registro
		if (cursor1.moveToFirst()) {
			datos_respuesta.add(cursor1.getString(0));
			datos_respuesta.add(cursor1.getString(1));
			datos_respuesta.add(cursor1.getString(2));
			datos_respuesta.add(cursor1.getInt(3));
		} else {
			datos_respuesta = null;
		}
		return datos_respuesta;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// Se ejecuta la sentencia SQL de creación de la tabla
		db.execSQL(crear_tabla);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}
