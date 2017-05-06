package steven.apifacbook.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.google.gson.Gson;

import java.util.List;

import steven.apifacbook.R;
import steven.apifacbook.adapter.VideoAdapter;
import steven.apifacbook.model.video.Video;
import steven.apifacbook.model.video.VideoData;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FollowsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FollowsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FollowsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView rvVideos;
    private GridLayoutManager layoutManager;
    private List<VideoData> list;
    private VideoAdapter adapter;

    private MyImageFragment.OnFragmentInteractionListener mListener;

    public FollowsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FollowsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FollowsFragment newInstance(String param1, String param2) {
        FollowsFragment fragment = new FollowsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MyImageFragment.OnFragmentInteractionListener) {
            mListener = (MyImageFragment.OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onViewCreated(View view, Bundle sis) {
        super.onViewCreated(view, sis);
        iniUI(view);
    }

    private void iniUI(View view) {
        rvVideos = (RecyclerView) view.findViewById(R.id.rv_videos);
        layoutManager = new GridLayoutManager(getContext(), 2);
        rvVideos.setHasFixedSize(true);
        rvVideos.setLayoutManager(layoutManager);
        getMyVideo();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_follows, container, false);
    }

    private void getMyVideo() {
        new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "/me/videos/uploaded?fields=source,description,thumbnails{uri},length",
                null,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
                        String json = response.getRawResponse();
                        Log.d("LOG_VIDEO", json);
                        Gson gson = new Gson();
                        Video video = gson.fromJson(json, Video.class);

                        Log.d("TEST_VIDEO", video.getData().get(0).getDescription());
                        list = video.getData();
                        adapter = new VideoAdapter(getContext(), list);
                        rvVideos.setAdapter(adapter);
                    }
                }
        ).executeAsync();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public static void newInstance(int position) {
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
