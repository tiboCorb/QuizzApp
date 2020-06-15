package com.example.quizzapp.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.example.quizzapp.model.Language;
import com.example.quizzapp.model.Option;
import com.example.quizzapp.model.Question;
import com.example.quizzapp.model.Quizz;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmList;

public class InitDataBase extends Service {

    private String [][]  questionsJs = {
            {"Dans quel balise et placé le javascript ?","```<javascript>```","`<scripting>`","`<js>`","`<script>`","D"},
            {"Quel est la syntax correct spour modifier l'élément HTML suivant : \n `<p id=\"demo\"> demo</p>`", "`document.getElementById(\"demo\").innerHTML = \"Hello World!\";`", "` document.getElement(\"p\").innerHTML = \"Hello World!\";`","` #demo.innerHTML = \"Hello World!\";`","` document.getElementByName(\"p\").innerHTML = \"Hello World!\";`", "A"},
            {"Dans quel section placer le javascript ?","La section `<body>`","La section `<head>` ou `<body>` aux choix","Aucune des deux","`<script>`","B"},
            {"Quel est la syntaxe correct pour utilisé un script extérieur ?"," `<script href=\"xxx.js\">`","` <script src=\"xxx.js\">`"," `<script name=\"xxx.js\">`"," `</script src=\"xxx.js\">`","B"},
            {"Comment écrire \"hello world\" dans une alerte ?","` alertBox(\"Hello World\");`"," `msgBox(\"Hello World\");`","` msg(\"Hello World\");`"," `alert(\"Hello World\");`","D"},
    };
    private String [][]  questionsC = {
            {"Pourquoi le nom C++ ?","Parce que c'est l'incrémenation du langage C"," Parce que c'est le langage C plus le langage Javascript","Parce que c'est plus classe","Opérateur `++` y est très important","A"},
            {"Quel est le nom des `#include` au début d'un code ?","Les directives de procédure","Les directives de préprocesseur"," Les directeurs de programme", "le programmes dirigés","B"},
            {"Quelle est l'utilité d'un pointeur ?","Mettre en valeur une variable","Accéder à l'adresse RAM d'une variable","Changer l'adresse RAM d'une variable","c'est un indicateur d'adresse disponible","B"},
            {"Comment faire une allocation dynamique ?","`mallocouille(int)`","`Int *variable = alloc(int)`","`Int *variable = new int`","`Int *variable = point(int)`","C"}
    };
    private String[][] questionJava = {{"Quel opérateur sert pour la concaténation des chaines de caractères ?","`AND`","`+`","`&`","`&&`","B"},
            {"Une variable de classe, commune à toutes les instances d'un classe doit être déclarée","`public`","`private`","`global`","`static`","D"},
            {"Comment afficher le nom de la classe instanciée ?","`this.getName()`","`this.Class()`","`this.getClass()`","`this.className()`","C"},
            {"Le package \"javax.swing\" est utilisé pour","les fenêtres","le réseau","les bases de données","les applets","A"},
            {"Comment peut-on aussi appeler la Machine Virtuelle ?","JDK","JRE","JVE","JRD","B"},
            {"Par convention une classe ","commence par un underscore ( _ )","commence par une majuscule","est en majuscules","est en minuscule","B"},
            {"Si elle n'est pas précisée, la portée d'une variable d'instance dans une classe est ","pas acceptée par le compilateur","publique","privée","locale","B"}};

    Realm realm;
    public InitDataBase() {
    }

    @Override
    public  IBinder onBind(Intent intent) {

        return  null;

    }
    @Override
    public void onCreate() {
        // Code to execute when the service is first created
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startid) {


        realm = Realm.getDefaultInstance();
        creatQuizz("Base su JS", "Javascript", questionsJs);
        creatQuizz("Base du C++","C++",questionsC);
        creatQuizz("Base du java", "Java",questionJava);
        return START_STICKY;
    }


    private void creatQuizz(String  name, String language , String[][] questions ){

        RealmList<Question> quizzQuestions =  new RealmList<>();
        realm.beginTransaction();
        Quizz quizz = realm.createObject(Quizz.class,UUID.randomUUID().toString());
        quizz.setQuizzName(name);
        quizz.setLanguage(language);

        for( String[] question : questions ) {
            quizzQuestions.add(addQuestion(question));
        }

        quizz.setQuestions(quizzQuestions);

        realm.commitTransaction();
    }



    private Question addQuestion(String[] question){



        Question quest =  realm.createObject(Question.class, UUID.randomUUID().toString());
        Option optionA = realm.createObject(Option.class,UUID.randomUUID().toString());
        Option optionB = realm.createObject(Option.class,UUID.randomUUID().toString());
        Option optionC = realm.createObject(Option.class,UUID.randomUUID().toString());
        Option optionD = realm.createObject(Option.class,UUID.randomUUID().toString());
        RealmList<Option> options = new RealmList();

        quest.setTextQuestion(question[0]);
        optionA.setTextAnswer(question[1]) ;
        optionB.setTextAnswer(question[2]) ;
        optionC.setTextAnswer(question[3]) ;
        optionD.setTextAnswer(question[4]) ;

        quest.setOptionA(optionA);
        quest.setOptionB(optionB);
        quest.setOptionC(optionC);
        quest.setOptionD(optionD);


        quest.setAnswer(question[5]);


        return quest;

    }
}
