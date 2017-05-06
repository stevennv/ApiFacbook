package steven.apifacbook.request;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import steven.apifacbook.model.like.Like;
import steven.apifacbook.model.like.LikesData;
import steven.apifacbook.model.likedpage2.Datum;

/**
 * Created by TruongNV on 3/28/2017.
 */

public interface LoadMoreLikedPage {
    @GET()
    Call<Like> getMoreLikedPage(@Query("pretty") String prettyy,
                                @Query("limit") String limit,
                                @Query("after") String after);
}
