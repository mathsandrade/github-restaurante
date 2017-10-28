/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Models.Locacao;
import Models.Mesa;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Matheus
 */

//Classe utilizada para auxiliar o inicio da locação na service
public class IniciandoLocacao {
    private Locacao locacao;
    private List<Mesa> mesas;

    public IniciandoLocacao() {
        mesas = new ArrayList<Mesa>();
    }
    
    /**
     * @return the locacao
     */
    public Locacao getLocacao() {
        return locacao;
    }

    /**
     * @param locacao the locacao to set
     */
    public void setLocacao(Locacao locacao) {
        this.locacao = locacao;
    }

    /**
     * @return the mesas
     */
    public List<Mesa> getMesas() {
        return mesas;
    }

    /**
     * @param mesas the mesas to set
     */
    public void setMesas(Mesa... mesas) {
        for (Mesa mesa : mesas) {
            this.mesas.add(mesa);
        }    
    }

    /**
     * @param mesas the mesas to set
     */
    public void setMesas(List<Mesa> mesas) {
        this.mesas = mesas;
    }
}
