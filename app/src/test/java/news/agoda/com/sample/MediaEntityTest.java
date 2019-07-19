package news.agoda.com.sample;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import app.news.agoda.com.domain.NewsFeedEntityDomainMapper;
import app.news.agoda.com.domain.model.MultiMediumDomain;
import app.news.agoda.com.domain.model.NewsEntity;

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
