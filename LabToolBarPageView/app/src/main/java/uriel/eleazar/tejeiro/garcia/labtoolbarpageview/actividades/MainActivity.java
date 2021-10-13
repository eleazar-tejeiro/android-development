package uriel.eleazar.tejeiro.garcia.labtoolbarpageview.actividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.util.Vector;

import uriel.eleazar.tejeiro.garcia.labtoolbarpageview.R;
import uriel.eleazar.tejeiro.garcia.labtoolbarpageview.adaptadores.PagerAdaptador;
import uriel.eleazar.tejeiro.garcia.labtoolbarpageview.fragmentos.PrirmerFragment;
import uriel.eleazar.tejeiro.garcia.labtoolbarpageview.fragmentos.SegundoFragment;
import uriel.eleazar.tejeiro.garcia.labtoolbarpageview.fragmentos.TercerFragment;
import uriel.eleazar.tejeiro.garcia.labtoolbarpageview.interfaces.IAdcionarPersona;
import uriel.eleazar.tejeiro.garcia.labtoolbarpageview.interfaces.IEnviarPersona;
import uriel.eleazar.tejeiro.garcia.labtoolbarpageview.modelos.Persona;

public class MainActivity extends AppCompatActivity implements IAdcionarPersona, IEnviarPersona {
    ViewPager viewPager;
    TabLayout tabLayout;
    PagerAdaptador adaptador;
    Vector<Fragment> fragments = new Vector<Fragment>();
    public static final int PRIMER_FRAGMENT = 0;
    public static final int SEGUNDO_FRAGMENT = 1;
    public static final int TERCER_FRAGMENT = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_Toolbar);
        setSupportActionBar(myToolbar);
        fragments.add(new PrirmerFragment());
        fragments.add(new SegundoFragment());
        fragments.add(new TercerFragment());
        configurarTabLayout();
        configurarViewPager();
        configurarListenerTabLayout(this.viewPager);
    }

    private void configurarListenerTabLayout(ViewPager viewPager) {
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void configurarViewPager() {
        viewPager = findViewById(R.id.viewPager);
        adaptador = new PagerAdaptador(getSupportFragmentManager(),fragments,3);
        viewPager.setAdapter(adaptador);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

    }

    private void configurarTabLayout() {
        tabLayout = findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("Tab 1"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 2"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 3"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu1,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onAdicionarPersona(Persona persona) {
        Toast.makeText(this, "Persona recibida en el activity principal", Toast.LENGTH_SHORT).show();
        SegundoFragment segundoFragment = (SegundoFragment) adaptador.getItem(SEGUNDO_FRAGMENT);
        segundoFragment.cargarNuevaPersona(persona);
        viewPager.setCurrentItem(SEGUNDO_FRAGMENT);
    }

    @Override
    public void onEnviarPersona(Persona persona) {
        Toast.makeText(this, "Persona recibida en el activity principal", Toast.LENGTH_SHORT).show();
        TercerFragment tercerFragment = (TercerFragment) adaptador.getItem(TERCER_FRAGMENT);
        tercerFragment.mostrarNuevaPersona(persona);
        viewPager.setCurrentItem(TERCER_FRAGMENT);
    }
}