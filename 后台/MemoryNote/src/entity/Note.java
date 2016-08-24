package entity;

public class Note {
	private int id;
	private String content;
	private int height;
	private int width;
	private int posX;
	private int posY;
	private String BGColour;
	private int contentTextSize;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getPosX() {
		return posX;
	}
	public void setPosX(int posX) {
		this.posX = posX;
	}
	public int getPosY() {
		return posY;
	}
	public void setPosY(int posY) {
		this.posY = posY;
	}
	public String getBGColour() {
		return BGColour;
	}
	public void setBGColour(String bGColour) {
		BGColour = bGColour;
	}
	public int getContentTextSize() {
		return contentTextSize;
	}
	public void setContentTextSize(int contentTextSize) {
		this.contentTextSize = contentTextSize;
	}
	public Note(String content, int height, int width, int posX, int posY,
			String bGColour, int contentTextSize) {
		super();
		this.content = content;
		this.height = height;
		this.width = width;
		this.posX = posX;
		this.posY = posY;
		BGColour = bGColour;
		this.contentTextSize = contentTextSize;
	}
	public Note() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
