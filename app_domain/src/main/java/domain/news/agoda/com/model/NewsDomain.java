package domain.news.agoda.com.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class NewsDomain implements Parcelable {

    private String status;
    private String copyright;
    private String section;
    private String lastUpdated;
    private int numResults;
    private List<NewsResponseDomain> results;

    public NewsDomain() {
    }

    protected NewsDomain(Parcel in) {
        status = in.readString();
        copyright = in.readString();
        section = in.readString();
        lastUpdated = in.readString();
        numResults = in.readInt();
        results = in.createTypedArrayList(NewsResponseDomain.CREATOR);
    }

    public static final Creator<NewsDomain> CREATOR = new Creator<NewsDomain>() {
        @Override
        public NewsDomain createFromParcel(Parcel in) {
            return new NewsDomain(in);
        }

        @Override
        public NewsDomain[] newArray(int size) {
            return new NewsDomain[size];
        }
    };

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public int getNumResults() {
        return numResults;
    }

    public void setNumResults(int numResults) {
        this.numResults = numResults;
    }

    public List<NewsResponseDomain> getResults() {
        return results;
    }

    public void setResults(List<NewsResponseDomain> results) {
        this.results = results;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(status);
        dest.writeString(copyright);
        dest.writeString(section);
        dest.writeString(lastUpdated);
        dest.writeInt(numResults);
        dest.writeTypedList(results);
    }
}
