package interneVerkettung;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Die Hash Tabelle beinhaltet die Methoden f&uuml;r das Einsortieren, Suchenn
 * und L&ouml;schen, sowie den Film
 */
public class Hash extends Applet implements ActionListener, ItemListener, MouseListener {

    /**
     * Die HTML Voreinstellung f&uuml;r den Interaktiven Modus
     */
    private boolean HTML_Interaktiv = false;

    /**
     * Die Farben
     */
    private static final Color HINTERGRUND_FARBE = Color.white;
    private static final Color BUTTON_FARBE = Color.lightGray;
    private static final Color CHOICE_FARBE = Color.lightGray;
    
    /**
     * Die Vorg&auml;nge, die durchgef&uuml;hrt werden sollen
     */
    private static final int EINFUEGEN = 1;
    private static final int SUCHEN = 2;
    private static final int LOESCHEN = 3;

    /**
     * Die ben&ouml;tigten Panel
     */
    private Panel oben;
	private Panel ganzoben;
	private Panel ganzobenLinks;
    private Panel bedienfeld;
    private Panel bedienfeldTabelle;
    private Panel bedienfeldSondierMethode;
    private Panel bedienfeldSchluessel;
    private Panel bedienfeldTempo;

    /**
     * Die Label f&uuml;r die Kommunikation mit dem Benutzer
     */
    private Label label1;
    private Label label2;

    /**
     * Die Label f&uuml;r die Beschriftungen
     */
    private Label beschriftungTabelle = new Label("Tabelle", Label.RIGHT);
    private Label beschriftungEingabe = new Label("Key", Label.RIGHT);
    private Label beschriftungTempo = new Label("Tempo", Label.RIGHT);
	private Label schluesselLabel;
	private Label tabellenLabel;
    private Label methodenLabel;

    /**
     * Die Choices f&uuml;r Tabellengr&ouml;&zslig;e und die Sondiermethode
     */
    private Choice tabellenGroesse;
    private Choice sondierMethode;

    /**
     * Die Scrollbar f&uuml;r die Geschwindigkeitsregelung
     */
    private Scrollbar tempo;

    /**
     * Die Buttons
     */
    private Button einfuegen;
    private Button suchen;
    private Button loeschen;
    private Button tabelleErstellen;

    /**
     * Das Textfeld f&uuml;r die Schl&uuml;sseleingabe
     */
    private TextField schluesselEingabe;

    /**
     * Die Hashtabelle
     */
    private HashTabelle tabelle;
    //private HashTabelle erase;

    /**
     * Die Tabellengr&ouml;&szlig;e
     */
    private int feldAnzahl;
    private int methode;

    /**
     * Der interaktive Modus
     */
    private boolean interaktiv = false;
    private boolean erstelle = false;
    private String aktion = "Klicke das n\u00e4chste Feld oder eine Aktion!";
    private final int KORREKTUR = -1;

    /**
     * Erzeugt das Applet mit oben der Beschriftung und unten den
     * Bedienfeldern; jedoch ohne Tabelle
     */
    public void init() {

	 HTML_Interaktiv = "true".equalsIgnoreCase(getParameter("HTML_Interaktiv"));

	this.setBackground(HINTERGRUND_FARBE);

	//die Label oben fuer die Kommunikation mit dem Benutzer
	//das Panel fuer die Label und das Layout
	oben = new Panel();
	oben.setLayout(new GridLayout(2, 1));

	ganzobenLinks = new Panel();
	ganzobenLinks.setLayout(new GridLayout(1,4));

	ganzoben = new Panel();
	ganzoben.setLayout( new BorderLayout() );

	//das obere Label
	String text1 = "Hashing: Interne Verkettung";
	Font fontLabel1 = new Font("SansSerif", Font.BOLD, 14);
	label1 = new Label(text1, Label.CENTER);
	label1.setFont(fontLabel1);

	//das untere Label
	String text2 = "W\u00e4hle Tabellengr\u00f6\u00dfe und Sondiermethode und klicke 'neu'";
	Font fontLabel2 = new Font("SansSerif", Font.PLAIN, 12);
	label2 = new Label(text2, Label.CENTER);
	label2.setFont(fontLabel2);

	//die Label in das Panel oben
	oben.add(ganzoben);
	oben.add(label2);

	//Unten: Schluesseleingabe Einfuegen Suchen Loeschen
	bedienfeld = new Panel();

	//das Panel fuer die Beschriftung und das Textfeld Schluesseleingabe
	bedienfeldSchluessel = new Panel();
	bedienfeldSchluessel.setLayout(new FlowLayout());
	schluesselEingabe = new TextField("", 4);
	schluesselEingabe.setEditable(false);
	bedienfeldSchluessel.add(beschriftungEingabe);
	bedienfeldSchluessel.add(schluesselEingabe);

	//die Buttons Einfuegen, Suchen, Loeschen
	einfuegen = new Button("einf\u00fcgen");
        einfuegen.setFont(new Font("SansSerif", Font.PLAIN,12));
	einfuegen.setBackground(BUTTON_FARBE);
	einfuegen.addActionListener(this);
	suchen = new Button("suchen");
	suchen.setBackground(BUTTON_FARBE);
        suchen.setFont(new Font("SansSerif", Font.PLAIN,12));
	suchen.addActionListener(this);
	loeschen = new Button("l\u00f6schen");
	loeschen.setBackground(BUTTON_FARBE);
        loeschen.setFont(new Font("SansSerif", Font.PLAIN,12));
	loeschen.addActionListener(this);

	//Choice fuer Tabellengroesse und Beschriftung
	tabellenGroesse = new Choice();
	tabellenGroesse.setBackground(CHOICE_FARBE);
	tabellenGroesse.add("7");
	tabellenGroesse.add("8");
	tabellenGroesse.add("9");
	tabellenGroesse.add("10");
	tabellenGroesse.add("11");
	tabellenGroesse.add("12");
	tabellenGroesse.add("13");
        tabellenGroesse.addItemListener(this);
	bedienfeldTabelle = new Panel();
	bedienfeldTabelle.setLayout(new GridLayout(1,2));
	bedienfeldTabelle.add(beschriftungTabelle);
	bedienfeldTabelle.add(tabellenGroesse);

	//Choice fuer Sondiermethode
	sondierMethode = new Choice();
	sondierMethode.setBackground(CHOICE_FARBE);
	sondierMethode.add("linear, p=1");
	sondierMethode.add("linear, p=2");
	sondierMethode.add("quadratisch");
        sondierMethode.addItemListener(this);
	bedienfeldSondierMethode = new Panel();
	bedienfeldSondierMethode.setLayout(new FlowLayout());
      	bedienfeldSondierMethode.add(sondierMethode);

	//der Button fuer das erstellen der Tabelle und das festlegen auf
	//die Sondiermethode
	tabelleErstellen = new Button("neu");
	tabelleErstellen.setBackground(BUTTON_FARBE);
        tabelleErstellen.setFont(new Font("SansSerif", Font.PLAIN,12));
	tabelleErstellen.addActionListener(this);

	//die Scrollbar fuer die Geschwindigkeitsregelung und die Beschriftung
	tempo = new Scrollbar(Scrollbar.HORIZONTAL , 16 , 4 , 6 , 40);
	bedienfeldTempo = new Panel();
	bedienfeldTempo.setLayout(new GridLayout(1,2));
	bedienfeldTempo.add(beschriftungTempo);
	bedienfeldTempo.add(tempo);

	// die Labels
	schluesselLabel = new Label("Schl\u00fcssel: ");
	schluesselLabel.setBackground(BUTTON_FARBE);
    schluesselLabel.setAlignment(Label.RIGHT);
	tabellenLabel = new Label("Gr\u00f6\u00dfe: ");
    tabellenLabel.setBackground(BUTTON_FARBE);
	tabellenLabel.setAlignment(Label.RIGHT);
	methodenLabel = new Label("Methode: ");
	methodenLabel.setBackground(BUTTON_FARBE);
	methodenLabel.setAlignment(Label.RIGHT);

	//Layout fuer das Bedienfeld unten
	bedienfeld.setLayout(new GridLayout(1, 5));
	// bedienfeld.add(bedienfeldSchluessel);
	bedienfeld.add(schluesselLabel);
    bedienfeld.add(schluesselEingabe);
	bedienfeld.add(einfuegen);
	bedienfeld.add(suchen);
	bedienfeld.add(loeschen);
	ganzobenLinks.add(tabellenLabel);
    ganzobenLinks.add(tabellenGroesse);
	// bedienfeld.add(bedienfeldTabelle);
	//bedienfeld.add(bedienfeldSondierMethode);
	ganzobenLinks.add(methodenLabel);
    ganzobenLinks.add(sondierMethode);
	// ganzoben.add(tabelleErstellen);
	// bedienfeld.add(bedienfeldTempo);
    ganzobenLinks.add(tempo);
	ganzoben.add("Center",ganzobenLinks);
	ganzoben.add("West",tabelleErstellen);

	//das Gesamtlayout ohne die Tabelle
	//die Tabelle wird erst nach Betaetigung des Buttons Erstellen erstellt
	this.setLayout(new BorderLayout());
	this.add("North", oben);
	this.add("South", bedienfeld);
    System.out.println("Step: Init");
    } //Ende public void init()

	/**
	 * Steuert die Reaktionen auf die Bet&auml;tigung der Buttons
	 */
	public void actionPerformed(ActionEvent ae) {
		String cmd = ae.getActionCommand();
		System.out.println("Step: ActionEvent: " + cmd);
		System.out.println("Tempo: " + (46 - tempo.getValue()));

		// Reaktion auf betaetigung des Buttons erstellen
		// es wird eine Tabelle mit der gewaehlten Spaltenzahl erstellt
		// und die Sondiermethode festgelegt
		if (cmd.equals("neu")) {
			doNew();
		} else if (interaktiv) {
			doInteractive(cmd);
		} else {
			doNotInteractive(cmd);
		}

	} // END public void actionPerformed(actionEvent ae)


	private void doNew() {
	    //verlaesst gegebenenfalls den Interaktiven-Modus
	    interaktiv = false;
	    erstelle = false;
	    feldAnzahl = Integer.valueOf(tabellenGroesse.getSelectedItem()).intValue();

	    methode = sondierMethode.getSelectedIndex() + 1;
	    tabelle = new HashTabelle(feldAnzahl);
	    // nicht notwendig, da sowieso neuer Vektor - ich bin sooooooo bloed
	    //	tabelle.setzeNichtInteraktiverModus();
	    
	    //Center wird gegebenenfalls freigeraeumt
	    //this.remove(this.getComponentAt(200,200));

	    
	    //die Tabelle wird in Center des Layout gesetzt
	    this.add("Center", tabelle);
	    tabelle.addMouseListener(this);

	    //mindestens eine dieser Methoden ist notwendig, um das
	    //Layout zu realisieren
	    this.doLayout();
	    this.validate();
		
	    //das Textfeld fuer die Schluesseleingabe wird freigegeben
	    schluesselEingabe.setText("");
	    schluesselEingabe.setEditable(true);

	    //label1, also die Ueberschrift wird geschrieben
	    if ((sondierMethode.getSelectedItem()).equals("linear, p=2")) {
		label1.setText("Tabelle mit " +feldAnzahl +" Spalten und lineares Sondieren mit p=2");
	    }
		if ((sondierMethode.getSelectedItem()).equals("linear, p=1")) {
		label1.setText("Tabelle mit " +feldAnzahl +" Spalten und lineares Sondieren mit p=1");
		}
		if ((sondierMethode.getSelectedItem()).equals("quadratisch")) {
		label1.setText("Tabelle mit " +feldAnzahl +" Spalten und quadratisches Sondieren");
		}

		// label1.setText("Tabelle mit " +feldAnzahl +" Spalten und " +sondierMethode.getSelectedItem() +"es Sondieren");
	    //}

	    //die Aufforderung fuer eine Schluesseleingabe wird in label2 geschrieben
	    label2.setText("Gib einen Schl\u00fcssel zwischen 0 und 9999 ein");

	    tabelle.repaint();
	}

	private void doInteractive(String cmd) {
		// Reaktion auf Betaetigung des Buttons EINFUEGEN
		if (cmd.equals("einf\u00fcgen")) {
			System.out.println("Tempo: " + tempo);
			String key = schluesselEingabe.getText();
			int schluessel = Integer.valueOf(key).intValue();
			Datum interaktivDatum = new Datum(feldAnzahl, schluessel);
			if (tabelle.kontrolliereInteraktiv(interaktivDatum, methode, EINFUEGEN)) {

				String erg = tabelle.sondierMethode(interaktivDatum, methode, EINFUEGEN, (46 - tempo.getValue()));

				if (erg.equals("Fehler!")) {

					label2.setText("Richtig: Tabelle voll!");
					schluesselEingabe.setText("");

				} else {

					label2.setText("Richtig: Schl\u00fcssel " + interaktivDatum.schluesselToString()
							+ " eingef\u00fcgt an Stelle " + erg);
					schluesselEingabe.setText("");

				} // Ende if (erg.equals("Fehler!"))

			} else {

				label2.setText("Leider falsch! Versuche es noch einmal.");

			} // Ende else if (tabelle.kontrolliereInteraktiv(interaktivDatum, methode,
				// EINFUEGEN))

			interaktiv = false;
			tabelle.setzeNichtInteraktiverModus();
			erstelle = false;
			schluesselEingabe.setEditable(true);
			tabelle.repaint();

		} // Ende if (cmd.equals("EINF\u00dcGEN"))

		// Reaktion auf Betaetigung des Button LOESCHEN
		if (cmd.equals("suchen")) {

			String key = schluesselEingabe.getText();
			int schluessel = Integer.valueOf(key).intValue();
			Datum interaktivDatum = new Datum(feldAnzahl, schluessel);
			if (tabelle.kontrolliereInteraktiv(interaktivDatum, methode, SUCHEN)) {

				String erg = tabelle.sondierMethode(interaktivDatum, methode, SUCHEN, (46 - tempo.getValue()));
				if (erg.equals("Fehler!")) {

					label2.setText("Richtig: Schl\u00fcssel nicht in Tabelle!");
					schluesselEingabe.setText("");

				} else {

					label2.setText("Richtig: Schl\u00fcssel " + interaktivDatum.schluesselToString()
							+ " gefunden an Stelle " + erg);
					schluesselEingabe.setText("");

				} // Ende if (erg.equals("Fehler!"))

			} else {

				label2.setText("Leider falsch! Versuche es noch einmal.");

			} // Ende else if (tabelle.kontrolliereInteraktiv(interaktivDatum, methode,
				// EINFUEGEN))

			interaktiv = false;
			tabelle.setzeNichtInteraktiverModus();
			erstelle = false;
			schluesselEingabe.setEditable(true);
			tabelle.repaint();

		} // Ende if (cmd.eqals("suchen"))

		// Reaktion auf Betaetigung des Button LOESCHEN
		if (cmd.equals("l\u00f6schen")) {

			String key = schluesselEingabe.getText();
			int schluessel = Integer.valueOf(key).intValue();
			Datum interaktivDatum = new Datum(feldAnzahl, schluessel);
			if (tabelle.kontrolliereInteraktiv(interaktivDatum, methode, LOESCHEN)) {

				String erg = tabelle.sondierMethode(interaktivDatum, methode, LOESCHEN, (46 - tempo.getValue()));
				if (erg.equals("Fehler!")) {

					label2.setText("Richtig: Schl\u00fcssel nicht in Tabelle!");
					schluesselEingabe.setText("");

				} else {

					label2.setText("Richtig: Schl\u00fcssel " + interaktivDatum.schluesselToString()
							+ " gel\u00f6scht an Stelle " + erg);
					schluesselEingabe.setText("");

				} // Ende if (erg.equals("Fehler!"))

			} else {

				label2.setText("Leider falsch! Versuche es noch einmal.");
			} // Ende else if (tabelle.kontrolliereInteraktiv(interaktivDatum, methode,
				// EINFUEGEN))

			interaktiv = false;
			tabelle.setzeNichtInteraktiverModus();
			erstelle = false;
			schluesselEingabe.setEditable(true);
			tabelle.repaint();

		} // Ende if (cmd.eqals("l\u00f6schen"))

	}

	private void doNotInteractive(String cmd) {

		// Reaktion auf Betaetigung des Button EINFUEGEN
		label2.setText("");
		if (cmd.equals("einf\u00fcgen")) {

			try {

				String key = schluesselEingabe.getText();
				int schluessel = Integer.valueOf(key).intValue();
				schluesselEingabe.setText("");

				// bei falscher Zahleneingabe
				if (schluessel < 0 || schluessel >= 10000) {
					label2.setText("Nur Schl\u00fcssel zwischen 0 und 9999!");
				}

				// bei richtiger Zahleneingabe wird einfuegen in der
				// HashTabelle aufgerufen. Die Rueckgabe aendert label2
				if (schluessel >= 0 && schluessel < 10000) {
					Datum eingabeDatum = new Datum(feldAnzahl, schluessel);
					String erg = tabelle.sondierMethode(eingabeDatum, methode, EINFUEGEN, (46 - tempo.getValue()));
					if (erg.equals("Fehler!")) {
						label2.setText("Tabelle voll!");
					} else {
						label2.setText("Schl\u00fcssel " + eingabeDatum.schluesselToString()
								+ " eingef\u00fcgt an Stelle " + erg);
					}
				}
				// bei falscher Eingabe in das Textfeld oder gar keiner Eingabe
				// wird die Number Format Exception gefangen
			} catch (NumberFormatException nfe) {

				label2.setText("Nur Schl\u00fcssel zwischen 0 und 9999!");
				schluesselEingabe.setText("");

			}
			// Die gegebenenfalls veraenderte Tabelle wird gezeichnet
			try {

				tabelle.repaint();

			} catch (NullPointerException npe) {

				label2.setText("Erst mit 'neu' eine Tabelle erstellen!");

			}

		} // Ende Button EINFUEGEN

		// Reaktion auf Betaetigung des Button suchen
		if (cmd.equals("suchen")) {

			try {

				String key = schluesselEingabe.getText();
				int schluessel = Integer.valueOf(key).intValue();
				schluesselEingabe.setText("");

				// bei falscher Zahleneingabe
				if (schluessel < 0 || schluessel >= 10000) {
					label2.setText("Nur Schl\u00fcssel zwischen 0 und 9999!");
				}

				// bei richtiger Zahleneingabe wird suchen in der
				// HashTabelle aufgerufen. Die Rueckgabe aendert label2
				if (schluessel >= 0 && schluessel < 10000) {
					Datum eingabeDatum = new Datum(feldAnzahl, schluessel);
					String erg = tabelle.sondierMethode(eingabeDatum, methode, SUCHEN, (46 - tempo.getValue()));
					if (erg.equals("Fehler!")) {
						label2.setText("Schl\u00fcssel nicht in Tabelle!");
					} else {
						label2.setText(
								"Schl\u00fcssel " + eingabeDatum.schluesselToString() + " gefunden an Stelle " + erg);
					}
				}
				// bei falscher Eingabe in das Textfeld oder gar keiner Eingabe
				// wird die Number Format Exception gefangen
			} catch (NumberFormatException nfe) {

				label2.setText("Nur Schl\u00fcssel zwischen 0 und 9999!");
				schluesselEingabe.setText("");

			}

			// Die Tabelle wird gezeichnet
			try {
				System.out.println("Step: Tabelle gezeichnet");
				tabelle.repaint();

			} catch (NullPointerException npe) {

				label2.setText("Erst mit 'neu' eine Tabelle erstellen!");

			}

		} // Ende Button suchen

		// Reaktion auf Betaetigung des Button LOESCHEN
		if (cmd.equals("l\u00f6schen")) {

			try {

				String key = schluesselEingabe.getText();
				int schluessel = Integer.valueOf(key).intValue();
				schluesselEingabe.setText("");

				// bei falscher Zahleneingabe
				if (schluessel < 0 || schluessel >= 10000) {
					label2.setText("Nur Schl\u00fcssel zwischen 0 und 9999!");
				}

				// bei richtiger Zahleneingabe wird suchen in der
				// HashTabelle aufgerufen. Die Rueckgabe aendert label2
				if (schluessel >= 0 && schluessel < 10000) {
					Datum eingabeDatum = new Datum(feldAnzahl, schluessel);
					String erg = tabelle.sondierMethode(eingabeDatum, methode, LOESCHEN, (46 - tempo.getValue()));
					if (erg.equals("Fehler!")) {
						label2.setText("Schl\u00fcssel nicht in Tabelle!");
					} else {
						label2.setText("Schl\u00fcssel " + eingabeDatum.schluesselToString()
								+ " gel\u00f6scht an Stelle " + erg);
					}
				}

				// bei falscher Eingabe in das Textfeld oder gar keiner Eingabe
				// wird die Number Format Exception gefangen
			} catch (NumberFormatException nfe) {

				label2.setText("Nur Schl\u00fcssel zwischen 0 und 9999!");
				schluesselEingabe.setText("");
			}

			// Die gegebenenfalls veraenderte Tabelle wird gezeichnet
			try {

				tabelle.repaint();

			} catch (NullPointerException npe) {

				label2.setText("Erst mit 'neu' eine Tabelle erstellen!");

			}

		} // Ende Button LOESCHEN
	}

	public void itemStateChanged(ItemEvent e) {
    	System.out.println("Step: StateChanged: " + e);
    	
    	//verlaesst gegebenenfalls den Interaktiven-Modus
	    interaktiv = false;
	    erstelle = false;
    	

        	
    
    	    feldAnzahl = Integer.valueOf(tabellenGroesse.getSelectedItem()).intValue();
    	    System.out.println("Step: getfeldanzahl: " + feldAnzahl);
    	    methode = sondierMethode.getSelectedIndex() + 1;
    	    tabelle = new HashTabelle(feldAnzahl);
    	    // nicht notwendig, da sowieso neuer Vektor - ich bin sooooooo bloed
    	    //	tabelle.setzeNichtInteraktiverModus();
    	  	
    	  
    	    //Center wird gegebenenfalls freigeraeumt
    	    //this.remove(this.getComponentAt(tabelle.getX(), tabelle.getY()));
    	    //tabelle.removeAll();
    	    //tabelle.g.clearRect(0, 0, 524, 520);
    	    

    	    //die Tabelle wird in Center des Layout gesetzt
    	    this.add("Center", tabelle);
    	    tabelle.addMouseListener(this);

    	    //mindestens eine dieser Methoden ist notwendig, um das
    	    //Layout zu realisieren
    	    this.doLayout();
    	    this.validate();
    	    
    	
    	    //das Textfeld fuer die Schluesseleingabe wird freigegeben
    	    schluesselEingabe.setText("");
    	    schluesselEingabe.setEditable(true);

    	    //label1, also die Ueberschrift wird geschrieben
    	    if ((sondierMethode.getSelectedItem()).equals("linear, p=2")) {
    		label1.setText("Tabelle mit " +feldAnzahl +" Spalten und lineares Sondieren mit p=2");
    	    }
    		if ((sondierMethode.getSelectedItem()).equals("linear, p=1")) {
    		label1.setText("Tabelle mit " +feldAnzahl +" Spalten und lineares Sondieren mit p=1");
    		}
    		if ((sondierMethode.getSelectedItem()).equals("quadratisch")) {
    		label1.setText("Tabelle mit " +feldAnzahl +" Spalten und quadratisches Sondieren");
    		}

    		// label1.setText("Tabelle mit " +feldAnzahl +" Spalten und " +sondierMethode.getSelectedItem() +"es Sondieren");
    	    //}

    	    //die Aufforderung fuer eine Schluesseleingabe wird in label2 geschrieben
    	    label2.setText("Gib einen Schl\u00fcssel zwischen 0 und 9999 ein");
    	    
        	
    	
    
        	System.out.println("Step: TabelleVoll: " + tabelle);
        	tabelle.repaint();

    		
    		
    		
    		
    		
    	
    	
    	
    	
         // wir tun so, als ob "neu" geklickt worden waere


    }



    private void remove(Rectangle bounds) {
		// TODO Auto-generated method stub
		
	}

	//Methoden fuer den Interaktiven-Modus**************************************
    //der MouseListener
    /**
     * Bei klick in die bestehende Tabelle wird in den interaktiven Modus
     * umgeschaltet.<br>
     * Es k&ouml;nnen die Felder in der Reihenfolge markiert werden und durch
     * Bet&auml;tigung des entsprechenden Buttons kontrolliert werden.
     */
    public void mousePressed(MouseEvent me) {

	int mx = me.getX();
	int my = me.getY();

	//wenn nichts geklickt ist der Wert -3
	int angeklicktesFeld = -3;

	//stellt fest, ob der interaktive Modus gewuenscht wird, falls der interaktive Modus erlaubt ist
	if (HTML_Interaktiv) {

	    if (interaktiv == false) {

		interaktiv = tabelle.kontrolliereModus(mx, my);

	    }//Ende if(interaktiv == false)

	} //Ende if (HTML_Interaktiv)

	if (interaktiv == true) {

	    try {

		String key = schluesselEingabe.getText();
		int schluessel = Integer.valueOf(key).intValue();

		//bei falscher Zahleneingabe
		if (schluessel<0 || schluessel>=10000) {
		    interaktiv = false;
		    erstelle = false;
		    schluesselEingabe.setEditable(true);
		    tabelle.setzeNichtInteraktiverModus();
		    label2.setText("Bitte auch im interaktiven Modus einen g\u00fcltigen Schl\u00fcssel eingeben!");
		    schluesselEingabe.setText("");
		}

		//bei richtiger Zahleneingabe
		if (schluessel>=0 && schluessel<10000) {

		    schluesselEingabe.setEditable(false);
		    angeklicktesFeld = tabelle.bestimmeAngeklicktesFeld(mx, my);

		    if ( 0 <= angeklicktesFeld && angeklicktesFeld < feldAnzahl) {
			if (erstelle == false) {
			    tabelle.erstelleInteraktivDatum(feldAnzahl, schluessel);
			    erstelle = true;
			} //Ende if (erstelle == false)
			tabelle.setzeMarkiert(angeklicktesFeld);

			if (tabelle.schreibeInKontrollvektor(angeklicktesFeld)) {

			    label2.setText(aktion);

			} else {

			    label2.setText("Das ist nicht m\u00f6glich!");
			    interaktiv = false;
			    erstelle = false;
			    schluesselEingabe.setEditable(true);

			    tabelle.setzeNichtInteraktiverModus();
			    tabelle.repaint();

			}

			tabelle.repaint();

		    } //Ende if (-1 <= angeklicktesFeld && angeklicktesFeld < feldAnzahl)

		    if (angeklicktesFeld == KORREKTUR) {

			boolean tmp = tabelle.korrektur();

			if (tmp == true) {

			    tabelle.repaint();

			} else {

			interaktiv = false;
			erstelle = false;
			schluesselEingabe.setEditable(true);
			label2.setText("Gib einen Schl\u00fcssel zwischen 0 und 9999 ein");
			tabelle.repaint();

			} //Ende if (tmp > 0)
		    } //Ende if (angeklicktesFeld == KORREKTUR)

		} //Ende bei richtiger Zahleneingabe
	    } //Falls kein oder kein gueltiger Schluessel in das Textfeld eingegeben wurde: NumberFormatException
	    catch (NumberFormatException nfe) {

		interaktiv = false;
		erstelle = false;
		schluesselEingabe.setEditable(true);
		tabelle.setzeNichtInteraktiverModus();
		label2.setText("Bitte auch im interaktiven Modus einen g\u00fcltigen Schl\u00fcssel eingeben!");
		schluesselEingabe.setText("");

	    }




	} //Ende if(interaktiv == true)

    } //Ende public void mousePressed(MouseEvent me)

    public void mouseClicked(MouseEvent me) {}
    public void mouseReleased(MouseEvent me) {}
    public void mouseEntered(MouseEvent me) {}
    public void mouseExited(MouseEvent me) {}

}
