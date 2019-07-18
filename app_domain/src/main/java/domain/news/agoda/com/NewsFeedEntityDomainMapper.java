package domain.news.agoda.com;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import domain.news.agoda.com.model.MultiMediumDomain;
import domain.news.agoda.com.model.MultiMediumEntity;
import domain.news.agoda.com.model.NewsEntity;
import domain.news.agoda.com.model.NewsResponseDomain;
import domain.news.agoda.com.model.Result;


public class NewsFeedEntityDomainMapper {

    public List<NewsResponseDomain> mapResults(List<Result> results) {
        List<NewsResponseDomain> newsResponseDomainList = new ArrayList<>();
        if (results != null && results.size() > 0) {
            for (Result resultEntity : results) {
                NewsResponseDomain newsResponseDomain = new NewsResponseDomain();
                newsResponseDomain.setTitle(resultEntity.getTitle());
                newsResponseDomain.setUrl(resultEntity.getUrl());
                newsResponseDomain.setAbstract(resultEntity.getAbstract());
                newsResponseDomain.setPublishedDate(resultEntity.getPublishedDate());
                List<MultiMediumDomain> mediumDomain = mapMedia(resultEntity.getMultimedia());
                newsResponseDomain.setMultimedia(mediumDomain);
                newsResponseDomainList.add(newsResponseDomain);
            }
        }
        return newsResponseDomainList;
    }

    public List<MultiMediumDomain> mapMedia(List<MultiMediumEntity> media) {
        List<MultiMediumDomain> mediumDomains = new ArrayList<>();
        if (media != null && media.size() > 0) {
            for (MultiMediumEntity mediaEntity : media) {
                MultiMediumDomain mediumDomain = new MultiMediumDomain();
                mediumDomain.setUrl(mediaEntity.getUrl());
                mediumDomain.setFormat(mediaEntity.getFormat());
                mediumDomain.setHeight(mediaEntity.getHeight());
                mediumDomain.setWidth(mediaEntity.getWidth());
                mediumDomain.setCaption(mediaEntity.getCaption());
                mediumDomain.setCopyright(mediaEntity.getCopyright());
                mediumDomains.add(mediumDomain);
            }
        }
        return mediumDomains;
    }

    public NewsEntity parseResponse(String content) {
        NewsEntity newsItem = new NewsEntity();
        if (content != null) {
            NewsEntity newsEntity;
            try {
                ObjectMapper mapper = new ObjectMapper();
                mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
                newsEntity = mapper.readValue(content, NewsEntity.class);
                newsItem = newsEntity;
            } catch (IOException e) {
                System.out.print("Fail to parse json string = " + e);
            }
        }
        return newsItem;
    }
}
