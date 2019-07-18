package domain.news.agoda.com.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class NewsResponseDomain implements Parcelable {

    private String section;
    private String subsection;
    private String title;
    private String _abstract;
    private String url;
    private String byline;
    private String itemType;
    private String updatedDate;
    private String createdDate;
    private String publishedDate;
    private String materialTypeFacet;
    private String kicker;
    private List<String> desFacet;
    private String orgFacet;
    private String perFacet;
    private List<String> geoFacet;
    private List<MultiMediumDomain> multimedia;

    public NewsResponseDomain() {
    }

    protected NewsResponseDomain(Parcel in) {
        section = in.readString();
        subsection = in.readString();
        title = in.readString();
        _abstract = in.readString();
        url = in.readString();
        byline = in.readString();
        itemType = in.readString();
        updatedDate = in.readString();
        createdDate = in.readString();
        publishedDate = in.readString();
        materialTypeFacet = in.readString();
        kicker = in.readString();
        desFacet = in.createStringArrayList();
        orgFacet = in.readString();
        perFacet = in.readString();
        geoFacet = in.createStringArrayList();
        multimedia = in.createTypedArrayList(MultiMediumDomain.CREATOR);
    }

    public static final Creator<NewsResponseDomain> CREATOR = new Creator<NewsResponseDomain>() {
        @Override
        public NewsResponseDomain createFromParcel(Parcel in) {
            return new NewsResponseDomain(in);
        }

        @Override
        public NewsResponseDomain[] newArray(int size) {
            return new NewsResponseDomain[size];
        }
    };

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getSubsection() {
        return subsection;
    }

    public void setSubsection(String subsection) {
        this.subsection = subsection;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAbstract() {
        return _abstract;
    }

    public void setAbstract(String _abstract) {
        this._abstract = _abstract;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getByline() {
        return byline;
    }

    public void setByline(String byline) {
        this.byline = byline;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getMaterialTypeFacet() {
        return materialTypeFacet;
    }

    public void setMaterialTypeFacet(String materialTypeFacet) {
        this.materialTypeFacet = materialTypeFacet;
    }

    public String getKicker() {
        return kicker;
    }

    public void setKicker(String kicker) {
        this.kicker = kicker;
    }

    public List<String> getDesFacet() {
        return desFacet;
    }

    public void setDesFacet(List<String> desFacet) {
        this.desFacet = desFacet;
    }

    public String getOrgFacet() {
        return orgFacet;
    }

    public void setOrgFacet(String orgFacet) {
        this.orgFacet = orgFacet;
    }

    public String getPerFacet() {
        return perFacet;
    }

    public void setPerFacet(String perFacet) {
        this.perFacet = perFacet;
    }

    public List<String> getGeoFacet() {
        return geoFacet;
    }

    public void setGeoFacet(List<String> geoFacet) {
        this.geoFacet = geoFacet;
    }

    public List<MultiMediumDomain> getMultimedia() {
        return multimedia;
    }

    public void setMultimedia(List<MultiMediumDomain> multimedia) {
        this.multimedia = multimedia;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(section);
        dest.writeString(subsection);
        dest.writeString(title);
        dest.writeString(_abstract);
        dest.writeString(url);
        dest.writeString(byline);
        dest.writeString(itemType);
        dest.writeString(updatedDate);
        dest.writeString(createdDate);
        dest.writeString(publishedDate);
        dest.writeString(materialTypeFacet);
        dest.writeString(kicker);
        dest.writeStringList(desFacet);
        dest.writeString(orgFacet);
        dest.writeString(perFacet);
        dest.writeStringList(geoFacet);
        dest.writeTypedList(multimedia);
    }
}
