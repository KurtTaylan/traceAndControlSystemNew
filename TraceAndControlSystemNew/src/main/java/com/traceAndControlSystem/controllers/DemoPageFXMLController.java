package com.traceAndControlSystem.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.traceAndControlSystem.model.Person;
import com.traceAndControlSystem.model.Tribe;
import com.traceAndControlSystem.service.PersonService;
import com.traceAndControlSystem.service.TribeService;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;

public class DemoPageFXMLController implements Initializable {
	
	private PersonService personService;
	private TribeService  tribeService;
    
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

	ObservableList<Person> personData;
    
    @FXML
    TableView<Person> tribe_tableView,person_tableView;
    @FXML
    TableColumn<Person, Integer> id;
    @FXML
    TableColumn<Person, String> name,position,numberOfPeople,tribeNumber;

    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    public void initialize(URL url, ResourceBundle rb) {
    	initSpring();
          
    }   

    /*                      DRAG AND DROP                                       */
        public void dragAndDropSource(final TableView<Person> source) {
        source.setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                /* drag was detected, start a drag-and-drop gesture*/
                /* allow any transfer mode */
                Dragboard db = source.startDragAndDrop(TransferMode.ANY);

                /* Put a string on a dragboard */
                ClipboardContent content = new ClipboardContent();
               // content.putString(source.getText());
                db.setContent(content);

                event.consume();
            }
        });

        source.setOnDragDone(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                /* the drag and drop gesture ended */
                /* if the data was successfully moved, clear it */
                if (event.getTransferMode() == TransferMode.MOVE) {
                 //   source.setText("");
                }

                event.consume();
            }
        });

    }

        public void dragAndDropTarget(final ImageView target) {
        target.setOnDragOver(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                /* data is dragged over the target */
                /* accept it only if it is not dragged from the same node 
                 * and if it has a string data */
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
                /* let the source know whether the string was successfully 
                 * transferred and used */
                event.setDropCompleted(success);

                event.consume();
            }
        });

    }
    
    /* 						Spring Start										*/
        private void initSpring(){
        	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
   		 
   		 	personService = (PersonService)context.getBean("personService");
   		 	tribeService = (TribeService) context.getBean("tribeService");
   		 
   		 	Person person1 = new Person("Taylan Kurt","Lider","30/12/2015");
   		 	Person person2 = new Person("Ugurcan Lacin","Kole","30/12/2015");
   		 
   		 	List<Person> list = new ArrayList<Person>();
   		 	list.add(person1);
   		 	list.add(person2);
   		 
   		 
   		 
   		 	Tribe tribe1 = new Tribe("Ekip1");
   		 	tribe1.setPersonList(list);
   		 	tribeService.saveTribe(tribe1);
   		 
        	
        	context.close();
        	
        }
}
