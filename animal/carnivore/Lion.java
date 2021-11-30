package animal.carnivore;
import animal.Animal;

public class Lion implements Animal {

	private String name;
	private String type = "Lion";

	public Lion(String name) {
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
