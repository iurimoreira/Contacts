package moreira.iuri.agenda;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView retorno;
    EditText nome;
    EditText telefone;
    EditText email;
    EditText cidade;
    ArrayList<Contato> listaContatos = new ArrayList<>();

    static final int READ_BLOCK_SIZE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nome = (EditText) findViewById(R.id.txtNome);
        telefone = (EditText) findViewById(R.id.txtTelefone);
        email = (EditText) findViewById(R.id.txtEmail);
        cidade = (EditText) findViewById(R.id.txtCidade);
        retorno = (TextView) findViewById(R.id.txtRetorno);

        montarListaContatos();
    }

    // write text to file
    public void WriteBtn(View v) {

        // add-write text into file
        try {
            FileOutputStream fileout = openFileOutput("contatos.txt", MODE_PRIVATE);
            /*OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);*/

            ObjectOutputStream outputWriter = new ObjectOutputStream(fileout);

            Contato contato = new Contato(
                    nome.getText().toString(),
                    telefone.getText().toString(),
                    email.getText().toString(),
                    cidade.getText().toString()
            );

            listaContatos.add(contato);

            outputWriter.writeObject(listaContatos);
            outputWriter.close();

            //display file saved message
            Toast.makeText(getBaseContext(), "File saved successfully!",
                    Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Read text from file
    public void ReadBtn(View v) {
        try {
            FileInputStream fileIn = openFileInput("contatos.txt");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            ArrayList<Contato> lista = (ArrayList<Contato>) in.readObject();
            in.close();
            fileIn.close();

            Contato c1;
            c1= lista.get(0);

            retorno.setText(c1.nomeContato.toString());


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
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
        Intent contatosActivity = new Intent(this, ContatosActivity.class);
        contatosActivity.putExtra("LIST", (Serializable) listaContatos);
        startActivity(contatosActivity);
    }


}

