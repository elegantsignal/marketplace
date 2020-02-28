var object = {
	a : "string",
	arr : [ {}, {} ],
	innerObject : {}
};

var str = JSON.stringify(object);
console.log(str);

var similarObject = JSON.parse(str);
console.log(similarObject);

console.log(object == similarObject);
console.log(object === similarObject);