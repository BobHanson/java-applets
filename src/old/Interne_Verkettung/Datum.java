package old.Interne_Verkettung;
/**
 * Die Daten, die in die Hash Tabelle eingetragen werden
 */
public class Datum {
    
    /**
     * Die Markierungen f&uuml;r die Zust&auml;nde frei und gel&ouml;scht
     */
    private static final int FREI      = -1;
    private static final int GELOESCHT = -2;

    /**
     * Die Tabellengr&ouml;&szlig;e f&uuml;r die Hash Funktion
     */
    private int FELD_ANZAHL;
    
    /**
     * Die Bestandteile eines Datums
     */
    private int schluessel = FREI;
    private int sollIndex  = FREI;
    private boolean markiert = false;
    
    /**
     * Erstellt Daten mit dem Eintrag FREI, um die leere Tabelle zu erstellen.
     */
    public Datum(int laenge) {
	
	schluessel = FREI;
	FELD_ANZAHL = laenge;
	
    } //Ende public Datum(int laenge)
    
    /**
     * Erstellt Daten, die eingef&uuml;gt, gesucht oder gel&ouml;scht werden.
     */
    public Datum(int laenge, int datenSchluessel) {
	
	FELD_ANZAHL = laenge;
	schluessel = datenSchluessel;
	sollIndex = Hash(schluessel);
	
    } //Ende public Datum(int laenge, int datenSchluessel)
    
    /**
     * Erm&ouml;glicht lesenden zugriff auf den Schl&uuml;ssel
     * @return der Schl&uuml;ssel als Integer
     */
    public int leseSchluessel() {
	
	return schluessel;
	
    } //Ende public int leseSchluessel()
    
    /**
     * Gibt den Schl&uuml;ssel als String zur&uuml;ck
     * @return der Schl&uuml;ssel als String
     */
    public String schluesselToString() {

	String key;

	if (schluessel >= 0 && schluessel < 10000) {
	    key = Integer.toString(schluessel);
	} else {
	    key = "";
	}

	return key;

    } //Ende public String schluesselToString()

    /**
     * Gibt die Position in der Tabelle zur&uuml;ck, an der der
     * Schl&uuml;ssel eingef&uuml;gt werden sollte
     * @return soll Positon in der Tabelle als Integer
     */
    public int leseSollIndex() {

	return sollIndex;

    } //Ende public int leseSollIndex()

    /**
     * Gibt die Position in der Tabelle zur&uuml;ck, an der der
     * Schl&uuml;ssel eingef&uuml;gt werden sollte
     * @return soll Positon in der Tabelle als String
     */
    public String sollIndexToString() {

	String sI;

	if (schluessel >= 0 && schluessel < 10000) {
	    sI = Integer.toString(sollIndex);
	} else {
	    sI = "";
	}
	
	return sI;
	
    } //Ende public String sollIndexToString()
    
    /**
     * Die Hash Funktion
     * @return sollIndex
     */
    private int Hash(int datenSchluessel) {
	
	return (datenSchluessel % FELD_ANZAHL);
	
    } //Ende private int Hash(int datenSchluessel)
    
    /**
     * Markiert einen Datensatz als gel&ouml;scht
     */
    public void setzeGeloescht() {
	
	schluessel = GELOESCHT;
	sollIndex = GELOESCHT;
	
    } //Ende public void setzeGeloescht()
    
    
    //Methoden fuer den Interaktiven-Modus*************************************
    /**
     * Gibt zur&uuml;ck, ob ein Feld angklickt ist.
     * @return true, falls markiert.<br>
     * false, falls nicht markiert
     */
    public boolean leseMarkiert() {
	
	return markiert;
	
    } //Ende public boolean leseMarkiert
    
    /**
     * Markiert ein Datum als angeklickt
     */
    public void setzeMarkiert() {
	
	markiert = true;
	
    } //Ende public void setzeMarkiert()
    
    /**
     * Markiert ein Datum als nicht angeklickt
     */
    public void setzeNichtMarkiert() {
	
	markiert = false;
	
    } //Ende public void setzeNichtMarkiert
    
}
