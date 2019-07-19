package app.news.agoda.com.domain.model;

import android.os.Parcel;
import android.os.Parcelable;

public class MultiMediumDomain implements Parcelable {

    private String url;
    private String format;
    private int height;
    private int width;
    private String type;
    private String subtype;
    private String caption;
    private String copyright;

    public MultiMediumDomain() {
    }

    protected MultiMediumDomain(Parcel in) {
        url = in.readString();
        format = in.readString();
        height = in.readInt();
        width = in.readInt();
        type = in.readString();
        subtype = in.readString();
        caption = in.readString();
        copyright = in.readString();
    }

    public static final Creator<MultiMediumDomain> CREATOR = new Creator<MultiMediumDomain>() {
        @Override
        public MultiMediumDomain createFromParcel(Parcel in) {
            return new MultiMediumDomain(in);
        }

        @Override
        public MultiMediumDomain[] newArray(int size) {
            return new MultiMediumDomain[size];
        }
    };

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(url);
        dest.writeString(format);
        dest.writeInt(height);
        dest.writeInt(width);
        dest.writeString(type);
        dest.writeString(subtype);
        dest.writeString(caption);
        dest.writeString(copyright);
    }
}
