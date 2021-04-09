package storyteam.server.story.model;

public class CreatorBloc {
	String id;
	String name;
	String text;
	Double x;
	Double y;
	Boolean selected;
	CcreatorBlocParent in;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
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

	public Boolean isSelected() {
		return this.selected;
	}

	public Boolean getSelected() {
		return this.selected;
	}

	public void setSelected(Boolean selected) {
		this.selected = selected;
	}

	public CcreatorBlocParent getIn() {
		return this.in;
	}

	public void setIn(CcreatorBlocParent in) {
		this.in = in;
	}

	@Override
	public String toString() {
		return "{" + " id='" + getId() + "'" + ", name='" + getName() + "'" + ", text='" + getText() + "'" + ", x='"
				+ getX() + "'" + ", y='" + getY() + "'" + ", selected='" + isSelected() + "'" + ", in='" + getIn() + "'"
				+ "}";
	}

}
