package Testes;

import Data.Pool;
import Models.*;
import Models.DAO.*;
import Models.Usuario;
import java.sql.Connection;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Matheus
 */
public class CrudMain {
    public static void main(String[] args) {
        Pool pool = new Pool();
        Connection conn = pool.get();
        EnderecoDAO enddao = new EnderecoDAO(conn);
        Endereco endereco = new Endereco(){{
            setBairro("bairro a");
            setCep("11515151");
            setCidade("poa");
            setNumero(1);
            setRua("gardênia");
        }};
        
        UsuarioDAO userdao = new UsuarioDAO(conn);
        Usuario usuario = new Usuario(){{
            setLogin("teste2");
            setSenha("252525");
            setPerfilAcesso("normal");
        }};
        
        System.out.println("Inserindo Usuario");
        Usuario inserted = userdao.insert(usuario);
        System.out.println(format(inserted));
        
        
        
        /*System.out.println("INSERIR");
        *Endereco inserted = enddao.insert(endereco);
        *System.out.println(format(inserted));
        *
        *Endereco att = new Endereco(){{
        *    setId(inserted.getId());
        *    setBairro("bairro b");
        *    setCep("9999999");
        *    setCidade("sp");
        *    setNumero(2);
        *    setRua("rua b");
        *}};
        *
        *System.out.println("\n\n\nALTERAR");
        *Endereco updated = enddao.update(att);
        *System.out.println(format(updated));
       * 
       * System.out.println("\n\n\nOUTRO INSERT");
       * //outro insert
       * enddao.insert(endereco);
       * 
       * System.out.println("\n\n\nSELECT");
       * //select
       * Endereco selected = enddao.select(inserted);
       * System.out.println(format(selected));
       * 
       * System.out.println("\n\n\nSELECT ALL");
       * //select all
       * List<Endereco> selects = enddao.selectAll();
       * for (Endereco select : selects) {
       *     System.out.println(format(select));
       * }
       * 
       * System.out.println("\n\n\nDELETE");
       * //DELETE
       * enddao.delete(selected);
    *}
    *
    *public static String format(Endereco endereco){
    *    StringBuilder string = new StringBuilder();
    *    string.append("Endereço: \nId:");
    *    string.append(endereco.getId());
    *    string.append(" - Numero: ");
    *    string.append(endereco.getNumero());
    *    string.append(" - Rua: ");
    *    string.append(endereco.getRua());
    *    string.append(" - Cep: ");
    *    string.append(endereco.getCep());
    *    string.append(" - Bairro: ");
    *    string.append(endereco.getBairro());
    *    string.append(" - Cidade: ");
    *    string.append(endereco.getCidade());
    *    return string.toString();}
    */}
    
    public static String format(Usuario usuario){
        StringBuilder string = new StringBuilder();
        string.append("Usuario: \nLogin:");
        string.append(usuario.getLogin());
        string.append(" - Senha: ");
        string.append(usuario.getSenha());
        string.append(" - Perfil de Acesso: ");
        string.append(usuario.getPerfilAcesso());
        return string.toString();
    }
}
