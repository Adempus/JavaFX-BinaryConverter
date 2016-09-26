import CustomExceptions.BinaryFormatException;
import java.io.IOException;
import java.math.BigInteger;

public class BinaryConverterModel
{
	// I/O objects
	private Object input;
	private Object output;

	// constructor 
	public BinaryConverterModel(String input, BinaryConverterController.ConversionOptions option)
			throws IOException, NumberFormatException, NullPointerException
	{
		if (input.length() == 0)
			throw new IOException("Must enter a value");
		if(option == null)
			throw new NullPointerException("Must select an option");

		switch (option) {
			case BINARY_TO_NUMBER : checkBinary(input); binaryToNumber(); break;
			case NUMBER_TO_BINARY : checkNumber(input); numberToBinary(); break;
			case BINARY_TO_TEXT : checkBinary(input); binaryToText(); break;
			case TEXT_TO_BINARY : this.input = input; textToBinary(); break;
			default : throw new IOException("Invalid option");
		}
	}
	
	// Checks input to see if it's a valid binary  
	private void checkBinary(String input) throws BinaryFormatException
    {
		input = input.replaceAll("\\s", ""); // remove spaces to prevent errors
		for (int i = 0 ; i < input.length() ; i++) {
			if(!(input.charAt(i) == '0' || input.charAt(i) == '1'))
				throw new BinaryFormatException("Not a valid binary value.");
		}
		this.input = input;
	}

	// Checks the input to see if it's a valid numeric value 
	private void checkNumber(String input) throws NumberFormatException
    {
		input = input.replaceAll("\\s", "");
		for(int i = 0 ; i < input.length() ; i++) {
			if(!Character.isDigit(input.charAt(i)))
				throw new NumberFormatException("Not a valid numeral");
		}
		this.input = new BigInteger(input);
	}
	
	// Turns binary into integer. 
	private void binaryToNumber() {
		BigInteger value = new BigInteger(input.toString(), 2);
		this.output = value.toString();
	}

	// Turns numbers into binary 
	private void numberToBinary() {
        BigInteger value = new BigInteger(input.toString());
		this.output = value.toString(2);
	}
	
	// Turns binary into text. 
	private void binaryToText() throws BinaryFormatException {
		if (input.toString().length() % 8 != 0)
			throw new BinaryFormatException("Binary must be a multiple of 8");
		
		String input = this.input.toString();
		String _byte = "", textResult = "";
		
		for (int i = 1 ; i <= input.length() ; i++) {
            _byte += input.charAt(i-1);
            if (i % 8 == 0) {
                int charCode = Integer.parseInt(_byte, 2);
                textResult += (char) charCode;
                _byte = "";
            }
        }
		this.output = textResult;
	}
	
	// Turns text into binary. 
	private void textToBinary() {
        String binResult = "";
        final String fourBitPrepend = "0000";
        final String twoBitPrepend = "00";
        final String bitPrepend = "0";
        byte[] charCodes = input.toString().getBytes();

        for(byte ch : charCodes) {
            String binary = Integer.toBinaryString(ch);
            switch(binary.length()) {
                case 4 : binResult += fourBitPrepend + binary; break;
                case 6 : binResult += twoBitPrepend + binary; break;
                case 7 : binResult += bitPrepend + binary; break;
                default : binResult += binary;
            }
        }
		this.output = binResult;
	}

	public String getConversion() {
        return output.toString();
	}
}
