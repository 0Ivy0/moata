package com.mysite.sbb.post;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

import com.mysite.sbb.user.SiteUser;
import com.mysite.sbb.user.UserService;

import lombok.RequiredArgsConstructor;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CommunityController 
{
	
	private final PostRepository postRepository;
	private final PostService postService;
	private final UserService userService;
	
	@GetMapping("/community")
    public String communityList(Model model)
    {
		List<Post> postList=this.postRepository.findAll(Sort.by(Sort.Direction.DESC,"id")); // 모든 포스트 긁어오기
		model.addAttribute("postList",postList); // 리스트 템블릿에 전달
    		return "community";
    }
	
	@GetMapping("/community_detail/{id}")
    public String community_detail(@PathVariable("id")Integer id, Model model)
    {
		Post post = postRepository.findById(id).orElseThrow(()->new RuntimeException("게시글을 찾을 수 없습니다. "));
		model.addAttribute("post",post); //게시글 정보 전송
		
		String user = userService.getCurrentUserId();

	    if (user == null) {
	        return "redirect:/login?needLogin2";
	    }
		
	    model.addAttribute("user",user);
    		return "community_detail";
    }
	
	@PostMapping("/community_off/{id}")
	public String stopRecruit(@PathVariable Integer id) {

	    postService.stopRecruit(id); // 서비스에서 처리

	    return "redirect:/community_detail/" + id;
	}

	
	@GetMapping("/community_write")
	public String community_write(Model model) 
	{

	    String userId = userService.getCurrentUserId();

	    if (userId == null) {
	        return "redirect:/login?needLogin";
	    }

	    model.addAttribute("userId", userId);
	    return "community_write";
	}
	
	@PostMapping("/community_write")
	public String writeProcess(@RequestParam("title") String title,
	                           @RequestParam("content") String content,
	                           @RequestParam("category") String category,
	                           @RequestParam("userId") String userId) {

	    postService.writePost(title, content, category,userId);

	    return "redirect:/community";
	}
	
	@PostMapping("/community_search")
	public String coummunity_search(@RequestParam("key") String key, Model model)
	{
		List<Post> postList=postService.searchPosts(key);
		
		model.addAttribute("postList",postList);
		model.addAttribute("key",key);
		
		return "community";
	}
	
	
}
