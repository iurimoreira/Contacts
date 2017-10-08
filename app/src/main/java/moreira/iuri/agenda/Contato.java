package moreira.iuri.agenda;

/**
 * Created by iurimoreira on 08/10/17.
 */

public class Contato implements java.io.Serializable {
    String nomeContato, telefoneContato, emailContato, cidadeContato;

    public Contato() {}

    public Contato(String nome, String telefone, String email, String cidade){
        this.nomeContato = nome;
        this.telefoneContato = telefone;
        this.emailContato = email;
        this.cidadeContato = cidade;
    }
}
