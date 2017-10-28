/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import javax.persistence.Transient;

/**
 *
 * @author Matheus
 */
public class Usuario {
    private String login;
    private String senha;
    private String perfilAcesso;
    
    
    @Transient
    private String senhaSemCriptografia;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getPerfilAcesso() {
        return perfilAcesso;
    }

    public void setPerfilAcesso(String perfilAcesso) {
        this.perfilAcesso = perfilAcesso;
    }

    public String getSenhaSemCriptografia() {
        return senhaSemCriptografia;
    }

    public void setSenhaSemCriptografia(String senhaSemCriptografia) {
        this.senhaSemCriptografia = senhaSemCriptografia;
    }
}
