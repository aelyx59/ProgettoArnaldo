package it.unibs.arnaldo.tamagolem;

import it.unibs.fp.mylib.InputDati;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/***
 * Classe principale contenente le dinamiche dello scontro
 * Contiene i 2 giocatori e i 2 Tamagolem attualmente in gioco
 * @author toBdefined
 */
public class Scontro {

    public static final String NO_EFFECT = "Non ha effetto";
    public static final String SCEGLI_PIETRE = "\n➢ Scegli le pietre da dare al tuo tamagolem";
    public static final String SCELTA = "Scelta: ";
    public static final String NON_DISPONIBILE = "Elemento non disponibile! \nScelta: ";
    public static final String QUANTITA = "\nQuante ne vuoi? ";
    public static final String ERRORE_DI_SELEZIONE = "Il numero selezionato supera il numero massimo di pietre per il tuo Tamagolem";
    public static final String VITTORIA = " HA VINTO!";
    public static final String RIVELAZIONE_EQUILIBRIO = "\n\nRIVELAZIONE DELL'EQUILIBRIO";
    public static final String VITTORIA_MEME = " HA VINTO LA BATTAGLIA DELLA VITA!";

    //attributi
    private Giocatore player1;
    private Giocatore player2;
    private TamaGolem tamaP1;
    private TamaGolem tamaP2;
    private Equilibrio eq;
    private HashMap<TipoElemento, Integer> magazzino;


    /***
     * Costruttore di Scontro
     * @param player1, cioè il nome del primo giocatore
     * @param player2, cioè il nome del seocndo giocatore
     * @param nElementi, cioè il numero di elementi presenti nell'equilibrio del mondo
     */
    public Scontro(String player1, String player2, int nElementi) {
        Config.init(nElementi);

        this.player1 = new Giocatore(player1);
        this.player2 = new Giocatore(player2);
        this.eq = new Equilibrio(nElementi);

        this.magazzino = creaMagazzino();
    }

    /***
     * Metodo per creare la scorta comune di pietre
     * @return magazzino, ossia la scorta comune ai due giocatori per scegliere le pietre
     */
    private HashMap<TipoElemento, Integer> creaMagazzino(){
        HashMap<TipoElemento, Integer> magazzino = new HashMap<>();
        TipoElemento[] possibili = eq.getTipiDisponibili();

        for (TipoElemento tipoElemento : possibili) {
            magazzino.put(tipoElemento, Config.getNumPietre());
        }

        return magazzino;
    }


    /***
     * Metodo per prendere il numero massimo di pietre disponibili di un certo elemento nella scorta
     * @param elem tipo di elemento
     * @return numero pietre dell' elemento
     */
    public int getNumeroPietre(TipoElemento elem){
        return magazzino.get(elem);
    }


    /***
     * Metodo per prendere una N pietre dalla scorta comune
     * @param elem tipo di elemento
     * @param nPietre numero di pietre
     */
    public void prelevaPietre(TipoElemento elem, int nPietre) {
        //Se l'elemento non esiste esco
        if (!magazzino.containsKey(elem))
            return;
        int pietreAttuali = magazzino.get(elem);
        //Se la richiesta NON supera la disponibilità prelevo
        if (pietreAttuali >= nPietre)
            magazzino.replace(elem, pietreAttuali - nPietre);
    }


    /***
     * Metodo per la rivincita
     * Prima resetta le modifiche dello scontro precedente (vite e le pietre), ricreo il magazzino e poi richiamo gioca
     */
    public void rivincita() {
        player1.resetModifiche();
        player2.resetModifiche();
        this.magazzino = creaMagazzino();
        gioca();
    }


    /***
     * Metodo per iniziare la battaglia
     */
    public void gioca(){
        //Fase preparatoria, input dei tamagochi
        if(player1.getTamagolems().size() == 0)
            player1.inputTama();
        if(player2.getTamagolems().size() == 0)
            player2.inputTama();

        //Determino chi va primo, faccio un rand e se è true scambio i giocatori
        Random rand = new Random();
        if(rand.nextBoolean()){
            Giocatore app;
            app = new Giocatore(player1);
            player1 = new Giocatore(player2);
            player2 = app;
        }

        //Evocazione
        tamaP1 = evoca(player1);
        tamaP2 = evoca(player2);

        //Inizia lo scontro
        int round = 0;
        int vittoria = 0;

        while(vittoria == 0){
            //Controllo se sono da evocare i tamagolem
            if(tamaP1 == null)
                tamaP1 = evoca(player1);
            if(tamaP2 == null)
                tamaP2 = evoca(player2);


            //Interazione tra pietre, determino il danno e lo assegno
            TipoElemento el1 = tamaP1.getPietra(round % Config.getNumPietre());
            TipoElemento el2 = tamaP2.getPietra(round % Config.getNumPietre());
            int potenza = eq.getPotenza(el1,el2);

            //Controllo le potenze
            //Se è uguale a 0, l'interazione è nulla
            if(potenza == 0)
                System.out.println(NO_EFFECT);

            //Se è maggiore di 0, allora p1 prevale su p2, p2 subisce danno, altrimenti viceversa
            if(potenza>0)
                tamaP2 = gestisciDanni(tamaP2, Math.abs(potenza));
            else
                tamaP1 = gestisciDanni(tamaP1, Math.abs(potenza));

            //check vittoria
            vittoria = checkVittoria();
            round++;
        }

        //Stampa chi ha vinto e rivela l'equilibrio
        stampaVittoria(vittoria);
    }

    /***
     * Metodo per evocare un tamagolem dalla propria squadra
     * @param player giocatore
     * @return null se non sono rimasti tamagolem oppure il tamagolem scelto
     */
    private TamaGolem evoca(Giocatore player) {
        //Seleziono un tamagolem
        TamaGolem tama = player.selectTamagolem();
        //Se è null esco
        if(tama == null)
            return null;

        //Gli imposto la vita al massimo
        tama.setVita(Config.getMaxPotenza());

        //Input pietre (rimuovi dal magazzino)
        tama.setPietre(inputPietre());

        return tama;
    }


    /***
     * Metodo per dare le pietre elementali al tuo tamagolem
     * @return gli elementi delle pietre scelte
     */
    private ArrayList<TipoElemento> inputPietre() {
        String[] simboli = {"🌪️","🔥","⛰","🌿","🌊","🌫️","🐲","🌩","☢","🧊"};
        int qtScelte = 0;
        ArrayList<TipoElemento> scelte = new ArrayList<>();

        System.out.println(SCEGLI_PIETRE);
        TipoElemento[] possibili = eq.getTipiDisponibili();

        //Finché non ha raggiunto il numero di pietre da prelevare
        while(qtScelte < Config.getNumPietre()){
            int pietre_rimaste = Config.getNumPietre()-qtScelte;

            //Stampo l'elenco di pietre disponibili
            System.out.println("Pietre possibili [sceglierne "+ pietre_rimaste +"]: ");
            for(int i = 0; i < possibili.length; i++){
                System.out.println("("+i+") " + possibili[i] + " " + simboli[i] + ": " + getNumeroPietre(possibili[i]));
            }
            //Continuo a prendere in input il tipo finché ne prendo uno con pietre disponibili
            int tipo = InputDati.leggiIntero(SCELTA,0,possibili.length-1);
            while(getNumeroPietre(possibili[tipo]) == 0)
                tipo = InputDati.leggiIntero(NON_DISPONIBILE,0,possibili.length-1);
            int qt = InputDati.leggiIntero(QUANTITA, 0, getNumeroPietre(possibili[tipo]));

            System.out.println();

            //Prelevo le pietre
            if(qtScelte+qt <= Config.getNumPietre()){
                prelevaPietre(possibili[tipo], qt);
                qtScelte+=qt;
                while(qt>0){
                    scelte.add(possibili[tipo]);
                    qt--;
                }
            }
            else
                System.out.println(ERRORE_DI_SELEZIONE);
        }
        return scelte;
    }


    /***
     * Metodo per gestire il calcolo dei danni durante lo scontro
     * @param subente,  cioè il tamagolem che sta subendo il danno
     * @param potenza, cioè il valore dell'interazione tra l'elemento forte e quello debole
     * @return null se il tamagolem resta senza vita oppure il tamagolem con la vita rimanente
     */
    private TamaGolem gestisciDanni(TamaGolem subente, int potenza) {
        subente.setVita(subente.getVita()- potenza);

        //check morte
        if(subente.getVita() <= 0){
            System.out.println("➢ " + subente + " è morto!");
            //rimuovo il tamagochi e rimetto nel magazzino le pietre
            riponiPietre(subente.getPietre());
            subente = null;
        }
        //se vivo, riporto i danni e si prosegue
        else
            System.out.println(subente.getNome() + " ha subito " + potenza + " danni, ora ha " + subente.getVita() +  " HP");

        return subente;
    }


    /***
     * Metodo per restituire le pietre tenute dal tamagolem sconfitto
     * @param pietre ArrayList delle pietre
     */
    private void riponiPietre(ArrayList<TipoElemento> pietre) {
        for (TipoElemento pietra : pietre) {
            magazzino.replace(pietra, magazzino.get(pietra)+1);
        }
    }


    /***
     * Metodo per verificare se e chi ha vinto
     * @return 2 se ha vinto il player2, 1 se ha vinto il player 1 oppure 0 se non ce ancora un vincitore
     */
    private int checkVittoria() {
        //Controllo se hanno almeno un tamagolem vivo
        boolean p1 = player1.hasTamagolem();
        boolean p2 = player2.hasTamagolem();

        //Se p1 non ha tamagolem ritorno 2 (che ha vinto), se p2 non ha tamagolem ritorno 1
        //Altrimenti ritorno 0
        if(!p1)
            return 2;
        if(!p2)
            return 1;

        return 0;
    }


    /***
     * Metodo per visualizzare in output chi ha vinto
     * @param vittoria, cioè il numero che indica quale dei due giocatori ha vinto
     */
    private void stampaVittoria(int vittoria) {
        System.out.println();
        System.out.println((vittoria==1 ? player1 : player2) + VITTORIA);
        System.out.println((vittoria==1 ? player1 : player2) + VITTORIA_MEME);
        System.out.println(RIVELAZIONE_EQUILIBRIO);
        eq.printPesiElem();
        System.out.println();
        eq.printMatAdiacenza();

    }
}
