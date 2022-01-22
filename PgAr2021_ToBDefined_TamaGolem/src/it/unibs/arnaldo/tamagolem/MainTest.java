package it.unibs.arnaldo.tamagolem;

/***
 * Classe di test per l'equilibrio
 * Prova 1000 volte per ogni numero di elementi selezionabile
 * Stampa qualcosa in caso non sia bilanciato
 * Se trovate errori mi uccido
 *
 * @author toBdefined
 */
public class MainTest {
    public static void main(String[] args) {
        Equilibrio eq;

        for (int i = 3; i < 10; i++) {
            Config.init(i);
            for (int j = 0; j < 1000; j++) {
                eq = new Equilibrio(i);
                if(!eq.checkEquilibrio()){
                    System.out.println("E' FINITA");
                    eq.printMatAdiacenza();
                }
            }
        }
    }
}
