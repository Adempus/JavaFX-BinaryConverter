import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class BinaryConverterMain extends Application {
	@Override
	public void start(Stage stage) throws Exception {
		Parent view = FXMLLoader.load(getClass().getResource("BinaryConverterView.fxml"));
		stage.setTitle("Binary Converter");
		stage.setScene(new Scene(view, 445, 245));
		Image icon = new Image(getClass().getResourceAsStream("/icon/binary.png"));
		stage.getIcons().add(icon);
		stage.show();
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}
}
