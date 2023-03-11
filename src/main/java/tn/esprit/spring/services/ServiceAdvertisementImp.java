package tn.esprit.spring.services;

import com.google.ads.googleads.lib.GoogleAdsClient;
import com.google.ads.googleads.v13.resources.Campaign;
import com.google.ads.googleads.v13.services.GoogleAdsRow;
import com.google.ads.googleads.v13.services.GoogleAdsServiceClient;
import com.google.ads.googleads.v13.services.SearchGoogleAdsStreamRequest;
import com.google.ads.googleads.v13.services.SearchGoogleAdsStreamResponse;
import com.google.api.gax.rpc.ServerStream;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entity.Advertisement;
import tn.esprit.spring.repositories.AdvertisementRepository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@AllArgsConstructor
public class ServiceAdvertisementImp implements IServiceAdvertisement, Serializable {
    private final AdvertisementRepository advertisementRepository;
    private final GoogleAdsClient googleAdsClient;

    public Map<String,String> getCampaigns() {
        Long customerId = Long.parseLong("4129888592");
        Map<String, String> campaigns = new HashMap<>();

        try (GoogleAdsServiceClient googleAdsServiceClient =
                     googleAdsClient.getLatestVersion().createGoogleAdsServiceClient()) {
            String query = "SELECT campaign.id, campaign.name FROM campaign ORDER BY campaign.id";
            // Constructs the SearchGoogleAdsStreamRequest.
            SearchGoogleAdsStreamRequest request =
                    SearchGoogleAdsStreamRequest.newBuilder()
                            .setCustomerId(Long.toString(customerId))
                            .setQuery(query)
                            .build();
            // Creates and issues a search Google Ads stream request that will retrieve all campaigns.
            ServerStream<SearchGoogleAdsStreamResponse> stream = googleAdsServiceClient
                    .searchStreamCallable()
                    .call(request);
            // Iterates through and adds all of the campaigns to the list.
            for (SearchGoogleAdsStreamResponse response : stream) {
                for (GoogleAdsRow googleAdsRow : response.getResultsList()) {
                    campaigns.put(String.valueOf(googleAdsRow.getCampaign().getId()), googleAdsRow.getCampaign().getName());
                    System.out.printf(
                            "Campaign with ID %d and name '%s' was found.%n",
                            googleAdsRow.getCampaign().getId(), googleAdsRow.getCampaign().getName());
                }
            }
        return campaigns;
        }
    }

    @Override
    public List<Advertisement> retrieveAllAdvertisements() {
        return advertisementRepository.findAll();
    }
    @Override
    public void deleteAdvertisement(Integer id) {
        advertisementRepository.delete(advertisementRepository.findById(id).get());
    }

    @Override
    public Advertisement retrieveAdvertisementById(Integer id) {
        return advertisementRepository.findById(id).get();
    }

    @Override
    public Advertisement addAdvertisement(Advertisement advertisement) {
        return advertisementRepository.save(advertisement);
    }

    @Override
    public Advertisement updateAdvertisement(Advertisement advertisement, Integer id) {
        advertisement.setId(id);
        return advertisementRepository.save(advertisement);
    }
}
