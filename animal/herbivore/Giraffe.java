package animal.herbivore;
import animal.Animal;

public class Giraffe implements Animal {

	private String name;
	private String type = "Giraffe";

	public Giraffe(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getType() {
		return this.type;
	}
}
