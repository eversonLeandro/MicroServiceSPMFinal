package co.edu.unicauca.microserviceproject.controller;

import co.edu.unicauca.microserviceproject.entities.Comment;
import co.edu.unicauca.microserviceproject.infra.dto.CommentRequest;
import co.edu.unicauca.microserviceproject.infra.dto.CommentResponse;
import co.edu.unicauca.microserviceproject.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/projects/{projectId}/comments")
public class ProjectCommentController {

    private final CommentService commentService;

    public ProjectCommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public ResponseEntity<List<CommentResponse>> getComments(@PathVariable Integer projectId) {
        List<CommentResponse> comments = commentService.getCommentsByProject(projectId).stream()
                .map(c -> new CommentResponse(
                        c.getCoordinatorName(),
                        c.getMessage(),
                        c.getTimestamp()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(comments);
    }

    @PostMapping
    public ResponseEntity<CommentResponse> addComment(
            @PathVariable Integer projectId,
            @RequestBody CommentRequest request) {

        Comment newComment = commentService.addComment(
                projectId,
                request.getCoordinatorId(),
                request.getCoordinatorName(),
                request.getMessage());

        CommentResponse response = new CommentResponse(
                newComment.getCoordinatorName(),
                newComment.getMessage(),
                newComment.getTimestamp());

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newComment.getId())
                .toUri();

        return ResponseEntity.created(location).body(response);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countComments(@PathVariable Integer projectId) {
        return ResponseEntity.ok(commentService.countCommentsByProject(projectId));
    }

    @GetMapping("/coordinator/{coordinatorId}")
    public ResponseEntity<List<CommentResponse>> getCommentsByCoordinator(
            @PathVariable Integer projectId,
            @PathVariable Integer coordinatorId) {

        List<CommentResponse> comments = commentService
                .getCommentsByProjectAndCoordinator(projectId, coordinatorId).stream()
                .map(c -> new CommentResponse(
                        c.getCoordinatorName(),
                        c.getMessage(),
                        c.getTimestamp()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(comments);
    }
}