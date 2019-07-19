package news.agoda.com.sample;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import app.news.agoda.com.domain.NewsFeedEntityDomainMapper;
import app.news.agoda.com.domain.model.NewsEntity;

public class MediaParserTest {

    private NewsFeedEntityDomainMapper newsFeedEntityDomainMapper;
    private NewsEntity parsedNewsEntity;

    @Before
    public void setUp() {
        newsFeedEntityDomainMapper = new NewsFeedEntityDomainMapper();
        parsedNewsEntity = newsFeedEntityDomainMapper.parseResponse(MockResponse.newsFeedResponse);
    }

    @Test
    public void testParsedResponseNotNull() {
        Assert.assertNotNull(parsedNewsEntity);
    }

    @Test
    public void testParsedResponseNull() {
        parsedNewsEntity = newsFeedEntityDomainMapper.parseResponse(null);
        Assert.assertNull(parsedNewsEntity.getStatus());
    }

    @Test
    public void testParsedResponse() {
        Assert.assertTrue(parsedNewsEntity.getNumResults() == 4);
    }

}
