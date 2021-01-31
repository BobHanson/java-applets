package Kollisionen;

//
// Diese Klasse Tabelle.java zeichnet die Struktur der benutzerdefinierten
// Hash-Tabelle (Tabellenfelder werden gezeichnet, Tabelle beschriftet).
//
import java.awt.*;
import java.lang.*;

// +++++++++++++++++++++++++++++++++++++
// ++  class Tabelle extends Panel    ++
// +++++++++++++++++++++++++++++++++++++

class Tabelle extends Panel {

	int felder = 1;
	int gessumme = 0;
	int[] balken;
	int[][] keymatrix;
	private static final int maxhoehe = 20;
	private static int feldbreite;
	private static Color leistenfarbe = new Color(220, 220, 220);
	private static Color saeulenfarbe = new Color(187, 204, 221);
//	public Graphics g;

	// Standardkonstruktorf
	Tabelle() {
	}

	// Methode paint() wird �berlagert

	public void paint(Graphics g) {
//		if (this.g == null) {
//			this.g = g;
//		}
		int anzahl = felder - 1;
		int[] aufbausaeule = new int[felder];
// BH: unnecessary?
//		zeichneTabelle(g);
//		// Malt den Rest der Tabelle.
//		g.drawRect(10, 10, 482, 250);
//		g.drawLine(10, 30, 492, 30);
//		g.drawLine(10, 235, 492, 235);

		// Malt die einzelnen Datenfelder inkl. Ziffern.
		maleFelder(anzahl, g);

		// �bermalt, falls vorhanden, S�ulen mit Schrift.
		if (balken != null) {
			for (int i = 0; i <= anzahl; i++) {
				aufbausaeule[i] = 0;
				// Balkenhoehe ist max. 19, da keymatrix max. j=19 sein kann!
				// Siehe Instantiierung Keymatrix (zeile 120)!
				for (int j = 1; j <= balken[i]; j++) {
					aufbausaeule[i] += 1;
					maleAufbauSaeule(aufbausaeule, i, keymatrix[i][j], g);
				}
			} // Ende for-Anweisung
		}
		aufbausaeule = null;
	} // Ende paint(Graphics g)

	// Erweiterung um eigene Methoden

	private void zeichneTabelle(Graphics g) {

		// Overpaint everything
		g.setColor(Color.white);
		g.fillRect(0, 0, 545, 350);
		g.setColor(Color.black);
		// repaint();

		// Rechteck hat Koo.: x, y, breite, hoehe (li. ob. Ecke)
		// Linie hat Koo.: x1, y1, x2, y3 (Anf.- + Endpunkt)
		// Teilst�cke werden gemalt. Rest wird in maleFelder() entspr. gemalt.
		// Tabelle wird beschriftet.
		g.drawLine(40, 10, 40, 260);
		g.setColor(leistenfarbe);
		g.fillRect(11, 11, 29, 20);
		g.setColor(Color.blue);
		g.drawString("Feld", 12, 25);
		// Fu�leiste wird komplett in "hellgrau" unterlegt.
		// Feldeintrag "Sum" wird bei Bedarf durch tats. Ges.-Summe ersetzt.
		g.setColor(leistenfarbe);
		g.fillRect(11, 236, 29, 24);
		g.setColor(Color.red);
		g.drawString("Sum", 12, 255);

	} // Ende public void zeichneTabelle()

	/**
	 * @deprecated Use {@link #maleFelder(int,Graphics)} instead
	 */
	public void maleFelder(int anzahl) {
		maleFelder(anzahl, null);
	} // Ende komplette Tabelle

	public void maleFelder(int anzahl, Graphics g0) { // malt die komplette Tabelle

		Graphics g = (g0 == null ? getGraphics() : g0.create());

		String eintrag;
		int tabstueck, haelfte;

		// Tabelle wird anhand der Daten komplett gemalt.
		zeichneTabelle(g);

		felder = anzahl + 1;
		// Ermittelt wird die Breite jedes Tabellenfeldes.
		feldbreite = Math.round(482 / felder);
		haelfte = Math.round(feldbreite / 2);
		g.setColor(leistenfarbe);
		g.fillRect(41, 11, feldbreite, 20);
		g.fillRect(41, 236, feldbreite, 24);
		g.setColor(Color.black);
		g.drawString("0", haelfte + 38, 25);
		tabstueck = feldbreite;
		// Tabelle beginnt mit "0" und endet mit feldanzahl-1
		for (int i = 1; i <= anzahl; i++) {
			eintrag = "" + i;
			g.setColor(leistenfarbe);
			g.fillRect(40 + tabstueck, 11, feldbreite, 20);
			g.fillRect(40 + tabstueck, 236, feldbreite, 24);
			g.setColor(Color.black);
			g.drawLine(40 + tabstueck, 10, 40 + tabstueck, 260);
			if (i >= 19)
				g.drawString(eintrag, 43 + tabstueck, 25);
			else {
				if (i > 9)
					g.drawString(eintrag, haelfte + 32 + tabstueck, 25);
				else
					g.drawString(eintrag, haelfte + 38 + tabstueck, 25);
			}
			tabstueck += feldbreite;
		} // ende for-Anweisung

		// Tabellenseiten werden noch gezeichet.
		g.drawRect(10, 10, 30 + tabstueck, 250);
		g.drawLine(10, 30, 40 + tabstueck, 30);
		g.drawLine(10, 235, 40 + tabstueck, 235);
		
		g.dispose();
	} // Ende komplette Tabelle

	public void maleSaeule(int[] hoehe, int feld, int dskey) {

		Graphics g = getGraphics();
		
// maleSaeule(...) malt baut bei Aufruf die komplette S�ule auf.
// Die globale Array-Variable balken bekommt nur hier die 
// Adresse von hoehe zugewiesen ( ---> f�r die paint()-Methode).

		balken = hoehe;

// Es wird eine keymatrix eingerichtet, die die DSS verwaltet.
// Keymatrix ist array aus max. 24 x 20 (S�ulenh�he) Eintr�gen.
// Falls instantiiert, wird diese um neuen DSS (dskey) aktualisiert.
// Dies dient haupts�chlich der paint-Methode, die wiederherstellt.

		if (keymatrix == null)
			keymatrix = new int[felder][maxhoehe];
		keymatrix[feld][hoehe[feld]] = dskey;
		System.out.println("keymatrix[" + feld + "][" + hoehe[feld] + "] = " + dskey);

// Nachfolgende Methode malt die S�ulen einschl. Bezifferung.

		maleAufbauSaeule(hoehe, feld, dskey, g);
		
		g.dispose();

	} // Ende maleSaeule(int[] hoehe, int feld, int dskey)

	private void maleAufbauSaeule(int[] hoehe, int feld, int dskey, Graphics g) {

// In dieser Methode werden die einzelnen S�ulen mit dskeys (DSS) 
// gemalt und f�r die paint-Methode zug�nglich gemacht, die
// die gel�schten S�ulen mit Ziffern wiederherstellt (malt).

		// Graphics g = getGraphics();
		String eintrag;
		int xpoint, ypoint, width, height;

		// F�lle den Datensatz entsprechend mit Nullen auf.

		eintrag = "" + dskey;
		if (dskey < 1000)
			eintrag = "0" + eintrag;
		if (dskey < 100)
			eintrag = "0" + eintrag;
		if (dskey < 10)
			eintrag = "0" + eintrag;

		// Die S�ulenst�ckchen werden eingezeichnet u. beschriftet.

		xpoint = 41 + (feldbreite * (feld));
		ypoint = 235 - (hoehe[feld] * 15);
		width = feldbreite - 1;
		height = 15;

		if (hoehe[feld] < 14) {
			g.drawRect(xpoint, ypoint, width, height);
			g.setColor(saeulenfarbe);
			g.fillRect(xpoint, ypoint, width, height);
		}
		if (hoehe[feld] == 14) {
			ypoint = 31;
			height = 8;
			g.drawRect(xpoint, ypoint, width, height);
			g.setColor(saeulenfarbe);
			g.fillRect(xpoint, ypoint, width, height);
		}

		// Die S�ulenk�stchen werden mit dem akt. eintrag (dskey) versehen,
		// sofern die Feldanzahl nicht groesser als 15 ist (sieht nicht aus).

		g.setColor(Color.black);
		if (hoehe[feld] < 14) {
			if (((felder) >= 13) && ((felder) <= 15))
				g.drawString(eintrag, xpoint, ypoint + 12);
			else {
				if (((felder) >= 9) && ((felder) <= 15))
					g.drawString(eintrag, xpoint + 2, ypoint + 12);
				if ((felder) < 9)
					g.drawString(eintrag, xpoint, ypoint + 12);
			}
		}

// Die Summe der jew. K�stchen wird unterhalb der Felder notiert.

		int tabstueck = 0;
		int haelfte = Math.round(feldbreite / 2);
		int viertel = Math.round(haelfte / 2);

		// Entsprechend d. Bezifferung werden die Summenk�stchen beschriftet.
		// Au�erdem wird die Tabellenfussleiste vorher grau hinterlegt.

		for (int i = 0; i <= felder - 1; i++) {
			eintrag = "" + hoehe[i];
			if (hoehe[i] >= 19) {
				g.clearRect(43 + tabstueck, 238, haelfte + viertel, 21);
				g.setColor(leistenfarbe);
				g.fillRect(41 + tabstueck, 236, feldbreite - 1, 24);
				g.setColor(Color.black);
				g.drawString(eintrag, haelfte + 32 + tabstueck, 255);
			} else {
				g.clearRect(43 + tabstueck, 238, haelfte + viertel, 21);
				g.setColor(leistenfarbe);
				g.fillRect(41 + tabstueck, 236, feldbreite - 1, 24);
				g.setColor(Color.black);
			}
			if (hoehe[i] > 9)
				g.drawString(eintrag, haelfte + 32 + tabstueck, 255);
			else
				g.drawString(eintrag, haelfte + 38 + tabstueck, 255);
			tabstueck += feldbreite;

		} // Ende for-Schleife Summen-Beschriftung

		// Gesamtsumme wird berechnet und K�stchen auf Hintergrund beschriftet.

		gessumme = addiereSummen(hoehe);
		g.clearRect(11, 238, 28, 21);
		g.setColor(leistenfarbe);
		g.fillRect(11, 236, 29, 24);
		g.setColor(Color.red);
		if (gessumme < 100)
			g.drawString("" + gessumme, 16, 255);
		else
			g.drawString("" + gessumme, 12, 255);

	} // Ende maleSaeule(int[] hoehe, int feld, int dskey)

	private int addiereSummen(int[] feldsumme) {
		int summe = 0;
		for (int i = 0; i <= felder - 1; i++)
			summe += feldsumme[i];
		return summe;
	}

} // class Tabelle extends Panel
