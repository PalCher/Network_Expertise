package com.nexp.pavel.networkexpertise.di.modules;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.nexp.pavel.networkexpertise.mvp.model.entity.DataFrame;
import com.nexp.pavel.networkexpertise.mvp.model.repo.DocsRepo;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApiModule {


    @Named("questionDoc")
    @Provides
    public DocumentReference questionsReference(FirebaseFirestore db, @Named("questions") String questions){

        DocumentReference docRef;
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setPersistenceEnabled(true)
                .build();
        db.setFirestoreSettings(settings);
        docRef= db.collection("expert_docs").document(questions);
        return docRef;
}

@Named("materialsDoc")
@Provides
public DocumentReference materialsReference(FirebaseFirestore db, @Named("materials") String materials){
    DocumentReference docRef;
    docRef= db.collection("expert_docs").document(materials);
    return docRef;
}

    @Provides
    public CollectionReference docsReference(FirebaseFirestore db){
        CollectionReference colRef;
        colRef= db.collection("expert_docs");
        return colRef;
    }

@Singleton
@Provides
public FirebaseFirestore firebaseFirestore(){
    FirebaseFirestore db = FirebaseFirestore.getInstance();
        return db;
}

@Named("questions")
@Provides
public String questionsPath(){
        return "questions";
}

@Named("materials")
@Provides
public String textPath(){
        return "materials";
}

    @Singleton
    @Provides
    public DocsRepo docsRepo (FirebaseFirestore db, @Named("questionDoc") DocumentReference questionsRef, @Named("materialsDoc") DocumentReference materialsRef, CollectionReference colRef){
        return new DocsRepo(db, questionsRef, materialsRef, colRef);
    }

    @Singleton
    @Provides
    public DataFrame createData(){
        DataFrame dataFrame = new DataFrame();
        return dataFrame;
    }

//    @Provides
//    public StorageReference getStorageRef(){
//        StorageReference storageReference = FirebaseStorage.getInstance().getReference();
//        storageReference.getPath()
//    }



}
