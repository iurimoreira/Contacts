package moreira.iuri.agenda;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by iurimoreira on 15/10/17.
 */

public class ListaAdapterContato extends ArrayAdapter<Contato> {

    private Context context;
    private ArrayList<Contato> lista;

    public ListaAdapterContato(Context context, ArrayList<Contato> lista){
        super(context,0,lista);
        this.context=context;
        this.lista=lista;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Contato contatoPosicao = this.lista.get(position);

        convertView = LayoutInflater.from(this.context).inflate(R.layout.activity_contatos, null);

        TextView nome = (TextView) convertView.findViewById(R.id.nomeContato);
        nome.setText(contatoPosicao.getNomeContato());

        TextView email = (TextView) convertView.findViewById(R.id.emailContato);
        email.setText(contatoPosicao.getEmailContato());

        TextView telefone = (TextView) convertView.findViewById(R.id.telefoneContato);
        telefone.setText(contatoPosicao.getTelefoneContato());

        TextView cidade = (TextView) convertView.findViewById(R.id.cidadeContato);
        cidade.setText(contatoPosicao.getCidadeContato());

        return convertView;

    }
}
