package com.gei.springboot.javafx;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;

/**
 * Helper class for initializing fxml with controllers instantiated through
 * spring. Collaborates with the main class in a Spring boot environment.
 *
 * @author rmarquez
 */
public class FxmlInitializer implements InitializingBean {

  private final List<String> fxmlList = new ArrayList<>();
  private final Map<String, Parent> rootNodes = new HashMap<>();
  private String sceneRoot;

  /**
   * Public constructor. Set properties using setters.
   */
  public FxmlInitializer() {
  }

  /**
   * Fxml List setter. This list does not need to have the root.
   *
   * @param fxmlList
   */
  public void setFxmlList(List<String> fxmlList) {
    this.fxmlList.addAll(fxmlList);
  }

  /**
   * Scene root setter. The scene root must be set otherwise the
   * {@linkplain #load(org.springframework.context.ApplicationContext)} method
   * will fail with a null pointer exception.
   *
   * @param sceneRoot
   * @see #load(org.springframework.context.ApplicationContext)
   */
  public void setSceneRoot(String sceneRoot) {
    this.sceneRoot = sceneRoot;
  }

  /**
   * {@inheritDoc} OVERRIDE : Checks if scene root is null. Adds the scene root
   * to the fxml list if not already present.
   *
   * @throws Exception if scene root has not been set.
   */
  @Override
  public void afterPropertiesSet() throws Exception {
    if (this.sceneRoot == null) {
      throw new NullPointerException("Scene root has not been set");
    }
    if (!this.fxmlList.contains(this.sceneRoot)) {
      this.fxmlList.add(this.sceneRoot);
    }
  }

  /**
   * Sets the controller factories to fxml loaders. Stores the roots for each
   * fxml node graph into a map. This method should be called by the main
   * application to load and get the scene root.
   *
   * @param context The application context which contains beans for the
   * controllers of the fxml list.
   * @return The parent node for the scene root fxml node graph.
   * @see #sceneRoot
   * @see #setFxmlList(java.util.List)
   * @see FXMLLoader
   */
  public Parent load(ApplicationContext context) {
    ClassLoader classLoader = this.getClass().getClassLoader();
    this.fxmlList.stream().forEach((fxml) -> {
      URL resource = classLoader.getResource(fxml);
      FXMLLoader loader = new FXMLLoader(resource);
      loader.setControllerFactory(context::getBean);
      Parent root;
      try {
        root = loader.load();
      } catch (IOException ex) {
        throw new RuntimeException("Error loading fxml from resource.  Check args : {"
                + "fxml = " + fxml
                + "}", ex);
      }
      this.rootNodes.put(fxml, root);
    });
    return this.rootNodes.get(this.sceneRoot);
  }
}
