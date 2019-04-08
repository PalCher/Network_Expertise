package com.nexp.pavel.networkexpertise.mvp.presenter;

import android.annotation.SuppressLint;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.nexp.pavel.networkexpertise.mvp.model.entity.DataFrame;
import com.nexp.pavel.networkexpertise.mvp.model.repo.DocsRepo;
import com.nexp.pavel.networkexpertise.mvp.view.MainView;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView>  {

    @Inject
    DocsRepo docsRepo;

    @Inject
    DataFrame dataFrame;

    @Inject
    @Named("questions")
     String questions;

    @Inject
    @Named("materials")
     String text;

    public MainPresenter(){

    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        loadDoc();
        loadImg();

        //delete();
        //loadColDocs();

    }

//    @SuppressLint("CheckResult")
//    public void loadColDocs(){
//        docsRepo.getDocs().observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<QueryDocumentSnapshot>() {
//            @Override
//            public void accept(QueryDocumentSnapshot queryDocumentSnapshot) throws Exception {
//                switch (queryDocumentSnapshot.getId()){
//                    case questions: dataFrame.setQuestions(docsRepo.parseDoc(queryDocumentSnapshot.getData()));
//                    break;
//
//                }
//                getViewState().getDocs(queryDocumentSnapshot);
//            }
//        });
//    }

    @SuppressLint("CheckResult")
    public void loadDoc(){
        docsRepo.getQuestions()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<String>>() {
                               @Override
                               public void accept(List<String> qList) throws Exception {
                                   dataFrame.setQuestions(qList);
                                   getViewState().getQuestions(qList);
                               }
                           },
                        throwable -> Log.d("My Logs", throwable.toString()));
    }

    public void delete(){
        docsRepo.delete().subscribe();
    }

    public void FabButtonClick(Map<Integer, Integer> answers) {
        docsRepo.sendAnswers(answers).subscribe();
    }

    public void loadImg(){
        getViewState().showImage("https://firebasestorage.googleapis.com/v0/b/networkexpertise-85a5d.appspot.com/o/image_test.jpeg?alt=media&token=5d447616-3c00-46b4-a076-848c2cd18c89");
    }
}
