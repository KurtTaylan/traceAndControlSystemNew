package com.traceAndControlSystem.controllers;

import java.awt.Font;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.traceAndControlSystem.model.Person;
import com.traceAndControlSystem.model.Tribe;
import com.traceAndControlSystem.service.PersonService;
import com.traceAndControlSystem.service.TribeService;
import com.traceAndControlSystem.service.impl.TribeServiceImpl;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.InputEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.PickResult;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Callback;

public class DemoPageFXMLController implements Initializable {

	private PersonService personService;
	private TribeService tribeService;
	private int mapTick;
	private Tribe tribe;
	private Tribe selectedTribe;
	private Tribe specialTribe;

	public Tribe getSpecialTribe() {
		return specialTribe;
	}

	public void setSpecialTribe(Tribe specialTribe) {
		this.specialTribe = specialTribe;
	}

	private Stage stage;
	private String addressOfImage;

	public Tribe getSelectedTribe() {
		return selectedTribe;
	}

	public void setSelectedTribe(Tribe selectedTribe) {
		this.selectedTribe = selectedTribe;
	}

	public int getMapTick() {
		return mapTick;
	}

	public void setMapTick(int mapTick) {
		this.mapTick = mapTick;
	}

	ObservableList<Person> personData;
	ObservableList<Tribe> tribeData;

	@FXML
	ImageView map;
	@FXML
	TableView<Person> person_tableView;
	@FXML
	TableColumn<Person, Integer> personId;
	@FXML
	TableColumn<Person, String> name, position, date;
	@FXML
	TableView<Tribe> tribe_tableView;
	@FXML
	TableColumn<Tribe, String> tribeName, address;
	@FXML
	TableColumn<Tribe, Integer> numberOfPeople;
	@FXML
	Label selectedTribeName, errorLabel;
	@FXML
	BorderPane borderPane;
	@FXML
	AnchorPane anchorPane;

	/**
	 * Initializes the controller class.
	 * 
	 * @param url
	 * @param rb
	 */
	public void initialize(URL url, ResourceBundle rb) {
		initAll();

	}

	/* DRAG AND DROP */
	public void dragAndDropSource(final TableView<Tribe> source) {

		source.setOnDragDetected(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {

				/* drag was detected, start a drag-and-drop gesture */
				/* allow any transfer mode */
				Dragboard db = source.startDragAndDrop(TransferMode.ANY);

				/* Put a string on a dragboard */
				ClipboardContent content = new ClipboardContent();
				content.putString(source.getSelectionModel().getSelectedCells()
						.toString());
				db.setContent(content);

				event.consume();
			}
		});

		source.setOnDragDone(new EventHandler<DragEvent>() {
			public void handle(DragEvent event) {
				/* the drag and drop gesture ended */
				/* if the data was successfully moved, clear it */
				if (event.getTransferMode() == TransferMode.MOVE) {
					// source.setText("");
				}

				event.consume();
			}
		});

	}

	public void dragAndDropTarget(final ImageView target) {
		target.setOnDragOver(new EventHandler<DragEvent>() {
			public void handle(DragEvent event) {

				/* data is dragged over the target */
				/*
				 * accept it only if it is not dragged from the same node and if
				 * it has a string data
				 */
				if (event.getGestureSource() != target
						&& event.getDragboard().hasString()) {

					/* allow for both copying and moving, whatever user chooses */
					event.acceptTransferModes(TransferMode.ANY);
				}
				event.consume();
			}
		});

		target.setOnDragEntered(new EventHandler<DragEvent>() {
			public void handle(DragEvent event) {
				/* the drag-and-drop gesture entered the target */
				/* show to the user that it is an actual gesture target */
				if (event.getGestureSource() != target
						&& event.getDragboard().hasString()) {

				}
				event.consume();
			}
		});

		target.setOnDragExited(new EventHandler<DragEvent>() {
			public void handle(DragEvent event) {
				/* mouse moved away, remove the graphical cues */

				event.consume();
			}
		});

		target.setOnDragDropped(new EventHandler<DragEvent>() {
			public void handle(DragEvent event) {
				/* data dropped */
				/* if there is a string data on dragboard, read it and use it */
				Dragboard db = event.getDragboard();
				boolean success = false;
				if (db.hasString()) {
					success = true;
					// target.setText(target.getText() + "\n" + db.getString());
				}
				/*
				 * let the source know whether the string was successfully
				 * transferred and used
				 */
				event.setDropCompleted(success);

				event.consume();
			}
		});

	}

	/* Starters */
	private void initAll() {
		initSpring();
		initTableViews();
		initMapTick();
	}

	private void initSpring() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationContext.xml");

		personService = (PersonService) context.getBean("personService");
		tribeService = (TribeService) context.getBean("tribeService");

	}

	private void initTableViews() {

		tribe_tableView
				.setRowFactory(cb -> {
					TableRow<Tribe> row = new TableRow<>();
					setSpecialTribe(getSelectedTribe());
					ImageView image = createPin();
					Label imageLabel = new Label();
					
					
					row.setOnContextMenuRequested(ev -> {

					});
					row.setOnDragDetected(ev -> {

						String name = row.getItem().getName();
						String rowInfo = row.getText();
						Dragboard db = row.startDragAndDrop(TransferMode.COPY);
						ClipboardContent content = new ClipboardContent();
						content.putString(rowInfo);
						db.setContent(content);

						imageLabel.setText(name);
						imageLabel.setTextFill(Paint.valueOf("red"));
						imageLabel.setStyle("-fx-font-family:Times New Roman Bold");
						
						image.toFront();
						image.setVisible(true);

						imageLabel.setTranslateX(image.getX() + 14);
						imageLabel.setTranslateY(image.getY() - 15);
						image.setX(ev.getSceneX() - 40);
						image.setY(ev.getSceneY() - 20);

						image.setOnMouseDragged(evt -> {
							image.setX(evt.getSceneX() - 40);
							image.setY(evt.getSceneY() - 20);

							imageLabel.setTranslateX(image.getX() + 14);
							imageLabel.setTranslateY(image.getY() - 15);
							tribe_tableView.getSelectionModel().select(
									row.getIndex());
							setSelectedTribe(getTribeService().getTribeById(
									row.getIndex() + 1));
							

							evt.consume();
						});
						image.setOnMouseReleased(evt -> {
							setAddressOfImage("" + image.getX() + ","
									+ image.getY());

							setSpecialTribe(getSelectedTribe());
							getSpecialTribe().setAddress(getAddressOfImage());

							getTribeService().saveTribe(getSpecialTribe());
							showTribeTable();
							evt.consume();
						});
						image.setOnContextMenuRequested(e -> {

							destroyPin(image, imageLabel);
							e.consume();
						});

						image.setOnMouseEntered(e -> {

							errorLabel
									.setText(row.getItem().getName()
											+ " imlecini kaldýrmak için SAÐ MOUSE tuþuna basýnýz!");
							e.consume();
						});
						image.setOnMouseExited(e -> errorLabel.setText(""));

						anchorPane.getChildren().addAll(image, imageLabel);

						ev.consume();
					});
					row.setOnMouseDragged(evt -> {
						image.setX(evt.getSceneX() - 40);
						image.setY(evt.getSceneY() - 20);

						imageLabel.setTranslateX(image.getX() + 14);
						imageLabel.setTranslateY(image.getY() - 15);

						evt.consume();
					});
					row.setOnMouseReleased(evt -> {
						setAddressOfImage("" + image.getX() + ","
								+ image.getY());

						setSelectedTribe(getTribeService().getTribeById(
								row.getIndex() + 1));

						setSpecialTribe(getSelectedTribe());
						getSpecialTribe().setAddress(getAddressOfImage());

						getTribeService().saveTribe(getSpecialTribe());
						showTribeTable();
						evt.consume();
					});

					return row;
				});

		personId.setCellValueFactory(new PropertyValueFactory<Person, Integer>(
				"id"));
		name.setCellValueFactory(new PropertyValueFactory<Person, String>(
				"name"));
		position.setCellValueFactory(new PropertyValueFactory<Person, String>(
				"position"));
		date.setCellValueFactory(new PropertyValueFactory<Person, String>(
				"workDate"));

		tribeName.setCellValueFactory(new PropertyValueFactory<Tribe, String>(
				"name"));
		numberOfPeople
				.setCellValueFactory(new PropertyValueFactory<Tribe, Integer>(
						"numberOfPeople"));
		address.setCellValueFactory(new PropertyValueFactory<Tribe, String>(
				"address"));

		showTribeTable();

	}

	public void showTribeTable() {
		personData = FXCollections.observableArrayList();
		tribeData = FXCollections.observableArrayList();

		List<Tribe> tribeList = getTribeService().listTribe();
		for (Tribe tr : tribeList) {
			tribeData.add(tr);

		}

		tribe_tableView.setItems(tribeData);

		tribe_tableView.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				selectedTribeName.setText(tribe_tableView.getSelectionModel()
						.selectedItemProperty().get().getName());
				if (getTribe() == null) {
					setTribe(getSelectedTribe());
				}

				setSelectedTribe(tribe_tableView.getSelectionModel()
						.selectedItemProperty().get());

				personData.clear();
				List<Person> personList = personService
						.listPersonsByTribeNumber(getSelectedTribe());
				for (Person pt : personList) {
					personData.add(pt);
				}

				person_tableView.setItems(personData);

				setTribe(getSelectedTribe());
			}

		});

	}

	private void initMapTick() {
		setMapTick(0);
	}

	/* DEMO ZOOM */
	public void zoomIn(ActionEvent event) {
		if (getMapTick() < 100) {
			setMapTick(getMapTick() + 25);
			map.setFitHeight(map.getFitHeight() + 150);
			map.setFitWidth(map.getFitWidth() + 150);

		} else if (getMapTick() == 100) {
		}
	}

	public void zoomOut(ActionEvent event) {
		if (getMapTick() > 0) {
			setMapTick(getMapTick() - 25);
			map.setFitHeight(map.getFitHeight() - 150);
			map.setFitWidth(map.getFitWidth() - 150);

		} else if (getMapTick() == 0) {
		}

	}

	public ImageView createPin() {
		ImageView pinImage = new ImageView();
		pinImage.setImage(new Image("pin.png"));
		pinImage.setFitHeight(55);
		pinImage.setFitWidth(55);
		return pinImage;
	}

	public void destroyPin(ImageView destroyedPin, Label imageLabel) {
		anchorPane.getChildren().remove(destroyedPin);
		anchorPane.getChildren().remove(imageLabel);
	}

	public PersonService getPersonService() {
		return personService;
	}

	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}

	public TribeService getTribeService() {
		return tribeService;
	}

	public void setTribeService(TribeService tribeService) {
		this.tribeService = tribeService;
	}

	public Tribe getTribe() {
		return tribe;
	}

	public void setTribe(Tribe tribe) {
		this.tribe = tribe;
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public String getAddressOfImage() {
		return addressOfImage;
	}

	public void setAddressOfImage(String addressOfImage) {
		this.addressOfImage = addressOfImage;
	}

}
