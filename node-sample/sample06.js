function printToConsole1() {
	console.log("named function executed");
}

var o = {};

o.printToConsole = printToConsole1;
o.printToConsole();

var printToConsole2 = function() {
	console.log("anonymous function executed");

};

o.printToConsole = printToConsole2;
o.printToConsole();

console.log("foo" + + "bar");