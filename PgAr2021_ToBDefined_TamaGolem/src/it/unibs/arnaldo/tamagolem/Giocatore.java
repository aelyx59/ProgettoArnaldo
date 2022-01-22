package it.unibs.arnaldo.tamagolem;

import it.unibs.fp.mylib.InputDati;

import java.util.ArrayList;

/***
 * Classe per inizializzare il costruttore di giocatore e assegnare i tamagolem ai giocatori
 */
public class Giocatore {

    public static final String INSERISCI_NOME_TAMA = "Inserisci il nome del Tamagolem: ";
    public static final String SCELTA = "Scelta: ";
    public static final String INSERIMENTO_TAMA = "\n➢ Inserimento dei TamaGolem di ";

    private final String nome;
    private ArrayList<TamaGolem> tamagolems;


    /***
     * Metodo costruttore di giocatore
     * @param g, cioè il giocatore
     */
    //costruttore del giocatore
    public Giocatore(Giocatore g){
        this.nome = g.getNome();
        this.tamagolems = new ArrayList<>(g.getTamagolems());
    }


    /***
     * Metodo costruttore di giocatore
     * @param nome, cioè il nome del giocatore
     */
    public Giocatore(String nome) {
        this.nome = nome;
        this.tamagolems = new ArrayList<>();
    }

    //GETTERS

    /***
     * Getter di nome
     * @return nome del giocatore
     */
    public String getNome() {
        return nome;
    }

    /***
     * Getter di tamagolems
     * @return arraylist tamagolems
     */
    public ArrayList<TamaGolem> getTamagolems() {
        return tamagolems;
    }


    /***
     * Metodo per controllare il numero di tamagolem ancora disponibili in squadra
     * @return true se ci sono oppure false
     */
    public boolean hasTamagolem() {
        for(TamaGolem t : tamagolems){
            if(t.getVita()>0 || !t.isUsato()){
                return true;
            }
        }
        return false;
    }


    /***
     * Metodo per inizializzare ed inserire un tamagotchi nella squadra
     */
    public void inputTama(){
        System.out.println(INSERIMENTO_TAMA + this.nome);
        for (int i = 0; i < Config.getNumGolem(); i++) {
            String nome = InputDati.leggiStringaNonVuota(INSERISCI_NOME_TAMA).toUpperCase();
            tamagolems.add(new TamaGolem(nome));
        }
    }


    /***
     * Metodo per selezionare il tamagolem da evocare
     * @return il tamagolem selezionato
     */
    public TamaGolem selectTamagolem() {
        //Controllo se sono presenti tamagolem
        if(tamagolems.size() <= 0)
            return null;

        //Prende in input dall'utente la posizione del golem (controlli)
        int posScelta = -1;
        boolean finito = false;
        //Stampa i tamagolem disponibili
        while(!finito){
            System.out.println("\n➢ " + this.nome + " scegli il tamagolem da evocare");
            stampaGolem();
            posScelta = InputDati.leggiIntero(SCELTA, 0,tamagolems.size()-1);

            if(!tamagolems.get(posScelta).isUsato())
                finito = true;
        }

        //Imposto ad usato il tamagolem
        tamagolems.get(posScelta).setUsato(true);

        //Ritorno
        return tamagolems.get(posScelta);
    }


    /***
     *Metodo per resettare le modifiche avvenute dopo la configurazione iniziale della partita
     */
    public void resetModifiche(){
        for(TamaGolem t : tamagolems){
            t.setUsato(false);
            t.setPietre(new ArrayList<>());
        }
    }


    /***
     * Metodo per stampare i tamagolem rimasti
     */
    private void stampaGolem(){
        for (int i = 0; i <tamagolems.size(); i++) {
            if(!tamagolems.get(i).isUsato())
                System.out.println("("+i+") " + tamagolems.get(i));
        }
    }


    /***
     * Metodo to string
     * @return nome del tamagolem
     */
    public String toString(){
        return this.nome;
    }

}