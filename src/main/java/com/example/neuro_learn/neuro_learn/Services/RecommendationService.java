package com.example.neuro_learn.neuro_learn.Services;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RecommendationService {

    private final RestTemplate restTemplate;
    private final GeminiRecommendationAgent geminiAgent;
    private final String youtubeApi;

    public RecommendationService(
            RestTemplate restTemplate,
            GeminiRecommendationAgent geminiAgent,
            @Value("${youtube.api.key}") String youtubeApi) {
        this.restTemplate = restTemplate;
        this.geminiAgent = geminiAgent;
        this.youtubeApi = youtubeApi;
    }

    // Fetch AI-powered text recommendations
    public String fetchTextRecommendations(String topic) {
        topic = topic.trim().replace("\"", "");
        StringBuilder result = new StringBuilder();
        try {
            result.append("🧠 **AI-Powered Suggestions:**\n");
            result.append(geminiAgent.generateRecommendations(topic)).append("\n");
        } catch (Exception e) {
            result.append("⚠️ Error while fetching recommendations: ").append(e.getMessage());
        }
        return result.toString();
    }

    // Fetch YouTube video recommendations
    public String fetchYoutubeRecommendations(String topic) {
        String url = "https://www.googleapis.com/youtube/v3/search?part=snippet&q=" +
                topic + "+course+tutorial&maxResults=5&type=video&key=" + youtubeApi;

        String response = restTemplate.getForObject(url, String.class);
        JSONObject json = new JSONObject(response);
        JSONArray items = json.getJSONArray("items");

        StringBuilder youtubeResults = new StringBuilder("🎥 **YouTube Video Recommendations:**\n");

        for (int i = 0; i < items.length(); i++) {
            JSONObject item = items.getJSONObject(i);
            String title = item.getJSONObject("snippet").getString("title");

            JSONObject idObj = item.optJSONObject("id");
            if (idObj != null && idObj.has("videoId")) {
                String videoId = idObj.getString("videoId");
                String link = "https://www.youtube.com/watch?v=" + videoId;
                youtubeResults.append("• ").append(title).append(" → ").append(link).append("\n");
            }
        }

        return youtubeResults.toString();
    }

    // Fetch free course recommendations
    public String fetchCourseRecommendations(String topic) {
        String encodedTopic = topic.replace(" ", "%20");

        StringBuilder coursesResults = new StringBuilder("📚 **Free Course Recommendations:**\n");

        coursesResults.append("• Coursera: https://www.coursera.org/courses?query=")
                .append(encodedTopic).append("\n");

        coursesResults.append("• Udemy: https://www.udemy.com/courses/search/?q=")
                .append(encodedTopic).append("&price=price-free\n");

        coursesResults.append("• edX: https://www.edx.org/search?tab=course&query=")
                .append(encodedTopic).append("\n");

        coursesResults.append("• Khan Academy: https://www.khanacademy.org/search?page_search_query=")
                .append(encodedTopic).append("\n");

        return coursesResults.toString();
    }
}
