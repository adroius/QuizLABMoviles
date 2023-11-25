package com.example.quizlabmoviles;

import androidx.fragment.app.Fragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentPreguntaExtra#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentPreguntaExtra extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private String respuestaCorrecta;

    public FragmentPreguntaExtra() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentPreguntaExtra.
     */
    public static FragmentPreguntaExtra newInstance(String param1, String param2) {
        FragmentPreguntaExtra fragment = new FragmentPreguntaExtra();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pregunta_extra, container, false);
        ListView list = view.findViewById(R.id.listQuestionDB);
        List<String> answers;
        AdminSQLite admin = new AdminSQLite(getContext());

        // Obtener la pregunta Extra
        Pregunta result=admin.obtenerPreguntaAleatoria();
        
        if (result!=null) {
            TextView pregunta = view.findViewById(R.id.preguntaExtra);
            pregunta.setText(result.getPregunta());
            respuestaCorrecta = result.getRespuestaCorrecta();
            answers = new ArrayList<>();
            answers.add(result.getRespuesta1());
            answers.add(result.getRespuesta2());
            answers.add(result.getRespuesta3());
            answers.add(result.getRespuesta4());

            ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, answers);

            list.setAdapter(adapter);
        }
        //BdD.close();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        ListView list = getView().findViewById(R.id.listQuestionDB);
        list.setClickable(true);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((MainActivity) getActivity()).comprobarExtra(respuestaCorrecta, getView());
            }
        });
    }
}
