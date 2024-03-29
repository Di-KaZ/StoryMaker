package storyteam.server.story.model;

public class CreatorBloc {
	String id;
	String name;
	String text;
	Double x;
	Double y;
	Boolean selected;
	CreatorBlocPrevious in;
	String cover;

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

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

	public void setContent(String content) {
		this.text = content;
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

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "{" + " id='" + getId() + "'" + ", name='" + getName() + "'" + ", text='" + getText() + "'" + ", x='"
				+ getX() + "'" + ", y='" + getY() + "'" + ", selected='" + isSelected() + "'" + ", in='" + getIn() + "'"
				+ "}";
	}

}
