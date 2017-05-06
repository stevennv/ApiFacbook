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
import steven.apifacbook.adapter.AlbumAdapter;
import steven.apifacbook.adapter.WallAdapter;
import steven.apifacbook.model.album.Album;
import steven.apifacbook.model.album.PictureData;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MyImageFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MyImageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyImageFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RecyclerView rvListAlbum;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private static final String ARG_POSITION = "position";
    private WallAdapter adapter;
    private List<PictureData> list;
    private String mParam2;
    private GridLayoutManager layoutManager;
    private OnFragmentInteractionListener mListener;

    public MyImageFragment() {
        // Required empty public constructor
    }

    public static MyImageFragment newInstance(int position) {
        MyImageFragment f = new MyImageFragment();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        f.setArguments(b);
        return f;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyImageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MyImageFragment newInstance(String param1, String param2) {
        MyImageFragment fragment = new MyImageFragment();
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

    @Override
    public void onViewCreated(View view, Bundle sis) {
        super.onViewCreated(view, sis);
        iniUI(view);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_image, container, false);

    }

    private void iniUI(View view) {
        rvListAlbum = (RecyclerView) view.findViewById(R.id.rv_my_album);
        layoutManager = new GridLayoutManager(getContext(), 2);
        rvListAlbum.setHasFixedSize(true);
        rvListAlbum.setLayoutManager(layoutManager);
        getListAlbum();
    }

    private void getListAlbum() {
        new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "me/albums?fields=name,picture",
                null,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
            /* handle the result */
                        String json = response.getRawResponse();
                        Log.e("TESST_JSON", json);
                        Gson gson = new Gson();
                        Album album = gson.fromJson(json, Album.class);
                        list = album.getData();
                        adapter = new WallAdapter(getContext(), list);
                        rvListAlbum.setAdapter(adapter);
                    }
                }
        ).executeAsync();
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
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
