package com.nexp.pavel.networkexpertise.mvp.model.repo;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.Source;
import com.google.firestore.v1beta1.WriteResult;
import com.nexp.pavel.networkexpertise.mvp.model.entity.DataFrame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class DocsRepo {

    private  DocumentReference questionsRef;
    private  DocumentReference materialsRef;
    private CollectionReference colRef;
    private FirebaseFirestore db;
    private static final String TAG = "My";

    public DocsRepo(FirebaseFirestore db, DocumentReference questionsRef, DocumentReference materialsRef, CollectionReference colRef) {
        this.questionsRef = questionsRef;
        this.materialsRef = materialsRef;
        this.colRef = colRef;
        this.db = db;
    }

    public Single<List<String>> getQuestions(){


        return Single.create(new SingleOnSubscribe<DocumentSnapshot>() {
            @Override
            public void subscribe(SingleEmitter<DocumentSnapshot> emitter) throws Exception {

                questionsRef.get().addOnCompleteListener(task -> {
                    if (task.isSuccessful()){
                        DocumentSnapshot documentSnapshot = task.getResult();
                        if(documentSnapshot.exists()){
                            emitter.onSuccess(documentSnapshot);
                        }
                    } else {
                        emitter.onError(task.getException());

                    }
                });

            }
        }).subscribeOn(Schedulers.io()).map(documentSnapshot -> parseDoc(documentSnapshot.getData()));


    }

    public Observable<QueryDocumentSnapshot> getDocs(){
        return Observable.create(new ObservableOnSubscribe<QueryDocumentSnapshot>() {
            @Override
            public void subscribe(ObservableEmitter<QueryDocumentSnapshot> emitter) throws Exception {
                colRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                emitter.onNext(document);
                            }

                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }

                    }
                });
            }
        }).subscribeOn(Schedulers.io());
    }

    public Single<Void> delete(){
        return Single.create(new SingleOnSubscribe<Void>() {
            @Override
            public void subscribe(SingleEmitter<Void> emitter) throws Exception {

                db.disableNetwork().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        questionsRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d(TAG, "DocumentSnapshot successfully deleted!");

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Error deleting document", e);

                            }
                        });

                    }
                });


            }
        }).subscribeOn(Schedulers.io());
    }


    public Single<Void> sendAnswers(Map<Integer,Integer> map){

        return Single.create(new SingleOnSubscribe<Void>() {
            @Override
            public void subscribe(SingleEmitter<Void> emitter) throws Exception {

                Map<String, Object> docData = new HashMap<>();
                Map<String,Object> testmap = new HashMap<>();
                testmap.put("1", 2);
                testmap.put("0", 14);
                testmap.put("5", 56);
                //docData.put("answers", map);
                docData.put("test", testmap);
                db.collection("expert_docs").document("test").set(docData)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully written!");
                    }
                })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Error writing document", e);
                            }
                        });

            }
        }).subscribeOn(Schedulers.io());
    }

    public List<String> parseDoc(Map<String,Object> map){
        Set<String> mKeySet = map.keySet();

        List<String> list = new ArrayList<>();

        for (int i=1; i< mKeySet.size()+1; i++){

            list.add(map.get(Integer.toString(i)).toString());
        }

        return list;
    }
}
