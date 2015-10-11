/**
 * Created by Logan on 3/10/2015.
 */
//Make an SVG Container
function drawCircle() {

    d3.select("svg")
        .remove();

    var svgContainer = d3.select("body").append("svg")
        .attr("width", 800)
        .attr("height", 800);


    var fourFiveWidth = 101.6;
    var fourFiveHeight = 127;

    drawFilm(svgContainer,fourFiveWidth,fourFiveHeight);

    var imageCircle = document.getElementById("input_text").value;


    drawImageCircle(svgContainer,imageCircle);

    var moveableAmount = calculateMoveableAmount(imageCircle,fourFiveWidth,fourFiveHeight);

    var div = document.getElementById('moveableAmount');

    div.innerHTML = "You can shift a maximum amount of " + moveableAmount.shift + "mm You can rise/fall a maximum amount of " + moveableAmount.riseFall + "mm";
}

function drawFilm(svgContainer,filmWidth,filmHeight){

    svgContainer.append("rect")
        .attr("x", 300 - filmWidth/2)
        .attr("y", 300 - filmHeight/2)
        .attr("width", filmWidth)
        .attr("height", filmHeight)
        .attr("stroke", "red")
        .style("fill","none");
}

function drawImageCircle(svgContainer,imageCircle){
    svgContainer.append("circle")
        .attr("cx", 300)
        .attr("cy", 300)
        .attr("r", imageCircle/2)
        .attr("stroke", "blue")
        .style("fill","none");
}

function calculateMoveableAmount(imageCircle,filmWidth,filmHeight){
        // x^2+y^2=r^2 equation for a circle
        //r = imageCircle/2
        var  shift = 0;
        var  riseFall = 0;

        //checks to see if entered image circle is above min required
        /*
         calculating shift
         y=filmHeight/2
         x is the x coordinate, it is in line with the top of the filmHeight
         */
        var x = calculateXY(imageCircle,filmHeight);
        var shift = Math.floor(x -(filmWidth / 2));

        // calculating rise/fall
        // x=filmHeight/2
        // y is the y coordinate, it is in line with the side of the
        // filmWidth
        var y = calculateXY(imageCircle,filmWidth);
        var riseFall = Math.floor(y - (filmHeight / 2));
        return { shift: shift, riseFall: riseFall};
}

function calculateXY(imageCircle,filmDimension){
    return Math.sqrt(Math.pow((imageCircle / 2), 2) - Math.pow((filmDimension / 2), 2));
}