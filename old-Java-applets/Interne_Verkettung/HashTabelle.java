import java.awt.*;

class HashTabelle extends Panel implements Runnable {


    /**
     * Die Farben
     */
    private static final Color TABELLE_BESCHRIFTUNG_FARBE = new Color(160,10,160);
    private static final Color FREI_FARBE = new Color(239, 239, 239);
    private static final Color BESETZT_FARBE = new Color(187, 201, 221);
    private static final Color GELOESCHT_FARBE = new Color(255,255,200);
    private static final Color SUCH_FARBE = new Color(200,225,255); //BESETZT_FARBE;
    private static final Color MARKIERT_FARBE = new Color(200, 239, 200);
    private static final Color DEFAULT_FARBE = Color.blue;


    /**
     * Klassenariablen
     */
    private static final int FREI      = -1;
    private static final int GELOESCHT = -2;
    private static final int EINFUEGEN = 1;
    private static final int SUCHEN    = 2;
    private static final int LOESCHEN  = 3;
    private static final int PAUSE     = 500;


    /**
     * F&uuml;r die Instanz g&uuml;tige Variablen
     */
    private final int FELD_ANZAHL;
    private final int BREITE;
    private final int HOEHE = 40;
    private final int BEGINN;
    private final int LAENGE;


    /**
     * F&uuml;r das Verschieben &uuml;ber den Rand
     */
    private int verschiebeUeberRand = -1;


    /**
     * Die Daten-Tabelle
     */
    private Datum[] tabelle;


    /**
     * Variabelen f&uuml;r den interaktiven Modus
     */
    private static final int KORREKTUR    = -1;
    private static final int KEIN_EINTRAG = -3;

    private Rectangle[] kontrollfelder;

    private final int xKorrekturfeld = 400;
    private final int yKorrekturfeld = 5;

    private final int breiteKorrekturfeld = 100;
    private final int hoeheKorrekturfeld = 50;

    private boolean interaktiv = false;

    private Rectangle korrekturfeld = new Rectangle(xKorrekturfeld, yKorrekturfeld, -1, -1);

    private int[] kontrollvektor;
    private int[] korrekturvektor;

    private Datum interaktivDatum;



    /**
     * Der Konstruktor f&uuml;r die Hashtabelle und die Kontroll- und
     * korrekturvektoren
     * @param anzEintr die Gr&ouml;&szlig;e der Hashtabelle
     */
    HashTabelle(int anzEintr) {

	FELD_ANZAHL = anzEintr;
	tabelle = new Datum[FELD_ANZAHL];
	BREITE = (int) Math.rint(524/FELD_ANZAHL)-2;
	LAENGE = FELD_ANZAHL * (BREITE+2);
	BEGINN = 262 - (int) Math.rint(LAENGE/2);

	for (int i=0; i<FELD_ANZAHL; i++) {

	    tabelle[i] = new Datum(FELD_ANZAHL);

	}

	//benoetigt fuer verschieben ueber den Rand
	verschiebeUeberRand = BEGINN+((FELD_ANZAHL)*(BREITE+2));

	//Erzeugen die Kontrollfelder fuer Interaktiv
	kontrollfelder = new Rectangle[FELD_ANZAHL];
	for(int i=0; i<FELD_ANZAHL; i++) {

	    kontrollfelder[i] = new Rectangle(BEGINN+(i*(BREITE+2)) , 130 , BREITE , HOEHE);

	}

	//Erzeugen der Kontroll- und Korrekturvektoren fuer Interaktiv
	kontrollvektor = new int[FELD_ANZAHL];
	korrekturvektor = new int[FELD_ANZAHL];
	for(int i=0; i<FELD_ANZAHL; i++) {

	    kontrollvektor[i] = KEIN_EINTRAG;
	    korrekturvektor[i] = KEIN_EINTRAG;

	}

    }


    /**
     * Die Sondiermethode und der Vorgang werden bestimmt.
     * @param datum das zu bearbeitende Datum
     * @param methode die gew&auml;hlte Sondiermethode
     * @param vorgang Einf&uuml;gen, Suchen oder L&ouml;schen
     * @param tempo die Filmgeschwindigkeit
     * @return das Ergebnis der Aufgabe
     */
    public String sondierMethode(Datum datum, int methode, int vorgang, int tempo) {

	switch(methode) {

	case 1: return this.sondierMethodeEins(datum, vorgang, tempo);
	case 2: return this.sondierMethodeZwei(datum, vorgang, tempo);
	case 3: return this.sondierMethodeDrei(datum, vorgang, tempo);

	}

	return "OHA";

    }

    /**
     * Das lineare Sondieren
     * @param datum das zu bearbeitende Datum
     * @param vorgang Einf&uuml;gen, Suchen oder L&ouml;schen
     * @param tempo die Filmgeschwindigkeit
     * @return das Ergebnis der Aufgabe
     */
    private String sondierMethodeEins(Datum datum, int vorgang, int tempo) {

	int tmp = datum.leseSollIndex();
	int count = 0;
	Graphics g = getGraphics();

	this.zeichneSoll(g, datum, vorgang, tempo);

	if (vorgang == EINFUEGEN) {
	    while(tabelle[tmp].leseSchluessel() > FREI && count < FELD_ANZAHL -1) {
		int verschiebeUmFelder = 1;

		this.zeichneVerschieben(g, datum, tmp, verschiebeUmFelder, vorgang, tempo);

		tmp = (tmp + 1) % FELD_ANZAHL;
		count++;

	    }
	} else {
	    while( (tabelle[tmp].leseSollIndex() != FREI &&
		    tabelle[tmp].leseSchluessel() != datum.leseSchluessel() ) &&
		   count < FELD_ANZAHL -1) {

		int verschiebeUmFelder = 1;

		this.zeichneVerschieben(g, datum, tmp, verschiebeUmFelder, vorgang, tempo);

		tmp = (tmp + 1) % FELD_ANZAHL;
		count++;

	    }

	}

	try {
	    Thread.sleep(PAUSE);
	}
	catch (InterruptedException ie) {}

	switch( vorgang ) {

	case EINFUEGEN: {
	    if( (tabelle[tmp].leseSchluessel() == FREI ||
		 tabelle[tmp].leseSchluessel() == GELOESCHT)
		&& count < FELD_ANZAHL) {
		tabelle[tmp] = datum;
		this.zeichneIst(g, datum, tmp, vorgang, tempo);
		return Integer.toString(tmp);
	    }
	}; break;
	case SUCHEN   : {
	    if( tabelle[tmp].leseSollIndex() == datum.leseSollIndex() &&
		tabelle[tmp].leseSchluessel() == datum.leseSchluessel() ) {
		// this.zeichneIst(g, datum, tmp, vorgang, tempo);
		return Integer.toString(tmp);
	    }
	}; break;
	case LOESCHEN : {
	    if( tabelle[tmp].leseSollIndex() == datum.leseSollIndex() &&
		tabelle[tmp].leseSchluessel() == datum.leseSchluessel() ) {
		this.zeichneIst(g, datum, tmp, vorgang, tempo);
		tabelle[tmp].setzeGeloescht();
		return Integer.toString(tmp);
	    }
	}; break;
	}
	return "Fehler!";

    }

    /**
     * Das lineare Sondieren mit p=2
     * @param datum das zu bearbeitende Datum
     * @param vorgang Einf&uuml;gen, Suchen oder L&ouml;schen
     * @param tempo die Filmgeschwindigkeit
     * @return das Ergebnis der Aufgabe
     */
    private String sondierMethodeZwei(Datum datum, int vorgang, int tempo) {

	int tmp = datum.leseSollIndex();
	int count = 0;
	Graphics g = getGraphics();

	this.zeichneSoll(g, datum, vorgang, tempo);

	if(vorgang == EINFUEGEN) {
	    while( tabelle[tmp].leseSchluessel() > FREI && count < FELD_ANZAHL-1) {
		count++;

		int verschiebeUmFelder = (datum.leseSollIndex() + (count*2)) - (datum.leseSollIndex() + ((count-1)*2));

		this.zeichneVerschieben(g, datum, tmp, verschiebeUmFelder, vorgang, tempo);

		tmp = (datum.leseSollIndex() + (count*2)) % FELD_ANZAHL;
	    }
	} else {
	    while( tabelle[tmp].leseSollIndex() !=  FREI &&
		   tabelle[tmp].leseSchluessel() != datum.leseSchluessel() &&
		   count < FELD_ANZAHL-1) {

		count++;

		int verschiebeUmFelder = (datum.leseSollIndex() + (count*2)) - (datum.leseSollIndex() + ((count-1)*2));

		this.zeichneVerschieben(g, datum, tmp, verschiebeUmFelder, vorgang, tempo);

		tmp = (datum.leseSollIndex() + (count*2)) % FELD_ANZAHL;
	    }
	}

	try {
	    Thread.sleep(PAUSE);
	}
	catch (InterruptedException ie) {}

	switch( vorgang ) {

	case EINFUEGEN: {
	    if( (tabelle[tmp].leseSchluessel() == FREI ||
		 tabelle[tmp].leseSchluessel() == GELOESCHT)
		&& count < FELD_ANZAHL) {
		tabelle[tmp] = datum;
		this.zeichneIst(g, datum, tmp, vorgang, tempo);
		return Integer.toString(tmp);
	    }
	}; break;
	case SUCHEN   : {
	    if( tabelle[tmp].leseSollIndex() == datum.leseSollIndex() &&
		tabelle[tmp].leseSchluessel() == datum.leseSchluessel() ) {
		// this.zeichneIst(g, datum, tmp, vorgang, tempo);
		return Integer.toString(tmp);
	    }
	}; break;
	case LOESCHEN : {
	    if( tabelle[tmp].leseSollIndex() == datum.leseSollIndex() &&
		tabelle[tmp].leseSchluessel() == datum.leseSchluessel() ) {
		this.zeichneIst(g, datum, tmp, vorgang, tempo);
		tabelle[tmp].setzeGeloescht();
		return Integer.toString(tmp);
	    }
	}; break;
	}
	return "Fehler!";

    }

    /**
     * Das quadratische Sondieren
     * @param datum das zu bearbeitende Datum
     * @param vorgang Einf&uuml;gen, Suchen oder L&ouml;schen
     * @param tempo die Filmgeschwindigkeit
     * @return das Ergebnis der Aufgabe
     */
    private String sondierMethodeDrei(Datum datum, int vorgang, int tempo) {

	int tmp = datum.leseSollIndex();
	int count = 0;
	Graphics g = getGraphics();

	this.zeichneSoll(g, datum, vorgang, tempo);

	if (vorgang == EINFUEGEN) {
	    while( tabelle[tmp].leseSchluessel() > FREI && count < ( (FELD_ANZAHL+1)/2 -1  ) ) {
		count++;

		int verschiebeUmFelder = (datum.leseSollIndex() + ((int) Math.pow(count, 2))) - (datum.leseSollIndex() + ((int) Math.pow((count-1), 2)));

		this.zeichneVerschieben(g, datum, tmp, verschiebeUmFelder, vorgang, tempo);

		tmp = (datum.leseSollIndex() + ((int) Math.pow(count, 2))) % FELD_ANZAHL;
                
                
                             
	    }
	} else {
	    while( (tabelle[tmp].leseSollIndex() != FREI &&
		    tabelle[tmp].leseSchluessel() != datum.leseSchluessel())
		   && count < ( (FELD_ANZAHL+1)/2-1 ) ) {
		count++;

		int verschiebeUmFelder = (datum.leseSollIndex() + ((int) Math.pow(count, 2))) - (datum.leseSollIndex() + ((int) Math.pow((count-1), 2)));

		this.zeichneVerschieben(g, datum, tmp, verschiebeUmFelder, vorgang, tempo);

		tmp = (datum.leseSollIndex() + ((int) Math.pow(count, 2))) % FELD_ANZAHL;
	    }
	}

	try {
	    Thread.sleep(PAUSE);
	}
	catch (InterruptedException ie) {}

	switch( vorgang ) {

	case EINFUEGEN: {
	    if( (tabelle[tmp].leseSchluessel() == FREI ||
		 tabelle[tmp].leseSchluessel() == GELOESCHT)
		&& count < ( (FELD_ANZAHL+1)/2) ) {
		tabelle[tmp] = datum;
		this.zeichneIst(g, datum, tmp, vorgang, tempo);
		return Integer.toString(tmp);
	    }
	}; break;
	case SUCHEN   : {
	    if( tabelle[tmp].leseSollIndex() == datum.leseSollIndex() &&
		tabelle[tmp].leseSchluessel() == datum.leseSchluessel() ) {
		// this.zeichneIst(g, datum, tmp, vorgang, tempo);
		return Integer.toString(tmp);
	    }
	}; break;
	case LOESCHEN : {
	    if( tabelle[tmp].leseSollIndex() == datum.leseSollIndex() &&
		tabelle[tmp].leseSchluessel() == datum.leseSchluessel() ) {
		this.zeichneIst(g, datum, tmp, vorgang, tempo);
		tabelle[tmp].setzeGeloescht();
		return Integer.toString(tmp);
	    }
	}; break;
	}
	return "Fehler!";

    }

    // wegen extends Runnable
    public void run() {}

    /**
     * zeichnet den Weg von oben links bis &uuml;ber die Sollposition
     * @param g die Graphik
     * @param datum das zu zeichnende Datum
     * @param vorgang f&uuml;r die Farbgebung
     * @param tempo die Filmgeschwindigkeit
     */
    private void zeichneSoll(Graphics gg, Datum datum, int vorgang, int tempo) {

	Color arbeitsFarbe;

        Image img = createImage(520,128);
        Graphics g = img.getGraphics();


	g.clearRect(0, 0, 520, 128);

	switch( vorgang ) {

	case EINFUEGEN: arbeitsFarbe = BESETZT_FARBE;   break;
	case SUCHEN   : arbeitsFarbe = SUCH_FARBE;      break;
	case LOESCHEN : arbeitsFarbe = GELOESCHT_FARBE; break;
	default: arbeitsFarbe = DEFAULT_FARBE;

	}

	for (int j=0;j<((datum.leseSollIndex()*(BREITE+2)));j=j+2) {

	    g.clearRect(0 , 50 , 520 , HOEHE);
	    g.setColor(arbeitsFarbe);
	    g.fillRect(BEGINN+j , 50 , BREITE , HOEHE);
       	    g.setColor(Color.black);
       	    g.drawString(datum.schluesselToString() , BEGINN+((BREITE/2)-16)+j , 65);
		g.setColor(TABELLE_BESCHRIFTUNG_FARBE);
	    g.drawString(datum.sollIndexToString() , BEGINN+((BREITE/2)-16)+j , 85);
	    try {
		Thread.sleep(tempo);
	    }
	    catch (InterruptedException ie) {}
        gg.drawImage(img,0,-2,this);
	}
	g.clearRect(0 , 50 , 520 , HOEHE);
	for (int j=0;j<40;j=j+2) {
	    if(j>=37){j=39;}

	    g.clearRect(BEGINN+(datum.leseSollIndex()*(BREITE+2)) , 50 , BREITE , 39);
	    g.setColor(arbeitsFarbe);
	    g.fillRect(BEGINN+(datum.leseSollIndex()*(BREITE+2)) , 50+j , BREITE, HOEHE);
	    g.setColor(Color.black);
	    g.drawString(datum.schluesselToString(), BEGINN+(datum.leseSollIndex()*(BREITE+2))+((BREITE/2)-16) , 65+j);
		g.setColor(TABELLE_BESCHRIFTUNG_FARBE);
	    g.drawString(datum.sollIndexToString(), BEGINN+(datum.leseSollIndex()*(BREITE+2))+((BREITE/2)-16) , 85+j);

	    try {
		Thread.sleep(tempo);
	    }
	    catch (InterruptedException ie) {}
	    gg.drawImage(img,0,-2,this);
	}

    }

    /**
     * zeichnet den Weg von &uuml;ber der Tabelle in die Istposition
     * @param g die Graphik
     * @param datum das zu zeichnende Datum
     * @param ort die Istposition
     * @param vorgang f&uuml;r die Farbgebung
     * @param tempo die Filmgeschwindigkeit
     */
    private void zeichneIst(Graphics gg, Datum datum, int ort, int vorgang, int tempo) {
	Color arbeitsFarbe;

        Image img = createImage(BREITE,HOEHE+3);
        Graphics g = img.getGraphics();



	switch( vorgang ) {

	case EINFUEGEN: arbeitsFarbe = BESETZT_FARBE;   break;
	case SUCHEN   : arbeitsFarbe = SUCH_FARBE;      break;
	case LOESCHEN : arbeitsFarbe = GELOESCHT_FARBE; break;
	default: arbeitsFarbe = DEFAULT_FARBE;

	}

	for(int j=0;j<41;j=j+3) {
	    g.clearRect(0, 0 , BREITE , HOEHE+3);
	    g.setColor(arbeitsFarbe);
	    g.fillRect(0 , 3 , BREITE , HOEHE);
	    g.setColor(Color.black);
	    g.drawString(datum.schluesselToString() ,(BREITE/2)-16 , 15+3 );
	    g.setColor(TABELLE_BESCHRIFTUNG_FARBE);
		g.drawString(datum.sollIndexToString() , (BREITE/2)-16 , 35+3 );
	    try {
		Thread.sleep(tempo);
	    }
	    catch (InterruptedException ie) {}
	    gg.drawImage(img,BEGINN+(ort*(BREITE+2)),83+j,this);
	}
	gg.drawImage(img,0,83+41,this);
	// g.clearRect(BEGINN+(ort*(BREITE+2)) , 68+41 , BREITE , HOEHE);
    }

    /**
     * zeichnet den Weg von der Sollposition zur Istposition
     * @param g die Graphik
     * @param datum das zu zeichnende Datum
     * @param ort ab wo verschoben wird
     * @param verschiebeUmFelder um wieviel Felder verschoben werden soll
     * @param vorgang f&uuml;r die Farbgebung
     * @param tempo die Filmgeschwindigkeit
     */
    private void zeichneVerschieben(Graphics gg, Datum datum, int ort, int verschiebeUmFelder, int vorgang, int tempo) {

        Image img = createImage(520,HOEHE+89);
        Graphics g = img.getGraphics();

	try {
	    Thread.sleep(PAUSE);
	}
	catch (InterruptedException ie) {}


	Color arbeitsFarbe;

	switch( vorgang ) {

	case EINFUEGEN: arbeitsFarbe = BESETZT_FARBE;   break;
	case SUCHEN   : arbeitsFarbe = SUCH_FARBE;      break;
	case LOESCHEN : arbeitsFarbe = GELOESCHT_FARBE; break;
	default: arbeitsFarbe = DEFAULT_FARBE;

	}

	int k=0;
	for(int i=0;i<verschiebeUmFelder*(BREITE+2);i=i+3) {
	    g.clearRect(0 , 89 , 520 , HOEHE);

	    if(BEGINN+(ort*(BREITE+2))+i < verschiebeUeberRand) {

		g.setColor(arbeitsFarbe);
		g.fillRect(BEGINN+(ort*(BREITE+2))+i , 89 , BREITE, HOEHE);
		g.setColor(Color.black);
		g.drawString(datum.schluesselToString() , BEGINN+(ort*(BREITE+2))+(BREITE/2)-16+i , 89+15 );
		g.setColor(TABELLE_BESCHRIFTUNG_FARBE);
		g.drawString(datum.sollIndexToString() , BEGINN+(ort*(BREITE+2))+(BREITE/2)-16+i , 89+35 );
	    }
	    if(BEGINN+(ort*(BREITE+2))+i >= verschiebeUeberRand-(BREITE+2)) {
		k=k+3;
		if( k>20 && (k+3)%verschiebeUeberRand<10) {k=0;}
		g.setColor(arbeitsFarbe);
		g.fillRect(BEGINN-(BREITE+2)+k, 89, BREITE , HOEHE);
		g.setColor(Color.black);
		g.drawString(datum.schluesselToString() , BEGINN-(BREITE+2)+(BREITE/2)-16+k , 89+15 );
		g.setColor(TABELLE_BESCHRIFTUNG_FARBE);
		g.drawString(datum.sollIndexToString() , BEGINN-(BREITE+2)+(BREITE/2)-16+k , 89+35 );
	    }
	    try {
		Thread.sleep(tempo);
	    }
	    catch (InterruptedException ie) {}
        gg.drawImage(img,0,-3,this);
	}
	g.clearRect(0 , 89 , 520 , HOEHE);
	g.setColor(arbeitsFarbe);
	g.fillRect(BEGINN+( ((ort+verschiebeUmFelder)%FELD_ANZAHL)*(BREITE+2)) , 89 , BREITE ,  HOEHE);
	g.setColor(Color.black);
	g.drawString(datum.schluesselToString() , BEGINN+( ((ort+verschiebeUmFelder)%FELD_ANZAHL)*(BREITE+2)) + (BREITE/2)-16 , 89+15);
	g.setColor(TABELLE_BESCHRIFTUNG_FARBE);
	g.drawString(datum.sollIndexToString() , BEGINN+( ((ort+verschiebeUmFelder)%FELD_ANZAHL)*(BREITE+2)) + (BREITE/2)-16 , 89+35);
	gg.drawImage(img,0,-3,this);
    }

    /**
     * Zeichnet im nicht interaktiven Modus die Tabelle<br>
     * Zeichnet im interaktiven Modus die Tabelle, die zus&auml;tzlichen
     * Kontrollfelder und das Datum
     * @param g die Graphik
     */
    public void paint(Graphics g) {

	//paint im nicht Interaktiven-Modus**************************************
	if (interaktiv == false) {
	    g.setColor(Color.black);
	    g.fillRect(BEGINN-1,129,LAENGE+1,42);

	    for (int i=0; i<FELD_ANZAHL; i++) {

		switch(tabelle[i].leseSchluessel()){
		case -2 : g.setColor(GELOESCHT_FARBE); break;
		case -1 : g.setColor(FREI_FARBE); break;
		default : g.setColor(BESETZT_FARBE); break;
		}
		g.fillRect(BEGINN+(i*(BREITE+2)) , 130 , BREITE , HOEHE);
		g.setColor(Color.black);
		g.drawString(tabelle[i].schluesselToString() , BEGINN+((BREITE/2)-16)+(i*(BREITE+2)) , 145);
		g.setColor(TABELLE_BESCHRIFTUNG_FARBE);
		g.drawString(tabelle[i].sollIndexToString() , BEGINN+((BREITE/2)-16)+(i*(BREITE+2)) , 165);
		g.setColor(TABELLE_BESCHRIFTUNG_FARBE);
		g.drawString(Integer.toString(i) , BEGINN+((BREITE/2)-16)+(i*(BREITE+2)) , 195);
	    }

	} // Ende paint im nicht Interaktiven-Modus******************************

	//paint im Interaktiven-Modus********************************************
	if (interaktiv == true) {
	    g.setColor(Color.black);
	    g.fillRect(BEGINN-1,129,LAENGE+1,42);

	    for (int i=0; i<FELD_ANZAHL; i++) {

		switch(tabelle[i].leseSchluessel()){
		case -2 : if (tabelle[i].leseMarkiert() == false) {
		    g.setColor(GELOESCHT_FARBE);
		} else {
		    g.setColor(MARKIERT_FARBE);
		} break;
		case -1 : if (tabelle[i].leseMarkiert() == false) {
		    g.setColor(FREI_FARBE);
		} else {

		    g.setColor(MARKIERT_FARBE);
		} break;
		default : if (tabelle[i].leseMarkiert() == false) {
		    g.setColor(BESETZT_FARBE);
		} else {
		    g.setColor(MARKIERT_FARBE);
		} break;
		}
		g.fillRect(BEGINN+(i*(BREITE+2)) , 130 , BREITE , HOEHE);
		g.setColor(Color.black);
		g.drawString(tabelle[i].schluesselToString() , BEGINN+((BREITE/2)-16)+(i*(BREITE+2)) , 145);
		g.setColor(TABELLE_BESCHRIFTUNG_FARBE);
		g.drawString(tabelle[i].sollIndexToString() , BEGINN+((BREITE/2)-16)+(i*(BREITE+2)) , 165);
		g.setColor(TABELLE_BESCHRIFTUNG_FARBE);
		g.drawString(Integer.toString(i) , BEGINN+((BREITE/2)-16)+(i*(BREITE+2)) , 195);
	    }

	    g.setColor(Color.black);
	    g.clearRect(xKorrekturfeld-1, yKorrekturfeld-1, breiteKorrekturfeld+2, hoeheKorrekturfeld+2);
	    g.drawRect(xKorrekturfeld-1, yKorrekturfeld-1, breiteKorrekturfeld+2, hoeheKorrekturfeld+2);
	    g.setColor(FREI_FARBE);
	    g.fillRect(xKorrekturfeld, yKorrekturfeld, breiteKorrekturfeld, hoeheKorrekturfeld);

	    g.setColor(Color.black);
	    Font schrift = g.getFont();
	    g.setFont(new Font(schrift.getName(), Font.PLAIN, 18));
	    g.drawString("Korrektur", xKorrekturfeld+5, yKorrekturfeld+30);
	    g.setFont(schrift);
	    g.setColor(BESETZT_FARBE);
	    g.fillRect(BEGINN, 50, BREITE, HOEHE);
	    g.setColor(Color.black);
	    g.drawString(interaktivDatum.schluesselToString(), BEGINN+(BREITE/2)-16, 65);
		g.setColor(TABELLE_BESCHRIFTUNG_FARBE);
	    g.drawString(interaktivDatum.sollIndexToString(), BEGINN+(BREITE/2)-16, 85);


	} //Ende paint im Interaktiven-Modus*************************************

    }


    //Methoden fuer den interakiven Modus****************************************

    /**
     * Kontrolliert und setzt, ob der interaktive Modus gew&uuml;nscht wird.
     * Dient nicht zum Verlassen des interaktiven Modus!
     */
    public boolean kontrolliereModus(int mx, int my) {

	int i=0;
	try{
	    while(interaktiv == false) {

		interaktiv = kontrollfelder[i].contains(mx, my);
		i++;

		korrekturfeld.setBounds(xKorrekturfeld, yKorrekturfeld, breiteKorrekturfeld, hoeheKorrekturfeld);

	    } //Ende while(interaktiv == false)

	} catch(ArrayIndexOutOfBoundsException aioobe) {

	    return false;

	}

	return interaktiv;

    } //Ende public boolean kontrolliereModus(int mx, int my)

    /**
     * Dient zum verlassen des interaktiven Modus
     */
    public void setzeNichtInteraktiverModus() {

	interaktiv = false;

	//Daten werden auf nicht markiert gesetzt
	for (int i=0; i< FELD_ANZAHL; i++) {

	    tabelle[i].setzeNichtMarkiert();

	}
	//Kontroll- und Korrekturvektor werden zurueckgesetzt
	for (int i=0; i<FELD_ANZAHL; i++) {
	    
	    kontrollvektor[i] = KEIN_EINTRAG;
	    korrekturvektor[i] = KEIN_EINTRAG;
	    
	}
	
    } //Ende public void setzeNichtInteraktiverModus()
    
    /**
     * Bestimmt das angeklickte Feld
     * @param mx die x-Koordinate des Maus-Klicks
     * @param my die y-Koordinate des Maus-Klicks
     * @return die Feldnummer<br>
     * das Tabelle voll bzw. Schl&uuml;ssel nicht in Tabelle Feld<br>
     * das Feld Neu
     */
    public int bestimmeAngeklicktesFeld(int mx, int my) {
	
	// korrekturfeld geklickt
	if (korrekturfeld.contains(mx, my)) {
	    
	    return KORREKTUR;
	    
	} //Ende if (korrekturfeld.contains(mx, my))
	    
	// Tabellenfeld geklickt
	boolean marke = false;
	int i = -1;
	try{
	    while(marke == false) {
		
		i++;
		marke = kontrollfelder[i].contains(mx, my);
		
	    } //Ende while(marke == false)
	    
	    return i;
	    
	} catch(ArrayIndexOutOfBoundsException aioobe) {
	    
	    return KEIN_EINTRAG;
	    
	}
	
    } //Ende public int bestimmeAngeklicktesFeld(int mx, int my)
    
    /**
     * Kennzeichnet die angeklickten Daten als markiert
     * @param angeklicktesFeld das angeklickte Feld
     */ 
    public void setzeMarkiert(int angeklicktesFeld) {
	
	tabelle[angeklicktesFeld].setzeMarkiert();
	
    } //Ende public void setzeMarkiert(int angeklicktesFeld)
    
    /**
     * Schreibt den Kontrollvektor
     * @param pos die Feldnummer
     * @return true wenn erfolgreich<br>
     * false wenn zu viele Eintr&auml;ge vorgenommen werden
     */
    public boolean schreibeInKontrollvektor(int pos) {
	
	int i=-1;
	
	try {
	    do {i++;}
	    while (kontrollvektor[i] != KEIN_EINTRAG);
	}
	catch(ArrayIndexOutOfBoundsException aioobe) {
	    
	    return false;
	    
	}
	
	kontrollvektor[i] = pos;
	return true;
	
    } //Ende public void schreibeInKontrollvektor(int pos)
    
    /**
     * Erstellt das Datum f&uuml;r den interaktiven Modus
     * @param feldAnzahl die Gr&ouml;&szlig;e der Hashtabelle
     * @param schluessel der Schl&uuml;ssel
     */
    public void erstelleInteraktivDatum(int feldAnzahl, int schluessel) {
	
	interaktivDatum = new Datum(feldAnzahl, schluessel);
	
    }
    
    /**
     * &Uuml;bergibt die Kontrolle der interaktiv eingegebenen
     * Reihenfolge entsprechend der Sondiermethode und L&ouml;schen
     * Suchen oder Einf&uuml;gen
     * @param interDatum das Datum
     * @param methode die Sondiermethode
     * @param vorgang Einf&uuml;gen, Suchen oder L&ouml;schen
     * @return true wenn Reihenfolge richig<br>
     * false wenn Reihenfolge falsch
     */
    public boolean kontrolliereInteraktiv(Datum interDatum, int methode, int vorgang) {
	
	switch(methode) {
	    
	case 1: return this.kontrolliereMethodeEins(interDatum, vorgang);
	case 2: return this.kontrolliereMethodeZwei(interDatum, vorgang);
	case 3: return this.kontrolliereMethodeDrei(interDatum, vorgang);
	    
	} //Ende switch Methode
	
	return false;
	
    } //Ende public boolean kontrolliereInteraktiv(Datum interDatum, int methode, int vorgang)


    /**
     * Bei klick auf das Korrekturfeld wird der letzte Eintrag gel&ouml;scht.
     * Falls der Kontrollvektor dann leer ist (also kein Feld markiert), wird
     * wieder in den nicht interaktiven Modus gewechselt.
     * @return true bleibe im interaktiven Modus
     * @return false wechsele zum nicht interaktiven Modus
     */
    public boolean korrektur() {

	int i = 0;

	try {
	    while (kontrollvektor[i] != KEIN_EINTRAG) {
		
		i++;
		
	    }

	} catch(ArrayIndexOutOfBoundsException aioobe) {}

	// i==0 kann nicht auftreten
	if ( i != 1) {

	    tabelle[kontrollvektor[i-1]].setzeNichtMarkiert();
	    kontrollvektor[i-1] = KEIN_EINTRAG;
	    return true;

	} else {

	    this.setzeNichtInteraktiverModus();
	    return false;

	}

    } //Ende public void korrektur()
    
    /**
     * Legt den Kontrollvektor f&uuml;r lineares Sondieren an
     * @param interDatum das Datum
     * @param vorgang Einf&uuml;gen, Suchen, L&ouml;schen
     * @return true wenn Reihenfolge richig<br>
     * false wenn Reihenfolge falsch
     */
    private boolean kontrolliereMethodeEins(Datum interDatum, int vorgang) {
	
	int tmp = interDatum.leseSollIndex();
	int count = 0;
	
	if (vorgang == EINFUEGEN) {
	    
	    while (tabelle[tmp].leseSchluessel() > FREI && count < FELD_ANZAHL) {
		
		korrekturvektor[count] = tmp;
		tmp = (tmp + 1) % FELD_ANZAHL;
		count++;
		
	    }
 
	    if ( (tabelle[tmp].leseSchluessel() == FREI || tabelle[tmp].leseSchluessel() == GELOESCHT) &&
		 (count < FELD_ANZAHL) ) {
		
		korrekturvektor[count] = tmp;
		
	    }

	    return this.bestimmeErgebnis();
	    
	} else {
	    
	    while ( (tabelle[tmp].leseSollIndex() != FREI &&
		     tabelle[tmp].leseSchluessel() != interDatum.leseSchluessel() ) &&
		    count < FELD_ANZAHL ) {
		
		korrekturvektor[count] = tmp;
		tmp = (tmp + 1) % FELD_ANZAHL;
		count++;
		
	    }
	    
	    if (count < FELD_ANZAHL) {

		korrekturvektor[count] = tmp;

	    }
 
	    return this.bestimmeErgebnis();

	} // Ende else if (vorgang == EINFUEGEN)
	
	
    } //Ende private boolean kontrolliereMethodeEins(Datum interDatum, int vorgang)
    
    /**
     * Legt den Kontrollvektor f&uuml;r lineares Sondieren mit p=2 an
     * @param interDatum das Datum
     * @param vorgang Einf&uuml;gen, Suchen, L&ouml;schen
     * @return true wenn Reihenfolge richig<br>
     * false wenn Reihenfolge falsch
     */
    private boolean kontrolliereMethodeZwei(Datum interDatum, int vorgang) {
	
	int tmp = interDatum.leseSollIndex();
	int count = 0;
	
	if (vorgang == EINFUEGEN) {
	    
	    while (tabelle[tmp].leseSchluessel() > FREI && count < FELD_ANZAHL) {
		
		korrekturvektor[count] = tmp;
		count++;
		tmp = (interDatum.leseSollIndex() + (count*2)) % FELD_ANZAHL;
		
	    }
	    
	    if ( (tabelle[tmp].leseSchluessel() == FREI || tabelle[tmp].leseSchluessel() == GELOESCHT) &&
		 (count < FELD_ANZAHL) ) {
 
		korrekturvektor[count] = tmp;

	    }
	    
	    return this.bestimmeErgebnis();
	    
	} else {
	    
	    while ( (tabelle[tmp].leseSollIndex() != FREI &&
		     tabelle[tmp].leseSchluessel() != interDatum.leseSchluessel() ) &&
		    count < FELD_ANZAHL ) {
		
		korrekturvektor[count] = tmp;
		count++;
		tmp = (interDatum.leseSollIndex() + (count*2)) % FELD_ANZAHL;
		
	    }

	    if (count < FELD_ANZAHL) {

		korrekturvektor[count] = tmp;

	    }
	    
	    return this.bestimmeErgebnis();
	    
	} //Ende else if (vorgang == EINFUEGEN)
	
    } //Ende private boolean kontrolliereMethodeZwei(Datum interDatum, int vorgang)
    
    /**
     * Legt den Kontrollvektor f&uuml;r quadratisches Sondieren an
     * @param interDatum das Datum
     * @param vorgang Einf&uuml;gen, Suchen, L&ouml;schen
     * @return true wenn Reihenfolge richig<br>
     * false wenn Reihenfolge falsch
     */
    private boolean kontrolliereMethodeDrei( Datum interDatum, int vorgang) {
	
	int tmp = interDatum.leseSollIndex();
	int count = 0;
	
	if (vorgang == EINFUEGEN) {
	    
	    while (tabelle[tmp].leseSchluessel() > FREI && count < (Math.floor(FELD_ANZAHL/2)) ) {
		
		korrekturvektor[count] = tmp;
		count++;
		tmp = (interDatum.leseSollIndex() + ((int) Math.pow(count, 2))) % FELD_ANZAHL;
		
	    }
	    
	    if ( (tabelle[tmp].leseSchluessel() == FREI || tabelle[tmp].leseSchluessel() == GELOESCHT) &&
		 (count < (Math.floor(FELD_ANZAHL/2))) ){
		
		    korrekturvektor[count] = tmp;

	    }
	    
	    return this.bestimmeErgebnis();
	    
	} else {
	    
	    while ( (tabelle[tmp].leseSollIndex() != FREI &&
		     tabelle[tmp].leseSchluessel() != interDatum.leseSchluessel() ) &&
		    count < (Math.floor(FELD_ANZAHL/2)) ) {
		
		korrekturvektor[count] = tmp;
		count++;
		tmp = (interDatum.leseSollIndex() + ((int) Math.pow(count, 2))) % FELD_ANZAHL;
		
	    }
	    
	    if ( count < (Math.floor(FELD_ANZAHL/2)) ) {

		korrekturvektor[count] = tmp;

	    }
	    
	    return this.bestimmeErgebnis();
	    
	} //Ende else if (vorgang == EINFUEGEN)
	
    } //Ende private boolean kontrolliereMethodeDrei( Datum interDatum, int vorgang)
    
    /**
     * Vergleicht den Kontrollvektor mit dem Korrekturvektor
     * @return true wenn Reihenfolge richig<br>
     * false wenn Reihenfolge falsch
     */
    private boolean bestimmeErgebnis() {
	
	boolean test = true;
	
	for (int i=0; i<FELD_ANZAHL; i++) {
	    
	    test = (kontrollvektor[i] == korrekturvektor[i]) && test;
	    
	} //Ende for (int i=0; i<FELD_ANZAHL+1; i++)
	
	return test;
	
    }
    
    // Ende Methoden fuer Interaktiven-Modus*************************************
    
}

