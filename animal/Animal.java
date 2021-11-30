package animal;
public interface Animal {

	default String getType() {
		return "This animal has no Type!";
	}

	default String getName() {
		return "This animal has no Name!";
	}
}
