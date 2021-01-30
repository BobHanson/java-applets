(function(){var P$=Clazz.newPackage("Kollisionen"),I$=[];
/*c*/var C$=Clazz.newClass(P$, "HashTabelle", null, 'java.util.Hashtable');

C$.$clinit$=2;

Clazz.newMeth(C$, '$init$', function () {
this.maxhoehe=19;
},1);

C$.$fields$=[['I',['maxhoehe'],'O',['saeule','int[]']]
,['I',['hsize','dszahl'],'O',['numbers','java.util.Hashtable','wert','Integer']]]

Clazz.newMeth(C$, 'c$', function () {
Clazz.super_(C$, this);
C$.hsize=1;
this.saeule=Clazz.array(Integer.TYPE, [C$.hsize]);
}, 1);

Clazz.newMeth(C$, 'c$$I', function (felder) {
Clazz.super_(C$, this);
C$.hsize=felder;
this.saeule=Clazz.array(Integer.TYPE, [C$.hsize]);
}, 1);

Clazz.newMeth(C$, 'HashEintrag$I$I', function (datensatz, methindex) {
var hashwert;
var ds= new Integer(datensatz);
if (methindex == 1) hashwert=C$.HashCode1$O(ds).intValue$();
 else if (methindex == 2) hashwert=C$.HashCode2$O(ds).intValue$();
 else if (methindex == 3) hashwert=C$.HashCode3$O(ds).intValue$();
 else if (methindex == 4) hashwert=C$.HashCode4$O(ds).intValue$();
 else if (methindex == 5) hashwert=C$.HashCode5$O(ds).intValue$();
 else hashwert=C$.HashCode5$O(ds).intValue$();
System.out.println$S("Hashwert: " + hashwert);
return hashwert;
});

Clazz.newMeth(C$, 'NeueSaeule$I', function (feldeintrag) {
if (this.saeule[feldeintrag] < 19) this.saeule[feldeintrag]+=1;
});

Clazz.newMeth(C$, 'HashCode1$O', function (obj) {
var summe;
C$.dszahl=(obj).intValue$();
summe=(C$.dszahl % 10);
C$.wert= new Integer(summe % C$.hsize);
return C$.wert;
}, 1);

Clazz.newMeth(C$, 'HashCode2$O', function (obj) {
var summe;
var zwwert;
C$.dszahl=(obj).intValue$();
summe=0;
zwwert=0;
if (C$.dszahl > 999) {
zwwert=((C$.dszahl - (C$.dszahl % 1000))/1000|0);
} else zwwert=0;
summe+=zwwert;
summe+=(C$.dszahl % 10);
C$.wert= new Integer(summe % C$.hsize);
return C$.wert;
}, 1);

Clazz.newMeth(C$, 'HashCode3$O', function (obj) {
var summe;
C$.dszahl=(obj).intValue$();
summe=0;
do {
var zwwert=0;
if (C$.dszahl > 999) {
zwwert=((C$.dszahl - (C$.dszahl % 1000))/1000|0);
C$.dszahl=C$.dszahl % 1000;
} else if (C$.dszahl > 99) {
zwwert=((C$.dszahl - (C$.dszahl % 100))/100|0);
C$.dszahl=C$.dszahl % 100;
} else if (C$.dszahl > 9) {
zwwert=((C$.dszahl - (C$.dszahl % 10))/10|0);
C$.dszahl=C$.dszahl % 10;
} else if (C$.dszahl > 0) {
zwwert=C$.dszahl;
C$.dszahl=0;
}summe=summe + zwwert;
} while (C$.dszahl != 0);
C$.wert= new Integer(summe % C$.hsize);
return C$.wert;
}, 1);

Clazz.newMeth(C$, 'HashCode4$O', function (obj) {
var rest;
C$.dszahl=(obj).intValue$();
if (C$.hsize <= C$.dszahl) {
rest=(C$.dszahl % C$.hsize);
} else rest=C$.dszahl;
C$.wert= new Integer(rest);
return C$.wert;
}, 1);

Clazz.newMeth(C$, 'HashCode5$O', function (obj) {
var rest;
var teta;
var kleinezahl;
var zwwert;
C$.dszahl=(obj).intValue$();
teta=(Math.sqrt(5.0) - 1) / 2;
zwwert=(((C$.dszahl * teta)|0));
kleinezahl=Math.abs(zwwert - (C$.dszahl * teta));
rest=Math.ceil((C$.hsize * kleinezahl) - 1);
C$.wert= new Integer((rest|0));
return C$.wert;
}, 1);
})();
;Clazz.setTVer('3.2.9-v1');//Created 2021-01-28 11:37:28 Java2ScriptVisitor version 3.2.9-v1 net.sf.j2s.core.jar version 3.2.9-v1
