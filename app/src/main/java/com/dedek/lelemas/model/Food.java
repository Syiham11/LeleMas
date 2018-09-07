package  com.dedek.lelemas.model;

public class Food {

	int id;
	String name;
	String description;
	String measure;

	// constructors
	public Food() {

	}

	public Food(String name) {
		this.name = name;
	}

	public Food(int id, String name, String description, String measure) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.measure = measure;
	}

	// setter
	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	// getter
	public int getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMeasure() {
		return measure;
	}

	public void setMeasure(String measure) {
		this.measure = measure;
	}

}
