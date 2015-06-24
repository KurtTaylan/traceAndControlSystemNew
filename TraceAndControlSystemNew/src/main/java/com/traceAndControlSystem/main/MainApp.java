package com.traceAndControlSystem.main;

import java.io.InputStream;

import com.traceAndControlSystem.controllers.DemoPageFXMLController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainApp extends Application {
	private Stage stage;

	@Override
	public void start(Stage primaryStage) {
		this.stage = primaryStage;
		gotoDemoPage();

		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void gotoDemoPage() {
		try {
			DemoPageFXMLController demo = 
					(DemoPageFXMLController) replaceSceneContent("/fxml/DemoPageFXML.fxml","Trace And Control System");
			demo.setStage(stage);
		} catch (Exception ex) {

			ex.printStackTrace();
		}
	}

	private Initializable replaceSceneContent(String fxml,String text) throws Exception {

		FXMLLoader loader = new FXMLLoader();
		InputStream in = MainApp.class.getResourceAsStream(fxml);
		loader.setBuilderFactory(new JavaFXBuilderFactory());
		loader.setLocation(MainApp.class.getResource(fxml));
		AnchorPane page;
		try {
			page = (AnchorPane) loader.load(in);
		} finally {
			in.close();
		}
		Scene scene = new Scene(page);
		stage.setScene(scene);
		stage.setTitle(text);
		stage.sizeToScene();
		
		
		return (Initializable) loader.getController();
	}
}
