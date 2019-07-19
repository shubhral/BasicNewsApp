package app.news.agoda.com.util;

import android.net.Uri;

import java.util.List;

import app.news.agoda.com.domain.model.MultiMediumDomain;
import app.news.agoda.com.domain.model.NewsResponseDomain;
import app.news.agoda.com.view.BundleKeys;

public class AppUtils {

    public static String getImageUrlFromResult(NewsResponseDomain result) {
        String url = null;
        if (result != null) {
            List<MultiMediumDomain> media = result.getMultimedia();
            if (media != null && !media.isEmpty()) {
                for (MultiMediumDomain multiMediumDomain : media) {
                    if (multiMediumDomain.getFormat().equals(BundleKeys.TITLE_IMAGE_FORMAT_NORMAL)) {
                        url = multiMediumDomain.getUrl();
                    }
                }
            }
        }
        return url;
    }

    public static Uri getImageURI(List<MultiMediumDomain> multiMediumDomains) {
        if (multiMediumDomains != null && multiMediumDomains.size() > 0) {
            String thumbnailURL = multiMediumDomains.get(0).getUrl();
            return Uri.parse(thumbnailURL);
        }
        return null;
    }
}
