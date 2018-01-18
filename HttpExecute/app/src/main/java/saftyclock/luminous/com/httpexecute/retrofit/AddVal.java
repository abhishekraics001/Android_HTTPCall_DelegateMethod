package saftyclock.luminous.com.httpexecute.retrofit;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mappsdeveloper on 18-01-2018.
 */

public class AddVal {

    @SerializedName("F")
    public String F;
    @SerializedName("S")
    public String S;

    public AddVal(String name, String job) {
        this.F = name;
        this.S = job;
    }
}
