package Kollisionen;

//
// Anhand des durch diese Datei erzeugten Java-Applets, soll visualisiert
// und veranschaulicht werden, was unter dem Begriff Hashing zu verstehen ist.
// Es werden Datens�tze (kurz: DSS) zuf�llig generiert, dies sind zum einen 
// DSS aus [0 - 9999] und DSS, die Geburtsdaten [TTMM] darstellen. 
// Ausserdem k�nnen Datens�tze [0 - 9999] einzeln eingegeben werden.
// Hierzu werden Hash-Codes aus der "Algorithmen & DS" -Vorlesung verwendet.
// Hierzu z�hlen: 4 Divisionsmethoden und 1 Multiplikationsmethode.
// 

import java.applet.*;
import java.awt.*;
import java.awt.event.*;


// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
// ++ public class Hash extends Applet implements ActionListener, ++++ 
// ++ ItemListener, TextListener                                  ++++
// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

/**
 * @j2sNative 
		var baseURI = document.body.baseURI;
		var isTest = (baseURI == null || baseURI.indexOf("_applet.html") >= 0);
		if (isTest) {
		 			J2S.thisApplet.__Info.width = 600; J2S.thisApplet.__Info.height = 400;
		}
 */

public class Hash extends Applet
                     implements ActionListener, ItemListener, TextListener {
    
    /* Panel kopfoben;
       Label labeloben;
    Panel kopfunten;
       Label fazit;
    Panel oben;
    Panel ebene0;
       Label leereslabel;
    Panel ebene1;
       Choice groesseChoice;
       Button k4;
       TextField tf2;
       Button k5;
       TextField tf3;
    Panel ebene2;
       Choice methodeChoice;
       Button k8;
       TextField tf4;
       Button k7;
    Panel ebene3;
       Button k6;
	*/
	Label schluesselLabel, anzahlLabel, groesseLabel, methodeLabel;
	Choice groesseChoice, methodeChoice, schluesselChoice;
	TextField anzahlTextField;
	Button neuButton, einfuegeButton;
    Panel nord, oben, sued;
	Label fazit, leereslabel;
    Tabelle unten;

    // Attribute und Objekte f�r die einzelnen Klassenmethoden

    private HashTabelle Hashing;
    private int feldanzahl;
    private int methode;
	private int schluesseltyp;
    private int zuweisung;
    private int key = -1;
    private int count = -1;
    private String dss;
    private String dsa;

    // Festlegung von Schriftfonts f�r die einzelnen Texte der Label

	/*
    private String text1 = " Hashing Methoden ";
    private String text2 = " Bitte die Anzahl an Datenfeldern ausw�hlen!";
    private Font fontlabel = new Font(text1, 1, 18);
    private Font fontlabel1 = new Font("", 1, 12);
    private Font fontlabel2 = new Font(text2, 1, 14);
    */

    // �berlagert wird die Methode init(), die das Applet initialisiert.
    public void init() {
    	
      setSize(600,400);	
      this.setBackground(Color.white);
	  // erst die Labels 
      groesseLabel = new Label("Gr\u00f6\u00dfe: ");
	  groesseLabel.setBackground(Color.lightGray);
	  groesseLabel.setAlignment(Label.RIGHT);

	  methodeLabel = new Label("Hash-Funktion: ");
	  methodeLabel.setBackground(Color.lightGray);
	  methodeLabel.setAlignment(Label.RIGHT);

	  schluesselLabel = new Label("Schl\u00fcsseltyp: ");
	  schluesselLabel.setBackground(Color.lightGray);
	  schluesselLabel.setAlignment(Label.RIGHT);

      anzahlLabel = new Label("Anzahl: ");
	  anzahlLabel.setBackground(Color.lightGray);
	  anzahlLabel.setAlignment(Label.RIGHT);

	  fazit = new Label("");
	  fazit.setBackground(Color.white);
	  fazit.setAlignment(Label.CENTER);
	  fazit.setFont( new Font("SansSerif",Font.PLAIN,12));

	  leereslabel = new Label("");
	  leereslabel.setBackground(Color.white);
	  leereslabel.setAlignment(Label.CENTER);
	  leereslabel.setFont( new Font("SansSerif",Font.BOLD,12));


	  // jetzt die Buttons
	  Color buttonColor = new Color(220,145,160);

	  neuButton = new Button("neu");
	  neuButton.setBackground(buttonColor);
	  neuButton.addActionListener(this);

	  einfuegeButton = new Button("einf\u00fcgen");
	  einfuegeButton.setBackground(buttonColor);
	  einfuegeButton.addActionListener(this);

	  
	  /*
	   groesseChoice = new Choice();
	   NeueTabelle(); // remove all / add items
	   groesseChoice.addItemListener( this );
	   groesseChoice.setBackground( Color.white );
	   */
	  
	  
	  // jetzt die Choices und Textfelder
      groesseChoice = new Choice();
	  methodeChoice = new Choice();
	  // Die Choice f�r die Feldanzahl wird gesetzt.
	  NeueTabelle();
	  // Die Choice f�r die Methoden wird gesetzt.
	  NeueHashWahl();
	  groesseChoice.addItemListener( this );
	  groesseChoice.setBackground( Color.white );
	  methodeChoice.setBackground( Color.white );
	  methodeChoice.addItemListener( this );
	  schluesselChoice = new Choice();
	  schluesselChoice.removeAll();
	  schluesselChoice.addItem("Zahlen");
	  schluesselChoice.addItem("Geb.-Daten");
	  schluesselChoice.addItemListener(this);
	  anzahlTextField = new TextField(3);
	  anzahlTextField.addTextListener(this);

	  // jetzt alles unterbringen
	  nord = new Panel();
	  nord.setLayout(new GridLayout(1,5));
	  nord.setBackground(Color.white);
	  nord.add(groesseLabel);
	  nord.add(groesseChoice);
	  nord.add(methodeLabel);
	  nord.add(methodeChoice);
	  nord.add(neuButton);

	  oben = new Panel();
	  oben.setBackground(Color.white);
	  oben.setLayout( new GridLayout(3,1));

	  oben.add(nord);
	  oben.add(leereslabel);
	  oben.add(fazit);

      sued = new Panel();
	  sued.setLayout(new GridLayout(1,5));
	  sued.setBackground(Color.white);
	  sued.add(schluesselLabel);
	  sued.add(schluesselChoice);
	  sued.add(anzahlLabel);
	  sued.add(anzahlTextField);
	  sued.add(einfuegeButton);

    /*
	//Instantiiere die Panel kopfoben, kopfunten mit entspr. Label;
	kopfoben = new Panel();
	kopfunten = new Panel();
	labeloben = new Label(text1, Label.CENTER);
	labeloben.setFont(fontlabel);
	labeloben.setForeground(Color.black);
	fazit = new Label(text2, Label.CENTER);
	fazit.setFont(fontlabel2);
	fazit.setForeground(Color.black);
	kopfoben.setLayout( new GridLayout(1,1) );
	kopfunten.setLayout(new GridLayout(1,1) );
	kopfoben.add(labeloben);
	kopfunten.add(fazit);

	// Panel ebene0 enth�lt ein zus�tzliches (z. T. leeres) Label
	ebene0 = new Panel();
	leereslabel = new Label("", Label.CENTER);
	leereslabel.setFont(fontlabel1);
	leereslabel.setForeground(Color.black);
	ebene0.setLayout(new GridLayout(1,1) );
	ebene0.add(leereslabel);

        // Instantiiere das Panel ebene1 mit zwei Choices.
	// ebene1 enth�lt die Choice f�r Datenfelder und Hash-Methoden.

	ebene1 = new Panel();
	groesseChoice = new Choice();
	methodeChoice = new Choice();
	// Die Choice f�r die Feldanzahl wird gesetzt.
	NeueTabelle();
	// Die Choice f�r die Methoden wird gesetzt.
	NeueHashWahl();
	groesseChoice.addItemListener( this );
	methodeChoice.setBackground( Color.white );
	methodeChoice.addItemListener( this );
	// Die Choices werden dem panel im FlowLayout hinzugef�gt.
	ebene1.add( groesseChoice );
	ebene1.add( methodeChoice );


	// Das Panel ebene2 soll alle Buttons f�r DSS-eingabe enthalten,
	// sowie deren Textfelder. ebene2 enth�lt Button Zufall-1 mit TF,
	// Zufall-2 mit TF, DSS-Eingabe mit TF.

	ebene2 = new Panel();
	// Button Zufallsauswahl-1 mit Textfield tf2
	k4 = new Button("Zufall-1");
	k4.setBackground( Color.lightGray );
	k4.setFont(fontlabel1);
	k4.setForeground(Color.black);
	k4.addActionListener( this );
	tf2 = new TextField("", 8);
	tf2.setEditable(false);
	// Button Zufallsauswahl-2 mit Textfield tf4
	k8 = new Button("Zufall-2");
	k8.setBackground( Color.lightGray );
	k8.setFont(fontlabel1);
	k8.setForeground(Color.black);
	k8.addActionListener( this );
	tf4 = new TextField("", 8);
	tf4.setEditable(false);
	//tf4.addTextListener( this );
	// Button DSS-Eingabe (Einzeleingabe) mit Textfield tf3
	k5 = new Button("DSS-Eingabe");
	k5.setBackground( Color.lightGray );
	k5.setFont(fontlabel1);
	k5.setForeground(Color.black);
	k5.addActionListener( this );
	tf3 = new TextField("", 8);
	tf3.setEditable(false);
	tf3.addTextListener( this );

	// ebene3 wird eingerichtet:
	// folgende Zeile auskommentiert --> FlowLayout.
	ebene2.setLayout( new GridLayout(1,6) );
	ebene2.add( k4 );
	ebene2.add( tf2 );
	ebene2.add( k8 );
	ebene2.add( tf4 );
	ebene2.add( k5 );
	ebene2.add( tf3);

	// ebene3 enth�lt Buttons "Start Hashing" und "Neues Hashing".
	// ebene3 enth�lt daher die Buttons k6 und k7.

	ebene3 = new Panel();
	// Button "Start Hashing"
        k6 = new Button("Start Hashing");
	k6.setFont(fontlabel);
	k6.setForeground(Color.black);
	k6.setBackground( Color.lightGray );
	k6.addActionListener( this );
	// Button "Neues Hashing"
	k7 = new Button("Neues Hashing");
	k7.setFont(fontlabel);
	k7.setForeground(Color.black);
	k7.setBackground( Color.lightGray );
	k7.addActionListener( this );
	// ebene4 wird eingerichtet:
	// nachstehende Zeile auskommentiert --> FlowLayout.
	ebene3.setLayout( new GridLayout(1,2) );
	ebene3.add( k6 );
	ebene3.add( k7 );

        // Instantiiere das Panel oben.
        // Das Panel oben soll die Komponenten
        // ebene0 - ebene3 mit Hilfe eines GridLayouts verwalten.

	oben = new Panel();
        oben.setLayout( new GridLayout(4,1) );
	// oben.add(kopfoben);
	// oben.add(kopfunten);
	oben.add(ebene1);
      	oben.add(ebene2);
	oben.add(ebene3);
	oben.add(ebene0);
    */

      	// Instantiiere die leere Tabelle unten mit einen Feld "0".
	unten = new Tabelle();
	
	//test
	//g = unten.getGraphics();
	//g = null;
	

	// Gesamtes Layout soll Border-Layout sein;
	// dabei soll Hashfilm.java sp�ter noch zu 'Center' hinzugef�gt
	// werden mit: unten + mitte = "Center", wobei mitte = Panel mitte.

	this.setLayout( new BorderLayout());
	// this.add("North", oben);
	this.add("Center", unten);
	this.add("North", oben);
	this.add("South", sued);

	// Gleiches gilt f�r Panel mitte, das die Klasse HashFilm verwaltet.
	// Kann noch implementiert werden: bei Einzeleingabe soll Film
	// in Gang gesetzt werden, der den DSS in sein Feld ziehen soll.
	// rechts = new HashFilm();
    System.out.println("LS | Panel Initialisiert: ok");

    } // Ende: public void init()
    
    public void actionPerformed(ActionEvent ae) { // Methode f. actionListener

	// Es werden 4 Buttons funktionalisiert. Zufall-1, Zufall-2,
	// DSS-Eingabe geben Label-Anweisungen aus und sperren die nicht
	// ben�tigten Textfelder. Button "Start Hashing" f�hrt mit Eingabe-
	// daten die Hash-Methode aus und setzt die Visualisierung in Gang.

fazit.setText("");

    String cmd = ae.getActionCommand();
    System.out.println("LS ActionCommand: " + cmd);
	// labeloben.setText("DSS  =  Datensatzschl�ssel");


	if (cmd.equals("einf\u00fcgen"))
	{
	    // Hauptprogramm: Ermittlung und �bergabe der jew. Hashparameter.
	    // �berpr�ft 1 editierbares Textfeld (andere sind blockiert).

        System.out.println("einfuegen");
		if ((feldanzahl != 0) && (methode != 0) && (count != -1)) {

	    // Instantiiert ein noch nicht ex. Objekt der Klasse HashTabelle.
	    // Falls Objekt bereits existiert, wird dies benutzt (global!).

		    if ( Hashing == null )
		    {
			Hashing = new HashTabelle(feldanzahl+1);
			System.out.println("Hashing = null");
		    }

		    //
		    if ( schluesseltyp == 0 )
		    {
			if (count == 1)
			    fazit.setText(count+" zuf\u00E4lliger Datensatz aus: [0 - 9999] wird eingef\u00fcgt");
			else
			    fazit.setText(count+" zuf\u00E4llige Datens\u00E4tze aus: [0 - 9999] werden eingef\u00fcgt");
			BerechneZufall1();
			LabelAngabe();
			System.out.println("schluesseltyp"+schluesseltyp);
		    }




		    // Hauptaktion (tf4 (TTMM) ist editierbar)
		    if ( schluesseltyp == 1 )
		    {
			if (count == 1)
			    fazit.setText(count+" zuf�lliges Geburtsdatum [TTMM] wird eingef\u00fcgt");
			else
			    fazit.setText(count+" zuf�llige Geburtsdaten [TTMM] werden eingef\u00fcgt");
			BerechneZufall2();
			LabelAngabe();
		    } // Ende tf4.isEditable

		} // Ende if-Anweisung, falls alle Eingaben o.k.

		// Eingabeparameter falsch.
		else {
		   if (( feldanzahl == 0) || (methode == 0) )
		    fazit.setText("Zuerst Gr\u00f6\u00dfe und Funktion ausw\u00e4hlen");
			else
			   fazit.setText("Bitte eine Anzahl eingeben");
		}
	} // Ende if (cmd.equals("einf\u00fcgen"))

	if (cmd.equals("neu"))
	{
	    Hashing = null;
	    unten.balken = null;
	    unten.keymatrix = null;
	    unten.maleFelder(feldanzahl, null);
	    unten.gessumme = 0;
	    // Falls Choice auf Anfangsitem, dann Zeile auskommentieren.
	    // groesseChoice.select(0);
	    // methodeChoice.select(0);
	    // labeloben.setText("Erneute Eingaben f�r Hashing-Methoden");
	    // leereslabel.setText("");
	    LabelAngabe();

	} // Ende if (cmd.equals("neu"))


/*
	fazit.setText("");

    String cmd = ae.getActionCommand();

	// labeloben.setText("DSS  =  Datensatzschl�ssel");

	if (cmd.equals("Zufall-1")) {
	    fazit.setText("Anzahl an allgemeinen DSS ( 1-100 ) ins Textfeld eintragen!");
	    count = -1;
	    tf2.setEditable(true);
	    tf2.addTextListener( this );
	    tf2.setText("");

	    // Bei Wahl: Zufall-1 werden Zufall-2 und Einzeleingabe blockiert.
	    tf4.setText("0");
	    tf4.setEditable(false);
	    tf3.setText("0");
	    tf3.setEditable(false);
	}

	if (cmd.equals("Zufall-2"))
	{
	    fazit.setText("Anzahl an Geburts-DSS 'TTMM' ( 1-100 ) ins Textfeld eintragen!");
	    count = -1;
	    tf4.setEditable(true);
	    tf4.addTextListener( this );
	    tf4.setText("");
      	    // Bei Wahl: Zufall-2 werden Zufall-1 und Einzeleingabe blockiert.
	    tf2.setText("0");
	    tf2.setEditable(false);
	    tf3.setText("0");
	    tf3.setEditable(false);
	}

	if (cmd.equals("DSS-Eingabe"))
	{
	    fazit.setText("Einen DSS (0000-9999) ins nebenstehende Textfeld eintragen!");
	    key = -1;
	    tf3.setEditable(true);
	    //tf3.addTextListener( this );
	    tf3.setText("");
	    // Bei DSS-Eingabe wird Zufallsauswahl komplett hinf�llig.
	    tf2.setText("0");
	    tf2.setEditable(false);
	    tf4.setText("0");
	    tf4.setEditable(false);
	}


	if (cmd.equals("einf\u00fcgen"))
	{
	    // Hauptprogramm: Ermittlung und �bergabe der jew. Hashparameter.
	    // �berpr�ft 1 editierbares Textfeld (andere sind blockiert).

	    if ( (tf2.isEditable()) || (tf4.isEditable()) ) {
		System.out.println(key);
		if ((feldanzahl != 0) && (methode != 0) && (count != -1)) {

	    // Instantiiert ein noch nicht ex. Objekt der Klasse HashTabelle.
	    // Falls Objekt bereits existiert, wird dies benutzt (global!).

		    if ( Hashing == null )
		    {
			Hashing = new HashTabelle(feldanzahl+1);
			System.out.println("Hashing = null");
		    }

		    // Hauptaktion (tf2 (allg. DSS) ist editierbar)
		    if ( tf2.isEditable() )
		    {
			if (count == 1)
			    fazit.setText(count+" zuf�lliger Datensatz aus: [0 - 9999] wird erzeugt!");
			else
			    fazit.setText(count+" zuf�llige Datens�tze aus: [0 - 9999] werden erzeugt!");
			BerechneZufall1();
			LabelAngabe();
		    } // Ende tf2.isEditable()


		    // Hauptaktion (tf4 (TTMM) ist editierbar)
		    else
		    {
			if (count == 1)
			    fazit.setText(count+" zuf�lliges Geburtsdatum [TTMM] wird erzeugt!");
			else
			    fazit.setText(count+" zuf�llige Geburtsdaten [TTMM] werden erzeugt!");
			BerechneZufall2();
			LabelAngabe();
		    } // Ende tf4.isEditable

		} // Ende if-Anweisung, falls alle Eingaben o.k.

		// Eingabeparameter falsch.
		else fazit.setText("Bitte Eingaben �berpr�fen und ggf. korrigieren!");
	    } // if-Ende f�r den Fall, da� 'Zufall' (tf2, tf3) gew�hlt wurde.


	    // Bei Einzelauswahl sind Zufall-Textfelder blockiert.
	    // tf3 ist daher nur noch editierbar.

	    if (tf3.isEditable())
	    {
		if ((feldanzahl != 0) && (methode != 0) && (key != -1))
		{
		    // Instantiiert ein n. n. ex. Objekt von HashTabelle.
		    // Falls Objekt bereits ex., wird dies benutzt (global!).
		    // Anschlie�end erfolgt �bergabe der Hashparameter.

		    if ( Hashing == null ) {
			Hashing = new HashTabelle(feldanzahl+1);
			System.out.println("Hashing = null");
		    }

		    // F�lle den Eingabedatensatz mit "Nullen" auf, um
		    // Labeltext korrekt anzugeben.
		    String eintrag = ""+key;
		    if (key < 1000)
			eintrag = "0"+eintrag;
		    if (key < 100)
			eintrag = "0"+eintrag;
		    if (key < 10)
			eintrag = "0"+eintrag;

		    fazit.setText("Der Datensatz '"+eintrag+"' wird zugewiesen.");
		    DatenTransfer();
		    LabelAngabe();

		} // Ende if-Anweisung, falls alle Eingaben o.k.

		else fazit.setText("Bitte Eingaben �berpr�fen und ggf. korrigieren!");
	    } // if-Ende f�r den Fall, da� Einzelauswahl gew�hlt wurde.

	} // Ende if (cmd.equals("Start Hashing"))

	if (cmd.equals("neu"))
	{
	    Hashing = null;
	    unten.balken = null;
	    unten.keymatrix = null;
	    unten.maleFelder(feldanzahl);
	    unten.gessumme = 0;
	    // Falls Choice auf Anfangsitem, dann Zeile auskommentieren.
	    // groesseChoice.select(0);
	    // methodeChoice.select(0);
	    // labeloben.setText("Erneute Eingaben f�r Hashing-Methoden");
	    // leereslabel.setText("");
	    LabelAngabe();

	} // Ende if (cmd.equals("Neues Hashing"))
*/
    } //Ende: public void actionPerformed(ActionEvent ae)


    // Implementiere die Methode f�r das Interface ItemListener.
    // �berschrieben wird die (abstr.) Methode des Interfaces.

    public void itemStateChanged(ItemEvent e) {
    	System.out.println("LS: " + e);
	int altefeldanz;
	int alterkey;
	int[] alterbalken;
	int[][] altematrix;
	boolean firstchoice = false;

	fazit.setText("");
    methode = methodeChoice.getSelectedIndex();
	feldanzahl = groesseChoice.getSelectedIndex();
	System.out.println("LS Feldanzahl: " + feldanzahl);

	schluesseltyp = schluesselChoice.getSelectedIndex();
	if (methode==0) leereslabel.setText("");
	else LabelAngabe();
	//
	// Choice Feldanzahl wird funktionalisiert.
	// Eine dynamische Tabelle wird generiert.

	// Noch kein Objekt Hashing wurde instantiiert.
	if ((feldanzahl != 0) && (Hashing == null))
	{
	    unten.maleFelder(feldanzahl, null);
	    // Bei "korrekter" Itemwahl �ndert sich das Label.
	    firstchoice = true;
	} // Ende Datenfelder werden bei Wahl von Feldanzahl gemalt.


	// Entspr. Umverteilung von Daten auf die neuen Datenfelder, aber
	// nur, falls bereits Datens�tze existieren (Hashing != null).
	// Hier existiert das Objekt HashTabelle Hashing bereits.
	// Zwischenspeicherung der alten Daten.

	if ((feldanzahl != 0) && (Hashing != null))
	{
	    altefeldanz = unten.felder;
	    Hashing = null;
	    Hashing = new HashTabelle(feldanzahl+1);
	    unten.maleFelder(feldanzahl, null);
	    firstchoice = true;
	    if ((key != -1) && (methode!=0))
	    {
		alterbalken = unten.balken;
		altematrix = unten.keymatrix;
		alterkey = key;
		// Keymatrix wird mit alten Werten neu aufgebaut.
		unten.keymatrix = null;
		LabelAngabe();
		for (int i=0; i<=altefeldanz-1; i++) {
		    for (int j = 1; j<= alterbalken[i]; j++) {
			key = altematrix[i][j];
			DatenTransfer();
			System.out.println("Matrix = "+i+","+j);
		    } // innere for-Anweisung
		} // �u�ere for-Anweisung
		// Alter Keywert soll noch verwendet werden k�nnen.
		key = alterkey;
	    } // Ende if-Anweisung: Durchf�hrung bei vollst. Eingabe.
	} // Ende if-Anweisung f�r entspr. Umverteilung der Datens�tze.


	// Setzen der entspr. Label, falls Benutzereingaben fehlen!

	if ((feldanzahl == 0) && (methode == 0))
	    fazit.setText("Zuerst Gr\u00fc\u00dfe und Funktion ausw�hlen!");
	if ((feldanzahl != 0) && (methode == 0))
	    fazit.setText("Die Funktion noch ausw�hlen!");
	if ((feldanzahl == 0) && (methode != 0))
	    fazit.setText("Die Gr\u00fc\u00dfe noch ausw�hlen!");

	// Label, falls Benutzer zum ersten Mal Applet bedient.
	if ((methode != 0) && (firstchoice) && (key == -1))
	{
	    fazit.setText("");
	    LabelAngabe();
	}
	if ((methode == 0) && (firstchoice))
	    fazit.setText("Bitte eine Hash-Funktion ausw�hlen!");

    }// ende itemStateChanged(ItemEvent e)


    // Implementiere die Methode fuer das Interface TextListener
    public void textValueChanged(TextEvent e) {

		dsa = anzahlTextField.getText();
	    // try-Block, wenn Eingabetext kein Integer-Wert ist,
	    // oder dieser nicht im Intervall [1 - 100] liegt.
	    try {
		count = Integer.valueOf(dsa).intValue();
		if ((count<1) || (count>100)) {
		    count = -1;
		    throw new RangeException(count);
		}
	    } // Ende try-Umgebung

	    catch (NumberFormatException nfe) {
		// Argument ist kein Integer, aber nicht der Leerstring.
		if (!dsa.equals(""))
		    fazit.setText("Bitte ein Zahl zwischen 1 und 100 eingeben");
	    } // Ende 1. Catch-Block

	    catch (RangeException re) {
		fazit.setText("Anzahl muss zwischen 1 und 100 liegen");
	    } // Ende 2. Catch-Block


    } // Ende textValueChanged(TextEvent e)



    // Zus�tzliche Klassenmethoden

    private void BerechneZufall1() { // Zufall-1 - Berechnung

	// Die DSS werden per Zufall ermittelt und zugewiesen.
	for (int i = 1; i <= count; i++)
	{
	    // Ein Datensatz (allgemein) wird zuf�llig ermittelt.
	    // Anschlie�end erfolgt der Datentransfer

	    key = ((int)Math.ceil(9999.0*Math.random()));
	    DatenTransfer();

	} // Ende for-Schleife (1 - count)
    } // Ende BerechneZufall1()


    private void BerechneZufall2() { // Zufall-2 - Berechnung

        // Die DSS werden per Zufall ermittelt und zugewiesen.
	for (int i = 1; i <= count; i++)
	{
	    // Ein Geburtsdatum wird zuf�llig ermittelt.
	    // Anschlie�end erfolgt der Datentransfer.

	    key = ErmittleTTMM();
	    DatenTransfer();

	} // Ende for-Schleife (1 - count)

    } // Ende BerechneZufall2


    private void DatenTransfer() { // Berechnung u. Transfer der Hashwerte

	// Ermittelt wird der Hashwert zu dem Datenschl�ssel (key).
	// Es erfolgt eine Aufdatierung der jew. Tabellens�ule,
	// sowie das Einzeichnen des entspr. S�ulenst�cks.

	zuweisung = Hashing.HashEintrag(key, methode);
	Hashing.NeueSaeule(zuweisung);
	if (Hashing.saeule[zuweisung]>14)
	    fazit.setText("Nicht mehr graphisch darstellbar - intern geht es    bis 19 weiter!");
	unten.maleSaeule(Hashing.saeule, zuweisung, key);

    } // Ende DatenTransfer()


    private int ErmittleTTMM() { // Ermittlung eines Geburtsdatums (TTMM)

	int zahl1, zahl2, zahl;

	zahl1 = (1+(int)Math.floor(31.0*Math.random()));

	if (zahl1==28) {
	    zahl2 = (1+(int)Math.floor(12.0*Math.random()));
	}
	else if (zahl1==31) {
	    do {
		zahl2 = (1+(int)Math.floor(12.0*Math.random()));
	    } while (((zahl2==2)||(zahl2==4))||((zahl2==6)||(zahl2==9))||
		     ((zahl2==11)));
	} else if ((zahl1>=29)&&(zahl1<=30)) {
	    do {
		zahl2 = (1+(int)Math.floor(12.0*Math.random()));
	    } while (zahl2==2);
	    // Fall: Tag TT<28
	} else {
	    zahl2 = (1+(int)Math.floor(12.0*Math.random()));
	}
	zahl = (zahl1*100)+zahl2;
	return zahl;

    } // Ende ErmittleTTMM()


    private void LabelAngabe() { // LabelAngabe()

	if (methode==1)
	    leereslabel.setText("Funktion 1:  s1s2s3s4  ---> s4 mod "+(feldanzahl+1)+"");
	if (methode==2)
	    leereslabel.setText("Funktion 2: s1s2s3s4  ---> s1+s4 mod "+(feldanzahl+1)+"");
	if (methode==3)
	    leereslabel.setText("Funktion 3: s1s2s3s4  ---> s1+s2+s3+s4 mod "+(feldanzahl+1)+"");
	if (methode==4)
	    leereslabel.setText("Funktion 4: s1s2s3s4  ---> (s1s2s3s4) mod "+(feldanzahl+1)+"");
	if (methode==5)
	    leereslabel.setText("Funktion 5: s1s2s3s4  ---> [ ( (t*(s1s2s3s4)) mod 1 )*("+(feldanzahl+1)+")], t irrational");

    } // Ende LabelAngabe()


    private void NeueTabelle() { // Choice f�r die Feldanzahl
	groesseChoice.removeAll();
	groesseChoice.addItem("  ");
	for (int i = 2; i <= 24; i++) {
	    groesseChoice.addItem(i +" Felder");
	}
    } // Ende NeueTabelle


    private void NeueHashWahl() { // Choice f�r die Hashmethode.
	methodeChoice.removeAll();
	methodeChoice.addItem("   ");
	methodeChoice.addItem("Funktion 1");
	methodeChoice.addItem("Funktion 2");
	methodeChoice.addItem("Funktion 3");
	methodeChoice.addItem("Funktion 4");
	methodeChoice.addItem("Funktion 5");
    } // Ende NeueHashWahl


} // Ende public class Hash extends Applet implements ActionListener, ...

class RangeException extends Exception {
    private int zahl;
    public RangeException(int zahl) {
	this.zahl = zahl;
    }
}









