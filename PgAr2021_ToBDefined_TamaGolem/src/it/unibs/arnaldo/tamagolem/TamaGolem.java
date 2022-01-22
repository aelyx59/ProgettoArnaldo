package it.unibs.arnaldo.tamagolem;

import java.util.ArrayList;

/***
 * Classe del tamagolem, contiene il nome, le pietre e la vita
 * @author toBdefined
 */
public class TamaGolem {

    private final String nome;
    private ArrayList<TipoElemento> pietre;
    private boolean usato;
    private int vita;

    /***
     * Metodo per ottenere una pietra tramite l'indice
     * @param index, cioè l'indice della pietra
     * @return pietre.get(index), cioè la pietra presente in quella posizione
     */
    public TipoElemento getPietra(int index){
        return pietre.get(index);
    }


    /***
     * Costruttore tamagolem
     * @param nome nome
     */
    public TamaGolem(String nome) {
        this.usato = false;
        this.nome = nome;
        pietre = new ArrayList<>();
    }

    //GETTERS
    /***
     * Getter di pietre
     * @return pietre
     */
    public ArrayList<TipoElemento> getPietre() {
        return pietre;
    }

    /***
     * Getter della variabile nome
     * @return nome
     */
    public String getNome() {
        return nome;
    }

    /***
     * Getter della variabile vita
     * @return vita
     */
    public int getVita() {
        return vita;
    }


    //SETTERS
    /***
     * Setter di usato
     * @param usato usato
     */
    public void setUsato(boolean usato) {
        this.usato = usato;
    }

    /***
     * Setter di vita
     * @param vita vita
     */
    public void setVita(int vita) {
        this.vita = vita;
    }

    /***
     *Setter di pietre
     * @param pietre pietre
     */
    public void setPietre(ArrayList<TipoElemento> pietre){
        this.pietre = new ArrayList<>(pietre);
    }


    /***
     * Metodo per individuare se il tamagolem e' usato oppure no
     * @return true se e' usato oppure false
     */
    public boolean isUsato(){
        return this.usato;
    }


    /***
     * Metodo toString
     * @return nome del tamagolem
     */
    public String toString(){
        return this.nome;
    }
}
