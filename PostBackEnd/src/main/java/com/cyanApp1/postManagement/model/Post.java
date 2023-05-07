package com.cyanApp1.postManagement.model;


import java.io.File;
import java.sql.Blob;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;

@Entity
public class Post {
	
	public Post() {
		this.targetDate = LocalDateTime.now();
	}
		public Post(String userName, Integer postNumber, String location, String description,
				LocalDateTime targetDate) {
			
			this.userName = userName;
			this.PostNumber = postNumber;
			this.location = location;
			this.description = description;
			this.targetDate = LocalDateTime.now();
		}

		private String userName;
		
		@Id
		@GeneratedValue
		private Integer PostNumber;
		private String location;
		
	
		private String description;
		private LocalDateTime targetDate;
		
		@OneToMany(targetEntity = PostImages.class,cascade = CascadeType.ALL)
		@JoinColumn(name="post.postNumber",referencedColumnName = "PostNumber")
		private List<PostImages> postImages;

		public String getUserName() {
			return userName;
		}


	

		public String getLocation() {
			return location;
		}


		

		public String getDescription() {
			return description;
		}


		public LocalDateTime getTargetDate() {
			return targetDate;
		}


	

		public Integer getPostNumber() {
			return PostNumber;
		}


		public void setPostNumber(Integer postNumber) {
			PostNumber = postNumber;
		}






		public void setUserName(String userName) {
			this.userName = userName;
		}



		public void setLocation(String location) {
			this.location = location;
		}


		

		public void setDescription(String description) {
			this.description = description;
		}


		public void setTargetDate(LocalDateTime targetDate) {
			this.targetDate = targetDate;
		}


	
		
		public List<PostImages> getPostImages() {
			return postImages;
		}
		public void setPostImages(List<PostImages> postImages) {
			this.postImages = postImages;
		}
		@Override
		public String toString() {
			return "Post [userName=" + userName + ", PostNumber=" + PostNumber + ", location=" + location
					+ ", description=" + description + ", targetDate=" + targetDate + ", postImages=" + postImages
					+ "]";
		}
	
				
		
		
		
}
