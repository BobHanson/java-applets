(function(){var P$=Clazz.newPackage("interneVerkettung"),p$1={},I$=[[0,'java.awt.Color','java.awt.Rectangle','interneVerkettung.Datum','Thread','java.awt.Font']],$I$=function(i,n){return((i=(I$[i]||(I$[i]=Clazz.load(I$[0][i])))),!n&&i.$load$&&Clazz.load(i,2),i)};
/*c*/var C$=Clazz.newClass(P$, "HashTabelle", null, 'java.awt.Panel', 'Runnable');

C$.$clinit$=2;

Clazz.newMeth(C$, '$init$', function () {
this.HOEHE=40;
this.verschiebeUeberRand=-1;
this.xKorrekturfeld=400;
this.yKorrekturfeld=5;
this.breiteKorrekturfeld=100;
this.hoeheKorrekturfeld=50;
this.interaktiv=false;
this.korrekturfeld=Clazz.new_($I$(2,1).c$$I$I$I$I,[400, 5, -1, -1]);
},1);

C$.$fields$=[['Z',['interaktiv'],'I',['FELD_ANZAHL','BREITE','HOEHE','BEGINN','LAENGE','verschiebeUeberRand','xKorrekturfeld','yKorrekturfeld','breiteKorrekturfeld','hoeheKorrekturfeld'],'O',['g','java.awt.Graphics','timer','javax.swing.Timer','tabelle','interneVerkettung.Datum[]','kontrollfelder','java.awt.Rectangle[]','korrekturfeld','java.awt.Rectangle','kontrollvektor','int[]','+korrekturvektor','interaktivDatum','interneVerkettung.Datum']]
,['O',['TABELLE_BESCHRIFTUNG_FARBE','java.awt.Color','+FREI_FARBE','+BESETZT_FARBE','+GELOESCHT_FARBE','+SUCH_FARBE','+MARKIERT_FARBE','+DEFAULT_FARBE']]]

Clazz.newMeth(C$, 'c$$I', function (anzEintr) {
Clazz.super_(C$, this);
this.FELD_ANZAHL=anzEintr;
this.tabelle=Clazz.array($I$(3), [this.FELD_ANZAHL]);
this.BREITE=(Math.rint((524/this.FELD_ANZAHL|0))|0) - 2;
this.LAENGE=this.FELD_ANZAHL * (this.BREITE + 2);
this.BEGINN=262 - (Math.rint((this.LAENGE/2|0))|0);
for (var i=0; i < this.FELD_ANZAHL; i++) {
this.tabelle[i]=Clazz.new_($I$(3,1).c$$I,[this.FELD_ANZAHL]);
}
this.verschiebeUeberRand=this.BEGINN + ((this.FELD_ANZAHL) * (this.BREITE + 2));
this.kontrollfelder=Clazz.array($I$(2), [this.FELD_ANZAHL]);
for (var i=0; i < this.FELD_ANZAHL; i++) {
this.kontrollfelder[i]=Clazz.new_([this.BEGINN + (i * (this.BREITE + 2)), 130, this.BREITE, 40],$I$(2,1).c$$I$I$I$I);
}
this.kontrollvektor=Clazz.array(Integer.TYPE, [this.FELD_ANZAHL]);
this.korrekturvektor=Clazz.array(Integer.TYPE, [this.FELD_ANZAHL]);
for (var i=0; i < this.FELD_ANZAHL; i++) {
this.kontrollvektor[i]=-3;
this.korrekturvektor[i]=-3;
}
}, 1);

Clazz.newMeth(C$, 'sondierMethode$interneVerkettung_Datum$I$I$I', function (datum, methode, vorgang, tempo) {
switch (methode) {
case 1:
return p$1.sondierMethodeEins$interneVerkettung_Datum$I$I$java_awt_Graphics.apply(this, [datum, vorgang, tempo, this.g]);
case 2:
return p$1.sondierMethodeZwei$interneVerkettung_Datum$I$I$java_awt_Graphics.apply(this, [datum, vorgang, tempo, this.g]);
case 3:
return p$1.sondierMethodeDrei$interneVerkettung_Datum$I$I$java_awt_Graphics.apply(this, [datum, vorgang, tempo, this.g]);
}
return "OHA";
});

Clazz.newMeth(C$, 'sondierMethodeEins$interneVerkettung_Datum$I$I$java_awt_Graphics', function (datum, vorgang, tempo, g) {
var tmp=datum.leseSollIndex$();
var count=0;
p$1.zeichneSoll$java_awt_Graphics$interneVerkettung_Datum$I$I$java_awt_Graphics.apply(this, [g, datum, vorgang, tempo, g]);
if (vorgang == 1) {
while (this.tabelle[tmp].leseSchluessel$() > -1 && count < this.FELD_ANZAHL - 1 ){
var verschiebeUmFelder=1;
p$1.zeichneVerschieben$java_awt_Graphics$interneVerkettung_Datum$I$I$I$I$java_awt_Graphics.apply(this, [g, datum, tmp, verschiebeUmFelder, vorgang, tempo, g]);
tmp=(tmp + 1) % this.FELD_ANZAHL;
count++;
}
} else {
while ((this.tabelle[tmp].leseSollIndex$() != -1 && this.tabelle[tmp].leseSchluessel$() != datum.leseSchluessel$() ) && count < this.FELD_ANZAHL - 1 ){
var verschiebeUmFelder=1;
p$1.zeichneVerschieben$java_awt_Graphics$interneVerkettung_Datum$I$I$I$I$java_awt_Graphics.apply(this, [g, datum, tmp, verschiebeUmFelder, vorgang, tempo, g]);
tmp=(tmp + 1) % this.FELD_ANZAHL;
count++;
}
}try {
$I$(4).sleep$J(500);
} catch (e) {
if (Clazz.exceptionOf(e,"InterruptedException")){
e.printStackTrace$();
} else {
throw e;
}
}
switch (vorgang) {
case 1:
{
if ((this.tabelle[tmp].leseSchluessel$() == -1 || this.tabelle[tmp].leseSchluessel$() == -2 ) && count < this.FELD_ANZAHL ) {
this.tabelle[tmp]=datum;
p$1.zeichneIst$java_awt_Graphics$interneVerkettung_Datum$I$I$I$java_awt_Graphics.apply(this, [g, datum, tmp, vorgang, tempo, g]);
return Integer.toString$I(tmp);
}};break;
case 2:
{
if (this.tabelle[tmp].leseSollIndex$() == datum.leseSollIndex$() && this.tabelle[tmp].leseSchluessel$() == datum.leseSchluessel$() ) {
return Integer.toString$I(tmp);
}};break;
case 3:
{
if (this.tabelle[tmp].leseSollIndex$() == datum.leseSollIndex$() && this.tabelle[tmp].leseSchluessel$() == datum.leseSchluessel$() ) {
p$1.zeichneIst$java_awt_Graphics$interneVerkettung_Datum$I$I$I$java_awt_Graphics.apply(this, [g, datum, tmp, vorgang, tempo, g]);
this.tabelle[tmp].setzeGeloescht$();
return Integer.toString$I(tmp);
}};break;
}
return "Fehler!";
}, p$1);

Clazz.newMeth(C$, 'sondierMethodeZwei$interneVerkettung_Datum$I$I$java_awt_Graphics', function (datum, vorgang, tempo, g) {
var tmp=datum.leseSollIndex$();
var count=0;
p$1.zeichneSoll$java_awt_Graphics$interneVerkettung_Datum$I$I$java_awt_Graphics.apply(this, [g, datum, vorgang, tempo, g]);
if (vorgang == 1) {
while (this.tabelle[tmp].leseSchluessel$() > -1 && count < this.FELD_ANZAHL - 1 ){
count++;
var verschiebeUmFelder=(datum.leseSollIndex$() + (count * 2)) - (datum.leseSollIndex$() + ((count - 1) * 2));
p$1.zeichneVerschieben$java_awt_Graphics$interneVerkettung_Datum$I$I$I$I$java_awt_Graphics.apply(this, [g, datum, tmp, verschiebeUmFelder, vorgang, tempo, g]);
tmp=(datum.leseSollIndex$() + (count * 2)) % this.FELD_ANZAHL;
}
} else {
while (this.tabelle[tmp].leseSollIndex$() != -1 && this.tabelle[tmp].leseSchluessel$() != datum.leseSchluessel$()  && count < this.FELD_ANZAHL - 1 ){
count++;
var verschiebeUmFelder=(datum.leseSollIndex$() + (count * 2)) - (datum.leseSollIndex$() + ((count - 1) * 2));
p$1.zeichneVerschieben$java_awt_Graphics$interneVerkettung_Datum$I$I$I$I$java_awt_Graphics.apply(this, [g, datum, tmp, verschiebeUmFelder, vorgang, tempo, g]);
tmp=(datum.leseSollIndex$() + (count * 2)) % this.FELD_ANZAHL;
}
}try {
$I$(4).sleep$J(500);
} catch (ie) {
if (Clazz.exceptionOf(ie,"InterruptedException")){
} else {
throw ie;
}
}
switch (vorgang) {
case 1:
{
if ((this.tabelle[tmp].leseSchluessel$() == -1 || this.tabelle[tmp].leseSchluessel$() == -2 ) && count < this.FELD_ANZAHL ) {
this.tabelle[tmp]=datum;
p$1.zeichneIst$java_awt_Graphics$interneVerkettung_Datum$I$I$I$java_awt_Graphics.apply(this, [g, datum, tmp, vorgang, tempo, g]);
return Integer.toString$I(tmp);
}};break;
case 2:
{
if (this.tabelle[tmp].leseSollIndex$() == datum.leseSollIndex$() && this.tabelle[tmp].leseSchluessel$() == datum.leseSchluessel$() ) {
return Integer.toString$I(tmp);
}};break;
case 3:
{
if (this.tabelle[tmp].leseSollIndex$() == datum.leseSollIndex$() && this.tabelle[tmp].leseSchluessel$() == datum.leseSchluessel$() ) {
p$1.zeichneIst$java_awt_Graphics$interneVerkettung_Datum$I$I$I$java_awt_Graphics.apply(this, [g, datum, tmp, vorgang, tempo, g]);
this.tabelle[tmp].setzeGeloescht$();
return Integer.toString$I(tmp);
}};break;
}
return "Fehler!";
}, p$1);

Clazz.newMeth(C$, 'sondierMethodeDrei$interneVerkettung_Datum$I$I$java_awt_Graphics', function (datum, vorgang, tempo, g) {
var tmp=datum.leseSollIndex$();
var count=0;
p$1.zeichneSoll$java_awt_Graphics$interneVerkettung_Datum$I$I$java_awt_Graphics.apply(this, [g, datum, vorgang, tempo, g]);
if (vorgang == 1) {
while (this.tabelle[tmp].leseSchluessel$() > -1 && count < (((this.FELD_ANZAHL + 1)/2|0) - 1) ){
count++;
var verschiebeUmFelder=(datum.leseSollIndex$() + ((Math.pow(count, 2)|0))) - (datum.leseSollIndex$() + ((Math.pow((count - 1), 2)|0)));
p$1.zeichneVerschieben$java_awt_Graphics$interneVerkettung_Datum$I$I$I$I$java_awt_Graphics.apply(this, [g, datum, tmp, verschiebeUmFelder, vorgang, tempo, g]);
tmp=(datum.leseSollIndex$() + ((Math.pow(count, 2)|0))) % this.FELD_ANZAHL;
}
} else {
while ((this.tabelle[tmp].leseSollIndex$() != -1 && this.tabelle[tmp].leseSchluessel$() != datum.leseSchluessel$() ) && count < (((this.FELD_ANZAHL + 1)/2|0) - 1) ){
count++;
var verschiebeUmFelder=(datum.leseSollIndex$() + ((Math.pow(count, 2)|0))) - (datum.leseSollIndex$() + ((Math.pow((count - 1), 2)|0)));
p$1.zeichneVerschieben$java_awt_Graphics$interneVerkettung_Datum$I$I$I$I$java_awt_Graphics.apply(this, [g, datum, tmp, verschiebeUmFelder, vorgang, tempo, g]);
tmp=(datum.leseSollIndex$() + ((Math.pow(count, 2)|0))) % this.FELD_ANZAHL;
}
}try {
$I$(4).sleep$J(500);
} catch (ie) {
if (Clazz.exceptionOf(ie,"InterruptedException")){
} else {
throw ie;
}
}
switch (vorgang) {
case 1:
{
if ((this.tabelle[tmp].leseSchluessel$() == -1 || this.tabelle[tmp].leseSchluessel$() == -2 ) && count < (((this.FELD_ANZAHL + 1)/2|0)) ) {
this.tabelle[tmp]=datum;
p$1.zeichneIst$java_awt_Graphics$interneVerkettung_Datum$I$I$I$java_awt_Graphics.apply(this, [g, datum, tmp, vorgang, tempo, g]);
return Integer.toString$I(tmp);
}};break;
case 2:
{
if (this.tabelle[tmp].leseSollIndex$() == datum.leseSollIndex$() && this.tabelle[tmp].leseSchluessel$() == datum.leseSchluessel$() ) {
return Integer.toString$I(tmp);
}};break;
case 3:
{
if (this.tabelle[tmp].leseSollIndex$() == datum.leseSollIndex$() && this.tabelle[tmp].leseSchluessel$() == datum.leseSchluessel$() ) {
p$1.zeichneIst$java_awt_Graphics$interneVerkettung_Datum$I$I$I$java_awt_Graphics.apply(this, [g, datum, tmp, vorgang, tempo, g]);
this.tabelle[tmp].setzeGeloescht$();
return Integer.toString$I(tmp);
}};break;
}
return "Fehler!";
}, p$1);

Clazz.newMeth(C$, 'run$', function () {
});

Clazz.newMeth(C$, 'zeichneSoll$java_awt_Graphics$interneVerkettung_Datum$I$I$java_awt_Graphics', function (gg, datum, vorgang, tempo, g) {
System.out.println$O(g);
System.out.println$O(gg);
var arbeitsFarbe;
var img=this.createImage$I$I(520, 128);
g.clearRect$I$I$I$I(0, 0, 520, 128);
switch (vorgang) {
case 1:
arbeitsFarbe=C$.BESETZT_FARBE;
break;
case 2:
arbeitsFarbe=C$.SUCH_FARBE;
break;
case 3:
arbeitsFarbe=C$.GELOESCHT_FARBE;
break;
default:
arbeitsFarbe=C$.DEFAULT_FARBE;
}
for (var j=0; j < ((datum.leseSollIndex$() * (this.BREITE + 2))); j=j + 2) {
g.clearRect$I$I$I$I(0, 50, 520, 40);
g.setColor$java_awt_Color(arbeitsFarbe);
g.fillRect$I$I$I$I(this.BEGINN + j, 50, this.BREITE, 40);
g.setColor$java_awt_Color($I$(1).black);
g.drawString$S$I$I(datum.schluesselToString$(), this.BEGINN + (((this.BREITE/2|0)) - 16) + j , 65);
g.setColor$java_awt_Color(C$.TABELLE_BESCHRIFTUNG_FARBE);
g.drawString$S$I$I(datum.sollIndexToString$(), this.BEGINN + (((this.BREITE/2|0)) - 16) + j , 85);
try {
$I$(4).sleep$J(tempo);
} catch (ie) {
if (Clazz.exceptionOf(ie,"InterruptedException")){
} else {
throw ie;
}
}
gg.drawImage$java_awt_Image$I$I$java_awt_image_ImageObserver(img, 0, -2, this);
}
g.clearRect$I$I$I$I(0, 50, 520, 40);
for (var j=0; j < 40; j=j + 2) {
if (j >= 37) {
j=39;
}g.clearRect$I$I$I$I(this.BEGINN + (datum.leseSollIndex$() * (this.BREITE + 2)), 50, this.BREITE, 39);
g.setColor$java_awt_Color(arbeitsFarbe);
g.fillRect$I$I$I$I(this.BEGINN + (datum.leseSollIndex$() * (this.BREITE + 2)), 50 + j, this.BREITE, 40);
g.setColor$java_awt_Color($I$(1).black);
g.drawString$S$I$I(datum.schluesselToString$(), this.BEGINN + (datum.leseSollIndex$() * (this.BREITE + 2)) + (((this.BREITE/2|0)) - 16) , 65 + j);
g.setColor$java_awt_Color(C$.TABELLE_BESCHRIFTUNG_FARBE);
g.drawString$S$I$I(datum.sollIndexToString$(), this.BEGINN + (datum.leseSollIndex$() * (this.BREITE + 2)) + (((this.BREITE/2|0)) - 16) , 85 + j);
try {
$I$(4).sleep$J(tempo);
} catch (ie) {
if (Clazz.exceptionOf(ie,"InterruptedException")){
} else {
throw ie;
}
}
gg.drawImage$java_awt_Image$I$I$java_awt_image_ImageObserver(img, 0, -2, this);
}
}, p$1);

Clazz.newMeth(C$, 'zeichneIst$java_awt_Graphics$interneVerkettung_Datum$I$I$I$java_awt_Graphics', function (gg, datum, ort, vorgang, tempo, g) {
var arbeitsFarbe;
var img=this.createImage$I$I(this.BREITE, 43);
switch (vorgang) {
case 1:
arbeitsFarbe=C$.BESETZT_FARBE;
break;
case 2:
arbeitsFarbe=C$.SUCH_FARBE;
break;
case 3:
arbeitsFarbe=C$.GELOESCHT_FARBE;
break;
default:
arbeitsFarbe=C$.DEFAULT_FARBE;
}
for (var j=0; j < 41; j=j + 3) {
g.clearRect$I$I$I$I(0, 0, this.BREITE, 43);
g.setColor$java_awt_Color(arbeitsFarbe);
g.fillRect$I$I$I$I(0, 3, this.BREITE, 40);
g.setColor$java_awt_Color($I$(1).black);
g.drawString$S$I$I(datum.schluesselToString$(), ((this.BREITE/2|0)) - 16, 18);
g.setColor$java_awt_Color(C$.TABELLE_BESCHRIFTUNG_FARBE);
g.drawString$S$I$I(datum.sollIndexToString$(), ((this.BREITE/2|0)) - 16, 38);
try {
$I$(4).sleep$J(tempo);
} catch (ie) {
if (Clazz.exceptionOf(ie,"InterruptedException")){
} else {
throw ie;
}
}
gg.drawImage$java_awt_Image$I$I$java_awt_image_ImageObserver(img, this.BEGINN + (ort * (this.BREITE + 2)), 83 + j, this);
}
gg.drawImage$java_awt_Image$I$I$java_awt_image_ImageObserver(img, 0, 124, this);
}, p$1);

Clazz.newMeth(C$, 'zeichneVerschieben$java_awt_Graphics$interneVerkettung_Datum$I$I$I$I$java_awt_Graphics', function (gg, datum, ort, verschiebeUmFelder, vorgang, tempo, g) {
var img=this.createImage$I$I(520, 129);
try {
$I$(4).sleep$J(500);
} catch (ie) {
if (Clazz.exceptionOf(ie,"InterruptedException")){
} else {
throw ie;
}
}
this.repaint$();
var arbeitsFarbe;
switch (vorgang) {
case 1:
arbeitsFarbe=C$.BESETZT_FARBE;
break;
case 2:
arbeitsFarbe=C$.SUCH_FARBE;
break;
case 3:
arbeitsFarbe=C$.GELOESCHT_FARBE;
break;
default:
arbeitsFarbe=C$.DEFAULT_FARBE;
}
var k=0;
for (var i=0; i < verschiebeUmFelder * (this.BREITE + 2); i=i + 3) {
g.clearRect$I$I$I$I(0, 89, 520, 40);
if (this.BEGINN + (ort * (this.BREITE + 2)) + i  < this.verschiebeUeberRand) {
g.setColor$java_awt_Color(arbeitsFarbe);
g.fillRect$I$I$I$I(this.BEGINN + (ort * (this.BREITE + 2)) + i , 89, this.BREITE, 40);
g.setColor$java_awt_Color($I$(1).black);
g.drawString$S$I$I(datum.schluesselToString$(), this.BEGINN + (ort * (this.BREITE + 2)) + ((this.BREITE/2|0))  - 16 + i, 104);
g.setColor$java_awt_Color(C$.TABELLE_BESCHRIFTUNG_FARBE);
g.drawString$S$I$I(datum.sollIndexToString$(), this.BEGINN + (ort * (this.BREITE + 2)) + ((this.BREITE/2|0))  - 16 + i, 124);
}if (this.BEGINN + (ort * (this.BREITE + 2)) + i  >= this.verschiebeUeberRand - (this.BREITE + 2)) {
k=k + 3;
if (k > 20 && (k + 3) % this.verschiebeUeberRand < 10 ) {
k=0;
}g.setColor$java_awt_Color(arbeitsFarbe);
g.fillRect$I$I$I$I(this.BEGINN - (this.BREITE + 2) + k, 89, this.BREITE, 40);
g.setColor$java_awt_Color($I$(1).black);
g.drawString$S$I$I(datum.schluesselToString$(), this.BEGINN - (this.BREITE + 2) + ((this.BREITE/2|0)) - 16 + k, 104);
g.setColor$java_awt_Color(C$.TABELLE_BESCHRIFTUNG_FARBE);
g.drawString$S$I$I(datum.sollIndexToString$(), this.BEGINN - (this.BREITE + 2) + ((this.BREITE/2|0)) - 16 + k, 124);
}try {
$I$(4).sleep$J(tempo);
} catch (ie) {
if (Clazz.exceptionOf(ie,"InterruptedException")){
} else {
throw ie;
}
}
gg.drawImage$java_awt_Image$I$I$java_awt_image_ImageObserver(img, 0, -3, this);
}
g.clearRect$I$I$I$I(0, 89, 520, 40);
g.setColor$java_awt_Color(arbeitsFarbe);
g.fillRect$I$I$I$I(this.BEGINN + (((ort + verschiebeUmFelder) % this.FELD_ANZAHL) * (this.BREITE + 2)), 89, this.BREITE, 40);
g.setColor$java_awt_Color($I$(1).black);
g.drawString$S$I$I(datum.schluesselToString$(), this.BEGINN + (((ort + verschiebeUmFelder) % this.FELD_ANZAHL) * (this.BREITE + 2)) + ((this.BREITE/2|0))  - 16, 104);
g.setColor$java_awt_Color(C$.TABELLE_BESCHRIFTUNG_FARBE);
g.drawString$S$I$I(datum.sollIndexToString$(), this.BEGINN + (((ort + verschiebeUmFelder) % this.FELD_ANZAHL) * (this.BREITE + 2)) + ((this.BREITE/2|0))  - 16, 124);
gg.drawImage$java_awt_Image$I$I$java_awt_image_ImageObserver(img, 0, -3, this);
}, p$1);

Clazz.newMeth(C$, 'paint$java_awt_Graphics', function (g) {
if (this.g == null ) {
this.g=g;
}if (this.interaktiv == false ) {
g.setColor$java_awt_Color($I$(1).white);
g.fillRect$I$I$I$I(0, 0, 545, 350);
g.setColor$java_awt_Color($I$(1).black);
g.setColor$java_awt_Color($I$(1).black);
g.fillRect$I$I$I$I(this.BEGINN - 1, 129, this.LAENGE + 1, 42);
for (var i=0; i < this.FELD_ANZAHL; i++) {
switch (this.tabelle[i].leseSchluessel$()) {
case -2:
g.setColor$java_awt_Color(C$.GELOESCHT_FARBE);
break;
case -1:
g.setColor$java_awt_Color(C$.FREI_FARBE);
break;
default:
g.setColor$java_awt_Color(C$.BESETZT_FARBE);
break;
}
g.fillRect$I$I$I$I(this.BEGINN + (i * (this.BREITE + 2)), 130, this.BREITE, 40);
g.setColor$java_awt_Color($I$(1).black);
g.drawString$S$I$I(this.tabelle[i].schluesselToString$(), this.BEGINN + (((this.BREITE/2|0)) - 16) + (i * (this.BREITE + 2)) , 145);
g.setColor$java_awt_Color(C$.TABELLE_BESCHRIFTUNG_FARBE);
g.drawString$S$I$I(this.tabelle[i].sollIndexToString$(), this.BEGINN + (((this.BREITE/2|0)) - 16) + (i * (this.BREITE + 2)) , 165);
g.setColor$java_awt_Color(C$.TABELLE_BESCHRIFTUNG_FARBE);
g.drawString$S$I$I(Integer.toString$I(i), this.BEGINN + (((this.BREITE/2|0)) - 16) + (i * (this.BREITE + 2)) , 195);
}
}if (this.interaktiv == true ) {
g.setColor$java_awt_Color($I$(1).black);
g.fillRect$I$I$I$I(this.BEGINN - 1, 129, this.LAENGE + 1, 42);
for (var i=0; i < this.FELD_ANZAHL; i++) {
switch (this.tabelle[i].leseSchluessel$()) {
case -2:
if (this.tabelle[i].leseMarkiert$() == false ) {
g.setColor$java_awt_Color(C$.GELOESCHT_FARBE);
} else {
g.setColor$java_awt_Color(C$.MARKIERT_FARBE);
}break;
case -1:
if (this.tabelle[i].leseMarkiert$() == false ) {
g.setColor$java_awt_Color(C$.FREI_FARBE);
} else {
g.setColor$java_awt_Color(C$.MARKIERT_FARBE);
}break;
default:
if (this.tabelle[i].leseMarkiert$() == false ) {
g.setColor$java_awt_Color(C$.BESETZT_FARBE);
} else {
g.setColor$java_awt_Color(C$.MARKIERT_FARBE);
}break;
}
g.fillRect$I$I$I$I(this.BEGINN + (i * (this.BREITE + 2)), 130, this.BREITE, 40);
g.setColor$java_awt_Color($I$(1).black);
g.drawString$S$I$I(this.tabelle[i].schluesselToString$(), this.BEGINN + (((this.BREITE/2|0)) - 16) + (i * (this.BREITE + 2)) , 145);
g.setColor$java_awt_Color(C$.TABELLE_BESCHRIFTUNG_FARBE);
g.drawString$S$I$I(this.tabelle[i].sollIndexToString$(), this.BEGINN + (((this.BREITE/2|0)) - 16) + (i * (this.BREITE + 2)) , 165);
g.setColor$java_awt_Color(C$.TABELLE_BESCHRIFTUNG_FARBE);
g.drawString$S$I$I(Integer.toString$I(i), this.BEGINN + (((this.BREITE/2|0)) - 16) + (i * (this.BREITE + 2)) , 195);
}
g.setColor$java_awt_Color($I$(1).black);
g.clearRect$I$I$I$I(399, 4, 102, 52);
g.drawRect$I$I$I$I(399, 4, 102, 52);
g.setColor$java_awt_Color(C$.FREI_FARBE);
g.fillRect$I$I$I$I(400, 5, 100, 50);
g.setColor$java_awt_Color($I$(1).black);
var schrift=g.getFont$();
g.setFont$java_awt_Font(Clazz.new_([schrift.getName$(), 0, 18],$I$(5,1).c$$S$I$I));
g.drawString$S$I$I("Korrektur", 405, 35);
g.setFont$java_awt_Font(schrift);
g.setColor$java_awt_Color(C$.BESETZT_FARBE);
g.fillRect$I$I$I$I(this.BEGINN, 50, this.BREITE, 40);
g.setColor$java_awt_Color($I$(1).black);
g.drawString$S$I$I(this.interaktivDatum.schluesselToString$(), this.BEGINN + ((this.BREITE/2|0)) - 16, 65);
g.setColor$java_awt_Color(C$.TABELLE_BESCHRIFTUNG_FARBE);
g.drawString$S$I$I(this.interaktivDatum.sollIndexToString$(), this.BEGINN + ((this.BREITE/2|0)) - 16, 85);
}});

Clazz.newMeth(C$, 'kontrolliereModus$I$I', function (mx, my) {
var i=0;
try {
while (this.interaktiv == false ){
this.interaktiv=this.kontrollfelder[i].contains$I$I(mx, my);
i++;
this.korrekturfeld.setBounds$I$I$I$I(400, 5, 100, 50);
}
} catch (aioobe) {
if (Clazz.exceptionOf(aioobe,"ArrayIndexOutOfBoundsException")){
return false;
} else {
throw aioobe;
}
}
return this.interaktiv;
});

Clazz.newMeth(C$, 'setzeNichtInteraktiverModus$', function () {
this.interaktiv=false;
for (var i=0; i < this.FELD_ANZAHL; i++) {
this.tabelle[i].setzeNichtMarkiert$();
}
for (var i=0; i < this.FELD_ANZAHL; i++) {
this.kontrollvektor[i]=-3;
this.korrekturvektor[i]=-3;
}
});

Clazz.newMeth(C$, 'bestimmeAngeklicktesFeld$I$I', function (mx, my) {
if (this.korrekturfeld.contains$I$I(mx, my)) {
return -1;
}var marke=false;
var i=-1;
try {
while (marke == false ){
i++;
marke=this.kontrollfelder[i].contains$I$I(mx, my);
}
return i;
} catch (aioobe) {
if (Clazz.exceptionOf(aioobe,"ArrayIndexOutOfBoundsException")){
return -3;
} else {
throw aioobe;
}
}
});

Clazz.newMeth(C$, 'setzeMarkiert$I', function (angeklicktesFeld) {
this.tabelle[angeklicktesFeld].setzeMarkiert$();
});

Clazz.newMeth(C$, 'schreibeInKontrollvektor$I', function (pos) {
var i=-1;
try {
do {
i++;
} while (this.kontrollvektor[i] != -3);
} catch (aioobe) {
if (Clazz.exceptionOf(aioobe,"ArrayIndexOutOfBoundsException")){
return false;
} else {
throw aioobe;
}
}
this.kontrollvektor[i]=pos;
return true;
});

Clazz.newMeth(C$, 'erstelleInteraktivDatum$I$I', function (feldAnzahl, schluessel) {
this.interaktivDatum=Clazz.new_($I$(3,1).c$$I$I,[feldAnzahl, schluessel]);
});

Clazz.newMeth(C$, 'kontrolliereInteraktiv$interneVerkettung_Datum$I$I', function (interDatum, methode, vorgang) {
switch (methode) {
case 1:
return p$1.kontrolliereMethodeEins$interneVerkettung_Datum$I.apply(this, [interDatum, vorgang]);
case 2:
return p$1.kontrolliereMethodeZwei$interneVerkettung_Datum$I.apply(this, [interDatum, vorgang]);
case 3:
return p$1.kontrolliereMethodeDrei$interneVerkettung_Datum$I.apply(this, [interDatum, vorgang]);
}
return false;
});

Clazz.newMeth(C$, 'korrektur$', function () {
var i=0;
try {
while (this.kontrollvektor[i] != -3){
i++;
}
} catch (aioobe) {
if (Clazz.exceptionOf(aioobe,"ArrayIndexOutOfBoundsException")){
} else {
throw aioobe;
}
}
if (i != 1) {
this.tabelle[this.kontrollvektor[i - 1]].setzeNichtMarkiert$();
this.kontrollvektor[i - 1]=-3;
return true;
} else {
this.setzeNichtInteraktiverModus$();
return false;
}});

Clazz.newMeth(C$, 'kontrolliereMethodeEins$interneVerkettung_Datum$I', function (interDatum, vorgang) {
var tmp=interDatum.leseSollIndex$();
var count=0;
if (vorgang == 1) {
while (this.tabelle[tmp].leseSchluessel$() > -1 && count < this.FELD_ANZAHL ){
this.korrekturvektor[count]=tmp;
tmp=(tmp + 1) % this.FELD_ANZAHL;
count++;
}
if ((this.tabelle[tmp].leseSchluessel$() == -1 || this.tabelle[tmp].leseSchluessel$() == -2 ) && (count < this.FELD_ANZAHL) ) {
this.korrekturvektor[count]=tmp;
}return p$1.bestimmeErgebnis.apply(this, []);
} else {
while ((this.tabelle[tmp].leseSollIndex$() != -1 && this.tabelle[tmp].leseSchluessel$() != interDatum.leseSchluessel$() ) && count < this.FELD_ANZAHL ){
this.korrekturvektor[count]=tmp;
tmp=(tmp + 1) % this.FELD_ANZAHL;
count++;
}
if (count < this.FELD_ANZAHL) {
this.korrekturvektor[count]=tmp;
}return p$1.bestimmeErgebnis.apply(this, []);
}}, p$1);

Clazz.newMeth(C$, 'kontrolliereMethodeZwei$interneVerkettung_Datum$I', function (interDatum, vorgang) {
var tmp=interDatum.leseSollIndex$();
var count=0;
if (vorgang == 1) {
while (this.tabelle[tmp].leseSchluessel$() > -1 && count < this.FELD_ANZAHL ){
this.korrekturvektor[count]=tmp;
count++;
tmp=(interDatum.leseSollIndex$() + (count * 2)) % this.FELD_ANZAHL;
}
if ((this.tabelle[tmp].leseSchluessel$() == -1 || this.tabelle[tmp].leseSchluessel$() == -2 ) && (count < this.FELD_ANZAHL) ) {
this.korrekturvektor[count]=tmp;
}return p$1.bestimmeErgebnis.apply(this, []);
} else {
while ((this.tabelle[tmp].leseSollIndex$() != -1 && this.tabelle[tmp].leseSchluessel$() != interDatum.leseSchluessel$() ) && count < this.FELD_ANZAHL ){
this.korrekturvektor[count]=tmp;
count++;
tmp=(interDatum.leseSollIndex$() + (count * 2)) % this.FELD_ANZAHL;
}
if (count < this.FELD_ANZAHL) {
this.korrekturvektor[count]=tmp;
}return p$1.bestimmeErgebnis.apply(this, []);
}}, p$1);

Clazz.newMeth(C$, 'kontrolliereMethodeDrei$interneVerkettung_Datum$I', function (interDatum, vorgang) {
var tmp=interDatum.leseSollIndex$();
var count=0;
if (vorgang == 1) {
while (this.tabelle[tmp].leseSchluessel$() > -1 && count < (Math.floor((this.FELD_ANZAHL/2|0)))  ){
this.korrekturvektor[count]=tmp;
count++;
tmp=(interDatum.leseSollIndex$() + ((Math.pow(count, 2)|0))) % this.FELD_ANZAHL;
}
if ((this.tabelle[tmp].leseSchluessel$() == -1 || this.tabelle[tmp].leseSchluessel$() == -2 ) && (count < (Math.floor((this.FELD_ANZAHL/2|0))) ) ) {
this.korrekturvektor[count]=tmp;
}return p$1.bestimmeErgebnis.apply(this, []);
} else {
while ((this.tabelle[tmp].leseSollIndex$() != -1 && this.tabelle[tmp].leseSchluessel$() != interDatum.leseSchluessel$() ) && count < (Math.floor((this.FELD_ANZAHL/2|0)))  ){
this.korrekturvektor[count]=tmp;
count++;
tmp=(interDatum.leseSollIndex$() + ((Math.pow(count, 2)|0))) % this.FELD_ANZAHL;
}
if (count < (Math.floor((this.FELD_ANZAHL/2|0))) ) {
this.korrekturvektor[count]=tmp;
}return p$1.bestimmeErgebnis.apply(this, []);
}}, p$1);

Clazz.newMeth(C$, 'bestimmeErgebnis', function () {
var test=true;
for (var i=0; i < this.FELD_ANZAHL; i++) {
test=(this.kontrollvektor[i] == this.korrekturvektor[i]) && test ;
}
return test;
}, p$1);

C$.$static$=function(){C$.$static$=0;
C$.TABELLE_BESCHRIFTUNG_FARBE=Clazz.new_($I$(1,1).c$$I$I$I,[160, 10, 160]);
C$.FREI_FARBE=Clazz.new_($I$(1,1).c$$I$I$I,[239, 239, 239]);
C$.BESETZT_FARBE=Clazz.new_($I$(1,1).c$$I$I$I,[187, 201, 221]);
C$.GELOESCHT_FARBE=Clazz.new_($I$(1,1).c$$I$I$I,[255, 255, 200]);
C$.SUCH_FARBE=Clazz.new_($I$(1,1).c$$I$I$I,[200, 225, 255]);
C$.MARKIERT_FARBE=Clazz.new_($I$(1,1).c$$I$I$I,[200, 239, 200]);
C$.DEFAULT_FARBE=$I$(1).blue;
};

Clazz.newMeth(C$);
})();
;Clazz.setTVer('3.2.9-v1');//Created 2021-01-30 17:44:24 Java2ScriptVisitor version 3.2.9-v1 net.sf.j2s.core.jar version 3.2.9-v1
