(function(){var P$=Clazz.newPackage("Kollisionen"),p$1={},I$=[[0,'java.awt.Color']],$I$=function(i,n){return((i=(I$[i]||(I$[i]=Clazz.load(I$[0][i])))),!n&&i.$load$&&Clazz.load(i,2),i)};
/*c*/var C$=Clazz.newClass(P$, "Tabelle", null, 'java.awt.Panel');

C$.$clinit$=2;

Clazz.newMeth(C$, '$init$', function () {
this.felder=1;
this.gessumme=0;
},1);

C$.$fields$=[['I',['felder','gessumme'],'O',['balken','int[]','keymatrix','int[][]','g','java.awt.Graphics']]
,['I',['feldbreite'],'O',['leistenfarbe','java.awt.Color','+saeulenfarbe']]]

Clazz.newMeth(C$, 'c$', function () {
Clazz.super_(C$, this);
}, 1);

Clazz.newMeth(C$, 'paint$java_awt_Graphics', function (g) {
if (this.g == null ) {
this.g=g;
}var anzahl=this.felder - 1;
var aufbausaeule=Clazz.array(Integer.TYPE, [this.felder]);
this.zeichneTabelle$java_awt_Graphics(g);
g.drawRect$I$I$I$I(10, 10, 482, 250);
g.drawLine$I$I$I$I(10, 30, 492, 30);
g.drawLine$I$I$I$I(10, 235, 492, 235);
this.maleFelder$I$java_awt_Graphics(anzahl, g);
if (this.balken != null ) {
for (var i=0; i <= anzahl; i++) {
aufbausaeule[i]=0;
for (var j=1; j <= this.balken[i]; j++) {
aufbausaeule[i]+=1;
this.maleAufbauSaeule$IA$I$I$java_awt_Graphics(aufbausaeule, i, this.keymatrix[i][j], g);
}
}
}aufbausaeule=null;
});

Clazz.newMeth(C$, 'zeichneTabelle$java_awt_Graphics', function (g) {
g.setColor$java_awt_Color($I$(1).white);
g.fillRect$I$I$I$I(0, 0, 545, 350);
g.setColor$java_awt_Color($I$(1).black);
g.drawLine$I$I$I$I(40, 10, 40, 260);
g.setColor$java_awt_Color(C$.leistenfarbe);
g.fillRect$I$I$I$I(11, 11, 29, 20);
g.setColor$java_awt_Color($I$(1).blue);
g.drawString$S$I$I("Feld", 12, 25);
g.setColor$java_awt_Color(C$.leistenfarbe);
g.fillRect$I$I$I$I(11, 236, 29, 24);
g.setColor$java_awt_Color($I$(1).red);
g.drawString$S$I$I("Sum", 12, 255);
});

Clazz.newMeth(C$, 'maleFelder$I$java_awt_Graphics', function (anzahl, g) {
var eintrag;
var tabstueck;
var haelfte;
this.zeichneTabelle$java_awt_Graphics(g);
this.felder=anzahl + 1;
C$.feldbreite=Math.round((482/this.felder|0));
haelfte=Math.round((C$.feldbreite/2|0));
g.setColor$java_awt_Color(C$.leistenfarbe);
g.fillRect$I$I$I$I(41, 11, C$.feldbreite, 20);
g.fillRect$I$I$I$I(41, 236, C$.feldbreite, 24);
g.setColor$java_awt_Color($I$(1).black);
g.drawString$S$I$I("0", haelfte + 38, 25);
tabstueck=C$.feldbreite;
for (var i=1; i <= anzahl; i++) {
eintrag="" + i;
g.setColor$java_awt_Color(C$.leistenfarbe);
g.fillRect$I$I$I$I(40 + tabstueck, 11, C$.feldbreite, 20);
g.fillRect$I$I$I$I(40 + tabstueck, 236, C$.feldbreite, 24);
g.setColor$java_awt_Color($I$(1).black);
g.drawLine$I$I$I$I(40 + tabstueck, 10, 40 + tabstueck, 260);
if (i >= 19) g.drawString$S$I$I(eintrag, 43 + tabstueck, 25);
 else {
if (i > 9) g.drawString$S$I$I(eintrag, haelfte + 32 + tabstueck , 25);
 else g.drawString$S$I$I(eintrag, haelfte + 38 + tabstueck , 25);
}tabstueck+=C$.feldbreite;
}
g.drawRect$I$I$I$I(10, 10, 30 + tabstueck, 250);
g.drawLine$I$I$I$I(10, 30, 40 + tabstueck, 30);
g.drawLine$I$I$I$I(10, 235, 40 + tabstueck, 235);
});

Clazz.newMeth(C$, 'maleSaeule$IA$I$I$java_awt_Graphics', function (hoehe, feld, dskey, g) {
this.balken=hoehe;
if (this.keymatrix == null ) this.keymatrix=Clazz.array(Integer.TYPE, [this.felder, 20]);
this.keymatrix[feld][hoehe[feld]]=dskey;
System.out.println$S("keymatrix[" + feld + "][" + hoehe[feld] + "] = " + dskey );
this.maleAufbauSaeule$IA$I$I$java_awt_Graphics(hoehe, feld, dskey, g);
});

Clazz.newMeth(C$, 'maleAufbauSaeule$IA$I$I$java_awt_Graphics', function (hoehe, feld, dskey, g) {
var eintrag;
var xpoint;
var ypoint;
var width;
var height;
eintrag="" + dskey;
if (dskey < 1000) eintrag="0" + eintrag;
if (dskey < 100) eintrag="0" + eintrag;
if (dskey < 10) eintrag="0" + eintrag;
xpoint=41 + (C$.feldbreite * (feld));
ypoint=235 - (hoehe[feld] * 15);
width=C$.feldbreite - 1;
height=15;
if (hoehe[feld] < 14) {
g.drawRect$I$I$I$I(xpoint, ypoint, width, height);
g.setColor$java_awt_Color(C$.saeulenfarbe);
g.fillRect$I$I$I$I(xpoint, ypoint, width, height);
}if (hoehe[feld] == 14) {
ypoint=31;
height=8;
g.drawRect$I$I$I$I(xpoint, ypoint, width, height);
g.setColor$java_awt_Color(C$.saeulenfarbe);
g.fillRect$I$I$I$I(xpoint, ypoint, width, height);
}g.setColor$java_awt_Color($I$(1).black);
if (hoehe[feld] < 14) {
if (((this.felder) >= 13) && ((this.felder) <= 15) ) g.drawString$S$I$I(eintrag, xpoint, ypoint + 12);
 else {
if (((this.felder) >= 9) && ((this.felder) <= 15) ) g.drawString$S$I$I(eintrag, xpoint + 2, ypoint + 12);
if ((this.felder) < 9) g.drawString$S$I$I(eintrag, xpoint, ypoint + 12);
}}var tabstueck=0;
var haelfte=Math.round((C$.feldbreite/2|0));
var viertel=Math.round((haelfte/2|0));
for (var i=0; i <= this.felder - 1; i++) {
eintrag="" + hoehe[i];
if (hoehe[i] >= 19) {
g.clearRect$I$I$I$I(43 + tabstueck, 238, haelfte + viertel, 21);
g.setColor$java_awt_Color(C$.leistenfarbe);
g.fillRect$I$I$I$I(41 + tabstueck, 236, C$.feldbreite - 1, 24);
g.setColor$java_awt_Color($I$(1).black);
g.drawString$S$I$I(eintrag, haelfte + 32 + tabstueck , 255);
} else {
g.clearRect$I$I$I$I(43 + tabstueck, 238, haelfte + viertel, 21);
g.setColor$java_awt_Color(C$.leistenfarbe);
g.fillRect$I$I$I$I(41 + tabstueck, 236, C$.feldbreite - 1, 24);
g.setColor$java_awt_Color($I$(1).black);
}if (hoehe[i] > 9) g.drawString$S$I$I(eintrag, haelfte + 32 + tabstueck , 255);
 else g.drawString$S$I$I(eintrag, haelfte + 38 + tabstueck , 255);
tabstueck+=C$.feldbreite;
}
this.gessumme=p$1.addiereSummen$IA.apply(this, [hoehe]);
g.clearRect$I$I$I$I(11, 238, 28, 21);
g.setColor$java_awt_Color(C$.leistenfarbe);
g.fillRect$I$I$I$I(11, 236, 29, 24);
g.setColor$java_awt_Color($I$(1).red);
if (this.gessumme < 100) g.drawString$S$I$I("" + this.gessumme, 16, 255);
 else g.drawString$S$I$I("" + this.gessumme, 12, 255);
});

Clazz.newMeth(C$, 'addiereSummen$IA', function (feldsumme) {
var summe=0;
for (var i=0; i <= this.felder - 1; i++) summe+=feldsumme[i];

return summe;
}, p$1);

C$.$static$=function(){C$.$static$=0;
C$.leistenfarbe=Clazz.new_($I$(1,1).c$$I$I$I,[220, 220, 220]);
C$.saeulenfarbe=Clazz.new_($I$(1,1).c$$I$I$I,[187, 204, 221]);
};
})();
;Clazz.setTVer('3.2.9-v1');//Created 2021-01-30 18:24:00 Java2ScriptVisitor version 3.2.9-v1 net.sf.j2s.core.jar version 3.2.9-v1
