package com.nexp.pavel.networkexpertise.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.nexp.pavel.networkexpertise.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

private List<String> list;
private Map<Integer, Integer> answers;
private static final String mLog = "My";

    public CustomAdapter(List<String> list) {
        this.list = list;
        answers = new HashMap<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder( ViewGroup viewGroup, int viewType) {
        View view;
        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_question, viewGroup, false);
        return new QuestionViewHolder(view);

    }

    @Override
    public void onBindViewHolder( RecyclerView.ViewHolder viewHolder, int position) {

        ((QuestionViewHolder)viewHolder).question.setText(list.get(position));
        ((QuestionViewHolder) viewHolder).radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){

                    case R.id.radioButton_0:
                        answers.put(position + 1, 0);
                        Log.d(mLog, "Вопрос № " + position + " :" + answers.get(position + 1).toString());
                        break;

                    case R.id.radioButton_1:
                        answers.put(position + 1, 1);
                        Log.d(mLog, "Вопрос № " + position + " :" + answers.get(position + 1).toString());
                        break;

                    case R.id.radioButton_2:
                        answers.put(position + 1, 2);
                        Log.d(mLog, "Вопрос № " + position + " :" + answers.get(position + 1).toString());
                        break;

                    default:
                            break;
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        if (list == null){
            return 0;

        }
        return list.size();
    }


    public Map<Integer, Integer> getAnswers() {
        return answers;
    }


    public static class QuestionViewHolder extends RecyclerView.ViewHolder{
        TextView question;
        RadioGroup radioGroup;

        public QuestionViewHolder(View itemView) {
            super(itemView);
            question = itemView.findViewById(R.id.tv_question);
            radioGroup = itemView.findViewById(R.id.radioGroup_answers);

        }
    }
}
