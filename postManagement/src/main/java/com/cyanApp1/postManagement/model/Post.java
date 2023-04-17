package com.cyanApp1.postManagement.model;


import java.io.File;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Post {
	
		public Post() {
			
		}


		public Post(String userName, Integer postNumber, String location, String imageFile, String description,
				LocalDate targetDate) {
			
			this.userName = userName;
			this.PostNumber = postNumber;
			this.location = location;
			this.imageFile = imageFile;
			this.description = description;
			this.targetDate = targetDate;
		}

		private String userName;
		
		@Id
		@GeneratedValue
		private Integer PostNumber;
		private String location;
		
	
		private String imageFile;
		private String description;
		private LocalDate targetDate;
		
		

		public String getUserName() {
			return userName;
		}


	

		public String getLocation() {
			return location;
		}


		

		public String getDescription() {
			return description;
		}


		public LocalDate getTargetDate() {
			return targetDate;
		}


	

		public Integer getPostNumber() {
			return PostNumber;
		}


		public void setPostNumber(Integer postNumber) {
			PostNumber = postNumber;
		}


		public String getImageFile() {
			return imageFile;
		}


		


		public void setImageFile(String imageFile) {
			this.imageFile = imageFile;
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


		public void setTargetDate(LocalDate targetDate) {
			this.targetDate = targetDate;
		}


		@Override
		public String toString() {
			return "Post [userName=" + userName + ", PostNumber=" + PostNumber + ", location=" + location + ", file="
					+ imageFile + ", description=" + description + ", targetDate=" + targetDate + "]";
		}
				
		
		
		
}
