//import javax.swing.BorderFactory;
//import javax.swing.JButton;
//import javax.swing.JCheckBox;
//import javax.swing.JComboBox;
//import javax.swing.JPanel;
//import javax.swing.JSlider;
//import javax.swing.JTextArea;
//import javax.swing.JTextField;
//import javax.swing.border.*;
//import javax.swing.event.ChangeEvent;
//import javax.swing.event.ChangeListener;
//
//import android.text.TextWatcher;
//import android.widget.EditText;
//
//import java.awt.Color;
//import java.awt.Graphics;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.KeyEvent;
//import java.util.ArrayList;
//
///**
// * Author: Logan Elliott
// * Program: Image Circle Calculator and diagram
// * Date: 6/11/2013
// * Version: 1.5
// * 
// * Additonal Info:
// *  4" = 101.6mm
// *  5" = 127mm
// *  8" = 203.2mm
// *  10" = 254mm
// **/
//
//public class ImageCirleJPanel extends Activity {
//
//	private final int maxArraySize = 10;
//	private final Color[] colorArray= {Color.blue,Color.green,Color.magenta,Color.orange,Color.red,Color.pink,Color.yellow,Color.cyan,Color.black,Color.darkGray};
//	private final String[] filmSizes = {"135","4.5x6cm","6x6cm","6x7cm","4x5\"","8x10\""};
//	private final DoublePoint[] filmDimensions = {new DoublePoint(24,36),new DoublePoint(45,60),new DoublePoint(60,60),new DoublePoint(60,70),new DoublePoint(101.6,127),new DoublePoint(203.2,254)};
//	private final double[] minImageCircles = {43.2666,75,84.8528,92.1954,162.6394,325.2787}; //divide by 2?
//	private double filmWidth = 101.6;
//	private double filmHeight = 127;
//	private double imageCircle;
//	private int constantSizeIncrease = 3;
//	private int locationInFilmSizeArray = 4;
//	private boolean drawAllImageCircles = false;
//	private boolean isdrawAllFilmTypesChecked =false;
//	private Color backGroundColor = Color.white;
//	private ArrayList<String> multipleImageCircles = new ArrayList<String>(0);
//	//private ArrayList<String> multipleFilmSizes = new ArrayList<String>(0); add adjustable filmsizes later?
//	private JComboBox<String> imageCircleComboBox;
//	private JComboBox<String> filmSizeComboBox;
//	private JCheckBox checkBoxForDrawAllImageCircles;
//	private JCheckBox checkBoxForDrawAllFilmTypes;
//	private JTextField imageCircleInputField = new JTextField(
//			"Enter image circle size in mm here");
//	private JTextArea moveableShiftAmountDisplay = new JTextArea("");
//	private JTextArea moveableRiseFallAmountDisplay = new JTextArea("");
//	private JTextArea toSmallImageCircleDisplay = new JTextArea("");
//
//	public ImageCirleJPanel() {
//		setBackground(backGroundColor);
//		setUpToolsPanel();
//
//	}
//
//
//
//	private void movementCalculator() {
//		if(!isThereAImageCircleInput() || isdrawAllFilmTypesChecked){
//			return;
//		}
//		DoublePoint moveableAmount = calculateMoveableAmount();
//		if (moveableAmount.getX() != -1 && moveableAmount.getY() != -1) {
//			toSmallImageCircleDisplay.setText("");
//			// double moveableAmount = calculateMoveableAmount();
//			moveableShiftAmountDisplay
//					.setText("You can shift a maximum amount of: "
//							+ (Math.round(moveableAmount.getX() * 100.0) / 100.0)
//							+ "mm");
//			moveableRiseFallAmountDisplay
//					.setText("You can rise/fall a maximum amount of: "
//							+ (Math.round(moveableAmount.getY() * 100.0) / 100.0)
//							+ "mm");
//		} else {
////			double increaseImageCircle = calculateImageCirleIncrease();
//			moveableShiftAmountDisplay.setText("");
//			moveableRiseFallAmountDisplay.setText("");
//			toSmallImageCircleDisplay
//					.setText("Your lens has insufficient coverage!"
//							+ "\n"
//							+ "You should find a lens with a image circle of atleast "
//							+ Math.abs((Math
//									.round(minImageCircles[locationInFilmSizeArray] * 100.0) / 100.0))
//							+ "mm ");
//		}
//
//	}
//	
//	private DoublePoint calculateMoveableAmount() {
//		// x^2+y^2=r^2 equation for a circle
//		double shift = 0;
//		double riseFall = 0;
//
//		if (imageCircle >= minImageCircles[locationInFilmSizeArray]) {
//			// calculating shift
//			// y=filmHeight/2
//			// x is the x coordinate, it is in line with the top of the
//			// filmHeight
//			double x = Math.sqrt(Math.pow((imageCircle / 2), 2)
//					- Math.pow((filmHeight / 2), 2));
//			shift = (x - (filmWidth / 2));
//
//			// calculating rise/fall
//			// x=filmHeight/2
//			// y is the y coordinate, it is in line with the side of the
//			// filmWidth
//			double y = Math.sqrt(Math.pow((imageCircle / 2), 2)
//					- Math.pow((filmWidth / 2), 2));
//			riseFall = (y - (filmHeight / 2));
//			return new DoublePoint(shift, riseFall);
//		} else {
//			return new DoublePoint(-1, -1);
//		}
//	}
//
//	// -------------------------------------------------------
//	// Draw everything
//	// -------------------------------------------------------
//	public void paintComponent(Graphics g) {
//		super.paintComponent(g);
//		filmDrawingAmountChoser(g);
//		drawImageCircle(g);
//	}
//	
//	private void filmDrawingAmountChoser(Graphics g){
//		if(isdrawAllFilmTypesChecked){
//			drawAllFilmTypes(g);
//		} else {
//			g.setColor(Color.red);
//			drawOneFilmType(g);
//		}
//	}
//	private void drawAllFilmTypes(Graphics g){
//		double currentFilmWidth = filmWidth;
//		double currentFilmHeight = filmHeight;
//		System.out.println();
//		for(int i =filmDimensions.length-1;i>=0;i--){
//			g.setColor(colorArray[i]);
//			filmWidth = filmDimensions[i].getX();
//			filmHeight = filmDimensions[i].getY();
//			drawFilm(g,adjustingForCentre(filmWidth,filmHeight));
//		}
//		filmWidth = currentFilmWidth;
//		filmHeight = currentFilmHeight; 
//	}
//	
//	private void drawOneFilmType(Graphics g){
//		drawFilm(g,adjustingForCentre(filmWidth,filmHeight));
//	}
//	private void drawFilm(Graphics g,DoublePoint adjustedValues){
//		//drawing the film
//		g.drawRect((int) adjustedValues.getX(), (int) adjustedValues.getY(),
//		(int) (filmWidth * constantSizeIncrease),
//		(int) (filmHeight * constantSizeIncrease));
//	}
//	
//	private void drawImageCircle(Graphics g){
//		//drawing the image circles
//		DoublePoint adjustedValues;
//		if(isThereAImageCircleInput()){
//			g.setColor(Color.blue);
//			if(multipleImageCircles.size()>1 && drawAllImageCircles){
//				double currentImageCircle = imageCircle;
//				for(int i=0;i<multipleImageCircles.size();i++){
//					imageCircle = Double.parseDouble(multipleImageCircles.get(i));
//					adjustedValues = adjustingForCentre(imageCircle, imageCircle);
//				g.drawOval((int) adjustedValues.getX(), (int) adjustedValues.getY(),
//						(int) (imageCircle * constantSizeIncrease),
//						(int) (imageCircle * constantSizeIncrease));
//				}
//				imageCircle = currentImageCircle;
//				
//			} else {
//				adjustedValues = adjustingForCentre(imageCircle, imageCircle);
//				g.drawOval((int) adjustedValues.getX(), (int) adjustedValues.getY(),
//						(int) (imageCircle * constantSizeIncrease),
//						(int) (imageCircle * constantSizeIncrease));
//			}
//		}
//	}
//
//	private boolean isThereAImageCircleInput() {
//		//in case you change the type of format without having entered a image circle
//		return (imageCircle != 0.0);
//	}
//
//
//
//	private DoublePoint adjustingForCentre(double adjustedWidth,
//			double adjustedHeight) {
//		return new DoublePoint((1024 / 2) - ((adjustedWidth * constantSizeIncrease) / 2), (1024 / 2)
//				- ((adjustedHeight * constantSizeIncrease) / 2));
//
//	}
//
//	private void setUpToolsPanel() {
//		setUpTextInputField();
//		setUpTextDisplay();
//		setUpSlider();
//		setUpButtons();
//		setupImageCircleComboBox();
//		checkBoxForDrawAllFilmSizes();
//		setUpFilmSizeComboBox();
//		
//	}
//	
//	private void setUpTextInputField(){
//		imageCircleInputField.addActionListener( new ActionListener() { 
//			public void actionPerformed(ActionEvent arg0) {
//			try {
//				imageCircle = Double.parseDouble(imageCircleInputField.getText());
//				movementCalculator();
//			} catch (NumberFormatException e) {
//				imageCircleInputField.setText("Not a number try again!");
//			}
//			repaint();
//		}
//		});
//		this.add(imageCircleInputField);
//	}
//	
//	private void setUpTextDisplay(){
//		this.add(moveableShiftAmountDisplay);
//		moveableShiftAmountDisplay.setText("You can shift a maximum amount of "
//				+ 0 + "mm");
//		this.add(moveableRiseFallAmountDisplay);
//		moveableRiseFallAmountDisplay
//				.setText("You can rise/fall a maxmium amount of " + 0 + "mm");
//		this.add(toSmallImageCircleDisplay);
//	}
//	
//	private void setupImageCircleComboBox(){
//		//Set up multiple image circle combo box
//		imageCircleComboBox = new JComboBox<String>(multipleImageCircles.toArray(new String[multipleImageCircles.size()]));
//		imageCircleComboBox.setToolTipText("Choose Image Circle");
//		imageCircleComboBox.addActionListener( new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				JComboBox cb = (JComboBox)e.getSource();
//				
//				//used for setting current image circle
//				try{
//				imageCircle= Double.parseDouble((String) cb.getSelectedItem());
//				} catch (NullPointerException e1){}
//				imageCircleInputField.setText("" + imageCircle);
//				movementCalculator();
//				repaint();
//			}
//		});
//		this.add(imageCircleComboBox);
//	}
//	
//	private void setUpFilmSizeComboBox(){
//		//Set up multiple film sizes combo box
//		filmSizeComboBox = new JComboBox<String>(filmSizes);
//		filmSizeComboBox.setToolTipText("Choose Film Size");
//		filmSizeComboBox.setSelectedIndex(locationInFilmSizeArray);
//		filmSizeComboBox.addActionListener( new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				JComboBox cb = (JComboBox)e.getSource();
//				
//				//used for setting current image circle
//				String selectedItem = (String)cb.getSelectedItem();
//				for(int i=0;i<filmSizes.length;i++){
//					String temp = filmSizes[i];
//					if(temp.equals(selectedItem)){
//						filmWidth = filmDimensions[i].getX();
//						filmHeight = filmDimensions[i].getY();
//						locationInFilmSizeArray =i;
//						break;
//					}
//				}
//				movementCalculator();
//				repaint();
//			}
//		});
//		this.add(filmSizeComboBox);
//	}
//	
//	//why do I have to do border twice?
//	private void setUpSlider() {
//		// Slider to 'zoom' in and out
//		JSlider slider = new JSlider(JSlider.HORIZONTAL, 1, 10, 3);
//		slider.setToolTipText("Adjust Speed");
//		TitledBorder tb = new TitledBorder(BorderFactory.createLineBorder(Color.black));
//		slider.setBorder(tb);
//		tb.setTitle("Zoom = " + "" + constantSizeIncrease);
//		slider.addChangeListener(new ChangeListener() {
//			public void stateChanged(ChangeEvent e) {
//				JSlider source = (JSlider) e.getSource();
//				if (!source.getValueIsAdjusting()) {
//					constantSizeIncrease = (int) (source.getValue()); // get value from slider
//					TitledBorder tb = new TitledBorder(BorderFactory.createLineBorder(Color.black));
//					source.setBorder(tb);
//					tb.setTitle("Zoom = " + "" + constantSizeIncrease);
//					source.repaint();
//					repaint();
//				}
//			}
//		});
//		this.add(slider);
//	}
//	
//	private void setUpButtons() {
//		setUpAddImageCircleButton();
//		setUpRemoveImageCircleButton();
//		checkBoxForDrawAllImageCircles();
//		
//	}
//	
//	//adds the specified item to the combo boxes arraylist
//	private void setUpAddImageCircleButton(){
//		//Set up the start button
//		JButton addImageCircleButton = new JButton("Add IC");
//		addImageCircleButton.setToolTipText("Add Image Circle");
//		addImageCircleButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				//Checks if max image circles has been reached and if the image circle is already in the array or not
//				if(multipleImageCircles.size() <= maxArraySize && isImageCirleAlreadyInList() == -1){
//					multipleImageCircles.add("" + imageCircle);
//					imageCircleComboBox.removeAllItems();
//					for(int i=0;i<multipleImageCircles.size();i++){
//						imageCircleComboBox.addItem(multipleImageCircles.get(i));
//				    }
//					imageCircleComboBox.setSelectedIndex(multipleImageCircles.size()-1);
//				}
//				if(isImageCirleAlreadyInList() < 0){
//					imageCircleComboBox.setSelectedIndex(multipleImageCircles.size()-1);
//				}
//				repaint();
//			}
//
//			
//		});
//		add(addImageCircleButton);
//	}
//	
//	//removes the specified item from the combo box arraylist
//	private void setUpRemoveImageCircleButton(){
//		//Set up the start button
//		JButton addImageCircleButton = new JButton("Remove IC");
//		addImageCircleButton.setToolTipText("Remove Image Circle");
//		addImageCircleButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				if(!multipleImageCircles.isEmpty()){
//					multipleImageCircles.remove(isImageCirleAlreadyInList());
//					imageCircleComboBox.removeAllItems();
//					for(int i=0;i<multipleImageCircles.size();i++){
//						imageCircleComboBox.addItem(multipleImageCircles.get(i));
//				    }
//				}
//				repaint();
//			}
//		});
//		add(addImageCircleButton);
//	}
//	
//	//Draws the checkbox for drawing all image circles in the combo box arraylist
//	private void checkBoxForDrawAllImageCircles(){
//		// why is in IC is C underlined??
//		checkBoxForDrawAllImageCircles = new JCheckBox("Draw all IC");
//		checkBoxForDrawAllImageCircles.setToolTipText("Draw all Image Circles in the combo box");
//		checkBoxForDrawAllImageCircles.setMnemonic(KeyEvent.VK_C); 
//		checkBoxForDrawAllImageCircles.setSelected(false);
//		checkBoxForDrawAllImageCircles.addActionListener(new ActionListener(){
//			public void actionPerformed(ActionEvent e) {
//				if(checkBoxForDrawAllImageCircles.isSelected()){
//					drawAllImageCircles = true;					
//				} else {
//					drawAllImageCircles = false;
//				}
//				repaint();
//			}
//		});
//		add(checkBoxForDrawAllImageCircles);
//	}
//	
//	private void checkBoxForDrawAllFilmSizes(){
//		// why is in IC is C underlined??
//		checkBoxForDrawAllFilmTypes = new JCheckBox("Draw all Film Types");
//		checkBoxForDrawAllFilmTypes.setToolTipText("Draw all Film Types in the combo box");
//		checkBoxForDrawAllFilmTypes.setMnemonic(KeyEvent.VK_C); 
//		checkBoxForDrawAllFilmTypes.setSelected(false);
//		checkBoxForDrawAllFilmTypes.addActionListener(new ActionListener(){
//		public void actionPerformed(ActionEvent e) {
//			if(checkBoxForDrawAllFilmTypes.isSelected()){
//				isdrawAllFilmTypesChecked = true;
//			} else {
//				isdrawAllFilmTypesChecked = false;
//			}
//			repaint();
//		}
//	});
//	add(checkBoxForDrawAllFilmTypes);
//}
//	
//	//used for finding if the image circle is already in the combo box arraylist
//	private int isImageCirleAlreadyInList() {
//		for(int i=0;i<multipleImageCircles.size();i++){
//			if((multipleImageCircles.get(i)).equals("" + imageCircle)){
//				return i;
//			}
//		}
//		return -1;
//	}
//	
//	@Override
//	public void actionPerformed(ActionEvent e) {}
//
//}
