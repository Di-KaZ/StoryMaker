package storyteam.server.story.model;

public class CcreatorBlocParent {
	String id;
	Double x;
	Double y;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Double getX() {
		return this.x;
	}

	public void setX(Double x) {
		this.x = x;
	}

	public Double getY() {
		return this.y;
	}

	public void setY(Double y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return "{" + " id='" + getId() + "'" + ", x='" + getX() + "'" + ", y='" + getY() + "'" + "}";
	}

}
