package com.gei.springboot.javafx.controllers;

import com.gei.springboot.javafx.helloworld.HelloWorldService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * FXML Controller class
 *
 * @author rmarquez
 */
@Component
public class SampleController implements Initializable, InitializingBean {
  
  @FXML
  Button helloWorldBtn;
  
  @Autowired
  HelloWorldService helloWorldService;
  
  @Override
  public void afterPropertiesSet() throws Exception {
    if (this.helloWorldService == null) {
      throw new NullPointerException("Hello World Service is undefined"); 
    }
  }
  /**
   * Initializes the controller class.
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
  }  
  
  
  /**
   * 
   * @param evt
   */
  public void onHelloWorldBtnAction(ActionEvent evt){
    this.helloWorldService.sayHello(); 
  }


  
}
