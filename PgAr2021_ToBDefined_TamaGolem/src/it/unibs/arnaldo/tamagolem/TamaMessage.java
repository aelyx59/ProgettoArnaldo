package it.unibs.arnaldo.tamagolem;

/***
 * Classe che contiene le costanti stringa del programma
 * @author toBdefined
 */
public class TamaMessage {

    //output iniziale del programma
    public static final String SALUTO =""
            + "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\r\n"
            + "║   Benvenuto nel mondo dei Tamagolem! :-)    ║ \r\n"
            + "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\r\n"
            + "\r\nLo scontro termina quando tutti i Tamagolem di uno dei giocatori non sono piu in grado di lottare.";


    //menu visualizzato dall'utente nel primo switch
    public static final String MENU = ""
            + "\r\n°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°"
            + "\r\n ➢ 1. Nuova Partita"
            + "\r\n ➢ 2. Rivincita"
            + "\r\n ➢ 0. Esci"
            + "\r\n°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°\r\n"
            + "\r\nScelta: ";


    //menu che verrà poi mostrato prima far inserire all'utente il valore della scelta del secondo switch
    public static final String MENU2 = ""
            + " ➢ 1. SCELTA NOMI"
            + "\r\n ➢ 2. SCONTRO"
            + "\r\n ➢ 3. FUGA"
            + "\r\n~~~~~~~~~~~~~~~~~~~~\r\n"
            + "\r\nScelta: ";


    //output per indicare all'utente che dato sta per inserire
    public static final String MESS_NOME = "INSERISCI IL NOME DEL GIOCATORE: ";


    //Output per la selezione del numero di elementi
    public static final String NUMERO_ELEMENTI = ""
            + "Inserisci il numero di elementi con la quale vuoi giocare:\r\n"
            + "➢ Facile    [3-5]\r\n"
            + "➢ Medio     [6-8]\r\n"
            + "➢ Difficile [9-10]\r\n"
            + "~~~~~~~~~~~~~~~~~~~~\r\n"
            + "Scelta [3-10]: ";


    //Errore che avviene quando si chiede la rivincita senza aver giocato
    public static final String ERR_RIVINCITA = "Non puoi usare questa opzione finché non giochi almeno una volta";


    //Errore che si ottiene selezionando un numero non presente nel menù
    public static final String ERR_SCELTA = "Scelta non valida";


    //Errore che si ottiene inserendo i 2 nomi uguali
    public static final String ERR_NOMI_UGUALI = "I nomi corrispondono! Inserisci un nome diverso";
}