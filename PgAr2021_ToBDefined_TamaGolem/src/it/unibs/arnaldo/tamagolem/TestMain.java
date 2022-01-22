package it.unibs.arnaldo.tamagolem;

public class TestMain {
    public static void main(String[] args) {
        Config.init(5);
        Equilibrio eq = new Equilibrio(5);
        System.out.println(eq.checkEquilibrio());
        eq.printPesiElem();


        /*
        //Scontro sc = new Scontro("nome1","nome2", 5);

        //NON SAREBBERO DA FARE MA E' PER VELOCIZZARE I TEST
        ArrayList<TamaGolem> tama1 = new ArrayList<>();
        tama1.add(new TamaGolem("Giuseppe"));
        tama1.add(new TamaGolem("Giacomo"));
        ArrayList<TamaGolem> tama2 = new ArrayList<>();
        tama2.add(new TamaGolem("Andrea"));
        tama2.add(new TamaGolem("Aristide"));

        Giocatore g1 = new Giocatore("Pippo", tama1);
        Giocatore g2 = new Giocatore("Franco", tama2);

        Scontro sc = new Scontro(g1, g2, 5);
        //NON SAREBBERO DA FARE MA E' PER VELOCIZZARE I TEST

        sc.gioca();
        */



        /*System.out.println("Forse ho creato il progetto");
        Equilibrio eq = new Equilibrio(5);

        //System.out.println(eq.getMultiplier(new Elemento("ACQUA"), new Elemento("ACQUA")));
        //System.out.println(eq.getMultiplier(new Elemento("ACQUA"), new Elemento("FUOCO")));
        eq.printCarino();

        //eq.bilancia();
        //System.out.println("BILANCIATO");
        System.out.println(eq.checkEquilibrio());
        System.out.println(eq.sommatoriaMatrice());
        //eq.printPesiAttuali();
        eq.printPesiElem();

        /*
        Equilibrio eq2 = new Equilibrio(10);
        eq2.printCarino();
        System.out.println(eq2.checkEquilibrio());
        System.out.println(eq2.sommatoriaMatrice());
        eq2.printPesiElem();
        */

        /*
        ArrayList<TamaGolem> tamagolems = new ArrayList<>();
        String nome = "hey";
        Squadra ciao = new Squadra();
        System.out.println(ciao.NumPietre(ciao.getNUM_ELEMENTI()));
        System.out.println(ciao.NumGolem(ciao.getNUM_ELEMENTI(), ciao.NumPietre(5)));
        System.out.println(ciao.getNUM_PIETRE());*/
    }
}
