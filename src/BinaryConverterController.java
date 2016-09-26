import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import java.io.IOException;
import javafx.fxml.FXML;

public class BinaryConverterController
{
	protected enum ConversionOptions {
		BINARY_TO_NUMBER, NUMBER_TO_BINARY, BINARY_TO_TEXT,
		TEXT_TO_BINARY
	}
	private ConversionOptions conversionOpt;
	// String array of options to compare against when selection checking.


	protected BinaryConverterModel converter;
	/* UI elements */
	@FXML private Text errorText;
	@FXML private TextArea outputField;
	@FXML private TextArea inputField;
	//@FXML private Button convertButton;
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
	
	/* Checks which toggle option was selected to perform a corresponding operation. */
	private void checkSelection() {
		String selection = "";
		try {
			selection = conversionOptions.getSelectedToggle().toString();
			conversionOpt = (selection.contains("Binary to Number") ? ConversionOptions.BINARY_TO_NUMBER :
							selection.contains("Number to Binary") ? ConversionOptions.NUMBER_TO_BINARY :
							selection.contains("Binary to Text") ? ConversionOptions.BINARY_TO_TEXT :
							ConversionOptions.TEXT_TO_BINARY);
		} catch (NullPointerException ex) {
			errorText.setFill(Color.FIREBRICK);
			errorText.setText("Select an option");
		}
		String input = inputField.getText();
		displayResults(input, conversionOpt);
	}
	
	/* Receives an input and option to send to the converter class, which 
	   then sends the results back to this method to be displayed */
	private void displayResults(String input, ConversionOptions opt)
	{
		boolean error = false;
		try {
			converter = new BinaryConverterModel(input, opt);
		} catch (IOException | NullPointerException | NumberFormatException ex) {
            displayError(ex.getMessage());
			error = true;
		}
		if(!error) {
			outputField.setStyle("-fx-text-fill: #1da21d;");
			outputField.setText(converter.getConversion());
			errorText.setVisible(false);
		}
	}

    private void displayError(String message) {
        errorText.setText(message);
        errorText.setFill(Color.FIREBRICK);
        errorText.setVisible(true);
        outputField.clear();
    }
}
