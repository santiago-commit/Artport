package com.artport.artport.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "posts")
@Entity
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
        @Column(name = "post_id")
	private Long postId;
	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;
	private String title;
	private String description;

}
