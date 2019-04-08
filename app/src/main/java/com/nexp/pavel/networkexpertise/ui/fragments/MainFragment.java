package com.nexp.pavel.networkexpertise.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.nexp.pavel.networkexpertise.App;
import com.nexp.pavel.networkexpertise.mvp.model.image.ImageLoader;
import com.nexp.pavel.networkexpertise.mvp.model.image.android.ImageLoaderGlide;
import com.nexp.pavel.networkexpertise.ui.MainActivity;
import com.nexp.pavel.networkexpertise.ui.MyCallback;
import com.nexp.pavel.networkexpertise.ui.adapters.CustomAdapter;
import com.nexp.pavel.networkexpertise.R;
import com.nexp.pavel.networkexpertise.mvp.model.entity.DataFrame;
import com.nexp.pavel.networkexpertise.mvp.presenter.MainPresenter;
import com.nexp.pavel.networkexpertise.mvp.view.MainView;

import java.util.List;

public class MainFragment extends MvpAppCompatFragment implements MainView, MainActivity.Ido {
    private CustomAdapter adapter;
    private LinearLayoutManager layoutManager;
    private RecyclerView recyclerView;
    private static final String TAG = "My";
    private TextView tvBody;
    private FloatingActionButton fab;
    private ImageView imgHeader;

    private ImageLoader<ImageView> imageLoader;

    @InjectPresenter
    MainPresenter presenter;

    public static MainFragment newInstance(){
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false );
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recycler_view);
        tvBody = view.findViewById(R.id.tv_body);
        fab = view.findViewById(R.id.fab);
        imgHeader = view.findViewById(R.id.img_header);
        init();

    }

    public void init(){

        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
        fab.setOnClickListener(new FabButtonListener());
        imageLoader = new ImageLoaderGlide();
    }


    @ProvidePresenter
    public MainPresenter provideMainPresenter() {
        MainPresenter presenter = new MainPresenter();
        App.getInstance().getAppComponent().inject(presenter);
        return presenter;
    }

    @Override
    public void getQuestions(List<String> data) {
        adapter = new CustomAdapter(data);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void getDocs(QueryDocumentSnapshot document) {
        Log.d(TAG, document.getId() + " => " + document.getData());

    }

    @Override
    public void showImage(String url) {
        imageLoader.loadInto(url, imgHeader);
    }


    private class FabButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            presenter.FabButtonClick(adapter.getAnswers());
        }
    }

    @Override
    public void doIt(String data) {
        tvBody.setText(data);
    }
}
