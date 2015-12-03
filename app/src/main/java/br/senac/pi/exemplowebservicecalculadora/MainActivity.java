package br.senac.pi.exemplowebservicecalculadora;

import android.app.ProgressDialog;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,Runnable {

    private EditText edtValor1, edtValor2;
    private TextView txtResultado;
    private Button btnCalcular;
    private Handler handler = new Handler();
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDialog = new ProgressDialog(this);
        edtValor1 = (EditText) findViewById(R.id.edtValor1);
        edtValor2 = (EditText) findViewById(R.id.edtvalor2);
        txtResultado = (TextView)findViewById(R.id.txtResultado);
        btnCalcular = (Button) findViewById(R.id.btn_calcular);
        btnCalcular.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        progressDialog.setTitle(getString(R.string.titulo_progressdialog));
        progressDialog.setMessage(getString(R.string.mensagem_progressdialog));
        progressDialog.show();
        Thread thread = new Thread(this);
        thread.start();

    }

    @Override
    public void run() {
        int valor1 = Integer.parseInt(edtValor1.getText().toString());
        int valor2 = Integer.parseInt(edtValor2.getText().toString());
        try {
            CalculadoraWS calculadoraWS = new CalculadoraWS();
            final int rusultado = calculadoraWS.add(valor1,valor2);
            handler.post(new Runnable() {
                @Override
                public void run() {
                   txtResultado.setText(getString(R.string.txt_resultado_final)+ " " + rusultado);
                }
            });
        }catch (Exception e) {
            Log.e("cursoandroid", "Erro: ", e);
        }finally {
            progressDialog.dismiss();
        }
    }
}
