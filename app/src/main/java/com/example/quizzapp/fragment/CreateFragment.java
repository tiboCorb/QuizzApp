package com.example.quizzapp.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import android.widget.TextView;
import android.widget.Toast;


import com.example.quizzapp.R;
import com.example.quizzapp.model.Option;
import com.example.quizzapp.model.Question;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.UUID;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import io.realm.Realm;
import io.realm.RealmList;


public class CreateFragment extends Fragment {

    Realm realm;

    EditText question;
    EditText aText;
    EditText bText;
    EditText cText;
    EditText dText;

    RadioButton aRadio;
    RadioButton bRadio;
    RadioButton cRadio;
    RadioButton dRadio;

    RadioGroup radioGroup;
    int currentQuestion = 1;
    TextView questionNumber;
    RealmList<Question> questions;
    String selectedOption = "";
    SendQuestions sendQuestions;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        question = (EditText) view.findViewById(R.id.questionView);
        aText = (EditText) view.findViewById(R.id.aText);
        bText = (EditText) view.findViewById(R.id.bText);
        cText = (EditText) view.findViewById(R.id.cText);
        dText = (EditText) view.findViewById(R.id.dText);
        questionNumber = (TextView) view.findViewById(R.id.questionNumber);
        aRadio = (RadioButton) view.findViewById(R.id.aRadio);

        bRadio = (RadioButton) view.findViewById(R.id.bRadio);
        cRadio = (RadioButton) view.findViewById(R.id.cRadio);
        dRadio = (RadioButton) view.findViewById(R.id.dRadio);
        selectedOption = "";
        currentQuestion = 1;
       realm = Realm.getDefaultInstance();
        setListeners();
        questions = new RealmList<>();
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.nextfab);
        final FloatingActionButton fabSave = (FloatingActionButton) view.findViewById(R.id.save);
        fab.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View view) {
                boolean cont = getEnteredQuestionsValue();
                if (cont == true) {
                    currentQuestion++;
                    Toast.makeText(view.getContext()  , "QUESTION " + currentQuestion, Toast.LENGTH_SHORT).show();
                    questionNumber.setText(String.valueOf(currentQuestion));
                    fabSave.setVisibility(View.VISIBLE);
                    clearAllData();
                }




            }
        });

        fabSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendQuestions.sendArray(questions);

            }
        });

    }

    private void clearAllData() {
        aRadio.setChecked(false);
        bRadio.setChecked(false);
        cRadio.setChecked(false);
        dRadio.setChecked(false);

        aText.setText(null);
        bText.setText(null);
        cText.setText(null);
        dText.setText(null);

        question.setText(null);

        selectedOption = "";

    }

    private boolean getEnteredQuestionsValue() {

        boolean cont = false;
        if (TextUtils.isEmpty(question.getText().toString().trim())) {
            question.setError("Please fill in a question");
        } else if (TextUtils.isEmpty(aText.getText().toString().trim())) {
            aText.setError("Please fill in option A");
        } else if (TextUtils.isEmpty(bText.getText().toString().trim())) {
            bText.setError("Please fill in option B");
        } else if (TextUtils.isEmpty(cText.getText().toString().trim())) {
            cText.setError("Please fill in option C");
        } else if (TextUtils.isEmpty(dText.getText().toString().trim())) {
            dText.setError("Please fill in option D");
        } else if (selectedOption.equals("")) {
            Toast.makeText(this.getView().getContext(), "Please select the correct answer", Toast.LENGTH_SHORT).show();
        } else {
            realm.beginTransaction();

            Question quest =  realm.createObject(Question.class,UUID.randomUUID().toString());
            Option optionA = realm.createObject(Option.class,UUID.randomUUID().toString());
            Option optionB = realm.createObject(Option.class,UUID.randomUUID().toString());
            Option optionC = realm.createObject(Option.class,UUID.randomUUID().toString());
            Option optionD = realm.createObject(Option.class,UUID.randomUUID().toString());
            RealmList<Option> options = new RealmList();

            quest.setTextQuestion(question.getText().toString());
            optionA.setTextAnswer(aText.getText().toString()) ;
            optionB.setTextAnswer(bText.getText().toString()) ;
            optionC.setTextAnswer(cText.getText().toString()) ;
            optionD.setTextAnswer(dText.getText().toString()) ;

            quest.setOptionA(optionA);
            quest.setOptionB(optionB);
            quest.setOptionC(optionC);
            quest.setOptionD(optionD);


            quest.setAnswer(selectedOption);
            realm.commitTransaction();
            questions.add(quest);
            cont = true;

        }


        return cont;
    }

    private void setListeners() {
        aRadio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedOption = "A";
                bRadio.setChecked(false);
                cRadio.setChecked(false);
                dRadio.setChecked(false);
            }
        });
        bRadio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedOption = "B";
                aRadio.setChecked(false);
                cRadio.setChecked(false);
                dRadio.setChecked(false);
            }
        });
        cRadio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedOption = "C";
                bRadio.setChecked(false);
                aRadio.setChecked(false);
                dRadio.setChecked(false);
            }
        });
        dRadio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedOption = "D";
                bRadio.setChecked(false);
                cRadio.setChecked(false);
                aRadio.setChecked(false);
            }
        });

    }


    public interface SendQuestions {
        void sendArray(RealmList<Question> questions);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            sendQuestions = (SendQuestions) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException("Error in retrieving data. Please try again");
        }
    }


}
