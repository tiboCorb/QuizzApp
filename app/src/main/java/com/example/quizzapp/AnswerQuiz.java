package com.example.quizzapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import io.realm.Realm;
import io.realm.RealmResults;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizzapp.model.Question;
import com.example.quizzapp.model.Quizz;
import com.example.quizzapp.model.QuizzUser;
import com.example.quizzapp.model.Result;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.mukesh.MarkdownView;

import java.util.ArrayList;
import java.util.UUID;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AnswerQuiz#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AnswerQuiz extends Fragment {


    MarkdownView question;

    MarkdownView aText;
    MarkdownView bText;
    MarkdownView cText;
    MarkdownView dText;
    FloatingActionButton fab;
    ScrollView scroll;
    RadioButton aRadio;
    RadioButton bRadio;
    RadioButton cRadio;
    private RadioButton dRadio;
    private int currentQuestion;
    private int questionCorrect;
    private TextView questionNumber;
    private ArrayList<Question> questions;
    private String selectedOption = "";
    private Quizz quizz;


    public static AnswerQuiz newInstance() {
        return new AnswerQuiz();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        String quizzId = AnswerQuizArgs.fromBundle(getArguments()).getQuizzId();
        Realm realm  = Realm.getDefaultInstance();
        questions = new ArrayList<>();

         quizz = realm.where(Quizz.class).equalTo("quizzId", quizzId).findFirst();
        questions.addAll(realm.copyFromRealm(quizz.getQuestions()));

        return inflater.inflate(R.layout.fragment_answer_quiz, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        questionCorrect = 0;
        currentQuestion = 1;
        question = (MarkdownView) view.findViewById(R.id.textView4);
        aText = (MarkdownView) view.findViewById(R.id.aText);
        bText = (MarkdownView) view.findViewById(R.id.bText);
        cText = (MarkdownView) view.findViewById(R.id.cText);
        dText = (MarkdownView) view.findViewById(R.id.dText);
        scroll = (ScrollView) view.findViewById(R.id.scrollView2);
        questionNumber = (TextView) view.findViewById(R.id.questionNumber);
        aRadio = (RadioButton) view.findViewById(R.id.aRadio);

        bRadio = (RadioButton) view.findViewById(R.id.bRadio);
        cRadio = (RadioButton) view.findViewById(R.id.cRadio);
        dRadio = (RadioButton) view.findViewById(R.id.dRadio);
        fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                if (selectedOption.equals("")) {
                    Toast.makeText(view.getContext(),"Choisisez une réponse pour continuer ",Toast.LENGTH_SHORT).show();
                } else {
                    currentQuestion++;

                    if (selectedOption.equals(corretoption)) {
                        fab.setClickable(false);

                        Snackbar.make(scroll, "CORRECT", Snackbar.LENGTH_INDEFINITE)
                                .setAction("Continue", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        questionCorrect++;
                                        clearPreviousselections();
                                        if (apocalypse == false)
                                            startThis();
                                        else {
                                            AlertDialog.Builder score = new AlertDialog.Builder(view.getContext()).setCancelable(false);
                                            score.setTitle("QUIZ Terminé!!!");
                                            score.setMessage(" " + questionCorrect + "bonnes réponses sur" + questions.size() );
                                            score.setIcon(android.R.drawable.ic_dialog_info);
                                            score.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                  saveAndQuite();
                                                }
                                            });
                                            score.setNeutralButton("SHARE RESULT", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    saveAndQuite();

                                                }
                                            });
                                            score.show();
                                            score.setCancelable(false);
                                            score.setOnCancelListener(new DialogInterface.OnCancelListener() {
                                                @Override
                                                public void onCancel(DialogInterface dialog) {
                                                    saveAndQuite();
                                                }
                                            });
                                            score.setOnKeyListener(new DialogInterface.OnKeyListener() {
                                                @Override
                                                public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                                                    saveAndQuite();
                                                    return true;
                                                }
                                            });
                                            score.setOnDismissListener(new DialogInterface.OnDismissListener() {
                                                @Override
                                                public void onDismiss(DialogInterface dialog) {
                                                    saveAndQuite();
                                                }
                                            });


                                        }
                                        fab.setClickable(true);
                                    }
                                }).show();

                    } else {
                        fab.setClickable(false);
                        Snackbar.make(scroll, "WRONG, CORRECT ANSWER :" + corretoption, Snackbar.LENGTH_INDEFINITE)
                                .setAction("Continue", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        clearPreviousselections();
                                        if (apocalypse == false)
                                            startThis();
                                        else {
                                            AlertDialog.Builder score = new AlertDialog.Builder(view.getContext()).setCancelable(false);
                                            score.setTitle("QUIZ terminé!!!");
                                            score.setMessage(" " + questionCorrect + " bonnes réponses sur  " + questions.size() + " questions");
                                            score.setIcon(android.R.drawable.ic_dialog_info);
                                            score.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    saveAndQuite();
                                                }
                                            });
                                            score.setNeutralButton("partager le résultat", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    saveAndQuite();
                                                }
                                            });


                                            score.show();
                                            score.setCancelable(false);
                                            score.setOnCancelListener(new DialogInterface.OnCancelListener() {
                                                @Override
                                                public void onCancel(DialogInterface dialog) {
                                                    saveAndQuite();
                                                }
                                            });
                                            score.setOnKeyListener(new DialogInterface.OnKeyListener() {
                                                @Override
                                                public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                                                    saveAndQuite();
                                                    return true;
                                                }
                                            });



                                        }
                                        fab.setClickable(true);
                                    }
                                }).show();

                    }


                }
            }

        });
        setListeners();
        setListenersForTexts();
        startThis();
    }

    String corretoption ="";
    boolean apocalypse = false;
    private void startThis() {
        int arraycount = currentQuestion-1;
        if(currentQuestion==questions.size()){
            // fab.setImageIcon(Icon.createWithResource(getApplicationContext(),R.mipmap.ic_finished));
            apocalypse = true;
        }
        try {
            questionNumber.setText(""+currentQuestion);
            question.setMarkDownText(questions.get(arraycount).getTextQuestion());
            aText.setMarkDownText(questions.get(arraycount).getOptionA().getTextAnswer());
            bText.setMarkDownText(questions.get(arraycount).getOptionB().getTextAnswer());
            cText.setMarkDownText(questions.get(arraycount).getOptionC().getTextAnswer());
            dText.setMarkDownText(questions.get(arraycount).getOptionD().getTextAnswer());
            corretoption = questions.get(arraycount).getAnswer();
        }catch (IndexOutOfBoundsException e){
            apocalypse = true;
           //todo leave

        }
    }

    private void clearPreviousselections() {
        aRadio.setChecked(false);

        selectedOption = "";
        bRadio.setChecked(false);
        cRadio.setChecked(false);
        dRadio.setChecked(false);
    }

    private void setListenersForTexts() {
        aText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aRadio.setChecked(true);
                selectedOption = "A";
                bRadio.setChecked(false);
                cRadio.setChecked(false);
                dRadio.setChecked(false);
            }
        });
        bText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bRadio.setChecked(true);
                selectedOption = "B";
                aRadio.setChecked(false);
                cRadio.setChecked(false);
                dRadio.setChecked(false);
            }
        });
        cText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cRadio.setChecked(true);
                selectedOption = "C";
                bRadio.setChecked(false);
                aRadio.setChecked(false);
                dRadio.setChecked(false);
            }
        });
        dText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dRadio.setChecked(true);
                selectedOption = "D";
                bRadio.setChecked(false);
                cRadio.setChecked(false);
                aRadio.setChecked(false);
            }
        });
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

    private void saveAndQuite(){
        Realm realm;
        realm = Realm.getDefaultInstance();
        QuizzUser user = getUser();

        realm.beginTransaction();

        user.setTotalScore(user.getTotalScore()+questionCorrect);

        Result result = realm.createObject(Result.class, UUID.randomUUID().toString());
        result.setQuizz(quizz);
        result.setScore(new Long(questionCorrect));
        result.setUser(realm.copyToRealmOrUpdate(user));
        realm.commitTransaction();
        Intent i = new Intent(getActivity(), MainActivity.class);
        i.putExtra("quizzUsersName", user.getNickname());
        startActivity(i);
        getActivity().finish();
    }

    private QuizzUser getUser(){
        Realm realm;
        ArrayList<QuizzUser> list = new ArrayList<>();
        realm = Realm.getDefaultInstance();
        RealmResults<QuizzUser> results = realm
                .where(QuizzUser.class)
                .findAll();
        list.addAll(realm.copyFromRealm(results));
        return list.size() > 0 ? list.get(0) : null;

    }
}
