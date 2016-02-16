import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import java.io.IOException;

public class BinaryConverterController 
{
	// String array of options to compare against when selection checking. 
	final private String[] OPTIONS = { "Binary to Number", "Number to Binary", 
			"Binary to Text", "Text to Binary" };
	
	protected BinaryConverter conversion;	
	
	/* UI elements */
	@FXML private Text errorText;
	@FXML private TextArea outputField;
	@FXML private TextArea inputField;
	@FXML private Button convertButton;
	@FXML private ToggleGroup conversionOptions;

	/* Handles an instance of a mouse click on the convert button. */
	@FXML protected void handleConvertBtnClick(ActionEvent action) 
	{
		checkSelection();
	}	
	
	/* Handles an instance of an enter key press on the convert button. */
	@FXML protected void handleConvertBtnKeyPress(KeyEvent keyEvent)
	{
		if(keyEvent.getCode() == KeyCode.ENTER)
			checkSelection();
	}
	
	/* Checks which toggle option was selected 
	 * to perform a corresponding operation. */
	private void checkSelection() 
	{
		String selection = "";
		
		try {
			selection = conversionOptions.getSelectedToggle().toString();
		} catch (NullPointerException ex) {
			errorText.setFill(Color.FIREBRICK);
			errorText.setText("Select an option");
		}
		
		String input = inputField.getText();
				
		if (selection.contains(OPTIONS[0])) 
			displayResults(input, OPTIONS[0]);
		else if (selection.contains(OPTIONS[1]))
			displayResults(input, OPTIONS[1]);
		else if (selection.contains(OPTIONS[2]))
			displayResults(input, OPTIONS[2]);
		else if (selection.contains(OPTIONS[3]))
			displayResults(input, OPTIONS[3]);
	}
	
	/* Receives an input and option to send to the converter class, which 
	   then delegates the results back to this method to be displayed */
	private void displayResults(String input, String option) 
	{
		boolean error = false;
		
		try {
			conversion = new BinaryConverter(input, option);
		} catch (IOException | NumberFormatException ex) {
			errorText.setText(ex.getMessage());
			errorText.setFill(Color.FIREBRICK);
			errorText.setVisible(true);
			outputField.clear();
			error = true;
		}
		
		if(!error) {
			outputField.setStyle("-fx-text-fill: #1da21d;");
			outputField.setText(conversion.getConversion());
			errorText.setVisible(false);
		}
	}
}