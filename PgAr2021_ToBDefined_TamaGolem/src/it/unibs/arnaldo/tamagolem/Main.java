package it.unibs.arnaldo.tamagolem;

import it.unibs.fp.mylib.InputDati;

/***
 * Classe main
 * @author ToBdefined
 */
public class Main {

    /***
     * Metodo main
     * @author toBdefined
     * @param args arguments
     */
    public static void main(String[] args) {
        //richiamo il messaggio di benvenuto
        System.out.println(TamaMessage.SALUTO);

        //inizializzo le variabili
        int scelta;
        boolean blocca = true;
        Scontro partita = null;

        //comincio il ciclo do while con lo switch
        do {
            //assegno alla variabile il valore inserito da tastiera dopo aver mostrato il menu di scelta
            scelta = InputDati.leggiInteroNonNegativo(TamaMessage.MENU);

            switch(scelta) {
                //inizia una nuova partita
                case 1:
                    partita = inputScontro();
                    partita.gioca();
                    blocca = false;
                    break;
                //inizia una nuova partita con gli stessi dati
                case 2:
                    if(!blocca){
                        partita.rivincita();
                    }
                    else
                        System.out.println(TamaMessage.ERR_RIVINCITA);
                    break;
                //termina il programma
                case 0: break;

                //messaggio di errore per altri valori inseriti
                default: System.out.println(TamaMessage.ERR_SCELTA);
            }

        }while(scelta != 0);
    }

    /***
     * Metodo per inserire i nomi dei giocatori e selezionare il numero di elementi
     * @return scontro
     */
    private static Scontro inputScontro(){
        String nome1 = InputDati.leggiStringa(TamaMessage.MESS_NOME).toUpperCase();
        String nome2 = InputDati.leggiStringa(TamaMessage.MESS_NOME).toUpperCase();
        while(nome1.equals(nome2)){
            System.out.println(TamaMessage.ERR_NOMI_UGUALI);
            nome2 = InputDati.leggiStringa(TamaMessage.MESS_NOME);
        }
        int nElementi = InputDati.leggiIntero(TamaMessage.NUMERO_ELEMENTI,3,10);
        return new Scontro(nome1, nome2, nElementi);
    }
}
