/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testes;

import Data.Pool;
import Models.Endereco;
import Models.DAO.EnderecoDAO;
import Models.Pessoa;
import Models.DAO.PessoaDAO;
import java.sql.Connection;

/**
 *
 * @author Matheus
 */
public class Main {
    public static void main(String[] args) {
        Pool pool = new Pool();
        Connection conn = pool.get();
        PessoaDAO dao = new PessoaDAO(conn);
        EnderecoDAO enddao = new EnderecoDAO(conn);
        Endereco endereco = new Endereco(){{
            setBairro("bairro a");
            setCep("11515151");
            setCidade("poa");
            setNumero(1);
            setRua("rua a");
        }};
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Lucas");
        pessoa.setIdade(20);
        pessoa.setCpf("123123");
        pessoa.setTelefone("123123123");
        Endereco e = enddao.insert(endereco);
        pessoa.setEndereco(e);
        Pessoa p = dao.insert(pessoa);
        System.out.print("Inseriu");
    }
}
