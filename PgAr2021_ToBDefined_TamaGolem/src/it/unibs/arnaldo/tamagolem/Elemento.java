package it.unibs.arnaldo.tamagolem;

/***
 * Classe per gestire gli elementi dell'equilibrio
 * Corrisponde a un nodo che comprende il tipo di elemento e la sommatoria dei pesi entranti e uscenti
 * @author toBdefined
 */
public class Elemento {

    //attributi
    private final TipoElemento tipo;
    private int sumAct;

    /***
     * Costruttore di elemento
     * @param tipo tipo
     */
    public Elemento(TipoElemento tipo){
        this.tipo = tipo;
        this.sumAct = 0;
    }


    //GETTERS
    /***
     * Getter del tipo di elemento
     * @return tipo elemento
     */
    public TipoElemento getTipo() {
        return tipo;
    }

    /***
     * Getter della somma attuale dei pesi
     * @return somma dei valori
     */
    public int getSumAct() {
        return sumAct;
    }


    /***
     * Metodo per aggiungere un peso entrante
     * @param val valore da aggiungere
     */
    public void addValEnt(int val) {
        this.sumAct += val;
    }


    /***
     * Metodo per aggiungere un peso uscente
     * @param val valore da aggiungere
     */
    public void addValUsc(int val) {
        this.sumAct -= val;
    }


    /***
     * Metodo per verificare che 2 oggetti siano uguali
     * @param obj oggetto da controllare
     * @return true se l'oggetto passato corrisponde all'oggetto corrente
     */
    @Override
    public boolean equals(Object obj) {
        // self check
        if (this == obj)
            return true;
        // null check
        if (obj == null)
            return false;
        // type check and cast
        if (getClass() != obj.getClass())
            return false;
        Elemento elemento = (Elemento) obj;
        // field comparison
        return tipo.equals(elemento.getTipo());
    }
}
