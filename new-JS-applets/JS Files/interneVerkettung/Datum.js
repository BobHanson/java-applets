(function(){var P$=Clazz.newPackage("interneVerkettung"),p$1={};
/*c*/var C$=Clazz.newClass(P$, "Datum");

C$.$clinit$=2;

Clazz.newMeth(C$, '$init$', function () {
this.schluessel=-1;
this.sollIndex=-1;
this.markiert=false;
},1);

C$.$fields$=[['Z',['markiert'],'I',['FELD_ANZAHL','schluessel','sollIndex']]]

Clazz.newMeth(C$, 'c$$I', function (laenge) {
;C$.$init$.apply(this);
this.schluessel=-1;
this.FELD_ANZAHL=laenge;
}, 1);

Clazz.newMeth(C$, 'c$$I$I', function (laenge, datenSchluessel) {
;C$.$init$.apply(this);
this.FELD_ANZAHL=laenge;
this.schluessel=datenSchluessel;
this.sollIndex=p$1.Hash$I.apply(this, [this.schluessel]);
}, 1);

Clazz.newMeth(C$, 'leseSchluessel$', function () {
return this.schluessel;
});

Clazz.newMeth(C$, 'schluesselToString$', function () {
var key;
if (this.schluessel >= 0 && this.schluessel < 10000 ) {
key=Integer.toString$I(this.schluessel);
} else {
key="";
}return key;
});

Clazz.newMeth(C$, 'leseSollIndex$', function () {
return this.sollIndex;
});

Clazz.newMeth(C$, 'sollIndexToString$', function () {
var sI;
if (this.schluessel >= 0 && this.schluessel < 10000 ) {
sI=Integer.toString$I(this.sollIndex);
} else {
sI="";
}return sI;
});

Clazz.newMeth(C$, 'Hash$I', function (datenSchluessel) {
return (datenSchluessel % this.FELD_ANZAHL);
}, p$1);

Clazz.newMeth(C$, 'setzeGeloescht$', function () {
this.schluessel=-2;
this.sollIndex=-2;
});

Clazz.newMeth(C$, 'leseMarkiert$', function () {
return this.markiert;
});

Clazz.newMeth(C$, 'setzeMarkiert$', function () {
this.markiert=true;
});

Clazz.newMeth(C$, 'setzeNichtMarkiert$', function () {
this.markiert=false;
});

Clazz.newMeth(C$);
})();
;Clazz.setTVer('3.2.9-v1');//Created 2021-01-28 15:22:57 Java2ScriptVisitor version 3.2.9-v1 net.sf.j2s.core.jar version 3.2.9-v1
