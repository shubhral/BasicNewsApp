package news.agoda.com.sample;

import domain.news.agoda.com.NewsFeedEntityDomainMapper;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import domain.news.agoda.com.model.NewsEntity;

public class NewsEntityTest {

    private NewsFeedEntityDomainMapper newsFeedEntityDomainMapper;
    private NewsEntity parsedNewsEntity;

    private String storyUrl = "http://www.nytimes.com/2015/08/18/business/work-policies-may-be-kinder-but-brutal-competition-isnt.html";

    @Before
    public void setUp() {
        newsFeedEntityDomainMapper = new NewsFeedEntityDomainMapper();
        parsedNewsEntity = newsFeedEntityDomainMapper.parseResponse(MockResponse.newsFeedResponse);
    }

    @Test
    public void validateStoryUrl() {
        Assert.assertEquals(parsedNewsEntity.getResults().get(0).getUrl(), storyUrl);
    }

    @Test
    public void validateStatus() {
        Assert.assertEquals(parsedNewsEntity.getStatus(), "OK");
    }

    @Test
    public void validateSResultSize() {
        Assert.assertEquals(parsedNewsEntity.getResults().size(), 4);
    }

    @Test
    public void validateSection() {
        Assert.assertEquals(parsedNewsEntity.getSection(), "technology");
    }

    @Test
    public void validateLastUpdated() {
        Assert.assertEquals(parsedNewsEntity.getLastUpdated(), "2015-08-18T10:15:06-05:00");
    }

    @Test
    public void validateEmptySection() {
        parsedNewsEntity = newsFeedEntityDomainMapper.parseResponse(MockResponse.newsFeedResponseEmptyProperties);
        Assert.assertEquals(parsedNewsEntity.getSection(), "");
    }

    @Test
    public void validateEmptyLastUpdated() {
        parsedNewsEntity = newsFeedEntityDomainMapper.parseResponse(MockResponse.newsFeedResponseEmptyProperties);
        Assert.assertEquals(parsedNewsEntity.getLastUpdated(), "");
    }
}
