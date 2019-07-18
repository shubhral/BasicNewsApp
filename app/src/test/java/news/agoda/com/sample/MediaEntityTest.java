package news.agoda.com.sample;

import domain.news.agoda.com.NewsFeedEntityDomainMapper;
import domain.news.agoda.com.model.MultiMediumDomain;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import domain.news.agoda.com.model.NewsEntity;

public class MediaEntityTest {

    private NewsFeedEntityDomainMapper newsFeedEntityDomainMapper;

    @Before
    public void setUp() {
        newsFeedEntityDomainMapper = new NewsFeedEntityDomainMapper();
    }

    @Test
    public void validateImageUrl() {
        NewsEntity newsEntity = newsFeedEntityDomainMapper.parseResponse(MockResponse.newsFeedResponse);
        List<MultiMediumDomain> multiMediumDomains = newsFeedEntityDomainMapper.mapMedia(newsEntity.getResults().get(0).getMultimedia());
        Assert.assertTrue(multiMediumDomains.get(0).getUrl().equals(newsEntity.getResults().get(0).getMultimedia().get(0).getUrl()));
    }

    @Test
    public void validateMultiMediaSize() {
        NewsEntity newsEntity = newsFeedEntityDomainMapper.parseResponse(MockResponse.newsFeedResponseEmptyProperties);
        List<MultiMediumDomain> multiMediumDomains = newsFeedEntityDomainMapper.mapMedia(newsEntity.getResults().get(0).getMultimedia());
        Assert.assertEquals(multiMediumDomains.size(), 4);
    }

    @Test
    public void validateEmptyImageUrl() {
        NewsEntity newsEntity = newsFeedEntityDomainMapper.parseResponse(MockResponse.newsFeedResponseEmptyProperties);
        List<MultiMediumDomain> multiMediumDomains = newsFeedEntityDomainMapper.mapMedia(newsEntity.getResults().get(0).getMultimedia());
        Assert.assertTrue(multiMediumDomains.get(0).getUrl().equals(""));
    }

    @Test
    public void validateEmptyMultimedia() {
        NewsEntity newsEntity = newsFeedEntityDomainMapper.parseResponse(MockResponse.newsFeedResponseEmptyProperties);
        List<MultiMediumDomain> multiMediumDomains = newsFeedEntityDomainMapper.mapMedia(newsEntity.getResults().get(3).getMultimedia());
        Assert.assertEquals(multiMediumDomains.size(), 0);
    }

}
