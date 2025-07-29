package com.onlinelearningplatform.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.onlinelearningplatform.models.Wishlist;

public interface WishlistRepository extends CrudRepository<Wishlist, String>
{
    public Wishlist findByCoursename(String coursename);
	
	public Wishlist findByCourseid(String courseid);
	
	public List<Wishlist> findByLikedusertype(String likedusertype);
	
	public List<Wishlist> findByLikeduser(String likeduser);
	
	public List<Wishlist> findByInstructorname(String instructorname);
	
	public List<Wishlist> findByInstructorinstitution(String instructorinstitution);
	
	public List<Wishlist> findByCoursetype(String coursetype);
	
    public List<Wishlist> findBySkilllevel(String skilllevel);
	
	public List<Wishlist> findByLanguage(String language);
}