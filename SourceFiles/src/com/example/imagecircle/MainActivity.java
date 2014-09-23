package com.example.imagecircle;

import java.util.ArrayList;
import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

/**
 * Author: Logan Elliott
 * Program: Image Circle Calculator and diagram
 * Date: 6/11/2013
 * Version: 1.5
 * 
 * Additonal Info:
 *  4" = 101.6mm
 *  5" = 127mm
 *  8" = 203.2mm
 *  10" = 254mm
 **/

public class MainActivity extends Activity{
	private final int maxArraySize = 10;
	//private final Color[] colorArray= {Color.blue,Color.green,Color.magenta,Color.orange,Color.red,Color.pink,Color.yellow,Color.cyan,Color.black,Color.darkGray};
	private final String[] filmSizes = {"135","4.5x6cm","6x6cm","6x7cm","4x5\"","8x10\""};
	private final DoublePoint[] filmDimensions = {new DoublePoint(24,36),new DoublePoint(45,60),new DoublePoint(60,60),new DoublePoint(60,70),new DoublePoint(101.6,127),new DoublePoint(203.2,254)};
	private final double[] minImageCircles = {43.2666,75,84.8528,92.1954,162.6394,325.2787}; //divide by 2?
	private double filmWidth = 101.6;
	private double filmHeight = 127;
	private double imageCircle;
	private int constantSizeIncrease = 3;
	private int locationInFilmSizeArray = 4;
	private boolean drawAllImageCircles = false;
	private boolean isdrawAllFilmTypesChecked =false;
	//private Color backGroundColor = Color.white;
	private ArrayList<String> multipleImageCircles = new ArrayList<String>(0);
	//private ArrayList<String> multipleFilmSizes = new ArrayList<String>(0); add adjustable filmsizes later?
	EditText imageCircleInput = (EditText)findViewById(R.id.imageCircleInput);
	TextView rise;
	TextView shift;
	
	 @Override  
    public void onCreate(Bundle savedInstanceState)   
    {
	 super.onCreate(savedInstanceState);  
	 setContentView(R.layout.activity_main);
	 System.out.println("test");
	 imageCircleInput.setOnEditorActionListener((OnEditorActionListener) this);
	
    }
	 
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	 
	 public void setOnEditorActionListener(){
		 imageCircleInput.setOnEditorActionListener(new OnEditorActionListener() {
			    @Override
			    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
			        if (actionId == EditorInfo.IME_ACTION_DONE) {
			        	imageCircle = Integer.parseInt((imageCircleInput.getText()).toString());
			        	movementCalculator();
			        }
			        return false;
			    }
		 });
	 }
	 
	 public void setUpEditTextListener2(){
		 imageCircleInput = (EditText)findViewById(R.id.imageCircleInput);
		 imageCircleInput.addTextChangedListener(new TextWatcher(){
		        public void afterTextChanged(Editable s) {
		        	movementCalculator();
		        }
		        public void beforeTextChanged(CharSequence s, int start, int count, int after){}
		        public void onTextChanged(CharSequence s, int start, int before, int count){}
		    }); 
	 }
	 
	private void movementCalculator() {
		if(!isThereAImageCircleInput() || isdrawAllFilmTypesChecked){
			return;
		}
		DoublePoint moveableAmount = calculateMoveableAmount();
		if (moveableAmount.getX() != -1 && moveableAmount.getY() != -1) {
			// double moveableAmount = calculateMoveableAmount();
			shift
					.setText("Shift: "
							+ (Math.round(moveableAmount.getX() * 100.0) / 100.0)
							+ "mm");
			rise
					.setText("Rise: "
							+ (Math.round(moveableAmount.getY() * 100.0) / 100.0)
							+ "mm");
		} else {
	//		double increaseImageCircle = calculateImageCirleIncrease();
	
	
	//		toSmallImageCircleDisplay
	//				.setText("Your lens has insufficient coverage!"
	//						+ "\n"
	//						+ "You should find a lens with a image circle of atleast "
	//						+ Math.abs((Math
	//								.round(minImageCircles	[locationInFilmSizeArray] * 100.0) / 100.0))
	//						+ "mm ");
		}
	
	}

	private DoublePoint calculateMoveableAmount() {
		// x^2+y^2=r^2 equation for a circle
		double shift = 0;
		double riseFall = 0;
	
		if (imageCircle >= minImageCircles[locationInFilmSizeArray]) {
			// calculating shift
			// y=filmHeight/2
			// x is the x coordinate, it is in line with the top of the
			// filmHeight
			double x = Math.sqrt(Math.pow((imageCircle / 2), 2)
					- Math.pow((filmHeight / 2), 2));
			shift = (x - (filmWidth / 2));
	
			// calculating rise/fall
			// x=filmHeight/2
			// y is the y coordinate, it is in line with the side of the
			// filmWidth
			double y = Math.sqrt(Math.pow((imageCircle / 2), 2)
					- Math.pow((filmWidth / 2), 2));
			riseFall = (y - (filmHeight / 2));
			return new DoublePoint(shift, riseFall);
		} else {
			return new DoublePoint(-1, -1);
		}
	}

	private boolean isThereAImageCircleInput() {
		//in case you change the type of format without having entered a image circle
		return (imageCircle != 0.0);
	}
}