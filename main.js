var field;


function setup()
{
	field = new Field(6, 9);
	field.clear();
	createCanvas(1600,1000);
	background(255);
	for (i in field.matrix) {
		for (j in i) {
			fill(51, 100);
			rect (j.xA, j.yA, 142, 103); 
		}
	}
}

function draw()
{

}