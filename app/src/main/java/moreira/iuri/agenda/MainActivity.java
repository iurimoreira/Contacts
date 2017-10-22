package moreira.iuri.agenda;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView retorno;
    EditText nome;
    EditText telefone;
    EditText email;
    EditText cidade;
    ArrayList<Contato> listaContatos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nome = (EditText) findViewById(R.id.txtNome);
        telefone = (EditText) findViewById(R.id.txtTelefone);
        email = (EditText) findViewById(R.id.txtEmail);
        cidade = (EditText) findViewById(R.id.txtCidade);

        /*Quando a activity principal é carregada ela monta a lista de contatos a partir
        do arquivo de texto que já está salvo no dispositivo */
        montarListaContatos();
    }


    public void SalvarContato(View v) {
        //Valida se os campos foram preenchidos
        if (validarCampos()){
            try {
                //Prepara o arquivo txt onde os contatos serão salvos
                FileOutputStream fileout = openFileOutput("contatos.txt", MODE_PRIVATE);
                ObjectOutputStream outputWriter = new ObjectOutputStream(fileout);

                //Cria um objeto contato
                Contato contato = new Contato(
                        nome.getText().toString(),
                        telefone.getText().toString(),
                        email.getText().toString(),
                        cidade.getText().toString()
                );

                //Adiciona o objeto a lista de contatos
                listaContatos.add(contato);

                //Salva o objeto no arquivo de texto
                outputWriter.writeObject(listaContatos);
                outputWriter.close();

                limparCampos();

                Toast.makeText(getBaseContext(), "Contato salvo com sucesso.", Toast.LENGTH_SHORT).show();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            Toast.makeText(getBaseContext(), "Ops. Você precisa preencher todos os campos.", Toast.LENGTH_SHORT).show();
        }

    }

    public void montarListaContatos() {
        try {
            FileInputStream fileIn = openFileInput("contatos.txt");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            listaContatos= (ArrayList<Contato>) in.readObject();
            in.close();
            fileIn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostrarListaContatos(View view) {
        if(listaContatos.isEmpty()){
            Toast.makeText(getBaseContext(), "A lista de contatos está vazia. Crie um contato primeiro.", Toast.LENGTH_SHORT).show();
        }else{
            Intent contatosActivity = new Intent(this, activity_lista_contatos.class);
            contatosActivity.putExtra("LIST", (Serializable) listaContatos);
            startActivity(contatosActivity);
        }
    }

    public void deletarContatos(View v){
        try {
            FileOutputStream fileout = openFileOutput("contatos.txt", MODE_PRIVATE);
            ObjectOutputStream outputWriter = new ObjectOutputStream(fileout);

            listaContatos.clear();

            outputWriter.writeObject(listaContatos);
            outputWriter.close();

            Toast.makeText(getBaseContext(), "Os contatos foram apagados.", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean validarCampos(){
        if(TextUtils.isEmpty(nome.getText())||TextUtils.isEmpty(telefone.getText())
                ||TextUtils.isEmpty(email.getText())||TextUtils.isEmpty(cidade.getText())){
            return false;
        }
        return true;
    }

    public void limparCampos(){
        nome.setText("");
        email.setText("");
        telefone.setText("");
        cidade.setText("");

        nome.requestFocus();
    }

}

