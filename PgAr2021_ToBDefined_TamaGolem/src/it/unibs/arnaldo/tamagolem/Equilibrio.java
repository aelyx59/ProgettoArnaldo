package it.unibs.arnaldo.tamagolem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/***
 * Classe per determinare l'equilibrio del mondo
 * @author ToBdefined
 */
public class Equilibrio {
    public static final String SUBENTI = "→ Subenti";
    public static final String ATTACCANTI = "↓ Attaccanti";
    private ArrayList<Elemento> elementi;
    private int [][] adiacenza;
    private int nElementi;


    /***
     * Costruttore di equilibrio
     * @param nElementi, cioè il numero di elementi esistenti
     */
    public Equilibrio(int nElementi){
        this.nElementi = nElementi;
        this.elementi = new ArrayList<>();
        this.adiacenza = new int[nElementi][nElementi];
        initElementi(nElementi);
        initValues();
    }


    /***
     * Metodo per stabilire gli elementi presenti nella partita e aggiungerli
     * @param num, cioè il numero di elementi esistenti
     */
    private void initElementi(int num){
        TipoElemento[] possibili = TipoElemento.values();
        for(int i=0; i<num; i++){
            Elemento e = new Elemento(possibili[i]);
            elementi.add(e);
        }
    }


    /***
     * Metodo per generare la matrice di adiacenza dell'equilibrio
     * Metto a 0 gli elementi della diagonale
     * Prima genero valori pseudo-random per gli elementi della matrice N-1xN-1 in modo che la loro sommatoria non sia maggiore della potenza massima e che non sia 0
     * In seguito assegno il valore rimanente di ogni riga al valore opposto a quello della sommatoria della riga stessa (sumRiga = 5 -> valore = -5)
     */
    public void initValues(){
        for(int i = 0; i < nElementi-1; i++) {
            for (int j = 0; j < nElementi-1; j++) {
                //Se siamo su un elemento della diagonale (fuoco-fuoco, acqua-acqua) mettiamo a 0
                if (i == j)
                    adiacenza[i][j] = 0;
                else {
                    //Controllo se il valore è già stato impostato
                    if (adiacenza[i][j] == 0 && adiacenza[j][i] == 0) {
                        //Se la somma attuale è maggiore di 0, allora è forte
                        boolean forte = elementi.get(i).getSumAct() > elementi.get(j).getSumAct();

                        if (forte) {
                            creaArcoPesato(i, j);
                        } else {
                            creaArcoPesato(j, i);
                        }
                    }
                }
            }
        }
        for (int j = 0; j < nElementi; j++) {
            int gap = elementi.get(j).getSumAct();
            adiacenza[nElementi-1][j] = -gap;
            adiacenza[j][nElementi-1] = gap;
            if(gap > 0){
                elementi.get(nElementi-1).addValUsc(gap);
                elementi.get(j).addValUsc(gap);
            }
            else{
                elementi.get(j).addValUsc(gap);
                elementi.get(nElementi-1).addValUsc(gap);
            }
        }
    }


    /***
     * Metodo per creare un interazione tra due elementi
     * @param posForte, cioè l'elemento forte
     * @param posDebole, cioè l'elemento debole
     */
    private void creaArcoPesato(int posForte, int posDebole) {
        //Imposto il forte a un valore random tra 1 e il massimo disponibile
        int peso = genPeso(posForte, posDebole);

        adiacenza[posForte][posDebole] = peso;
        elementi.get(posForte).addValUsc(peso);
        //Imposto il debole al negativo del peso
        adiacenza[posDebole][posForte] = -peso;
        elementi.get(posDebole).addValEnt(peso);
    }


    /***
     * Metodo per generare il valore dell'interazione tra i due elementi
     * Verifica che il valore generato non mandi a 0 la sommatoria e che non aumenti oltre il limite di potenza l'arco finale
     * @param posForte, cioè l'elemento forte
     * @param posDebole, cioè l'elemento debole
     * @return peso, cioè il valore dell'arco pesato
     */
    private int genPeso(int posForte, int posDebole) {
        Random rand = new Random();
        int maxRand = Config.getMaxPotenza();
        //Controllo se sfora (ricorda di non mandare maxRand a 1-
        while(maxRand > 1 && (Math.abs(elementi.get(posForte).getSumAct() - maxRand) > Config.getMaxPotenza() ||
                Math.abs(elementi.get(posDebole).getSumAct() + maxRand) > Config.getMaxPotenza())){
            maxRand--;
        }
        int peso;
        //genero finché il valore non manda a 0 nessuna sommatoria dei due elementi
        do {
            peso = rand.nextInt(maxRand) + 1;
        }while (elementi.get(posForte).getSumAct() - peso == 0 ||
                elementi.get(posDebole).getSumAct() + peso == 0
        );
        return peso;
    }


    /***
     * Metodo per verificare l'equilibrio
     * Ritorna false quando trova una colonna non bilanciata, ritorna alla fine del metodo altrimenti
     * @return true/false
     */
    public boolean checkEquilibrio(){
        //Scorro tutti gli elementi, se trovo qualcosa non bilanciato ritorno falso, altrimenti in fondo ritorno true
        for(int i=0; i<nElementi; i++)
            if(elementi.get(i).getSumAct() != 0)
                    return false;
        return true;
    }


    /***
     * Metodo per prendere la potenza dell'interazione tra 2 elementi, getter di adiacenza[][]
     * @param attaccante, cioè l'elemento forte
     * @param subente, cioè l'elemento debole
     * @return adiacenza[posAtk][posDef], cioè la potenza dell'interazione tra i due elementi
     */
    public int getPotenza(TipoElemento attaccante, TipoElemento subente){
        int posAtk = getElementValue(attaccante);
        int posDef = getElementValue(subente);

        return adiacenza[posAtk][posDef];
    }


    /***
     * Getter dell'indice di un elemento
     * @param e, cioè l'elemento da ricercare
     * @return l'indice della posizione di tale elemento
     */
    public int getElementValue(TipoElemento e){
        return elementi.indexOf(new Elemento(e));
    }


    /***
     *Getter dei tipi disponibili nella partita
     * @return tutti i tipi presenti durante la partita
     */
    public TipoElemento[] getTipiDisponibili(){
        return Arrays.copyOf(TipoElemento.values(), Config.getNumElementi());
    }


    /***
     *Metodo per stampare la matrice di adiacenza
     */
    public void printMatAdiacenza(){
        System.out.println(SUBENTI);
        System.out.println(ATTACCANTI);
        //Stampa lo slash
        System.out.print("/  ");
        //Stampa la riga con le iniziali e va a capo
        for(int i=0; i<nElementi; i++)
            System.out.print(elementi.get(i).getTipo().toString().charAt(0) + "  ");
        System.out.println();

        for(int i=0; i<nElementi; i++) {
            System.out.print("" + elementi.get(i).getTipo().toString().charAt(0));
            for(int j=0; j<nElementi; j++){
                System.out.printf("%3d", adiacenza[i][j]);
            }
            System.out.println();
        }
    }


    /***
     *Metodo per stampare le adiacenze
     */
    public void printPesiElem(){
        for (int i = 0; i < nElementi; i++) {
            System.out.print(elementi.get(i).getTipo() + " -> ");
            for (int j = 0; j < nElementi; j++) {
                if(i != j){
                    System.out.print(elementi.get(j).getTipo() + ": " + adiacenza[i][j] + "; ");
                }
            }
            System.out.println();
        }
    }
}