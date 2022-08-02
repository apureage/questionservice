package com.project.question.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Controller to handle user's question request and response
 * @author apurba
 *
 */
@Tag(name = "QuestionApplication Api", description = "Question service application")
public interface QuestionController {
	
	@Operation(summary = "Client Request a question", description = "Hey Service, can you provide me a question with numbers to add?")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Here you go, solve the question: \"Please sum the numbers 9,5,3\""),
	        @ApiResponse(responseCode = "400", description = "Not a valid request"),
	        @ApiResponse(responseCode = "401", description = "You are not authorized to access "),
	        @ApiResponse(responseCode = "404", description = "Not found")
	})
	public ResponseEntity<String>   getQuestion(@RequestParam(value = "question") String question);
	
	@Operation(summary = "Send Response to a question", description = "Great. The original question was \"Please sum the numbers 9,5,3\" and the answer is 15.")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "That’s great!"),
	        @ApiResponse(responseCode = "400", description = "Not a valid request"),
	        @ApiResponse(responseCode = "401", description = "You are not authorized to access "),
	        @ApiResponse(responseCode = "404", description = "Not found")
	})
	public ResponseEntity<String>   getAnswer(@RequestParam(value = "input") String input);
}
