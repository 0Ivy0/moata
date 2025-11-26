package com.mysite.sbb.recommendation;

import org.springframework.stereotype.Service;

import com.mysite.sbb.user.SiteUser;
import com.mysite.sbb.post.Post;

import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RecommendationService {

    private final RecommendationRepository recommendationRepository;

    // 추천 실행
    public boolean recommend(Post post, SiteUser user) {

        RecommendationId id = new RecommendationId(user.getId(), post.getId());

        // 이미 추천했는지 검사
        if (recommendationRepository.existsById(id)) {
            return false;  // 이미 추천한 상태
        }

        // 새로운 추천 저장
        Recommendation rec = new Recommendation();
        rec.setId(id);
        rec.setUser(user);
        rec.setPost(post);

        recommendationRepository.save(rec);
        return true;
    }

    // 추천 count 조회
    public int count(Post post) {
        return recommendationRepository.countByPost(post);
    }
    
    // 추천 여부 검사 
    public boolean isRecommended(Post post, SiteUser user) {
        RecommendationId id = new RecommendationId(user.getId(), post.getId());
        return recommendationRepository.existsById(id);
    }
    
    public boolean toggleRecommend(Post post, SiteUser user)  // 토글 기능 구현
    {

        RecommendationId id = new RecommendationId(user.getId(), post.getId());

        // 이미 추천한 상태 → 추천 취소
        if (recommendationRepository.existsById(id)) {
            recommendationRepository.deleteById(id);
            return false;
        }

        // 추천 추가
        Recommendation rec = new Recommendation();
        rec.setId(id);
        rec.setUser(user);
        rec.setPost(post);
        rec.setCreatedAt(LocalDateTime.now());

        recommendationRepository.save(rec);
        return true;
    }


}
