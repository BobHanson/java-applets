package old.Kollisionen;
import java.util.*;
import java.lang.*;

public class HashTabelle extends Hashtable {

    private static Hashtable numbers;
    private static int hsize;
    private static Integer wert;
    private static int dszahl;
	private final int maxhoehe = 19;
    int[] saeule;

    // Konstruktoren 

    HashTabelle() {
	// Bei Bedarf wird auf Klasse java.util.Hashtable zugegriffen. 
	// numbers = new Hashtable(1);
	hsize = 1;
	saeule = new int[hsize];
    }    

    HashTabelle(int felder) 
	{
	    // Bei Bedarf wird auf Klasse java.util.Hashtable zugegriffen.
	    // numbers = new Hashtable(felder);
	    hsize = felder;
	    saeule = new int[hsize];
	}
    

    public int HashEintrag(int datensatz, int methindex) {//ermittelt Hashwert

	int hashwert;
	Integer ds = new Integer(datensatz);

	// Zuweisung des Hashwertes durch die Methode HashCodex(); 
	if (methindex==1)
	    hashwert = HashCode1(ds).intValue();
	else if (methindex==2)
	    hashwert = HashCode2(ds).intValue();
	else if (methindex==3)
	    hashwert = HashCode3(ds).intValue();
	else if (methindex==4)
	    hashwert = HashCode4(ds).intValue();
	else if (methindex==5)
	    hashwert = HashCode5(ds).intValue();
	else hashwert = HashCode5(ds).intValue();
	System.out.println("Hashwert: "+hashwert);

	// falls gewünscht, muß number im Konstruktor inst. worden sein.
	// this.numbers.put(ds, new Integer(hashwert));

	return hashwert;	
    } // HashEintrag(int datensatz, int methindex) 


    public void NeueSaeule(int feldeintrag) { // Säulenindex wird aufdatiert
	if (this.saeule[feldeintrag] < maxhoehe)
	    this.saeule[feldeintrag] += 1;
    }

    private static Integer HashCode1(Object obj) { // 1. Divisionsmethode
	
	// Diese Methode ist die 1. Divisionsmethode;
	// Hashwert wird mit letztem Zeichen berechnet (eher schlecht).
	
	int summe;
	
	dszahl = ((Integer)obj).intValue();
	
	summe = (dszahl%10);
	    
	    wert = new Integer(summe % hsize);
	return wert;
    } // Ende HashCode1(Object obj); 
    


    private static Integer HashCode2(Object obj) { // 2. Divisionsmethode
	
	// Diese Methode ist die 2. Divisionsmethode;
	// Sie summiert das erste und letzte Zeichen des Int.-Wertes, bevor   
	// per Division der Hashwert gebildet wird (etwas besser).

	int summe;
	int zwwert;

	dszahl = ((Integer)obj).intValue();
	
	summe = 0;
	zwwert = 0;
	    if (dszahl > 999) { // 1000er Ziffer wird evtl. ermittelt
		zwwert = (dszahl-(dszahl%1000))/1000;
	    }
	    else // Das erste Zeichen ist sonst eine "0"
		zwwert = 0;
	    summe += zwwert;
	    // 1er Ziffer wird ermittelt 
	    summe += (dszahl%10);
	    
	    wert = new Integer(summe % hsize);
	return wert;
    } // Ende HashCode2(Object obj); 


    private static Integer HashCode3(Object obj) { // 3. Divisionsmethode

	// Diese Methode ist die 3. Divisionsmethode;
	// Die Quersumme des Integer-Datensatzes wird gebildet und 
	// anschliessend per Division der Hashwert ermittelt (noch besser).

	int summe;
	
	dszahl = ((Integer)obj).intValue();
	
	// In der folgenden do-while-Schleife wird der Int.-wert pro Iteration 
	// um eine 10er Potenz reduziert, bevor die entspr. Ziffer aufsummiert 
	// wird. Ultrastabil!
	
	summe = 0;
	do {
	    int zwwert = 0;
	    if (dszahl > 999) { // 1000er Ziffer wird ermittelt
		zwwert = (dszahl-(dszahl%1000))/1000;
		dszahl = dszahl%1000;
	    }
	    else if (dszahl > 99) { // 100er Ziffer wird ermittelt
		zwwert = (dszahl-(dszahl%100))/100;
		dszahl = dszahl%100;
	    }
	    else if (dszahl > 9) { // 10er Ziffer wird ermittelt
		zwwert = (dszahl-(dszahl%10))/10;
		dszahl = dszahl%10;
	    }
	    else if (dszahl > 0) { // 1er Ziffer wird ermittelt
		zwwert = dszahl;
		dszahl = 0;
	    }
	    summe = summe + zwwert;
	
	} while (dszahl!=0);
	
	wert = new Integer(summe % hsize);
	return wert;
    } // Ende HashCode3(Object obj); 


    private static Integer HashCode4(Object obj) { // 4. Divisionsmethode

	// Diese Methode berechnet (eingabezahl mod(hsize)); dies ist 
	// natürlich nur mit Integer-Eingaben möglich. Daher nicht klassisch.
	// I.A. noch besser als Methode 3 aufgrund der Streuung.

	int rest;
	dszahl = ((Integer)obj).intValue();	
	if (hsize <= dszahl) {
	    rest = (dszahl % hsize);
	}
	else rest = dszahl;
	wert = new Integer(rest);
	return wert;
    } // Ende HashCode4(Object obj); 


    private static Integer HashCode5(Object obj) { // 5. Methode (Multipl.)
	// Diese Methode ist eine Multiplikationsmethode (beste Streuung) 
	// Integer-Datensatz wird mit geeignetet Faktor (--> teta) multipl.,
	// anschl. die Nachkommastelle betrachtet => aus (0,1) anschl. wie 
	// bei "random" auf die Anzahl der Felder verteilt!
	     
	double rest;
	double teta;
	double kleinezahl;
	int zwwert;
	
	dszahl = ((Integer)obj).intValue();
	teta = (Math.sqrt(5.0)-1)/2;
	
	zwwert = ((int)(dszahl * teta));
	kleinezahl = Math.abs(zwwert-(dszahl*teta));
	rest = Math.ceil((hsize * kleinezahl)-1);
	
	wert = new Integer((int)rest);
	return wert;
    } // Ende HashCode5(Object obj); 
    
    
} // Ende class HashTabelle extends Hashtable







