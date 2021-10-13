package uriel.eleazar.tejeiro.garcia.labtoolbarpageview.adaptadores;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.Vector;

public class PagerAdaptador extends FragmentPagerAdapter {
    private int numeroTabs;
    Vector<Fragment> fragments;
    public PagerAdaptador(@NonNull FragmentManager fm, Vector<Fragment> fragments, int behavior) {
        super(fm, behavior);
        this.numeroTabs = behavior;
        this.fragments = fragments;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return this.numeroTabs;
    }
}
