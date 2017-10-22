package moreira.iuri.agenda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class activity_lista_contatos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_contatos);

        Intent intent = getIntent();
        ArrayList<Contato> listaContatos = (ArrayList<Contato>) intent.getSerializableExtra("LIST");

        ListaAdapterContato adapterContato = new ListaAdapterContato(this,listaContatos);

        ListView lista = (ListView)findViewById(R.id.listaContatos);

        lista.setAdapter(adapterContato);

    }
}
