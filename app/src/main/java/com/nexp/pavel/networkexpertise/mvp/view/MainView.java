package com.nexp.pavel.networkexpertise.mvp.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.nexp.pavel.networkexpertise.mvp.model.entity.DataFrame;

import java.util.List;

@StateStrategyType(AddToEndSingleStrategy.class)

public interface MainView extends MvpView  {

    void getQuestions(List<String> data);

    void getDocs(QueryDocumentSnapshot document);

    void showImage(String url);

}
