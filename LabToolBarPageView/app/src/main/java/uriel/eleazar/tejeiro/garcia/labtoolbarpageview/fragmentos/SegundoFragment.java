package uriel.eleazar.tejeiro.garcia.labtoolbarpageview.fragmentos;

import android.app.Person;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

import uriel.eleazar.tejeiro.garcia.labtoolbarpageview.R;
import uriel.eleazar.tejeiro.garcia.labtoolbarpageview.interfaces.IEnviarPersona;
import uriel.eleazar.tejeiro.garcia.labtoolbarpageview.modelos.Persona;

/**
 * A simple {@link Fragment} subclass.
 */
public class SegundoFragment extends Fragment {
    ArrayList <Persona> personas = new ArrayList<Persona>();
    Spinner spinnerPersonas;
    Button btnEnviarPersonas;
    ArrayAdapter <Persona> adaptadorPersonas;
    IEnviarPersona iEnviarPersona;

    // TODO: Rename parameter arguments, choose names that match
    public SegundoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_segundo, container, false);
        spinnerPersonas = view.findViewById(R.id.spinner);
        btnEnviarPersonas = view.findViewById(R.id.buttonEnviarPersona);
        adaptadorPersonas = new ArrayAdapter<Persona>(getContext(),R.layout.support_simple_spinner_dropdown_item,personas);
        spinnerPersonas.setAdapter(adaptadorPersonas);
        //tercer fragment enviar persona
        btnEnviarPersonas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enviarPersona_click();
            }
        });
        return view;
    }

    private void enviarPersona_click() {
        Persona persona = (Persona) spinnerPersonas.getSelectedItem();
        iEnviarPersona.onEnviarPersona(persona);
    }

    public void cargarNuevaPersona(Persona persona) {
        personas.add(persona);
        adaptadorPersonas.notifyDataSetChanged();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        iEnviarPersona = (IEnviarPersona) context;
    }
}