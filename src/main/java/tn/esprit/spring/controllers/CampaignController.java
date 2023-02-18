package tn.esprit.spring.controllers;

import com.google.ads.googleads.lib.GoogleAdsClient;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/campaigns")
public class CampaignController {
    private final GoogleAdsClient googleAdsClient;

    @GetMapping("/{campaignId}")
    public Campaign getCampaign(@PathVariable Long campaignId) {
        try (GoogleAdsServiceClient googleAdsServiceClient = googleAdsClient.getLatestVersion().createGoogleAdsServiceClient()) {
            String query = "SELECT campaign.id, campaign.name, campaign.status FROM campaign WHERE campaign.id = " + campaignId;
            SearchPagedResponse response = googleAdsServiceClient.search(query);
            return response.getPage().getResponse().getResults().stream()
                    .map(row -> Campaign.builder()
                            .id(row.getCampaign().getId().getValue())
                            .name(row.getCampaign().getName().getValue())
                            .status(row.getCampaign().getStatus().toString())
                            .build())
                    .findFirst()
                    .orElse(null);
        } catch (Exception e) {
            throw new RuntimeException("Failed to get campaign: " + e.getMessage(), e);
        }
    }
}