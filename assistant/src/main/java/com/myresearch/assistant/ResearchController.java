package com.myresearch.assistant;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*") // Allow requests from any frontend
@RestController
@RequestMapping("/api/research")
@AllArgsConstructor
public class ResearchController {

    private final ResearchService researchService; // No @Autowired needed

    @PostMapping("/sum")
    public ResponseEntity<String> processContent(@RequestBody ResearchRequest req){ // ✅ FIXED CLASS NAME
        String result = researchService.processContent(req);
        return ResponseEntity.ok(result);
    }
    
}
