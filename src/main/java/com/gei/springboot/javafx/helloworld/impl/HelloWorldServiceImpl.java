package com.gei.springboot.javafx.helloworld.impl;

import com.gei.springboot.javafx.helloworld.HelloWorldService;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import org.springframework.stereotype.Component;

/**
 * Simple implementation of {@linkplain HelloWorldService} as part of
 * demonstration spring injection into a JavaFX controller.
 *
 * @author rmarquez
 */
@Component
public class HelloWorldServiceImpl implements HelloWorldService {

  /**
   * {@inheritDoc}
   */
  @Override
  public void sayHello() {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setGraphic(new Label("Hello World!"));
    alert.show();
  }
}
