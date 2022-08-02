package com.project.question.cache;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;

import com.project.question.model.Question;

/**
 * Caching implementation for question services
 * @author apurba
 *
 */
public interface QuestionCacheService {

	@Caching(
			put= { @CachePut(value= "questions", key= "#question.questionName") },
            evict= { @CacheEvict(value= "questions", allEntries= true) }
        )
	void saveQuestionToCache(Question question);
	
	@Cacheable({"questions"})
    List<Question> getQuestionsFromCache();
	
	@Caching(
            put= { @CachePut(value= "answers") },
            evict= { @CacheEvict(value= "answers", allEntries= true) }
        )
    void saveAnswerToCache(String answer);
	
	@Cacheable({"answers"})
    List<String> getAnswerFromCache();
}
