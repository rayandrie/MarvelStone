/**
 * 
 */
package help;

import java.io.Serializable;

import javafx.scene.input.DataFormat;

/**
 * Brief Description of Code
 * @author Raymond Andriejanssen
 * ITP 368, Fall 2018
 * Final Project
 * andrieja@usc.edu
 */
public class DataStore extends DataFormat implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	public String id;
	
	public DataStore() {
		
	}

	public DataStore(String id) {
		super();
		this.id = id;
	}

}

//private VBox setCardWithValues(VBox v, ImageView iv, 
//String imagePath, Label hpLabel, Label hpValue,
//IntegerProperty hpProp, Label damageLabel, 
//Label damageValue, IntegerProperty damageProp) {
//v = new VBox(5);
//iv = setCardImage(iv, imagePath);
//HBox h1 = new HBox(5);
//hpLabel = new Label("HP: ");
//hpValue = new Label();
//hpValue.textProperty().bind(hpProp.asString());
//h1.getChildren().addAll(hpLabel, hpValue);
//h1.setAlignment(Pos.CENTER);
//HBox h2 = new HBox(5);
//damageLabel = new Label("Damage: ");
//damageValue = new Label();
//damageValue.textProperty().bind(damageProp.asString());
//h2.getChildren().addAll(damageLabel, damageValue);
//h2.setAlignment(Pos.CENTER);
//v.getChildren().addAll(iv, h1, h2);
//return v;
//}
