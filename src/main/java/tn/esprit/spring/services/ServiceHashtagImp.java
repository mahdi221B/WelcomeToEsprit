package tn.esprit.spring.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entity.Hashtag;
import tn.esprit.spring.repositories.HashtagRepository;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@AllArgsConstructor
public class ServiceHashtagImp implements IServiceHashtag {
    private final HashtagRepository hashtagRepository;
    @Override
    public void updateHashtags(String content) {
        Pattern pattern = Pattern.compile("#\\w+");
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            String hashtagText = matcher.group().substring(1); // remove '#'
            Hashtag hashtag = hashtagRepository.findByText(hashtagText);
            if (hashtag == null) {
                hashtag = new Hashtag();
                hashtag.setText(hashtagText);
            }
            hashtag.setCount(hashtag.getCount() + 1);
            hashtagRepository.save(hashtag);
        }
    }

    @Override
    public List<Hashtag> getTopHashtags(int limit) {
        return hashtagRepository.findTopByCount(limit);
    }
}