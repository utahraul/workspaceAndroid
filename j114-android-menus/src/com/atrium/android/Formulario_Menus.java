package com.atrium.android;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * Ejemplo de creacion y uso de menus en un activity co ndiferentes opciones de
 * creacion.
 * 
 * @author Juan Antonio Solves Garcia.
 * @version 1.0 24-10-2012.
 */
public class Formulario_Menus extends Activity {

	private static final int MNU_OPC1 = 1;
	private static final int MNU_OPC2 = 2;
	private static final int MNU_OPC3 = 3;

	private static final int SMNU_OPC1 = 31;
	private static final int SMNU_OPC2 = 32;

	private TextView lblMensaje;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.formulario__menus);

		lblMensaje = (TextView) findViewById(R.id.LblMensaje);
	}

	/**
	 * Metodo en donde establecemos la existencia de un menu y su creacion de
	 * dos formas distintas.
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Alternativa 1. EL MENU SE CREA A PARTIR DE UN FICHERO XML
		getMenuInflater().inflate(R.menu.formulario__menus, menu);

		// Alternativa 2. EL MENU SE CREA PROGRAMATICAMENTE.

		// menu.add(Menu.NONE, MNU_OPC1, Menu.NONE,
		// "Opcion1").setIcon(R.drawable.tag);
		// menu.add(Menu.NONE, MNU_OPC2, Menu.NONE,
		// "Opcion2").setIcon(R.drawable.filter);

		// SubMenu smnu = menu.addSubMenu(Menu.NONE, MNU_OPC3, Menu.NONE,
		// "Opcion3").setIcon(R.drawable.chart);
		// smnu.add(Menu.NONE, SMNU_OPC1, Menu.NONE, "Opcion 3.1");
		// smnu.add(Menu.NONE, SMNU_OPC2, Menu.NONE, "Opcion 3.2");

		return true;
	}

	/**
	 * Tratamiento del evento de menu.
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.MnuOpc1:
			lblMensaje.setText("Opcion 1 pulsada!");
			return true;
		case R.id.MnuOpc2:
			lblMensaje.setText("Opcion 2 pulsada!");
			return true;
		case R.id.MnuOpc3:
			lblMensaje.setText("Opcion 3 pulsada!");
			return true;
		case R.id.SubMnuOpc1:
			lblMensaje.setText("Opcion 3 1 pulsada!");
			return true;
		case R.id.SubMnuOpc2:
			lblMensaje.setText("Opcion 3 2 pulsada!");
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
}
