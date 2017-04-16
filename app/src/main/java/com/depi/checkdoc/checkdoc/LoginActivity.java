package com.depi.checkdoc.checkdoc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {
    Button b1,b2;
    CheckBox rm;
    EditText ed1,ed2;

    //pass y logins por defecto: "admin" para ambos
    TextView tx1, txtPassword;

    //número de intentos
    int counter = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //botón de login
        b1 = (Button)findViewById(R.id.button);

        //nick y pass
        ed1 = (EditText)findViewById(R.id.editText);
        ed2 = (EditText)findViewById(R.id.editText2);

        //botón de cancelar
        b2 = (Button)findViewById(R.id.button2);

        //remember me
        rm = (CheckBox) findViewById(R.id.remember);

        //al clickear para recordar contraseña
        txtPassword = (TextView)findViewById(R.id.password);

        tx1 = (TextView)findViewById(R.id.textView3);
        tx1.setVisibility(View.GONE);




        //logearse
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate(ed1) &&
                        validate(ed2) ) {
                    Toast.makeText(getApplicationContext(),
                            getResources().getString(R.string.redirecting),Toast.LENGTH_SHORT).show();
                    Intent intent =
                            new Intent(LoginActivity.this, MenuActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.wrong_credentials),Toast.LENGTH_SHORT).show();
                    tx1.setVisibility(View.VISIBLE);
                    //tx1.setBackgroundColor(Color.RED);
                    counter--;
                    tx1.setText(getResources().getString(R.string.tries)+Integer.toString(counter));

                    if (counter == 0) {
                        b1.setEnabled(false);
                    }
                }
            }
        });

        //cancelar login, matamos app
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        //La implementacion de esto supondria enviar un correo o notificacion recordando la contraseña
        txtPassword.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                //Aqui el codigo que queremos que ejecute al ser pulsado
                /*
                Intent intent =
                            new Intent(LoginActivity.this, PasswordActivity.class);
                    startActivity(intent);
                 */
            }
        });

    }
    //Posibilidades de implementar esto:
    //guardar en BD con SQLite o en un fichero plano.
    public void onCheckboxClicked(View view) {
        if (rm.isChecked()){
            //guardar credenciales en algun lugar
        }else{
            //no hacer nada
        }
    }
    //metodo simple para validar el login y contraseña
    public boolean validate(EditText t){
        if ( t==null || !t.getText().toString().equals("admin") )
                return false;
        else
            return true;

    }
}
