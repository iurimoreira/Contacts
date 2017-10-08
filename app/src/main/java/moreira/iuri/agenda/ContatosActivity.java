package moreira.iuri.agenda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class ContatosActivity extends AppCompatActivity {

    TextView oi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contatos);

        oi = (TextView) findViewById(R.id.txtOi);

        Intent intent = getIntent();
        ArrayList<Contato> listaContatos = (ArrayList<Contato>) intent.getSerializableExtra("LIST");

        oi.setText(listaContatos.get(0).nomeContato);
    }
}
