package storyteam.server.story.model;

/**
 * Class used to convert story from the tory creator into strory savable in bdd
 */
public class CreatorBlocPrevious {
	Integer id;
	Double x;
	Double y;

	public CreatorBlocPrevious() {

	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
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
