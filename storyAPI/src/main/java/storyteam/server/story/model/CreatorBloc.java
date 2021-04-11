package storyteam.server.story.model;

public class CreatorBloc {
	Integer id;
	String name;
	String content;
	Double x;
	Double y;
	Boolean selected;
	CreatorBlocPrevious in;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public CreatorBlocPrevious getIn() {
		return this.in;
	}

	public void setIn(CreatorBlocPrevious in) {
		this.in = in;
	}

	@Override
	public String toString() {
		return "{" + " id='" + getId() + "'" + ", name='" + getName() + "'" + ", text='" + getContent() + "'" + ", x='"
				+ getX() + "'" + ", y='" + getY() + "'" + ", selected='" + isSelected() + "'" + ", in='" + getIn() + "'"
				+ "}";
	}

}
