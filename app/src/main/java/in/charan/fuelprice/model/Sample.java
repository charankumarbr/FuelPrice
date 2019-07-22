package in.charan.fuelprice.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Author: Charan Kumar
 * Date: 2019-07-17
 */
public class Sample implements Parcelable {

    private String name;

    protected Sample(Parcel in) {
        name = in.readString();
    }

    public static final Creator<Sample> CREATOR = new Creator<Sample>() {
        @Override
        public Sample createFromParcel(Parcel in) {
            return new Sample(in);
        }

        @Override
        public Sample[] newArray(int size) {
            return new Sample[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
    }
}
