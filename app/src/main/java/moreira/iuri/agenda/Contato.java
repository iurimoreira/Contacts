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

    public String getNomeContato() {
        return nomeContato;
    }

    public void setNomeContato(String nomeContato) {
        this.nomeContato = nomeContato;
    }

    public String getTelefoneContato() {
        return telefoneContato;
    }

    public void setTelefoneContato(String telefoneContato) {
        this.telefoneContato = telefoneContato;
    }

    public String getEmailContato() {
        return emailContato;
    }

    public void setEmailContato(String emailContato) {
        this.emailContato = emailContato;
    }

    public String getCidadeContato() {
        return cidadeContato;
    }

    public void setCidadeContato(String cidadeContato) {
        this.cidadeContato = cidadeContato;
    }
}
