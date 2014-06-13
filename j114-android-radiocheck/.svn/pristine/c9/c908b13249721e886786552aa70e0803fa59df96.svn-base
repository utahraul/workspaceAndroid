package com.atrium.android;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Formulario_RadioCheck extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.formulario__radio_check);

		// RECOGEMOS EL COMPONENTE DE LA PANTALLA
		final CheckBox cb = (CheckBox) findViewById(R.id.ChkMarcame);
		// ESTABLECEMOS EL EVENTO PARA SU TRATAMIENTO
		cb.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					cb.setText("Checkbox marcado!");
				} else {
					cb.setText("Checkbox desmarcado!");
				}
			}
		});
		// RECOGEMOS EL COMPONENTE DE LA PANTALLA
		final TextView lblMensaje = (TextView) findViewById(R.id.LblSeleccion);

		final RadioGroup rg = (RadioGroup) findViewById(R.id.gruporb);
		// ESTABLECEMOS EL EVENTO PARA SU TRATAMIENTO
		rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				lblMensaje.setText("ID opción seleccionada: " + checkedId);
			}
		});
	}
}
