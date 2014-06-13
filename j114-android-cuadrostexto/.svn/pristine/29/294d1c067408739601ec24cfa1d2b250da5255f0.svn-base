package comatrium.android;



import android.os.Bundle;
import android.app.Activity;
import android.text.Html;
import android.text.Spannable;
import android.text.style.StyleSpan;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView.BufferType;

/**
 * Ejemplo de uso de campos de textos. Dicho texto podra ser texto plano o con
 * formato (texto enriquecido).
 * 
 * @author Juan Antonio Solves Garcia.
 * @version 1.1 24-10-2012.
 */
public class Formulario_Textos extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.formulario__textos);

		// RECOGEMOS DE LA PANTALLA LOS COMPONENTES A USAR
		final EditText txtTexto = (EditText) findViewById(R.id.TxtTexto);

		final Button btnNegrita = (Button) findViewById(R.id.BtnNegrita);
		// ESTABLECEMOS EL EVENTO DE BOTON PARA LA OPERATIVA
		btnNegrita.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// CAMBIAMOS EL FORMATO DE TEXTO A PARTIR DEL SPANABLE
				Spannable texto = txtTexto.getText();
				// RECOGEMOS LA ZONA SELECCIONADA. SI NO HAY SELECCION NO SE
				// HACE NADA
				int ini = txtTexto.getSelectionStart();
				int fin = txtTexto.getSelectionEnd();
				// PARA EL CASO DE SELECIONAR DE ATRAS A DELANTE SE INTERCAMBIAN
				// LOS VALORES DE INI Y FIN PORQUE DARIAN ERROR AL LLAMAR AL
				// METOD SETSPAN
				if (ini > fin) {
					int cambio = ini;
					ini = fin;
					fin = cambio;
				}
				// CAMBIAMOS EL FORMATO EN LA ZONA SELECCIONADA
				texto.setSpan(new StyleSpan(android.graphics.Typeface.BOLD),
						ini, fin, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			}
		});
		
		final Button btnSetText = (Button) findViewById(R.id.BtnSetText);
		// ESTABLECEMOS EL EVENTO DE BOTON PARA LA OPERATIVA CON VARIAS OPCIONES
		// DE ESTABLECER EL FORMATO DE TEXTO
		btnSetText.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// FORMATO EN HTML AL TEXTO
				// Nueva cadena de texto
				String nuevoTexto = "<p>Otro <b>texto</b> de ejemplo.</p>";
				// Asigna texto con formato HTML
				txtTexto.setText(Html.fromHtml(nuevoTexto),
						BufferType.SPANNABLE);

				// Asigna texto sin formato (incluirá todas las etiquetas HTML)
				// txtTexto.setText(nuevoTexto);

				// Obtiene el texto SIN etiquetas de formato HTML
				// String aux1 = txtTexto.getText().toString();

				// Obtiene el texto CON etiquetas de formato HTML
				// String aux2 = Html.toHtml(txtTexto.getText());
			}
		});

	}

}
