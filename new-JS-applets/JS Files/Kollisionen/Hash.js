(function(){var P$=Clazz.newPackage("Kollisionen"),p$1={},I$=[[0,'java.awt.Color','java.awt.Label','java.awt.Font','java.awt.Button','java.awt.Choice','java.awt.TextField','java.awt.Panel','java.awt.GridLayout','Kollisionen.Tabelle','java.awt.BorderLayout','Kollisionen.HashTabelle']],$I$=function(i,n){return((i=(I$[i]||(I$[i]=Clazz.load(I$[0][i])))),!n&&i.$load$&&Clazz.load(i,2),i)};
/*c*/var C$=Clazz.newClass(P$, "Hash", null, 'java.applet.Applet', ['java.awt.event.ActionListener', 'java.awt.event.ItemListener', 'java.awt.event.TextListener']);

C$.$clinit$=2;

Clazz.newMeth(C$, '$init$', function () {
this.key=-1;
this.count=-1;
},1);

C$.$fields$=[['I',['feldanzahl','methode','schluesseltyp','zuweisung','key','count'],'S',['dss','dsa'],'O',['schluesselLabel','java.awt.Label','+anzahlLabel','+groesseLabel','+methodeLabel','groesseChoice','java.awt.Choice','+methodeChoice','+schluesselChoice','anzahlTextField','java.awt.TextField','neuButton','java.awt.Button','+einfuegeButton','nord','java.awt.Panel','+oben','+sued','fazit','java.awt.Label','+leereslabel','unten','Kollisionen.Tabelle','Hashing','Kollisionen.HashTabelle']]]

Clazz.newMeth(C$, ['init$','init'], function () {
this.setBackground$java_awt_Color($I$(1).white);
this.groesseLabel=Clazz.new_($I$(2,1).c$$S,["Gr\u00f6\u00dfe: "]);
this.groesseLabel.setBackground$java_awt_Color($I$(1).lightGray);
this.groesseLabel.setAlignment$I(2);
this.methodeLabel=Clazz.new_($I$(2,1).c$$S,["Hash-Funktion: "]);
this.methodeLabel.setBackground$java_awt_Color($I$(1).lightGray);
this.methodeLabel.setAlignment$I(2);
this.schluesselLabel=Clazz.new_($I$(2,1).c$$S,["Schl\u00fcsseltyp: "]);
this.schluesselLabel.setBackground$java_awt_Color($I$(1).lightGray);
this.schluesselLabel.setAlignment$I(2);
this.anzahlLabel=Clazz.new_($I$(2,1).c$$S,["Anzahl: "]);
this.anzahlLabel.setBackground$java_awt_Color($I$(1).lightGray);
this.anzahlLabel.setAlignment$I(2);
this.fazit=Clazz.new_($I$(2,1).c$$S,[""]);
this.fazit.setBackground$java_awt_Color($I$(1).white);
this.fazit.setAlignment$I(1);
this.fazit.setFont$java_awt_Font(Clazz.new_($I$(3,1).c$$S$I$I,["SansSerif", 0, 12]));
this.leereslabel=Clazz.new_($I$(2,1).c$$S,[""]);
this.leereslabel.setBackground$java_awt_Color($I$(1).white);
this.leereslabel.setAlignment$I(1);
this.leereslabel.setFont$java_awt_Font(Clazz.new_($I$(3,1).c$$S$I$I,["SansSerif", 1, 12]));
var buttonColor=Clazz.new_($I$(1,1).c$$I$I$I,[220, 145, 160]);
this.neuButton=Clazz.new_($I$(4,1).c$$S,["neu"]);
this.neuButton.setBackground$java_awt_Color(buttonColor);
this.neuButton.addActionListener$java_awt_event_ActionListener(this);
this.einfuegeButton=Clazz.new_($I$(4,1).c$$S,["einf\u00fcgen"]);
this.einfuegeButton.setBackground$java_awt_Color(buttonColor);
this.einfuegeButton.addActionListener$java_awt_event_ActionListener(this);
this.groesseChoice=Clazz.new_($I$(5,1));
this.methodeChoice=Clazz.new_($I$(5,1));
p$1.NeueTabelle.apply(this, []);
p$1.NeueHashWahl.apply(this, []);
this.groesseChoice.addItemListener$java_awt_event_ItemListener(this);
this.groesseChoice.setBackground$java_awt_Color($I$(1).white);
this.methodeChoice.setBackground$java_awt_Color($I$(1).white);
this.methodeChoice.addItemListener$java_awt_event_ItemListener(this);
this.schluesselChoice=Clazz.new_($I$(5,1));
this.schluesselChoice.removeAll$();
this.schluesselChoice.addItem$S("Zahlen");
this.schluesselChoice.addItem$S("Geb.-Daten");
this.schluesselChoice.addItemListener$java_awt_event_ItemListener(this);
this.anzahlTextField=Clazz.new_($I$(6,1).c$$I,[3]);
this.anzahlTextField.addTextListener$java_awt_event_TextListener(this);
this.nord=Clazz.new_($I$(7,1));
this.nord.setLayout$java_awt_LayoutManager(Clazz.new_($I$(8,1).c$$I$I,[1, 5]));
this.nord.setBackground$java_awt_Color($I$(1).white);
this.nord.add$java_awt_Component(this.groesseLabel);
this.nord.add$java_awt_Component(this.groesseChoice);
this.nord.add$java_awt_Component(this.methodeLabel);
this.nord.add$java_awt_Component(this.methodeChoice);
this.nord.add$java_awt_Component(this.neuButton);
this.oben=Clazz.new_($I$(7,1));
this.oben.setBackground$java_awt_Color($I$(1).white);
this.oben.setLayout$java_awt_LayoutManager(Clazz.new_($I$(8,1).c$$I$I,[3, 1]));
this.oben.add$java_awt_Component(this.nord);
this.oben.add$java_awt_Component(this.leereslabel);
this.oben.add$java_awt_Component(this.fazit);
this.sued=Clazz.new_($I$(7,1));
this.sued.setLayout$java_awt_LayoutManager(Clazz.new_($I$(8,1).c$$I$I,[1, 5]));
this.sued.setBackground$java_awt_Color($I$(1).white);
this.sued.add$java_awt_Component(this.schluesselLabel);
this.sued.add$java_awt_Component(this.schluesselChoice);
this.sued.add$java_awt_Component(this.anzahlLabel);
this.sued.add$java_awt_Component(this.anzahlTextField);
this.sued.add$java_awt_Component(this.einfuegeButton);
this.unten=Clazz.new_($I$(9,1));
this.setLayout$java_awt_LayoutManager(Clazz.new_($I$(10,1)));
this.add$S$java_awt_Component("Center", this.unten);
this.add$S$java_awt_Component("North", this.oben);
this.add$S$java_awt_Component("South", this.sued);
System.out.println$S("LS | Panel Initialisiert: ok");
});

Clazz.newMeth(C$, ['actionPerformed$java_awt_event_ActionEvent','actionPerformed'], function (ae) {
this.fazit.setText$S("");
var cmd=ae.getActionCommand$();
System.out.println$S("LS ActionCommand: " + cmd);
if (cmd.equals$O("einf\u00fcgen")) {
System.out.println$S("einfuegen");
if ((this.feldanzahl != 0) && (this.methode != 0) && (this.count != -1)  ) {
if (this.Hashing == null ) {
this.Hashing=Clazz.new_($I$(11,1).c$$I,[this.feldanzahl + 1]);
System.out.println$S("Hashing = null");
}if (this.schluesseltyp == 0) {
if (this.count == 1) this.fazit.setText$S(this.count + " zuf�lliger Datensatz aus: [0 - 9999] wird eingef\u00fcgt");
 else this.fazit.setText$S(this.count + " zuf�llige Datens�tze aus: [0 - 9999] werden eingef\u00fcgt");
p$1.BerechneZufall1.apply(this, []);
p$1.LabelAngabe.apply(this, []);
System.out.println$S("schluesseltyp" + this.schluesseltyp);
}if (this.schluesseltyp == 1) {
if (this.count == 1) this.fazit.setText$S(this.count + " zuf�lliges Geburtsdatum [TTMM] wird eingef\u00fcgt");
 else this.fazit.setText$S(this.count + " zuf�llige Geburtsdaten [TTMM] werden eingef\u00fcgt");
p$1.BerechneZufall2.apply(this, []);
p$1.LabelAngabe.apply(this, []);
}} else {
if ((this.feldanzahl == 0) || (this.methode == 0) ) this.fazit.setText$S("Zuerst Gr\u00f6\u00dfe und Funktion ausw\u00e4hlen");
 else this.fazit.setText$S("Bitte eine Anzahl eingeben");
}}if (cmd.equals$O("neu")) {
this.Hashing=null;
this.unten.balken=null;
this.unten.keymatrix=null;
this.unten.maleFelder$I$java_awt_Graphics(this.feldanzahl, this.unten.g);
this.unten.gessumme=0;
p$1.LabelAngabe.apply(this, []);
}});

Clazz.newMeth(C$, ['itemStateChanged$java_awt_event_ItemEvent','itemStateChanged'], function (e) {
System.out.println$S("LS: " + e);
var altefeldanz;
var alterkey;
var alterbalken;
var altematrix;
var firstchoice=false;
this.fazit.setText$S("");
this.methode=this.methodeChoice.getSelectedIndex$();
this.feldanzahl=this.groesseChoice.getSelectedIndex$();
System.out.println$S("LS Feldanzahl: " + this.feldanzahl);
this.schluesseltyp=this.schluesselChoice.getSelectedIndex$();
if (this.methode == 0) this.leereslabel.setText$S("");
 else p$1.LabelAngabe.apply(this, []);
if ((this.feldanzahl != 0) && (this.Hashing == null ) ) {
this.unten.maleFelder$I$java_awt_Graphics(this.feldanzahl, this.unten.g);
firstchoice=true;
}if ((this.feldanzahl != 0) && (this.Hashing != null ) ) {
altefeldanz=this.unten.felder;
this.Hashing=null;
this.Hashing=Clazz.new_($I$(11,1).c$$I,[this.feldanzahl + 1]);
this.unten.maleFelder$I$java_awt_Graphics(this.feldanzahl, this.unten.g);
firstchoice=true;
if ((this.key != -1) && (this.methode != 0) ) {
alterbalken=this.unten.balken;
altematrix=this.unten.keymatrix;
alterkey=this.key;
this.unten.keymatrix=null;
p$1.LabelAngabe.apply(this, []);
for (var i=0; i <= altefeldanz - 1; i++) {
for (var j=1; j <= alterbalken[i]; j++) {
this.key=altematrix[i][j];
p$1.DatenTransfer.apply(this, []);
System.out.println$S("Matrix = " + i + "," + j );
}
}
this.key=alterkey;
}}if ((this.feldanzahl == 0) && (this.methode == 0) ) this.fazit.setText$S("Zuerst Gr\u00fc\u00dfe und Funktion ausw\ufffdhlen!");
if ((this.feldanzahl != 0) && (this.methode == 0) ) this.fazit.setText$S("Die Funktion noch ausw\ufffdhlen!");
if ((this.feldanzahl == 0) && (this.methode != 0) ) this.fazit.setText$S("Die Gr\u00fc\u00dfe noch ausw\ufffdhlen!");
if ((this.methode != 0) && (firstchoice) && (this.key == -1)  ) {
this.fazit.setText$S("");
p$1.LabelAngabe.apply(this, []);
}if ((this.methode == 0) && (firstchoice) ) this.fazit.setText$S("Bitte eine Hash-Funktion ausw\ufffdhlen!");
});

Clazz.newMeth(C$, ['textValueChanged$java_awt_event_TextEvent','textValueChanged'], function (e) {
this.dsa=this.anzahlTextField.getText$();
try {
this.count=Integer.valueOf$S(this.dsa).intValue$();
if ((this.count < 1) || (this.count > 100) ) {
this.count=-1;
throw Clazz.new_(Clazz.load('Kollisionen.RangeException').c$$I,[this.count]);
}} catch (e$$) {
if (Clazz.exceptionOf(e$$,"NumberFormatException")){
var nfe = e$$;
{
if (!this.dsa.equals$O("")) this.fazit.setText$S("Bitte ein Zahl zwischen 1 und 100 eingeben");
}
} else if (Clazz.exceptionOf(e$$,"Kollisionen.RangeException")){
var re = e$$;
{
this.fazit.setText$S("Anzahl muss zwischen 1 und 100 liegen");
}
} else {
throw e$$;
}
}
});

Clazz.newMeth(C$, 'BerechneZufall1', function () {
for (var i=1; i <= this.count; i++) {
this.key=((Math.ceil(9999.0 * Math.random())|0));
p$1.DatenTransfer.apply(this, []);
}
}, p$1);

Clazz.newMeth(C$, 'BerechneZufall2', function () {
for (var i=1; i <= this.count; i++) {
this.key=p$1.ErmittleTTMM.apply(this, []);
p$1.DatenTransfer.apply(this, []);
}
}, p$1);

Clazz.newMeth(C$, 'DatenTransfer', function () {
this.zuweisung=this.Hashing.HashEintrag$I$I(this.key, this.methode);
this.Hashing.NeueSaeule$I(this.zuweisung);
if (this.Hashing.saeule[this.zuweisung] > 14) this.fazit.setText$S("Nicht mehr graphisch darstellbar - intern geht es    bis 19 weiter!");
this.unten.maleSaeule$IA$I$I$java_awt_Graphics(this.Hashing.saeule, this.zuweisung, this.key, this.unten.g);
}, p$1);

Clazz.newMeth(C$, 'ErmittleTTMM', function () {
var zahl1;
var zahl2;
var zahl;
zahl1=(1 + (Math.floor(31.0 * Math.random())|0));
if (zahl1 == 28) {
zahl2=(1 + (Math.floor(12.0 * Math.random())|0));
} else if (zahl1 == 31) {
do {
zahl2=(1 + (Math.floor(12.0 * Math.random())|0));
} while (((zahl2 == 2) || (zahl2 == 4) ) || ((zahl2 == 6) || (zahl2 == 9) ) || ((zahl2 == 11))  );
} else if ((zahl1 >= 29) && (zahl1 <= 30) ) {
do {
zahl2=(1 + (Math.floor(12.0 * Math.random())|0));
} while (zahl2 == 2);
} else {
zahl2=(1 + (Math.floor(12.0 * Math.random())|0));
}zahl=(zahl1 * 100) + zahl2;
return zahl;
}, p$1);

Clazz.newMeth(C$, 'LabelAngabe', function () {
if (this.methode == 1) this.leereslabel.setText$S("Funktion 1:  s1s2s3s4  ---> s4 mod " + (this.feldanzahl + 1) + "" );
if (this.methode == 2) this.leereslabel.setText$S("Funktion 2: s1s2s3s4  ---> s1+s4 mod " + (this.feldanzahl + 1) + "" );
if (this.methode == 3) this.leereslabel.setText$S("Funktion 3: s1s2s3s4  ---> s1+s2+s3+s4 mod " + (this.feldanzahl + 1) + "" );
if (this.methode == 4) this.leereslabel.setText$S("Funktion 4: s1s2s3s4  ---> (s1s2s3s4) mod " + (this.feldanzahl + 1) + "" );
if (this.methode == 5) this.leereslabel.setText$S("Funktion 5: s1s2s3s4  ---> [ ( (t*(s1s2s3s4)) mod 1 )*(" + (this.feldanzahl + 1) + ")], t irrational" );
}, p$1);

Clazz.newMeth(C$, 'NeueTabelle', function () {
this.groesseChoice.removeAll$();
this.groesseChoice.addItem$S("  ");
for (var i=2; i <= 24; i++) {
this.groesseChoice.addItem$S(i + " Felder");
}
}, p$1);

Clazz.newMeth(C$, 'NeueHashWahl', function () {
this.methodeChoice.removeAll$();
this.methodeChoice.addItem$S("   ");
this.methodeChoice.addItem$S("Funktion 1");
this.methodeChoice.addItem$S("Funktion 2");
this.methodeChoice.addItem$S("Funktion 3");
this.methodeChoice.addItem$S("Funktion 4");
this.methodeChoice.addItem$S("Funktion 5");
}, p$1);

Clazz.newMeth(C$);
})();
;Clazz.setTVer('3.2.9-v1');//Created 2021-01-29 14:31:01 Java2ScriptVisitor version 3.2.9-v1 net.sf.j2s.core.jar version 3.2.9-v1
