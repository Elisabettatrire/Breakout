package ids.univpm.breakout.view.validation;

/**
 * Classe che contiene tutti i pattern per compiere un controllo RegEx sui campi inseriti nelle varie form dell'utente
 */

public abstract class FormControl {
        //lunghezza minima della password
    private static final int pass_lenght = 8;

    //controlla che ci siano solamente lettere, @ e punto
    public static boolean mailControl(String s) {
        boolean b;
        String pattern= "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        if(s.matches(pattern)) b = true;
        else b = false;
        return b;
    }

    //controllo della password sulla lunghezza
    public static boolean passwordControl(String s) {
        boolean b;
        if (s.length()<pass_lenght) b = false;
        else b = true;
        return b;
    }

}
