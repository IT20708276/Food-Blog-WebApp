package com.cyanApp1.postManagement.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class PostImages {
	
	@Id
	@GeneratedValue
	private Integer imageID;
	private String imageName;
	
	
	public PostImages() {
		super();
	}



	@JsonCreator
	public PostImages(@JsonProperty("imageID") Integer imageID,@JsonProperty("imageName") String imageName) {
		super();
		this.imageID = imageID;
		this.imageName = imageName;
	}

	

	public Integer getImageID() {
		return imageID;
	}

	public String getImageName() {
		return imageName;
	}

	
	public void setImageID(Integer imageID) {
		this.imageID = imageID;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}



	@Override
	public String toString() {
		return "PostImages [imageID=" + imageID + ", imageName=" + imageName + "]";
	}

	
	
	
}
