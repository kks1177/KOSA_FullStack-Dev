package sec07.exam03_view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class RootController implements Initializable {
	//컨트롤러 등록
	@FXML private ListView<String> listView;
	@FXML private TableView<Phone> tableView;	
	@FXML private ImageView imageView;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//리스크 뷰 세팅
		listView.setItems(
				FXCollections.observableArrayList(
			"갤럭시S1", "갤럭시S2", "갤럭시S3", "갤럭시S4", "갤럭시S5", "갤럭시S6", "갤럭시S7"
		));
		//selectedIndex 속성 감시
		listView.getSelectionModel().selectedIndexProperty().addListener(
				new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				tableView.getSelectionModel().select(newValue.intValue());
				tableView.scrollTo(newValue.intValue());
			}
		});
		
		//총7개의 데이터 등록 TableView 에서 사용할 데이터
		ObservableList phoneList = FXCollections.observableArrayList(
		    new Phone("갤럭시S1", "phone01.png"),
		    new Phone("갤럭시S2", "phone02.png"),
		    new Phone("갤럭시S3", "phone03.png"),
		    new Phone("갤럭시S4", "phone04.png"),
		    new Phone("갤럭시S5", "phone05.png"),
		    new Phone("갤럭시S6", "phone06.png"),
		    new Phone("갤럭시S7", "phone07.png")
		);//end observableArrayList
		
		//TableView 열 세팅
		TableColumn tcSmartPhone = tableView.getColumns().get(0);
		tcSmartPhone.setCellValueFactory(
			new PropertyValueFactory("smartPhone")
		);
		tcSmartPhone.setStyle("-fx-alignment: CENTER;");	
		
		//TableView 열 세팅
		TableColumn tcImage = tableView.getColumns().get(1);
		tcImage.setCellValueFactory(
			new PropertyValueFactory("image")
	    );
		tcImage.setStyle("-fx-alignment: CENTER;");		
		
		//phoneList 등록
		tableView.setItems(phoneList);
		
		//selectedIndex 속성 감시
		tableView.getSelectionModel().selectedItemProperty().addListener(
				new ChangeListener<Phone>() {					
			@Override
			public void changed(
			ObservableValue<? extends Phone> observable	, Phone oldValue, Phone newValue) {
					if(newValue!=null) {
						imageView.setImage(
							new Image(
								getClass()
								.getResource("images/" + newValue.getImage())
								.toString())//end Image
							);//end setImage
					}//end if
				}//end observable
	    	}//end new ChangeListener
       );//end addListener
		
	}//end initialize
	
	//버튼 클릭 이벤트 함수
	@FXML
	public void handleBtnOkAction(ActionEvent e) {
		String item = listView.getSelectionModel().getSelectedItem();
		System.out.println("ListView 스마트폰: " + item);
		
		Phone phone = tableView.getSelectionModel().getSelectedItem();
		System.out.println("TableView 스마트폰: " + phone.getSmartPhone());
		System.out.println("TableView 이미지: " + phone.getImage());
	}//end handleBtnOkAction
	
	public void handleBtnCancelAction(ActionEvent e) {
		Platform.exit();
	}
}//end class
