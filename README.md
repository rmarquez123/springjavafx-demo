# springjavafx-demo
<h3>Purpose</h3>

This demo uses a helper class to keep the Main class clean and to illustrate an approach for improved maintenance by having minimal hard-coded configuration in the Main java file. 
<h3> Implementation</h3>
Below is the sequence diagram created for the implementation.  

  <div align="center" width="1200px"> 
    <img src="http://ricardo-marquez.com/rm/assets/images/sequence-diagram-springjavafx.svg" alt="" height="450px">
  </div> 
  
<h2>1. Sequence Entry: <code>init()</code> </h2>

The init() method is the first method called and it takes place before the application has started.  At this point the Main class will initiate the spring loading.  SpringBoot will return the application context after it is done loading.   JavaFX controllers  will also be initialized during the Spring loading if they are beans.  Next, FXMLLoaders will be created with the controller factories using the spring instantiated controllers.  This step will processed by a custom FxmlInitializer class which is a Spring bean which should be configured with a Spring .xml file so that fxml files with Spring instantiated controllers can be injected by the Spring loader.  The fxml root should also be specified.  This structure makes it so that minimal work is done by the Main and the (volatile) configurations are maintained within spring .xml files.

<h2>2. Sequence Entry:  <code>start()</code> </h2>

The start method invocation simpy sets the scene and invokes the show method of the Stage.  It would be a good idea in the future to fire an OnShowing event. 

<h2> 3. Sequence Entry: <code>stop()</code> </h2>

On the stop method, the context is closed which will give beans their chance to do clean-up.
