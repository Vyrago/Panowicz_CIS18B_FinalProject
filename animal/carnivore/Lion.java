package animal.carnivore;
import animal.Animal;

public class Lion implements Animal {

//3. In the Giraffe and Lion classes, create private members variables called "name" and "type". ->
	private String name;
	private String type = "Lion";

	public Lion(String name) {
		this.name = name;
	}

//-> Modify getName to return the this.name variable. ->
	@Override
	public String getName() {
		return this.name;
	}

//-> Implement the getType() function to return the this.type variable.
	@Override
	public String getType() {
		return this.type;
	}
}
