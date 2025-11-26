package com.mysite.sbb.recommendation;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

import com.mysite.sbb.user.SiteUser;
import com.mysite.sbb.user.UserService;
import com.mysite.sbb.comment.CommentService;
import com.mysite.sbb.post.PostRepository;
import com.mysite.sbb.post.PostService;
import com.mysite.sbb.comment.Comment;
import com.mysite.sbb.post.Post;
import com.mysite.sbb.recommendation.RecommendationService;

import lombok.RequiredArgsConstructor;
import java.time.LocalDateTime;


@Controller
@RequiredArgsConstructor
public class RecommendationController 
{
	
	private final PostRepository postRepository;
	private final PostService postService;
	private final UserService userService;
	private final CommentService commentService;
	private final RecommendationService recommendationService;
	

	@PostMapping("/post/{id}/recommend")

	public String recommend(@PathVariable Integer id) {
	
	    SiteUser user = userService.getCurrentUser();
	    Post post = postService.getPost(id);
	
	    recommendationService.toggleRecommend(post, user);
	
	    return "redirect:/community_detail/" + id;
	}


}