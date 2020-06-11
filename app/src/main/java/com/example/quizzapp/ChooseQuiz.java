package com.example.quizzapp;

import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import io.realm.Realm;
import io.realm.RealmResults;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizzapp.model.Quizz;
import com.example.quizzapp.model.QuizzUser;

import java.util.ArrayList;

public class ChooseQuiz extends Fragment {

    ListView listView;
    ArrayList<Quizz> quizzes;

    public static ChooseQuiz newInstance() {
        return new ChooseQuiz();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.choose_quiz_fragment, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        listView = view.findViewById(R.id.listView);
        // now create an adapter class
        quizzes = getQuizList();
        MyAdapter adapter = new MyAdapter(view.getContext(),quizzes);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

              ChooseQuizDirections.ActionChoosequizToCreateFragment action  = ChooseQuizDirections.actionChoosequizToCreateFragment(quizzes.get(position).getQuizzId());
                Navigation.findNavController(view).navigate(action);
            }
        });
    }




    class MyAdapter extends ArrayAdapter<Quizz> {

        Context context;
        ArrayList<Quizz> quizzes;



        MyAdapter (Context c, ArrayList<Quizz> quizzes) {
            super(c, R.layout.row, R.id.textView1, quizzes);
            this.context = c;
            this.quizzes = quizzes;
        }



        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.row, parent, false);
            ImageView images = row.findViewById(R.id.image);
            TextView myTitle = row.findViewById(R.id.textView1);
            TextView myDescription = row.findViewById(R.id.textView2);

            // now set our resources on views
            images.setImageResource(getPic(quizzes.get(position).getLanguage()));
            myTitle.setText(quizzes.get(position).getQuizzName());
            myDescription.setText(quizzes.get(position).getOwner() != null ? quizzes.get(position).getOwner().getNickname() : "");




            return row;
        }

        int getPic(String languageName){
            switch (languageName){
                case "Javascript":
                    return R.drawable.js_icon;
                case "C++" :
                    return R.drawable.c_icon;
                case "Java":
                    return  R.drawable.java;

            }
            return 0;
        }
    }

    public ArrayList<Quizz> getQuizList() {
        ArrayList<Quizz> list = new ArrayList<>();
        Realm realm;
        realm = Realm.getDefaultInstance();
        try {

            RealmResults<Quizz> results = realm
                    .where(Quizz.class)
                    .findAll();
            list.addAll(realm.copyFromRealm(results));
        } finally {
            if (realm != null) {
                realm.close();
            }
        }
        return list;
    }



}
