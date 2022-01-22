package it.unibs.arnaldo.tamagolem;

/***
 * Classe statica con la configurazione delle costanti del gioco
 * @author toBdefined
 */
public class Config {
    public static final int INTERVALLO_3_5 = 0;
    public static final int INTERVALLO_6_8 = 1;
    public static final int INTERVALLO_9_10 = 2;
    private static int NUM_ELEMENTI;
    private static int NUM_PIETRE;
    private static int NUM_GOLEM;
    private static int MAX_POTENZA;
    private static final int[] POTENZA_PER_INTERVALLO = {4,5,6};

    /***
     * Metodo per inizializzare tutte le costanti del gioco inserendo il numero di elementi
     * @param numElementi, cioè il numero di elementi presenti nell'equilibrio del mondo
     */
    //Imposta i valori calcolati dal numero di elementi
    public static void init(int numElementi){
        NUM_ELEMENTI = numElementi;
        NUM_PIETRE =  (int) (Math.ceil( (double) (NUM_ELEMENTI + 1) / 3) + 1);
        NUM_GOLEM = (int) Math.ceil( (double) (NUM_ELEMENTI-1)*(NUM_ELEMENTI-2)/(2*NUM_PIETRE));
        int intervallo = ((NUM_ELEMENTI < 6) ? INTERVALLO_3_5 : ((NUM_ELEMENTI < 9) ? INTERVALLO_6_8 : INTERVALLO_9_10));
        MAX_POTENZA = POTENZA_PER_INTERVALLO[intervallo];
    }

    //Getters
    /***
     * Getter della variabile num_elementi
     * @return NUM_ELEMENTI
     */
    public static int getNumElementi() {
        return NUM_ELEMENTI;
    }

    /***
     * Getter della variabile num_pietre
     * @return NUM_PIETRE
     */
    public static int getNumPietre() {
        return NUM_PIETRE;
    }

    /***
     * Getter della variabile num_golem
     * @return NUM_GOLEM
     */
    public static int getNumGolem() {
        return NUM_GOLEM;
    }

    /***
     * Getter della variabile max_potenza, ossia la vita del tamagolem
     * @return MAX_POTENZA
     */
    public static int getMaxPotenza() {
        return MAX_POTENZA;
    }
}
