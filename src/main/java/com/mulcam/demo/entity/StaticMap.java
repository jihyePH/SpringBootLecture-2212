package com.mulcam.demo.entity;

public class StaticMap {
	private int width;
	private int height;
	private String lat;
	private String lng;
	private int level;
	private String maptype;
	private String format;
	private String scale;
	private String lang;
	
	public StaticMap() {}
	public StaticMap(int width, int height, String lat, String lng, int level, String maptype, String format,
			String scale, String lang) {
		this.width = width;
		this.height = height;
		this.lat = lat;
		this.lng = lng;
		this.level = level;
		this.maptype = maptype;
		this.format = format;
		this.scale = scale;
		this.lang = lang;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getMaptype() {
		return maptype;
	}
	public void setMaptype(String maptype) {
		this.maptype = maptype;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public String getScale() {
		return scale;
	}
	public void setScale(String scale) {
		this.scale = scale;
	}
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	@Override
	public String toString() {
		return "StaticMap [width=" + width + ", height=" + height + ", lat=" + lat + ", lng=" + lng + ", level=" + level
				+ ", maptype=" + maptype + ", format=" + format + ", scale=" + scale + ", lang=" + lang + "]";
	}

}

